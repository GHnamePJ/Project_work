import glob
import os
import pickle

import numpy
import pymysql

# 定义数据库连接信息
from PIL import Image


# 连接数据库，获取人脸图片信息
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
    # print(res)
    # 提交事务
    conn.commit()
    # 关闭资源
    cursor.close()
    conn.close()
    return res


# 将图片复制
def face():
    res = ImgPath()
    # print(res)
    WSI_MASK_PATH = "J:/face/src/main/webapp/res/facePhoto"  # 存放图片的文件夹路径
    for i in res:
        for j in i.values():
            FaceImgPath = "J:/face/src/main/webapp/res" + j
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
    print(paths)
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
    print(label)
    label = label.astype(numpy.int64)
    print(label)
    for i in range(10):
        label[i * 10:i * 10 + 10] = i
        label = label.astype(numpy.int64)
    print(label)
    # return faces, label
    write_file = open('all.pkl', 'wb')
    pickle.dump(faces, write_file, -1)
    pickle.dump(label, write_file, -1)
    write_file.close()


face()

# 读取原始图片并转化为numpy.ndarray，将灰度值由0～256转换到0～1
#     img = Image.open(paths)
#         # 按比缩小
#         width, height = img.size
#         img.thumbnail((width / 3, height / 3))
#         # 将图片标准化(灰度)
#         img_ndarray = numpy.asarray(img, dtype='float64') / 256
#         # 将图片放进数组
#         faces[k] = numpy.ndarray.flatten(img_ndarray[0:57, 0:47])
#         # 存入标记
#         label[k] = k
# label = label.astype(numpy.int)
# # return faces, label
# write_file = open('demo.pkl', 'wb')
# pickle.dump(faces, write_file, -1)
# pickle.dump(label, write_file, -1)
# write_file.close()
#
#

# def copy():
#
#     # 打开源图片:

#     f_src = open('FaceImgPath','rb')
#     # 读取图片内容并存储到content变量
#     content = f_src.read()
#     # 打开复制后的图片，没有则创建
#     f_copy = open('FaceImgPath.jpg','wb')
#     # 将原图片内容通过二进制形式写入新的图片文件
#     f_copy.write(content)
#     #f_copy.write(content[:-100000])截取图片
#     #
#     f_src.close()
#     f_copy.close()

# def cs():
#     res = ImgPath()
#     faces, label = face(res)
#     # print(label)
#     img1 = faces[0].reshape(57, 47)
#     print(label[1])
#     pylab.imshow(img1)
#     pylab.gray()
#     pylab.show()
#     return faces, label


# cs()


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
