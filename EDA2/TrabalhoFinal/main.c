#include "hashTable.h"

#define STUDENTS_FILE "students.bin"
#define COUNTRY_FILE "country.bin"

FILE *file_open(char *file_name)
{
    //abre file
    FILE *f = fopen(file_name, "rb+");

    //caso file exista retorna-o
    if(f != NULL)
        return f;

    //caso file nao exista cria
    f = fopen(file_name, "wb+");

    return f;
}

void file_close(FILE *file)
{
    fclose(file);
}

bool parseInput(char c, char *id, char *p_id, Student *student, Country *country, FILE *file, FILE *cFile)
{
    if(c == 'I')
    {
        scanf("%s %s", id, p_id);        

        student = student_new(id, p_id);
        country = country_new(p_id);

        unsigned long pos = searchPosCountry(cFile, country);

        //printf("%ld\n", pos);

        //Pelo menos 1 acesso ao ficheiro
        if(insertStudent(file, student) == false)
        {
            printf("+ estudante %s existe\n", id);  
        }
        else if(pos == -1)
        {
            strcpy(country->p_id, p_id);

            //Pelo menos 1 acesso ao ficheiro
            insertCountry(cFile, country);

            //Pelo menos 1 acesso ao ficheiro
            addActive(cFile, country, pos);
        }
        else
        {
            //Pelo menos 1 acesso ao ficheiro
            addActive(cFile, country, pos); 
        }      
    }
    else if(c == 'R')
    {
        scanf("%s", id);
        //scanf("%s", p_id);

        strcpy(student->id, id);

        //Pelo menos 1 acesso ao ficheiro
        student = student_new(id, getCountry(file, student));
        country = country_new(student->p_id);

        if(removeStudent(file, student) == true)
        {
            removeActive(cFile, country);
            //free(student);
        }
        //Pelo menos 1 acesso ao ficheiro
        else if(student->quit == false && student->finished == false)
        {
            printf("+ estudante %s inexistente\n", id);
        }
        else if(student->quit == true)
        {
            printf("+ estudante %s abandonou\n", student->id);
            //free(student);
        }
        else if(student->finished == true)
        {
            printf("+ estudante %s terminou\n", student->id);
            //free(student);
        }
    }
    else if(c == 'A')
    {
        scanf("%s", id);
            
        strcpy(student->id, id);

        //Pelo menos 1 acesso ao ficheiro 
        student = student_new(id, getCountry(file, student));
        country = country_new(student->p_id);

        //Pelo menos 1 acesso ao ficheiro
        if(abandoned(file, student) == false)
        {
            if(student->finished == false && student->quit == false)
                printf("+ estudante %s inexistente\n", id);
            else if(student->finished == true)
                printf("+ estudante %s terminou\n", id);
            else if(student->quit == true)
                printf("+ estudante %s abandonou\n", id);           
        }
        else
        {
            //Pelo menos 1 acesso ao ficheiro
            addAbandoned(cFile, country);
        }            
    }
    else if(c == 'T')
    {
        scanf("%s", id);
            
        strcpy(student->id, id);

        //Pelo menos 1 acesso ao ficheiro
        student = student_new(id, getCountry(file, student));
        country = country_new(student->p_id);

        //Pelo menos 1 acesso ao ficheiro
        if(finished(file, student) == false)
        {
            if(student->finished == false && student->quit == false)
                printf("+ estudante %s inexistente\n", id);
            else if(student->quit == true)
                printf("+ estudante %s abandonou\n", id);
            else if(student->finished == true)
                printf("+ estudante %s terminou\n", id);
        }
        else
        {  
            //Pelo menos 1 acesso ao ficheiro
            addFinished(cFile, country);
        }
    }
    else if(c == 'P')
    {
        scanf("%s", p_id);

        country = country_new(p_id);

        if(searchPosCountry(cFile, country) == false)
        {
            printf("+ sem dados sobre %s\n", p_id);
            //free(country);
        }  
        else
        {
            if(country->total == 0)
                printf("+ sem dados sobre %s\n", p_id);
            else
                printf("+ %s - correntes: %d, diplomados: %d, abandonaram: %d, total: %d\n", p_id, country->active, country->finished, country->abandoned, country->total);
            //free(country);
        }

            //free(country);    
    }
    else if(c == 'X')
        return false;

    return true;
}

int main(void)
{
    FILE *file = file_open(STUDENTS_FILE);
    FILE *cFile = file_open(COUNTRY_FILE);

    Country *country = country_new("--");
    Student *student = student_new("------", "--");

    char c;
    char id[ID_SIZE];
    char p_id[P_ID_SIZE];

    while(scanf("%c ", &c) != EOF)
    {        
        if(parseInput(c, id, p_id, student, country, file, cFile) == false)
            break;               
    }

    free(student);
    free(country); 

    file_close(file);
    file_close(cFile);

    return 0;
}    