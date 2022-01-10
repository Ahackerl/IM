package com.im.imapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fishaq.activity.login.FishLoginActivity;
import com.example.fishaq.internet.FishHttp;
import com.im.imapp.os.VarCons;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends FishLoginActivity {


    @Override
    protected void loginToDo(String account, String password) {

        Bundle bundle = new Bundle();
        bundle.putString("name",account);
        bundle.putString("password",password);

        new FishHttp() {
            @Override
            public void OnCallBack(String result) {

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String userId = new JSONObject(data).getString("userId");
                    VarCons.userId = userId;

                    if(200 == code){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }.okPost(VarCons.LOGIN_URL_HTTP,bundle);

    }

    @Override
    protected void registerToDo() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void retrieveToDo() {

    }

    @Override
    protected int setIcon() {
        return R.mipmap.ic_launcher;
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_login;
    }
}
