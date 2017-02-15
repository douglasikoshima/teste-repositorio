//////////////////////////////////////////////////////////////////////
// CRelatorioMotivos.cpp: CRelatorioMotivos class.
//////////////////////////////////////////////////////////////////////

#include <CRelatorioMotivos.h>
#include <CDefErro.h>

stErro sErroMotivos[10] ={{"05I0000", "Dados OK!", "", EOK },
	{ "05E0001", "Campanha invalida ou não selecionada"	, "idCampanha", EIDCAMPANHA},
	{ "05E0002", "Sub-campanha invalida ou não selecionada"	, "idSubCampanha", EIDSUBCAMPANHA},

	{ "05E0004", "Data Inicial invalida ou não selecionada"	, "dtInicio", EDTINICIO},
	{ "05E0005", "Data Final invalida ou não selecionada"	, "dtFim", EDTFIM} 
};


stErro CRelatorioMotivos::CheckRelatorio(struct SRelatorioCampanha* sRelatorioCampanha) 
{
	if ( CheckVar ( sRelatorioCampanha->pcidCampanha, EIDSUBCAMPANHA ) ) 
		  return sErroMotivos[EIDCAMPANHA]; 

	if ( CheckVar ( sRelatorioCampanha->pcidSubCampanha, EIDSUBCAMPANHA ) ) 
		  return sErroMotivos[EIDSUBCAMPANHA]; 

	if ( CheckVar ( sRelatorioCampanha->pcdtInicio, EDTINICIO ) ) 
		  return sErroMotivos[EDTINICIO]; 

	if ( CheckVar ( sRelatorioCampanha->pcdtFim, EDTFIM ) ) 
		  return sErroMotivos[EDTFIM]; 

	return sErroMotivos[EOK]; 
}
