/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:34 $
 **/

#include "../include/cWFAtdDetAcaoEx.h"
#include "../../../commons/msgPadrao.h"

DECLARE_TUXEDO_SERVICE( ATDDETACAOEX );

void implATDDETACAOEX::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDDETACAOEX::Execute()"); 

    char *usuario;

    try
    {
        if ( usuario=getUser(),!usuario ) 
        {
            throw new TuxBasicSvcException("00E0000","Valor da tag 'user' é obrigatório.");
        }

        WFAtdDetAcaoEx ob(dnode,xml_g);

        ob.setarIdUsuario(atoi(usuario));

        ob.executar();

        setStatusCode("07I0000","Operação concluida");
    }
    catch ( TuxBasicOraException *pOra )
    {
        char codErro[32];

        sprintf(codErro,"00E%04d",pOra->eCode<0?pOra->eCode*-1:pOra->eCode);

        ULOGE("TuxBasicOraException: %s",pOra->pMsg?pOra->pMsg:"????");

        setStatusCode(codErro,pOra->pMsg?pOra->pMsg:"????");

        delete pOra;
    }
    catch( TuxBasicSvcException *pTuxBasicSvcException )
    {
        char *pCode = pTuxBasicSvcException->getCode();
        char *pMessage = pTuxBasicSvcException->getMessage();

        if ( !pCode ) pCode = "04E9999";
        if ( !pMessage ) pMessage = "Erro não identificado.";

        if ( *(pCode+2) != 'E' )
        {
            xml_g->clearAndDestroy();
            
            xml_g->createTag("WFAcaoRetornoVO");
            xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
                xml_g->addItem("acaoExecucao","N");
                xml_g->addItem("mensagem",pMessage);
                xml_g->addItem("urlDestino","1");
            xml_g->closeTag();
        }

        ULOGE("TuxBasicSvcException: %s",pMessage);

        setStatusCode(pCode,pMessage);

        delete pTuxBasicSvcException;
    }
    catch(TuxException *pTux)
    {
        char *pCode = pTux->getCode();
        if ( !pCode ) pCode = "04E9999";

        char *pMessage = pTux->getMessage();
        if ( !pMessage ) pMessage = "Erro não identificado.";

        if ( *(pCode+2) != 'E' )
        {
            xml_g->clearAndDestroy();
            
            xml_g->createTag("WFAcaoRetornoVO");
            xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
                xml_g->addItem("acaoExecucao","N");
                xml_g->addItem("mensagem",pMessage);
                xml_g->addItem("urlDestino","1");
            xml_g->closeTag();
        }

        ULOGE("TuxException: %s",pMessage);

        setStatusCode(pCode,pMessage);

        delete pTux;
    }
    catch(...)
    {
        ULOGE("erro desconhecido!");

        setStatusCode("04E9999","Operação NÃO concluida");
    }

    ULOG_END("implATDDETACAOEX::Execute()"); 
}
