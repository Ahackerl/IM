package com.netty.nettyclass;

import java.util.HashMap;
import java.util.Map;

public class DoubleMap<K,V> extends HashMap<K,V> {


    public K getKey(V value){
        K key = null;
        for (Map.Entry<K,V> entry : this.entrySet()) {
            if(value.equals(entry.getValue())){
                key=entry.getKey();
            }
        }
        return key;
    }
}
