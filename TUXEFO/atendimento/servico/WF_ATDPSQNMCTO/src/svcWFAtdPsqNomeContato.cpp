/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:50 $
 **/

#include "../include/cWFAtdPsqNomeContato.h"
#include "../../../commons/msgPadrao.h"

DECLARE_TUXEDO_SERVICE(WFATDPSQNMCTO);

void implWFATDPSQNMCTO::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implWFATDPSQNMCTO::Execute()");

    try
    {
        char *user;

        if ( user=getUser(),!user ) 
        {
            throw new TuxBasicSvcException("04I0000","Valor da tag 'user' é obrigatório.");
        }

        cWFAtdPsqNomeContato ob(dnode,xml_g);

        ob.setarIdUsuario(atoi(user));

        ob.Executar();

        setStatusCode("04I0000","Operação concluida");
    }
    catch ( TuxBasicOraException *pOra )
    {
        char codErro[32];
        char *pMessage = pOra->pMsg?pOra->pMsg:"Mensagem de erro do banco não informada pelo Oracle.";

        sprintf(codErro,"00I%04d",pOra->eCode<0?pOra->eCode*-1:pOra->eCode);

        ULOGE("TuxBasicOraException: %s",pMessage);

        xml_g->clearAndDestroy();

        xml_g->createTag("WFListaContatosVO");
        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
            xml_g->addItem("status","1");
            xml_g->addItem("msgStatus","Erro na base de dados");
        xml_g->closeTag();

        setStatusCode(codErro,pMessage);

        delete pOra;
    }
    catch( TuxBasicSvcException *pTuxBasicSvcException )
    {
        char *pCode = pTuxBasicSvcException->getCode();
        char *pMessage = pTuxBasicSvcException->getMessage();

        if ( !pCode ) pCode = "04I0000";
        if ( !pMessage ) pMessage = "TuxBasicSvcException:Erro não identificado.";

        ULOGE("TuxBasicSvcException: %s",pMessage);

        xml_g->clearAndDestroy();

        xml_g->createTag("WFListaContatosVO");
        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
            xml_g->addItem("status","1");
            xml_g->addItem("msgStatus",pMessage);
        xml_g->closeTag();

        setStatusCode(pCode,pMessage);

        delete pTuxBasicSvcException;
    }
    catch(TuxException *pTux)
    {
        char *pCode = pTux->getCode();
        char *pMessage = pTux->getMessage();

        if ( !pCode ) pCode = "04I0000";
        if ( !pMessage ) pMessage = "TuxException:Erro não identificado.";

        ULOGE("TuxException: %s",pMessage);

        xml_g->clearAndDestroy();

        xml_g->createTag("WFListaContatosVO");
        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
            xml_g->addItem("status","1");
            xml_g->addItem("msgStatus",pMessage);
        xml_g->closeTag();

        setStatusCode(pCode,pMessage);

        delete pTux;
    }
    catch(...)
    {
        ULOGE("erro desconhecido!");

        xml_g->clearAndDestroy();

        xml_g->createTag("WFListaContatosVO");
        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
            xml_g->addItem("status","1");
            xml_g->addItem("msgStatus","Erro desconhecido ocorrido");
        xml_g->closeTag();

        setStatusCode("04I0000","Operação NÃO concluida");
    }

    ULOG_END("implWFATDPSQNMCTO::Execute()");
}
