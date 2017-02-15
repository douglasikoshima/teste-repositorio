//-----------------------------------------------------------------------------------------
//* 
//* Classe: CDocumentoVO
//* 
//* Purpose: Classe para preencher o VO de Documento do VO LupaCliente
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe e´ do tipo Pro*C.
//*
//* Public part:
//*     Metodos:
//*		void setIdDocumento(char *);
//*		void setIdTipoDocumento(char *);
//*		void setSgTipoDocumento(char *);
//*		void setDsTipoDocumento(char *);
//*		void setNrDocumento(char *);
//*		void setSgOrgaoExpedidor(char *);
//*		void setSgUf(char *);
//*		void setDtEmissaoOut(char *);
//*		void setIdPais(char *);
//*		void setSgPais(char *);
//*		void setNmPais(char *);
//*		void setDsNacionalidade(char *);
//*		void setCdCpfCnpjBase(char *);
//*		void setCdCnpjFilial(char *);
//*		void setCdCpfCnpjControle(char *);
//*		void setTsSincronismo(long);
//*		void setSqSincronismo(long);
//*		char* getIdDocumento(void);
//*		char* getIdTipoDocumento(void);
//*		char* getSgTipoDocumento(void);
//*		char* getDsTipoDocumento(void);
//*		char* getNrDocumento(void);
//*		char* getSgOrgaoExpedidor(void);
//*		char* getSgUf(void);
//*		char* getDtEmissaoOut(void);
//*		char* getIdPais(void);
//*		char* getSgPais(void);
//*		char* getNmPais(void);
//*		char* getDsNacionalidade(void);
//*		char* getCdCpfCnpjBase(void);
//*		char* getCdCnpjFilial(void);
//*		char* getCdCpfCnpjControle(void);
//*		long getTsSincronismo(void);
//*		long getSqSincronismo(void);
//*
//* Protegida part :
//*
//* Private part:
//*     Atributos:  TTABELA    tTabela
//-----------------------------------------------------------------------------------------
#ifndef CLASSDOCUMENTOVO
#define CLASSDOCUMENTOVO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdDocumento[LEN_NUMBER+LEN_EOS];
VARCHAR sIdTipoDocumento[LEN_NUMBER+LEN_EOS];
VARCHAR sSgTipoDocumento[LEN_SGTIPODOCUMENTO+LEN_EOS];
VARCHAR sDsTipoDocumento[LEN_DSTIPODOCUMENTO+LEN_EOS];
VARCHAR sNrDocumento[LEN_NRDOCUMENTO+LEN_EOS];
VARCHAR sSgOrgaoExpedidor[LEN_SGORGAOEXPEDIDOR+LEN_EOS];
VARCHAR sSgUf[LEN_SGUF+LEN_EOS];
VARCHAR sDtEmissaoOut[LEN_DATE_FORMATADA+LEN_EOS];
VARCHAR sIdPais[LEN_NUMBER+LEN_EOS];
VARCHAR sSgPais[LEN_SGPAIS+LEN_EOS];
VARCHAR sNmPais[LEN_DSPAIS+LEN_EOS];
VARCHAR sDsNacionalidade[LEN_DSNACIONALIDADE+LEN_EOS];
VARCHAR sCdCpfCnpjBase[LEN_CDCPFCNPJBASE+LEN_EOS];
VARCHAR sCdCnpjFilial[LEN_CDCNPJFILIAL+LEN_EOS];
VARCHAR sCdCpfCnpjControle[LEN_CDCPFCNPJCONTROLE+LEN_EOS];
VARCHAR sNrPrioridade[LEN_NUMBER + LEN_EOS];
long    lTsSincronismo;
long    lSqSincronismo;
}TDOCUMENTOVO;

//Nulls
typedef struct{
short iIdDocumento;
short iIdTipodocumento;
short iSgTipodocumento;
short iDsTipodocumento;
short iNrDocumento;
short iSgOrgaoexpedidor;
short iSgUf;
short iDtEmissaoout;
short iIdPais;
short iSgPais;
short iNmPais;
short iDsNacionalidade;
short iCdCpfcnpjbase;
short iCdCnpjfilial;
short iCdCpfcnpjcontrole;
short iNrPrioridade_ora;
short iTsSincronismo;
short iSqSincronismo;
}TDOCUMENTOVO_NULL;

EXEC SQL END DECLARE SECTION;

class CDocumentoVO{

public:
       /* Construtor/Destrutor */
    CDocumentoVO();
    virtual ~CDocumentoVO();

    static CDocumentoVO *RecuperarTodos(int *, char*);
    int buscarPorIdPessoa(char*);

    //Metodos de acessos aos atributos
    //Setters
    void setIdDocumento(char *);
    void setIdTipoDocumento(char *);
    void setSgTipoDocumento(char *);
    void setDsTipoDocumento(char *);
    void setNrDocumento(char *);
    void setSgOrgaoExpedidor(char *);
    void setSgUf(char *);
    void setDtEmissaoOut(char *);
    void setIdPais(char *);
    void setSgPais(char *);
    void setNmPais(char *);
    void setDsNacionalidade(char *);
    void setCdCpfCnpjBase(char *);
    void setCdCnpjFilial(char *);
    void setCdCpfCnpjControle(char *);
    void setNrPrioridade(char *);
    void setTsSincronismo(long);
    void setSqSincronismo(long);
    //Getters
    char* getIdDocumento(void);
    char* getIdTipoDocumento(void);
    char* getSgTipoDocumento(void);
    char* getDsTipoDocumento(void);
    char* getNrDocumento(void);
    char* getSgOrgaoExpedidor(void);
    char* getSgUf(void);
    char* getDtEmissaoOut(void);
    char* getIdPais(void);
    char* getSgPais(void);
    char* getNmPais(void);
    char* getDsNacionalidade(void);
    char* getCdCpfCnpjBase(void);
    char* getCdCnpjFilial(void);
    char* getCdCpfCnpjControle(void);
    char* getNrPrioridade(void);
    long getTsSincronismo(void);
    long getSqSincronismo(void);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TDOCUMENTOVO      tDocVO;
        TDOCUMENTOVO_NULL tDocVONull;
    EXEC SQL END DECLARE SECTION;

};

#endif
