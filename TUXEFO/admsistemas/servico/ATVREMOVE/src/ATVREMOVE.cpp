#define ATVREMOVECPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CAtv.h"

DECLARE_TUXEDO_SERVICE(ATVREMOVE);

void implATVREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implATVREMOVE::Execute()");
	CSafePointer oSafePointer;
	CAtribuicao oAtribuicao;
	
	char* cidAtribuicao = oSafePointer.getTag(dnode,"idAtividade",0);
	if( strlennull( cidAtribuicao ) <= 0 )
	{
		setStatusCode("14E0000","idAtribuicao esta nulo");
		ULOG_END("implATVREMOVE::Execute()");
		return;
	}
	
	switch(oAtribuicao.Delete(cidAtribuicao))
	{
		case 0:
			setStatusCode("14E0000","Falha na execução do serviço.");
		break;
		case -2292:
			oAtribuicao.ListAll();
			oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
			setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
		break;
		default: 
			oAtribuicao.ListAll();
			oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
			setStatusCode("14I0000","Sucesso na execução do serviço!"); 
		break;
	}
	ULOG_END("implATVREMOVE::Execute()");
}
