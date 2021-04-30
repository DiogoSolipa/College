#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "bst.h"


struct node
{
    struct node *left;
    struct node *right;
    char id [IDSIZE];
};

struct node *new_node(char id [IDSIZE])
{
    struct node *node = malloc(sizeof(struct node));

    strcpy(node->id, id);

    node->left = node->right = NULL;

    return node;
}

struct node *min(struct node *root)
{
    if (root->left == NULL)
        return root;

    return min(root->left);    
}

struct node *insert(struct node *root, char id [IDSIZE])
{
    //se arvore vazia cria uma nova raiz
    if (root == NULL)
        return new_node(id);
    
    //inserir esquerda
    if (strcmp(id, root->id) < 0)
        root->left = insert(root->left, id);
    
    //inserir direita
    else if (strcmp(id, root->id) > 0)
        root->right = insert(root->right, id);

    return root;        
}

struct node *search(struct node *root, char id [IDSIZE])
{
    //caso base em que raiz e o valor ou arvore vazia
    if (root == NULL || strcmp(id, root->id) == 0)
        return root;

    if (strcmp(id, root->id) < 0)
        return search(root->left, id);

    return search(root->right, id);        
}

struct node *deleteNode(struct node *root, char id [IDSIZE])
{
    if (root == NULL)
        return root;

    if (strcmp(id, root->id) < 0)
        root->left = deleteNode(root->left, id);

    else if (strcmp(id, root->id) > 0)
        root->right = deleteNode(root->right, id);       

    else
    {
        if(root->left == NULL)
        {
            struct node *node = root->right;
            free(root);

            return node;
        }
        else if (root->right == NULL)
        {
            struct node *node = root->left;
            free(root);

            return node;
        }

        struct node *node = min(root->right);

        strcpy(root->id, node->id);

        root->right = deleteNode(root->right, node->id);
    }
    
    return root;
}

void printInOrder(struct node *root)
{
    if (root != NULL)
    {
        printInOrder(root->left);
        printf("%s\n", root->id);
        printInOrder(root->right);
    }
}