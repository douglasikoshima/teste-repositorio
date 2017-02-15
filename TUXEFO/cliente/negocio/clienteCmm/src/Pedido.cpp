/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas de negócio com a tabela Pedido sendo a principal
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/

#include "../include/Pedido.h"
#include "../include/Pedidopc.h"
#include "../include/EtapaPedidopc.h"
#include "../include/OrdemVendapc.h"
#include "../include/OrdemNotaFiscalpc.h"
#include "../include/ProdutoEntregapc.h"

//==============================================================================
void CPedido::buscarDetPedidoOrdemPorDocumento(struct DadosParametros *pDadosParametros)
{
    ULOG_START("CPedido::buscarDetPedidoOrdemPorDocumento");

    if ( !xml_g )
    {
        throw new TuxException("24E9999","Área de saída não disponível.");
    }

    if ( !pDadosParametros )
    {
        throw new TuxException("24E9999","Parâmetro de entrada invalido!");
    }

    // Se o sistema origem for diferente de SAP, busca dados na tabela de pedido
    // caso contrário, busca na tabela de ordem de venda
    VEC_TPEDIDO vecTPedido;
    if ( stricmp(pDadosParametros->nmSistemaOrigem,"SAP") )
    {
        CPedidopc cpedidopc;
        cpedidopc.procBuscarDetPedidoPorDocumento(pDadosParametros,vecTPedido);
    }
    else
    {
        COrdemVendapc cordemvendapc;

        cordemvendapc.procBuscarDetOVendaPorDocumento(pDadosParametros,vecTPedido);

        // se encontrou dados de ordem de venda, busca a somatoria das parcelas de todas
        // as notas fiscais associadas a ordem de venda.
        if ( vecTPedido.size() > 0 )
        {
            COrdemNotaFiscalpc cordemnotafiscalpc;
            VEC_TORDEMNOTAFISCAL vecTOrdemNotaFiscal;

            strcpy(pDadosParametros->idOrdemVenda,vecTPedido.at(0).getIdOrdemVenda());

            // obtém a soma das parcelas das notas fiscais associadas à ordem de venda
            cordemnotafiscalpc.procBuscarValorTotalParcelas(pDadosParametros,vecTOrdemNotaFiscal);

            if ( vecTOrdemNotaFiscal.size() )
            {
                vecTPedido.at(0).setVlParcela((unsigned char*)vecTOrdemNotaFiscal.at(0).getVlTotalNotaFiscal());
            }
        }
    }

    int nElemVecTPedido = vecTPedido.size();

    xml_g->addItem(XML_PEDIDO_IDPEDIDO,pDadosParametros->nrPedido);
    xml_g->addItem(XML_SISTEMAORIGEM_NMSISTEMAORIGEM,pDadosParametros->nmSistemaOrigem);
    xml_g->addItem(XML_UF_SGUF,pDadosParametros->sgUF);
    //xml_g->addItem(XML_UF_SGUF,nElemVecTPedido?vecTPedido.at(0).getSgUF():" ");
    xml_g->addItem(XML_PEDIDO_DTABERTURAPEDIDO,nElemVecTPedido?vecTPedido.at(0).getDtAberturaPedido():" ");
    xml_g->addItem(XML_PEDIDO_CDAGENTEFILIAL,nElemVecTPedido?vecTPedido.at(0).getCdAgenteFilial():" ");
    xml_g->addItem(XML_PEDIDO_DSCANALORIGEM,nElemVecTPedido?vecTPedido.at(0).getDsCanalOrigem():" ");
    xml_g->addItem(XML_PEDIDO_VLTOTALPEDIDO,nElemVecTPedido?vecTPedido.at(0).getVlTotalPedido():" ");
    xml_g->addItem(XML_PEDIDO_QTPONTORESGATADO,nElemVecTPedido?vecTPedido.at(0).getQtPontoResgatado():" ");
    xml_g->addItem(XML_PEDIDO_VLPARCELA,nElemVecTPedido?vecTPedido.at(0).getVlParcela():" ");
    xml_g->addItem(XML_PEDIDO_FORMAPAGAMENTO,nElemVecTPedido?vecTPedido.at(0).getFormaPagamento():" ");
    xml_g->addItem(XML_PEDIDO_OBSERVACAOPEDIDO,nElemVecTPedido?vecTPedido.at(0).getObservacaoPedido():" ");
    xml_g->addItem(XML_PEDIDO_ENDERECOENTREGA,nElemVecTPedido?vecTPedido.at(0).getEnderecoEntrega():" ");

    ULOG_END("CPedido::buscarDetPedidoOrdemPorDocumento");
}

//==============================================================================
void CPedido::buscarDetPedidoPorDocumento(struct DadosParametros *pDadosParametros)
{
    ULOG_START("CPedido::buscarDetPedidoPorDocumento");

    if ( !xml_g )
    {
        throw new TuxException("24E9999","Área de saída não disponível.");
    }

    if ( !pDadosParametros )
    {
        throw new TuxException("24E9999","Parâmetro de entrada invalido!");
    }

    CPedidopc cpedidopc;
    VEC_TPEDIDO vecTPedido;

    // Busca dados do pedido
    cpedidopc.procBuscarDetPedidoPorDocumento(pDadosParametros,vecTPedido);

    int nElemVecTPedido = vecTPedido.size();

    xml_g->addItem(XML_PEDIDO_IDPEDIDO,nElemVecTPedido?vecTPedido.at(0).getIdPedido():" ");
    xml_g->addItem(XML_SISTEMAORIGEM_NMSISTEMAORIGEM,nElemVecTPedido?vecTPedido.at(0).getNmSistemaOrigem():" ");
    xml_g->addItem(XML_UF_SGUF,nElemVecTPedido?vecTPedido.at(0).getSgUF():" ");
    xml_g->addItem(XML_PEDIDO_DTABERTURAPEDIDO,nElemVecTPedido?vecTPedido.at(0).getDtAberturaPedido():" ");
    xml_g->addItem(XML_PEDIDO_CDAGENTEFILIAL,nElemVecTPedido?vecTPedido.at(0).getCdAgenteFilial():" ");
    xml_g->addItem(XML_PEDIDO_DSCANALORIGEM,nElemVecTPedido?vecTPedido.at(0).getDsCanalOrigem():" ");
    xml_g->addItem(XML_PEDIDO_VLTOTALPEDIDO,nElemVecTPedido?vecTPedido.at(0).getVlTotalPedido():" ");
    xml_g->addItem(XML_PEDIDO_QTPONTORESGATADO,nElemVecTPedido?vecTPedido.at(0).getQtPontoResgatado():" ");
    xml_g->addItem(XML_PEDIDO_VLPARCELA,nElemVecTPedido?vecTPedido.at(0).getVlParcela():" ");
    xml_g->addItem(XML_PEDIDO_FORMAPAGAMENTO,nElemVecTPedido?vecTPedido.at(0).getFormaPagamento():" ");
    xml_g->addItem(XML_PEDIDO_OBSERVACAOPEDIDO,nElemVecTPedido?vecTPedido.at(0).getObservacaoPedido():" ");
    xml_g->addItem(XML_PEDIDO_ENDERECOENTREGA,nElemVecTPedido?vecTPedido.at(0).getEnderecoEntrega():" ");

    ULOG_END("CPedido::buscarDetPedidoPorDocumento");
}
