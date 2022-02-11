'''
4
5
....o
...o.
..o..
.o...
o....
5
...o.
ooooo
...o.
...o.
.....
5
.o.oo
oo.oo
.oo..
.o...
.o...
5
.o.o.
o.o.o
.o.o.
o.o.o
.o.o.

출력
1 yes
2 yes
3 yes
4 no
'''
import sys
input = sys.stdin.readline

def omok(x,y):
    cnt = 1
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        while True:
            
            if cnt == 5:
                return 1
            
            if nx < 0 or nx >= n or ny < 0 or n <= ny:
                cnt = 1
                break
            if board[nx][ny] == "o":
                cnt +=1
            else:
                cnt = 1
                break
            
            nx = nx + dx[i]
            ny = ny + dy[i]
            
            
            
            
T = int (input())
for a in range(1,T+1):
    n =int(input())
    board = []
    for _ in range(n):
        board.append(list(input()))

    dx = [1,1,0,1]
    dy = [-1,0,1,1]
         
    check = False
    for i in range(n):
        for j in range(n):
            if board[i][j] == "o":
                # 좌하 하 우 우하
                if omok(i,j)==1:
                    check = True
                    break
        if check:
            break
        
    if check:
        print("#%d YES" % a)
    else:
        print("#%d NO" % a)
    check = False

