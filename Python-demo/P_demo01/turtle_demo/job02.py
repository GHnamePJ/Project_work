# 第1题
# 使用turtle库，绘制一个正方形
# import turtle as t
# t.color("slateblue")
# t.pensize(3)
# t.speed(2)
# t.hideturtle()
# for i in range(4):
#     t.fd(150)
#     t.left(90)
# t.done()

# 第2题
# 使用turtle库，绘制一个六边形
# import turtle as t
# t.color("slategray")
# t.pensize(3)
# t.speed(2)
# t.hideturtle()
# for i in range(6):
#     t.fd(80)
#     t.left(60)
# t.done()

# 第3题
# 使用海龟绘图在同一个水平线上分别绘制一个红色的三角形、绿色的正方形、蓝色的正五边形和说明文字。
# 其中红色三角形从原点开始绘制，三个图形间隔100点。最后在与三角形相隔200点之处打印“绘制完成！”的字样。
# import turtle as t
# t.color("red")
# t.pensize(3)
# t.speed(2)
# for i in range(3):
#     t.fd(100)
#     t.left(120)
# t.color("green")
# t.penup()
# t.fd(150)
# t.pendown()
# for i in range(4):
#     t.fd(100)
#     t.left(90)
# t.color("skyblue")
# t.penup()
# t.fd(150)
# t.pendown()
# for i in range(5):
#     t.fd(100)
#     t.left(72)
# t.penup()
# t.bk(500)
# t.pendown()
# t.write("绘制完成！")
# t.done()

# 第4题
# 使用海龟绘图分别绘制红、蓝、绿、黄4种颜色的圆形螺旋。
# import turtle as t
# t.speed(0)
# t.pensize(2)
# colors=[ "red", "blue", "green", "yellow"]
# for i in range(100):
#     t.color(colors[i% 4])
#     t.circle(i)
#     t.left(92)
# t.done()

# 第5题
# 使用plot函数画图,绘制x轴坐标系为0、1、2、3、4，所对应的y轴坐标系值为y=6x+5的直线图。
# import matplotlib.pyplot as plt
# x1= {}
# y1= {}
# for x in range(5):
#     y=int(6*(int(x))+5)
#     x1[x]=x
#     y1[x]=y
# plt.plot([x1[0], x1[1], x1[2], x1[3], x1[4]],[y1[0], y1[1], y1[2], y1[3], y1[4]])
# plt.show()

# 第6题
# turtle叠边形绘制
# 使用turtle库，绘制一个叠边形，其中，叠边形内角为100度。
# 提示：一共9条边，共2圈，每次左转角度为80度（720/9）
# 输出示例
# 叠边形效果如下：
# import turtle as t
# t.color("black")
# t.pensize(3)
# for i in range(9):
#     t.bk(100)
#     t.right(80)
# t.done()

# 第7题
import matplotlib.pyplot as plt

plt.figure(figsize=(8, 8))
plt.figure(1)
ax1 = plt.subplot(211)
plt.plot([1, 2, 3, 4], [1.5, 4, 8, 16], 'ro')
plt.axis([0, 6, 0.0, 20.0])
ax2 = plt.subplot(212)
plt.plot([1, 2, 3, 4.2], [1.5, 4, 8, 16.5], 'g')
plt.show()

# 第8题绘制苹果和橘子12个月的销量条形图，销量数据自己任意定义
# import matplotlib.pyplot as plt
# import numpy as np
# plt.subplot(1, 1, 1)
# fig = plt.figure()
# plt.figure(figsize=(8, 6))
# plt.rcParams['font.sans-serif'] = ['KaiTi']
# plt.rcParams['axes.unicode_minus'] = False
# x = np.array([1, 2, 3, 4,5,6,7,8,9,10,11,12])
# y1 = np.array([566, 335, 610, 482, 424, 875, 831, 257, 731, 480, 488, 911])
# y2 = np.array([140, 367, 283, 667, 655, 241, 666, 812, 432, 576, 777, 555])
# plt.bar(x, y1, width=0.3, label="Apple")
# plt.bar(x + 0.3, y2, width=0.3, label="Orange")
# # plt.bar(水平坐标, 高度, 宽度[, 底坐标], color=颜色,alpha=透明度, label=图例标签)
# plt.title("Bar", loc="center")
# for a, b in zip(x, y1):
#     plt.text(a, b, b, ha='center', va='bottom', fontsize=12)
# for a, b in zip(x + 0.3, y2):
#     plt.text(a, b, b, ha='center', va='bottom', fontsize=12)
# plt.ylabel("Month")
# plt.xlabel("Price")
# plt.xticks(x, ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"])
# plt.grid(False)
# plt.legend()
# plt.show()

# 第9题
# 绘制类似上图的直方图，统计身高从149cm~173cm的人数。
# 要求：组距3cm, 也就是我们将身高从149~173 的人平均分成了8组，然后每组的距离就是3cm。
# 如图，身高在149cm-152cm这个范围内的人数是2个人，身高的范围从158cm-161cm这个范围内的人数是19个人。
# import matplotlib.pyplot as plt
# from matplotlib.font_manager import FontProperties
# plt.rcParams['font.sans-serif'] = ['KaiTi'] # 指定默认字体
# plt.rcParams['axes.unicode_minus'] = False
# font = FontProperties(size=14)
# data=[
#    149, 151,
#    153, 154, 154, 154, 154, 153,
#    156, 156, 156, 157, 156, 156, 157, 156, 156, 157, 156, 156,
#    160, 160, 160, 160, 160, 160, 160, 159, 159, 159, 159, 159, 159, 159, 159, 159, 159, 159, 159,
#    162, 162, 162, 162, 162, 162, 162, 162, 162, 162,
#    165, 165, 166, 165, 165, 166, 165, 165,
#    168, 169, 168, 168,
#    171, 172,
# ]
# bins =range(149,173,3)
# plt.xticks(range(149,173,3))
# plt.yticks(range(0,20,5))
# plt.xlabel('身高/cm')
# plt.ylabel('频数（学生人数）')
# plt.hist(data,bins,edgecolor="black")
# plt.show()
