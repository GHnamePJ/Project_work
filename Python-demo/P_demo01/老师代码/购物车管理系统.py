import os
import json
class Shop_car:
    def __init__(self):
        # 保存商品信息的文件
        self.file_path = "shop_info.txt"
        self.shop_over_info = []
        # 总数量
        self.all_count = 0
        # 总介价格
        self.all_price = 0
# 1.跟我写的一样：数据要5条
# 2.删除指令
    def orders(self):
        # 商品id,商品名，商品价格，商品数量
        print("指令1：向购物车内添加商品：")
        print("指令2：显示购物车内的商品信息：")
        print("指令3：修改商品数量：")
        print("指令4：购物车商品结算：")
        print("指令5：退出购物车系统：")
        ord = input("请输入指令：")
        return ord
    # 添加商品信息
    def add_shop_info(self):
        while True:
            res = input("是否要添加商品（y/其他为否）：")
            if res == "y":
                shop_info = {}
                shop_info["shop_id"] = input("请输入商品id:")
                shop_info["shop_name"] = input("请输入商品名:")
                shop_info["shop_price"] = input("请输入商品价格:")
                shop_info["shop_count"]= input("请输入商品数量:")
                self.save_info(shop_info)
            else:
                break
    # 保存商品信息
    def save_info(self,shop_dic):
        if os.path.exists(self.file_path): # 判断文件是否存在，结果是布尔值
            with open(self.file_path,"a") as f:
                f.write(json.dumps(shop_dic,ensure_ascii=False)+"\n")
        else:
            with open(self.file_path,"w") as f:
                f.write(json.dumps(shop_dic,ensure_ascii=False)+"\n")
    # 显示购物车内的商品信息
    def show_shop_info(self):
        if os.path.exists(self.file_path):
            with open(self.file_path,"r") as f:
                shop_list = f.readlines()
            print("*************************************************************")
            for shop in shop_list:
                shop = json.loads(shop) # 将json转成字典
                print("商品id：{}  商品名：{}  商品价格：{}  商品数量：{}".format
                      (shop["shop_id"],shop["shop_name"],shop["shop_price"],shop["shop_count"]))
            print("*************************************************************")
        else:
            print("还未添加购物车商品，请添加商品")
    # 修改商品价格
    def update_price(self):
        if os.path.exists(self.file_path):
            with open(self.file_path,"r") as f:
                shop_list = f.readlines()
            shop_id = input("请输入你要修改的商品id:")
            with open(self.file_path, "w") as f: # 10，第9条满足
                for shop in shop_list:
                    shop = json.loads(shop)
                    if shop["shop_id"] == shop_id: # 判断输入的商品id和原商品id是否相等
                        shop["shop_count"] = input("请输入你要修改的数量：")
                        f.write(json.dumps(shop, ensure_ascii=False) + "\n") # 修改后的写入
                    else:
                        # 不相等，直接将剩下的数据写道原来的文件
                        f.write(json.dumps(shop, ensure_ascii=False) + "\n") # 未修改的写入
        else:
            print("还未添加购物车商品，请添加商品")

    # 商品结算
    def shop_over(self):
        if os.path.exists(self.file_path):
            with open(self.file_path,"r") as f:
                shop_list = f.readlines()
            while True:
                res = input("是否需要结算商品（y/其它为否）：")
                if res == "y":
                    price_count = {}
                    shop_id = input("请输入结算商品的id:")
                    with open(self.file_path,"w") as f:
                        for shop in shop_list:
                            shop = json.loads(shop)
                            if shop["shop_id"] == shop_id:
                                # 如果需要结算的商品，不再写入文件
                                price_count["shop_all_num"] = shop["shop_count"] # 商品数量
                                price_count["shop_all_price"] = int(shop["shop_price"]) * int(shop["shop_count"]) # 商品总价格
                                self.shop_over_info.append(price_count)
                                print("当前结算的商品名为：{}，价格为：{}，数量为：{}，本商品总价为：{}".format(
                                    shop["shop_name"],shop["shop_price"],shop["shop_count"],int(shop["shop_price"]) * int(shop["shop_count"])
                                ))
                            else:
                                # 不需要结算的重新写入
                                f.write(json.dumps(shop,ensure_ascii=False)+"\n")
                else:
                    break
            # 汇总
            for over_info in self.shop_over_info:
                self.all_count += int(over_info["shop_all_num"])
                self.all_price += int(over_info["shop_all_price"])

            print("当前购物车结算的总数量为：{}  总价格为：{}".format(self.all_count,self.all_price))
            print("********************************************************************************")

        else:
            print("还未添加购物车商品，请添加商品")
# 对象
shop_car = Shop_car()
while True:
    ord = shop_car.orders()
    if ord == "1":
        # 添加商品信息
        shop_car.add_shop_info()
    elif ord == "2":
        # 展示商品信息
        shop_car.show_shop_info()
    elif ord == "3":
        # 修改商品价格
        shop_car.update_price()
    elif ord == "4":
        # 结算
        shop_car.shop_over()
    elif ord == "5":
        print("正在退出购物车系统...")
        break
    else:
        print("命令输入错误，请重新输入")