/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Comparador entre o pedido e a ordem de venda
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:17 $
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
#include "../../../negocio/clienteCmm/include/ItemOrdemVenda.h"
#include "../../../negocio/clienteCmm/include/GlobalPedido.h"
#else
#include "../../negocio/clienteCmm/include/Funcoes.h"
#include "../../negocio/clienteCmm/include/Messages.h"
#include "../../negocio/clienteCmm/include/Pedido.h"
#include "../../negocio/clienteCmm/include/ItemPedido.h"
#include "../../negocio/clienteCmm/include/EtapaPedido.h"
#include "../../negocio/clienteCmm/include/ProdutoEntrega.h"
#include "../../negocio/clienteCmm/include/OrdemVenda.h"
#include "../../negocio/clienteCmm/include/ItemOrdemVenda.h"
#include "../../negocio/clienteCmm/include/GlobalPedido.h"
#endif

//==============================================================================
// Prot�tipos
//==============================================================================
void Executar(DadosParametros *pDadosParametros,XMLGen* xml_g);
void CarregarDados(DadosParametros *pDadosParametros,DOMNode* dnode);

//==============================================================================
DECLARE_TUXEDO_SERVICE(TRCKCMPPEDIDO);

//==============================================================================
// Main
//==============================================================================
void implTRCKCMPPEDIDO::Execute(DOMNode* dnode, XMLGen* xml_g)
{
    ULOG_START( "implTRCKCMPPEDIDO::Execute()" );

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
        if ( !pMessage ) pMessage = "Erro n�o identificado.";

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

    ULOG_END( "implTRCKCMPPEDIDO::Execute()" );
}

//==============================================================================
// Implementa��o do neg�cio do servi�o
//==============================================================================
void Executar(DadosParametros *pDadosParametros,XMLGen* xml_g)
{
    CPedido pedido(xml_g);
    CItemPedido citempedido(xml_g);
    COrdemVenda cordemvenda(xml_g);
    CItemOrdemVenda citemordemvenda(xml_g);
    CEtapaPedido cetapapedido(xml_g);

    xml_g->createTag("TrackingAparelhosVO");
    xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
    xml_g->createTag("ComparaTrackingAparelhoVO");

    //===============================================================
    // ORIGEM
    //===============================================================
    xml_g->createTag("origem");

    //===============================================================
    // Dados do pedido
    pedido.buscarDetPedidoPorDocumento(pDadosParametros);

    //===============================================================
    // Itens do pedido
    xml_g->createTag("ListaItens");
    citempedido.buscarLstItemPedidoPorPedDocComp(pDadosParametros);

    xml_g->closeTag(); // ListaItens

    xml_g->closeTag(); // origem

    //===============================================================
    // SAP
    //===============================================================
    xml_g->createTag("sap");

    //===============================================================
    // Ordens de Venda Associadas ao Pedido x Produtos Entregues
    cordemvenda.buscarOrdVdaComparacao(pDadosParametros);

    //===============================================================
    // Itens da ordem de venda
    citemordemvenda.buscarLstItemOrVdaPorPedDoc(pDadosParametros);

    xml_g->closeTag(); // sap

    xml_g->closeTag(); // ComparaTrackingAparelhoVO

    xml_g->addItem("tpRetorno","C");

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
            throw new TuxException(SERVICE "I0000","N�mero do documento vazio");
        }
    }
    else
    {
        throw new TuxException(SERVICE "I0000","N�mero do documento n�o informado.");
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
        ULOG("<idSistemaOrigem>=n�o informado");
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
        ULOG("<nmSistemaOrigem>=n�o informado");
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
        ULOG("<nrPedido>=n�o informado");
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
        ULOG("<nrOrdemVenda>=n�o informado");
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
        ULOG("<nrFornecimento>=n�o informado");
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
        ULOG("<nrPicking>=n�o informado");
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
        ULOG("<nrNotaFiscal>=n�o informado");
    }

    ULOG_END("CarregarDados()");
}
