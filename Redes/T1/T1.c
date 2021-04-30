#include <stdio.h>
#include <string.h>
#include <math.h>

#define size 100
#define stuffed_size 200
#define generator_size 7
#define hamming_size 3

int generator[generator_size] = {0b1101, 0b1011, 0b1000, 0b0111, 0b0100, 0b0010, 0b0001};
int hamming[hamming_size] = {0b1010101, 0b0110011, 0b0001111};

//void checkErrors(unsigned char *checkBits);

/*void hdecode(unsigned char **input_string, unsigned char *output_string)
{
    int counter = 0;

    unsigned char s[8];
    s[7] = '\0';

    unsigned char cBits[4];
    cBits[3] = '\0';

    for(int i = 0; i < hamming_size; i++)
    {
        counter = 0;

        for(int j = 0; j < 8; j++)
        {
            s[j] = input_string[i][j] & code[j];//AND bit a bit
        
            if(s[j] == '1')
                counter++;
        }

        if(counter % 2 == 0)
            cBits[i] = '0';
        else
            cBits[i] = '1';    
    }


}*/

void calculate(char *l1, char *l2, unsigned char *output_string, int n)
{
    unsigned int leftBinary , rightBinary;

    leftBinary = 0;
    rightBinary = 0;

    for(int i = strlen(l1) - 1; i > 0; i--)
    { 
        if(l1[i] == '1')
            leftBinary += pow(2, strlen(l1) -  i - 1);
        if(l2[i] == '1')
            rightBinary += pow(2, strlen(l2) - i - 1);       
    }

    if(leftBinary == 0)
        leftBinary = 128;
    if(rightBinary == 0)
        rightBinary = 128;

    //printf("%d | %d\n", leftBinary, rightBinary);

    output_string[n] = (unsigned char)(leftBinary);
    output_string[n + 1] = (unsigned char)(rightBinary);

    //printf("%c | %c\n", output_string[n], output_string[n+1]);
    //printf("%s\n", output_string);
}

void hcode(unsigned char *input_string, unsigned char *output_string)
{
    
    char l1[9], l2[9];
    l1[8] = '\0';
    l2[8] = '\0';

    l1[0] = '1';
    l2[0] = '1';

    int count = 1;

    unsigned char letter, left, right, aux;

    int countLeft, countRight, outputIterator, length;

    countLeft = countRight = outputIterator = 0;

    length = strlen((char*)input_string);

    for(int n = 0; n < length; n++)
    {
        letter = input_string[n];
        count = 1;

        for(int i = 0; i < generator_size; i++)
        {
            left = letter >> 4;
            aux = letter << 4;
            right = aux >> 4;

            right = generator[i] & right;
            left = generator[i] & left;

            countLeft = ((left >> 3) & 1) + ((left >> 2) & 1) + ((left >> 1) & 1) + (left & 1);
            countRight = ((right >> 3) & 1) + ((right >> 2) & 1) + ((right >> 1) & 1) + (right & 1);

            if(countLeft % 2 == 0)
                l1[count] = '0';
            else
                l1[count] = '1';
            if(countRight % 2 == 0)
                l2[count] = '0';
            else
                l2[count] = '1';     

            count++;    
        }

        //printf("%s | %s %ld\n", l1, l2, strlen(input_string));   

        calculate(l1, l2, output_string, outputIterator);
        
        outputIterator += 2;
    }
    printf("%s\n", output_string);
}

void bytestuff(unsigned char *input_string, unsigned char *output_string)
{
    int it = 0;
    int itStuffed = 0;

    while(input_string[it] != '\0')
    {
        if(input_string[it] != 'H')
        {
            output_string[itStuffed] = input_string[it];
            itStuffed++;
        }    
        else
        {
            output_string[itStuffed] = 'H';
            itStuffed++;

            if(input_string[it+1] == 'H')
            {
                output_string[itStuffed] = '|';
                itStuffed++;
            }
        }
        it++;
    }
}

void bytedestuff(unsigned char *input_string, unsigned char *output_string)
{
    int it = 0;
    int itStuffed = 0;

    while(input_string[itStuffed] != '\0')
    {
        if(input_string[itStuffed] != '|')
        {
            output_string[it] = input_string[itStuffed];
            it++;
        }    
        else
        {
            itStuffed++;
            output_string[it] = input_string[itStuffed];
            it++;
        }
        itStuffed++;
    }
}


int main()
{
    unsigned char s1[size], /*s2[stuffed_size],*/ s_coded[stuffed_size];

    strcpy((char*)s1, "ABCDEFGHHHHHIJKLMNOPQR1234567890abcdefghijklmnopqrstuvxyz  :-))))))");

    hcode(s1, s_coded);

    //bytestuff(s1, s2);
}