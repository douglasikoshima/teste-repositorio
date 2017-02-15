/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas de negócio com a tabela EtapaPedido sendo a principal
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/

#include "../include/EtapaPedido.h"
#include "../include/EtapaPedidopc.h"
#include "../include/Pedidopc.h"

//==============================================================================
void CEtapaPedido::buscarLstEtapaPedidoPorPedDoc(struct DadosParametros *pDadosParametros)
{
    ULOG_START("CEtapaPedido::buscarLstEtapaPedidoPorPedDoc");

    if ( !xml_g )
    {
        throw new TuxException("24E9999","Área de saída não disponível.");
    }

    if ( !pDadosParametros )
    {
        throw new TuxException("24E9999","Parâmetro de entrada invalido!");
    }

    CEtapaPedidopc cetapapedidopc;
    VEC_TETAPAPEDIDO vecTEtapaPedido;
    
    // busca as etapas do pedido se nenhuma existir simplesmente não gera no XML de saída
    cetapapedidopc.proCbuscarLstEtapaPedidoPorPedDoc(pDadosParametros,vecTEtapaPedido);

    int nElemVecTEtapaPedido = vecTEtapaPedido.size();

    for ( int i=0; i < nElemVecTEtapaPedido; i++ )
    {
        xml_g->createTag("DadosEtapa");
            xml_g->addItem(XML_ETAPAPEDIDO_DTETAPAPEDIDO,vecTEtapaPedido.at(i).getDtEtapaPedido());
            xml_g->addItem(XML_ETAPAPEDIDO_DTATUALIZACAOETAPA,vecTEtapaPedido.at(i).getDtAtualizacaoEtapa());
            xml_g->addItem(XML_ETAPAPEDIDO_IDSISTEMAORIGEMETAPA,vecTEtapaPedido.at(i).getNmSistemaOrigem());
            xml_g->addItem(XML_ETAPAPEDIDO_DSTIPOOCORRENCIA,vecTEtapaPedido.at(i).getDsTipoOcorrencia());
            xml_g->addItem(XML_ETAPAPEDIDO_LOGINUSUARIO,vecTEtapaPedido.at(i).getLoginUsuario());
            xml_g->addItem(XML_ETAPAPEDIDO_DSETAPA,vecTEtapaPedido.at(i).getDsEtapa());
            xml_g->addItem(XML_ETAPAPEDIDO_OBSERVACAOETAPAPEDIDO,vecTEtapaPedido.at(i).getObservacaoEtapaPedido());
        xml_g->closeTag(); // DadosEtapa
    }

    ULOG_END("CEtapaPedido::buscarLstEtapaPedidoPorPedDoc");
}

//==============================================================================
int CEtapaPedido::buscarLstPedidosPorDocumento(struct DadosParametros *pDadosParametros)
{
    ULOG_START("CEtapaPedido::buscarLstPedidosPorDocumento");

    if ( !xml_g )
    {
        throw new TuxException("24E9999","Área de saída não disponível.");
    }

    if ( !pDadosParametros )
    {
        throw new TuxException("24E9999","Parâmetro de entrada invalido!");
    }

    CEtapaPedidopc cetapapedidopc;
    VEC_TETAPAPEDIDOPORDOC vecTEtapaPedidoPorDoc;

    int retorno = cetapapedidopc.proCbuscarLstPedidosPorDocumento(pDadosParametros,vecTEtapaPedidoPorDoc);

    if ( retorno == RET_NAO_EXISTEM_DADOS )
    {
        ULOG_END("CEtapaPedido::buscarLstPedidosPorDocumento ==> Não existem dados");
        return retorno;
    }

    xml_g->addItem(XML_PEDIDO_NMNOMERAZAOSOCIAL,pDadosParametros->nmPessoa);
    xml_g->addItem(XML_PEDIDO_CDCPFCNPJ,pDadosParametros->nrDoc);
    xml_g->addItem(XML_DSTIPODOCUMENTO,pDadosParametros->tpDoc);

    int nElemVecTEtapaPedidoPorDoc = vecTEtapaPedidoPorDoc.size();
    int de = pDadosParametros->pagina*9;
    int ate = de + 9;

    retorno = RET_SUCESSO;

    for ( int i = de; i < ate; i++ )
    {
        if ( i >= nElemVecTEtapaPedidoPorDoc )
        {
            retorno = RET_FIM_LEITURA;
            break;
        }

        xml_g->createTag("ListaPedidoAparelhosVO");
            xml_g->addItem(XML_SISTEMAORIGEM_NMSISTEMAORIGEM,vecTEtapaPedidoPorDoc.at(i).getNmSistemaOrigem());
            xml_g->addItem(XML_SISTEMAORIGEM_IDSISTEMAORIGEM,"");
            xml_g->addItem(XML_UF_SGUF,vecTEtapaPedidoPorDoc.at(i).getSgUF());
            xml_g->addItem(XML_PEDIDO_DTPEDIDO,vecTEtapaPedidoPorDoc.at(i).getDtAberturaPedido());
            xml_g->addItem(XML_PEDIDO_IDPEDIDO,vecTEtapaPedidoPorDoc.at(i).getIdPedido());
            xml_g->addItem(XML_ORDEMVENDA_NRORDEMVENDA,vecTEtapaPedidoPorDoc.at(i).getNrOrdemVenda());
            xml_g->addItem(XML_ORDEMVENDA_NRFORNECIMENTO,vecTEtapaPedidoPorDoc.at(i).getNrFornecimento());
            xml_g->addItem(XML_ORDEMVENDA_NRPICKING,vecTEtapaPedidoPorDoc.at(i).getNrPicking());
            xml_g->addItem(XML_ORDEMVENDA_NRNOTAFISCAL,vecTEtapaPedidoPorDoc.at(i).getNrNotaFiscal());
            xml_g->addItem(XML_ORDEMVENDA_DTFORNECIMENTO,vecTEtapaPedidoPorDoc.at(i).getDtFornecimento());
            xml_g->addItem(XML_ORDEMVENDA_DTPICKING,vecTEtapaPedidoPorDoc.at(i).getDtPicking());
            xml_g->addItem(XML_ORDEMVENDA_DTNOTAFISCAL,vecTEtapaPedidoPorDoc.at(i).getDtNotaFiscal());
            xml_g->addItem(XML_ORDEMVENDA_ENDERECOENTREGA,vecTEtapaPedidoPorDoc.at(i).getEnderecoEntrega());
            xml_g->addItem(XML_ORDEMVENDA_DTPREVISAOENTREGA,vecTEtapaPedidoPorDoc.at(i).getDtPrevisaoEntrega());
            xml_g->addItem(XML_ORDEMVENDA_SGSTATUSORDEMVENDA,vecTEtapaPedidoPorDoc.at(i).getStatusEntrega());
            xml_g->addItem(XML_ORDEMVENDA_INCORROMPIDO,vecTEtapaPedidoPorDoc.at(i).getInCorrompido());
        xml_g->closeTag(); // ListaPedidoAparelhosVO
    }

    xml_g->createTag("Paginacao");
        xml_g->addItem("hasNext",retorno == RET_FIM_LEITURA?"0":"1");
        xml_g->addItem("pageNumber",pDadosParametros->pagina);
    xml_g->closeTag(); // Paginacao

    ULOG_END("CEtapaPedido::buscarLstPedidosPorDocumento");

    return RET_SUCESSO;
}
