#ifndef VINCULORECARGASMSH
#define VINCULORECARGASMSH

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

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;

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
bool existeRegistroNavinculoRecargaSMS(char *pszIdLinhaTelefonica);

int DBConnect(char *pUsr, char *pPwd, char *pInst);
int InitSemaphore(key_t key, int nsems);
int ObtemParamConf(TParamConf *ptParamConf);
int ObterParamMaxRegistros(int LimMax);
int Process(char *pszFileName, int iFlagLock);

// void ConectarTibco(tibemsSSLParams &sslParams,tibemsQueueConnection *queueConnection,tibemsQueue &queue,tibemsQueueSender *queueSender,TParamConf &tParamConf,char *argVZero);
// void DesconectarTibco(tibemsSSLParams &sslParams,tibemsQueueConnection &queueConnection,tibemsQueue &queue);

void ArmaSinal(int iSignal);
void DBDisconnect(void);
void EnviarMensagemTibco(const char *idAtendimentoProtocolo,const char *cdAreaRegistro,const char *nrLinha,const char *dsMensagemEnvio,const char *queueName,char *argVZero,tibemsQueueSender &queueSender,const char *pSzMessageLog);
void Fail(const char* message, tibems_status s);
void LockSemaphore(int semid);
void ObterParamMsgLog(TParamConf *tParamConf);
void ObterParamMsgParse(TParamConf *tParamConf);
void ProcessaSinal(int iSig);
void UnLockSemaphore(int semid);

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
