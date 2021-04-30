#include <stdio.h>
#include "bst.h"

int main (void)
{
    struct node *root = NULL;
    root = insert(root, "c"); 
    root = insert(root, "b"); 
    root = insert(root, "a"); 
    root = insert(root, "d"); 
    root = insert(root, "f"); 
    root = insert(root, "e"); 
    root = insert(root, "g");

    printInOrder(root);

    root = deleteNode(root, "a");

    printf("------------------------\n");

    printInOrder(root);

    root = deleteNode(root, "b");

    printf("------------------------\n");

    printInOrder(root);
}