package com.netty.nettyclass;



import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netty.bean.User;
import com.netty.service.impl.UserService;
import com.netty.util.GsonUtil;
import com.netty.util.R;
import org.springframework.ui.ModelMap;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @ClassName main
 * @Description
 * @Author SkySong
 * @Date 2021-04-06 17:56
 */
class UserTest{
    private String id;
    private String name;
    public UserTest(String id,String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserTest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
public class Main {
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) {

        Gson gson = GsonUtil.getGson();

//        UserTest userTest = new UserTest("1","sjdfk");

//
//        System.out.println("1:  "+ gson.toJson(R.data(userTest)));
//
//
//        Map<String,Object> retMap = new HashMap<>();
//        retMap.put("code",200);
//        retMap.put("msg","ok");
//        retMap.put("user",userTest);
//        String retJsonStr = gson.toJson(retMap);
//        System.out.println(retJsonStr);
        String retJsonStr = "{\n" +
                "    \"msg\": \"success\",\n" +
                "    \"code\": 200,\n" +
                "    \"data\": \"[{\\\"userId\\\":\\\"6076af6a241ae30720f6c428\\\",\\\"password\\\":\\\"123\\\",\\\"name\\\":\\\"biantg\\\",\\\"nickName\\\":\\\"tiange\\\",\\\"isOnline\\\":1}]\"\n" +
                "}";

        Type type = new TypeToken<Map<String,Object>>(){}.getType();
        Type type2 = new TypeToken<List<User>>(){}.getType();
        Map<String,Object> resultMap = gson.fromJson(retJsonStr,type);
        List<User> users = gson.fromJson(resultMap.get("data").toString(),type2);
        System.out.println(users.toString());

//        System.out.println(userTest1.toString());
//        UserTest userTest1 = gson.fromJson(outputObj.toString(),UserTest.class);
//
//        System.out.println(userTest1.toString());



    }
}
