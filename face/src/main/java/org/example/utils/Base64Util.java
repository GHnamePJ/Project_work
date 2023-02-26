package org.example.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class Base64Util {
    /**
     * 测试base64转图片，并存储到本地
     * @param base64 base64字符串
     *
     */
    public String Base64Util(String base64) throws IOException {
        byte[] bs = new byte[1024];
        bs = Base64.getMimeDecoder().decode(base64);
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        String path = Base64Util.class.getResource("/").getPath().split("/target")[0];
        System.out.println(Base64Util.class.getClassLoader().getResource("/").getPath());
        file = new File(path+"\\src\\main\\webapp\\res\\facePhoto\\"+ UUID.randomUUID()+".jpg");
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file.toString();
    }
}


