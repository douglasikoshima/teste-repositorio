
/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Lista Documentos Associados
 * Migracao: Script 231
 * Revisao.: Nov-22-2004 - Marcelo Nunes
 *--------------------------------------------------------*/

#include "../include/cWF_LstDocAssocPC.h"

DECLARE_TUXEDO_SERVICE(LSTDOCASSOC);

void implLSTDOCASSOC::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implLSTDOCASSOC::Execute()");
    register int indx;
	Collection resultado;
    cWF_LstDocAssoc  rc;
	st_VariaveisLstDocAssoc * pdadosResult;
    st_DocumentoTecnico dados;
    st_vlDocumentoTecnico status;

    memset( &dados, 0x0, sizeof(dados) );
    memset( &status, -1, sizeof(status) );

    rc.carregaDados( dnode,&dados,&status );

    if (status.idAssociados != -1)
		rc.selecaoDocumentosTecnicosAtendimento( &dados,&status,&resultado );
    else
		rc.selecaoDocumentosTecnicos( &dados,&status,&resultado );

	xml_g->createTag("AtendimentoWorkflowTecnicoVO");
	xml_g->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
	xml_g->addProp( "xmlns:ns2","admsistemas.fo.vivo.com.br/vo" );
    for ( indx=0;indx < resultado.GetCount();indx++ )
    {
      pdadosResult  = (st_VariaveisLstDocAssoc *)resultado.GetItem(indx);

   	xml_g->createTag("AtendimentoWorkflowTecnicoDocVO");
   		xml_g->addItem("idDocumentoTecnico", pdadosResult->idDocumentoTecnico );
   		xml_g->addItem("nrDocumento", pdadosResult->nrDocumento );
   		xml_g->addItem("dtAbertura", pdadosResult->dtAbertura );
         if ( strlen(pdadosResult->dtFechamento) > 0 )
   			xml_g->addItem("dtFechamento", pdadosResult->dtFechamento );
   		if ( strlen(pdadosResult->dtEstimadaFechamento) > 0 )
   			xml_g->addItem("dtEstimadaFechamento",pdadosResult->dtEstimadaFechamento );
   		xml_g->addItem("dsDocumento",pdadosResult->dsDocumento );
   		xml_g->addItem("dsResposta",pdadosResult->dsResposta );
         xml_g->addItem("dsTipoDocumentoProcesso",pdadosResult->dsDocumentoTecnicoTipo );
      
   		if ( pdadosResult->inEstadoTecnico[0] == '0' )
   			xml_g->addItem("dsEstadoDocumentoProcesso", "Aberto" );
   		else
   			xml_g->addItem("dsEstadoDocumentoProcesso", "Fechado" );
   			
   		xml_g->addItem("qtdProcessosVinculados",pdadosResult->qtdProcessosVinculados );
 		xml_g->closeTag();

    }
    xml_g->closeTag();

  	setStatusCode("07I0000","Obtencao de Formulario Dinamico Concluido");
    ULOG_END("implLSTDOCASSOC::Execute()");
}
