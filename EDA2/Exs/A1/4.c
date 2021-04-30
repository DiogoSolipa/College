#include <stdio.h>

int max(int n, int n2)
{
    if (n > n2)
        return n;

    else
        return n2;
}

int main()
{
    int n;
    int n2;

    scanf("%d", &n);
    scanf("%d", &n2);

    printf("%d\n", max(n, n2));

    return 0;
}