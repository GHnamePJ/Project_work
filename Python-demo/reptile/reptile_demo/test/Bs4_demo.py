# -*- coding: utf-8 -*-
# @Time    : 2022/8/3 22:09
# @Author  : 胖橘
# @File    : Bs4_demo.py
# @Software: PyCharm

# Bs4库的使用
# 文档解析
"""
BeautifulSoup4将复杂HTML文档转换成一个复杂的树型结构，每个节点都是Python对象，所有对象可以归纳为四种：

①Tag
②NavigableString
③BeautifulSoup
④Comment
"""
from bs4 import BeautifulSoup

file = open("baidu.html", "rb")
html = file.read().decode("utf-8")
# 解析
bs = BeautifulSoup(html, "html.parser")

# ①Tag,找到标签及内容：拿到它所找到的第一个内容
# print(bs.a)
# print(type(bs.a))

# ②NavigableString,标签里的内容
# print(bs.title)
# print(type(bs.title.string))

# 标签的信息用字典存储起来
# print(bs.a.attrs)
# print(type(bs.a.attrs))

# ③BeautifulSoup,表示整个文档
# print(bs)
# print(type(bs))

# ④Comment，是一个特殊的NavigableString，输出内容不包括注释符号
# print(bs.a.string)
# print(type(bs.a.string))

# ----------------------------

# 文档的遍历
# print(bs.head.contents)
# print(bs.head.contents[1])

# 文档的搜索
# ①字符串过滤：会查找与字符串完全匹配的内容
# t_list = bs.find_all("a")
# print(t_list)

# ②正则表达式收索：使用search（）方法来匹配内容
# import re
#
# t_list = bs.find_all(re.compile("a"))
# print(t_list)

# ③方法：传入一个函数（方法），根据函数的要求来收索(了解)
# def name_is_exist(tag):
#     return tag.has_attr("name")
#
#
# t_list = bs.find_all(name_is_exist)
# print(t_list)

# ④kwargs 参数
# t_list= bs.find_all(id="head")
# t_list = bs.find_all(class_=True)
# for item in t_list:
#     print(item)

# ⑤text参数
# t_list = bs.find_all(text="hao123")
# t_list = bs.find_all(text=["hao123", "地图", "贴吧"])
# import re
#
# t_list = bs.find_all(text=re.compile("\d"))  # 应用正则表达式来查找包含特定文本的内容（标签里的字符串）
# for item in t_list:
#     print(item)

# ⑥limit 参数
# t_list = bs.find_all("a", limit=3)
#
# for item in t_list:
#     print(item)

# ⑦css选择器
# 按标签查找
# t_list = bs.select("title")
# for item in t_list:
#     print(item)

# 按类名查找
# t_list = bs.select(".mnav")
# for item in t_list:
#     print(item)

# 按id查找
# t_list = bs.select("#u1")
# for item in t_list:
#     print(item)

# 按属性查找
# t_list = bs.select("a[class='bri']")
# for item in t_list:
#     print(item)

# 按子标签查找
# t_list = bs.select("head > title")
# for item in t_list:
#     print(item)

# 按兄弟节点查找
t_list = bs.select(".mnav ~ .bri")
print(t_list[0].get_text())
for item in t_list:
    print(item)


