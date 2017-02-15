#ifndef CLASSCSTSUBASSUNTO
#define CLASSCSTSUBASSUNTO

#include "Global.h"

EXEC SQL INCLUDE "classCanal.h";

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdAtributo[LEN_NUMBER + LEN_EOS];
VARCHAR sDsAtributo[LEN_DSATRIBUTO + LEN_EOS];
VARCHAR sIdSubAssunto[LEN_NUMBER + LEN_EOS];
VARCHAR sIdAssunto[LEN_NUMBER + LEN_EOS];
VARCHAR sNmSubAssunto[LEN_NMSUBASSUNTO + LEN_EOS];
VARCHAR sInDisponibilidade[LEN_NUMBER + LEN_EOS];
VARCHAR sDsAssunto[LEN_DSASSUNTO + LEN_EOS];
VARCHAR sSqApresentacao[LEN_NUMBER + LEN_EOS];
VARCHAR sIdTAPergunta[LEN_NUMBER + LEN_EOS];
VARCHAR sSgTipoApresentacaoPergunta[LEN_SGTIPOAPRESPERGUNTA + LEN_EOS];
VARCHAR sDsTipoApresentacaoPergunta[LEN_DSTIPOAPRESPERGUNTA + LEN_EOS];

/* Nulls */
short iIdAtributoNull;
short iDsAtributoNull;
short iIdSubAssuntoNull;
short iIdAssuntoNull;
short iNmSubAssuntoNull;
short iInDisponibilidadeNull;
short iDsAssuntoNull;
short iSqApresentacaoNull;
short iIdTAPerguntaNull;
short iSgTipoApresentacaoPerguntaNull;
short iDsTipoApresentacaoPerguntaNull;
}TATRIBUTOB01;

EXEC SQL END DECLARE SECTION;

class CCstAtributoB01{

public:
    CCstAtributoB01();
    ~CCstAtributoB01() {};

    void setIdAtributo(char*);
    void setIdSubAssunto(char*);
    void setDsAtributo(char*);
    void setSqApresentacao(char*);
    void setIdTAPergunta(char*);
    void setInDisponibilidade(char*);
    void setIdAssunto(char *);
    void setNmSubAssunto(char*);
    void setDsAssunto(char *);
    void setSgTipoApresentacaoPergunta(char*);
    void setDsTipoApresentacaoPergunta(char* value);
    void setCanais(CCanal *);
    void setQtdCanais(int);

    char* getIdAtributo(void);
    char* getIdSubAssunto(void);
    char* getDsAtributo(void);
    char* getSqApresentacao(void);
    char* getIdTAPergunta(void);
    char* getInDisponibilidade(void);
    char* getIdAssunto(void);
    char* getNmSubAssunto();
    char* getDsAssunto(void);
    char* getSgTipoApresentacaoPergunta();
    char* getDsTipoApresentacaoPergunta();
    CCanal* getCanais(void);
    int getQtdCanais(void);

    static CCstAtributoB01 *RecuperarTodos(int*, char*);
    static CCstAtributoB01 *RecuperarTodosComCanal(int*, char*);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TATRIBUTOB01 tTabela;
    EXEC SQL END DECLARE SECTION;

    CCanal *poCanal;
    int iQtdCanais;
};

#endif
