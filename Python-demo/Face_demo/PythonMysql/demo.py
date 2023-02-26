from PIL import Image

# img = Image.open('3.gif')
# width, height = img.size
# # 按比例缩小
# img.thumbnail((width / 2, height / 2))
# img.save('3.gif', 'gif')


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


def main():
    img_path = 'pj.gif'
    image = Image.open(img_path)
    size = (47, 57)
    # pad_image(image, size)  # 填充图像
    newImage = pad_image(image, size)
    newImage.save("pj1.gif")


if __name__ == '__main__':
    main()