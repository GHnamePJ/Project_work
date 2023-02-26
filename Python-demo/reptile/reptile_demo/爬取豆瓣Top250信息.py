# -*- coding: utf-8 -*-
# @Time    : 2022/7/29 22:18
# @Author  : 胖橘
# @File    : 爬取豆瓣Top250信息.py
# @Software: PyCharm 

# 爬取电影名称,豆瓣评分,评价数,电影概况,电影连接
# https://movie.douban.com/top250

from bs4 import BeautifulSoup  # 网页解析，获取数据
import re  # 正则表达式，进行文字匹配
import urllib.request  # 制定URL，获取网页数据
import xlwt  # 进行excel操作
import sqlite3  # 进行数据库的操作


def main():
    baseurl = "https://movie.douban.com/top250?start="
    # 1.爬取网页，逐一解析数据
    datalist = getData(baseurl)

    savepath = ".\\豆瓣电影Top250.xls"
    # 2.保存数据
    # saveData(savepath)


# 电影链接规则
findLink = re.compile(r'<a href="(.*?)">')  # 创建正则表达式对象，表示规则（字符串模式）
# 电影图片链接规则
findImgSrc = re.compile(r'<img.*src="(.*?)"', re.S)  # re.S让换行符包含在字符中
# 电影名规则
findTitle = re.compile(r'<span class="title">(.*)</span>')
# 电影评分规则
findScore = re.compile(r'<span class="rating_num" property="v:average">(.*)</span>')
# 评价人数规则
findJudge = re.compile(r'<span>(\d*)人评价</span>')
# 电影概况规则
findOverview = re.compile(r'<span class="inq">(.*)</span>')
# 电影相关内容规则
findContent = re.compile(r'<p class="">(.*?)</p>', re.S)


# 爬取网页，逐一解析数据
def getData(baseurl):
    datalist = []
    # 调用获取页面信息的函数
    for i in range(0, 10):
        url = baseurl + str(i * 25)
        # 保存获取到的网页源码
        html = askURL(url)
        # 逐一解析数据
        soup = BeautifulSoup(html, "html.parser")
        for item in soup.find_all('div', class_='item'):  # 查找符合要求的字符串，形成列表
            # print(item)  # 测试查看电影全部信息
            data = []  # 保存一部电影的所有信息
            item = str(item)
            # 电影链接
            Link = re.findall(findLink, item)[0]  # re库用来通过正则表达式查找指定字符串
            # print(Link)
            data.append(Link)
            # 图片链接
            ImgSrc = re.findall(findImgSrc, item)[0]
            # print(ImgSrc)
            data.append(ImgSrc)
            # 名称(可能会有两个名称，一个中文一个外文)
            Titles = re.findall(findTitle, item)
            if (len(Titles) == 2):
                cTitle = Titles[0]
                data.append(cTitle)  # 添加中文名
                oTitle = Titles[1].replace("/", "")
                oTitle = re.sub("\xa0\xa0", "", oTitle)  # 去掉\xa0\xa0
                data.append(oTitle)  # 添加外文名
            else:
                data.append(Titles[0])
                data.append(" ")  # 外文名留空
            # 评分
            Score = re.findall(findScore, item)[0]
            data.append(Score)
            # print(Score)
            # 人数
            Judge = re.findall(findJudge, item)[0]
            # print(Judge)
            data.append(Judge)
            # 概况(可能有些没有概况)
            Overview = re.findall(findOverview, item)
            if (len(Overview) != 0):
                Overview = Overview[0].replace("。", "")  # 去掉句号
                data.append(Overview)
                # print(Overview)
            else:
                data.append(" ")
                # print(" ")
            # 内容
            Content = re.findall(findContent, item)[0]
            Content = re.sub("<br(\s+)?/>(\s+)?", " ", Content)  # 去掉<br/>
            Content = re.sub("/", " ", Content)  # 替换/
            data.append(Content.strip())  # 去掉空格
            datalist.append(data)
            print(data, end="\n")
    return datalist


# 得到一个指定url的网页内容
def askURL(url):
    # head模拟浏览器头部信息，想豆瓣浏览器发送信息
    head = {
        # 用户代理：表示告诉豆瓣服务器，我们是什么类型的机器（本质上是告诉浏览器，我们可以接受什么水平的文件内容）
        # 不能空格！！！！
        "User-Agent": "Mozilla / 5.0(Windows NT 10.0;WOW64) AppleWebKit / 537.36(KHTML, likeGecko) Chrome / 86.0.4240.198Safari / 537.36"
    }
    # 封装请求对象
    request = urllib.request.Request(url, headers=head)

    html = ""
    try:
        # 向浏览器发送请求
        response = urllib.request.urlopen(request)
        html = response.read().decode("utf-8")
        # print(html)
    except Exception as result:
        print(result)
    return html


# 保存数据
def saveData(savepath):
    pass


if __name__ == "__main__":
    main()
