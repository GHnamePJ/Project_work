# class Car:
#     def __init__(self, company, level, year):
#         self.company = company
#         self.level = level
#         self.year = year
#         self.odometer_reading = 40

#     def get_descriptive_name(self):
#         long_name = str(self.year) + "" + self.company + "" + self.level
#         return long_name
#
#     def update_odometer(self, mileage):
#         if mileage >= self.odometer_reading:
#             self.odometer_reading = mileage
#         else:
#             print("you")
#
#     def read_odometer(self):
#         print(str(self.odometer_reading))
#
#
# my_new_car = Car("audi", "b", 2016)
# print(my_new_car.get_descriptive_name())
# my_new_car.update_odometer(66)
# my_new_car.read_odometer()

# 类和对象
# class Student:
#     def __init__(self, name, gender):
#         self.__gender = gender
#         self.__name = name
#
#     def get_gender(self):
#         return self.__gender
#
#     def set_gender(self, gender):
#         self.__gender = gender
#
#     def get_name(self):
#         return self.__anme
#
#     def srt_name(self, name):
#         self.__name = name
#
#     def print(self):
#         print("学生姓名为" + self.__name + "性别" + self.__gender)
#
#
# bart = Student('Bart', 'male')
# if bart.get_gender() != 'male':
#     print('测试失败!')
# else:
#     bart.set_gender('female')
# if bart.get_gender() != 'female':
#     print('测试失败!')
# else:
#     print('测试成功!')

# 继承与多态

# class Animal:
#     def run(self):
#         print("Animal id running...")
#
#
# class Dog(Animal):
#     def run(self):
#         print("Dog is running...")
#
#
# class Cat(Animal):
#     def run(self):
#         print("Cat is running...")
#
#
# class Timer(object):
#     def run(self):
#         print("Start...")
#
#
# class run_twice(animal):
#     animal.run()
#
#
# a = Animal()
# b = Dog()
# c = Cat()
# d=run_twice()

