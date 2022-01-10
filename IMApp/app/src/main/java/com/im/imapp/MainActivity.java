package com.im.imapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.im.imapp.netty.NettyCore;
import com.im.imapp.utils.elf.CopyElfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new NettyCore().registerNetty();

    }


    
}