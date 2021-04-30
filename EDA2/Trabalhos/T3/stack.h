#include <stdio.h>

struct stack;

struct stack *stack_new(int n);
int pop(struct stack *stack);
void push(struct stack *stack, int n);
int top(struct stack *stack);
int empty(struct stack *stack);