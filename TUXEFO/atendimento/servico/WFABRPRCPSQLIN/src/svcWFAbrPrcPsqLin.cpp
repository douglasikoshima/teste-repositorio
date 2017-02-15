
#include "../include/cWFAbrPrcPsqLinPC.h"

DECLARE_TUXEDO_SERVICE(WFPSQLINHAS);

void implWFPSQLINHAS::Execute( DOMNode*dnode,XMLGen*xml_g )
{
    ULOG_START("implCNSPRIORIZ::Execute()");
    char                 *pBuffer;
    cWFAbrPrcPsqLinPC    rc;
    stVariaveisPsqLinhas  dados;

    pBuffer = walkTree( dnode, "idConta", 0 );

    if ( pBuffer == NULL )
    {
        dados.idConta[0] = 0x0;
    }
    else
    {
        strcpy( dados.idConta,pBuffer );
        XMLString::release(&pBuffer);
    }

    pBuffer = walkTree( dnode, "idLinha", 0 );

    if ( pBuffer == NULL )
    {
        dados.idLinha[0] = 0x0;
    }
    else
    {
        strcpy( dados.idLinha,pBuffer );
        XMLString::release(&pBuffer);
    }


    xml_g->createTag("ContaVO");
    xml_g->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );

	 rc.proCObtemWFAtdLinhas(atoi(dados.idConta), atoi(dados.idLinha), xml_g);

    xml_g->closeTag();

    setStatusCode("04I0000","Pesquisa de Linhas Concluida.");
    
    ULOG_END("implCNSPRIORIZ::Execute()");

}
