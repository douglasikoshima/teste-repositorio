/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:49 $
 **/ 


#include "../include/cWFDocumentoTecnico.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFDocTec);

void implWFDocTec::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao[2];
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
        XMLString::release(&p);
    }

	cWFDocumentoTecnico cs(dnode, xml_g);

	if (operacao[0] == 'I')
		cs.incluir();
	else if (operacao[0] == 'A') 
	{
		ret = cs.alterar();
	}
	else if (operacao[0] == 'E')
	{
		ret = cs.excluir();
	}
	else if (operacao[0] == 'C')
		cs.consultar();
	else if (operacao[0] == 'T')
		cs.consultarAtendimento();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idDocumentoTecnico' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}


BEGIN_DECLARE_ROUTER_PARM(WFDocTecIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFDocTecAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFDocTecExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFDocTecConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM


DECLARE_RETURN_TYPE(WFDocTecIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFDocTecAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFDocTecExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFDocTecConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFDocTecIncluir,1)
	cWFDocumentoTecnico objeto(pWFDocTecIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFDocTecAlterar,1)
	cWFDocumentoTecnico objeto(pWFDocTecAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFDocTecExcluir,1)
	cWFDocumentoTecnico objeto(pWFDocTecExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFDocTecConsultar,1)
	cWFDocumentoTecnico objeto(pWFDocTecConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

// Pesquisa o documento do atendimento.
BEGIN_DECLARE_ROUTER_PARM(DocAtendimento)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(DocAtendimento,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(DocAtendimento,1)
	cWFDocumentoTecnico objeto(pDocAtendimento[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultarAtendimento();
END_DECLARE_ROUTER_INTERF

// Pesquisa todos documentos.
BEGIN_DECLARE_ROUTER_PARM(TodosDocumentos)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(TodosDocumentos,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(TodosDocumentos,1)
	cWFDocumentoTecnico objeto(pTodosDocumentos[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultarTodosDocumentos();
END_DECLARE_ROUTER_INTERF

// Selecao documentos
BEGIN_DECLARE_ROUTER_PARM(WFSelDocumentos)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFSelDocumentos,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFSelDocumentos,1)
	cWFDocumentoTecnico objeto(pWFSelDocumentos[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultarSelecaoDocumentos();
END_DECLARE_ROUTER_INTERF
