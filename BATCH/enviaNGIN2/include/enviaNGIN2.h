#ifndef ENVIANGINH
#define ENVIANGINH

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
    char szNmTipoLogradouro[255 + 1];
    char szEndereco[512 + 1];
    char szDsEnderecoComplemento[255 + 1];
    char szNmBairro[255 + 1];
    char szNrCep[255 + 1];
    char szCidade[255 + 1];
    char szEstado[25 + 1];
    char szPais[3 + 1];
    char szNumero[255 + 1];
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
    char szPath[255 + 1];
    char szPath2[255 + 1];
    char szPathToGo[255 + 1];
} TParamConf;

/* prototipos */
bool InsereRegistro(char *pszIdLinhaTelefonica, char *pszXml);
bool ObtemDadosXml(char *pszIdLinhaTelefonica, TObtemDadosXml *ptObtemDadosXml);
bool ObtemIdLinhaTelefonica(char *pszNrLinha, char *pszIdLinhaTelefonica);
bool proCBuscaDocumentoPorIdPessoa(char *pszIdPessoa);

char *leArquivo(char *pszPath, char *pszFileName);
char *ObtemDataHora(void);
char *ReadDir(DIR *pDir);

DIR *OpenDir(char *pszDiretorio);
int DBConnect(char *pUsr, char *pPwd, char *pInst);
int geraArquivo(char *pszNrLinha, char *pszStatus, char *pszPath, char *pszFileName);
int ObtemParamConf(TParamConf *ptParamConf);

TDadosDocumento *BuscaDocumentoPorIdPessoa(char *pszIdPessoa);

void ArmaSinal(int iSignal);
void CloseDir(DIR *pDir);
void controleAlocacao(void);
void DBDisconnect(void);
void moverArquivo(char *pszPathOrigem,char *pszPathDestino,char *pszFileName);
void ProcessaSignal(int iSig);
void Sumariza(int iQtdTotalRegReProc, int iQtdTotalRegReProcError, char *pszPatch, char *pszBatchName);
void TrataDadosEntrada(char *pszBuffer);

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
