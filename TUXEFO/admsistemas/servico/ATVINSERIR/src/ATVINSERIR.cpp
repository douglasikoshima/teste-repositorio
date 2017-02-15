#define ATVINSERIRCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CAtv.h"

DECLARE_TUXEDO_SERVICE(ATVINSERIR);

void implATVINSERIR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implATVINSERIR::Execute()");
	CSafePointer oSafePointer;
	CAtribuicao oAtribuicao;
	
	char* cnmAtribuicao = oSafePointer.getTag(dnode,"nmAtividade",0);
	if( strlennull( cnmAtribuicao ) <= 0 )
	{
		setStatusCode("14E0000","nmAtribuicao esta nulo");
		ULOG_END("implATVINSERIR::Execute()");
		return;
	}
	char* cidUsuarioAlteracao = getUser();

	switch(oAtribuicao.Insert( cnmAtribuicao, cidUsuarioAlteracao))
	{
		case 0:	
			setStatusCode("14E0000","Falha na execu��o do servi�o.");
			ULOG_END("implATVINSERIR::Execute()");
			return;
		break;
		case -1:
			oAtribuicao.ListAll();
			oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
			setStatusCode("14W0000","J� existe esta descri��o ou registro, tente novamente com outra diferente.");
		break;
		default: 
			oAtribuicao.ListAll();
			oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
			setStatusCode("14I0000","Sucesso na execu��o do servi�o!"); 
		break;
	}
	
	ULOG_END("implATVINSERIR::Execute()");
}
