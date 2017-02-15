#define RELCRITERIOCPP

#include "../../negocio/CampanhaCmm/include/CRelatorioFactory.h"
#include "../../negocio/CampanhaCmm/include/CRelatorioCampanha.h"

DECLARE_TUXEDO_SERVICE(RELCRITERIO);

void implRELCRITERIO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	stErro se; 
	se = CRelatorioFactory::VerificaRelatorio(dnode);
	setStatusCode(se.Codigo,se.Descricao);
}
