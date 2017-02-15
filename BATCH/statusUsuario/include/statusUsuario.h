#ifndef STATUSUSUARIOH
#define STATUSUSUARIOH

#define VIVO_GENERIC_PASSWORD       "vivo"
#define VIVO_APP_PASSWORD           "vivo"


/* extensao dos nomes dos arquivos de saida no decorrer do processamento */
#define ID_STATUS_ARQ_SAIDA_PENDENTE       ".Pendente"
#define ID_STATUS_ARQ_SAIDA_CONCLUIDO      ".Concluido"
#define ID_STATUS_ARQ_SAIDA_PROCESSAMENTO  ".Processamento"

typedef struct {
    char szPws[10 + 1];          // pass BD          pwd_db
    char szUsr[10 + 1];          // user DB          usr_db
    char szInst[20 + 1];         // instancia BD     inst_db
} TParamConf;

typedef struct {
    char szIdStatusUsuario[21 + 1];
    char szIdUsuarioAlteracao[21 + 1];
} TDadosEntrada;

typedef struct {
    char szIdPessoaUsuario[21 + 1];
    char szCPF[255 + 1];
    char szNomeUsuario[255 + 1];
    char szStatus[255 + 1];
    char szLoginExecutor[255 + 1];
    char szDataOperacao[19 + 1]; // 01/12/2005 12:14:13
} TDadosSaida;

/* referente ao oracle */
int DBConnect(char *pUsr, char *pPwd, char *pInst);
void DBDesconect(void);

/* tratamento para interrupcao do processamento */
void ArmaSinal(int iSignal);
void ProcessaSignal(int iSig);

/* conexao oracle/tuxedo */
int ObtemParamConf(TParamConf *ptParamConf);

/* tratamento dos arquivos de entrada */
char *OpenDir(char *pszDiretorio);
char *ObtemLinhaArquivo(char *pszPathArq);
void GravaArqSaida(char *pszPathSaida, char *pszArquivoEntrada, TDadosSaida *ptDadosSaida, int iStatusProcessamento);
void ObtemValoresArqEntrada(char *pszStringIn, TDadosEntrada *ptDadosEntrada);
void MoveArqSaida(char *pszPathEntradaProcessados, char *pszPathEntrada, char *pszArquivoEntrada);

/* atualizacao/obtencao de dados referente ao processamento do conteudo do arquivo */
void AtualizaUsuario(char *pszIdPessoaUsuario, char *pszStatusUsuario, char *pszIdUsuarioAlteracao);
bool ObtemDadosSaida(TDadosSaida *ptDadosSaida);
bool ObtemLoginUsuarioExecutor(char *pszIdPessoaUsuario, char *pszLoginExecutor);
bool ObtemSgStatusUsuario(char *pszIdStatusUsuario, char *pszSgStatusUsuario);

char *ObtemDataHora(void);
char *ObtemNomeArqSaida(char *pszStringIn);
bool ValidaCPF(TDadosSaida *ptDadosSaida);
void TrataFormatoDOS(char *pszBuffer);



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
