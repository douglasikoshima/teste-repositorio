//-----------------------------------------------------------------------------------------
//* 
//* Classe: CCep
//* 
//* Purpose: Classe para a view CEP
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe sao do char*.
//*
//* Public part:
//*     Metodos:
//*
//* Protegida part : nenhuma
//*
//* Private part:
//-----------------------------------------------------------------------------------------#ifndef CLASSCANAL
#ifndef CLASSCEP
#define CLASSCEP

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sCodCep[LEN_NUMBER+LEN_EOS];
VARCHAR sCodLocalidade[LEN_NUMBER+LEN_EOS];
VARCHAR sNumCep[LEN_CEP+LEN_EOS];

VARCHAR sDatAtualizacao[LEN_DATE_FORMATADA+LEN_EOS];
VARCHAR sIdUsuarioAlteracao[LEN_NUMBER+LEN_EOS];

//NULLs
short iCodCep_ora;
short iCodLocalidade_ora;
short iIdUsuarioAlteracao_ora;
short iNumCep_ora;
short iDatAtualizacao_ora;
}TCEP;

EXEC SQL END DECLARE SECTION;

class CCep{

public:
    CCep();
    ~CCep();

    //Setters
	void setIdUsuarioAlteracao(char*);
    void setCodCep(char*);
    void setCodLocalidade(char*);
    void setNumCep(char*);
    void setDatAtualizacao(char*);

    //Getters
    char *getCodCep(void);
    char *getCodLocalidade(void);
    char *getNumCep(void);
    char *getDatAtualizacao(void);

    //Metodos para o BD.
    static CCep *RecuperarTodos(int *);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TCEP    tTabela;
    EXEC SQL END DECLARE SECTION;
};

#endif

