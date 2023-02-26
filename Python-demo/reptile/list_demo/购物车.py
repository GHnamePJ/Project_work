products = [["Lv", 1148], ["Dior", 2339], ["Chanel", 1036], ["Armani", 926], ["BMW", 32121],
            ["Nike", 132], ]
print("----------  商品列表  ----------")
print("{:<9}".format("序 号"), end="")
print("{:<13}".format("商  品"), end="")
print("{:<}".format("价 格"))
k = 1
s = []
# enumerate(products)
# 枚举类型可以打印出下标，遍历数组
for num,i in enumerate(products):
    print("{:<10}".format(num+1), end="")
    for j in i:
        print("{:<14}".format(j), end="")
    print()
print("输入q返回购物车".center(23))
while k == 1:
    n = input("请输入要购买的商品的序号：")
    if n == 'q':
        print("退 出")
        print("----------  购物车  ----------")
        print("{:<9}".format("序 号"), end="")
        print("{:<13}".format("商  品"), end="")
        print("{:<}".format("价 格"))
        num = 1
        s1 = []
        sum = 0
        for num,i in enumerate(s):
            print("{:<10}".format(num+1), end="")
            for j in i:
                print("{:<14}".format(j), end="")
            print()
            sum += i[1]
        print("总额：", sum)
        break
    s.append(products[eval(n) - 1])
    print("购物车加入了", products[eval(n) - 1])
