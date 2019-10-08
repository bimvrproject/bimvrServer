package com.jhbim.bimvr.stauts.unti;

import java.util.Random;
import java.util.UUID;

/**
 * @author shuihu
 * @create 2019-08-19 10:25
 */
public class UUIDUtils {
    public static String getUUID() {

        return UUID.randomUUID().toString().replace("-", "")+UUID.randomUUID().toString().replace("-","");



    }

    public  String findkeyUtil() {
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZqwertyuiopasdfghjklzxcvbnm0123456789";
        StringBuilder st=new StringBuilder(4);
        for(int i=0;i<4;i++){
            char ch=str.charAt(new Random().nextInt(str.length()));
            st.append(ch);
        }
        String findkey=st.toString().toLowerCase();
//        System.out.println("生成找回的key为："+st.toString());
        return findkey;
    }

    public static void main(String[] args) {

//        System.out.println(UUIDUtils.findkeyUtil());
    }
}

