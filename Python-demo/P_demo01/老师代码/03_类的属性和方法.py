
a = 11
# 函数
def eat(a):
    print("猫1喜欢吃鱼",a)

eat(123)

class Cat:
    # 属性：类中的公用变量：
    def __init__(self):
        self.names = "黑猫警长"
    # 方法：类中的行为动作
    def eat(self):
        print(self.names)
        print("猫2喜欢吃鱼")

    def sleep(self):
        print("{}睡觉打呼噜".format(self.names))

    def play(self,name):
        print("猫喜欢{}".format(name))

# 面向对象编程
# 必须要有对象

c1 = Cat()
c2 = Cat()
c1.eat()
c1.sleep()
c1.play("抓小偷")
print(c1.names)
# print（）
# lists.sort()
print("c2对象也可：",c2.names)
# 游戏：
# 人物名，变量
# 方法：射击
# 方法：回血

# 建类
# 变量法的使用：
# 类里：self.变量名
# 类外：对象名.变量名
# self表示的意思：哪个对象使用，就代表哪对象名
class Game:
    def __init__(self,nice_name):
        self.name = "德玛西亚" # 变量
        self.nice_name = nice_name

    def sj(self):
        print("{}正在射击".format(self.nice_name))
        return "射击"

    def hx(self):
        print("正在回血")

player1 = Game("赵信")
print(player1.nice_name)
player1.sj()
# # 使用变量：
# # 对象名.变量名
# print("英雄的名字：{}".format(player1.name))
# # 使用方法：
# # 对象名.方法名（）
# print("影响的射击方法：{}".format(player1.sj()))
