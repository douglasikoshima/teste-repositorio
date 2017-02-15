/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:15 $
 **/


#include "../include/cMigracaoACS.h"

DECLARE_TUXEDO_SERVICE(WFMIGRACAOACS);

void implWFMIGRACAOACS::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFMIGRACAOACS::Execute()");
    register int indx;
    Collection resultado;
    cMigracaoACSPC rc;
    st_VariaveisMigracaoACS dados;
    st_VariaveisMigracaoACS * pdadosResult;
    char *p = walkTree( dnode, "idAtendimento", 0 );

    if ( p )
    {
        strcpy( dados.idAtendimento, p);
        XMLString::release(&p);
    }

     rc.ConsultaWFMigracaoAcsPC( &dados, &resultado );

    for ( indx=0;indx < resultado.GetCount();indx++ )
    {
        pdadosResult  = (st_VariaveisMigracaoACS *)resultado.GetItem(indx);

        xml_g->createTag("AtendimentoDetalheACSMigracaoVO");
        xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
	          xml_g->addItem("idAtendimento", pdadosResult->idAtendimento);
	          xml_g->addItem("idSistemaOrigem", pdadosResult->idSistemaOrigem);
	          xml_g->addItem("idProcesso", pdadosResult->idProcesso);
	          xml_g->addItem("nmUsuarioGrupoBKO", pdadosResult->nmUsuarioGrupoBKO);
	          xml_g->addItem("nmUsuarioBKO", pdadosResult->nmUsuarioBKO);
	          xml_g->addItem("inProcessoTecnico", pdadosResult->inProcessoTecnico);
	          xml_g->addItem("obsAtendimento", pdadosResult->obsAtendimento);
	          xml_g->addItem("nmClienteUsuario", pdadosResult->nmClienteUsuario);
	          xml_g->addItem("inClienteVIVO", pdadosResult->inClienteVIVO);
	          xml_g->addItem("nmTipoCliente", pdadosResult->nmTipoCliente);
	          xml_g->addItem("nmCPFCNPJ", pdadosResult->nmCPF_CNPJ);
	          xml_g->addItem("nmContato", pdadosResult->nmContato);
	          xml_g->addItem("nmTelefoneContato", pdadosResult->nmTelefoneContato);
	          xml_g->addItem("nmEmailContato", pdadosResult->nmEmailContato);
	          xml_g->addItem("nmTipoCarteira", pdadosResult->nmTipoCarteira);
	          xml_g->addItem("nmSegmentacao", pdadosResult->nmSegmentacao);
	          xml_g->addItem("idLinhaTelefonica", pdadosResult->idLinhaTelefonica);
	          xml_g->addItem("nrLinha", pdadosResult->nrLinha);
	          xml_g->addItem("nmTipoServico", pdadosResult->nmTipoServico);
	          xml_g->addItem("nmProcesso", pdadosResult->nmProcesso);
	          xml_g->addItem("dtTratamentoBKO", pdadosResult->dtTratamentoBKO);
	          xml_g->addItem("dtAtualizacao", pdadosResult->dtAtualizacao);
	          xml_g->addItem("dtImportacao", pdadosResult->dtImportacao);
	          xml_g->addItem("obsHistorico", pdadosResult->obsHistorico);
	          xml_g->addItem("idPessoaDePara", pdadosResult->idPessoaDePara);
	          xml_g->addItem("idTipoAtendimento", pdadosResult->idTipoAtendimento);

  	          if ( atol(pdadosResult->idAndamento) > 0 )
	          {
		         xml_g->createTag("AtendimentoDetalheACSAndamentoVO");
      	          xml_g->addItem("idAndamento", pdadosResult->idAndamento);
      	          xml_g->addItem("idAtividade", pdadosResult->idAtividade);
      	          xml_g->addItem("idContatoFolhaBaixa", pdadosResult->idContatoFolhaBaixa);
      	          xml_g->addItem("dtAndamento", pdadosResult->dtAndamento);
      	          xml_g->addItem("nmUsuarioGrupo", pdadosResult->nmUsuarioGrupo);
      	          xml_g->addItem("nmUsuario", pdadosResult->nmUsuario);
              xml_g->closeTag();
           }

           if ( atoi(pdadosResult->idComentario) > 0 )
           {
		         xml_g->createTag("AtendimentoDetalheACSComentariosVO");
      	          xml_g->addItem("idComentario", pdadosResult->idComentario);
      	          xml_g->addItem("nmUsuarioGrupo", pdadosResult->nmUsuarioGrupoMigracao);
      	          xml_g->addItem("nmLoginUsuario", pdadosResult->nmLoginUsuario);
      	          xml_g->addItem("dtAbertura", pdadosResult->dtAbertura);
      	          xml_g->addItem("observacao", pdadosResult->Observacao);
              xml_g->closeTag();
          }
           if ( strlen(pdadosResult->dsTipoAtendimento) > 0 )
           {
		         xml_g->createTag("TipoAtendimentoVO");
			        xml_g->addItem("dsTipoAtendimento", pdadosResult->dsTipoAtendimento);
              xml_g->closeTag();
           }

        xml_g->closeTag();
    }

    setStatusCode("04I0000","Obtencao de Campanhas por Grupo Concluida.");
    
    ULOG_END("implWFMIGRACAOACS::Execute()");
}
