package org.example.controller;

import org.example.http.Results;
import org.example.pojo.Affair;
import org.example.pojo.User;
import org.example.pojo.face.Result;
import org.example.service.UserService;
import org.example.utils.Base64Util;
import org.example.utils.FaceUtils;
import org.example.utils.JwtUtils;
import org.example.utils.uploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.junit.Test;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes(value={"validate_code"})
@ResponseBody
public class UserController {
    //依赖注入
    @Autowired
    UserService userService;

//    public void setUserService(UserService userService){
//        this.userService=userService;
//    }

    //登录
    @RequestMapping("/register")
    public Results register(
            @RequestBody User user,
            @RequestParam("verify_code") String verifyCode,
            @ModelAttribute("validate_code") StringBuffer validateCode
    ){
        User u=userService.checkUser(user);
        System.out.println(user.getPhone()+user.getPassword());
        System.out.println(validateCode);
        if(u!=null&&verifyCode.equalsIgnoreCase(validateCode.toString())){
            System.out.println(u);
            String token = JwtUtils.sign(u, 60L * 1000L * 30L);//加密
            return new Results(true,token,u);
        }else{
            System.out.println(u);
            return new Results(false,"登录失败");
        }
    }

    //验证手机号是否唯一
    @RequestMapping( "/phone")
    public Results getUserByPhone(@RequestBody String phone){
        User u=userService.getUserByPhone(phone);
        if(u==null){
            return new Results(true,"");
        }else{
            return new Results(false,"手机号已被占用");
        }
    }

    //注册
    @RequestMapping("/login")
    public Results login(@RequestBody User user) throws Exception {
        if(user.getUrl()!=null){//检测到图片
            FaceUtils face=new FaceUtils();
            //1.人脸检测
            String check=face.FaceCheck(user.getUrl());
            //2.人脸注册
            String register=face.FaceRegister(user.getUrl());
            if(check=="检测成功"&&register=="注册成功"){//人脸检测，注册成功
                //3.把图片保存在本地，并返回虚拟路径保存在数据库
                Base64Util base64Util=new Base64Util();
                //获取保存图片的真实路径
                String ImgPath=base64Util.Base64Util(user.getUrl());
                ImgPath=ImgPath.replace('\\', '/');
                System.out.println(ImgPath);
                //虚拟路径
                String newImgPath="/facePhoto/"+ImgPath.substring(ImgPath.lastIndexOf("/")+1);
                System.out.println(newImgPath);
                int rs=userService.insertUser(user.getName(),user.getPhone(),user.getPassword(),newImgPath);
                if(rs!=0){
                    return new Results(true,"注册成功");
                }else{
                    return new Results(false,"注册失败");
                }
            }
            else if(check=="未检测到人脸"||register=="注册失败"){
                return new Results(false,"注册失败");
            }
            else{
                return new Results(false,"图片异常");
            }
        }else{//前端没有图片
            return new Results(false,"图片信息为空");
        }

    }
    //判断用户是否存在
    @RequestMapping("/changePassword")
    public Results changePassword(@RequestBody User user){
        User u=userService.checkUser(user);
        if(u!=null){
            return new Results(true,"成功");

        }else{
            return new Results(false,"失败");
        }
    }
    //修改密码
    @RequestMapping("/updatePassword")
    public Results updatePassword(HttpServletRequest request, @RequestBody String newPassword){
        String phone = request.getHeader("phone");
        System.out.println(newPassword);
        System.out.println(phone);
        int rs=userService.updatePassword(phone,newPassword);
        if(rs>0){
            return new Results(true,"修改密码成功");
        }else{
            return new Results(false,"修改密码失败");
        }

    }
    //判断请求页面是否已登录
    @RequestMapping("/verifyLogin")
    public Results  verifyLogin(){
        System.out.println("验证--》已登录");
        return new Results(true,"已登录");
    }

    //    获取手机号
    @RequestMapping("/verifyPhone")
    public Results getUserPhone(HttpServletRequest request){
        String phone = request.getHeader("phone");
        if (phone == null) {
            return new Results(false, "无");
        } else {
            return new Results(true, "手机号为", phone);

        }
    }
    @RequestMapping("/name")
    public Results  getUserByPhone(HttpServletRequest request) {//修改前数据
        String phone = request.getHeader("phone");
        User u = userService.getUserByPhone(phone);
        if (phone == null) {

            return new Results(false, "请重新输入");
        } else {

            System.out.println(u);
            return new Results(true, "信息", u);
        }

    }

    //判断用户是否存在
    @RequestMapping("/changeName")
    public Results changeName(@RequestBody User user){
        User u=userService.checkUser(user);
        System.out.println("数据"+u);
        if(u!=null){
            return new Results(true,"成功");

        }else{
            return new Results(false,"失败");
        }
    }
    //修改名字
    @RequestMapping("/updateName")
    public Results updateName(HttpServletRequest request, @RequestBody String newName){
        String phone = request.getHeader("phone");
        System.out.println(newName);
        System.out.println(phone);
        int rs = userService.updateNameByPhone(phone,newName);
        if(rs>0){
            return new Results(true,"修改名字成功");
        }else{
            return new Results(false,"修改名字失败");
        }

    }
    //判断用户是否存在
    @RequestMapping("/changePhone")
    public Results changePhone(@RequestBody User user){
        User u=userService.checkUser(user);
        System.out.println("数据"+u);
        if(u!=null){
            return new Results(true,"",u);

        }else{
            return new Results(false,"失败");
        }
    }
    //修改手机号
    @RequestMapping("/updatePhone")
    public Results updatePhone(HttpServletRequest request, @RequestBody String newPhone){
        String phone = request.getHeader("phone");
//        System.out.println("585444====="+newPhone);

        int rs = userService.updatePhoneByPassword(phone,newPhone);
        System.out.println(rs);
        if(rs>0){
            return new Results(true,"修改手机号成功",rs);
        }else{
            return new Results(false,"修改手机号失败");
        }

    }
    //判断用户是否存在
    @RequestMapping("/changeHead_photo")
    public Results changeHead_photo(@RequestBody User user){
        User u=userService.checkUser(user);
        System.out.println("数据"+u);
        if(u!=null){
            return new Results(true,"",u);

        }else{
            return new Results(false,"失败");
        }
    }
    //修改头像图片
    @RequestMapping("/upload")
    public Results registerUser(MultipartFile file,String headPhotopath,HttpServletRequest request) throws IOException {
        String head_photo = uploadUtils.upload(file);
        String phone = request.getHeader("phone");

        int rs = userService.updateHead_photo(phone,head_photo);
        if(rs>0){
            //删除files文件夹下文件名为name.txt的文件
            String path = Base64Util.class.getResource("/").getPath().split("/target")[0];
            File folder = new File(path + "/src/main/webapp/res/headPhoto");
            File[] files = folder.listFiles();
            for(File f:files){
                if(f.getName().equals(headPhotopath)){
                    f.delete();
                }
            }
            return new Results(true,"修改头像成功",head_photo);
        }else{
            return new Results(false,"修改头像失败");
        }
    }

    //修改人脸识别头像图片
    @RequestMapping("/updateFace_photo")
    public Results updateFace_photo(String url,String facePhotopath, HttpServletRequest request) throws Exception {
//        String pathface = facePhotopath.split("/facePhotopath/")[1];
//        System.out.println("测试=" + pathface);
        //        获取 phone 数据
        String phone = request.getHeader("phone");
//        修改 人脸识别图片
        if (url != null) {//检测到图片
            FaceUtils face = new FaceUtils();
            //1.人脸检测
            String check = face.FaceCheck(url);
            if (check == "检测成功") {//人脸检测
                //3.把图片保存在本地，并返回虚拟路径保存在数据库
                Base64Util base64Util = new Base64Util();
                //获取保存图片的真实路径
                String ImgPath = base64Util.Base64Util(url);
                ImgPath = ImgPath.replace('\\', '/');
                System.out.println(ImgPath);
                //虚拟路径
                String newImgPath = "/facePhoto/" + ImgPath.substring(ImgPath.lastIndexOf("/") + 1);
                System.out.println(newImgPath);
                int rs = userService.updateFace_photo(phone,newImgPath);
                if (rs != 0) {
                    //删除files文件夹下文件名为name.txt的文件
                    String path = Base64Util.class.getResource("/").getPath().split("/target")[0];
                    File folder = new File(path + "/src/main/webapp/res/facePhoto");
                    File[] files = folder.listFiles();
                    for(File file:files){
                        if(file.getName().equals(facePhotopath)){
                            file.delete();
                        }
                    }
                    return new Results(true, "修改成功",newImgPath);
                } else {
                    return new Results(false, "修改失败");
                }
            } else if (check == "未检测到人脸") {
                return new Results(false, "修改失败");
            } else {
                return new Results(false, "图片异常");
            }
        } else {//前端没有图片
            return new Results(false, "图片信息为空");
        }
    }

    //签到
    @RequestMapping("/signIn")
    public Results signIn(@RequestBody User user,HttpServletRequest request) throws Exception {
        //检查图片base64是否有人脸
        FaceUtils faceUtils=new FaceUtils();
        faceUtils.FaceCheck(user.getUrl());
        if(faceUtils.FaceCheck(user.getUrl())=="检测成功"){
            //1.签到图片

            //2.数据库获取到图片(根据手机号查虚拟路径转为本地路径)
            String phone=request.getHeader("phone");
            //3.根据手机号查路径
            User u=userService.getUserByPhone(phone);
            //虚拟路径
            String virtualPath=u.getFace_photo();
            //真实路径
            String newImgPath=virtualPath.substring(virtualPath.lastIndexOf("/")+1);
            String path = Base64Util.class.getResource("/").getPath().split("/target")[0];
            String realPath= path+"/src/main/webapp/res/facePhoto/"+newImgPath;
            System.out.println(realPath);
            //对比人脸
            String r=faceUtils.FaceMatch(user.getUrl(),realPath);
            System.out.println(r);
            if(faceUtils.FaceMatch(user.getUrl(),realPath)=="比对成功"){
                System.out.println("比对成功");
                return new Results(true,"比对成功");
//                //事物表改变签到状态,记录签到事件
//
            }else{
                return new Results(false,"签到失败");
            }
        }else{
            return new Results(false,"签到失败");
        }
    }

}
