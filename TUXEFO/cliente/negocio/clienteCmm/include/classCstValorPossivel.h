//-----------------------------------------------------------------------------------------
//* 
//* Classe: CCstValorPossivel
//* 
//* Purpose: Classe para preencher o VO de Documento do VO LupaCliente
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe e´ do tipo Pro*C.
//*
//* Public part:
//*     Metodos:
//*    void setIdValorPossivel(char*);
//*    void setIdAtributo(char*);
//*    void setDsValorPossivel(char*);
//*    void setSqApresentacao(char*);
//*    void setInDisponibilidade(char*);
//*    char* getIdValorPossivel(void);
//*    char* getIdAtributo(void);
//*    char* getDsValorPossivel(void);
//*    char* getSqApresentacao(void);
//*    char* getInDisponibilidade(void);
//*    int Alterar();
//*    int Excluir();
//*    int Incluir();
//*    static CCstValorPossivel *RecuperarTodos(int*);
//*
//* Protegida part :
//*
//* Private part:
//*     Atributos:  TTABELA    tTabela
//-----------------------------------------------------------------------------------------
#ifndef CLASSCSTVALORPOSSIVEL
#define CLASSCSTVALORPOSSIVEL

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdValorPossivel[LEN_NUMBER + LEN_EOS];
VARCHAR sDsValorPossivel[LEN_DSVALORPOSSIVEL + LEN_EOS];
VARCHAR sIdAtributo[LEN_NUMBER + LEN_EOS];
VARCHAR sSqApresentacao[LEN_NUMBER + LEN_EOS];
VARCHAR sInDisponibilidade[LEN_NUMBER + LEN_EOS];

/* Nulls */
short iIdValorPossivelNull;
short iIdAtributoNull;
short iDsValorPossivelNull;
short iSqApresentacaoNull;
short iInDisponibilidadeNull;
}TVALORPOSSIVEL;

EXEC SQL END DECLARE SECTION;

class CCstValorPossivel{
public:
    CCstValorPossivel();
    ~CCstValorPossivel() {};

    void setIdValorPossivel(char*);
    void setIdAtributo(char*);
    void setDsValorPossivel(char*);
    void setSqApresentacao(char*);
    void setInDisponibilidade(char*);
    void setInSelecionado(int);

    char* getIdValorPossivel(void);
    char* getIdAtributo(void);
    char* getDsValorPossivel(void);
    char* getSqApresentacao(void);
    char* getInDisponibilidade(void);
    int getInSelecionado(void);

    int Alterar();
    int Excluir();
    int Incluir();
    static CCstValorPossivel *RecuperarTodos(int*);
    static CCstValorPossivel *RecuperarPorIdConteudo(int*, char*);
	// Usuário
	void setUsuarioAlteracao(char*);


private:
    EXEC SQL BEGIN DECLARE SECTION;
        TVALORPOSSIVEL tTabela;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
    EXEC SQL END DECLARE SECTION;
	int iInSelecionado;
};

#endif
