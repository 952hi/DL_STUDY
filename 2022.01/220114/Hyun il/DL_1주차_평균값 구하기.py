'''

2022 01 23

'''
import sys
input = sys.stdin.readline

T = int(input())
temp = [];
for i in range(T):
    temp = list(map(int,input().split()))
    sum = 0
    avg = 0
    for j in range(len(temp)):
        sum += temp[j]
        avg = round(sum/len(temp),0)
    print("#%d %d"% ((i+1),avg))