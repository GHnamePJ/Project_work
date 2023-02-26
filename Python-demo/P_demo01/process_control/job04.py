# 求200以内能被17整除的最大正整数。
# 第1题
# max= 0
# for i in range(1,201):
#     if i%17 == 0:
#        max=i
#     i+=1
# print(max)

# 第2题
# 鸡兔同笼问题。假设共有鸡、兔30只，脚90只，求鸡、兔各有多少只。
# for x in range(22):
#     if (x*4)+2*(30-x) == 90:
#         print("鸡有:",30-x,"只,兔有：",x,"只")

# 第3题
# 四位玫瑰数是4位数的自幂数。自幂数是指一个 n 位数，它的每个位上的数字的 n 次幂之和等于它本身。
# 例如：当n为3时，有1^3 + 5^3 + 3^3 = 153，153即是n为3时的一个自幂数，3位数的自幂数被称为水仙花数。
# 请输出所有4位数的四位玫瑰数，按照从小到大顺序，每个数字一行。
# for i in range(1000,10000):
#     x=int(i/1000)
#     y=int((i/100)%10)
#     z=int((i/10)%10)
#     k=int(i%10)
#     if x**4+y**4+z**4+k**4==i:
#         print(i)

# 第4题
# 100以内素数之和
# 描述
# 求100以内所有素数之和并输出。
# 素数指从大于1，且仅能被1和自己整除的整数。
# 提示：可以逐一判断100以内每个数是否为素数，然后求和。

sum=0
for num in range(2,101):
    for i in range(2,num):
        if num%i==0:
            break
    else:
        sum+=num
print(sum)

# 第5题i
# 利用嵌套循环打印九九乘法表（分别输出上三角、下三角格式）
# for i in range(1,10):
#     for j in range(1,i+1):
#         sum=i*j
#         print(j,"*",i,"=",sum,end="  ")
#     print()
# print("\n")
# for i in range(9,0,-1):
#     for j in range(1,i+1):
#         sum=i*j
#         print(j,"*",i,"=",sum,end="  ")
#     print()

