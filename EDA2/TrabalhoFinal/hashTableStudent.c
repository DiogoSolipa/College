#include "hashTable.h"

#define MAX_SIZE 38000009

unsigned long currentPosStudent = 0;

Student *student_new(char *id, char *p_id)
{
    Student *student = malloc(sizeof(Student));

    strcpy(student->id, id);
    strcpy(student->p_id, p_id);

    student->quit = student->finished = student->isStudent = false;

    return student;
}

unsigned long hashStudent(char *s)
{
    unsigned long hashKey = 5381; //numero utilizado pelo algoritmo para começar
    unsigned long c;
    while((c = *s++)) // c vai ter o ASCII de cada char e *s++ percorre a string
    {
        hashKey = ((hashKey << 5) + hashKey) + c; //shift left 5 bits da hashkey + hashkey + valor ASCI de cada char
    }  

    return hashKey % MAX_SIZE; //divisao para nao sair out of bounds da HashTable
}

//funcao que encontra uma posicao disponivel para insercao e marca um pointer no 1 byte dessa posicao
bool insertPosStudent(FILE *file, Student *student)
{
    currentPosStudent = hashStudent(student->id);
    unsigned long aux = currentPosStudent;
    unsigned long offset = sizeof(Student);

    if(currentPosStudent < 0)
    {
        currentPosStudent *= -1;
        aux *= -1;
    }

    Student *s = student_new(student->id, student->p_id);

    fseek(file, currentPosStudent * sizeof(Student), SEEK_SET);

    while(fread(s, sizeof(Student), 1, file) == 1)
    {
        if(strcmp(s->id, "\0") == 0)
        {
            free(s);
            return true;
        }
        else if(s->isStudent == false && strcmp(s->id, student->id) == 0)
        {
            free(s);
            return true;
        }
        else if(s->isStudent == true && strcmp(s->id, student->id) == 0)
        {
            free(s);
            return false;
        }

        currentPosStudent = aux + pow(offset, 2);
        offset += offset;

        currentPosStudent = currentPosStudent % MAX_SIZE;

        fseek(file, currentPosStudent * sizeof(Student), SEEK_SET); 
    }
    return true;
}

//funcao que procura a posicao de um elemento especifico e marca um pointer no ficheiro no 1 byte desse elemento
bool searchPosStudent(FILE *file, Student *student)
{
    currentPosStudent = hashStudent(student->id);
    unsigned long aux = currentPosStudent;
    unsigned long offset = sizeof(Student);

    if(currentPosStudent < 0)
    {
        currentPosStudent *= -1;
        aux *= -1;
    }

    Student *s = student_new(student->id, student->p_id);

    fseek(file, currentPosStudent * sizeof(Student), SEEK_SET);

    while(fread(student, sizeof(Student), 1, file) == 1)
    {
        if(student->isStudent == true && strcmp(s->id, student->id) == 0)
        {
            free(s);
            return true;
        }
        else if(student->isStudent == false && strcmp(s->id, student->id) == 0)
        {
            free(s);
            return false;
        }
        else if(strcmp(student->id, "\0") == 0)
        {
            free(s);
            return false;
        }
        
        currentPosStudent = aux + pow(offset, 2);
        offset += offset;

        currentPosStudent = currentPosStudent % MAX_SIZE;
        
        //vai para a posicao calculada pela funcao de hash
        fseek(file, currentPosStudent * sizeof(Student), SEEK_SET);           
    }
    return false;
}

bool insertStudent(FILE *file, Student *student)
{
    if(insertPosStudent(file, student) == true)
    {
        student->isStudent = true;

        //fread avanca uma posicao entao temos de voltar a posicao inicial
        if(fseek(file, currentPosStudent * sizeof(Student), SEEK_SET) == 0)
        {
            if(fwrite(student, sizeof(Student), 1, file) == 1)
            {
                return true;
            }
        }

        
    }
    return false;
}

bool removeStudent(FILE *file, Student *student)
{
    if(searchPosStudent(file, student) == true)
    {   
        if(student->quit == true || student->finished == true)
            return false;

        student->isStudent = false;

        //fread avanca o pointer entao temos de voltar a posicao inicial
        if(fseek(file, currentPosStudent * sizeof(Student), SEEK_SET) == 0)
        {
            //se fwrite != 1 ocorreu um erro
            if(fwrite(student, sizeof(Student), 1, file) == 1)
            {
                //printf("REMOVIDO NA POSICAO %ld\n", currentPosStudent);
                return true;
            }
        }
    }
    return false;
}

bool abandoned(FILE *file, Student *student)
{
    if(searchPosStudent(file, student) == true)
    {
        if(student->quit == true || student->finished == true)
            return false;

        //marcar estudante como abandono do curso
        student->quit = true;

        //fread avanca o pointer entao temos de voltar a posicao inicial
        if(fseek(file, currentPosStudent * sizeof(Student), SEEK_SET) == 0)
        {
            //se fwrite != 1 ocorreu um erro
            if(fwrite(student, sizeof(Student), 1, file) == 1)
            {
                return true;
            }
        }
    }
    return false;  
}

bool finished(FILE *file, Student *student)
{
    if(searchPosStudent(file, student) == true)
    {        
        if(student->finished == true || student->quit == true)
            return false;

        //marcar estudante como abandono do curso
        student->finished = true;

        //fread avanca o pointer entao temos de voltar a posicao inicial
        if(fseek(file, currentPosStudent * sizeof(Student), SEEK_SET) == 0)
        {
            //se fwrite != 1 ocorreu um erro
            if(fwrite(student, sizeof(Student), 1, file) == 1)
            {
                return true;
            }
        }
    }

    return false;  
}

char *getCountry(FILE *file, Student *student)
{
    if(searchPosStudent(file, student) == true)
    {
        return student->p_id;
    }

    return "--";
}
