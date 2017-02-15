//////////////////////////////////////////////////////////////////////
// CRelatorioAgAni.cpp: CRelatorioAgAni class.
//////////////////////////////////////////////////////////////////////

#include <CRelatorioAgendaAniversario.h>
#include <CDefErro.h>
#include <ErroMsg.h>

stErro CRelatorioAgAni::CheckRelatorio(struct SRelatorioCampanha* sRelatorioCampanha) 
{
	if ( CheckVar ( sRelatorioCampanha->pcidCampanha, EIDSUBCAMPANHA ) ) 
		  return sErro[EIDCAMPANHA]; 

	if ( CheckVar ( sRelatorioCampanha->pcidSubCampanha, EIDSUBCAMPANHA ) ) 
		  return sErro[EIDSUBCAMPANHA]; 

	if ( CheckVar ( sRelatorioCampanha->pcdtInicio, EDTINICIO ) ) 
		  return sErro[EDTINICIO]; 

	if ( CheckVar ( sRelatorioCampanha->pcdtFim, EDTFIM ) ) 
		  return sErro[EDTFIM]; 

	return sErro[EOK]; 
}