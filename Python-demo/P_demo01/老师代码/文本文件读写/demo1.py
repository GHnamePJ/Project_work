# 理解文本文件和二进制文件的区别
# 如果txt文件是UTF-8编码，则打开文件时编码需要转为utf-8
textFile = open("7-1.txt", "rt", encoding='utf-8')  # t 表示文本文件方式
print(textFile.readline())
textFile.close()
binFile = open("7-1.txt", "rb")  # r 表示二进制文件方式
print(binFile.readline())
binFile.close()
