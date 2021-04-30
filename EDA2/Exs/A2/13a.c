#include <stdio.h>

#define SIZE 50000

int search(int n, int s, int v[s])
{
    for (int i = 0; i <= s; i++)
    {
        if (v[i] == n)
            return i;
    }

    return -1;
}

int main()
{
    int arr[SIZE];

    for (int i = 0; i < SIZE; i++)
    {
        arr[i] = (i * 2) + 2;
    }

    arr[0] = 1;
    arr[SIZE / 2] = 50001;
    arr[SIZE - 1] = 100001;

    for (int i = 1; i <= SIZE; i++)
    {
        int p = search(2, SIZE, arr);

        if (p == -1)
            printf("Não encontrou %d\n", 2 * i);

        else if (arr[p] != 2 * i)
            printf("Encontrou %d na posição errada: %d\n", 2 * i, p);
    }

    return 0;
}