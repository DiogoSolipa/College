#include "hashTable.h"

#define COUNTRY_SIZE 900001

Country *country_new(char *p_id)
{
    Country *country = malloc(sizeof(Country));

    strcpy(country->p_id, p_id);

    country->active = country->finished = country->abandoned = country->total = 0;
    country->isCountry = false;

    return country;
}

unsigned long hashCountry(char *s)
{
    unsigned long hashKey = 5381; //numero utilizado pelo algoritmo para começar
    int c;
    while((c = *s++)) // c vai ter o ASCII de cada char e *s++ percorre a string
    {
        hashKey = ((hashKey << 5) + hashKey) + c; //shift left 5 bits da hashkey + hashkey + valor ASCI de cada chat
    }  

    return hashKey % COUNTRY_SIZE; //divisao para nao sair out of bounds da HashTable
}

//funcao que encontra uma posicao disponivel para insercao e marca um pointer no 1 byte dessa posicao
unsigned long insertPosCountry(FILE *file, Country *country)
{
    unsigned long currentPos = hashCountry(country->p_id);
    unsigned long aux = currentPos;
    unsigned long offset = sizeof(Country);

    Country *c = country_new(country->p_id);

    fseek(file, currentPos * sizeof(Country), SEEK_SET);

    while(fread(c, sizeof(Country), 1, file) == 1)
    {
        if(currentPos < 0 && aux < 0)
        {
            currentPos *= -1;
            aux *= -1;
        }    
        if(strcmp(c->p_id, "\0") == 0)
        {
            free(c);
            return currentPos;
        }
        else if(strcmp(c->p_id, country->p_id) == 0)
        {
            free(c);
            return -1;
        }

        currentPos = aux + pow(offset, 2);
        offset += offset;

        currentPos = currentPos % COUNTRY_SIZE;

        fseek(file, currentPos * sizeof(Country), SEEK_SET);
    }

    return currentPos;
}

//funcao que procura a posicao de um elemento especifico e marca um pointer no ficheiro no 1 byte desse elemento
unsigned long searchPosCountry(FILE *file, Country *country)
{
    unsigned long currentPos = hashCountry(country->p_id);
    unsigned long aux = currentPos;
    unsigned long offset = sizeof(Country);

    Country *c = country_new(country->p_id);

    fseek(file, currentPos * sizeof(Country), SEEK_SET);

    while(fread(country, sizeof(Country), 1, file) == 1)
    {   
        if(currentPos < 0 && aux < 0)
        {
            currentPos *= -1;
            aux *= -1;
        }

        if(strcmp(c->p_id, country->p_id) == 0)
        {
            //free(c);
            return currentPos;
        }
        else if(strcmp(country->p_id, "\0") == 0)
        {
            return -1;
        }

        currentPos = aux + pow(offset, 2);
        offset += offset;

        currentPos = currentPos % COUNTRY_SIZE;

        fseek(file, currentPos * sizeof(Country), SEEK_SET); 
    }

    return -1;
}

bool insertCountry(FILE *file, Country *country)
{
    unsigned long pos = insertPosCountry(file, country);

    if(pos != -1)
    {
        country->isCountry = true;

        //fread avanca o pointer entao temos de voltar a posicao inicial
        if(fseek(file, pos * sizeof(Country), SEEK_SET) == 0)
        {
            if(fwrite(country, sizeof(Country), 1, file) == 1)
            {
                return true;
            }
        }
    }
    return false;
}

void addActive(FILE *file, Country *country, unsigned long pos)
{    
        country->active++;
        country->total++;

        //fread avanca o pointer entao temos de voltar a posicao inicial
        if(fseek(file, pos * sizeof(Country), SEEK_SET) == 0)
        {
            fwrite(country, sizeof(Country), 1, file); 
        }
}

void removeActive(FILE *file, Country *country)
{
    unsigned long pos = searchPosCountry(file, country);

    if(pos != -1)
    {
        country->active--;
        country->total--;

        //fread avanca o pointer entao temos de voltar a posicao inicial
        if(fseek(file, pos * sizeof(Country), SEEK_SET) == 0)
        {
            fwrite(country, sizeof(Country), 1, file); 
        }
    }
}

void addAbandoned(FILE *file, Country *country)
{
    unsigned long pos = searchPosCountry(file, country);

    if(pos != -1)
    {
        country->abandoned++;
        country->active--;

        //fread avanca o pointer entao temos de voltar a posicao inicial
        if(fseek(file, pos * sizeof(Country), SEEK_SET) == 0)
        {
            fwrite(country, sizeof(Country), 1, file);
        }
    }
}

void addFinished(FILE *file, Country *country)
{
    unsigned long pos = searchPosCountry(file, country);

    if(pos != -1)
    {   
        country->finished++;
        country->active--;

        //fread avanca o pointer entao temos de voltar a posicao inicial
        if(fseek(file, pos * sizeof(Country), SEEK_SET) == 0)
        {
            fwrite(country, sizeof(Country), 1, file);
        }
    }
}