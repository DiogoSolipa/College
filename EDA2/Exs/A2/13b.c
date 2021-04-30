#include <stdio.h>

#define SIZE 50000

int binarySearch(int i, int j, int n, int s, int v[s])
{
    int x = (i + j) / 2;

    //printf("i: %d|| j: %d|| x: %d || v[x]: %d\n", i, j, x, v[x]);

    if (i > j)
        return -1;

    if (n < v[x])
    {
        j = x - 1;
        return binarySearch(i, j, n, x, v);
    }
    else if (n > v[x])
    {
        i = x + 1;
        return binarySearch(i, j, n, x, v);
    }
    else
        return x;
}

int search(int n, int s, int v[s])
{

    return binarySearch(0, s - 1, n, s, v);
}

int main()
{
    int arr[SIZE];

    for (int i = 0; i < SIZE; i++)
    {
        arr[i] = (i * 2) + 2;
    }

    for (int i = 1; i < SIZE; i++)
    {
        int p = search(i * 2, SIZE, arr);

        if (p == -1)
            printf("Não encontrou %d\n", 2 * i);

        else if (arr[p] != 2 * i)
            printf("Encontrou %d na posição errada: %d\n", 2 * i, p);
    }

    for (int i = 0; i < SIZE; i++)
    {
        printf("%d", i);
    }

    /*if (search(100001, SIZE, arr) != -1)
        printf("Encontrou algo errado\n");*/
}