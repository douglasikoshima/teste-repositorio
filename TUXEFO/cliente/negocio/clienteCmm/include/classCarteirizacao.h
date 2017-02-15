//-----------------------------------------------------------------------------------------
//* 
//* Classe: CCarteirizacao
//* 
//* Purpose: 
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe e´ do tipo Pro*C.
//*
//* Public part:
//*     Metodos:
//*
//* Protegida part :
//*
//* Private part:
//-----------------------------------------------------------------------------------------
#ifndef CLASSCARTEIRIZACAO
#define CLASSCARTEIRIZACAO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sNome[LEN_NOME + LEN_EOS];
VARCHAR sTelefone[LEN_CONTATO + LEN_EOS];
VARCHAR sCelular[LEN_CONTATO + LEN_EOS];
VARCHAR sEmail[LEN_CONTATO + LEN_EOS];
VARCHAR sIdTipoComunicacao[LEN_NUMBER + LEN_EOS];
VARCHAR sIdTipoRelacionamento[LEN_NUMBER + LEN_EOS];
VARCHAR sSgTipoRelacionamento[LEN_SGTIPORELACIONAMENTO + LEN_EOS];
VARCHAR sDsTipoRelacionamento[LEN_DSTIPORELACIONAMENTO + LEN_EOS];

/* Nulls */
short iNome_ora;
short iTelefone_ora;
short iCelular_ora;
short iEmail_ora;
short iIdTipoComunicacao_ora;
short iIdTipoRelacionamento_ora;
short iSgTipoRelacionamento_ora;
short iDsTipoRelacionamento_ora;
}TCARTEIRAZACAO;

EXEC SQL END DECLARE SECTION;

class CCarteirizacao{

public:
    CCarteirizacao();
    ~CCarteirizacao();

	void setNome(char*);
	void setTelefone(char*);
	void setCelular(char*);
	void setEmail(char*);
	void setIdTipoComunicacao(char*);
	void setIdTipoRelacionamento(char*);
	void setSgTipoRelacionamento(char*);
	void setDsTipoRelacionamento(char*);

	char* getNome(void);
	char* getTelefone(void);
	char* getCelular(void);
	char* getEmail(void);
	char* getIdTipoComunicacao(void);
	char* getIdTipoRelacionamento(void);
	char* getSgTipoRelacionamento(void);
	char* getDsTipoRelacionamento(void);

    static CCarteirizacao *RecuperaPorIdPessoa(int*, char*);
    int RecuperaPorIdPessoaUsuario(char* pID);
    int obterQtdeLinhas(char* pID);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TCARTEIRAZACAO    tTabela;
    EXEC SQL END DECLARE SECTION;
};

#endif

