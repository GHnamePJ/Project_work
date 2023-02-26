#遍历全文本，一次读入
# 打开文件
'''fo = open("f1.txt", "r")
txt=fo.read()
print(txt)
# 关闭文件
fo.close()'''

#遍历全文本 ，循环读入
'''fo = open("f1.txt", "r")
txt=fo.read(2)
while txt!="":
    print(txt,end='')
    txt=fo.read(2)
# 关闭文件
fo.close()'''


#逐行遍历文件
'''fo = open("f1.txt", "r")
for line in fo.readlines():
    print(line,end='')
# 关闭文件
fo.close()
'''

#逐行遍历文件
fo = open("f1.txt", "r")
for line in fo:
    print(line,end='')
fo.close()