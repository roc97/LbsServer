(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7af08af0"],{"62bf":function(e,t,r){},a0dc:function(e,t,r){"use strict";var s=r("62bf"),a=r.n(s);a.a},dd7b:function(e,t,r){"use strict";r.r(t);var s=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("div",{staticClass:"container"},[r("p",{staticClass:"title"},[e._v("登录")]),r("mt-field",{attrs:{label:"账号",placeholder:"请输入账号"},model:{value:e.userName,callback:function(t){e.userName=t},expression:"userName"}}),r("mt-field",{attrs:{label:"密码",placeholder:"请输入密码",type:"password"},model:{value:e.password,callback:function(t){e.password=t},expression:"password"}})],1),r("div",{staticStyle:{padding:"12px"},on:{touchmove:function(e){e.preventDefault()}}},[r("SliderVerificationCode",{attrs:{height:"40px",sliderWidth:"90px",inactiveValue:"false",activeValue:"true",content:"请滑动通过验证"},model:{value:e.code,callback:function(t){e.code=t},expression:"code"}})],1),r("div",{staticClass:"button-div"},[r("mt-button",{attrs:{type:"primary"},nativeOn:{click:function(t){return e.handleLogin(t)}}},[e._v("登录")]),r("mt-button",{staticClass:"register",attrs:{plain:""},nativeOn:{click:function(t){return e.handleRegister(t)}}},[e._v("注册")])],1),r("LbsNav",{attrs:{propSelected:"user"}})],1)},a=[],n=(r("96cf"),r("3b8d")),i=r("bdaa"),o=(r("4be7"),r("76a0")),c=r("3d01"),u=r("cf45"),d={name:"home",components:{Field:o["Field"],LbsNav:c["a"],Header:o["Header"]},data:function(){return{code:!1,userName:"",password:""}},created:function(){},mounted:function(){},methods:{handleLogin:function(){var e=Object(n["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:if(""!=this.userName.trim()&&""!=this.password.trim()){e.next=3;break}return Object(u["e"])("请填写完整!"),e.abrupt("return");case 3:if(0!=this.code){e.next=6;break}return Object(u["e"])("请滑动验证!"),e.abrupt("return");case 6:return o["Indicator"].open(),e.prev=7,e.next=10,Object(i["s"])(this.userName,this.password);case 10:t=e.sent,200===t.status?(Object(u["f"])("登录成功"),this.$store.commit("set_token",t.token),sessionStorage.setItem("token",t.token),this.$store.commit("set_userInfo",t.user),sessionStorage.setItem("userInfo",JSON.stringify(t.user)),this.$router.push({path:"/user"})):Object(u["e"])(t.msg||t.error),e.next=17;break;case 14:e.prev=14,e.t0=e["catch"](7),Object(u["e"])("网络错误，请稍后重试！");case 17:case"end":return e.stop()}}),e,this,[[7,14]])})));function t(){return e.apply(this,arguments)}return t}(),handleRegister:function(){this.$router.push({path:"/register"})}}},l=d,p=(r("a0dc"),r("2877")),f=Object(p["a"])(l,s,a,!1,null,"59d698e9",null);t["default"]=f.exports}}]);