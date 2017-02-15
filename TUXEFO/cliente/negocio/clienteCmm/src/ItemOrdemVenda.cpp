/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas de negócio com a tabela ItemOrdemVenda sendo a principal
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/

#include "../include/ItemOrdemVenda.h"
#include "../include/ItemOrdemVendapc.h"
#include "../include/PedidoNegocio.h"

//==============================================================================
void CItemOrdemVenda::buscarLstItemOrVdaPorPedDoc(struct DadosParametros *pDadosParametros)
{
    ULOG_START("CItemOrdemVenda::buscarLstItemOrVdaPorPedDoc");

    if ( !xml_g )
    {
        throw new TuxException("04E9999","Área de saída não disponível.");
    }

    if ( !pDadosParametros )
    {
        throw new TuxException("04E9999","Parâmetro de entrada invalido!");
    }

    CItemOrdemVendapc citemordemvendapc;
    VEC_TITEMORDEMVENDA vecTItemOrdemVenda;

    citemordemvendapc.proCbuscarLstItemOrVdaPorPedDoc(pDadosParametros,vecTItemOrdemVenda);

    int nElemVecTItemOrdemVenda = vecTItemOrdemVenda.size();

    xml_g->createTag("ListaItens");

    if ( nElemVecTItemOrdemVenda )
    {
        for ( int i=0; i < nElemVecTItemOrdemVenda; i++ )
        {
            xml_g->createTag("Itens");
                xml_g->addItem(XML_ITEMOVENDA_IDITEMPEDIDO,vecTItemOrdemVenda.at(i).getNrItemOrdem());
                xml_g->addItem(XML_ITEMOVENDA_CDPRODUTO,vecTItemOrdemVenda.at(i).getCdProduto());
                xml_g->addItem(XML_ITEMOVENDA_DSITEMPEDIDO,vecTItemOrdemVenda.at(i).getDsItemOrdem());
                xml_g->addItem(XML_ITEMOVENDA_VLITEM,vecTItemOrdemVenda.at(i).getVlItem());
                xml_g->addItem(XML_ITEMOVENDA_QTITEM,vecTItemOrdemVenda.at(i).getQtItem());
                xml_g->addItem(XML_ITEMOVENDA_OBSERVACAOITEM,vecTItemOrdemVenda.at(i).getObservacaoItem());
            xml_g->closeTag(); // Itens
        }
    }
    else
    {
        xml_g->createTag("Itens");
            xml_g->addItem(XML_ITEMOVENDA_IDITEMPEDIDO," ");
            xml_g->addItem(XML_ITEMOVENDA_CDPRODUTO," ");
            xml_g->addItem(XML_ITEMOVENDA_DSITEMPEDIDO," ");
            xml_g->addItem(XML_ITEMOVENDA_VLITEM," ");
            xml_g->addItem(XML_ITEMOVENDA_QTITEM," ");
            xml_g->addItem(XML_ITEMOVENDA_OBSERVACAOITEM," ");
        xml_g->closeTag(); // Itens
    }

    xml_g->closeTag(); // ListaItens

    ULOG_END("CItemOrdemVenda::buscarLstItemOrVdaPorPedDoc");
}
