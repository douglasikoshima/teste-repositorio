#ifndef REPROCESSANGINH
#define REPROCESSANGINH

#define VIVO_GENERIC_PASSWORD       "vivo"
#define TUX_USRNAME                 "demoapp"
#define TUX_CLTNAME                 "tester"
#define VIVO_APP_PASSWORD           "vivo"


typedef struct {
    char szIdTipoPessoa[25 + 1];
    char szIdPessoa[25 + 1];
    char szTipoCliente[1 + 1];
    char szNome[255 + 1];
    char szConfidencial[1 + 1];
    char szDataNascimento[14 + 1];
    char szEstadoCivil[1 + 1];
    char szCodSexo[1 + 1];
} TObtemDadosXml;

typedef struct {
    char szSgTipoDocumento[255 + 1];
    char szNrDocumento[255 + 1];
    char szSgOrgaoExpedidor[255 + 1];
} TDadosDocumento;

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
bool AtualizaRegistro(char *pszIdLinhaTelefonica, char *pszXml);
bool ObtemDadosXml(char *pszIdLinhaTelefonica, TObtemDadosXml *ptObtemDadosXml);
char *ObtemDtTimeStamp(char *pszIdLinhaTelefonica);

char *ObtemDataHora(void);
void Sumariza(int iQtdRegCursor, int iQtdLoops, int iQtdTotalRegReProc, int iQtdTotalRegReProcError, int iQtdTotalReg);
int ObtemParamConf(TParamConf *ptParamConf);

void ArmaSinal(int iSignal);
void ProcessaSignal(int iSig);


void controleAlocacao(void);
bool proCBuscaDocumentoPorIdPessoa(char *pszIdPessoa);
TDadosDocumento *BuscaDocumentoPorIdPessoa(char *pszIdPessoa);

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
