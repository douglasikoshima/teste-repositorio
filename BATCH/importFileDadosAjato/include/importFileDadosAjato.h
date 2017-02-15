#ifndef TEMPLATEPROJH
#define TEMPLATEPROJH

#define TAM_BD_PASSWD       32
#define TAM_BD_USRNAME      32
#define TAM_BD_INST         32
#define TAM_TX_PASSWD       TAM_BD_PASSWD
#define TAM_TX_USRNAME      TAM_BD_USRNAME
#define TAM_TX_CLT          32
#define TAM_TX_GEN          32

#define TAM_SZ_QUEUE_NAME   100
#define TAM_SZ_SERVER_URL   50
#define TAM_SZ_USERNAME     20
#define TAM_SZ_PASSWORD     20
#define TAM_SZ_PKPASSWD     20
#define TAM_SZ_PATH         255

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;

void UnLockSemaphore(int semid);
void LockSemaphore(int semid);
int InitSemaphore(key_t key, int nsems);
int obterParameters();
int DBConnect();
int DBDisconnect();

/*******************************************************************************
 * Macros para manipulacao de dados ProC/C++
 *******************************************************************************/
#define STRCPY_TO_ORA(dest, source) { \
    dest.len = (unsigned short) strlen(source);\
    strncpy((char *) dest.arr, (const char *) source, (size_t)dest.len); }

#define STRCPY_FROM_ORA(dest, source) { \
    dest[source.len] = 0; \
    strncpy((char *)dest,(const char *)source.arr, source.len); }

#endif
