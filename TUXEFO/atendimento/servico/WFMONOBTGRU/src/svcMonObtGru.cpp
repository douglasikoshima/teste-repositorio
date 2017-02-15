/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:39 $
 **/

#include "../include/cMonObtGruPC.h"

DECLARE_TUXEDO_SERVICE(WFMONOBTGRU);

void implWFMONOBTGRU::Execute( DOMNode*dnode,XMLGen*xml_g )
{
    ULOG_START("implWFMONOBTGRU::Execute()");
    
    int indx;
    int inPesquisa = 0;
    cMonObtGruPC rc;
    Collection resultado;
    st_VariaveisGrupo dados;
    st_vlVariaveisGrupo status;
    st_VariaveisGrupo *gruposPC;
    char *p;

    // Inicializa as estruturas de dados para armazenar os valores.
    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    sprintf( dados.idUsuario,"%.*s",sizeof(dados.idUsuario)-1,getUser() );
    dados.idPessoaUsuarioPesquisa = atol(dados.idUsuario);

    // verifica qual o tipo de pesquisa de grupo deverá ser feita
    if ( p=walkTree( dnode, "inPesquisa", 0 ),p )
    {
        inPesquisa = strlen(p) ? atoi(p) : 0;
        XMLString::release(&p);
    }

    if ( p=walkTree( dnode, "dtInicio", 0 ),p )
    {
        inPesquisa = 1;
        if (strlen(p) > 0)
        {
            sprintf(dados.dtInicio,"%.*s",sizeof(dados.dtInicio)-1,p);
            status.dtInicio = 1;
        }
        XMLString::release(&p);
    }

    if (inPesquisa == 0)
    {
         xml_g->createTag("MonitoramentoPesquisaVO");
         xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );


        /****************************************************************************/
        // Pesquisa Tipos de Pessoas se vier a tag inTipoPessoa
        if ( p=walkTree( dnode, "inClassificadorTipoLinha", 0 ),p )
        {
            XMLString::release(&p);

            ULOG("Iniciando pesquisa de tipos de pessoas...");

            // Para a obtenção dos tipos de pessoa
            Collection resultadoClassificacaoTipoLinha;
            st_VariaveisClassificacaoTipoLinha dadosClassificacaoTipoLinha;
            st_VariaveisClassificacaoTipoLinha *ClassificacaoTipoLinhaPC;

            rc.obterClassificacaoTipoLinhaPC( &dadosClassificacaoTipoLinha,&resultadoClassificacaoTipoLinha );

            int getCountClassificacaoTipoLinha = resultadoClassificacaoTipoLinha.GetCount();

            ULOG("getCountClassificacaoTipoLinha=%d",getCountClassificacaoTipoLinha);

            for ( indx=0; indx<getCountClassificacaoTipoLinha; indx++ )
            {
                if ( ClassificacaoTipoLinhaPC = (st_VariaveisClassificacaoTipoLinha *)resultadoClassificacaoTipoLinha.GetItem(indx),ClassificacaoTipoLinhaPC )
                {
                    xml_g->createTag("ClassificacaoTipoLinhaVO");
                    xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo" );
                    xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
                        xml_g->addItem("idClassificacaoTipoLinhaVO",ClassificacaoTipoLinhaPC->idClassificacaoTipoLinha);
                        xml_g->addItem("dsClassificacaoTipoLinhaVO",ClassificacaoTipoLinhaPC->dsClassificacaoTipoLinha);
                    xml_g->closeTag();
                }
            }
        }

        /****************************************************************************/
        // Pesquisa Tipos de Pessoas se vier a tag inTipoPessoa
        if ( p=walkTree( dnode, "inTipoPessoa", 0 ),p )
        {
            XMLString::release(&p);

            ULOG("Iniciando pesquisa de tipos de pessoas...");

            // Para a obtenção dos tipos de pessoa
            Collection resultadoTipoPessoa;
            st_VariaveisTipoPessoa dadosTipoPessoa;
            st_VariaveisTipoPessoa *TipoPessoaPC;

            rc.obterTipoPessoaPC( &dadosTipoPessoa, &resultadoTipoPessoa );

            int getCountTipoPessoa = resultadoTipoPessoa.GetCount();

            ULOG("@cassio: getCountTipoPessoa=%d",getCountTipoPessoa); //@cassio

            for ( indx=0; indx<getCountTipoPessoa; indx++ )
            {
                if ( TipoPessoaPC = (st_VariaveisTipoPessoa *)resultadoTipoPessoa.GetItem(indx),TipoPessoaPC )
                {

                    ULOG("@cassio: idTipoPessoa=%s",TipoPessoaPC->idTipoPessoa); //@cassio
                    ULOG("@cassio: dsTipoPessoa=%s",TipoPessoaPC->dsTipoPessoa); //@cassio

                    xml_g->createTag("TipoPessoaVO");
                    xml_g->addProp("xmlns", "http://fidelizacao.fo.vivo.com.br/vo" );
                        xml_g->addItem( "idtipopessoa", TipoPessoaPC->idTipoPessoa );
                        xml_g->addItem( "dstipopessoa", TipoPessoaPC->dsTipoPessoa );
                    xml_g->closeTag();
                }
            }
        }

        if ( p=walkTree( dnode, "inRC", 0 ),p )
        {
            XMLString::release(&p);
            rc.obtemGruposPCRC( &dados, &resultado );
        }
        else
        {
            rc.obtemGruposPC( &dados, &resultado );
        }

         int getCount = resultado.GetCount();

         for ( indx=0;indx < getCount;indx++ )
         {
            if ( gruposPC = (st_VariaveisGrupo *)resultado.GetItem(indx),gruposPC )
            {
                xml_g->createTag("WFGrupoVO");
                xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );
                     xml_g->addItem( "idGrupo", gruposPC->idGrupo );
                     xml_g->addItem( "dsGrupo", gruposPC->dsGrupo );
                  xml_g->closeTag();
              }
         }
         
        /****************************************************************************/
        // Pesquisa as Regionais se vier a tag inRegional       
        if ( p=walkTree( dnode, "inRegional", 0 ),p )
        {
            XMLString::release(&p);

            ULOG("Iniciando pesquisa de regionais...");

            // Para a obtenção das regionais
            Collection resultadoRegional;
            st_VariaveisRegional dadosRegional;
            st_VariaveisRegional *regionaisPC;

            rc.obtemRegionaisPC( &dadosRegional, &resultadoRegional );

            int getCountRegional = resultadoRegional.GetCount();

            for ( indx=0; indx<getCountRegional; indx++ )
            {
                if ( regionaisPC = (st_VariaveisRegional *)resultadoRegional.GetItem(indx),regionaisPC )
                {
                    xml_g->createTag("WFRegionalVO");
                    xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );
                        xml_g->addItem( "idRegional", regionaisPC->idRegional );
                        xml_g->addItem( "dsRegional", regionaisPC->dsRegional );
                    xml_g->closeTag();
                }
            }
        }

        /****************************************************************************/
        // Pesquisa Tipos de Carteiras se vier a tag inTipoCarteira
        if ( p=walkTree( dnode, "inTipoCarteira", 0 ),p )
        {
            XMLString::release(&p);

            ULOG("Iniciando pesquisa de tipos de carteiras...");

            // Para a obtenção dos tipos de carteira
            Collection resultadoTipoCarteira;
            st_VariaveisTipoCarteira dadosTipoCarteira;
            st_VariaveisTipoCarteira *tipoCarteiraPC;

            rc.obterTiposCarteiraPC( &dadosTipoCarteira, &resultadoTipoCarteira );

            int getCountTipoCarteira = resultadoTipoCarteira.GetCount();

            for ( indx=0; indx<getCountTipoCarteira; indx++ )
            {
                if ( tipoCarteiraPC = (st_VariaveisTipoCarteira *)resultadoTipoCarteira.GetItem(indx),tipoCarteiraPC )
                {
                    xml_g->createTag("CarterizacaoVO");
                    xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
                        xml_g->addItem( "idTipoCarteira", tipoCarteiraPC->idTipoCarteira );
                        xml_g->addItem( "descricao", tipoCarteiraPC->dsTipoCarteira );
                    xml_g->closeTag();
                }
            }
        }

        /****************************************************************************/
        // Pesquisa Segmentações se vier a tag inSegmentacao
        if ( p=walkTree( dnode, "inSegmentacao", 0 ),p )
        {
            XMLString::release(&p);

            ULOG("Iniciando pesquisa de segmentações...");

            // Para a obtenção das segmentações
            Collection resultadoSegmentacao;
            st_VariaveisSegmentacao dadosSegmentacao;
            st_VariaveisSegmentacao *segmentacaoPC;

            rc.obterSegmentacaoPC( &dadosSegmentacao, &resultadoSegmentacao );

            int getCountSegmentacao = resultadoSegmentacao.GetCount();

            for ( indx=0; indx<getCountSegmentacao; indx++ )
            {
                if ( segmentacaoPC = (st_VariaveisSegmentacao *)resultadoSegmentacao.GetItem(indx),segmentacaoPC )
                {
                    xml_g->createTag("SegmentacaoVO");
                    xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
                        xml_g->addItem( "idSegmentacao", segmentacaoPC->idSegmentacao );
                        xml_g->addItem( "descricao", segmentacaoPC->dsSegmentacao );
                    xml_g->closeTag();
                }
            }
        }

        /****************************************************************************/
        // Pesquisa Alerta se vier a tag inAlerta
        if ( p=walkTree( dnode, "inAlerta", 0 ),p )
        {
            XMLString::release(&p);

            ULOG("Iniciando pesquisa de Alertas...");

            // Para a obtenção dos tipos de carteira
            Collection resultadoAlerta;
            st_VariaveisAlerta dadosAlerta;
            st_VariaveisAlerta *alertaPC;

            rc.obterAlertaPC( &dadosAlerta, &resultadoAlerta );

            int getCountAlerta = resultadoAlerta.GetCount();

            for ( indx=0; indx<getCountAlerta; indx++ )
            {
                if ( alertaPC = (st_VariaveisAlerta *)resultadoAlerta.GetItem(indx),alertaPC )
                {
                    xml_g->createTag("AlertaVO");
                    xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );
                        xml_g->addItem( "idAlerta", alertaPC->idAlerta );
                        xml_g->addItem( "dsAlerta", alertaPC->dsAlerta );
                    xml_g->closeTag();
                }
            }
        }

        /****************************************************************************/

        xml_g->closeTag();
    }
    else if (inPesquisa == 2 /* PESQUISA TODOS OS GRUPOS PARA REL.DE MONITORAÇÃO */)
    {
        rc.obterTodosGruposPC( &dados, &resultado );

        xml_g->createTag("MonitoramentoPesquisaVO");
        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );

        int getCount = resultado.GetCount();

        for ( indx=0;indx < getCount;indx++ )
        {
            if ( gruposPC = (st_VariaveisGrupo *)resultado.GetItem(indx),gruposPC )
            {
                xml_g->createTag("WFGrupoVO");
                    xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );
                    xml_g->addItem( "idGrupo", gruposPC->idGrupo );
                    xml_g->addItem( "dsGrupo", gruposPC->dsGrupo );
                xml_g->closeTag();
            }
        }
         
        xml_g->closeTag();
    }
    else
    {
        if ( p=walkTree( dnode, "dtFim", 0 ),p )
        {
            if (strlen(p) > 0)
            {
                sprintf(dados.dtFim,"%.*s",sizeof(dados.dtFim)-1,p);
                status.dtFim = 1;
            }
            XMLString::release(&p);
        }

        if ( p=walkTree( dnode, "idGrupo", 0 ),p )
        {
            if (strlen(p) > 0)
            {
                dados.idGrupoPesquisa = atol (p);
                status.idGrupoPesquisa = 1;
            }
            XMLString::release(&p);
        }

        if ( p=walkTree( dnode, "inPagina", 0 ),p )
        {
            if (strlen(p) > 0)
            {
                dados.inPagina = atol (p);
                status.inPagina = 1;
            }
            XMLString::release(&p);
        }

         xml_g->createTag("MonitoramentoResultadoVO");
         xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );
            rc.obtemMonitoramentoPC( &dados, &status, xml_g );
         xml_g->closeTag();
    }

    setStatusCode("04I0000","Obtenção de Grupos Concluida.");
    
    ULOG_END("implWFMONOBTGRU::Execute()");
}
