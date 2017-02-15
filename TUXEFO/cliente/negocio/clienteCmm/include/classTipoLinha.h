//-----------------------------------------------------------------------------------------
//* 
//* Classe: CTipoLinha
//* 
//* Purpose: Classe para a tabela de apoio TipoLinha
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
//*    void setPeso(char*);
//*    char *getId(void);
//*    char *getSigla(void);
//*    char *getDescricao(void);
//*    char *getPeso(void);
//*    int Incluir();
//*    int Alterar();
//*    int Excluir();
//*    static CTipoLinha *RecuperarTodos(int *);
//*
//* Protegida part : nenhuma
//*
//* Private part:
//*             tTabela
//-----------------------------------------------------------------------------------------
#ifndef CLASSTIPOLINHA
#define CLASSTIPOLINHA

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sId[LEN_NUMBER + LEN_EOS];
VARCHAR sSigla[LEN_CDSIGLA + LEN_EOS];
VARCHAR sDescricao[LEN_NMSIGLA + LEN_EOS];
VARCHAR sPeso[LEN_NUMBER + LEN_EOS];

/* Nulls */
short   iId_ora;
short   iSigla_ora;
short   iDescricao_ora;
short   iPeso_ora;
}TAPOIO_TL;

EXEC SQL END DECLARE SECTION;

class CTipoLinha{

public:
    CTipoLinha();
    ~CTipoLinha();

    void setId(char *);
    void setSigla(char *);
    void setDescricao(char*);
    void setPeso(char*);

    char *getId(void);
    char *getSigla(void);
    char *getDescricao(void);
    char *getPeso(void);

    int Incluir();
    int Alterar();
    int Excluir();
    static CTipoLinha *RecuperarTodos(int *);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TAPOIO_TL    tTabela;
    EXEC SQL END DECLARE SECTION;
};

#endif
