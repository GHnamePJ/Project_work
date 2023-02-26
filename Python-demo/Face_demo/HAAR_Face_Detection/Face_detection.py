from sys import argv

import cv2


# 人脸检测【ok】
from PIL import Image


def Face_detection():
    # dir = r"F:\my_project\opencv\face_recognize"、
    # 人脸彩色图
    path_in = "2bb607a8-9201-4ca2-a2ed-a98d52da23ea.gif"
    image = Image.open(path_in)
    image = image.convert("RGB")
    image.save("JPEG", quality=80, optimize=True, progressive=True)

    # print(argv[1])
    # image = cv2.imread(argv[1])
    # 加载分类器模型
    faceCascade = cv2.CascadeClassifier(
        r"J:\\face\\src\\main\\webapp\\res\\python_face\\haarcascade_frontalface_default.xml")
    # 彩色图转为灰度图片，可用0作为实参替代
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    # 它可以检测出图片中所有的人脸，并将人脸用vector保存各个人脸的坐标、大小(用矩形表示)，并返回numpy向量数组
    faces = faceCascade.detectMultiScale(
        gray,
        scaleFactor=1.15,
        minNeighbors=5,
        minSize=(5, 5)
    )
    # 返回的人脸特征数组
    # for i in range(len(faces)):
    #     print(faces[i])
    # 人脸标准的5个参考特征点坐标#
    # src = np.array([
    # [30.2946+8.0, 51.6963],
    # [65.5318+8.0, 51.5014],
    # [48.0252+8.0, 71.7366],
    # [33.5493+8.0, 92.3655],
    # [62.7299+8.0, 92.2041] ], dtype=np.float32 )

    # 判断是否有人脸
    if len(faces) >= 1:
        # 逐个标注人脸
        for (x, y, w, h) in faces:
            # 矩形标注
            cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
        if len(faces) > 1:
            # 回显框出人脸的图片
            # cv2.imshow("dect", image)
            # 函数的功能是不断刷新图像, 频率时间为delay, 单位为ms
            # 返回值为当前键盘按键值
            # cv2.waitKey()
            # 用来删除窗口的
            # cv2.destroyAllWindows()
            return "图中有多个人脸"

        else:
            return "图中有1个人脸"
            # 回显框出人脸的图片
            # cv2.imshow("dect", image)
            # 函数的功能是不断刷新图像, 频率时间为delay, 单位为ms
            # 返回值为当前键盘按键值
            # cv2.waitKey()
            # 用来删除窗口的
            # cv2.destroyAllWindows()

    else:
        return "图中没有人脸"


a = Face_detection()
print(a)
