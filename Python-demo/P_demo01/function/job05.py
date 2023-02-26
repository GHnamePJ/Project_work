# 第1题
import random


def genpwd(length):
    for j in range(length):
        a=random.randint(10**(length-1),10**length)
        return a


length1 = eval(input())
random.seed(17)
for i in range(3):
    print(genpwd(length1))

# 第2题
# n = eval(input())
# n1 = int(n)
# k = 0
# for num in range(n, 10*n):
#     for i in range(2, num):
#         if num % i == 0:
#             break
#     else:
#         print(num, end=",")
#         k += 1
#         if k >= 5:
#             break

# 第3题

# def cmul(*n):
#     sum = 1
#     for i in n:
#         sum = sum * i
#     return sum
#
#
# print(eval("cmul({})".format(input())))

# 第四题
# 根据编程模板补充代码，计算斐波那契数列的值，具体功能如下：‬ 获取用户输入整数 N，其中，N 为正整数 2.
# 计算斐波那契数列的值如果将斐波那契数列表示为 fbi(N)，对于整数 N，值如下：‬
# fbi(1)和 fbi(2)的值是 1，当 N>2 时，fbi(N) = fbi(N-1) + fbi(N-2)
# 请采用递归方式编写。

# def fbi(n):
#     if n == 1 or n == 2:
#         return 1
#     if n > 2:
#         f = fbi(n - 1) + fbi(n - 2)
#         return f
#
#
# n = eval(input())
# print(fbi(n))

# 第5题
# 编写一个程序， 利用可变参数定义一个求任意个数值的最小值的函数
# min_n(a,b,*c),并编写测试代码。测试代码如下：‬
# print("最小值为", min_n(8, 2))
# print("最小值为", min_n(16, 1, 7, 4, 15))
# 输出结果如下：‬
# 最小值为 2
# 最小值为 1
# def min_n(a, b, *c):
#     m = a
#     if b < a:
#         m = b
#
#     for i in c:
#         c_min = c[0]
#         if i < c_min:
#             c_min = i
#         if c_min < m:
#             return c_min
#         else:
#             return m
#     return m
#
#
# print("最小值为", min_n(8, 2))
# print("最小值为", min_n(16, 1, 7, 4, 15))
