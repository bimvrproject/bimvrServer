package com.jhbim.bimvr;

import com.jhbim.bimvr.utils.Ftp;
import com.jhbim.bimvr.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)

@SpringBootTest
public class BimvrApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        String aa="/";
        String bb="C:\\Users\\Administrator\\Desktop\\新建文件夹 (3)";
        Ftp ftpFileUpload = Ftp.getSftpUtil();
        ftpFileUpload.upload(aa,bb);
    }
    @Test
    public void   aa(){

        String str="1231asdada12d1a2s3d1a32sd";
        String regex = "[a-z0-9A-Z]+$";
        if(str.matches(regex)==false){
            System.out.println(false);
        }else {
            System.out.println(true);

            System.out.println( MD5Util.encrypt(str));
        }
    }

}
