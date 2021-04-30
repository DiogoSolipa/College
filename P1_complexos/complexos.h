#include <stdio.h>

typedef struct complexos {

    int real, img;
}complexo;

complexo le_complexo();
void escreve_complexo(complexo c);
complexo soma_complexo(complexo c1, complexo c2);
double modulo_complexo(complexo c);
double argumento_complexo(complexo c);