/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas de negócio com a tabela OrdemVenda sendo a principal
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:18 $
 **/

#include "../include/OrdemVenda.h"
#include "../include/OrdemVendapc.h"
#include "../include/PedidoNegocio.h"

//==============================================================================
void COrdemVenda::buscarOrdVendaPorPedDoc(struct DadosParametros *pDadosParametros)
{
    ULOG_START("COrdemVenda::buscarOrdVendaPorPedDoc");

    if ( !xml_g )
    {
        throw new TuxException("24E9999","Área de saída não disponível.");
    }

    if ( !pDadosParametros )
    {
        throw new TuxException("24E9999","Parâmetro de entrada invalido!");
    }

    COrdemVendapc cordemvendapc;
    VEC_TORDEMVENDA vecTOrdemVenda;

    cordemvendapc.proCbuscarOrdVendaPorPedDoc(pDadosParametros,vecTOrdemVenda);

    int nElemVecTOrdemVenda = vecTOrdemVenda.size();

    if ( nElemVecTOrdemVenda )
    {
        for ( int i=0; i < nElemVecTOrdemVenda; i++ )
        {
            xml_g->addItem(XML_ORDEMVENDA_NRORDEMVENDA,vecTOrdemVenda.at(i).getNrOrdemVenda());
            xml_g->addItem(XML_ORDEMVENDA_INCORROMPIDO,vecTOrdemVenda.at(i).getInCorrompido());

            if ( vecTOrdemVenda.at(i).vecTOrdemNotaFiscal.size() )
            {
                xml_g->addItem(XML_ORDEMVENDA_NRNOTAFISCAL,vecTOrdemVenda.at(i).vecTOrdemNotaFiscal.at(0).getIdNotaFiscal());
                xml_g->addItem(XML_ORDEMVENDA_VLTOTALNOTAFISCAL,vecTOrdemVenda.at(i).vecTOrdemNotaFiscal.at(0).getVlTotalNotaFiscal());
                xml_g->addItem(XML_ORDEMVENDA_SERIENOTAFISCAL,vecTOrdemVenda.at(i).vecTOrdemNotaFiscal.at(0).getSerieNotaFiscal());
            }
            else
            {
                xml_g->addItem(XML_ORDEMVENDA_NRNOTAFISCAL,"");
                xml_g->addItem(XML_ORDEMVENDA_VLTOTALNOTAFISCAL,"");
                xml_g->addItem(XML_ORDEMVENDA_SERIENOTAFISCAL,"");
            }

            xml_g->addItem(XML_ORDEMVENDA_NMRESPONSAVELORDEMVENDA,vecTOrdemVenda.at(i).getNmResponsavelOrdemVenda());
            xml_g->addItem(XML_ORDEMVENDA_DSTIPOORDEMVENDA,vecTOrdemVenda.at(i).getDsTipoOrdemVenda());
            xml_g->addItem(XML_ORDEMVENDA_DSMOTIVOORDEM,vecTOrdemVenda.at(i).getDsMotivoOrdem());
            xml_g->addItem(XML_ORDEMVENDA_DSSTATUSORDEMVENDA,vecTOrdemVenda.at(i).getStatusOrdemVenda());

            xml_g->addItem(XML_ORDEMVENDA_DSMOTIVOBLOQUEIO,vecTOrdemVenda.at(i).getDsMotivoBloqueio());
            xml_g->addItem(XML_ORDEMVENDA_TPMOTIVOBLOQUEIO,vecTOrdemVenda.at(i).getTpMotivoBloqueio());

            xml_g->addItem(XML_ORDEMVENDA_NRFORNECIMENTO,vecTOrdemVenda.at(i).getNrFornecimento());
            xml_g->addItem(XML_ORDEMVENDA_DTFORNECIMENTO,vecTOrdemVenda.at(i).getDtFornecimento());
            xml_g->addItem(XML_ORDEMVENDA_NRPICKING,vecTOrdemVenda.at(i).getNrPicking());
            xml_g->addItem(XML_ORDEMVENDA_DTPICKING,vecTOrdemVenda.at(i).getDtPicking());
            xml_g->addItem(XML_ORDEMVENDA_DTCONFIRMACAOPICKING,vecTOrdemVenda.at(i).getDtConfirmacaoPicking());
            xml_g->addItem(XML_ORDEMVENDA_DTSAIDAMERCADORIA,vecTOrdemVenda.at(i).getDtSaidaMercadoria());
            xml_g->addItem(XML_ORDEMVENDA_DTNOTAFISCAL,vecTOrdemVenda.at(i).getDtNotaFiscal());

            xml_g->addItem(XML_ORDEMVENDA_NORMAEXPEDICAO,vecTOrdemVenda.at(i).getNormaExpedicao());
            xml_g->addItem(XML_ORDEMVENDA_ESCRITORIOVENDA,vecTOrdemVenda.at(i).getEscritorioVenda());
            xml_g->addItem(XML_ORDEMVENDA_GRUPOVENDA,vecTOrdemVenda.at(i).getGrupoVenda());
            xml_g->addItem(XML_ORDEMVENDA_CANALVENDA,vecTOrdemVenda.at(i).getCanalVenda());

            // Se o sistema origem for diferente de SAP envia tag com flag para desabilitar
            // o botão comparar.
            if ( stricmp(pDadosParametros->nmSistemaOrigem,"SAP") )
            {
                xml_g->addItem(XML_FLAGCOMPARAR,"1");
            }
            else
            {
                xml_g->addItem(XML_FLAGCOMPARAR,"0");
            }

            // Relaciona os dados de entrega com Ordem de Venda no VO de saída
            int nElemVecTProdutoEntrega = vecTOrdemVenda.at(i).vecTProdutoEntrega.size();

            xml_g->createTag("DadosEntrega");

            for ( int j=0; j < nElemVecTProdutoEntrega; j++ )
            {
               xml_g->addItem(XML_PRODUTOENTREGA_STATUSENTREGA,vecTOrdemVenda.at(i).vecTProdutoEntrega.at(j).getStatusEntrega());
               xml_g->addItem(XML_PRODUTOENTREGA_DSTIPOENTREGA,vecTOrdemVenda.at(i).vecTProdutoEntrega.at(j).getDsTipoEntrega());
               xml_g->addItem(XML_PRODUTOENTREGA_DTPREVISAOENTREGA,vecTOrdemVenda.at(i).vecTProdutoEntrega.at(j).getDtPrevisaoEntrega());
               xml_g->addItem(XML_PRODUTOENTREGA_DSCOMPROVANTEENTREGA,vecTOrdemVenda.at(i).vecTProdutoEntrega.at(j).getDsComprovanteEntrega());
               xml_g->addItem(XML_PRODUTOENTREGA_NMRECEBEDORPEDIDO,vecTOrdemVenda.at(i).vecTProdutoEntrega.at(j).getNmRecebedorPedido());
               xml_g->addItem(XML_PRODUTOENTREGA_NRDOCUMENTORECEBEDOR,vecTOrdemVenda.at(i).vecTProdutoEntrega.at(j).getNrDocumentoRecebedor());
               xml_g->addItem(XML_PRODUTOENTREGA_OBSERVACAOENTREGA,vecTOrdemVenda.at(i).vecTProdutoEntrega.at(j).getObservacaoEntrega());
               xml_g->addItem(XML_PRODUTOENTREGA_DSMOTIVOSTATUS,vecTOrdemVenda.at(i).vecTProdutoEntrega.at(j).getDsMotivoEntrega());
            }

            xml_g->closeTag(); // DadosEntrega
        }
    }
    else
    { // se não retornou dados da ordem de venda retorna um VO com tags em branco
        xml_g->addItem(XML_ORDEMVENDA_NRORDEMVENDA," ");

        xml_g->addItem(XML_ORDEMVENDA_NRNOTAFISCAL," ");
        xml_g->addItem(XML_ORDEMVENDA_VLTOTALNOTAFISCAL," ");

        xml_g->addItem(XML_ORDEMVENDA_NMRESPONSAVELORDEMVENDA," ");
        xml_g->addItem(XML_ORDEMVENDA_DSTIPOORDEMVENDA," ");
        xml_g->addItem(XML_ORDEMVENDA_DSMOTIVOORDEM," ");
        xml_g->addItem(XML_ORDEMVENDA_DSSTATUSORDEMVENDA," ");
        xml_g->addItem(XML_ORDEMVENDA_DSMOTIVOBLOQUEIO," ");
        xml_g->addItem(XML_ORDEMVENDA_TPMOTIVOBLOQUEIO," ");
        xml_g->addItem(XML_ORDEMVENDA_NRFORNECIMENTO," ");

        xml_g->addItem(XML_ORDEMVENDA_DTFORNECIMENTO," ");
        xml_g->addItem(XML_ORDEMVENDA_DTPICKING," ");
        xml_g->addItem(XML_ORDEMVENDA_DTCONFIRMACAOPICKING," ");
        xml_g->addItem(XML_ORDEMVENDA_DTSAIDAMERCADORIA," ");
        xml_g->addItem(XML_ORDEMVENDA_DTNOTAFISCAL," ");

        xml_g->addItem(XML_ORDEMVENDA_NRPICKING," ");
        xml_g->addItem(XML_ORDEMVENDA_NORMAEXPEDICAO," ");
        xml_g->addItem(XML_ORDEMVENDA_ESCRITORIOVENDA," ");
        xml_g->addItem(XML_ORDEMVENDA_GRUPOVENDA," ");
        xml_g->addItem(XML_ORDEMVENDA_CANALVENDA," ");
        xml_g->addItem(XML_ORDEMVENDA_INCORROMPIDO,"1");

        // Se o sistema origem for diferente de SAP envia tag com flag para desabilitar
        // o botão comparar.
        xml_g->addItem(XML_FLAGCOMPARAR,"0");

        xml_g->createTag("DadosEntrega");

        xml_g->addItem(XML_PRODUTOENTREGA_STATUSENTREGA," ");
        xml_g->addItem(XML_PRODUTOENTREGA_DSTIPOENTREGA," ");
        xml_g->addItem(XML_PRODUTOENTREGA_DTPREVISAOENTREGA," ");
        xml_g->addItem(XML_PRODUTOENTREGA_DSCOMPROVANTEENTREGA," ");
        xml_g->addItem(XML_PRODUTOENTREGA_NMRECEBEDORPEDIDO," ");
        xml_g->addItem(XML_PRODUTOENTREGA_NRDOCUMENTORECEBEDOR," ");
        xml_g->addItem(XML_PRODUTOENTREGA_OBSERVACAOENTREGA," ");

        xml_g->closeTag(); // DadosEntrega
    }

    ULOG_END("COrdemVenda::buscarOrdVendaPorPedDoc");
}


//==============================================================================
void COrdemVenda::buscarOrdVdaComparacao(struct DadosParametros *pDadosParametros)
{
    ULOG_START("COrdemVenda::buscarOrdVdaComparacao");

    if ( !xml_g )
    {
        throw new TuxException("24E9999","Área de saída não disponível.");
    }

    if ( !pDadosParametros )
    {
        throw new TuxException("24E9999","Parâmetro de entrada invalido!");
    }

    COrdemVendapc cordemvendapc;
    VEC_TORDEMVENDA vecTOrdemVenda;
    bool preencherLstProdEntrega = false;

    cordemvendapc.proCbuscarOrdVdaComparacao(pDadosParametros,vecTOrdemVenda
                                            ,preencherLstProdEntrega);

    int nElemVecTOrdemVenda = vecTOrdemVenda.size();

    if ( nElemVecTOrdemVenda )
    {
        // ID da ordem de venda
        strcpy(pDadosParametros->idOrdemVenda,vecTOrdemVenda.at(0).getNrOrdemVenda());

        for ( int i=0; i < nElemVecTOrdemVenda; i++ )
        {
            xml_g->addItem(XML_ORDEMVENDA_NRPEDIDO,vecTOrdemVenda.at(i).getNrPedido());
            xml_g->addItem(XML_SISTEMAORIGEM_NMSISTEMAORIGEM,vecTOrdemVenda.at(i).getNmSistemaOrigem());
            xml_g->addItem(XML_UF_SGUF,vecTOrdemVenda.at(i).getUfOrdem());
            xml_g->addItem(XML_ORDEMVENDA_DTORDEMVENDA,vecTOrdemVenda.at(i).getDtAberturaPedido());
            xml_g->addItem(XML_PEDIDO_DTABERTURAPEDIDO,vecTOrdemVenda.at(i).getDtAberturaPedido());
            xml_g->addItem(XML_ORDEMVENDA_NRORDEMVENDA,vecTOrdemVenda.at(i).getNrOrdemVenda());
            xml_g->addItem(XML_PEDIDO_VLTOTALPEDIDO,vecTOrdemVenda.at(i).getVlTotalPedido());
            if ( vecTOrdemVenda.at(i).vecTOrdemNotaFiscal.size() )
            { // Só exibe a nota fiscal selecionada na lista da primeira tela
                xml_g->addItem(XML_ORDEMVENDA_NRNOTAFISCAL,vecTOrdemVenda.at(i).vecTOrdemNotaFiscal.at(0).getIdNotaFiscal());
                xml_g->addItem(XML_ORDEMVENDA_VLTOTALNOTAFISCAL,vecTOrdemVenda.at(i).vecTOrdemNotaFiscal.at(0).getVlTotalNotaFiscal());
                xml_g->addItem(XML_ORDEMVENDA_VLPARCELA,vecTOrdemVenda.at(i).vecTOrdemNotaFiscal.at(0).getVlParcela());
            }
            else
            {
                xml_g->addItem(XML_ORDEMVENDA_NRNOTAFISCAL," ");
                xml_g->addItem(XML_ORDEMVENDA_VLTOTALNOTAFISCAL," ");
                xml_g->addItem(XML_ORDEMVENDA_VLPARCELA," ");
            }
            xml_g->addItem(XML_ORDEMVENDA_FORMAPAGAMENTO,vecTOrdemVenda.at(i).getFormaPagamento());
            xml_g->addItem(XML_ETAPAPEDIDO_OBSERVACAOETAPAPEDIDO,vecTOrdemVenda.at(i).getObservacaoEtapaPedido());
            xml_g->addItem(XML_ORDEMVENDA_ENDERECOENTREGA,vecTOrdemVenda.at(i).getEnderecoEntrega());
        }
    }
    else
    {
        memset(pDadosParametros->idOrdemVenda,0,sizeof(pDadosParametros->idOrdemVenda));

        xml_g->addItem(XML_ORDEMVENDA_NRPEDIDO," ");
        xml_g->addItem(XML_SISTEMAORIGEM_NMSISTEMAORIGEM," ");
        xml_g->addItem(XML_UF_SGUF," ");
        xml_g->addItem(XML_ORDEMVENDA_DTORDEMVENDA," ");
        xml_g->addItem(XML_PEDIDO_DTABERTURAPEDIDO," ");
        xml_g->addItem(XML_ORDEMVENDA_NRORDEMVENDA," ");
        xml_g->addItem(XML_PEDIDO_VLTOTALPEDIDO," ");
        xml_g->addItem(XML_ORDEMVENDA_NRNOTAFISCAL," ");
        xml_g->addItem(XML_ORDEMVENDA_VLTOTALNOTAFISCAL," ");
        xml_g->addItem(XML_ORDEMVENDA_VLPARCELA," ");
        xml_g->addItem(XML_ORDEMVENDA_FORMAPAGAMENTO," ");
        xml_g->addItem(XML_ETAPAPEDIDO_OBSERVACAOETAPAPEDIDO," ");
        xml_g->addItem(XML_ORDEMVENDA_ENDERECOENTREGA," ");
    }

    ULOG_END("COrdemVenda::buscarOrdVdaComparacao");
}
