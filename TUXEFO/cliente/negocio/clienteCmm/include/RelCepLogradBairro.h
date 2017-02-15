//-----------------------------------------------------------------------------------------
//* 
//* Classe: RELCEPLOGRADBAIRRO
//* 
//* Purpose: Classe para a view RelCepLogradBairrob01
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: 
//*
//* Public part:
//*     Metodos:
//*
//* Protegida part : nenhuma
//*
//* Private part:
//-----------------------------------------------------------------------------------------#ifndef CLASSCANAL
#ifndef RELCEPLOGRADBAIRRO
#define RELCEPLOGRADBAIRRO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR scdLogradouro[255 + LEN_EOS];
VARCHAR sinCNL[255 + LEN_EOS];
VARCHAR scdIBGE[255 + LEN_EOS];
VARCHAR sIdRelCepLogradBairro[LEN_NUMBER + LEN_EOS];
VARCHAR sIdLogradouro[LEN_NUMBER + LEN_EOS];
VARCHAR sIdCep[LEN_NUMBER + LEN_EOS];
VARCHAR sIdBairro[LEN_NUMBER + LEN_EOS];
VARCHAR sDtAtualizacaoOut[LEN_DATE_FORMATADA + LEN_EOS];
VARCHAR sInLadoNumeracao[LEN_NUMBER + LEN_EOS];
VARCHAR sNrFinal[LEN_NUMBER + LEN_EOS];
VARCHAR sNrInicial[LEN_NUMBER + LEN_EOS];
VARCHAR sCodTituloLograd[LEN_NUMBER + LEN_EOS];
VARCHAR sCodUsuarioAtualizacao[LEN_NUMBER + LEN_EOS];
VARCHAR sDscTituloLograd[LEN_DSCTITULOLOGRAD + LEN_EOS];
VARCHAR sDscAbrevTituloLograd[LEN_DSCABREVTITULOLOGRAD + LEN_EOS];
VARCHAR sDatAtualizacaoOut[LEN_DATE_FORMATADA + LEN_EOS];
VARCHAR sCodLogradouro[LEN_NUMBER + LEN_EOS];
VARCHAR sCodTipoLograd[LEN_NUMBER + LEN_EOS];
VARCHAR sCodLocalidade[LEN_NUMBER + LEN_EOS];
VARCHAR sNomLogradouro[LEN_NOMLOGRADOURO + LEN_EOS];
VARCHAR sIndNumeracaoEndereco[LEN_INDNUMERACAOENDERECO + LEN_EOS];
VARCHAR sFlgComplemento[LEN_FLGCOMPLEMENTO + LEN_EOS];
VARCHAR sDscTipoLograd[LEN_DSCTIPOLOGRAD + LEN_EOS];
VARCHAR sDscAbrevTipoLograd[LEN_DSCABREVTIPOLOGRAD + LEN_EOS];
VARCHAR sCodAreaTarifaria[LEN_NUMBER + LEN_EOS];
VARCHAR sNumCodNacLocalidade[LEN_NUMCODNACLOCALIDADE + LEN_EOS];
VARCHAR sDscAbrevLocalidade[LEN_DSCABREVLOCALIDADE + LEN_EOS];
VARCHAR sDscLocalidade[LEN_DSCLOCALIDADE + LEN_EOS];
VARCHAR sSglCodNacLocalidade[LEN_SGLCODNACLOCALIDADE + LEN_EOS];
VARCHAR sCodLocalidadePrinc[LEN_NUMBER + LEN_EOS];
VARCHAR sCodUF[LEN_NUMBER + LEN_EOS];
VARCHAR sHorFusoHorario[LEN_NUMBER + LEN_EOS];
VARCHAR sIdAreaTarifaria[LEN_NUMBER + LEN_EOS];
VARCHAR sIdPessoaUsuario[LEN_NUMBER + LEN_EOS];
VARCHAR sCdAreaTarifariaMiniCom[LEN_CDAREATARIFARIAMINICOM + LEN_EOS];
VARCHAR sSglUF[LEN_SGLUF + LEN_EOS];
VARCHAR sNomUF[LEN_NOMUF + LEN_EOS];
VARCHAR sCodPais[LEN_NUMBER + LEN_EOS];
VARCHAR sSglIsoPais[LEN_SGLISOPAIS + LEN_EOS];
VARCHAR sSglIso2Pais[LEN_SGLISO2PAIS + LEN_EOS];
VARCHAR sNomPais[LEN_NOMPAIS + LEN_EOS];
VARCHAR sNumPais[LEN_NUMPAIS + LEN_EOS];
VARCHAR sNumIsoPais[LEN_NUMISOPAIS + LEN_EOS];
VARCHAR sCodCEP[LEN_NUMBER + LEN_EOS];
VARCHAR sNumCEP[LEN_CEP + LEN_EOS];
VARCHAR sCodBairro[ + LEN_EOS];
VARCHAR sNomAbrevBairro[LEN_NUMBER + LEN_EOS];
VARCHAR sNomBairro[LEN_NOMBAIRRO + LEN_EOS];

//NULLs
short i_scdLogradouro;
short i_sinCNL;
short i_scdIBGE;
short iIdRelCepLogradBairro_ora;
short iIdLogradouro_ora;
short iIdCep_ora;
short iIdBairro_ora;
short iDtAtualizacaoOut_ora;
short iInLadoNumeracao_ora;
short iNrFinal_ora;
short iNrInicial_ora;
short iCodTituloLograd_ora;
short iCodUsuarioAtualizacao_ora;
short iDscTituloLograd_ora;
short iDscAbrevTituloLograd_ora;
short iDatAtualizacaoOut_ora;
short iCodLogradouro_ora;
short iCodTipoLograd_ora;
short iCodLocalidade_ora;
short iNomLogradouro_ora;
short iIndNumeracaoEndereco_ora;
short iFlgComplemento_ora;
short iDscTipoLograd_ora;
short iDscAbrevTipoLograd_ora;
short iCodAreaTarifaria_ora;
short iNumCodNacLocalidade_ora;
short iDscAbrevLocalidade_ora;
short iDscLocalidade_ora;
short iSglCodNacLocalidade_ora;
short iCodLocalidadePrinc_ora;
short iCodUF_ora;
short iHorFusoHorario_ora;
short iIdAreaTarifaria_ora;
short iIdPessoaUsuario_ora;
short iCdAreaTarifariaMiniCom_ora;
short iSglUF_ora;
short iNomUF_ora;
short iCodPais_ora;
short iSglIsoPais_ora;
short iSglIso2Pais_ora;
short iNomPais_ora;
short iNumPais_ora;
short iNumIsoPais_ora;
short iCodCEP_ora;
short iNumCEP_ora;
short iCodBairro_ora;
short iNomAbrevBairro_ora;
short iNomBairro_ora;

}TRELCEPLOGRADBAIRROB01;

EXEC SQL END DECLARE SECTION;

class CRelCepLogradBairro{

public:
    CRelCepLogradBairro();
    ~CRelCepLogradBairro();

	void setIdUsuarioAlteracao(char*);

    //Metodos Setters
	void setCdLogradouro( char* pDado );
	void setInCNL( char* pDado );
	void setCdIBGE( char* pDado );
    void setIdRelCepLogradBairro(char* pDado);
    void setIdLogradouro(char* pDado);
    void setIdCep(char* pDado);
    void setIdBairro(char* pDado);
    void setDtAtualizacaoOut(char* pDado);
    void setInLadoNumeracao(char* pDado);
    void setNrFinal(char* pDado);
    void setNrInicial(char* pDado);
    void setCodTituloLograd(char* pDado);
    void setCodUsuarioAtualizacao(char* pDado);
    void setDscTituloLograd(char* pDado);
    void setDscAbrevTituloLograd(char* pDado);
    void setDatAtualizacaOut(char* pDado);
    void setCodLogradouro(char* pDado);
    void setCodTipoLograd(char* pDado);
    void setCodLocalidade(char* pDado);
    void setNomLogradouro(char* pDado);
    void setIndNumeracaoEndereco(char* pDado);
    void setFlgComplemento(char* pDado);
    void setDscTipoLograd(char* pDado);
    void setDscAbrevTipoLograd(char* pDado);
    void setCodAreaTarifaria(char* pDado);
    void setNumCodNacLocalidade(char* pDado);
    void setDscAbrevLocalidade(char* pDado);
    void setDscLocalidade(char* pDado);
    void setSglCodNacLocalidade(char* pDado);
    void setCodLocalidadePrinc(char* pDado);
    void setCodUF(char* pDado);
    void setHorFusoHorario(char* pDado);
    void setIdAreaTarifaria(char* pDado);
    void setIdPessoaUsuario(char* pDado);
    void setCdAreaTarifariaMiniCom(char* pDado);
    void setSglUF(char* pDado);
    void setNomUF(char* pDado);
    void setCodPais(char* pDado);
    void setSglIsoPais(char* pDado);
    void setSglIso2Pais(char* pDado);
    void setNomPais(char* pDado);
    void setNumPais(char* pDado);
    void setNumIsoPais(char* pDado);
    void setCodCEP(char* pDado);
    void setNumCEP(char* pDado);
    void setCodBairro(char* pDado);
    void setNomAbrevBairro(char* pDado);
    void setNomBairro(char* pDado);
    void setDatAtualizacaoOut(char* pDado);

    //Metodos Getters
    char* getIdRelCepLogradBairro(void);
    char* getIdLogradouro(void);
    char* getIdCep(void);
    char* getIdBairro(void);
    char* getDtAtualizacaoOut(void);
    char* getInLadoNumeracao(void);
    char* getNrFinal(void);
    char* getNrInicial(void);
    char* getCodTituloLograd(void);
    char* getCodUsuarioAtualizacao(void);
    char* getDscTituloLograd(void);
    char* getDscAbrevTituloLograd(void);
    char* getDatAtualizacaoOut(void);
    char* getCodLogradouro(void);
    char* getCodTipoLograd(void);
    char* getCodLocalidade(void);
    char* getNomLogradouro(void);
    char* getIndNumeracaoEndereco(void);
    char* getFlgComplemento(void);
    char* getDscTipoLograd(void);
    char* getDscAbrevTipoLograd(void);
    char* getCodAreaTarifaria(void);
    char* getNumCodNacLocalidade(void);
    char* getDscAbrevLocalidade(void);
    char* getDscLocalidade(void);
    char* getSglCodNacLocalidade(void);
    char* getCodLocalidadePrinc(void);
    char* getCodUF(void);
    char* getHorFusoHorario(void);
    char* getIdAreaTarifaria(void);
    char* getIdPessoaUsuario(void);
    char* getCdAreaTarifariaMiniCom(void);
    char* getSglUF(void);
    char* getNomUF(void);
    char* getCodPais(void);
    char* getSglIsoPais(void);
    char* getSglIso2Pais(void);
    char* getNomPais(void);
    char* getNumPais(void);
    char* getNumIsoPais(void);
    char* getCodCEP(void);
    char* getNumCEP(void);
    char* getCodBairro(void);
    char* getNomAbrevBairro(void);
    char* getNomBairro(void);
	char* getCdLogradouro( void );
	char* getInCNL(void);
	char* getCdIBGE(void);

    //Metodos para o BD.
    static CRelCepLogradBairro *RecuperarTodos(int *);
    static CRelCepLogradBairro* RecuperarTodosPorFiltro(int* iNroObjetos, char *pNrCEP, char *pDsLogradouro, char *pDsLocalidade, char *pDsBairro,char *pIdUF);
    static CRelCepLogradBairro* RecuperarTodasUF(int* iNroObjetos);

    int RecuperarPorCep(char* pNumCep);
    bool existeCEP(char* pNumCep);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TRELCEPLOGRADBAIRROB01    tTabela;
    EXEC SQL END DECLARE SECTION;
};

#endif
