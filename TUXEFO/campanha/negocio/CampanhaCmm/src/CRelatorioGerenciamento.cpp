//////////////////////////////////////////////////////////////////////
// CRelatorioGerenciamento.cpp: CRelatorioGerenciamento class.
//////////////////////////////////////////////////////////////////////

#include <CRelatorioGerenciamento.h>
#include <CDefErro.h>

stErro sErroGerenciamento[10] ={{"05I0000", "Dados OK!", "", EOK },
	{ "05E0001", "Campanha invalida ou não selecionada"	, "idCampanha", EIDCAMPANHA},
	{ "05E0002", "Sub-campanha invalida ou não selecionada"	, "idSubCampanha", EIDSUBCAMPANHA},
/*	
	{ "05E0003", "Versão invalida ou não selecionada"	, "idVersao", EIDVERSAO}, 
*/
	{ "05E0004", "Data Inicial invalida ou não selecionada"	, "dtInicio", EDTINICIO},
	{ "05E0005", "Data Final invalida ou não selecionada"	, "dtFim", EDTFIM} 
};

stErro CRelatorioGerenciamento::CheckRelatorio(struct SRelatorioCampanha* sRelatorioCampanha) 
{
	if ( CheckVar ( sRelatorioCampanha->pcidCampanha, EIDSUBCAMPANHA ) ) 
		  return sErroGerenciamento[EIDCAMPANHA]; 

	if ( CheckVar ( sRelatorioCampanha->pcidSubCampanha, EIDSUBCAMPANHA ) ) 
		  return sErroGerenciamento[EIDSUBCAMPANHA]; 
/*
	if ( CheckVar ( sRelatorioCampanha->pcidVersao, EIDVERSAO ) ) 
		  return sErroGerenciamento[EIDVERSAO]; 
*/
	if ( CheckVar ( sRelatorioCampanha->pcdtInicio, EDTINICIO ) ) 
		  return sErroGerenciamento[EDTINICIO]; 

	if ( CheckVar ( sRelatorioCampanha->pcdtFim, EDTFIM ) ) 
		  return sErroGerenciamento[EDTFIM]; 

	return sErroGerenciamento[EOK]; 
}
