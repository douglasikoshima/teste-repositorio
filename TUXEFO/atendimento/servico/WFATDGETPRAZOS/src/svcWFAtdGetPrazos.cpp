#include "tuxfw.h"
#include "../include/AtendimentoAlerta.h"

DECLARE_TUXEDO_SERVICE(ATDGETPRAZOS);

void implATDGETPRAZOS::Execute( DOMNode*dnode,XMLGen*xml_g )
{
    ULOG_START("implATDGETPRAZOS::Execute()");
    
    AtendimentoAlerta atdAlerta;
    int retorno = 0;
    TuxHelper tx;
    char *szInCri;

    xml_g->createTag("AtendimentoVO");
    xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:ns2","admsistemas.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:ns3","usuario.fo.vivo.com.br/vo");
    
    try
    {
        // Verifica se devemos realizar um tratamento específico para CRI
        if ( szInCri = tx.walkTree( dnode, "inCRI", 0 ), szInCri)
        {
            if( atoi(szInCri) )
            {
                retorno = atdAlerta.getWorkflowAlertaCRI(xml_g,strtoul(getUser(),0,10));
            }
            else
            {
                retorno = atdAlerta.getWorkflowAlerta(xml_g);
            }
            XMLString::release(&szInCri);
        }
        else
        {
            retorno = atdAlerta.getWorkflowAlerta(xml_g);
        }
    }
    catch(TuxBasicOraException tboe)
    {
        retorno = -1;
    }

    xml_g->closeTag();

    switch(retorno)
    {
        case -1:
            setStatusCode("00W0001","Erro de Oracle");
        break;

        default:
            setStatusCode("00I0000","sucesso");
        break;
    }
    
    ULOG_END("implATDGETPRAZOS::Execute()");
    
}