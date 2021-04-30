#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

void forkTest()
{
    puts("TESTE FORK:\n");

    pid_t pid;

    pid = fork(); //cria processo

    if (pid > 0)
    {
        printf("P1rocesso Pai PID: %d ", pid);
    }
    else
    {
        printf("P1rocesso Filho PID: %d ", pid);
    }
    puts("FIM");
}

void forkTest2()
{
    puts("TESTE FORK2:\n");

    pid_t pid;

    pid = fork(); //cria processo

    if (pid > 0)
    {
        printf("P2rocesso Pai PID: %d ", pid);
    }
    else
    {
        printf("P2rocesso Filho PID: %d ", pid);
    }
    puts("FIM");
}

void main()
{
    forkTest();
    forkTest2();
}