package com.jhbim.bimvr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient1 {
    public static void main(String[] args) {
        try {
            Socket socket=new Socket("127.0.0.1",8080);
            System.out.println("客户1：服务已经连接......");
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            PrintWriter write=new PrintWriter(socket.getOutputStream());
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //System.out.println(in.readLine());
            String line=br.readLine();
            //System.out.println(line);
            while(!line.equals("end")){
                //System.out.println(line);
                write.println("客户端1："+line);
                write.flush();
                System.out.println(in.readLine());
                line=br.readLine();
            }
            write.close();
            in.close();
            br.close();
            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
