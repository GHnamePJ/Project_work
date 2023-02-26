import glob
import os
import pickle
from sys import argv

import numpy
import pymysql

# 定义数据库连接信息
from PIL import Image


# 将图片复制
def face(QdImgPath):
    try:
        WSI_MASK_PATH = "J:\\face\\src\\main\\webapp\\res\\DdPhoto"  # 存放图片的文件夹路径

        # 打开图片并复制9张到原路径
        file = open(QdImgPath, "rb")
        data = file.read()
        name, suffix = os.path.splitext(QdImgPath)
        for i in range(9):
            new_file = open(name + str(i) + ".gif", "ab")
            new_file.write(data)
        file.close()
        new_file.close()
        # 查看图片
        # print("FaceImgPath", QdImgPath)
        #
        paths = glob.glob(os.path.join(WSI_MASK_PATH, '*.gif'))
        # print(paths)
        k = -1
        # 标记空数组、存放人脸空数组
        label = numpy.empty(len(paths))
        faces = numpy.empty((len(paths), 2679))
        for i in paths:
            k = k + 1
            # 读取原始图片并转化为numpy.ndarray，将灰度值由0～256转换到0～1
            img = Image.open(i)
            # 将图片标准化(灰度)
            # 按比缩小
            width, height = img.size
            img.thumbnail((width / 3, height / 3))
            img_ndarray = numpy.asarray(img, dtype='float64') / 256
            # 将图片放进数组
            faces[k] = numpy.ndarray.flatten(img_ndarray[0:57, 0:47])
            # 存入标记
            label[k] = k
        # print(label)
        label = label.astype(numpy.int64)
        # print(label)
        for i in range(10):
            label[i * 10:i * 10 + 10] = i
            label = label.astype(numpy.int64)
        # print(label)
        # return faces, label
        write_file = open('J:\\face\\src\\main\\webapp\\res\\python_face\\one.pkl', 'wb')
        pickle.dump(faces, write_file, -1)
        pickle.dump(label, write_file, -1)
        write_file.close()
        if os.path.exists("J:\\face\\src\\main\\webapp\\res\\python_face\\one.pkl"):
            return "True"
        else:
            return "False"

    except Exception as e:
        print(e)
        return "异常"


# 图片路径
QdImgPath = "J:\\face\\src\\main\\webapp\\res\\QdFace\\03b7b89a-3178-4bfc-bb87-8d6e77934ac0.gif"
# QdImgPath = argv[1]
a = face(QdImgPath)
print(a)
