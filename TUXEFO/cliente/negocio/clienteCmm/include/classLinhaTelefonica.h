//-----------------------------------------------------------------------------------------
//* 
//* Classe: CLinhaTelefonica
//* 
//* Purpose: Classe para trabalhara com a tabela LinhaTelefonica
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe sao do Pro*C
//*
//* Public part:
//*     Metodos:
//*             int Alterar();
//*             void setInDivulgacaoNrLinha(char*);
//*             char* getInDivulgacaoNrLinha(void);
//*
//* Protegida part : nenhuma
//*
//* Private part: estrutura tTabela
//-----------------------------------------------------------------------------------------
#ifndef CLASSLINHATELEFONICA
#define CLASSLINHATELEFONICA

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdLinhaTelefonica[LEN_NUMBER +LEN_EOS];
VARCHAR sInDivulgacaoNrLinha[LEN_NUMBER  +LEN_EOS];
VARCHAR sIdLinhaBase[LEN_IDLINHABASE +LEN_EOS];
VARCHAR sIdLinhaSistemaOrigem[LEN_NUMBER +LEN_EOS];
VARCHAR sIdSistemaOrigem[LEN_NUMBER +LEN_EOS];
VARCHAR sIdTipoLinha[LEN_NUMBER +LEN_EOS];
VARCHAR sDtAutorizacaoDivulgacao[LEN_DATE +LEN_EOS];
VARCHAR sIdEstadoLinha[LEN_NUMBER+LEN_EOS];
VARCHAR sIdUsuarioAlteracao[LEN_NUMBER+LEN_EOS];


/* Nulls */
short iIdLinhaTelefonica_ora;
short iInDivulgacaoNrLinha_ora;
short iIdLinhaBase_ora;
short iIdLinhaSistemaOrigem_ora;
short iIdIdSistemaOrigem_ora;
short iIdTipoLinha_ora;
short iDtAutorizacaoDivulgacao_ora; 
short iIdEstadoLinha_ora;
short sIdUsuarioAlteracao_ora; 
}TLINHATELEFONICA;

EXEC SQL END DECLARE SECTION;

class CLinhaTelefonica{

public:
    CLinhaTelefonica();
    ~CLinhaTelefonica();

	static char* getLinhaSisOrigem(char*, int);
	static char* getIdLinhaSisOrigem(char*);
    static int getIdTipoLinha(char*);
	static char* getIdContaSisOrigem(char*);

	//Metodos de acesso ao BD
    int Alterar();

	//Metodos setter
    void setIdLinhaTelefonica(char*);
    void setInDivulgacaoNrLinha(char*);
	void setIdLinhaBase(char*);

	//Metodos getter
    char* getIdLinhaTelefonica(void);
    char* getInDivulgacaoNrLinha(void);

	// Usuário
	void setUsuarioAlteracao(char*);
	void InsereLinhaTelefonica(); 
	void BuscaPorLinhaBase();

	void setData( TPessoaJuridicaXML xmj);
	void setData( TPessoaFisicaXML xmj);
    void setIdSistemaOrigem(char *pszIdSistemaOrigem);
    void setIdLinhaSistemaOrigem(char *pszIdLinhaSistemaOrigem);
    void setIdEstadoLinha(char *pszIdEstadoLinha);
    void setIdEstadoLinha(void);
	void setIdTipoLinha( char * ); 

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TLINHATELEFONICA    tTabela;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
    EXEC SQL END DECLARE SECTION;
};

#endif

