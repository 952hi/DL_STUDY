"""
https://www.acmicpc.net/problem/1715
카드정렬하기

카드의 수를 입력받아 모든카드를 비교할때 최소의 비교 횟수을 출력하는 문제

입력예시
3
10
20
40

10 20 40 카드 뭉치를 비교할때 작은 카드뭉치 두개를 합쳐 비교해야 최소의 수이다
10 + 20 = 30
30 + 40 = 70
총 30 + 70 = 100

무작위로 선택했을때
40 + 20 60
60 + 10 70
-> 130 

느낀점
처음 리스트형식으로 해결하려했음 -> 시간초과
큐에서 정렬기능 사용하려면 힙과 우선순위 큐 두가지
힙큐를 이용해서 구현함 
자식의 인덱스를 l,r *2 *2+1 로 설정해서 오류수정하는데 시간이걸림 

"""
# import sys
# input = sys.stdin.readline
# import heapq

# temp = []
# comp = 0

# for _ in range(int(input())):
#     temp.append(int(input()))

# heapq.heapify(temp)

# while len(temp) > 1:
#     a = heapq.heappop(temp)
#     b = heapq.heappop(temp)
#     c = a+b
#     comp += c
    
#     heapq.heappush(temp,c)

# print(comp)

import sys
input = sys.stdin.readline

def heapify(unsorted,index,size):
    root = index
    r = index * 2 + 2
    l = index * 2 + 1
    
    if l < size and unsorted[l] > unsorted[root]:
        root = l
    if r < size and unsorted[r] > unsorted[root]:
        root = r
    if root != index :
        unsorted[root], unsorted[index] = unsorted[index] , unsorted[root]
        heapify(unsorted,root , size)
        
def heapsort(unsorted):
    n = len(temp)
    for i in range(n//2-1,-1,-1):
        heapify(unsorted,i,n)
    for i in range(n-1,-1,-1):
        unsorted[0], unsorted[i] = unsorted[i], unsorted[0]
        heapify(unsorted,0,i)
    return unsorted 
temp = []
result = 0

for _ in range(int(input())):
    temp.append(int(input()))

while len(temp)> 1:
    heapsort(temp)
    a = temp.pop(0) + temp.pop(0)
    result += a
    temp.append(a)

print(result) 