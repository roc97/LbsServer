package com.roc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roc.pojo.SysUser;
import com.roc.service.UserService;
import com.roc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author p
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomMetaDataSource dataSource;
    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    private AuthenticationAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(dataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        JsonResult jsonResult;
                        if (e instanceof BadCredentialsException ||
                                e instanceof UsernameNotFoundException) {
                            jsonResult = JsonResult.error(ResultEnum.USERNANE_OR_PASSWORD_ERROR.getMsg());
                        } else {
                            jsonResult = JsonResult.error(ResultEnum.LOGIN_FAILURE.getMsg());
                        }
                        httpServletResponse.setStatus(401);
                        PrintWriter writer = httpServletResponse.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(jsonResult));
                        writer.flush();
                        writer.close();
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = httpServletResponse.getWriter();
                        SysUser currentUser = UserUtil.getCurrentUser();
                        JsonResult jr = JsonResult.ok(ResultEnum.LOGIN_SUCCESS.getMsg());
                        Map<String, Object> map = new HashMap<>(16);
                        String token = MD5Utils.MD5Encode(currentUser.getUsername() + currentUser.getPassword()+currentUser.getUserId(),"utf-8");
                        map.put("userId", currentUser.getUserId());
                        map.put("name", currentUser.getName());
                        map.put("userName", currentUser.getUsername());
                        map.put("headImage", currentUser.getHeadImage());
                        jr.put("token", token);
                        jr.put("user", map);
                        Object o = redisUtil.get(token);
                        if (o != null) {
                            redisUtil.expire(token, 60 * 60+redisUtil.getExpire(token));
                        } else {
                            redisUtil.set(token, JsonUtil.obj2String(currentUser), 60 * 60);
                        }
                        writer.write(new ObjectMapper().writeValueAsString(jr));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        String token = httpServletRequest.getHeader("token");
                        redisUtil.del(token);
                        JsonResult jr = JsonResult.ok(ResultEnum.LOGOUT_SUCCESS.getMsg());
                        PrintWriter writer = httpServletResponse.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(jr));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/index.html","/static/**","/login_p","/swagger-ui.html","/v2/**","/swagger-resources/**");
        web.ignoring().antMatchers("/login_p")
                .antMatchers("/static/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/user/register**");
    }
}
