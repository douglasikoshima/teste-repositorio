//////////////////////////////////////////////////////////////////////
// CRelatorioCampanha.h: interface for the CRelatorioCampanha class.
//////////////////////////////////////////////////////////////////////

#ifndef RelatorioCampanhaHH
#define RelatorioCampanhaHH

#include "CTipoMotivoCampanha.h"
#include "TString.h"
#include "SRelatorioCampanha.h"
#include <tuxfw.h>
#include "CDefErro.h"


// Constantes para os relatórios de campanha.
#define REL_GERENCIAMENTO  	1
#define REL_EFETIVIDADE    	2
#define REL_OPERADOR   		3
#define REL_MOTIVOS   		4
#define REL_RESPOSTAS  		5
#define REL_AGANIVERSARIO	6
#define REL_ARQRESP	        7
#define REL_ARQRESULT       8

#define STRCPY_TO_ORA(dest, source){ \
        dest.len = (unsigned short) strlen(source); \
        strcpy((char *) dest.arr, (const char *) source);}

#define STRCPY_FROM_ORA(dest, source)\
        dest[source.len] = 0;\
        strncpy((char *)dest,(const char *)source.arr, source.len)

#define STRNCPY_TO_ORA(dest, source, tam){\
	            memset(&dest, 0x00, sizeof(dest));\
				strncpy((char *)dest.arr, (const char *)source, tam);\
                dest.len = (unsigned short) strlen((char*)dest.arr);}

#define STRNCAT_TO_ORA(dest, tok, tam) { \
        dest.len += (unsigned short) strlen(tok);\
        strncat((char *) dest.arr, (const char *) tok, tam); }


#define STRCPY_ORA_2_ORA(dest, source) { \
        dest.len = (unsigned short) source.len;\
        strncpy((char *) dest.arr, (const char *) source.arr, (size_t)source.len); }

#define ZERA_TO_ORA(source){\
        source.arr[source.len] = '\0';}


class CRelatorioCampanha
{
public:
	// Destrutor
	CRelatorioCampanha() {};
	~CRelatorioCampanha() {};

	// Serviços Auxiliares
	char*	trim(char *pStr);
	int		CheckVar(char *Campo, int indice);

	// Monta o relatório
	virtual void   montaRelatorio(struct SRelatorioCampanha*,XMLGen*) = 0;
	virtual stErro CheckRelatorio(struct SRelatorioCampanha*) = 0;
	
	CTipoMotivoCampanha oTipoMotivoCampanha;	
};

#endif


