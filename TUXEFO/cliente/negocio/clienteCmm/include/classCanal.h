#ifndef CLASSCANAL
#define CLASSCANAL

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sId[LEN_NUMBER + LEN_EOS];
VARCHAR sSigla[LEN_CDSIGLA + LEN_EOS];
VARCHAR sDescricao[LEN_NMSIGLA + LEN_EOS];

/* Nulls */
short   iId_ora;
short   iSigla_ora;
short   iDescricao_ora;
}TAPOIO;

EXEC SQL END DECLARE SECTION;

class CCanal{

public:
    CCanal();
    ~CCanal();

    void setId(char *);
    void setSigla(char *);
    void setDescricao(char*);

    char *getId(void);
    char *getSigla(void);
    char *getDescricao(void);

    int Incluir();
    int Alterar();
    int Excluir();
    static CCanal *RecuperarTodos(int *);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TAPOIO    tTabela;
    EXEC SQL END DECLARE SECTION;
};

#endif

