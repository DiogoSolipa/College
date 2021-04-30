#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "list.h"

struct list
{
    int size;
    struct node *first;
};

struct node
{
    int value;
    struct node *next;
};

struct list *list_new()
{
    struct list *list = malloc(sizeof(struct list));

    if (list != NULL)
    {
        list->size = 0;
        list->first = NULL;
    }

    return list;
}

struct node *node_new(struct list *list, int value)
{
    struct node *node = malloc(sizeof(struct node));

    if (node != NULL)
    {
        node->value = value;
        node->next = NULL;
    }

    return node;
}

bool list_insert(struct list *list, int value)
{
    struct node *node = node_new(list, value);
    struct node *it = list->first;

    if (list_find(list, value) != -1)
    {
        return false;
    }

    if (list_empty(list))
    {
        list->first = node;
        list->size++;

        return true;
    }

    while (it->next != NULL)
    {   
        it = it->next;
    }

    if (node != NULL)
    {
        it->next = node;
        list->size++;

        return true;
    }

    return false;
}

void list_print(struct list *list)
{
    struct node *node = list->first;

    for (int i = 0; i < list->size - 1; i++)
    {
        if (node == NULL)
        {
            return;
        }
        
        printf("%d ", node->value);
        node = node->next;
    }
        if(node != NULL)
            printf("%d\n", node->value);
}

struct list *list_destroy(struct list *list)
{
    struct node *node = list->first;
    struct node *next;
    

    while (node != NULL)
    {
        next = node->next;
        free(node);

        node = next;
    }

    free(list);

    return list;
}

bool list_empty(struct list *list)
{
    return list->first == NULL;
}

int list_find(struct list *list, int value)
{
    struct node *node = list->first;

    int count = 0;

    while (node != NULL)
    {
        if (node->value == value)
        {
            return count;
        }

        node = node->next;
        count++;
    }

    return -1;
}

int list_length(struct list *list)
{
    return list->size;
}

bool list_remove(struct list *list, int value)
{
    struct node *node = list->first;
    struct node *prev;

    if (list_empty(list))
        return false;
    
    //caso em que queremos remover o 1 elemento da lista
    if (node->value == value)
    {
        //lista com apenas 1 elemento
        if (node->next == NULL)
        {
            list->first = NULL;
            list->size--;
            free(node);

            return true;
        }

        list->first = node->next;
        list->size--;
        free(node);

        return true;
    }


    for (int i = 0; i < list->size; i++)
    {
        if (node->value == value)
        {
            prev->next = node->next;

            list->size--;

            free(node);

            return true;
        }

        prev = node;
        node = node->next;
    }

    return false;
}