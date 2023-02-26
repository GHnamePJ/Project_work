# -*- coding: utf-8 -*-
# @Time    : 2022/7/30 10:36
# @Author  : 胖橘
# @File    : urllib_demo.py
# @Software: PyCharm


# urllib库的使用
import urllib.request
import urllib.parse

# 获取一个get请求
# response = urllib.request.urlopen("http://www.baidu.com")
# print(response.read().decode("utf-8"))  # 对获取到的网页源码进行utf-8的解码

# 获取一个post请求
# httpbin.org #可以用来测试post请求,必须传递信息
# data = bytes(urllib.parse.urlencode({"name": "PJ"}), encoding="utf-8")
# response = urllib.request.urlopen("http://httpbin.org/post", data)
# print(response.read().decode("utf-8"))

# 超时处理
# try:
#     response = urllib.request.urlopen("http://httpbin.org/get", timeout=0.1)
#     print(response.read().decode("utf-8"))
# except urllib.error.URLError as e:
#     print("time out!")

# 获取响应头
# response = urllib.request.urlopen("http://baidu.com")
# # print(response.status)
# print(response.getheader("Server"))

# 伪装浏览器发请求method
url = "https://www.douban.com"
# url = "http://httpbin.org/post"
headers = {
    "User-Agent":  "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"

}
data = bytes(urllib.parse.urlencode({"name": "PJ"}), encoding="utf-8")
# 构建请求对象
req = urllib.request.Request(url=url, data=data, headers=headers, method="POST")
# 发送请求
response = urllib.request.urlopen(req)
print(response.read().decode("utf-8"))
