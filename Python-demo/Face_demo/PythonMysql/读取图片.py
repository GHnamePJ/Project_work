# 读取人脸库olivettifaces，并存储为pkl文件
import matplotlib.pyplot as plt
from matplotlib.image import imread

import numpy
import pylab
from PIL import Image
import pickle

faces = numpy.empty((1, 2679))
# 读取原始图片并转化为numpy.ndarray，将灰度值由0～256转换到0～1
img = Image.open('pj1.gif')
# 将图片标准化(灰度)
img_ndarray = numpy.asarray(img, dtype='float64') / 256
# 将图片放进数组
faces[0] = numpy.ndarray.flatten(img_ndarray[0:57, 0:47])

write_file = open('one.pkl', 'wb')
pickle.dump(faces, write_file, -1)
write_file.close()


# 读取
# read_file = open('one.pkl', 'rb')
# faces = pickle.load(read_file)
# read_file.close()
# img1 = faces[0].reshape(57, 47)
# pylab.imshow(img1)
# pylab.gray()
# pylab.show()

# from PIL import Image
# file_path = 'yu.gif'
# img = Image.open(file_path)
# imgSize = img.size  #大小/尺寸
# w = img.width       #图片的宽
# h = img.height      #图片的高
# f = img.format      #图像格式
# print(imgSize)
# print(w, h, f)

