# # 读取人脸库olivettifaces，并存储为pkl文件
import numpy
import pylab
from PIL import Image
import pickle

# 读取原始图片并转化为numpy.ndarray，将灰度值由0～256转换到0～1
img = Image.open('3.jpg')
# 将图片标准化
img_ndarray = numpy.asarray(img, dtype='float64') / 256

# 创建给定数量的标记的空数组
img_label = numpy.empty(1)
img_label = 1
# 保存图片以及标记到Face.pkl文件
write_file = open('Face.pkl', 'ab')
# 负数或HIGHEST_PROTOCOL使用 highest protocol
pickle.dump(img_ndarray, write_file, -1)
pickle.dump(img_label, write_file, -1)
write_file.close()


# 回显图片
read_file = open('Face.pkl', 'rb')
faces = pickle.load(read_file)
read_file.close()
img1 = faces
pylab.imshow(img)
pylab.gray()
pylab.show()
