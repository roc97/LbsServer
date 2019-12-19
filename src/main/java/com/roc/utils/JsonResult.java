package com.roc.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *  页面数据返回信息
 * @author SDG
 *
 */
public class JsonResult extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public JsonResult() {
		put("status", 200);
	}
	
	public static JsonResult error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static JsonResult putValue(String key,Object o){
		JsonResult jr=new JsonResult();
		jr.put(key,o);
		return jr;
	}

	public static JsonResult error(String msg) {
		return error(500, msg);
	}
	
	public static JsonResult error(int status, String msg) {
		JsonResult r = new JsonResult();
		r.put("status", status);
		r.put("msg", msg);
		return r;
	}

	public static JsonResult error(ResultEnum resultEnum) {
		JsonResult r = new JsonResult();
		r.put("status", resultEnum.getCode());
		r.put("msg", resultEnum.getMsg());
		return r;
	}

	public static JsonResult ok(String msg) {
		JsonResult r = new JsonResult();
		r.put("msg", msg);
		return r;
	}

	public static JsonResult ok(ResultEnum resultEnum) {
		JsonResult r = new JsonResult();
		r.put("msg", resultEnum.getMsg());
		return r;
	}

	public static JsonResult ok(JsonResult r,String msg) {
		r.put("msg", msg);
		return r;
	}

	public static JsonResult ok(Map<String, Object> map) {
		JsonResult r = new JsonResult();
		r.putAll(map);
		return r;
	}
	
	public static JsonResult ok() {
		return new JsonResult();
	}

	@Override
	public JsonResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
