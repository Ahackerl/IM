package com.netty.util;

import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * User: SkySong
 * Date: 23/02/2021
 * Time: 18:31
 * Description: 针对 Gson 的 单例工具类
 */
public class GsonUtil {
    private volatile static GsonUtil gsonUtil;

    private GsonUtil(){
        System.out.println(Thread.currentThread().getName()+"OK");
    }

    public static Gson gson;

    public static Gson getGson(){
        if (gsonUtil == null){
            synchronized (GsonUtil.class){
                if(gsonUtil == null){
                    gsonUtil = new GsonUtil();
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(GsonUtil.getGson());
            }
            ).start();
        }
    }

}
