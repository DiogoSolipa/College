#include <stdio.h>
#include <math.h>
#include "complexos.c"


int main(){

    complexo c1;
    complexo c2;
    complexo c3;

    c1 = le_complexo();
    c2 = le_complexo();

    escreve_complexo(c1);
    escreve_complexo(c2);

    c3 = soma_complexo(c1, c2);

    printf("%d+%di\n", c3.real, c3.img);

    printf("o Modulo do complexo é : %f\n", modulo_complexo(c1));
    printf("O Modulo do complexo é : %f\n", modulo_complexo(c2));

    printf("O argumento do complexo é : %f\n", argumento_complexo(c1));
    printf("O argumento do complexo é : %f\n", argumento_complexo(c2));

}