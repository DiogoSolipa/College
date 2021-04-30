#include <stdio.h>

int fac(int n)
{

    int x = n;

    if (n == 0)
        return 1;

    else
    {
        for (int i = 1; i < n; i++)
        {
            x = x * i;
        }
    }
    return x;
}

int main()
{
    int x = fac(10);
    int x2 = fac(20);
    int x3 = fac(21);

    printf("%d\n%d\n%d\n", x, x2, x3);

    return 0;
}
