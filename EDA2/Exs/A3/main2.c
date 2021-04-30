#include <stdio.h>
#include "list-single.c"
#include "list.h"

#define VALORES 10

int main(void)
{
    struct list *l;

    // cria lista
    printf("+ cria lista\n");

    l = list_new();

    // testa list_new() + list_empty() de uma lista vazia
    printf("+ lista vazia?");
    fflush(stdout);

    if (!list_empty(l))
    {
        printf("ERRO: list_empty() diz que a nova lista nao esta' vazia\n");

        return 1;
    }

    printf(" sim\n");

    // testa list_print() de uma lista vazia
    printf("elementos da lista (vazia): ");
    list_print(l);
    printf("\n");

    // testa list_find() com 0 numa lista vazia
    printf("+ procura o 0 na lista vazia...");
    fflush(stdout);

    if (list_find(l, 0) != -1)
    {
        printf("\nERRO: encontrou o 0 na lista vazia\n");

        return 1;
    }

    printf(" ok\n");

    // testa list_insert()
    printf("+ insere valores:");
    fflush(stdout);

    for (int i = 1; i <= VALORES; ++i)
    {
        printf(" %d", i);
        fflush(stdout);

        if (!list_insert(l, i))
        {
            printf("\nERRO: problema ao inserir %d\n", i);

            return 1;
        }
    }

    printf("\n");

    // testa list_print() de uma lista com elementos
    printf("elementos da lista: ");
    list_print(l);
    printf("\n");

    // testa list_empty() de uma lista nao vazia
    printf("+ lista vazia?");
    fflush(stdout);

    if (list_empty(l))
    {
        printf("ERRO: list_empty() diz que a lista esta' vazia\n");

        return 1;
    }

    printf(" nao\n");

    // testa list_find()
    printf("+ procura elementos:");
    fflush(stdout);

    for (int i = 1; i <= VALORES; ++i)
    {
        printf(" %d", i);
        fflush(stdout);

        int p = list_find(l, i);

        if (p == -1)
        {
            printf("\nERRO: nao encontrou %d\n", i);

            return 1;
        }

        if (p != VALORES - i)
        {
            printf("\nERRO: encontrou %d na posicao errada: %d\n", i, p);

            return 1;
        }
    }

    printf(" ok\n");

    // testa list_find() com 0
    printf("+ procura o 0...");
    fflush(stdout);

    if (list_find(l, 0) != -1)
    {
        printf("\nERRO: encontrou o 0 na lista\n");

        return 1;
    }

    printf(" ok\n");

    // apaga a lista
    list_destroy(l);

    printf("+ bye\n");

    return 0;
}