package com.jhbim.bimvr;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private int port=8080;//默认服务器端口
    public void setPort(int port){
        this.port=port;
    }
    public int getPort(){
        return this.port;
    }
    public void servers(){
        try {
            ServerSocket server=new ServerSocket(this.getPort());
            int i=0;
            while(true){
                //等待客户端连接
                Socket socket=server.accept();
                i++;
                System.out.println("第"+i+"个客户端连接！");
                //连接成功后创建一个新的线程实现服务器通信服务
                new Thread(new ThreadServer(socket)).start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException  {
        new TCPServer().servers();
    }
}
