#ifndef RELPALITAGEMH
#define RELPALITAGEMH

#define ID_CONNECT_ORIGEM           1
#define ID_CONNECT_DESTINO          2


#define LEN_RESULTADO               100

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

int geraRelatorio(char *pszIdRelatorioTMA, char *pszDtUltimaAlteracao);
int geraArquivo(char *pszResultado, char *pszDtUltimaAlteracao);
int adicionaIdRelatorioTMA(char *pszIdRelatorioTMA);
void ArmaSinal(int iSignal);
void ProcessaSignal(int iSig);
int ObtemParamConf(int iConexao);
bool AtualizaRegistro(char *pszIdRelatorioTMA);
int DBConnect(char *pUsr, char *pPwd, char *pInst);
void DBDesconect(void);
int Conecta(int iConexao);
int atualizaPorIdRelatorioTMA(void);


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
