/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Andrei Kurak
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:53 $
 **/

#include "tuxfw.h"
#include "../include/AtendimentoMotivo.h"

DECLARE_TUXEDO_SERVICE(ATDGETMOTIVOS);

void implATDGETMOTIVOS::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implATDGETMOTIVOS::Execute()");
	AtendimentoMotivo obj;
	char *idTabelaMotivo = walkTree(dnode,"idTabelaMotivo",0);
	//char *idAtividade = walkTree(dnode,"idAtividade",0);
	int retorno = 0;

	xml_g->createTag("WFAtdNotasVO");
	xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
	xml_g->createTag("WFAtdNotaVO");

	try
	{
		if(idTabelaMotivo == NULL)
		//if(idAtividade == NULL)
        {
			retorno = obj.listAllMotivos(xml_g);
        }
		else
        {
			retorno = obj.listMotivos(idTabelaMotivo,xml_g);
//			retorno = obj.listMotivos(idAtividade,xml_g);
        }
	}
    catch (TuxBasicOraException *ex)
    {
		ULOGE("sqlca.sqlcode %d",ex->eCode);
		retorno = ex->eCode;
        
        delete ex;
	}
    catch (TuxBasicOraException ex)
    {
		ULOGE("sqlca.sqlcode %d",ex.eCode);
		retorno = ex.eCode;
	}
    catch (...)
    {
		ULOGE("erro desconhecido");
		retorno = -1;
	}

	xml_g->closeTag();
	xml_g->closeTag();
//	XMLString::release(&idAtividade);
    XMLString::release(&idTabelaMotivo);

	if(retorno == 1)
	{
		setStatusCode("00I0000","Sucesso");
	}
	else
	{
		setStatusCode("00W0000","Erro ao listar dados");
	}
	
	ULOG_END("implATDGETMOTIVOS::Execute()");
	
}