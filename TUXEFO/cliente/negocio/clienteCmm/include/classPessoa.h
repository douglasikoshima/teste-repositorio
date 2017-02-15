//-----------------------------------------------------------------------------------------
//* 
//* Classe: CPessoa
//* 
//* Purpose: Classe para a tabela PessoaFisica ou PessoaJuridica
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
#ifndef CLASSPESSOA
#define CLASSPESSOA


#include "Global.h"
#include "TString.h"

//------------------------------------------------------------------------------------------------------
EXEC SQL INCLUDE classPessoaLinha.h;

EXEC SQL BEGIN DECLARE SECTION;

#define LEN_EOS 1

//------------------------------------------------------------------------------------------------------
typedef enum enTipoPessoa { FISICA = 1, JURIDICA };
//------------------------------------------------------------------------------------------------------

 typedef struct
 {
	VARCHAR sDtNascimentoOut[LEN_DATE_FORMATADA + LEN_EOS]; /* sendo utilizado */
	VARCHAR sNmMae[LEN_EOS];
	VARCHAR sNmPai[LEN_EOS];
	VARCHAR sIdPaiS[LEN_EOS];
	VARCHAR sIdSexo[LEN_EOS];
	VARCHAR sIdTratamento[LEN_EOS];
	VARCHAR sIdEstadoCivil[LEN_EOS];    /* sendo utilizado */
	//NULLs
	short iDtNascimentoOut_ora;
	short iNmMae_ora;
	short iNmPai_ora;
	short iIdPaiS_ora;
	short iIdSexo_ora;
	short iIdTratamento_ora;
	short iIdEstadoCivil_ora;
 } TPESSOAFISICA;

 typedef struct
 {
	VARCHAR sNmPessoaFilial[LEN_PESSOAFILIAL + LEN_EOS];
	VARCHAR sNmFantasia[LEN_FANTASIA + LEN_EOS];
	//NULLs
	short iNmPessoaFilial_ora;
	short iNmFantasia_ora;
 } TPESSOAJURIDICA;

//------------------------------------------------------------------------------------------------------
 typedef struct
 {
	VARCHAR sIdPessoa[LEN_NUMBER + LEN_EOS];     /* sendo utilizado */
	VARCHAR sIdPessoaDePara[LEN_NUMBER + LEN_EOS];     /* sendo utilizado */
	VARCHAR sDtTipoCarteiraOut[LEN_DATE + LEN_EOS];
	VARCHAR sIdProbInadimplencia[LEN_NUMBER + LEN_EOS];
	VARCHAR sIdChurnProbabilidade[LEN_NUMBER + LEN_EOS];
	VARCHAR sDtCadastro[LEN_NUMBER + LEN_EOS];
	VARCHAR sDtCadastroOut[LEN_DATE_FORMATADA + LEN_EOS]; /* sendo utilizado */
	VARCHAR sIdTipoPessoa[LEN_NUMBER + LEN_EOS];
	VARCHAR sDsTipoPessoa[LEN_NOME + LEN_EOS];
	VARCHAR sSgTipoCarteira[LEN_NUMBER + LEN_EOS];
	VARCHAR sDsTipoCarteira[LEN_NOME + LEN_EOS];
	VARCHAR sVlPeso[LEN_NUMBER + LEN_EOS];
	VARCHAR sSgUF[LEN_NUMBER + LEN_EOS];
	VARCHAR sNmUF[LEN_NUMBER + LEN_EOS];
	VARCHAR sIdProbabilidade[LEN_NUMBER + LEN_EOS];
	VARCHAR sDsProbabilidade[LEN_NOME + LEN_EOS];
	VARCHAR sIdSistemaOrigem[LEN_NUMBER + LEN_EOS];
	VARCHAR sSgSistemaOrigem[LEN_NUMBER + LEN_EOS];
	VARCHAR sNmSistemaOrigem[LEN_NOME + LEN_EOS];
	VARCHAR sIdPessoaSistemaOrigem[LEN_NUMBER + LEN_EOS];
	VARCHAR sNmPessoa[LEN_NOME + LEN_EOS];     /* sendo utilizado */
	VARCHAR sNmNome[LEN_NOME + LEN_EOS];
	VARCHAR sNmNomemeio[LEN_NOME + LEN_EOS];
	VARCHAR sNmSobrenome[LEN_NOME + LEN_EOS];
	VARCHAR sDtChurn[LEN_DATE_FORMATADA + LEN_EOS];
	VARCHAR sDtChurNOut[LEN_DATE_FORMATADA + LEN_EOS];
	VARCHAR sInFalecimentoInformado[LEN_DATE_FORMATADA + LEN_EOS];
	VARCHAR sDtFalecimento[LEN_DATE_FORMATADA + LEN_EOS];
	VARCHAR sDtFalecimentoOut[LEN_DATE_FORMATADA + LEN_EOS];
	VARCHAR sIdTipopessoa[LEN_NUMBER + LEN_EOS];
	VARCHAR sTsSincronismo[LEN_NUMBER + LEN_EOS];
	VARCHAR sSqSincronismo[LEN_NUMBER + LEN_EOS];
	VARCHAR sIdTipoCarteira[LEN_NUMBER + LEN_EOS];
	VARCHAR sIdUF[LEN_NUMBER + LEN_EOS];
	VARCHAR sVlRChurnProbabilidade[LEN_NUMBER +LEN_EOS];
	VARCHAR sDtTipoCarteira[LEN_NOME + LEN_EOS];
	VARCHAR sSgTratamento[LEN_EOS];
	VARCHAR sDsTratamento[LEN_NOME + LEN_EOS];
	VARCHAR sSgEstadoCivil[LEN_NOME + LEN_EOS];
	VARCHAR sDsEstadoCivil[LEN_DSESTADOCIVIL + LEN_EOS]; /* sendo utilizado */
	VARCHAR sSgPais[LEN_NOME + LEN_EOS];
	VARCHAR sNmPais[LEN_NOME + LEN_EOS];
	VARCHAR sDsNacionalidade[LEN_NOME + LEN_EOS];
	VARCHAR sSgSexo[LEN_NOME + LEN_EOS];
	VARCHAR sDsSexo[LEN_NOME + LEN_EOS];
	VARCHAR sDsCargoContato[LEN_NOME + LEN_PESSOAFILIAL];
	VARCHAR sDsDeptoContato[LEN_NOME + LEN_PESSOAFILIAL];



	TPESSOAFISICA   tPF;
	TPESSOAJURIDICA tPJ;

	/* Nulls */
	short iIdPessoa_ora;
	short iNmPessoa_ora;
	short iDtCadastroOut_ora;
	short iDsEstadoCivil_ora;
	} TPESSOA;

//------------------------------------------------------------------------------------------------------

EXEC SQL END DECLARE SECTION;

//------------------------------------------------------------------------------------------------------
class CPessoa {
public:
     CPessoa();
    ~CPessoa(){};

    void setTipoPessoa(int);
	void setidUsuario(int);
    void setIdPessoa(char *);
    void setNmPessoa(char *);
    void setDtCadastroOut(char *);
    void setDsEstadoCivil(char *);
    void setDtNascimentoOut(char *);
    void setNmPessoaFilial(char *);
    void setNmFantasia(char *);


    char *getIdPessoa(void);
    char *getDtNascimentoOut(void);
    char *getDtCadastroOut(void);
    char *getNmPessoa(void);
    char *getDsEstadoCivil(void);
    char *getNmPessoaFilial(void);
    char *getNmFantasia(void);
    int   getTipoPessoa(void);
	char *getIdPessoaDePara();
	void GetData(TPessoaFisicaXML *xmlJ);

    int Incluir();
    int Alterar();
    int Excluir();
    int Recupera();
    int RecuperarPorTipoPessoa(char*);
	int UsuarioLinha; 
	TString sTipoRelacionamento; 

	void SetData(TPessoaFisicaXML xmlJ);
	void SetData(TPessoaJuridicaXML xmlJ); 
	void inserePessoa();
	void atualizaPessoa();
	void atualizaPessoaPorID();
	void proCInserePessoa( TPESSOA &tPessoa );
	void proCAtualizaPessoa( TPESSOA &tPessoa );
	void proCAtualizaPessoaPorID( TPESSOA &tPessoa );
	void proCInsereSegmentacao(); 
	
	EXEC SQL BEGIN DECLARE SECTION;
		int idSistemaOrigem; 
		int idUsuario; 
		short iidUsuario; 
		short iidSistemaOrigem; 
    EXEC SQL END DECLARE SECTION;


protected:
    EXEC SQL BEGIN DECLARE SECTION;
        TPESSOA			tPessoa;
		TPESSOAJURIDICA	tPessoaJuridica;
        int iTipoPessoa;
		short iTipoPessoa_ora;
    EXEC SQL END DECLARE SECTION;
	CPessoaLinha *pCPessoaLinha; 
    TPessoaDePara   tPessoaDePara;


};


#endif

