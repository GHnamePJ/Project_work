//package org.example.utils;
//
//import com.jni.face.Face;
//import com.jni.face.ImageBuf;
//import com.jni.struct.FaceBox;
//import org.opencv.core.Mat;
//import org.testng.annotations.Test;
//
////@Test
//public class FaceOfflineUtil{
//    public void FaceDetect(String imgPath){
//        Face face=new Face();
//        String modelPath ="J:\\modelPath";
//        int res = face.sdkInit(modelPath);
//        if (res != 0) {
//            System.out.printf("sdk init fail and error =%d\n", res);
//            return;
//        }
////        Mat mat = ImageBuf.getImageMat("d:/test/小李.jpg"); //根据图片路径，读buf示例
//        Mat mat = ImageBuf.getImageMat(imgPath); //根据图片路径，读buf示例
//        if (mat.empty()) {
//            System.out.println("image not exist or empty");
//            System.out.println("图片为空");
//            return;
//        }
//        long matAddr = mat.getNativeObjAddr();
//        // type 0： 表示rgb 人脸检测 1：表示nir人脸检测
//        int type = 0;
//        FaceBox[] boxList = Face.detect(matAddr, type);
//        if (boxList == null || boxList.length <= 0) {
//            System.out.println("detect no face");
//            System.out.println("未检测出人脸");
//            return;
//        }
//        for (int i = 0; i < boxList.length; i++) {
//            // 第几个人脸
//            System.out.println("face index is:" + i);
//            // 人脸框宽度
//            System.out.println("face width is:" + boxList[i].width);
//            // 人脸框高度
//            System.out.println("face height is:" + boxList[i].height);
//            // 人脸角度
//            System.out.println("face angle is:" + boxList[i].angle);
//            // 人脸框中心x坐标
//            System.out.println("face center x is:" + boxList[i].centerx);
//            // 人脸框中心y坐标
//            System.out.println("face center y is:" + boxList[i].centery);
//            // 人脸置信度
//            System.out.println("face score is:" + boxList[i].score);
//        }
//        // sdk销毁，释放内存防内存泄漏
//       face.sdkDestroy();
//       return;
//    }
//
//}
