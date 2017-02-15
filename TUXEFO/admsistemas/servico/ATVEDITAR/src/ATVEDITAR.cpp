#define ATVEDITARCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CAtv.h"

DECLARE_TUXEDO_SERVICE(ATVEDITAR);

void implATVEDITAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implATVEDITAR::Execute()");
	CSafePointer oSafePointer;
	CAtribuicao oAtribuicao;
	
	char* cidAtribuicao = oSafePointer.getTag(dnode,"idAtividade",0);
	char* cnmAtribuicao = oSafePointer.getTag(dnode,"nmAtividade",0);
	if( strlennull( cidAtribuicao ) <= 0 )
	{
		setStatusCode("14E0000","idAtribuicao esta nulo");
		ULOG_END("implATVEDITAR::Execute()");
		return;
	}
	if( strlennull( cnmAtribuicao ) <= 0 )
	{
		setStatusCode("14E0000","nmAtribuicao esta nulo");
		ULOG_END("implATVEDITAR::Execute()");
		return;
	}
	char* cidUsuarioAlteracao = getUser();
	
	switch(oAtribuicao.Update(cidAtribuicao, cnmAtribuicao, cidUsuarioAlteracao))
	{
		case 0:	
			setStatusCode("14E0000","Falha na execução do serviço.");
			ULOG_END("implATVEDITAR::Execute()");
			return;
		break;
		case -1:
			oAtribuicao.ListAll();
			oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
			setStatusCode("14W0000","Já existe esta descrição ou registro, tente novamente com outra diferente.");
		break;
		default: 
			oAtribuicao.ListAll();
			oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
			setStatusCode("14I0000","Sucesso na execução do serviço!"); 
		break;
		
	}
	ULOG_END("implATVEDITAR::Execute()");
}
