package com.jhbim.bimvr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadServer implements Runnable{
    private Socket socket;

    public ThreadServer(Socket socket){
        this.socket=socket;

    }
    public Socket getSocket(){
        return this.socket;
    }
    @Override
    public void run() {
        BufferedReader in= null;
        PrintWriter write = null;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        try {
            //获取输出流
            write=new PrintWriter(this.socket.getOutputStream());
            //获取输入流
            in=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String str=in.readLine();

            while(!(str==null||str.equals("end")||str.equals(""))){
                System.out.println(str);
                write.println("服务器(转)"+str);
                write.flush();//强制刷新缓冲区，将缓冲区全部输出
                str=in.readLine();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                //关闭流
                write.close();
                in.close();
                this.getSocket().close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
