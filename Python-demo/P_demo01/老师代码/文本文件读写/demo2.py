#文本文件逐行打印
fname = input("请输入要打开的文件: ")
#如果txt文件是UTF-8编码，则打开文件时编码需要转为utf-8，否则不需要
fo = open(fname,"r",encoding='utf-8')
for line in fo.readlines():
    print(line)
fo.close()