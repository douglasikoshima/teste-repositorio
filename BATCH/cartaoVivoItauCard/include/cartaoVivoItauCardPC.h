#ifndef _CARTAOVIVOITAUCARD_H_
#define _CARTAOVIVOITAUCARD_H_

#ifndef _MAX_PATH
#   define _MAX_PATH 1024
#endif

#ifndef _MAX_FNAME
#   define _MAX_FNAME 256
#endif

#define TAM_BD_PASSWD 32
#define TAM_BD_USRNAME 32
#define TAM_BD_INST 32
#define TAM_TX_PASSWD TAM_BD_PASSWD
#define TAM_TX_USRNAME TAM_BD_USRNAME
#define TAM_TX_CLT 32
#define TAM_TX_GEN 32

#define STATUS_ERRO     0
#define STATUS_SUCESSO  1
#define STATUS_VAZIO    2

typedef struct
{
    char szPws[TAM_BD_PASSWD + 1];     // pass BD          pwd_db
    char szUsr[TAM_BD_USRNAME + 1];    // user DB          usr_db
    char szInst[TAM_BD_INST + 1];      // instancia BD     inst_db
    char szPwsTux[TAM_TX_PASSWD + 1];  // pass TUXEDO      pwd_tux
    char szUsrTux[TAM_TX_USRNAME + 1]; // user TUXEDO      usr_tux
    char szCltTux[TAM_TX_CLT + 1];     // client TUX       clt_tux
    char szPwsTuxGen[TAM_TX_GEN + 1];  // pass TUX GEN     pwd_tux_gen
    char szFileNameIn[_MAX_FNAME + 1];
    char szPathIn[_MAX_PATH + 1];
    char szFileNameErr[_MAX_FNAME + 1];
    char szPathErr[_MAX_PATH + 1];
    char szPathProcessados[_MAX_PATH + 1];
} TParamConf;

/* prototipos */
int DBConnect(char *pUsr, char *pPwd, char *pInst);
void DBDisconnect(void);
int ObtemParamConf(TParamConf *ptParamConf);

void ArmaSinal(int iSignal);
void ProcessaSignal(int iSig);

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
