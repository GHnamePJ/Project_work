package org.example.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class uploadUtils {
    public static String upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String projectpath = Base64Util.class.getResource("/").getPath().split("/target")[0];
        String path =  projectpath+"\\src\\main\\webapp\\res\\headPhoto\\"+ fileName;
        //创建File对象
        File newFile=new File(path);

        //通过CommonsMultipartFile的方法直接写文件
        file.transferTo(newFile);
        String path1 = "/headPhoto/"+fileName;
        return path1;
    }
}
