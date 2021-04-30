#include <stdio.h>
#include <math.h>

int firstDiv(int n)
{
    if (n % 2 == 0)
        return 2;

    for (int i = 3; i <= sqrt(n); i += 2)
    {
        if (n % i == 0)
            return i;
    }

    return n;
}

int factors(int n)
{
    int count = 0;

    while (n > 1)
    {
        n = n / firstDiv(n);
        count++;
    }

    return count;
}

int main()
{
    int n;
    int x;

    scanf("%d", &n);

    for (int i = 0; i < n; i++)
    {
        scanf("%d", &x);

        printf("%d: %d\n", x, factors(x));
    }
}