# 定义Book类和BookManage类，使用列表存储图书信息，并初始化书籍信息，完成图书管理系统功能如下功能：
#                    1. 查询
#                    2. 增加
#                    3. 借阅
#                    4. 归还
#                    5. 退出


# 管理系统：
class Book:
    # 书的属性：书名，作者，状态，位置
    def __init__(self, name, author, status, bookindex):
        self.name = name
        self.author = author
        self.status = status
        self.bookindex = bookindex

    # 返回值为一个字符串对象
    def __str__(self):
        if self.status == 1:
            stats = '未借出'
        elif self.status == 0:
            stats = '已借出'
        else:
            stats = '状态异常'
        return '书名: 《%s》 作者: %s 状态: <%s> 位置: %s' \
               % (self.name, self.author, stats, self.bookindex)


# 书籍管理类
class BookManage(object):
    # 用一个列表存放书籍数据
    books = []

    # 原始所有图书
    def start(self):
        self.books.append(Book('小王子', '安托万·德·圣-埃克苏佩里', 1, '0001'))
        self.books.append(Book('红楼梦 ', '曹雪芹', 1, '0002'))
        self.books.append(Book('人间告白', '金鱼酱 ', 1, '0003'))
        # 0:借出 1：存在

    # 菜单显示
    def Menu(self):
        self.start()
        while True:
            print("_" * 80)
            print("""
                        图书管理系统
                        1.查询图书
                        2.增加图书
                        3.借阅图书
                        4.归还图书
                        5.退出系统
                        """)

            choice = input('请选择：')

            if choice == '1':
                self.showAllBook()
            elif choice == '2':
                self.addBook()
            elif choice == '3':
                self.borrowBook()
            elif choice == '4':
                self.returnBook()
            elif choice == '5':
                print('欢迎下次使用...')
                exit()
            else:
                print('请输入正确选择')
                continue

    # 显示所有书籍
    def showAllBook(self):
        for book in self.books:
            print(book)

    # 增加书籍
    def addBook(self):
        name = input('图书名称:')
        self.books.append(Book(name, input('作者:'), 1, input('存储位置:')))
        print('图书《%s》增加成功' % name)

    # 借出书籍
    def checkBook(self, name):  # name是返回值
        for book in self.books:
            if book.name == name:  # 如果借阅书的名称在已有的图书中，返回书的名称，否则为空
                return book
        else:
            return None

    # 借阅图书
    def borrowBook(self):
        name = input('借阅图书名称: ')
        ret = self.checkBook(name)
        print(ret)

        if ret != None:
            if ret.status == 0:  # 如果书籍的状态为0，说明此书已借出
                print('书籍《%s》已经借出' % name)
            else:
                ret.status = 0  # 借出后显示书籍的状态为0
                print('书籍《%s》借阅成功' % name)
        else:
            print('书籍《%s》不存在' % name)

    # 归还书籍
    def returnBook(self):
        name = input('归还图书名称:')
        ret = self.checkBook(name)

        if ret != None:
            if ret.status == 0:
                ret.status = 1
                print('书籍《%s》归还成功' % name)
                print(ret)
            else:
                print('书籍《%s》未借出' % name)
        else:
            print('书籍《%s》不存在' % name)


manager = BookManage()
manager.Menu()
