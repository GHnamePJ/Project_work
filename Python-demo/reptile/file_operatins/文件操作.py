# 写
# text = open("E:\文档\demo.txt", "a");  # 获得文件句柄
# text.write("？？？")
# text.close()

# 读n个字符
# text = open("E:\文档\demo.txt","r")
# txt = text.read(30)
# text.close()
# print(txt)

# 读取整个文本，以列表形式存储，一行表示一个列表元素
"""
text = open("E:\文档\demo.txt","r")
txt = text.readlines()
text.close()
for i in txt:
    print(i)
"""

# 读取某一行
# text = open("E:\文档\demo.txt","r")
# txt = text.readline()
# text.close()
# print(txt)

# 练习1 写文件
# def Wfile():
#     try:
#         file = open("gushi.txt", "w")
#         try:
#             gushi = ["夏日绝句\n", "宋 李清照\n", "死亦为鬼雄\n", "至今思项羽\n", "不肯过江东"]
#             file.writelines(gushi)
#         finally:
#             file.close()
#             print("关闭文件")
#     except Exception as result:
#         print("出错了。。。")
#         print(result)
#
#
# Wfile()


# 练习2 读文件内容并写入新的文件里
# def Rfile():
#     try:
#         file = open("gushi.txt", "r")
#         try:
#             content = file.readlines()
#             try:
#                 file1 = open("copy.txt", "w")
#                 try:
#                     file1.writelines(content)
#                     print("复制完毕")
#                 except Exception as result:
#                     print("写入copy.txt出错啦。。。")
#                     print(result)
#                 finally:
#                     file1.close()
#                     print("\n关闭copy.txt")
#
#             except Exception as result:
#                 print("打开copy.txt出错啦。。。")
#                 print(result)
#
#         except Exception as result:
#             print("读gushi.txt出错了。。。")
#             print(result)
#
#         finally:
#             file.close()
#             print("\n关闭gushi.txt")
#     except Exception as result:
#         print("打开gushi.txt出错了。。。")
#         print(result)
#
#
# Rfile()

# 利用os模块复制文件
import os
import shutil


def copyfile():
    try:
        file = "gushi.txt"
        Copyfile = "Copygushi.txt"
        if not os.path.isfile(file):
            print("该路径下没有找到文件！")
        else:
            shutil.copyfile(file, Copyfile)
            print("复制完毕")
    except Exception as result:
        print("出错啦。。。")
        print(result)


copyfile()
