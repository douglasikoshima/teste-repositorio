#define TAM_BD_PASSWD       32
#define TAM_BD_USRNAME      32
#define TAM_BD_INST         32

#define LEN_NMARQ_CSV       25
#define LEN_NMARQ_LOG       19



typedef struct
{
    char szPws[TAM_BD_PASSWD + 1];      // pass BD          pwd_db
    char szUsr[TAM_BD_USRNAME + 1];     // user DB          usr_db
    char szInst[TAM_BD_INST + 1];       // instancia BD     inst_db
} TParamConf;


// prototipos
int DBConnect(char *pUsr, char *pPwd, char *pInst);
void DBDisconnect(void);
void ExpurgoLog( char * sDiretorio );
void ExpurgoRelatorio( char * sDiretorio );
void CancelMovel( void );
void CancelFixa( void );
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
