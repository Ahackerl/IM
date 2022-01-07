package com.im.imapp.utils.elf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BusyBox {

    public String callElf(String cmd,CopyElfs ce){
        Process p;
        String tmpText;
        String execResult = "";

        try {
            p = Runtime.getRuntime().exec(ce.getExecutableFilePath() + "/"+cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((tmpText = br.readLine()) != null) {
                execResult += tmpText+"\n";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return execResult;
    }

}
