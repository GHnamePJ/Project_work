# 第1题
# 无空隙回声输出（获得用户输入，直接打印输出。）
# 获得用户输入，去掉其中全部空格，将其他字符按收入顺序打印输出。
# print(input("请输入：").replace(" ", ""))

# 第2题
# 文件关键行数（集合去重）
# 关键行指一个文件中包含的不重复行。关键行数指一个文件中包含的不重复行的数量。附件文件：latex.log
# 统计附件文件中与关键行的数量。
# file = open("latex.log", "r").readlines()
# l = len(set(file))
# print("共", l, "关键行")



# 第3题
# 字典翻转输出
# 读入一个字典类型的字符串，反转其中键值对输出。
# 即，读入字典key:value模式，输出value:key模式。
# 输入格式
# 用户输入的字典格式的字符串，如果输入不正确，提示：输入错误。
# 输出格式
# 给定字典d，按照print(d)方式输出
# while 1:
#     try:
#         d = eval(input("请输入一个字典格式的数据:"))
#         d1 = {}
#         for key, value in d.items():
#             key, value = value, key
#             d1[key] = value
#         print(d1)
#         break
#     except:
#         print("格式错误!!!!")

# 第4题（jieba）
# 《沉默的羔羊》之最多单词
# 附件是《沉默的羔羊》中文版内容，请读入内容，分词后输出长度大于2且最多的单词。
# 如果存在多个单词出现频率一致，请输出按照Unicode排序后最大的单词。
# import jieba
#
# file = open("沉默的羔羊.txt", "r", encoding='utf-8').read()
# # 精准分词
# l = jieba.lcut(file)
# zd = {}
# # 长度大于2用字典保存并计数
# for i in l:
#     if len(i) <= 2:
#         l.remove(i)
#     else:
#         zd[i] = zd.get(i, 0) + 1
# # 用列表进行排序
# items = list(zd.items())
# items.sort(key=lambda x: x[1], reverse=True)
# # 排序后最大的单词
# print(items[0][0])

# 第5题
# 以下为北京2016年全年各月份测量数据
# 降水量：2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3
# 蒸发量为：2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3
# 请根据数据画出表示降水量和蒸发量对应关系的散点图。
import pyecharts.options as opts
from pyecharts.charts import Scatter

# X,Y数据
data = [[2.0, 2.6], [4.9, 5.9], [7.0, 9.0], [23.2, 26.4], [25.6, 28.7], [76.7, 70.7], [135.6, 175.6], [162.2, 182.2],
        [32.6, 48.7], [20.0, 18.8], [6.4, 6.0], [3.3, 2.3],
        ]
scatter = Scatter()
# 对X数据排序
data.sort(key=lambda x: x[0])
x_data = [d[0] for d in data]  # x数据
y_data = [d[1] for d in data]  # y数据
c = (
    # 初始化散点图
    Scatter(init_opts=opts.InitOpts(width="900px", height="600px"))
        .add_xaxis(xaxis_data=x_data)
        .add_yaxis(
        # 系列名称
        series_name="散点图",
        # 系列数据
        y_axis=y_data,
        # 标记的大小
        symbol_size=20,
        # 标记的图形
        symbol=None,
        # 是否选中图例
        is_selected=True,
        # 系列 label 颜色
        color='#00CCFF',
        # 标签配置项
        label_opts=opts.LabelOpts(is_show=False),  # 不显示标签
    )
        # 系统配置项
        .set_series_opts()
        # 全局配置项
        .set_global_opts(
        # x轴配置
        xaxis_opts=opts.AxisOpts(
            name='x轴',
            name_location='center',
            name_gap=15,
            # 坐标轴类型 'value': 数值轴
            type_="value",
            # 分割线配置项
            splitline_opts=opts.SplitLineOpts(is_show=True)  # 显示分割线
        ),
        # y轴配置
        yaxis_opts=opts.AxisOpts(
            name='y轴',
            # 坐标轴类型 'value': 数值轴
            type_="value",
            # 坐标轴刻度配置项
            axistick_opts=opts.AxisTickOpts(is_show=True),  # 显示刻度
            # 分割线配置项
            splitline_opts=opts.SplitLineOpts(is_show=True),  # 显示分割线
        ),
        # 提示框配置项
        tooltip_opts=opts.TooltipOpts(is_show=False),  # 不显示提示框组件
    )
        .render("散点图.html")
)

# 第6题
# 某公司产品在广东省内的销量（万件）有如下统计：
# 汕头市20，汕尾市190，揭阳市253，阳江市77，肇庆市65
# 请以Geo或Map显示广东省地图并标识以上数据。
# from pyecharts.charts import Geo
# import pyecharts.options as opts
#
# data = [
#     ("汕头市", 20), ("汕尾市", 190), ("揭阳市", 253), ("阳江市", "77"), ("肇庆市", 65), ]
# geo = Geo()
# geo.add_schema(maptype="广东")
# geo.add("data from pm2.5", data)
# geo.set_series_opts(label_opts=opts.LabelOpts(is_show=False))  # 设置是否显示标签
# geo.set_global_opts(visualmap_opts=opts.VisualMapOpts(), title_opts=opts.TitleOpts(title="广东部分城市空气质量"))
# geo.render("广东部分城市空气质量.html")
