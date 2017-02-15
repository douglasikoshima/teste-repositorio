//-----------------------------------------------------------------------------------------
//* 
//* Classe: CPessoaLinha
//* 
//* Purpose: Classe para a tabela PessoLinha
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe sao do VARCHAR.
//*
//* Public part:
//*     Metodos:
//*
//* Protegida part : nenhuma
//*
//* Private part:
//*             tTabelab01
//-----------------------------------------------------------------------------------------

#ifndef CLASSPESSOALINHAH
#define CLASSPESSOALINHAH

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

/* Nao possui todos os campos da VIEW B01 */
typedef struct{
VARCHAR sIdPessoa[LEN_NUMBER +LEN_EOS];
VARCHAR sIdPessoaDePara[LEN_NUMBER +LEN_EOS];
VARCHAR sNmPessoa[LEN_NOME +LEN_EOS];
VARCHAR sIdLinhaTelefonica[LEN_NUMBER +LEN_EOS];
VARCHAR sCdAreaRegistro[LEN_CDAREAREGISTRO +LEN_EOS];
VARCHAR sNrLinha[LEN_NRLINHA + LEN_CDAREAREGISTRO + LEN_EOS];
VARCHAR sDsTipoLinha[LEN_DSTIPOLINHA +LEN_EOS];
VARCHAR sDsEstadoLinha[LEN_DSESTADOLINHA+LEN_DSESTADOLINHA+LEN_EOS];
VARCHAR sDtHabilitacaoOut[LEN_DATE_FORMATADA +LEN_EOS];
VARCHAR sInDivulgacaoNrLinha[LEN_NUMBER  +LEN_EOS];
VARCHAR sDtChurn[LEN_DATE_FORMATADA+LEN_EOS];
VARCHAR sDsTipoCarteira[LEN_DSTIPOCARTEIRA+LEN_EOS];
VARCHAR sIdTipoCarteira[LEN_NUMBER+LEN_EOS];
VARCHAR sSgTipoCarteira[LEN_SGTIPOCARTEIRA+LEN_EOS];
VARCHAR sNrTelefone[LEN_CONTATO + LEN_EOS];
VARCHAR sDsTipoPessoa[LEN_DSTIPOPESSOA + LEN_EOS];
VARCHAR sSgTipoPessoa[LEN_SGTIPOPESSOA + LEN_EOS];
VARCHAR sIdTipoRelacionamento[LEN_NUMBER + LEN_EOS ]; 
VARCHAR sDddLinhaFormatada[255]; /* (xx)xxxx-xxxx */
VARCHAR sIdTipoLinha[LEN_NUMBER + LEN_EOS ];
VARCHAR sNmServico[LEN_NMSERVICO+LEN_EOS];
VARCHAR dsModalidadeCartaoItau[6+LEN_EOS];
int     nUltimaPagina;

/* Nulls */
short iIdPessoa_ora;
short iIdPessoaDePara_ora;
short iNmPessoa_ora;
short iIdLinhaTelefonica_ora;
short iCdAreaRegistro_ora;
short iNrLinha_ora;
short iDsTipoLinha_ora;
short iDsEstadoLinha_ora;
short iDtHabilitacaoOut_ora;
short iInDivulgacaoNrLinha_ora;
short iDtChurn_ora;
short iDsTipoCarteira_ora;
short iIdTipoCarteira_ora;
short iSgTipoCarteira_ora;
short iNrTelefone_ora;
short iDsTipoPessoa_ora;
short iSgTipoPessoa_ora;
short iIdTipoRelacionamento_ora;
short iIdTipoLinha_ora;
short iNmServico_ora;
short idsModalidadeCartaoItau_ora;
short iUltimaPagina;

}TTABELA_01;
EXEC SQL END DECLARE SECTION;

class CPessoaLinha{
public:
    CPessoaLinha();
    ~CPessoaLinha();

    static CPessoaLinha *RecuperarTodosB01(int *, char*, char*, char*);
    static CPessoaLinha *buscarPorIdPessoa(int *, char*);
    static CPessoaLinha *buscarPorNrConta(int *, char*, char*);
	static CPessoaLinha *buscarPorNrLinha(int*,char*);

    bool obterNomeGestorConta();

	void InserePessoaLinha();
	void TrocaPessoaLinha();


    int  buscarDadosClientePorIdLinhaTelefonica(char* pidLinhaTelefonica);
    int  buscarDadosProspectPorIdLinhaTelefonica(char* pidLinhaTelefonica);


    int  buscarDadosClientePorNrLinha(char*);
    int  buscarDadosProspectPorNrLinha(char* pNrLin);
    int  buscarDadosClienteTIPorNrLinha(char*);
	int  buscarDadosLinhaTIPorNrLinha(char*);
    int  obterQtdDeLinhaAtivaPorIdPessoa(char* pId);
    int  obterQtdDeLinhaPorIdPessoa(char* pId);
    int  buscarDadosCartaoVivoItau(char* pNrLin);
    // bool IsClienteSaltador( char * pNrLinha );

	int  obterDadosPortabilidade(char*cNrlinha,char *ctpPortada,char *cdsPortada);

	void RemoveAll();
	int  obterQtdReg();
	int  obterQtdRegUsuario(); 
	int  obterQtdEstadoLinha(char*, char *);
	int  obterQtdEstadoLinhaCarregaTI(char*, char *);
    int  obterQtdTipoLinha(char*, int);
    int  obterQtdTipoLinhaCarregaTI(char*);

    void setIdPessoa(char*);
    void setIdTipoLinha(char*);
    void setIdPessoaDePara(char*);
    void setNmPessoa(char*);
    void setIdLinhaTelefonica(char*);
    void setCdAreaRegistro(char*);
    void setNrLinha(char*);
    void setDsTipoLinha(char*);
    void setDsEstadoLinha(char*);
    void setDtHabilitacaoOut(char*);
    void setInDivulgacaoNrLinha(char*);
    void setDtChurn(char*);
    void setDsTipoCarteira(char*);
    void setIdTipoCarteira(char*);
    void setSgTipoCarteira(char*);
    void setNrTelefone(char*);
    void setDsTipoPessoa(char*);
    void setSgTipoPessoa(char*);
	void setIdTipoRelacionamento(char *); 
	void TrocaUsuario();
	void setNmServico(char*);
	void setUltimaPagina(int);
	int	 getBlindagem(char*);

    char* getIdPessoa(void);
    char* getIdTipoLinha(void);
	char* getIdPessoaDePara(void);
    char* getIdLinhaTelefonica(void);
    char* getNmPessoa(void);
    char* getCdAreaRegistro(void);
    char* getNrLinha(void);
    char* getDsTipoLinha(void);
    char* getDsEstadoLinha(void);
    char* getDtHabilitacaoOut(void);
    char* getInDivulgacaoNrLinha(void);
    char* getDtChurn(void);
    char* getDsTipoCarteira(void);
    char* getIdTipoCarteira(void);
    char* getSgTipoCarteira(void);
    char* getNrTelefone(void);
    char* getDsTipoPessoa(void);
    char* getSgTipoPessoa(void);
    char* getDddLinhaFormatada(void);
	char* getNmServico(void);
    char* getDsModalidadeCartaoItau(void);

	int   getUltimaPagina(void);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TTABELA_01    tTabelab01;
    EXEC SQL END DECLARE SECTION;
};
#endif

