package com.netty.util;

/**
 * @ClassName IPFormat
 * @Description
 * @Author SkySong
 * @Date 2021-04-29 17:56
 */
@Deprecated
public class IPFormat {

    public static String Format(String IP){
        String[] split = IP.split(":");
        String s = split[0];
        return s.substring(1);
    }
}
