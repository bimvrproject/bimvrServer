package com.jhbim.bimvr.utils;

import java.io.*;
import java.net.Socket;

public class ReceiveFile {
    public static void receiveFile(Socket socket) throws IOException {
        byte[] inputByte = null;
        int length = 0;
        DataInputStream din = null;
        DataOutputStream dout = null;
        FileOutputStream fout = null;

        try {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            //接收文件数量
            byte[] numByte = new byte[256];
            int file_num = din.read(numByte);
            byte[] NumByte = new byte[file_num];
            for (int i = 0; i < file_num; i++) {
                NumByte[i] = numByte[i];
            }
            String fileNumStr = new String(NumByte);
            int fileNum = Integer.parseInt(fileNumStr);
            if (fileNum > 0) {
                //回复确认
                String num_back = "num_recv";
                byte[] num_back_byte = num_back.getBytes();
                dout.write(num_back_byte, 0, num_back.length());
//            	dout.writeBytes(num_back);

                for (int index = 0; index < fileNum; index++) {
                    //接收文件名
                    byte[] nameByte = new byte[256];
                    int file_name_len = din.read(nameByte);
                    //                    din.read()
                    byte[] NameByte = new byte[file_name_len];
                    for (int i = 0; i < file_name_len; i++) {
                        NameByte[i] = nameByte[i];
                    }
                    System.out.println(NameByte);
                    String file_name = new String(NameByte);
                    System.out.println(file_name);
                    String file_path = "D:\\Tomcat9\\apache-tomcat-9.0.27\\webapps\\ROOT\\picture\\" + file_name;
                    System.out.println(file_path);
                    //回复确认
                    String name_back = "name_recv";
                    byte[] name_back_byte = name_back.getBytes();
                    dout.write(name_back_byte, 0, name_back.length());

                    //接收文件大小
                    byte[] sizeByte = new byte[256];
                    int file_size = din.read(sizeByte);
                    byte[] SizeByte = new byte[file_size];
                    for (int i = 0; i < file_size; i++) {
                        SizeByte[i] = sizeByte[i];
                    }
                    String fileSizeStr = new String(SizeByte);
                    int fileSize = Integer.parseInt(fileSizeStr);
                    System.out.println(fileSize);
                    //回复确认
                    String size_back = "size_recv";
                    byte[] size_back_byte = size_back.getBytes();
                    dout.write(size_back_byte, 0, size_back.length());

                    File file = new File(file_path);
                    fout = new FileOutputStream(file);
//                     inputByte = new byte[1024];
                    System.out.println("开始接收数据...");
                    while (fileSize > 0) {
                        if (din != null) {
                            if (fileSize - 1024 < 0)
                                inputByte = new byte[fileSize];
                            else
                                inputByte = new byte[1024];
                            length = din.read(inputByte, 0, inputByte.length);

                        }
                        if (length == -1) {
                            break;
                        }
                        fout.write(inputByte, 0, length);
                        fileSize -= length;
                        fout.flush();
                    }
                    fout.close();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//        	if (fout != null)
//                fout.close();
//            if (din != null)
//                din.close();
//            if (socket != null)
//                socket.close();
        }
    }
}
