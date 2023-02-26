#!/usr/bin/env python3
# -*- coding: utf-8 -*-

class Student(object):
    # 在类中定义的函数只有一点不同，就是第一个参数永远是实例变量self，并且，调用时，不用传递该参数
    def __init__(self, name):
        self.name = name
        self.score = 60

    def print_score(self):
        print('%s: %s' % (self.name, self.score))

    def get_grade(self):
        if self.score >= 90:
            return 'A'
        elif self.score >= 60:
            return 'B'
        else:
            return 'C'


bart = Student('Bart Simpson')
lisa = Student('Lisa Simpson')

print('bart.name =', bart.name)
print('bart.score =', bart.score)
bart.print_score()
# # Python允许对实例变量绑定任何数据，也就是说，对于两个实例变量，虽然它们都是同一个类的不同实例，但拥有的属性变量名称都可能不同
# bart.age = 20
# print(bart.age)
# print(lisa.age)

print('grade of Bart:', bart.get_grade())
print('grade of Lisa:', lisa.get_grade())
