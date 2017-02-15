#ifndef CADPREPAGOH
#define CADPREPAGOH

#define VIVO_GENERIC_PASSWORD       "vivo"
#define TUX_USRNAME                 "demoapp"
#define TUX_CLTNAME                 "tester"
#define VIVO_APP_PASSWORD           "vivo"

#define DIVISOR_DE_CAMPO            ';'

typedef struct {
    char szPws[10 + 1];          // pass BD          pwd_db
    char szUsr[10 + 1];          // user DB          usr_db
    char szInst[20 + 1];         // instancia BD     inst_db
    char szPwsTux[10 + 1];       // pass TUXEDO      pwd_tux
    char szUsrTux[10 + 1];       // user TUXEDO      usr_tux
    char szCltTux[10 + 1];       // client TUX       clt_tux
    char szPwsTuxGen[10 + 1];    // pass TUX GEN     pwd_tux_gen
    char szPath[255 + 1];
} TParamConf;


typedef struct {
    char szNomeCliente[255 + 1];
    char szCpf[11 + 1];
    char szDDD[2 + 1];
    char szNrLinha[10 + 1];
    char szDtNascimento[8 + 1];
    char szCep[8 + 1];
    char szEndereco[255 + 1];
    char szNrEndereco[255 + 1];
    char szComplemento[255 + 1];
    char szBairro[255 + 1];
    char szMunicipio[255 + 1];
    char szUf[255 + 1];
    char szSexo[1 + 1];
} TDadosEntrada;


/* prototipos */
void Sumariza(int iQtdRegCursor, int iQtdLoops, int iQtdTotalRegOK, int iQtdTotalRegErro, int iQtdTotalReg);
char *ObtemDataHora(void);
char *ObtemValorTag(char *pXml, char *pTag);
int TUXinit(TParamConf *ptParamConf);
int sendXML(char *pXmlSaida, char *pRetMsg);
void ArmaSinal(int iSignal);
void ProcessaSignal(int iSig);
int ObtemParamConf(TParamConf *ptParamConf, char *pszExecName);
bool AtualizaRegistroErro(char *pszIdCadPrepago, char *pszStatusCode, char *pszStatusText);
bool AtualizaRegistroOK(char *pszIdCadPrepago);
int DBConnect(char *pUsr, char *pPwd, char *pInst);
void DBDesconect(void);
char *OpenDir(char *pszDiretorio);
TDadosEntrada *leArquivo(char *pszPath, char *pszFileName);
void TrataDadosEntrada(char *pszStringEntrada, TDadosEntrada *ptDadosEntrada);
bool InsereRegistro(TDadosEntrada *ptDadosEntrada);
int renomeiaArquivo(char *pszPath, char *pszArquivoEntrada);
char *obtemDado(char *pszString);

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
