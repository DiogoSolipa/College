#include <stdio.h>
#include "list.h"

#define VALORES 10
#define UM_VALOR 5

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

    // testa list_insert() + list_length()
    int c = list_length(l);

    printf("list_length() diz que o comprimento da lista é %d\n", c);

    if (c != VALORES)
    {
        printf("ERRO: o comprimento da lista devia ser %d\n", VALORES);

        return 1;
    }

    // testa list_nth()
    int n = list_nth(l, list_length(l) - 1);

    printf("o ultimo elemento da lista é o %d\n", n);

    if (n != 1)
    {
        printf("ERRO: o ultimo elemento da lista devia ser o 1\n");

        return 1;
    }

    // testa list_remove() com 0
    printf("+ tenta remover 0 da lista...");

    if (list_remove(l, 0))
    {
        printf("\nERRO: removeu o 0 da lista, sem ele existir\n");

        return 1;
    }

    printf(" ok\n");

    // testa list_remove()
    printf("+ remove %d\n", UM_VALOR);


    if (!list_remove(l, UM_VALOR))
    {
        printf("ERRO: problema ao remover o %d\n", UM_VALOR);

        return 1;
    }


    printf("lista sem %d: ", UM_VALOR);
    list_print(l);
    printf("\n");

    // testa list_remove() + list_find()
    printf("+ procura %d...", UM_VALOR);
    fflush(stdout);

    if (list_find(l, UM_VALOR) != -1)
    {
        printf("\nERRO: encontrou o valor removido %d\n", UM_VALOR);

        return 1;
    }
    else
        printf(" nao encontrou\n");

    printf("+ procura restantes elementos...");
    fflush(stdout);

    for (int i = 1; i <= VALORES; ++i)
        if (i != UM_VALOR)
            if (list_find(l, i) == -1)
            {
                printf("\nERRO: nao encontrou %d\n", i);

                return 1;
            }

    printf(" ok\n");

    // testa mais list_remove()
    printf("+ remove restantes elementos\n");

    for (int i = 1; i <= VALORES; ++i)
        if (i != UM_VALOR)
            if (!list_remove(l, i))
            {
                printf("ERRO: problema ao remover o %d\n", i);

                return 1;
            }

    printf("elementos da lista: ");
    list_print(l);
    printf("\n");

    if (!list_empty(l))
    {
        printf("ERRO: list_empty() diz que a lista nao esta' vazia\n");

        return 1;
    }

    // apaga a lista
    list_destroy(l);

    printf("+ bye\n");

    return 0;
}