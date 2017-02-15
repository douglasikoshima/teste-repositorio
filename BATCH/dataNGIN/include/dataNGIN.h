#ifndef DATANGINH
#define DATANGINH

#define VIVO_GENERIC_PASSWORD       "vivo"
#define TUX_USRNAME                 "demoapp"
#define TUX_CLTNAME                 "tester"
#define VIVO_APP_PASSWORD           "vivo"

typedef struct {
    char szPws[10 + 1];          // pass BD          pwd_db
    char szUsr[10 + 1];          // user DB          usr_db
    char szInst[20 + 1];         // instancia BD     inst_db
    char szPwsTux[10 + 1];       // pass TUXEDO      pwd_tux
    char szUsrTux[10 + 1];       // user TUXEDO      usr_tux
    char szCltTux[10 + 1];       // client TUX       clt_tux
    char szPwsTuxGen[10 + 1];    // pass TUX GEN     pwd_tux_gen
} TParamConf;

/* prototipos */
int DBConnect(char *pUsr, char *pPwd, char *pInst);
void DBDesconect(void);

int ObtemParamConf(TParamConf *ptParamConf);
char *ObtemValorTag(char *pXml, char *pTag);
bool AtualizaRegistro(char *pszIdFilaSetClientInfo, char *pszXML1);
void formataXML(char *pszXML1, char *pszTag, int iLenDadosTag);


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
