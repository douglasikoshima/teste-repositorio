/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Pesquisa Lista de Pedidos Por Numero de Documento
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:35 $
 **/

#include <tuxfw.h>

#ifdef WIN32
#include "../../../negocio/clienteCmm/include/Funcoes.h"
#include "../../../negocio/clienteCmm/include/Messages.h"
#include "../../../negocio/clienteCmm/include/Pedido.h"
#include "../../../negocio/clienteCmm/include/ItemPedido.h"
#include "../../../negocio/clienteCmm/include/EtapaPedido.h"
#include "../../../negocio/clienteCmm/include/ProdutoEntrega.h"
#include "../../../negocio/clienteCmm/include/OrdemVenda.h"
#include "../../../negocio/clienteCmm/include/GlobalPedido.h"
#else
#include "../../negocio/clienteCmm/include/Funcoes.h"
#include "../../negocio/clienteCmm/include/Messages.h"
#include "../../negocio/clienteCmm/include/Pedido.h"
#include "../../negocio/clienteCmm/include/ItemPedido.h"
#include "../../negocio/clienteCmm/include/EtapaPedido.h"
#include "../../negocio/clienteCmm/include/ProdutoEntrega.h"
#include "../../negocio/clienteCmm/include/OrdemVenda.h"
#include "../../negocio/clienteCmm/include/GlobalPedido.h"
#endif

//==============================================================================
// Protótipos
//==============================================================================
void Executar(DadosParametros *pDadosParametros,XMLGen* xml_g);
void CarregarDados(DadosParametros *pDadosParametros,DOMNode* dnode);

//==============================================================================
DECLARE_TUXEDO_SERVICE(TRCKDADPEDIDO);

//==============================================================================
// Main
//==============================================================================
void implTRCKDADPEDIDO::Execute(DOMNode* dnode, XMLGen* xml_g)
{
    ULOG_START( "implTRCKDADPEDIDO::Execute()" );

    try
    {
        DadosParametros dadosParametros;

        memset(&dadosParametros,0,sizeof(dadosParametros));

        CarregarDados(&dadosParametros,dnode);

        Executar(&dadosParametros,xml_g);
    }
    catch (TuxBasicOraException *ex)
    {
        char codErro[25];
        sprintf(codErro,"%sE%04d",SERVICE,ex->eCode<0 ?ex->eCode*(-1):ex->eCode);

        xml_g->clearAndDestroy();

        xml_g->createTag("TrackingAparelhosVO");
        xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
            xml_g->addItem("msgErro",ex->pMsg);
            xml_g->addItem("codErro",codErro);
            xml_g->addItem("tpRetorno","E");
        xml_g->closeTag(); // TrackingAparelhosVO

        delete ex;
    }
    catch(TuxException *pTux)
    {
        char *pCode = pTux->getCode();
        if ( !pCode ) pCode = SERVICE "E9999";

        char *pMessage = pTux->getMessage();
        if ( !pMessage ) pMessage = "Erro não identificado.";

        xml_g->clearAndDestroy();

        xml_g->createTag("TrackingAparelhosVO");
        xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
            xml_g->addItem("msgErro",pMessage);
            xml_g->addItem("codErro",pCode);
            xml_g->addItem("tpRetorno","E");
        xml_g->closeTag(); // TrackingAparelhosVO

        delete pTux;
    }
    catch(...)
    {
        xml_g->clearAndDestroy();

        xml_g->createTag("TrackingAparelhosVO");
        xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
            xml_g->addItem("msgErro","Erro desconhecido");
            xml_g->addItem("codErro",SERVICE "E9999");
            xml_g->addItem("tpRetorno","E");
        xml_g->closeTag(); // TrackingAparelhosVO
    }

    INFORMATION(NRO_OK);

    setStatusCode(sNrMsg, MSG_OK);

    ULOG_END( "implTRCKDADPEDIDO::Execute()" );
}

//==============================================================================
// Implementação do negócio do serviço
//==============================================================================
void Executar(DadosParametros *pDadosParametros,XMLGen* xml_g)
{
    CPedido pedido(xml_g);
    CItemPedido citempedido(xml_g);
    COrdemVenda cordemvenda(xml_g);
    CEtapaPedido cetapapedido(xml_g);

    xml_g->createTag("TrackingAparelhosVO");
    xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
    xml_g->createTag("DetalheTrackingAparelhoVO");

    //===============================================================
    // Dados do pedido ou ordem de venda
    xml_g->createTag("DadosPedido");
    pedido.buscarDetPedidoOrdemPorDocumento(pDadosParametros);

    //===============================================================
    // Itens do pedido
    xml_g->createTag("ListaItens");
    citempedido.buscarLstItemPedidoPorPedDoc(pDadosParametros);
    xml_g->closeTag(); // ListaItens

    xml_g->closeTag(); // DadosPedido

    //===============================================================
    // Ordens de Venda Associadas ao Pedido x Produtos Entregues
    xml_g->createTag("ListaDadosOrdem");

    xml_g->createTag("DadosOrdem");
    cordemvenda.buscarOrdVendaPorPedDoc(pDadosParametros);

    cetapapedido.buscarLstEtapaPedidoPorPedDoc(pDadosParametros);

    xml_g->closeTag(); // DadosOrdem

    xml_g->closeTag(); // ListaDadosOrdem

    xml_g->closeTag(); // DetalheTrackingAparelhoVO

    xml_g->addItem("tpRetorno","D");

    xml_g->closeTag(); // TrackingAparelhosVO
}

//============================================================================
void CarregarDados(DadosParametros *pDadosParametros,DOMNode* dnode)
{
    ULOG_START("CarregarDados()");

    char *p;
    TuxHelper tx;

    //===============================================================
    //==<nrDoc>==
    if (p=tx.walkTree( dnode, "nrDoc", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->nrDoc,p);
        XMLString::release(&p);

        if ( strlen(pDadosParametros->nrDoc) < 1 )
        {
            throw new TuxException(SERVICE "I0000","Número do documento vazio");
        }
    }
    else
    {
        throw new TuxException(SERVICE "I0000","Número do documento não informado.");
    }
    ULOG("<nrDoc>='%s'",pDadosParametros->nrDoc);

    //===============================================================
    //==<idSistemaOrigem>==
    if (p=tx.walkTree( dnode, "idSistemaOrigem", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->idSistemaOrigem,p);
        XMLString::release(&p);
        ULOG("<idSistemaOrigem>='%s'",pDadosParametros->idSistemaOrigem);
    }
    else
    {
        ULOG("<idSistemaOrigem>=não informado");
    }

    //===============================================================
    //==<nmSistemaOrigem>==
    if (p=tx.walkTree( dnode, "nmSistemaOrigem", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->nmSistemaOrigem,p);
        XMLString::release(&p);
        ULOG("<nmSistemaOrigem>='%s'",pDadosParametros->nmSistemaOrigem);
    }
    else
    {
        ULOG("<nmSistemaOrigem>=não informado");
    }

    ULOG("<nmSistemaOrigem>='%s'",pDadosParametros->nmSistemaOrigem);

    //===============================================================
    //==<nrPedido>==
    if (p=tx.walkTree( dnode, "nrPedido", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->nrPedido,p);
        XMLString::release(&p);
        ULOG("<nrPedido>='%s'",pDadosParametros->nrPedido);
    }
    else
    {
        ULOG("<nrPedido>=não informado");
    }

    //===============================================================
    //==<nrOrdemVenda>==
    if (p=tx.walkTree( dnode, "nrOrdemVenda", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->nrOrdemVenda,p);
        XMLString::release(&p);
        ULOG("<nrOrdemVenda>='%s'",pDadosParametros->nrOrdemVenda);
    }
    else
    {
        ULOG("<nrOrdemVenda>=não informado");
    }

    //===============================================================
    //==<nrFornecimento>==
    if (p=tx.walkTree( dnode, "nrFornecimento", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->nrFornecimento,p);
        XMLString::release(&p);
        ULOG("<nrFornecimento>='%s'",pDadosParametros->nrFornecimento);
    }
    else
    {
        ULOG("<nrFornecimento>=não informado");
    }

    //===============================================================
    //==<nrPicking>==
    if (p=tx.walkTree( dnode, "nrPicking", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->nrPicking,p);
        XMLString::release(&p);
        ULOG("<nrPicking>='%s'",pDadosParametros->nrPicking);
    }
    else
    {
        ULOG("<nrPicking>=não informado");
    }

    //===============================================================
    //==<nrNotaFiscal>==
    if (p=tx.walkTree( dnode, "nrNotaFiscal", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->nrNotaFiscal,p);
        SAFE_STRNCPY(pDadosParametros->idNotaFiscal,p);
        XMLString::release(&p);
        ULOG("<nrNotaFiscal>='%s'",pDadosParametros->nrNotaFiscal);
    }
    else
    {
        ULOG("<nrNotaFiscal>=não informado");
    }

    //===============================================================
    //==<sgUF>==
    if (p=tx.walkTree( dnode, "sgUF", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->sgUF,p);
        SAFE_STRNCPY(pDadosParametros->sgUF,p);
        XMLString::release(&p);
        ULOG("<sgUF>='%s'",pDadosParametros->sgUF);
    }
    else
    {
        ULOG("<sgUF>=não informado");
    }

    ULOG_END("CarregarDados()");
}
