//-----------------------------------------------------------------------------------------
//* 
//* Classe: CStatusUsuario
//* 
//* Purpose: Classe para a tabela de apoio StatusLInha
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe sao do char*.
//*
//* Public part:
//*     Metodos:
//*    void setId(char *);
//*    void setSigla(char *);
//*    void setDescricao(char*);
//*    void setDisponibilidade(char*);
//*    char *getId(void);
//*    char *getSigla(void);
//*    char *getDescricao(void);
//*    char *getDisponibilidade(void);
//*    int Incluir();
//*    int Alterar();
//*    int Excluir();
//*    static CStatusUsuario *RecuperarTodos(int *);
//*
//* Protegida part : nenhuma
//*
//* Private part:
//*             char sNrCodArea[LEN_NUMBER + LEN_EOS];
//*             char sNrLinha[LEN_NUMBER + LEN_EOS];
//-----------------------------------------------------------------------------------------
#ifndef CLASSSTATUSUSUARIO
#define CLASSSTATUSUSUARIO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sId[LEN_NUMBER + LEN_EOS];
VARCHAR sSigla[LEN_CDSIGLA + LEN_EOS];
VARCHAR sDescricao[LEN_NMSIGLA + LEN_EOS];
VARCHAR sDisponibilidade[LEN_NUMBER + LEN_EOS];

/* Nulls */
short   iId_ora;
short   iSigla_ora;
short   iDescricao_ora;
short   iDisponibilidade_ora;
}TAPOIO_SU;

EXEC SQL END DECLARE SECTION;

class CStatusUsuario{
public:
    CStatusUsuario();
    ~CStatusUsuario();

    void setId(char *);
    void setSigla(char *);
    void setDescricao(char*);
    void setDisponibilidade(char*);

    char *getId(void);
    char *getSigla(void);
    char *getDescricao(void);
    char *getDisponibilidade(void);

    int Incluir();
    int Alterar();
    int Excluir();
    static CStatusUsuario *RecuperarTodos(int *);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TAPOIO_SU    tTabela;
    EXEC SQL END DECLARE SECTION;
};

#endif
