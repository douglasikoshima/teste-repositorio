#ifndef CLASSMEIOCONTATO
#define CLASSMEIOCONTATO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sId[LEN_NUMBER + LEN_EOS];
VARCHAR sSigla[LEN_CDMEIOCONTATO + LEN_EOS];
VARCHAR sDescricao[LEN_NMMEIOCONTATO + LEN_EOS];

/* Nulls */
short   iId_ora;
short   iSigla_ora;
short   iDescricao_ora;
}TAPOIO;

EXEC SQL END DECLARE SECTION;

class CMeioContato{

public:
    CMeioContato();
    ~CMeioContato();

    void setId(char *);
    void setSigla(char *);
    void setDescricao(char*);

    char *getId(void);
    char *getSigla(void);
    char *getDescricao(void);

    int Incluir();
    int Alterar();
    int Excluir();
    static CMeioContato *RecuperarTodos(int *);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TAPOIO    tTabela;
    EXEC SQL END DECLARE SECTION;
};

#endif
