//-----------------------------------------------------------------------------------------
//* 
//* Classe: CEstadoLinha
//* 
//* Purpose: Classe para a tabela de apoio EstadoLinha
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
//*    void setIdSisOrig(char*);
//*    char *getId(void);
//*    char *getSigla(void);
//*    char *getDescricao(void);
//*    char *getIdSisOrig(void);
//*    int Incluir();
//*    int Alterar();
//*    int Excluir();
//*    static CEstadoLinha *RecuperarTodos(int *);
//*
//* Protegida part : nenhuma
//*
//* Private part:
//*             tTabela
//-----------------------------------------------------------------------------------------
#ifndef CLASSESTADOLINHA
#define CLASSESTADOLINHA

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sId[LEN_NUMBER + LEN_EOS];
VARCHAR sIdSisOrig[LEN_NUMBER + LEN_EOS];
VARCHAR sSigla[LEN_CDSIGLA + LEN_EOS];
VARCHAR sDescricao[LEN_NMSIGLA + LEN_EOS];

/* Nulls */
short   iId_ora;
short   iIdSisOrig_ora;
short   iSigla_ora;
short   iDescricao_ora;
}TAPOIO_EL;

EXEC SQL END DECLARE SECTION;

class CEstadoLinha{
public:
    CEstadoLinha();
    ~CEstadoLinha();

    void setId(char *);
    void setSigla(char *);
    void setDescricao(char*);
    void setIdSisOrig(char*);

    char *getId(void);
    char *getSigla(void);
    char *getDescricao(void);
    char *getIdSisOrig(void);

    int Incluir();
    int Alterar();
    int Excluir();
    // ==> Incidência 3114 no TD de pré-produção. Correção de valores duplicados na lupaLinha - Cassio - Jan/2006
    // static CEstadoLinha *RecuperarTodos(int *);
    static CEstadoLinha *RecuperarTodos(int*,int);
    // <== Incidência 3114 no TD de pré-produção. Correção de valores duplicados na lupaLinha - Cassio - Jan/2006

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TAPOIO_EL    tTabela;
    EXEC SQL END DECLARE SECTION;
};

#endif
