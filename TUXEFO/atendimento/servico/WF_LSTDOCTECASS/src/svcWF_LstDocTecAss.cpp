
/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Consulta Documentos Tecnicos Associados
 * Migracao: Script 105
 * Revisao.: Nov-23-2004 - Marcelo Nunes
 *--------------------------------------------------------*/

#include "../include/cWF_LstDocTecAssPC.h"

DECLARE_TUXEDO_SERVICE(LSTDOCTECASS);

void implLSTDOCTECASS::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implLSTDOCTECASS::Execute()");
    register int indx;
	Collection resultado;
    cWF_LstDocTecAss  rc;
	st_VariaveisLstDocTecAss * pdadosResult;
    st_VariaveisLstDocTecAss dados;
    char Msg[256];
    char * pBuffer;

    memset( &dados, 0x0, sizeof(dados) );

    pBuffer = walkTree( dnode, "idAtendimento", 0 );
    if ( pBuffer != NULL )
    {
      strcpy( dados.idAtendimento, pBuffer );
      XMLString::release(&pBuffer);
    }
    else
    {
      sprintf( Msg,"Codigo  de atendimento invalido: [%s]",pBuffer );
      throw new TuxBasicSvcException("09E0001",Msg);
    }


	xml_g->createTag("AtendimentoWorkflowTestesVO");
	xml_g->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
		xml_g->createTag("AtendimentoWorkflowTestesQuestVO");
			rc.consultaWFTestes( xml_g );
		xml_g->closeTag();
		xml_g->createTag("AtendimentoWorkflowTestesHistVO");
			rc.consultaWFTestesAtendimento( &dados,xml_g );
		xml_g->closeTag();
	xml_g->closeTag();
	

   
	setStatusCode("04I0000","Consulta de Testes Atendimento Concluida");
	
	ULOG_END("implLSTDOCTECASS::Execute()");

}
