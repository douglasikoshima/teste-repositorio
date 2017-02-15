//-----------------------------------------------------------------------------------------
//* 
//* Classe: CPessoaRecusaMeioContato
//* 
//* Purpose: Classe para realizar operacoes na tabela PessoaRecusaMeioContato
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe e´ do tipo Pro*C.
//*
//* Public part:
//*     Metodos:
//*             void setIdPessoaMeioContato(char *);
//*             void setIdPessoa(char *);
//*             void setIdMeioContato(char*);
//*             char *getIdPessoaMeioContato(void);
//*             char *getIdPessoa(void);
//*             char *getIdMeioContato(void);
//*              int Incluir();
//*              int Alterar();
//*              int Excluir();
//*           static CPessoaCanalPreferido *RecuperarTodos(int *);
//*          static CPessoaCanalPreferido *ExcluirPorPessoa(char* sDado);
//*
//* Protegida part :
//*
//* Private part:
//*     Atributos:  TTABELA    tTabela
//-----------------------------------------------------------------------------------------

#ifndef CLASSPESSOARECUSAMEIOCONTATO
#define CLASSPESSOARECUSAMEIOCONTATO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdPessoaRecusaMeioContato[LEN_NUMBER + LEN_EOS];
VARCHAR sIdPessoa[LEN_NUMBER + LEN_EOS];
VARCHAR sIdMeioContato[LEN_NUMBER + LEN_EOS];
VARCHAR sDescricao[LEN_NMMEIOCONTATO + LEN_EOS];

/* Nulls */
short iIdPessoaRecusaMeioContato_ora;
short iIdPessoa_ora;
short iIdMeioContato_ora;
short   iDescricao_ora;
}TTABELA;

EXEC SQL END DECLARE SECTION;

class CPessoaRecusaMeioContato{
public:
    CPessoaRecusaMeioContato();
    ~CPessoaRecusaMeioContato();

    void setIdPessoaRecusaMeioContato(char *);
    void setIdPessoa(char *);
    void setIdMeioContato(char*);
    void setDescricao(char*);

    char *getIdPessoaRecusaMeioContato(void);
    char *getIdPessoa(void);
    char *getIdMeioContato(void);
    char *getDescricao(void);

    int Incluir();
    int Alterar();
    int Excluir();
    int ExcluirPorPessoa(char* sDado);
    static CPessoaRecusaMeioContato *RecuperarTodos(int *);
    static CPessoaRecusaMeioContato *RecuperarPorIdPessoa(int *, char*pParam);

	// Usuário
	void setUsuarioAlteracao(char*);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TTABELA    tTabela;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
    EXEC SQL END DECLARE SECTION;
};

#endif
