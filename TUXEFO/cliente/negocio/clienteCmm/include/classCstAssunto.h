#ifndef CLASSCSTASSUNTO
#define CLASSCSTASSUNTO

#include "Global.h"

EXEC SQL INCLUDE "classCstSubAssunto.h";

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdAssunto[LEN_NUMBER + LEN_EOS];
VARCHAR sDsAssunto[LEN_DSASSUNTO + LEN_EOS];
VARCHAR sInDisponibilidade[LEN_NUMBER + LEN_EOS];

/* Nulls */
short   iIdAssuntoNull;
short   iDsAssuntoNull;
short   iInDisponibilidadeNull;
}TASSUNTO;

EXEC SQL END DECLARE SECTION;

class CCstAssunto{

public:
    CCstAssunto();
    ~CCstAssunto(){if(poSubAssuntos) free(poSubAssuntos);};
    void setIdAssunto(char *);
    void setDsAssunto(char *);
    void setInDisponibilidade(char*);
	void setSubAssuntos(CCstSubAssunto*);
	void setNrSubAssuntos(int);
    char *getIdAssunto(void);
    char *getDsAssunto(void);
    char *getInDisponibilidade();
	CCstSubAssunto* getSubAssuntos();
	int getNrSubAssuntos();

    int Incluir();
    int Alterar();
    int Excluir();
    static CCstAssunto *RecuperarTodos(int *);
    static CCstAssunto *RecuperarTodosComSubAssunto(int *, char *);
    static CCstAssunto *ListarPorTipoApresentacao(int*, char*);
	// Usuário
	void setUsuarioAlteracao(char*);


private:
    EXEC SQL BEGIN DECLARE SECTION;
        TASSUNTO    tAss;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
    EXEC SQL END DECLARE SECTION;
	CCstSubAssunto* poSubAssuntos;
	int iNrSubAssuntos;
};


#endif

