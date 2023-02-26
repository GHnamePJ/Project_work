file = open("info.txt", "w+")
file.write("202361020501340\nPJ")
file = open("info.txt", "r", encoding='UTF-8')
file.seek(0)
print(file.read())
file.seek(0)
print(file.read(3))
file.seek(0)
print(file.readline())
for i in file.readlines():
    print(i)


file = open("info.txt", "a")
afile = file.write("\nThis is a test\nfor file IO\nDid u get it?")
file.close()

file = open("info.txt", "r")
s = str(file.read())
print(s)
file.seek(0)
for i in set(file.read()):
    print(i, ":", s.count(i), end=",")
file.close()
