//////////////////////////////////////////////////////////////////////
// CRelatorioOperador.cpp: CRelatorioOperador class.
//////////////////////////////////////////////////////////////////////

#include <CRelatorioOperador.h>

stErro sErroOperador[10] =
{
	{ "05I0000", "Dados OK!"                                , ""             , EOK },
	{ "05E0001", "Campanha invalida ou n�o selecionada"	    , "idCampanha"   , EIDCAMPANHA},
	{ "05E0002", "Sub-campanha invalida ou n�o selecionada"	, "idSubCampanha", EIDSUBCAMPANHA},
	{ "05E0004", "Data Inicial invalida ou n�o selecionada"	, "dtInicio"     , EDTINICIO},
	{ "05E0005", "Data Final invalida ou n�o selecionada"	, "dtFim"        , EDTFIM} 
};

stErro CRelatorioOperador::CheckRelatorio(struct SRelatorioCampanha* sRelatorioCampanha) 
{
	if ( CheckVar ( sRelatorioCampanha->pcidCampanha, EIDSUBCAMPANHA ) ) 
		  return sErroOperador[EIDCAMPANHA]; 

	if ( CheckVar ( sRelatorioCampanha->pcidSubCampanha, EIDSUBCAMPANHA ) ) 
		  return sErroOperador[EIDSUBCAMPANHA]; 

	if ( CheckVar ( sRelatorioCampanha->pcdtInicio, EDTINICIO ) ) 
		  return sErroOperador[EDTINICIO]; 

	if ( CheckVar ( sRelatorioCampanha->pcdtFim, EDTFIM ) ) 
		  return sErroOperador[EDTFIM]; 

	return sErroOperador[EOK]; 
}

