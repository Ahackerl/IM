package com.netty.controller;

import com.google.gson.Gson;
import com.netty.bean.User;
import com.netty.bean.UserVO;
import com.netty.service.impl.UserService;
import com.netty.util.GsonUtil;
import com.netty.util.R;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {
    private final Gson gson = GsonUtil.getGson();

    @Resource
    private UserService userService;

    @PostMapping("/findUserByName")
    public R findUserByName(@RequestParam(name = "name") String name) {
        System.out.println("name = " + name);

        if (name == null || "".equals(name)) {
            return null;
        }
        User user = userService.findUserByName(name);
        if (user == null) {
            return null;
        }
        System.out.println(user.toString());
        return R.data(user);
    }

    @PostMapping("/addUser")//注册
    public R addUser(UserVO userVO) {
        if (userVO == null) {
            return R.error("参数异常");
        }
        System.out.println(userVO.toString());

        User user = new User();
        user.setName(userVO.getName());
        user.setPassword(userVO.getPassword());
        user.setNickName(userVO.getNickname());

        int i = userService.insertUser(user);
        if (i <= 0) {
            return R.error("插入失败");
        }
        return R.ok();
    }

    //登录（根据用户名和密码查找  之后修改User 的状态）
    //返回整 user 给前端。
    @PostMapping("/login")
    public R login(@RequestParam(name = "name") String userName,
                   @RequestParam(name = "password") String password) {
        //查找
        User user = userService.findUserByName(userName);
        if (user == null) {
            return R.error(401, "userNull");
        }
        if (!user.getPassword().equals(password)) {
            return R.error(400, "passwordError");
        }
        //修改此用户的状态
        long l = userService.updateOnlineStatusByUserName(userName, 1);
        System.out.println("状态修改："+l);
        if (l == 0){
            return R.error(400, "状态修改失败");
        }
        return R.data(user);
    }


    //获取好友列表
    @GetMapping("/getFriendList")
    public R getFriendList(@RequestParam(name = "isOnline", defaultValue = "-1") String isOnline) {
        if ("-1".equals(isOnline)) {
            return R.error("参数错误");
        }
        List<User> userList = userService.getUserListByOnlineStatus(isOnline);
        System.out.println(userList.toString());
        return R.data(gson.toJson(userList));
    }

}
