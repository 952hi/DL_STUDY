'''
입력예시
1
5
2 5 14
2 3 2
3 2 7
1 1 2
2 1 3

'''
for T in range(int(input())):
    n = int(input())
    city = []
    result = []
    affect = [[0]*n for _ in range(n)]
    
    for _ in range(n):
        city.append(list(map(int,input().split())))

    for i in range(n):
        x,y= city[i][0],city[i][1]
        for j in range(n):
            dx,dy = city[j][0],city[j][1]
            if x==dx and y==dy:
                continue
            affect[i][j] = city[i][2]/(pow((x-dx),2)+pow((y-dy),2))
            
    cnt = 0
    max_digit = 0
    for i in range(n):
        for j in range(n):
            if j == i:
                continue
            if affect[j][i] > city[i][3]:
                cnt +=1
                max_digit = max(max_digit,affect[j][i])
             
        result.append(cnt)
        cnt = 0
        
# 이해 불가