//-----------------------------------------------------------------------------------------
//* 
//* Classe: CUsuario
//* 
//* Purpose: Classe listar os dados de UsuarioVO da Lupa Linha
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe sao do tipo Pro*C.
//*
//* Public part:
//*     Metodos:
//*
//* Protegida part :
//*
//* Private part:
//-----------------------------------------------------------------------------------------
#ifndef CLASSUSUARIO
#define CLASSUSUARIO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
	VARCHAR sIdPessoa[LEN_NUMBER + LEN_EOS];
	VARCHAR sIdDocumento[LEN_NUMBER + LEN_EOS];
	VARCHAR sNmPessoa[LEN_NMPESSOA + LEN_EOS];
	VARCHAR sSgTipoDocumento[LEN_SGTIPODOCUMENTO + LEN_EOS];
	VARCHAR sDsTipoDocumento[LEN_DSTIPODOCUMENTO + LEN_EOS];
	VARCHAR sNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
	VARCHAR sDsTipoComunicacao[LEN_DSTIPODOCUMENTO + LEN_EOS];
	VARCHAR sDsContato[LEN_CONTATO + LEN_EOS];
	VARCHAR sVlRentabilidade[LEN_NUMBER + LEN_EOS];
	VARCHAR sDsCargoContato[LEN_CARGO + LEN_EOS];
	VARCHAR sInDivulgacaoNrLinha[LEN_NUMBER + LEN_EOS];
	VARCHAR sDtUltimaAlteracao[LEN_DATE_FORMATADA + LEN_EOS];
	VARCHAR sIdPessoaDePara[LEN_NUMBER + LEN_EOS];

	//Nulls
	short iIdPessoa_ora;
	short iIdDocumento_ora;
	short iNmPessoa_ora;
	short iSgTipoDocumento_ora;
	short iDsTipoDocumento_ora;
	short iNrDocumento_ora;
	short iDsTipoComunicacao_ora;
	short iDsContato_ora;
	short iVlRentabilidade_ora;
	short iDsCargoContato_ora;
	short iInDivulgacaoNrLinha_ora;
    short iDtUltimaAlteracao_ora;
	short iIdPessoaDePara_ora;
}TUSUARIOVO;

EXEC SQL END DECLARE SECTION;

class CUsuario{

public:
       /* Construtor/Destrutor */
    CUsuario();
    CUsuario(char*);
    virtual ~CUsuario();

    void Recuperar(char*);
    static CUsuario *RecuperarTodos(int *, char*);
    int buscarDadosTIPorNrLinha(char* pNrLin);

    //Metodos de acessos aos atributos
    //Setters
	void setIdPessoa(char*);
	void setIdPessoaDePara(char*);
	void setIdDocumento(char*);
	void setNmPessoa(char*);
	void setSgTipoDocumento(char*);
	void setDsTipoDocumento(char*);
	void setNrDocumento(char*);
	void setDsTipoComunicacao(char*);
	void setDsContato(char*);
	void setVlRentabilidade(char*);
	void setDsCargoContato(char*);
	void setInDivulgacaoNrLinha(char*);
	void setDtUltimaAlteracao(char*);
    //Getters
	char* getIdPessoa(void);
	char* getIdPessoaDePara(void);
	char* getIdDocumento(void);
	char* getNmPessoa(void);
	char* getSgTipoDocumento(void);
	char* getDsTipoDocumento(void);
	char* getNrDocumento(void);
	char* getDsTipoComunicacao(void);
	char* getDsContato(void);
	char* getVlRentabilidade(void);
	char* getDsCargoContato(void);
	char* getInDivulgacaoNrLinha(void);
	char* getDtUltimaAlteracao(void);

    char *getDsCargoContatoDeVerdade(char *pszIdPessoa);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TUSUARIOVO      tUsuVO;
    EXEC SQL END DECLARE SECTION;

};

#endif
