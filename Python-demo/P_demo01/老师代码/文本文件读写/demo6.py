# 打开文件
fo = open("f1.txt", "r+")
print ("文件名: ", fo.name)
ls=['中国','美国','法国']
str = "www.runoob.com"
# 在文件末尾写入一行
fo.seek(0, 2)
fo.write('\n'+str+'\n')
line = fo.writelines( ls )

# 读取文件所有内容
fo.seek(0,0)
for index in range(7):
    line = next(fo)
    print ("文件行号 %d - %s" % (index, line))

# 关闭文件
fo.close()