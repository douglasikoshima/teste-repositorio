/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas de negócio com a tabela ItemPedido sendo a principal
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/

#include "../include/ItemPedido.h"
#include "../include/ItemPedidopc.h"
#include "../include/ItemOrdemVendapc.h"

//==============================================================================
void CItemPedido::buscarLstItemPedidoPorPedDoc(struct DadosParametros *pDadosParametros)
{
    ULOG_START("CItemPedido::buscarLstPedidosPorDocumento");

    if ( !xml_g )
    {
        throw new TuxException("24E9999","Área de saída não disponível.");
    }

    if ( !pDadosParametros )
    {
        throw new TuxException("24E9999","Parâmetro de entrada invalido!");
    }

    CItemPedidopc cItemPedidopc;
    VEC_TITEMPEDIDO vecTItemPedido;

    if(RET_NAO_EXISTEM_DADOS != cItemPedidopc.proCbuscarLstItemOrdemVendaPorPedDoc(pDadosParametros,vecTItemPedido))
    { // itens do pedido
        int nElemVecTItemPedido = vecTItemPedido.size();

        for ( int i=0; i < nElemVecTItemPedido; i++ )
        {
            xml_g->createTag("Itens");
               xml_g->addItem(XML_ITEMPEDIDO_NRITEMPEDIDO,vecTItemPedido.at(i).getIdItemPedido());
               xml_g->addItem(XML_ITEMPEDIDO_IDITEMPEDIDO,vecTItemPedido.at(i).getCdProduto());
               xml_g->addItem(XML_ITEMPEDIDO_DSITEMPEDIDO,vecTItemPedido.at(i).getDsItemPedido());
               xml_g->addItem(XML_ITEMPEDIDO_VLITEM,vecTItemPedido.at(i).getVlItem());
               xml_g->addItem(XML_ITEMPEDIDO_QTITEM,vecTItemPedido.at(i).getQtItem());
               xml_g->addItem(XML_ITEMPEDIDO_OBSERVACAOITEM,vecTItemPedido.at(i).getObservacaoItem());
            xml_g->closeTag(); // Itens
        }
    }
    else if(RET_NAO_EXISTEM_DADOS != cItemPedidopc.proCbuscarLstItemPedidoPorPedDoc(pDadosParametros,vecTItemPedido))
    { // itens do pedido
        int nElemVecTItemPedido = vecTItemPedido.size();

        for ( int i=0; i < nElemVecTItemPedido; i++ )
        {
            xml_g->createTag("Itens");
               xml_g->addItem(XML_ITEMPEDIDO_NRITEMPEDIDO,vecTItemPedido.at(i).getIdItemPedido());
               xml_g->addItem(XML_ITEMPEDIDO_IDITEMPEDIDO,vecTItemPedido.at(i).getCdProduto());
               xml_g->addItem(XML_ITEMPEDIDO_DSITEMPEDIDO,vecTItemPedido.at(i).getDsItemPedido());
               xml_g->addItem(XML_ITEMPEDIDO_VLITEM,vecTItemPedido.at(i).getVlItem());
               xml_g->addItem(XML_ITEMPEDIDO_QTITEM,vecTItemPedido.at(i).getQtItem());
               xml_g->addItem(XML_ITEMPEDIDO_OBSERVACAOITEM,vecTItemPedido.at(i).getObservacaoItem());
            xml_g->closeTag(); // Itens
        }
    }
    else
    { // se não tem itens de pedido, busca itens de ordem de venda com itens de pedido

        CItemOrdemVendapc cItemOrdemVendapc;
        VEC_TITEMORDEMVENDASAP vecTItemOrdemVendaSap;

        cItemOrdemVendapc.proCbuscarLstItemOrVdaSapPorPedDoc(pDadosParametros,vecTItemOrdemVendaSap);

        int nElemVecTItemOrdemVendaSap = vecTItemOrdemVendaSap.size();

        for ( int i=0; i < nElemVecTItemOrdemVendaSap; i++ )
        {
            xml_g->createTag("Itens");
               xml_g->addItem(XML_ITEMPEDIDO_NRITEMPEDIDO,vecTItemOrdemVendaSap.at(i).getIdItemOrdem());
               xml_g->addItem(XML_ITEMPEDIDO_IDITEMPEDIDO,vecTItemOrdemVendaSap.at(i).getCdProduto());
               xml_g->addItem(XML_ITEMPEDIDO_DSITEMPEDIDO,vecTItemOrdemVendaSap.at(i).getDsItemOrdem());
               xml_g->addItem(XML_ITEMPEDIDO_VLITEM,vecTItemOrdemVendaSap.at(i).getVlItem());
               xml_g->addItem(XML_ITEMPEDIDO_QTITEM,vecTItemOrdemVendaSap.at(i).getQtItem());
               xml_g->addItem(XML_ITEMPEDIDO_OBSERVACAOITEM,vecTItemOrdemVendaSap.at(i).getObservacaoItem());
            xml_g->closeTag(); // Itens
        }
    }

    ULOG_END("CItemPedido::buscarLstPedidosPorDocumento");
}

//==============================================================================
void CItemPedido::buscarLstItemPedidoPorPedDocComp(struct DadosParametros *pDadosParametros)
{
    ULOG_START("CItemPedido::buscarLstItemPedidoPorPedDocComp");

    if ( !xml_g )
    {
        throw new TuxException("24E9999","Área de saída não disponível.");
    }

    if ( !pDadosParametros )
    {
        throw new TuxException("24E9999","Parâmetro de entrada invalido!");
    }

    CItemPedidopc cItemPedidopc;
    VEC_TITEMPEDIDO vecTItemPedido;

    cItemPedidopc.proCbuscarLstItemPedidoPorPedDoc(pDadosParametros,vecTItemPedido);

    int nElemVecTItemPedido = vecTItemPedido.size();

    if ( nElemVecTItemPedido )
    {
        for ( int i=0; i < nElemVecTItemPedido; i++ )
        {
            xml_g->createTag("Itens");
               xml_g->addItem(XML_ITEMPEDIDO_NRITEMPEDIDO,vecTItemPedido.at(i).getIdItemPedido());
               xml_g->addItem(XML_ITEMPEDIDO_IDITEMPEDIDO,vecTItemPedido.at(i).getCdProduto());
               xml_g->addItem(XML_ITEMPEDIDO_DSITEMPEDIDO,vecTItemPedido.at(i).getDsItemPedido());
               xml_g->addItem(XML_ITEMPEDIDO_VLITEM,vecTItemPedido.at(i).getVlItem());
               xml_g->addItem(XML_ITEMPEDIDO_QTITEM,vecTItemPedido.at(i).getQtItem());
               xml_g->addItem(XML_ITEMPEDIDO_OBSERVACAOITEM,vecTItemPedido.at(i).getObservacaoItem());
            xml_g->closeTag(); // Itens
        }
    }
    else
    { // retorna o VO sem dados.
        xml_g->createTag("Itens");
           xml_g->addItem(XML_ITEMPEDIDO_NRITEMPEDIDO," ");
           xml_g->addItem(XML_ITEMPEDIDO_IDITEMPEDIDO," ");
           xml_g->addItem(XML_ITEMPEDIDO_DSITEMPEDIDO," ");
           xml_g->addItem(XML_ITEMPEDIDO_VLITEM," ");
           xml_g->addItem(XML_ITEMPEDIDO_QTITEM," ");
           xml_g->addItem(XML_ITEMPEDIDO_OBSERVACAOITEM," ");
        xml_g->closeTag(); // Itens
    }

    ULOG_END("CItemPedido::buscarLstItemPedidoPorPedDocComp");
}

