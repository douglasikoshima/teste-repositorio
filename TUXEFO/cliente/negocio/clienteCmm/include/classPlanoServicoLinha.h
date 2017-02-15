//-----------------------------------------------------------------------------------------
//* 
//* Classe: CServico
//* 
//* Purpose: Classe para recuperar todos os Serviços, Plano de Serviço e Contrato,
//*          utiliza como base a tabela PlanoServicoLinha.
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
#ifndef CLASSPLANOSERCVICOLINHA
#define CLASSPLANOSERCVICOLINHA

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;
typedef struct{
	VARCHAR sIdServicoLinha[LEN_NUMBER + LEN_EOS];
	VARCHAR sInPlano[LEN_NUMBER + LEN_EOS];
	VARCHAR sIdPacote[LEN_NUMBER + LEN_EOS];
	VARCHAR sNmServico[LEN_NMSERVICO + LEN_EOS];
	VARCHAR sDataFinal[LEN_DATE_FORMATADA + LEN_EOS];
	
	/* Indicator */
	short iIdServicoLinha_ora;
	short iInPlano_ora;
	short iIdPacote_ora;
	short iNmServico_ora;
	short iDataFinal_ora;
}TTABPLANOSERVICOLINHA;
EXEC SQL END DECLARE SECTION;

class CPlanoServicoLinha{
public:
       /* Construtor/Destrutor */
    CPlanoServicoLinha();
    virtual ~CPlanoServicoLinha();

	static CPlanoServicoLinha *RecuperarTodosServico(int*, char*);
	static CPlanoServicoLinha *RecuperarTodosPlServico(int*, char*);
	static CPlanoServicoLinha *RecuperarTodosContrato(int*, char*);
    int buscarPlanoServico(char*);

    //Metodos de acessos aos atributos
    //Setters
	void setIdServicoLinha(char*);
	void setInPlano(char*);
	void setIdPacote(char*);
	void setNmServico(char*);
	void setDataFinal(char*);
    //Getters
	char* getIdServicoLinha(void);
	char* getInPlano(void);
	char* getIdPacote(void);
	char* getNmServico(void);
	char* getDataFinal(void);

private:
	EXEC SQL BEGIN DECLARE SECTION;
		TTABPLANOSERVICOLINHA	tTabela;
	EXEC SQL END DECLARE SECTION;
};

#endif
