#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

//hashtable.h nao e incluida 2 vezes causando erro
#ifndef HASHTABLE_H
#define HASHTABLE_H
#define ID_SIZE 7
#define P_ID_SIZE 3

typedef struct student 
{
    char id[ID_SIZE], p_id[P_ID_SIZE];
    bool quit, finished;
    bool isStudent;
}Student;

typedef struct country
{
    char p_id[P_ID_SIZE];
    int active, finished, abandoned, total; 
    bool isCountry;
}Country;

Student *student_new();
Country *country_new();

unsigned long hashStudent(char *s);
unsigned long hashCountry(char *s);

//Student Hashtable
bool searchPosStudent(FILE *file, Student *student);
bool insertPosStudent(FILE *file, Student *student);
bool insertStudent(FILE *file, Student *student);
bool removeStudent(FILE *file, Student *student);
bool abandoned(FILE *file, Student *student);
bool finished(FILE *file, Student *student);
char *getCountry(FILE *file, Student *student);

//Country Hashtable
unsigned long searchPosCountry(FILE *file, Country *country);
unsigned long insertPosCountry(FILE *file, Country *country);
bool insertCountry(FILE *file, Country *country);
void addActive(FILE *file, Country *country, unsigned long pos);
void removeActive(FILE *file, Country *country);
void addAbandoned(FILE *file, Country *country);
void addFinished(FILE *file, Country *country);
#endif