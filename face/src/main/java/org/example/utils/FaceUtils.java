package org.example.utils;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;

import com.baidu.aip.util.Base64Util;
import org.example.pojo.face.FaceInfo;
import org.example.pojo.face.Result;
import org.example.pojo.face.User_list;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
//人脸注册【2】，人脸更新【1】，人脸检测【1】，人脸检索【1】
public class FaceUtils {
    private AipFace client;
    //报空指针异常？
    //@Before
    //public void init(){
        //client=new AipFace("25946127","aB0OG0BO5pgYSBW93GVGasGN","wrflnad6b1vNZTGcpcwP5h7452M42ULq");
    //}

    //①人脸检测：判断图片中是否有面部信息
    public String FaceCheck(String url) throws Exception {
        try {
            //1.创建java代码与百度云交互的对象
            client = new AipFace("25946127", "aB0OG0BO5pgYSBW93GVGasGN", "wrflnad6b1vNZTGcpcwP5h7452M42ULq");
            //2.构造图片
            //String path = "D:\\test\\小李.jpg";
            //3.上传的图片 两种方法 1.url地址2.Base64字符串形式
            //byte[] bytes = Files.readAllBytes(Paths.get(path));
            //String image = Base64Util.encode(bytes);
            //4.调用api方法进行人脸检测
            /**
             * 参数一：图片的url或图片的Base64字符串;
             * 参数二：图片形式（URL或BASE64）
             * 参数三：hashMap中的基本参数(null,代表使用默认配置)
             */
            JSONObject res = client.detect(url, "BASE64", null);
            //括号里随便写一个数字可以进行一个格式化
            //System.out.println(res.toString(2));
            //解析json数据,获取检测结果
            //需要什么信息，就看看该信息是哪个类的属性。通过创建该类的对象，对象的get,set方法对数据进行操作。
            //res为百度API返回的JSON格式数据
            //将res转为faceInfo的对象，这样我们就可以对数据进行操作了。
            JSON json = JSON.parseObject(String.valueOf(res));
            //转换为java对象
            FaceInfo faceInfo = JSON.toJavaObject(json, FaceInfo.class);
            if (faceInfo.getError_msg().equals("SUCCESS")) {
                System.out.println("人脸检测成功，" + faceInfo.getError_msg());
                return "检测成功";
            } else {
                System.out.println("人脸检测失败,错误信息：" + faceInfo.getError_msg() + ",错误码：" + faceInfo.getError_code());
                return "未检测到人脸";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("图片异常");
            return "人脸检测图片异常";
        }
    }

    //②人脸注册：向百度的人脸库中添加人脸
    public String FaceRegister(String url) throws Exception {
        try {
            client = new AipFace("25946127", "aB0OG0BO5pgYSBW93GVGasGN", "wrflnad6b1vNZTGcpcwP5h7452M42ULq");
            HashMap<String, String> options = new HashMap<>();
            options.put("quality_control", "NORMAL");//图片质量 NONE LOW NORMAL HIGH
            options.put("liveness_control", "LOW");//活体检测（判断是否是真人）
            //4.调用api方法完成人脸注册
            /**
             * 参数一：图片的url或图片的Base64字符串;
             * 参数二：图片形式（URL或BASE64）
             * 参数三：组ID（固定字符串）
             * 参数四：用户ID
             * 参数五：hashMap中的基本参数
             */
            JSONObject res = client.addUser(url, "BASE64", "20125", "111", options);
            JSON json = JSON.parseObject(String.valueOf(res));
            FaceInfo faceInfo = JSON.toJavaObject(json, FaceInfo.class);
            if (faceInfo.getError_msg().equals("SUCCESS")) {
                System.out.println("注册成功，" + faceInfo.getError_msg());
                return "注册成功";
            } else {
                System.out.println("注册失败,错误信息：" + faceInfo.getError_msg() + ",错误码：" + faceInfo.getError_code());
                return "注册失败";
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("图片异常");
            return "图片异常";

        }

    }
    //①人脸检索：根据用户上传的图片和指定人脸库中的所有人脸进行比较，获取相似度最高的一个或者某几个的评分情况（默认找到一个）
    //score，相似度评分（80分以上可以认为是同一个人）
    public String FaceSearch(String url) throws Exception {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(url));
            String encode = Base64Util.encode(bytes);
            client = new AipFace("25946127", "aB0OG0BO5pgYSBW93GVGasGN", "wrflnad6b1vNZTGcpcwP5h7452M42ULq");
            JSONObject res = client.search(encode, "BASE64", "20125", null);
            JSON json = JSON.parseObject(String.valueOf(res));

            FaceInfo faceInfo = JSON.toJavaObject(json, FaceInfo.class);
            Result result = faceInfo.getResult();
            List<User_list> user_list = result.getUser_list();
            for (User_list userList : user_list) {
                if (userList.getScore() >= 80) {
                    System.out.println("检索成功，相似度为" + userList.getScore() + "%");
                    return "检索成功";
                } else {
                    System.out.println("检索失败，相似度为" + userList.getScore() + "%");
                    return "相似度过低";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("图片异常");
            return "图片异常";
        }
        return null;
    }
//    @Test
    //④人脸更新，更新人脸库的照片（年龄增长面部会有变化）
    public void FaceUpdate() {
        try {
            client = new AipFace("25946127", "aB0OG0BO5pgYSBW93GVGasGN", "wrflnad6b1vNZTGcpcwP5h7452M42ULq");
            HashMap<String, String> options = new HashMap<>();
            options.put("quality_control", "NORMAL");//图片质量 NONE LOW NORMAL HIGH
            options.put("liveness_control", "LOW");//活体检测（判断是否是真人）
            String path = "D:/test/小李.jpg";
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            String encode = Base64Util.encode(bytes);
            /**
             * 参数一：图片的url或图片的Base64字符串;
             * 参数二：图片形式（URL或BASE64）
             * 参数三：组ID（固定字符串）
             * 参数四：用户ID
             * 参数五：hashMap中的基本参数
             */
            JSONObject res = client.updateUser(encode, "BASE64", "20125", "2", options);
            JSON json = JSON.parseObject(String.valueOf(res));
            FaceInfo faceInfo = JSON.toJavaObject(json, FaceInfo.class);
            if (faceInfo.getError_msg().equals("SUCCESS")) {
                System.out.println("人脸更新成功，" + faceInfo.getError_msg());
            } else {
                System.out.println("人脸更新失败，错误信息：" + faceInfo.getError_msg() + ",错误码：" + faceInfo.getError_code());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("图片异常");
        }
    }
//    @Test//两张图片对比
    public String FaceMatch(String ImgPath1,String ImgPath2) {
//public String FaceMatch() {
//        public void FaceMatch() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            //第一张图片是签到图片base64,第二张图片是根据数据库虚拟路径转换得到的
//            byte[] bytes1 = FileUtil.readFileByBytes("E:\\SSM\\FaceQD\\src\\main\\webapp\\res\\facePhoto\\232ac78d-6a42-4cbc-b621-ac74ba6b48a4.jpg");
//            byte[] bytes2 = FileUtil.readFileByBytes("E:\\SSM\\FaceQD\\src\\main\\webapp\\res\\facePhoto\\232ac78d-6a42-4cbc-b621-ac74ba6b48a4.jpg");
            byte[] bytes2 = FileUtil.readFileByBytes(ImgPath2);
//            String image1 = Base64Util.encode(bytes1);
            String image2 = Base64Util.encode(bytes2);

            List<Map<String, Object>> images = new ArrayList<>();


            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", ImgPath1);//签到图片的base64
//            map1.put("image", ImgPath1);//签到图片的base64
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NORMAL");

            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.99408f4c6542f08682ec194bdf5e68de.2592000.1657329079.282335-25946127";

            String res = HttpUtil.post(url, accessToken, "application/json", param);
            //转为json字符串
            JSONObject json_str = new JSONObject(res);
            JSON json = JSON.parseObject(String.valueOf(json_str));
            //转为java对象
            FaceInfo faceInfo = JSON.toJavaObject(json, FaceInfo.class);
            System.out.println(faceInfo.getResult().getScore());
            if(faceInfo.getResult().getScore()>=80){
                System.out.println("同一个人");
                return "比对成功";
            }else{
                System.out.println("相似度过低");
                return "相似度过低";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "异常";
        }

    }
//    @Test
//    //获取token
//    public void demo(){
//        AuthService.getAuth();
//    }



}
