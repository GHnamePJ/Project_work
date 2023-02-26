import os

import numpy
import pylab
from PIL import Image


# 根据需求裁剪图像填充图像
def pad_image(image, target_size):
    iw, ih = image.size  # 原始图像的尺寸
    w, h = target_size  # 目标图像的尺寸
    scale = min(w / iw, h / ih)  # 转换的最小比例
    # 保证长或宽，至少一个符合目标图像的尺寸 0.5保证四舍五入
    nw = int(iw * scale + 0.5)
    nh = int(ih * scale + 0.5)
    print("now nums are: ", (nw, nh))
    image = image.resize((nw, nh), Image.BICUBIC)  # 更改图像尺寸，双立法插值效果很好
    new_image = Image.new('RGB', target_size, (0, 0, 0))  # 生成黑色图像
    new_image.paste(image, ((w - nw) // 2, (h - nh) // 2))  # 将图像填充为中间图像，两侧为黑色的样式
    return new_image


# 根据路径打开文件，返回所有图片路径
def Path():
    path = "E:/Python-demo/Face_demo/PythonMysql/cs/AgeDB/"
    faces_list = os.listdir(path)
    os.chdir(path)
    # for i in faces_list:
    #     print(path+i)
    return faces_list


def main():
    faces_list = Path()
    # 标记空数组、存放人脸空数组
    label = numpy.empty(len(faces_list))
    faces = numpy.empty((len(faces_list), 2679))
    k = -1
    # 遍历图片路径
    for i in faces_list:
        k = k + 1
        path = "E:/Python-demo/Face_demo/PythonMysql/cs/AgeDB/"
        img_path = path + i
        print(img_path)
        image = Image.open(img_path)
        # 将图片全部转为灰度
        img_ndarray = numpy.asarray(image, dtype='float64')
        # 规定大小，并转换格式
        # size = (47, 57)
        # pad_image(image, size)  # 填充图像
        # newImage = pad_image(image, size)
        image.save(path+i.replace("jpg", "gif"))
        # 将图片放进数组
        faces[k] = numpy.ndarray.flatten(img_ndarray[0:57, 0:47])
        # 存入标记
        label[k] = k
    label = label.astype(numpy.int)
    return faces, label


if __name__ == '__main__':
    faces, label = main()
    # 测试
    # print(len(faces))
    # print(len(label))
    # for i in range(len(faces)):
    #     img = faces[i].reshape(57, 47)
    #     pylab.imshow(img)
    #     pylab.gray()
    #     pylab.show()


