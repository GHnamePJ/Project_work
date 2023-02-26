#图像的轮廓获取

from PIL import Image
from PIL import ImageFilter
im = Image.open('birdnest.jpg')
om = im.filter(ImageFilter.CONTOUR)
om.save('birdnestContour.jpg')