//-----------------------------------------------------------------------------------------
//* 
//* Classe: CPessoaCanalPreferido
//* 
//* Purpose: Classe para realizar operacoes na tabela PessoaCanalPreferido
//*
//* Relacao com outra(s) classe(s): nenhuma
//*
//* Instrucoes de uso: Os atributos desta classe e´ do tipo Pro*C.
//*
//* Public part:
//*     Metodos:
//*             void setIdPessoaCanalPreferido(char *);
//*             void setIdPessoa(char *);
//*             void setIdCanal(char*);
//*             char *getIdPessoaCanalPreferido(void);
//*             char *getIdPessoa(void);
//*             char *getIdCanal(void);
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

#ifndef CLASSPESSOACANALPREFERIDO
#define CLASSPESSOACANALPREFERIDO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdPessoaCanalPreferido[LEN_NUMBER + LEN_EOS];
VARCHAR sIdPessoa[LEN_NUMBER + LEN_EOS];
VARCHAR sIdCanal[LEN_NUMBER + LEN_EOS];
VARCHAR sDescricao[LEN_NMSIGLA + LEN_EOS];

/* Nulls */
short iIdPessoaCanalPreferido_ora;
short iIdPessoa_ora;
short iIdCanal_ora;
short   iDescricao_ora;
}TTABELA_PCP;

EXEC SQL END DECLARE SECTION;

class CPessoaCanalPreferido
{
public:
    CPessoaCanalPreferido();
    ~CPessoaCanalPreferido();

    void setIdPessoaCanalPreferido(char *);
    void setIdPessoa(char *);
    void setIdCanal(char*);
    void setDescricao(char*);

    char *getIdPessoaCanalPreferido(void);
    char *getIdPessoa(void);
    char *getIdCanal(void);
    char *getDescricao(void);

    int Incluir();
    int Alterar();
    int Excluir();
    int ExcluirPorPessoa(char* sDado);
    static CPessoaCanalPreferido *RecuperarTodos(int *);
    static CPessoaCanalPreferido *RecuperarPorIdPessoa(int *, char *);

	// Usuário
	void setUsuarioAlteracao(char*);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TTABELA_PCP    tTabela;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
    EXEC SQL END DECLARE SECTION;
};

#endif
