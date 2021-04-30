#include <stdio.h>
#include <stdlib.h>
#include "stack.h"

struct stack
{
    int *arr;
    int top;
    int size;
};

struct stack *stack_new(int size)
{
    struct stack *stack = malloc(sizeof(struct stack));

    if (stack != NULL)
    {
        stack->size = size;
        stack->top = 0;
        stack->arr = malloc(stack->size * sizeof(int));
    }

    return stack;
}

int empty(struct stack *stack)
{
    return stack->top == 0;
}

void push(struct stack *stack, int n)
{
    stack->arr[stack->top] = n;
    stack->top++;
    //printf("pushed %d\n", n);
}

int pop(struct stack *stack)
{

    if (empty(stack))
        return -1;

    stack->top--;

    return stack->arr[stack->top];
}

int top(struct stack *stack)
{
    return stack->top;
}
