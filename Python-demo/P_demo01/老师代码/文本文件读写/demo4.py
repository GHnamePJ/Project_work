# 打开文件
fo = open("f1.txt", "r+")
print ("文件名为: ", fo.name)

line = fo.read(10)
print ("读取的字符串:\n%s" % (line))

# 关闭文件
fo.close()