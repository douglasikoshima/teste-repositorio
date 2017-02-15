
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <time.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

#define MAX_RETRIES     10

#define LOCK            1
#define UNLOCK          0
#define KILL            2


void LockSemaphore(int semid);
void UnLockSemaphore(int semid);
int InitSemaphore(key_t key, int nsems);
int Process(char *pszFileName, int iFlagLock);


#include "../include/procMatrizesRetencaoPC.h"
#include "../../commons/Log/include/Log.h"

int main(int argc, char* argv[])
{
    Log log;

    log.logInformation("Iniciando o processamento de matrizes retencao.\n");

    /*
    int iRet = Process(argv[0], LOCK);
    if(iRet) {
    	log.logInformation("Saida por TIMEOUT\n");
        return -1;
    }*/

    cprocMatrizesRetencaoPC apx;

    apx.processa();

    //Process(argv[0], UNLOCK);

    log.logInformation("PRONTO - Processamento finalizado.\n");

    return 0;
}




/********************************************************************************************/
int Process(char *pszFileName, int iFlagLock)
{
    static int semid;
    time_t tStart, tEnd;
    double tDiff;
    key_t key;


    if(iFlagLock == UNLOCK) {
        UnLockSemaphore(semid);
    }
    else if(iFlagLock == LOCK) {

        if((key = ftok(pszFileName, 'a')) == (key_t) -1) {
            perror("IPC error: ftok");
            exit(-1);
        }
        /* printf("key(%ld)\n", key); */

        if((semid = InitSemaphore(key, 1)) == -1) {
            perror("InitSemaphore");
            exit(-1);
        }

        time(&tStart);
        LockSemaphore(semid);
        time(&tEnd);

        tDiff = difftime(tEnd, tStart);
        /* printf("difftime (%lf) segundos\n", tDiff); */

        /* tempo limite para espera de processamento */
        if(tDiff > (double)0)
            return -1;

    }
    else if(iFlagLock == KILL) {
        semctl(semid, 0, IPC_RMID); /* deleta o semaforo */
    }
    else {
        /* printf("Error\n"); */
        exit(-1);
    }

    return 0;
}

/********************************************************************************************/
void UnLockSemaphore(int semid)
{
    struct sembuf sb;

    sb.sem_num = 0;
    sb.sem_op = 1; /* free resource */
    sb.sem_flg = SEM_UNDO;

    /* printf("Desalocando...\n"); */

    if (semop(semid, &sb, 1) == -1) {
        perror("semop");
        exit(1);
    }

    /* printf("Desalocado...\n"); */
}

/********************************************************************************************/
void LockSemaphore(int semid)
{
    struct sembuf sb;

    sb.sem_num = 0;
    sb.sem_op = -1;  /* set to allocate resource */
    sb.sem_flg = SEM_UNDO;

    /* printf("Locando...\n"); */

    if (semop(semid, &sb, 1) == -1) {
        perror("semop");
        exit(1);
    }

    /* printf("Locado...\n"); */
}

/********************************************************************************************/
/*
** InitSemaphore() -- more-than-inspired by W. Richard Stevens' UNIX Network
** Programming 2nd edition, volume 2, lockvsem.c, page 295.
*/
int InitSemaphore(key_t key, int nsems)
{
    union semun {
        int val;
        struct semid_ds *buf;
        ushort *array;
    } arg;

    int i;
    struct semid_ds buf;
    struct sembuf sb;
    int semid;

    semid = semget(key, nsems, IPC_CREAT | IPC_EXCL | 0666);
    if (semid >= 0) { /* we got it first */
        sb.sem_op = 1;
        sb.sem_flg = 0;
        arg.val = 1;

        /* printf("Criando semaforo... (%d)\n", semid); */

        for(sb.sem_num = 0; sb.sem_num < nsems; sb.sem_num++) {
            /* do a semop() to "free" the semaphores. */
            /* this sets the sem_otime field, as needed below. */
            if (semop(semid, &sb, 1) == -1) {
                int e = errno;
                semctl(semid, 0, IPC_RMID); /* clean up */
                errno = e;
                return -1; /* error, check errno */
            }
        }
    }
    else if (errno == EEXIST) {
        int ready = 0;

        semid = semget(key, nsems, 0); /* get the id */
        /* printf("Semaforo já criado... (%d)\n", semid); */
        if (semid < 0)
            return semid; /* error, check errno */

        /* wait for other process to initialize the semaphore: */
        arg.buf = &buf;

        /*
        printf("arg.buf->sem_otime(%d)\n", arg.buf->sem_otime);
        printf("arg.buf->sem_nsems(%d)\n", arg.buf->sem_nsems);
        printf("arg.buf->sem_ctime(%d)\n", arg.buf->sem_ctime);
        */

        for(i = 0; i < MAX_RETRIES && !ready; i++) {
            semctl(semid, nsems-1, IPC_STAT, arg);

            if (arg.buf->sem_otime != 0) {
                ready = 1;
            }
            else {
                /* printf("sleep\n"); */
                sleep(1);
            }
        }
        if(!ready) {
            errno = ETIME;
            return -1;
        }
    }
    else {
        return semid; /* error, check errno */
    }

    /* printf("semid(%d)\n", semid); */
    return semid;
}
