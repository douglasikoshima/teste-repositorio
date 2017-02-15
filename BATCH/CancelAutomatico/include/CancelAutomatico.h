#ifndef ENVIOSEGMH
#define ENVIOSEGMH

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

typedef struct
{
    char szPws[TAM_BD_PASSWD + 1];      // pass BD          pwd_db
    char szUsr[TAM_BD_USRNAME + 1];     // user DB          usr_db
    char szInst[TAM_BD_INST + 1];       // instancia BD     inst_db
    char szPwsTux[TAM_TX_PASSWD + 1];   // pass TUXEDO      pwd_tux
    char szUsrTux[TAM_TX_USRNAME + 1];  // user TUXEDO      usr_tux
    char szCltTux[TAM_TX_CLT + 1];      // client TUX       clt_tux
    char szPwsTuxGen[TAM_TX_GEN + 1];	// pass TUX GEN     pwd_tux_gen

    char szQueueName[TAM_SZ_QUEUE_NAME + 1]; // VIVO.FO.SMS.SEND  QueueName
    char szServerUrl[TAM_SZ_SERVER_URL + 1]; // itaborai          ServerUrl
    char szUserName[TAM_SZ_USERNAME + 1];    // fo                UserName
    char szPassword[TAM_SZ_PASSWORD + 1];    // 71b60             Password
    char szPk_password[TAM_SZ_PKPASSWD + 1]; // teste             Pk_password

    char szMessageParse[2]; // Tratar mensagem de envio?
    char szMessageLog[2]; // logar a mensagem de envio?
} TParamConf;


// prototipos
int DBConnect(char *pUsr, char *pPwd, char *pInst);
void DBDisconnect(void);
int InitSemaphore(key_t key, int nsems);
int ObtemParamConf(TParamConf *ptParamConf);


/*******************************************************************************
 * Macros para manipulacao de dados ProC/C++
 *******************************************************************************/
#define CONVIND(O,I) \
{\
    if (I == -1) { \
        ##O.arr[0]=0; \
    } else { \
        ##O.arr[##O.len]=0; \
    } \
}

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;

#define STRCPY_TO_ORA(dest, source) { \
    dest.len = (unsigned short) strlen(source);\
    strncpy((char *) dest.arr, (const char *) source, (size_t)dest.len); }

#define STRCPY_FROM_ORA(dest, source) { \
    dest[source.len] = 0; \
    strncpy((char *)dest,(const char *)source.arr, source.len); }

#endif
