#include <stdio.h>
#include <string.h>
#include "stack.h"

#define SIZE 1001

void rpn(int sz, char s[sz])
{
    struct stack *stack = stack_new(sz);
    int a = 0;
    int b = 0;
    int aux = 0;

    for (int i = 0; i < sz; i++)
    {
        if (s[i] != '+' && s[i] != '-' && s[i] != '*' && s[i] != '/' && s[i] != '~')
        {
            aux = s[i] - '0';

            push(stack, aux);
        }
        else if (s[i] == '+')
        {
            b = pop(stack);
            a = pop(stack);

            aux = a + b;

            push(stack, aux);
        }
        else if (s[i] == '-')
        {
            b = pop(stack);
            a = pop(stack);

            aux = a - b;

            push(stack, aux);
        }
        else if (s[i] == '*')
        {
            b = pop(stack);
            a = pop(stack);

            aux = a * b;

            push(stack, aux);
        }
        else if (s[i] == '/')
        {
            b = pop(stack);
            a = pop(stack);

            if (b == 0)
            {
                printf("divisao por 0\n");
                return;
            }

            aux = a / b;

            push(stack, aux);
        }
        else if (s[i] == '~')
        {
            a = pop(stack);

            aux = a * (-1);

            push(stack, aux);
        }
    }

    aux = pop(stack);

    printf("%d\n", aux);
}

int main()
{
    char s[SIZE];

    while (scanf("%s", s) != EOF)
    {
        rpn(strlen(s), s);
    }

    return 0;
}