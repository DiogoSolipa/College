#include <stdio.h>

int fac(int n)
{
    if (n == 0)
        return 1;

    return n * fac(n - 1);
}

int main()
{
    int x = fac(10);
    int x1 = fac(20);
    int x2 = fac(21);

    printf("%d\n%d\n%d\n", x, x1, x2);
}