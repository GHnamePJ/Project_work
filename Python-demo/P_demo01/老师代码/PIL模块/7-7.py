#图像的对比度增强

from PIL import Image
from PIL import ImageEnhance
im = Image.open('birdnest.jpg')
om = ImageEnhance.Contrast(im)
om.enhance(20).save('birdnestEnContrast.jpg')