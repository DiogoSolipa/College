#include <stdio.h>

int sum(int sz, int v[sz], int a, int b, int arr[sz])
{
    int x = 0;

    for (int i = a; i <= b; i++)
    {
        x += v[i];
    }

    return x;
}

int *seq(int n, int sz, int v[sz], int arr[sz])
{
    int count = 1;
    int aux = 1;

    for (int i = 0; i <= sz; i++)
    {
        arr[i] = 0;
    }

    for (int i = 1; i <= sz; i++)
    {
        count = aux;

        for (int j = 1; count <= sz; j++)
        {

            if (v[j] == n)
            {
                arr[1] = j;

                return arr;
            }

            if (sum(sz, v, j, count, arr) == n)
            {
                arr[1] = j;
                arr[2] = count;
                arr[3] = count - j;

                return arr;
            }

            count++;
        }
        aux++;
    }

    arr[3] = -1;

    return arr;
}

void test(int sz, int v[sz], int num)
{
    if (v[3] == 0)
    {
        printf("s[%d] = %d\n", v[1], num);
    }
    else if (v[3] == 1)
    {
        printf("s[%d] + s[%d] = %d\n", v[1], v[2], num);
    }

    else if (v[3] >= 2)
    {
        printf("s[%d] + ... + s[%d] = %d\n", v[1], v[2], num);
    }
    else
    {
        printf("nenhuma subsequencia soma %d\n", num);
    }
}

int main()
{
    int x;
    int n;
    int num;

    scanf("%d", &x);

    int arr[x];
    int arr1[x];

    for (int i = 1; i <= x; i++)
    {
        scanf("%d", &n);
        arr[i] = n;
    }

    scanf("%d", &num);

    test(x, seq(num, x, arr, arr1), num);
}

/*
6
3 8 4 1 7 6
12

10
1 1 1 1 2 1 1 1 2 3
4
*/