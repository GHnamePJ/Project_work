import random

class Gun:
    '''枪支类'''

    def __init__(self, type):
        '''初始化方法'''
        # 枪支类型
        self.type = type
        # 子弹的数量，新枪没有子弹
        self.bullet_count = 0

    def add_bullet(self, count):
        '''添加子弹的方法'''
        self.bullet_count += count


class Solider:
    '''士兵类'''

    def __init__(self, name, gun=None, typ=True):
        '''初始化方法'''
        # 士兵的名字
        self.name = name
        # 士兵的枪支，新兵默认没有枪
        self.gun = gun
        # 初始化血量
        self.blood = 100
        # 初始伤害值
        self.hurt = 0
        # 警察还是土匪，默认是True，表示警察
        self.typ = typ

    def add_gun(self, gun):
        self.solider_print('%s装备了枪%s' % (self.name, gun.type))
        self.gun = gun

    def add_gun_bullet(self, bullet_num):
        '''士兵拿到枪添加子弹'''
        # 1. 如果没枪不能开火，提示分配枪支
        if self.gun is None:
            self.solider_print('%s没有枪，无法开火,请分配枪支' % (self.name))
            return
        # 2. 如果有，则士兵拿着枪添加子弹
        self.gun.add_bullet(bullet_num)
        self.solider_print('%s已经为%s添加了%s发子弹' % (self.name, self.gun.type, bullet_num))

    def solider_print(self, *args):
        # 根据是警察还是匪徒，打印不同颜色字体
        msg = ''.join(args)
        if self.typ:
            msg = '\033[31m%s\033[0m' % msg
        else:
            msg = '\033[34m%s\033[0m' % msg
        print(msg)

    def fire(self, other):
        # 射击方法
        if self.gun is None:
            self.solider_print('%s没有枪，无法开火,请分配枪支' % (self.name))
            return
        # 如果没有子弹，不能射击，提示添加子弹
        if self.gun.bullet_count <= 0:
            self.solider_print('%s的%s没有子弹，无法射击,请添加子弹' % (self.name, self.gun.type))
            return
        # 如果自己没血了，不能射击
        if self.blood <= 0:
            return
        # 如果别人没血了，也没必要射击了
        if other.blood <= 0:
            return
        # 如果有子弹，正常射击，假设一次射一发,更新子弹数量
        self.gun.bullet_count -= 1
        # 一枪随机掉血10-50点
        loss_blood = random.randint(10, 50)
        # 别人血量减掉
        other.blood -= loss_blood
        # 自己伤害值加上
        self.hurt += loss_blood
        # 模拟射击动作，打印剩余子弹数量和造成的伤害
        self.solider_print('%s正在射击%s，造成了%d点伤害,剩余子弹数量是%d' % (self.name, other.name, loss_blood, self.gun.bullet_count))

    def show_infos(self):
        if self.blood > 0:
            if self.gun is None:
                self.solider_print('%s存活，没有枪，血量是%d' % (self.name, self.blood))
            else:
                self.solider_print('%s存活，他的枪是%s,有%d发子弹，血量是%d，一共造成了%d伤害' % (self.name, self.gun.type,
                                                                           self.gun.bullet_count, self.blood,
                                                                           self.hurt))
        else:
            if self.gun is None:
                self.solider_print('%s已经中阵亡，没有枪' % (self.name))
            else:
                self.solider_print('%s已经中阵亡，他的枪是%s,有%d发子弹,一共造成了%d伤害' % (self.name, self.gun.type,
                                                                        self.gun.bullet_count, self.hurt))


def counter_strike():
    # 创建枪支M4
    m4 = Gun('M4')
    # 创建士兵对象兰博
    police = Solider('兰博')
    # 兰博拿起枪
    police.add_gun(m4)
    # 添加子弹
    police.add_gun_bullet(50)

    # 创建枪支ak47
    ak = Gun('AK47')
    # 创建恐怖分子本拉登,枪是ak
    terrorist = Solider('本拉登', ak, False)
    # 本拉登射击兰博，但是没有子弹
    terrorist.fire(police)
    # 本拉登补充100发子弹
    terrorist.add_gun_bullet(100)
    # 随机射击
    for i in range(random.randint(5, 10)):
        soliders = [police, terrorist]
        solider1 = random.choice(soliders)
        soliders.remove(solider1)
        solider2 = soliders[0]
        solider1.fire(solider2)

    # 显示警察信息
    police.show_infos()
    # 显示恐怖分子信息
    terrorist.show_infos()


# 如果是主模块,执行,被导入则不执行，规定程序的入口
if __name__ == '__main__':
    counter_strike()
