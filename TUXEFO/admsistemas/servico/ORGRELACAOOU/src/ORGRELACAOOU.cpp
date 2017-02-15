#define ORGRELACAOOUCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CUnd.h"

DECLARE_TUXEDO_SERVICE(ORGRELACAOOU);

void implORGRELACAOOU::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implORGRELACAOOU::Execute()");
	CSafePointer oSafePointer;
	CDepartamento oDepartamento;

	char* cidOrganizacao = oSafePointer.getTag(dnode,"idOrganizacao",0);
	if( strlennull( cidOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","idOrganizacao esta nulo");
		ULOG_END("implORGRELACAOOU::Execute()");
		return;
	}
	
    xml_g->createTag("ListaUnidadePorOrganizacaoVO");
	//Adiciona a propriedade necessaria para o xml
	xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );
	//Adiciona as tags necessarias
	xml_g->addItem("idOrganizacao", cidOrganizacao );

	    switch(oDepartamento.ListDeptoPorIdOrganizacao(cidOrganizacao))
	    {
		    case -1:	
				    setStatusCode("14E0000","Problemas no metodo List da Classe CDepartamento");
		    break;
		    default: 
				     oDepartamento.GetXml("UnidadeOrganogramaVO",xml_g); 
				     setStatusCode("14I0099","Sucesso na execução do método List da Classe CDepartamento"); 
		    break;
	    }

    xml_g->closeTag();	
    
   ULOG_END("implORGRELACAOOU::Execute()");
	
	return;
}
