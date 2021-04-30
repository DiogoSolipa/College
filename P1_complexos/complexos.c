#include <stdio.h>
#include <math.h>
#include "complexos.h"

#define PI 3.14159265359

//le os valores do utilizador

complexo le_complexo(){

    complexo c; 

    printf("Introduza a parte real: ");
    scanf("%d", &c.real);
    printf("Introduza a parte imaginaria: ");
    scanf("%d", &c.img);

    return c;
}

void escreve_complexo(complexo c){

    printf("%d + %di\n", c.real, c.img);
}

complexo soma_complexo(complexo c1, complexo c2){

    complexo c;

    c.real = c1.real + c2.real;
    c.img = c1.img + c2.img;

    return c;
}

double modulo_complexo(complexo c){

    return sqrt(pow(c.real, 2) + pow(c.img, 2));
}

double argumento_complexo(complexo c){

    if(c.real != 0)
    {
        return atan(c.img / c.real);
    }else{
        if(c.img > 0)
        {
            return PI / 2;

        }else{

            return -PI / 2;
        }
    }
}