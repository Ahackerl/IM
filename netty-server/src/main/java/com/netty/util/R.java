package com.netty.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {


    public R() {
        put("code", 200);
        put("msg", "success");
    }

    public static R error(String msg) {
        return error(400, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R data(Object value) {
        return R.ok().put("data", value);
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R data(int code, String msg, Object data) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        r.put("data", data);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
