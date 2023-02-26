# 0x4DC0 是一个十六进制数，它对应的 Unicode 编码是中国古老的《易经》六十四卦的第一卦，请输出第 51 卦（震卦）对应的 Unicode 编码的二进制、十进制、八进制和十六进制格式。
# 方式①利用python的内置函数：bin()、int()、oct()、hex()进行转换
# 方式②利用format对数字格式化
# python中十六进制和十进制相加自动转换为十进制
# 第1题
# print("内置函数二进制：",bin(0x4DC0+51))
# print("内置函数十进制：",int(0x4DC0+51))
# print("内置函数八进制：",oct(0x4DC0+51))
# print("内置函数十六进制：",hex(0x4DC0+51))
# print("format二进制：{:0b}".format(0x4DC0+51))
# print("format十进制：{:}".format(0x4DC0+51))
# print("format八进制：{:0o}".format(0x4DC0+51))
# print("format十六进制：{:0x}".format(0x4DC0+51))

# 统计一个字符串中总字母、英文字母、数字、空格及其他字符出现的次数。
# 提示：
# 是否全为字母str.isalpha(ch)
# 是否全为数字（0-9）str.isdigit(ch)
# 是否只包含空白字符str.isspace(ch)
# 第2题
# str1=str(input("请输入字符串："))
# x,y,z,k=0,0,0,0
# for i in str1:
#    if str.isalpha(i)==True:
#        x+=1
#    if str.isdigit(i) == True:
#        y += 1
#    if str.isspace(i) == True:
#        z += 1
#    else:
#        k+=1
# print("字母:%d"%x,"数字:%d"%y,"空格:%d"%z,"其他:%d"%k)

# 平方根格式化
# 描述获得用户输入的一个整数a，计算a的平方根，保留小数点后3位，并打印输出。
# 输出结果采用宽度30个字符、右对齐输出、多余字符采用加号(+)填充。
# 如果结果超过30个字符，则以结果宽度为准。
# 输入输出示例
# 第3题
# import math
#
# n=int(input("请输入一个整数："))
# if len("math.sqrt(n)") <= 30:
#     print("{:+>30.3f}".format(math.sqrt(n)))
# else:
#     print("{:.3f}".format(math.sqrt(n)))


# 字符串分段组合
# 描述
# 获得输入的一个字符串s，以字符减号(-)分割s，将其中首尾两段用加号(+)组合后输出。
# 第4题
# s=str(input("请输入一个字符串："))
# list=s.split("-")
# print(list[0]+"+"+list[-1])

# 三次方格式化
# 描述
# 获得用户输入的一个数字，可能是整数或浮点数，a，计算a的三次方值，并打印输出。输出结果采用宽度20个字符、居中输出、多余字符采用减号(-)填充。
# 如果结果超过20个字符，则以结果宽度为准。
# 第5题
# n=eval(input("请输入一个数字："))
# value=n**3;
# if len("value") <= 20:
#     print("{:-^20}".format(value))
# else:
#     print("{:}".format(value))

# 星号三角形
# 读入一个整数N，N是奇数，输出由星号字符组成的等边三角形，要求：
# 第1行1个星号，第2行3个星号，第3行5个星号，依次类推，最后一行共N的星号。
# 输入输出示例
# 第6题
# n = eval(input("请输入一个奇数:"))
# for i in range(1,n+1,2):
#     a = "*" * i
#     print(a.center(n))

# 恺撒密码是古罗马恺撒大帝用来对军事情报进行加解密的算法，它采用了替换方法对信息中的每一个英文字符循环替换为字母表序列中该字符后面的第三个字符，即，字母表的对应关系如下：
# 原文：A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
# 密文：D E F G H I J K L M N O P Q R S T U V W X Y Z A B C
# 对于原文字符P，其密文字符C满足如下条件：C=(P+3) mod 26上述是凯撒密码的加密方法，解密方法反之，
# 即：P=(C-3) mod 26假设用户可能使用的输入包含大小写字母a~zA~Z、空格和特殊符号，请编写一个程序，对输入字符串进行恺撒密码加密，
# 直接输出结果，其中空格不用进行加密处理。使用input()获得输入
# 第7题
# str=input("请输入一个字符串：")
# str1='abcdefghijklmnoparstuvwxyz'
# for i in str :
#     if i==" ":
#         print(" ",end="")
#     else:
#         a=str1.find(i)
#         b=(a+3)%26
#         print(str1[b],end="")












