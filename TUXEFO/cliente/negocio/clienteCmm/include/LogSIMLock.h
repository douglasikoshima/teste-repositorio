#ifndef LOGSIMLOCKH
#define LOGSIMLOCKH

#include <tuxfw.h>
#include "Global.h"

typedef struct {
    char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szNrLinha[LEN_NRLINHA + LEN_EOS];
    char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
} TDadosPesq1;

typedef struct {
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szIdTipoDocumento[LEN_IDTIPODOCUMENTO + LEN_EOS];
    char szNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
} TDadosPesq2;


typedef struct {
    char szIdGrupo[LEN_IDGRUPO + LEN_EOS];
    char szIdProcedencia[LEN_IDPROCEDENCIA + LEN_EOS];
} TDadosPesq3;

typedef struct {
    char szIdGrupo[LEN_IDGRUPO + LEN_EOS];
    char szIdCanal[LEN_IDCANAL + LEN_EOS];
} TDadosPesq4;

typedef struct {
    char szIdContato[LEN_IDCONTATO + LEN_EOS];
} TDadosPesq5;

#define QTD_ELEMENTOS_SIMLOCK                   30  /* quantidade de elementos alocados inicialmente */

/* identificadores das querys dinamicas */
#define ID_QUERY_OBRIGATORIA_PERIODO            1
#define ID_QUERY_OBRIGATORIA_IMEI               2
#define ID_QUERY_OBRIGATORIA_LOGIN              3

#define ID_QUERY_OPCIONAL_LINHA                 1
#define ID_QUERY_OPCIONAL_DOCUMENTO             2


#define LEN_VALOR_OBRIGATORIO                   50
#define LEN_VALOR_OPCIONAL                      50

typedef struct {
    char szDtUltimaAlteracao[LEN_DATA_HORA + LEN_EOS];
    char szIMEI[LEN_IMEI + LEN_EOS];
    char szNmLoginUsuario[LEN_NMLOGINUSUARIO + LEN_EOS];
    char szIP[LEN_IP + LEN_EOS];
    char szEstadoConsulta[LEN_ESTADOCONSULTA + LEN_EOS];
    char szNrLinha[LEN_NRLINHA + LEN_EOS];
    char szNmTipoRelacionamento[LEN_NMTIPORELACIONAMENTO + LEN_EOS];
    char szSgTipoDocumento[LEN_SGTIPODOCUMENTO + LEN_EOS];
    char szNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
    char szNmPessoa[LEN_NMPESSOA + LEN_EOS];
} TDadosRelatorio;


class LogSIMLock
{
public:
    TLogSIMLock tLogSIMLock;

    LogSIMLock(void);
    ~LogSIMLock(void);

    void clearLogSIMLock(void);

    void setIP(char *pszIP);
    void setIMEI(char *pszIMEI);
    void setEstadoConsulta(char *pszEstadoConsulta);
    void setIdTipoDocumento(char *pszIdTipoDocumento);
    void setNrDocumento(char *pszNrDocumento);
    void setIdPessoaLinhaHistorico(char *pszIdPessoaLinhaHistorico);
    void setIdPessoa(char *pszIdPessoa);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    void insereLogSIMLock(void);
    bool buscaPLHLogSIMLock(TDadosPesq1 *ptDadosPesq1);
    bool buscaPDLogSIMLock(TDadosPesq2 *ptDadosPesq2);
    char *obtemParametro(char *pCdParametro);
    bool buscaProcedenciaLogSIMLock(TDadosPesq3 *ptDadosPesq3);
    bool buscaCanalLogSIMLock(TDadosPesq4 *ptDadosPesq4);
    bool buscaContatoLogSIMLock(TDadosPesq5 *ptDadosPesq5);

    // controle de paginacao
    void setRegIni(int iRegIni);
    void setRegFim(int iRegFim);

    TDadosRelatorio *buscaLogSIMLockDinamica(void);
    int buscaQtdLogSIMLockPcDinamica(void);

    // Identificador de query dinamica
    void setIdQueryObrigatoria(int iIdQueryOBrigatoria);
    void setIdQueryOpcional(int iIdQueryOpcional);

    void setValorQueryObrigatoria(char *pszValorQueryObrigatoria);
    void setValorQueryOpcional(char *pszValorQueryOpcional);


protected:
    int m_iIdQueryObrigatorio;
    int m_iIdQueryOpcional;

    char m_szValorQueryObrigatoria[LEN_VALOR_OBRIGATORIO + LEN_EOS];
    char m_szValorQueryOpcional[LEN_VALOR_OPCIONAL + LEN_EOS];

    TDadosRelatorio m_tDadosRelatorio;
    TDadosRelatorio *m_ptDadosRelatorio;

    // controle de alocacao de memoria
    int m_iQtdElementosAlocados;
    int m_iQtdElementosObtidos;
    void controleAlocacao(void);

    // controle de paginacao
    int m_iRegIni;
    int m_iRegFim;
    int getRegIni(void);
    int getRegFim(void);

    void buscaLogSIMLockPcDinamica(void);

    // Identificador de query dinamica
    int getIdQueryObrigatoria(void);
    int getIdQueryOpcional(void);

    char *getValorQueryObrigatoria(void);
    char *getValorQueryOpcional(void);
};
#endif
