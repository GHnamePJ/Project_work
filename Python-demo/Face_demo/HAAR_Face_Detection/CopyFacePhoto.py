import glob
import os
import pickle

import numpy
import pymysql

# 定义数据库连接信息
from PIL import Image


# 连接数据库，获取人脸图片信息
def ImgPath():
    try:
        config = {
            'host': 'localhost',
            'port': 3306,
            'user': 'root',
            'password': '12345678',
            'database': 'face',
            'charset': 'utf8'
        }
        # 获取连接
        conn = pymysql.connect(**config)
        # 获取游标，相当于java中的Statement
        cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)  # 可以指定cursor的类型为字典，查询 结果为Dict类型
        # 执行sql
        sql = ''' select face_photo from face_user '''
        cursor.execute(sql)
        # 获取所有
        res = cursor.fetchall()
        # print(res)
        # 提交事务
        conn.commit()
        # 关闭资源
        cursor.close()
        conn.close()
        return res
    except Exception as e:
        print(e)


# 将图片复制
def face():
    try:
        res = ImgPath()
        # print(res)
        WSI_MASK_PATH = "J:\\face\\src\\main\\webapp\\res\\FacePhoto"  # 存放图片的文件夹路径
        for i in res:
            for j in i.values():
                FaceImgPath = "J:\\face\\src\\main\\webapp\\res" + j
                # 打开图片并复制9张到原路径
                file = open(FaceImgPath, "rb")
                data = file.read()
                name, suffix = os.path.splitext(FaceImgPath)
                for i in range(9):
                    new_file = open(name + str(i) + ".gif", "ab")
                    new_file.write(data)
                file.close()
                new_file.close()
                # 查看图片
                # print("FaceImgPath", FaceImgPath)
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
        write_file = open('J:\\face\\src\\main\\webapp\\res\\python_face\\all.pkl', 'wb')
        pickle.dump(faces, write_file, -1)
        pickle.dump(label, write_file, -1)
        write_file.close()
        if os.path.exists("J:\\face\\src\\main\\webapp\\res\\python_face\\all.pkl"):
            return "True"
        else:
            return "False"

    except Exception as e:
        print(e)
        return "异常"


a = face()
print(a)
