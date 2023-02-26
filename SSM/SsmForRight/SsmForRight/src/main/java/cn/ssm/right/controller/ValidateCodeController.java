package cn.ssm.right.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@SessionAttributes(value={"validate_code","user"})//验证码
public class ValidateCodeController {

    @RequestMapping("/generate")
    public void generateValidateCode(HttpServletResponse response, Model model) throws IOException {
        //创建画布
        BufferedImage bufferedImage = new BufferedImage(90, 30, BufferedImage.TYPE_INT_RGB);
        //创建绘制图像对象
        Graphics graphics = bufferedImage.createGraphics();
        //创建颜色对象
        Color color = new Color(200, 200, 255);
        //通过绘制图像对象设置画布颜色、填充指定区域
        graphics.setColor(color);
        graphics.fillRect(0,0,90,30);

        // 定义验证码字符数组
        char[] ch = "ABCDEFGHIJKLMNPQRSTUVWXYZ0123456798".toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < 4; i++) {
            int index = random.nextInt(ch.length);
            stringBuffer.append(ch[index]);
            //设置颜色，字体
            Color color1 = new Color(random.nextInt(22), random.nextInt(33), random.nextInt(66));
            Font font = new Font("Times New Roman", Font.ITALIC, 20);
            graphics.setColor(color1);
            graphics.setFont(font);
            //绘制图形字符到画布上
            graphics.drawString(ch[index]+"", (i * 20) + 5, 20);
        }
        //将validateCode保存至会话中
        model.addAttribute("validate_code", stringBuffer);
//        System.out.println(stringBuffer.toString());
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        // 指定生成的响应是图片
        response.setContentType("image/jpeg");
//        System.out.println(response.getOutputStream());
        // 写入输出流
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }
}
