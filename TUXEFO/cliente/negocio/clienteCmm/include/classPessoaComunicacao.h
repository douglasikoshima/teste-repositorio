//-----------------------------------------------------------------------------------------
//* 
//* Classe: CPessoaComunicacao
//* 
//* Purpose: Classe para a view PessoaComunicacao
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe sao do tipo Pro*C.
//*
//* Public part:
//*     Metodos:
//*
//* Protegida part :
//*
//* Private part:
//*     
//-----------------------------------------------------------------------------------------
#ifndef CLASSPESSOACOMUNICACO
#define CLASSPESSOACOMUNICACO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdPessoa[LEN_NUMBER+LEN_EOS];
VARCHAR sIdComunicacao[LEN_NUMBER+LEN_EOS];
VARCHAR sIdTipoComunicacao[LEN_NUMBER+LEN_EOS];
VARCHAR sSgTipoComunicacao[LEN_SGTIPOCOMUNICACAO+LEN_EOS];
VARCHAR sDsTipoComunicacao[LEN_DSTIPOCOMUNICACAO+LEN_EOS];
VARCHAR sSgClassificacao[LEN_DSTIPOCOMUNICACAO+LEN_EOS];
VARCHAR sDsContato[LEN_CONTATO+LEN_EOS];
VARCHAR sDtCadastroOut[LEN_DATE_FORMATADA+LEN_EOS];
long    lTsSincronismo;
long    lSqSincronismo;
VARCHAR sNmContato[LEN_CONTATO+LEN_EOS];
VARCHAR sNrSequencia[LEN_NUMBER+LEN_EOS];
VARCHAR sIdSistemaOrigemBase[LEN_NUMBER+LEN_EOS];
VARCHAR sIdSistemaOrigem[LEN_NUMBER+LEN_EOS];

//Nulls
short iIdPessoa_ora;
short iIdComunicacao_ora;
short iIdTipoComunicacao_ora;
short iSgTipoComunicacao_ora;
short iDsTipoComunicacao_ora;
short iSgClassificacao_ora;
short iDsContato_ora;
short iDtCadastroOut_ora;
short iTsSincronismo_ora;
short iSqSincronismo_ora;
short iNmContato_ora;
short iNrSequencia_ora;
short iIdSistemaOrigemBase_ora;
short iIdSistemaOrigem_ora;
}TPESSOACOMUNICACAO;

EXEC SQL END DECLARE SECTION;

class CPessoaComunicacao{

public:
       /* Construtor/Destrutor */
    CPessoaComunicacao();
    virtual ~CPessoaComunicacao();

    int Incluir();
    int Excluir();
    int Alterar();
	int AlterarPorUsuario(); 
	int DeletaPorUsuario();
	int   isGood(); 
    static CPessoaComunicacao *RecuperarTodos(int *, char*);
    int buscaIdSistemaOrigem( char* csgSistemOrigem );

    //Metodos de acessos aos atributos
    //Setters
    void setIdPessoa(char*);
    void setIdComunicacao(char*);
    void setIdSistemaOrigem(char*);
    void setIdSistemaOrigemBase(char*);
    void setIdTipoComunicacao(char*);
    void setSgTipoComunicacao(char*);
    void setDsTipoComunicacao(char*);
    void setDsContato(char*);
    void setDtCadastroOut(char*);
    void setTsSincronismo(long);
    void setSqSincronismo(long);
	void setNmContato(char *);
	void setNrSequencia(char *);
	void setSgClassificacao(char *);
    //Getters
    char* getIdPessoa(void);
    char* getIdComunicacao(void);
    char* getIdSistemaOrigem(void);
    char* getIdSistemaOrigemBase(void);
    char* getIdTipoComunicacao(void);
    char* getSgTipoComunicacao(void);
    char* getDsTipoComunicacao(void);
    char* getDsContato(void);
    char* getDtCadastroOut(void);
    long getTsSincronismo(void);
    long getSqSincronismo(void);
	char* getNmContato(void);
	char* getNrSequencia(void);
	char* getSgClassificacao(void);

	// Usuário
	void setUsuarioAlteracao(char*);
	void SetData(TListaTelefone); 

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TPESSOACOMUNICACAO      tPessCom;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
    EXEC SQL END DECLARE SECTION;

};

#endif

