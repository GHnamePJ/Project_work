# 第1题
# 数字不同数之和
# 获得用户输入的一个整数N，输出N中所出现不同数字的和。
# 例如：用户输入 123123123，其中所出现的不同数字为：1、2、3，这几个数字和为6。
# n = input("请输入一个数：")
# l = []
# sum = 0
# for i in n:
#     l.append(i)
# for i in set(l):
#     sum += eval(i)
# print(sum)

# 第2题
# 人名最多数统计
# 编程模板中给出了一个字符串，其中包含了含有重复的人名，请直接输出出现最多的人名。

# s = '双儿 洪七公 赵敏 赵敏 逍遥子 鳌拜 殷天正 金轮法王 乔峰 杨过 洪七公 郭靖 杨逍 鳌拜 殷天正 段誉 杨逍 慕容复 阿紫 慕容复 郭芙 乔峰 令狐冲 郭芙 金轮法王 小龙女 杨过 慕容复 梅超风 李莫愁 洪七公 ' \
#     '张无忌 梅超风 杨逍 鳌拜 岳不群 黄药师 黄蓉 段誉 金轮法王 忽必烈 忽必烈 张三丰 乔峰 乔峰 阿紫 乔峰 金轮法王 袁冠南 张无忌 郭襄 黄蓉 李莫愁 赵敏 赵敏 郭芙 张三丰 乔峰 赵敏 梅超风 双儿 鳌拜 陈家洛 ' \
#     '袁冠南 郭芙 郭芙 杨逍 赵敏 金轮法王 忽必烈 慕容复 张三丰 赵敏 杨逍 令狐冲 黄药师 袁冠南 杨逍 完颜洪烈 殷天正 李莫愁 阿紫 逍遥子 乔峰 逍遥子 完颜洪烈 郭芙 杨逍 张无忌 杨过 慕容复 逍遥子 虚竹 双儿 ' \
#     '乔峰 郭芙 黄蓉 李莫愁 陈家洛 杨过 忽必烈 鳌拜 王语嫣 洪七公 韦小宝 阿朱 梅超风 段誉 岳灵珊 完颜洪烈 乔峰 段誉 杨过 杨过 慕容复 黄蓉 杨过 阿紫 杨逍 张三丰 张三丰 赵敏 张三丰 杨逍 黄蓉 金轮法王 ' \
#     '郭襄 张三丰 令狐冲 赵敏 郭芙 韦小宝 黄药师 阿紫 韦小宝 金轮法王 杨逍 令狐冲 阿紫 洪七公 袁冠南 双儿 郭靖 鳌拜 谢逊 阿紫 郭襄 梅超风 张无忌 段誉 忽必烈 完颜洪烈 双儿 逍遥子 谢逊 完颜洪烈 殷天正 ' \
#     '金轮法王 张三丰 双儿 郭襄 阿朱 郭襄 双儿 李莫愁 郭襄 忽必烈 金轮法王 张无忌 鳌拜 忽必烈 郭襄 令狐冲 谢逊 梅超风 殷天正 段誉 袁冠南 张三丰 王语嫣 阿紫 谢逊 杨过 郭靖 黄蓉 双儿 灭绝师太 段誉 ' \
#     '张无忌 陈家洛 黄蓉 鳌拜 黄药师 逍遥子 忽必烈 赵敏 逍遥子 完颜洪烈 金轮法王 双儿 鳌拜 洪七公 郭芙 郭襄 赵敏 '
# name = s.split()
# s1 = {}
# for i in name:
#     s1[i] = s1.get(i, 0) + 1
# items = list(s1.items())
# # 按照key值来进行降序排序
# # key=lambda 元素: 元素[字段索引]
# items.sort(key=lambda x: x[1], reverse=True)
# print(items[0][0])

# 第3题
# 字典翻转输出
# 读入一个字典类型的字符串，反转其中键值对输出。
# 即，读入字典key:value模式，输出value:key模式。
# 输入格式
# 用户输入的字典格式的字符串，如果输入不正确，提示：输入错误。
# 输出格式
# 给定字典d，按照print(d)
# try:
#     str1 = eval(input("请输入字符串:"))
#     str2 = {}
#     for key, value in str1.items():
#         key, value = value, key
#         str2[key] = value
#     print(str2)
# except:
#     print("输入错误！！！！")

# 第4题
# 统计输入的字符串中单词的个数，单词之间用空格分隔。
# Str = input("请输入一个字符串：")
# for i in set(Str):
#     print(i,":",Str.count(i),end=",")

# 第5题
# 请去重后给出独特性人名的统计。
# 输出模板中字符串共有多少个独特人名。
# s = '''双儿 洪七公 赵敏 赵敏 逍遥子 鳌拜 殷天正 金轮法王 乔峰 杨过 洪七公 郭靖 杨逍 鳌拜 殷天正 段誉 杨逍 慕容复 阿紫 慕容复 郭芙 乔峰 令狐冲 郭芙 金轮法王 小龙女 杨过 慕容复 梅超风 李莫愁 洪七公
# 张无忌 梅超风 杨逍 鳌拜 岳不群 黄药师 黄蓉 段誉 金轮法王 忽必烈 忽必烈 张三丰 乔峰 乔峰 阿紫 乔峰 金轮法王 袁冠南 张无忌 郭襄 黄蓉 李莫愁 赵敏 赵敏 郭芙 张三丰 乔峰 赵敏 梅超风 双儿 鳌拜 陈家洛 袁冠南
# 郭芙 郭芙 杨逍 赵敏 金轮法王 忽必烈 慕容复 张三丰 杨逍 令狐冲 黄药师 袁冠南 杨逍 完颜洪烈 殷天正 李莫愁 阿紫 逍遥子 乔峰 逍遥子 完颜洪烈 郭芙 杨逍 张无忌 杨过 慕容复 逍遥子 虚竹 双儿 乔峰 郭芙 黄蓉
# 李莫愁 陈家洛 杨过 忽必烈 鳌拜 王语嫣 洪七公 韦小宝 阿朱 梅超风 段誉 岳灵珊 完颜洪烈 乔峰 段誉 杨过 杨过 慕容复 黄蓉 杨过 阿紫 杨逍 张三丰 张三丰 赵敏 张三丰 杨逍 黄蓉 金轮法王 郭襄 张三丰 令狐冲 郭芙
# 韦小宝 黄药师 阿紫 韦小宝 金轮法王 杨逍 令狐冲 阿紫 洪七公 袁冠南 双儿 郭靖 鳌拜 谢逊 阿紫 郭襄 梅超风 张无忌 段誉 忽必烈 完颜洪烈 双儿 逍遥子 谢逊 完颜洪烈 殷天正 金轮法王 张三丰 双儿 郭襄 阿朱 郭襄
# 双儿 李莫愁 郭襄 忽必烈 金轮法王 张无忌 鳌拜 忽必烈 郭襄 令狐冲 谢逊 梅超风 殷天正 段誉 袁冠南 张三丰 王语嫣 阿紫 谢逊 杨过 郭靖 黄蓉 双儿 灭绝师太 段誉 张无忌 陈家洛 黄蓉 鳌拜 黄药师 逍遥子 忽必烈 赵敏
# 逍遥子 完颜洪烈 金轮法王 双儿 鳌拜 洪七公 郭芙 郭襄 '''
# print(len(set(s.split())))

# 第6题
# 输入字符串，为其每个字符的ASCII码形成列表并输出，ord(s[i])获取字符对应ASCII码。
# Str = input("请输入字符串:")
# l = []
# for i in Str:
#     l.append(ord(i))
# print(l)

# 第7题
# NBA球星信息查询 需求描述 以下是一段包括球员信息的文本，要求编写一个程序，让用户能按照自己喜欢的球队查询其所有球员信 息，并格式化打印出来。
# "Carmelo Anthony,Portland Trail Blazers,SF;
# Anthony Davis,Los Angeles Lakers,PF;
# LeBron James,Los Angeles Lakers,SF;
# Kevin Durant,Brooklyn Nets,SF;
# James Harden,Brooklyn Nets,PG;
# Kyrie Irving,Brooklyn Nets,SG;
# Damian Lillard,Portland Trail Blazers,PG"。
# 其中每个分号分隔代表一个球员，球员信息包括姓名、球队、位置，每个特征用逗号隔开。
# text = "Carmelo Anthony,Portland Trail Blazers,SF;Anthony Davis,Los Angeles Lakers,PF;LeBron James,Los Angeles " \
#        "Lakers,SF;Kevin Durant,Brooklyn Nets,SF;James Harden,Brooklyn Nets,PG;Kyrie Irving,Brooklyn Nets," \
#        "SG;Damian Lillard,Portland Trail Blazers,PG "
#
# n = input("请输入球队Los Angeles Lakers|Brooklyn Nets|Portland Trail Blazers:")
# print("+{:-^20}+{:-^28}+{:-^10}+".format("-", "-", "-"))
# print("|{:^20}|{:^28}|{:^10}|".format("name", "team", "position"))
# print("+{:-^20}+{:-^28}+{:-^10}+".format("-", "-", "-"))
# textl = list(text.split(";"))
# # for i in textl:
# #     if n in i:
# #         print("|{:^20}|{:^28}|{:^10}|".format(list(i.split(","))[0], list(i.split(","))[1], list(i.split(","))[2]))
# # print("+{:-^20}+{:-^28}+{:-^10}+".format("-", "-", "-"))
# team_dic = {}
# for player in textl:
#     player_list = player.split(',')
#     name = player_list[0]
#     team = player_list[1]
#     position = player_list[2]
#     play_dic = {'name': name, 'team': team, 'position': position}
#     if team not in team_dic:
#         team_dic[team_dic] = []
#     team_dic[team].append(play_dic)
