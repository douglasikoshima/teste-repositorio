#ifndef FINALIZACADASTROPORTINH
#define FINALIZACADASTROPORTINH

#define VIVO_GENERIC_PASSWORD       "vivo"
#define TUX_USRNAME                 "demoapp"
#define TUX_CLTNAME                 "tester"
#define VIVO_APP_PASSWORD           "vivo"

#define ID_USUARIOALTERACAO         "-123"


typedef struct {
    char szPws[10 + 1];          // pass BD          pwd_db
    char szUsr[10 + 1];          // user DB          usr_db
    char szInst[20 + 1];         // instancia BD     inst_db
} TParamConf;


typedef struct {
    char szIdLinhaTelefonica[21 + 1];
    char szIdConta[21 + 1];
} TDadosPublicadosNGIN;

typedef struct {
    char szIdTipoRelacionamento[21 + 1];
    char szIdPessoaDePara[21 + 1];
    char szIdLinhaTelefonica[21 + 1];
} TPessoaLinha;


typedef struct {
    char szIdPessoaLinhaHistorico[21 + 1];
    char szCdAreaRegistro[21 + 1];
    char szNrLinha[21 + 1];
    char szIdTipoRelacionamento[21 + 1];
    char szIdPessoaDePara[21 + 1];
    char szIdLinhaTelefonica[21 + 1];
} TPessoaLinhaHistorico;


typedef struct {
    char szIdPessoaConta[21 + 1];
    char szIdTipoRelacionamento[21 + 1];
    char szIdPessoaDePara[21 + 1];
    char szIdConta[21 + 1];
} TPessoaConta;

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


/* prototipos */
void Sumariza(int iQtdTotalRegNaoPublicado, int iQtdTotalRegOK, int iQtdTotalRegErro);
char *ObtemDataHora(void);
void ArmaSinal(int iSignal);
void ProcessaSignal(int iSig);
int ObtemParamConf(TParamConf *ptParamConf, char *pszExecName);

int DBConnect(char *pUsr, char *pPwd, char *pInst);
void DBDesconect(void);

bool inserePessoaLinha(TPessoaLinha *ptPessoaLinha);
bool inserePessoaLinhaHistorico(TPessoaLinhaHistorico *ptPessoaLinhaHistorico);
bool inserePessoaConta(TPessoaConta *ptPessoaConta);
bool insereFilaSetClientInfo(char *pszIdLinhaTelefonica, char *pszXml);
bool buscaDadosPublicados(char *pszCdAreaRegistro, char *pszNrLinha, TDadosPublicadosNGIN *ptDadosPublicadosNGIN);
bool atualizaPessoaPortabilidade(char *pszIdPessoaDePara);
bool ObtemDadosXml(char *pszIdLinhaTelefonica, TObtemDadosXml *ptObtemDadosXml);
TDadosDocumento *BuscaDocumentoPorIdPessoa(char *pszIdPessoa);
bool proCBuscaDocumentoPorIdPessoa(char *pszIdPessoa);
void controleAlocacao(void);

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

