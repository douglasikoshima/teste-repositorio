#ifndef SITTELMONITORH
#define SITTELMONITORH

#define TAM_BD_PASSWD       32
#define TAM_BD_USRNAME      32
#define TAM_BD_INST         32

#define MAX_DEFAULT_INST    20

typedef struct
{
    char szPws[TAM_BD_PASSWD + 1];      // pass BD          pwd_db
    char szUsr[TAM_BD_USRNAME + 1];     // user DB          usr_db
    char szInst[TAM_BD_INST + 1];       // instancia BD     inst_db
    char szMessageParse[2]; // Tratar mensagem de envio?
    char szMessageLog[2]; // logar a mensagem de envio?
} TParamConf;

// Prototypes
int ObtemParamConf(TParamConf *ptParamConf);
int ObterParamMaxRegistros(void);
int ObterParamMaxInstancia(void);
int Process(char *pszFileName, int iFlagLock);
int InitSemaphore(key_t key, int nsems);
int ReservaLoteRegistros( char * idControleFilaSMS, char * nrMaxRegistros_PRM, char * sParticao );
int ProcessaRegistros ( char * idControleFilaSMS, char * host, char * porta, char * sParticao );
int ConectaTorpedeira( char * host, char * porta );
int EnviaSMS( int fdSocket, char * Destino, char * Msg );
double getRealTime(void);
void ElapsedTime(double StartTime, double EndTime, char * UsedTime);
int DBConnect(char *pUsr, char *pPwd, char *pInst);
int DBDisconnect(void);
int AtualizaEstadoProcesso ( char * idCtlFilaSMS_PRM, char * Status_Prm );
int VoltaEstadoOriginal( char * idControleFilaSMS, char * sParticao );
char* tratamentoErro(int cdErro);
int ObterParamMaxParticao( void );
void AtualizaParticao( char * sParticaoPrm );

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

#define CONVIND(O,I) \
{\
    if (I == -1) { \
        ##O.arr[0]=0; \
    } else { \
        ##O.arr[##O.len]=0; \
    } \
}

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;
#define endOraStr(varstr)      varstr.arr[varstr.len]= '\0'

