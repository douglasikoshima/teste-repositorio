#ifndef ENVIAVIPH
#define ENVIAVIPH

#define VIVO_GENERIC_PASSWORD       "vivo"
#define TUX_USRNAME                 "demoapp"
#define TUX_CLTNAME                 "tester"
#define VIVO_APP_PASSWORD           "vivo"


typedef struct {
    char szPws[15 + 1];          // pass BD          pwd_db
    char szUsr[15 + 1];          // user DB          usr_db
    char szInst[20 + 1];         // instancia BD     inst_db
    char szPwsTux[10 + 1];       // pass TUXEDO      pwd_tux
    char szUsrTux[10 + 1];       // user TUXEDO      usr_tux
    char szCltTux[10 + 1];       // client TUX       clt_tux
    char szPwsTuxGen[10 + 1];    // pass TUX GEN     pwd_tux_gen
} TParamConf;

/* prototipos */
int TUXinit(TParamConf *ptParamConf);
int sendXML(char *pXmlSaida, char *pRetMsg, char* pServico);

int DBConnect(char *pUsr, char *pPwd, char *pInst);
void DBDesconect(void);
bool DeletaRegistro(char *pszIdLinhaTelefonica);
bool AtualizaRegistro(char *pszIdLinhaTelefonica, char *pszStatusCode, char *pszStatusText);
char *ObtemParametro(char *pCdParametro);
char *ObtemDtTimeStamp(char *pszIdLinhaTelefonica);

char *ObtemValorTag(char *pXml, char *pTag);
char *ObtemDataHora(void);
void Sumariza(int iQtdRegCursor, int iQtdLoops, int iQtdTotalRegOK, int iQtdTotalRegErro, int iQtdTotalRegReProc, int iQtdTotalReg);
int ObtemParamConf(TParamConf *ptParamConf);
bool ContinuaProcessamento(int *piSleep);
int ObtemTempoSleep(void);
int ObtemTamanhoCursor(void);
void ObtemParamBase(int *piSleep);
void registrarContato(char* cServico, int iNrLinha, int iCdAreaRegistro);

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
