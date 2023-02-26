# -*- coding: utf-8 -*-
# @Time    : 2022/8/6 11:08
# @Author  : 胖橘
# @File    : re_demo.py
# @Software: PyCharm

# 正则表达式：字符串模式（判断字符串是否符合一定的标准）
import re

# 创建模式对象
# pat = re.compile("AA")  # 此次的AA，是正则表达式，用来去验证其他的字符串
# m = pat.search("CBA")  # search字符串被校验的内容
# m = pat.search("aCBaaAA")
# m = pat.search("aAACBaaAA")  # search方法：进行比对查找

# 没有模式对象
# m = re.search("asd", "basd") # 前面的字符串是规则（模板），后面的字符串是被校验的对象
# print(m)
# m = re.findall("s", "sSDKIODJasffer")  # 前面字符串是规则（正则表达式），后面字符串是被校验的字符串
# m = re.findall("[A-Z]", "sSDKIODJasffer")
# m = re.findall("[A-Z]+", "sODJasffGGeAAAr")
# print(m)


# sub
# print(re.sub("a", "A", "casd"))  # 从第三个字符串中查找a用A替换

# 建议在正则表达式中，被比较的字符串前面加上人r，不用担心转义字符的问题
a = r"\nahhisd-\'"
# a = "\nahhisd-\'"
print(a)