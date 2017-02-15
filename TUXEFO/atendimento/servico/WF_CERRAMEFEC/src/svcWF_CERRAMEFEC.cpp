/**
 * @modulo  Atendimento
 * @usecase Fechamento em massa
 * @author  Eder Jani Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:24 $
 **/ 

#include "../include/cWF_CERRAMEFEC.h"

//******************************************************************************************************************
// CERRAMEFEC - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(CERRAMEFEC);

void implCERRAMEFEC::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implCERRAMEFEC::Execute()");

	try
	{
		char* pzcRetCode = "04I0000";
		char* pzcRetMesg = "FECHAMENTO concluído com sucesso";

		cWF_CERRAMEFEC rc(dnode, getUser() );

		rc.execute(); 

        xml_g->createTag("WFAcaoRetornoVO");
	        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
	        xml_g->addItem("acaoExecucao", "S");
	        xml_g->addItem("mensagem", pzcRetMesg);
	        xml_g->addItem("urlDestino","1");
        xml_g->closeTag();
	
		setStatusCode( pzcRetCode, pzcRetMesg );
	}
    catch ( TuxBasicOraException *tboe )
    {
        char errCode[16];
        sprintf(errCode,"00E%04d",tboe->eCode<0?-1*tboe->eCode:tboe->eCode);
		setStatusCode( errCode, tboe->pMsg );
        xml_g->createTag("WFAcaoRetornoVO");
	        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
	        xml_g->addItem("acaoExecucao", "N");
	        xml_g->addItem("mensagem", tboe->pMsg);
	        xml_g->addItem("urlDestino","1");
        xml_g->closeTag();
        delete tboe;
    }
    catch( TuxException* pTux )
    {
		setStatusCode( "04E9998", pTux->getMessage() );
        xml_g->createTag("WFAcaoRetornoVO");
	        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
	        xml_g->addItem("acaoExecucao", "N");
	        xml_g->addItem("mensagem", pTux->getMessage());
	        xml_g->addItem("urlDestino","1");
        xml_g->closeTag();
        delete pTux;
    }
    catch(...)
    {
		setStatusCode( "04E9999", "Excecao desconhecida" );
        xml_g->createTag("WFAcaoRetornoVO");
	        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
	        xml_g->addItem("acaoExecucao", "N");
	        xml_g->addItem("mensagem", "Excecao desconhecida");
	        xml_g->addItem("urlDestino","1");
        xml_g->closeTag();
    }
    
    ULOG_END("implCERRAMEFEC::Execute()");
}
