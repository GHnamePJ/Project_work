import pickle

import numpy
import pylab
import pymysql

# 定义数据库连接信息
from PIL import Image


# 连接数据库，获取图片路径
def ImgPath():
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
    # print(a)
    # 提交事务
    conn.commit()
    # 关闭资源
    cursor.close()
    conn.close()
    return res


# 将人脸与标记分别放入数组里
def face(res):
    # print(len(a))
    # 用于输出人脸，与标记
    k = -1
    # 标记空数组、存放人脸空数组
    label = numpy.empty(len(res))
    faces = numpy.empty((len(res), 2679))
    for i in res:
        for j in i.values():
            k = k + 1
            # 转为真实路径
            FaceImgPath = "E:/SSM/face/src/main/webapp/res" + j
            # 查看图片
            print(FaceImgPath)

            # 读取原始图片并转化为numpy.ndarray，将灰度值由0～256转换到0～1
            img = Image.open(FaceImgPath)
            # 按比缩小
            width, height = img.size
            img.thumbnail((width / 3, height / 3))
            # 将图片标准化(灰度)
            img_ndarray = numpy.asarray(img, dtype='float64') / 256
            # 将图片放进数组
            faces[k] = numpy.ndarray.flatten(img_ndarray[0:57, 0:47])
            # 存入标记
            label[k] = k
    label = label.astype(numpy.int)
    return faces, label


def cs():
    res = ImgPath()
    faces, label = face(res)

    # img1 = faces[0].reshape(57, 47)
    # print(label[1])
    # pylab.imshow(img1)
    # pylab.gray()
    # pylab.show()
    return faces, label


cs()

# 保存图片以及标记到Face.pkl文件
# write_file = open('Face.pkl', 'wb')
# # 负数或HIGHEST_PROTOCOL使用 highest protocol
# # pickle.dump(faces, write_file, -1)
# # pickle.dump(img_label, write_file, -1)
# write_file.close()

# 回显图片
# read_file = open('Face.pkl', 'rb')
# faces = pickle.load(read_file)
# # label = pickle.load(read_file)
# read_file.close()
# img1 = faces[0].reshape(57, 47)
# print(faces[1][0])
# pylab.imshow(img1)
# pylab.gray()
# pylab.show()
# print(img1)
