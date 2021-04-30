#include <stdio.h>
#include "list-single.c"
#include "list.h"

#define num 50000

int main(void)
{
    struct list *l;

    l = list_new();

    // testa list_insert()
    printf("+ insere valores:");
    fflush(stdout);

    for (int i = 2; i <= num; i += 2)
    {
        fflush(stdout);

        if (!list_insert(l, i))
        {
            printf("\nERRO: problema ao inserir %d\n", i);

            return 1;
        }
    }

    //list_print(l);

    for (int i = 2; i <= num; i += 2)
    {
        list_find(l, i);
    }
}

/*

    Pesquisa Linear Recursiva > Find LinkedList

*/