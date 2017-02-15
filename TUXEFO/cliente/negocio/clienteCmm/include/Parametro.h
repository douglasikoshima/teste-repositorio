//-----------------------------------------------------------------------------------------
//* 
//* Classe: CParametro
//* 
//* Purpose: Classe para carregar os parametros utilizados na carga da Tela Inicial
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe sao do char*.
//*
//* Public part:
//*     Metodos:
//*
//* Protegida part : nenhuma
//*
//* Private part:
//-----------------------------------------------------------------------------------------
#ifndef PARAMETRO
#define PARAMETRO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdCliente[LEN_NUMBER +LEN_EOS];
VARCHAR sIdUsuario[LEN_NUMBER +LEN_EOS];
VARCHAR sIdClienteDePara[LEN_NUMBER +LEN_EOS];
VARCHAR sIdUsuarioDePara[LEN_NUMBER +LEN_EOS];
VARCHAR sIdLinhaTelefonica[LEN_NUMBER +LEN_EOS];
VARCHAR sIdTipoLinha[LEN_NUMBER +LEN_EOS];
VARCHAR sNrLinha[LEN_NRLINHA +LEN_EOS];
VARCHAR sCdAreaRegistro[LEN_CDAREAREGISTRO +LEN_EOS];
VARCHAR sIdConta[LEN_NUMBER +LEN_EOS];
VARCHAR sCdConta[LEN_NRCONTA +LEN_EOS];
VARCHAR sIdLinhaSistemaOrigem[LEN_NUMBER +LEN_EOS];
VARCHAR sIdContaSistemaOrigem[LEN_NUMBER +LEN_EOS];
VARCHAR sNmPessoa[LEN_NMPESSOA +LEN_EOS];
VARCHAR sNmContato[LEN_NMPESSOA +LEN_EOS];
VARCHAR sIdTipoPessoa[LEN_NUMBER +LEN_EOS];
VARCHAR sSgTipoCarteira[LEN_SGTIPOCARTEIRA +LEN_EOS];
VARCHAR sDsTipoCarteira[LEN_DSTIPOCARTEIRA +LEN_EOS];
VARCHAR sSgTipoPessoa[LEN_SGTIPOPESSOA +LEN_EOS];
VARCHAR sDsTipoPessoa[LEN_DSTIPOPESSOA +LEN_EOS];
VARCHAR sIdSegmentacao[LEN_NUMBER +LEN_EOS];
VARCHAR sDsSegmentacao[LEN_SEGMENTACAO +LEN_EOS];
VARCHAR sIdTipoCarteira[LEN_NUMBER +LEN_EOS];
VARCHAR sIdTipoRelacionamento[LEN_NUMBER+LEN_EOS];
VARCHAR sSgTipoRelacionamento[LEN_SGTIPORELACIONAMENTO +LEN_EOS];
VARCHAR sNmTipoRelacionamento[LEN_NMTIPORELACIONAMENTO +LEN_EOS];
VARCHAR sIdPessoaLinhaHistorico[LEN_NUMBER +LEN_EOS];
VARCHAR sTipoLinha[LEN_DSTIPOLINHA + LEN_EOS];
VARCHAR sEstadoLinha[LEN_DSESTADOLINHA + LEN_DSESTADOLINHA + LEN_EOS];
VARCHAR sDddLinhaFormatada[255]; /* (xx)xxxx-xxxx */

VARCHAR sIdUfOperadora[LEN_NUMBER+LEN_EOS];
VARCHAR sInCorporativo[LEN_NUMBER+LEN_EOS];

int iInCorrespDevolvida;

/* NULLs */
short iIdUsuario_ora;
short iIdUsuarioDePara_ora;
short iIdLinhaTelefonica_ora;
short iIdTipoLinha_ora;
short iNrLinha_ora;
short iCdAreaRegistro_ora;
short iIdConta_ora;
short iCdConta_ora;
short iIdLinhaSistemaOrigem_ora;
short iIdContaSistemaOrigem_ora;
short iNmPessoa_ora;
short iIdTipoPessoa_ora;
short iSgTipoCarteira_ora;
short iDsTipoCarteira_ora;
short iSgTipoPessoa_ora;
short iDsTipoPessoa_ora;
short iIdSegmentacao_ora;
short iDsSegmentacao_ora;
short iIdTipoCarteira_ora;
short iIdTipoRelacionamento_ora;
short iSgTipoRelacionamento_ora;
short iNmTipoRelacionamento_ora;
short iIdPessoaLinhaHistorico_ora;
short iTipoLinha_ora;
short iEstadoLinha_ora;
short iDddLinhaFormatada_ora;

short iIdUfOperadora_ora;
short iInCorporativo_ora;

}TCAMPOS;

EXEC SQL END DECLARE SECTION;

class CParametro{
public:
    CParametro();
    ~CParametro();

    void setIdCliente(char *);
    void setIdUsuario(char *);
    void setIdClienteDePara(char *);
    void setIdUsuarioDePara(char *);
    void setIdLinhaTelefonica(char *);
    void setIdTipoLinha(char *);
    void setNrLinha(char *);
    void setCdAreaRegistro(char *);
    void setIdConta(char *);
    void setCdConta(char *);
    void setIdLinhaSistemaOrigem(char *);
    void setIdContaSistemaOrigem(char *);
    void setNmPessoa(char *);
    void setNmContato(char *);
    void setIdTipoPessoa(char *);
    void setSgTipoCarteira(char *);
    void setDsTipoCarteira(char *);
    void setSgTipoPessoa(char *);
    void setDsTipoPessoa(char *);
    void setIdSegmentacao(char *);
    void setDsSegmentacao(char *);
    void setIdTipoCarteira(char *);
    void setSgTipoRelacionamento(char *);
    void setNmTipoRelacionamento(char *);
    void setIdPessoaLinhaHistorico(char *);
    void setInCorrespDevolvida(int);
    void setIdUfOperador(char*);
    void setInCorporativo(char*);

    char *getIdCliente();
    char *getIdUsuario();
    char *getIdClienteDePara();
    char *getIdUsuarioDePara();
    char *getIdLinhaTelefonica();
    char *getIdTipoLinha();
    char *getNrLinha();
    char *getCdAreaRegistro();
    char *getIdConta();
    char *getCdConta();
    char *getIdLinhaSistemaOrigem();
    char *getIdContaSistemaOrigem();
    char *getNmPessoa();
    char *getNmContato();
    char *getIdTipoPessoa();
    char *getSgTipoCarteira();
    char *getDsTipoCarteira();
    char *getSgTipoPessoa();
    char *getDsTipoPessoa();
    char *getIdSegmentacao();
    char *getDsSegmentacao();
    char *getIdTipoCarteira();
    char *getSgTipoRelacionamento();
    char *getNmTipoRelacionamento();
    char *getIdPessoaLinhaHistorico();
    char *getTipoLinha();
    char *getEstadoLinha();
    char *getDddLinhaFormatada();
    char *getIdUfOperador();
    char *getInCorporativo();
    char *getIdTipoRelacionamento();

    int  getInCorrespDevolvida();

    int Recuperar(char*, int);
    int RecuperarPorIdLinhaTelefonica(char* pIdLinhaTelefonica, char* pIdTipoRelacionamento);
    int RecuperarPorIdLinTelefETpRelac(char* pIdLin, char *pTpRel);
    bool buscaLegado(char* pszIdUfOperadora, char *pszIdSistemaOrigem);
    bool buscaIdSistemaOrigem(char *pszNrLinha, char *pszCdAreaRegistro, char *pszIdSistemaOrigem);
    bool buscaParametro(char *pszCdParametro, TApoioParametro *ptApoioParametro);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TCAMPOS		tTabela;
    EXEC SQL END DECLARE SECTION;
};

#endif
