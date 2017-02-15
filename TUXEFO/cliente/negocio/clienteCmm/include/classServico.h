//-----------------------------------------------------------------------------------------
//* 
//* Classe: CServico
//* 
//* Purpose: Classe para recuperar todos os servicos de um numero de linha.
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: 
//*
//* Public part:
//*     Metodos:
//*
//* Protegida part :
//*
//* Private part:
//*     Atributos:  
//-----------------------------------------------------------------------------------------
#ifndef CLASSSERVICO
#define CLASSSERVICO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;
typedef struct{
	VARCHAR sIdServicoLinha[LEN_NUMBER + LEN_EOS];
	VARCHAR sInPlano[LEN_NUMBER + LEN_EOS];
	VARCHAR sIdPacote[LEN_NUMBER + LEN_EOS];
	VARCHAR sNmServico[LEN_NMSERVICO + LEN_EOS];
	
	/* Indicator */
	short iIdServicoLinha_ora;
	short iInPlano_ora;
	short iIdPacote_ora;
	short iNmServico_ora;
}TTABSERVICO;
EXEC SQL END DECLARE SECTION;

class CServico{
public:
       /* Construtor/Destrutor */
    CServico();
    virtual ~CServico();

    static CServico *RecuperarTodos(int*, char*);

    //Metodos de acessos aos atributos
    //Setters
	void setIdServicoLinha(char*);
	void setInPlano(char*);
	void setIdPacote(char*);
	void setNmServico(char*);
    //Getters
	char* getIdServicoLinha(void);
	char* getInPlano(void);
	char* getIdPacote(void);
	char* getNmServico(void);

private:
	EXEC SQL BEGIN DECLARE SECTION;
		TTABSERVICO	tTabela;
	EXEC SQL END DECLARE SECTION;
};

#endif
