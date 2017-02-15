//-----------------------------------------------------------------------------------------
//* 
//* Classe: CBuscaDadosLinha
//* 
//* Purpose: Classe para buscar dados da linha para LupaLinha.
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe sao do VARCHAR.
//*
//* Public part:
//*     Metodos:
//*
//* Protegida part : nenhuma
//*
//* Private part:
//*             tTabelab01
//-----------------------------------------------------------------------------------------
#ifndef CLASSBUSCADADOSLINHA
#define CLASSBUSCADADOSLINHA

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdPessoa[LEN_NUMBER +LEN_EOS];
VARCHAR sIdLinhaTelefonica[LEN_NUMBER +LEN_EOS];
VARCHAR sCdAreaRegistro[LEN_CDAREAREGISTRO +LEN_EOS];
VARCHAR sNrLinha[LEN_NRLINHA +LEN_EOS];
VARCHAR sDsTipoLinha[LEN_DSTIPOLINHA +LEN_EOS];
VARCHAR sDsEstadoLinha[LEN_DSESTADOLINHA +LEN_EOS];
VARCHAR sDtHabilitacaoOut[LEN_DATE_FORMATADA +LEN_EOS];
VARCHAR sInDivulgacaoNrLinha[LEN_NUMBER  +LEN_EOS];
VARCHAR sDtTerminoContrato[LEN_DATE_FORMATADA +LEN_EOS];
VARCHAR sNmServico[LEN_NMSERVICO +LEN_EOS];
VARCHAR sTpContrato[LEN_DSTIPOCONTRATO +LEN_EOS];
long lTotalReg;

/* Nulls */
short iIdPessoa_ora;
short iIdLinhaTelefonica_ora;
short iCdAreaRegistro_ora;
short iNrLinha_ora;
short iDsTipoLinha_ora;
short iDsEstadoLinha_ora;
short iDtHabilitacaoOut_ora;
short iInDivulgacaoNrLinha_ora;
short iDtTerminoContrato_ora;
short iNmServico_ora;
short iTpContrato_ora;
}TBUSCADADOSLINHA;
EXEC SQL END DECLARE SECTION;

class CBuscaDadosLinha{
public:
    CBuscaDadosLinha();
    ~CBuscaDadosLinha();
    
    static CBuscaDadosLinha *RecuperarPorIdPessoa(int *, char*);
	static CBuscaDadosLinha* buscarPorDadosFiltro(int* iNroObjetos, char* pId, char* pLinhaDe, char* pLinhaAte, char* pIdTipo, char* pIdEstado);
	static CBuscaDadosLinha* buscarPorDadosFiltro(int* iNroObjetos, char* pId, char* pLinhaDe, char* pLinhaAte, char* pIdTipo, char* pIdEstado, char* pPagina, char* cContadorPagina,int* iProximaPagina);
    static long getTotalReg(char* pId,char* pLinhaDe, char* pLinhaAte, char* pIdTipo, char* pIdEstado);
    static void obterQtdEstadoLinhaCarregaTI(char* pId, int &qtLinhasAtivas, int &qtLinhasInativas);

    void setIdPessoa(char*);
    void setIdLinhaTelefonica(char*);
    void setCdAreaRegistro(char*);
    void setNrLinha(char*);
    void setDsTipoLinha(char*);
    void setDsEstadoLinha(char*);
    void setDtHabilitacaoOut(char*);
    void setInDivulgacaoNrLinha(char*);
    void setDtTerminoContrato(char*);
    void setNmServico(char*);
    void setTpContrato(char*);
    void setTotalReg(long);

    char* getIdPessoa(void);
    char* getIdLinhaTelefonica(void);
    char* getCdAreaRegistro(void);
    char* getNrLinha(void);
    char* getDsTipoLinha(void);
    char* getDsEstadoLinha(void);
    char* getDtHabilitacaoOut(void);
    char* getInDivulgacaoNrLinha(void);
    char* getDtTerminoContrato(void);
    char* getNmServico(void);
    char* getTpContrato(void);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TBUSCADADOSLINHA    tCampos;
    EXEC SQL END DECLARE SECTION;
};

#endif

