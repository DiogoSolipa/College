#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

#define SIZE 1000000
#define INPUT 6

struct list *arr[SIZE];

void addGrp(int index, int n /*, struct list *arr[SIZE]*/)
{
    if (arr[index] == NULL)
    {
        arr[index] = list_new();
    }

    if(list_insert(arr[index], n))
    {
        return;
    }
}

void removeGrp(int index, int n /*, struct list *arr[SIZE]*/)
{
    if (arr[index] == NULL)
        return;

    if(list_remove(arr[index], n))
        return;
}

void print(int index /*, struct list *arr[SIZE]*/)
{
    if (arr[index] == NULL)
    {
        arr[index] = list_new();
    }

    if (list_length(arr[index]) == 0)
    {
        printf("%d %d\n", index, list_length(arr[index]));
        return;
    }
    else 
    {
        printf("%d %d ", index, list_length(arr[index]));
        list_print(arr[index]);
    }
    
}

int main()
{
    char c;
    int index, n;

    /*for (int i = 0; i < SIZE; i++)
    {
        list[i] = NULL;
    }*/

    while(scanf(" %c", &c) != EOF)
    {
        if (c == 'q')
        {
            scanf("%d", &index);
            print(index/*, arr*/);
        }
        else if (c == 'p')
        {
            scanf("%d %d", &index, &n);
            addGrp(index, n /*, arr*/);
        }
        else if (c == 'x')
        {
            scanf("%d %d", &index, &n);
            removeGrp(index, n /*, arr*/);
        } 
    }

    for (int i = 0; i < SIZE; i++)
    {
        if (arr[i] != NULL)
        {
            list_destroy(arr[i]);
        }
    }

    return 0;
}
