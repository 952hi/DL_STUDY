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
    for j in range(len(temp)):
        if (temp[j]%2)==1 :
            sum += temp[j]
    print("#%d %d"% ((i+1),sum))
    
        