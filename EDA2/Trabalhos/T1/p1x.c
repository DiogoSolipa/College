#include <stdio.h>
#include <math.h>
#include <string.h>

unsigned int firstDiv(unsigned int n)
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

void factors(unsigned int n)
{
    while (n > 1)
    {
        printf(" %u", firstDiv(n));
        n = n / firstDiv(n);
    }
}

int main()
{
    int n;
    unsigned int x;

    scanf("%d", &n);

    for (int i = 0; i < n; i++)
    {
        scanf("%u", &x);
        printf("%u:", x);
        factors(x);
        printf("\n");
    }
}