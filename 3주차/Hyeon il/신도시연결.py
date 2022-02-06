for _ in range(int(input())):
    n,k = map(int, input().split())
    nums = list(map(int , input().split()))

    dist = []
    for i in range(1,n):
        dist.append(nums[i]-nums[i-1])
    dist.sort()
    print(dist)
    ans = 0
    for i in range(n-k):
        ans += dist[i]

    print(ans)
