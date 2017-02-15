/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Pesquisa Lista de Pedidos Por Numero de Documento
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:16 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>

using namespace std;

#include <tuxfw.h>

#ifdef WIN32
#include "../../../negocio/clienteCmm/include/Funcoes.h"
#include "../../../negocio/clienteCmm/include/Messages.h"
#include "../../../negocio/clienteCmm/include/Pedido.h"
#include "../../../negocio/clienteCmm/include/Documentopc.h"
#include "../../../negocio/clienteCmm/include/EtapaPedido.h"
#include "../../../negocio/clienteCmm/include/ParametrosPedidopc.h"
#else
#include "../../negocio/clienteCmm/include/Funcoes.h"
#include "../../negocio/clienteCmm/include/Messages.h"
#include "../../negocio/clienteCmm/include/Pedido.h"
#include "../../negocio/clienteCmm/include/Documentopc.h"
#include "../../negocio/clienteCmm/include/EtapaPedido.h"
#include "../../negocio/clienteCmm/include/ParametrosPedidopc.h"
#endif

//==============================================================================
// Protótipos
//==============================================================================
void Executar(DadosParametros *pDadosParametros,XMLGen* xml_g);
void CarregarDados(DadosParametros *pDadosParametros,DOMNode* dnode);
int parseListaNmSOrigemOperLogistico(DadosParametros *pDadosParametros);
int parseListaURLOperLogistico(DadosParametros *pDadosParametros);

//==============================================================================
DECLARE_TUXEDO_SERVICE(TRCKLSTPEDIDO);

//==============================================================================
// Main
//==============================================================================
void implTRCKLSTPEDIDO::Execute(DOMNode* dnode, XMLGen* xml_g)
{
    ULOG_START( "implTRCKLSTPEDIDO::Execute()" );

    try
    {
        DadosParametros dadosParametros;

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

    ULOG_END( "implTRCKLSTPEDIDO::Execute()" );
}

//==============================================================================
// Implementação do negócio do serviço
//==============================================================================
void Executar(DadosParametros *pDadosParametros,XMLGen* xml_g)
{
    CEtapaPedido cEtapaPedido(xml_g);
    CDocumentopc cdocumentopc;
    TDocumento tDocumento;
    CParamPedidopc cParamPedidopc;

    //==========================================================================
    // Documento
    //==========================================================================

    strcpy(pDadosParametros->nmPessoa,"Não Cliente");

    if ( pDadosParametros->idPessoaCliente[0] && pDadosParametros->nrDoc[0] == 0 )
    {
        if ( false == cdocumentopc.proCBuscaCPFCNPJPorIdPessoa(pDadosParametros->idPessoaCliente
                                                              ,&tDocumento) )
        {
            throw new TuxException(SERVICE "I0000","Para visualizar os dados referentes ao tracking "
                                                   "de aparelhos, é necessário que a pessoa seja "
                                                   "cadastrada no Vivo Net, e tenha como tipo de "
                                                   "documento CPF ou CNPJ cadastrado");
        }

        // Número e tipo do documento
        SAFE_STRNCPY(pDadosParametros->nmPessoa,tDocumento.szNmPessoa);
        SAFE_STRNCPY(pDadosParametros->nrDoc,tDocumento.szNrDocumento);
        SAFE_STRNCPY(pDadosParametros->tpDoc,tDocumento.szDsTipoDocumento);
        strcat(pDadosParametros->tpDoc,":");
    }
    else if ( pDadosParametros->nrDoc[0] == 0 || pDadosParametros->tpDoc[0] == 0 )
    {
        throw new TuxException(SERVICE "I0001","Número ou tipo do documento não fornecidos ");
    }

    //==========================================================================
    // Parametros de Operadores Logísticos
    //==========================================================================

    if ( cParamPedidopc.proCbuscarLstNmSOrigemOperLogistico(pDadosParametros) == RET_NAO_EXISTEM_DADOS_NMSO )
    {
        throw new TuxException(SERVICE "I0001","Parametros com os nomes de operadores logísticos não carregado.");
    }

    if ( cParamPedidopc.proCbuscarLstURLOperLogistico(pDadosParametros) == RET_NAO_EXISTEM_DADOS_URL )
    {
        throw new TuxException(SERVICE "I0002","Lista de parâmetros de URLs de operadores logísticos "
                                               "não encontrada.");
    }

    int iParseListaNmSOrigemOperLogistico = parseListaNmSOrigemOperLogistico(pDadosParametros);
    int iParseListaURLOperLogistico = parseListaURLOperLogistico(pDadosParametros);

    if ( iParseListaNmSOrigemOperLogistico == -1 )
    {
        throw new TuxException(SERVICE "I0003","Lista de parâmetros de sistemas origem de operadores logísticos "
                                               "com excesso de elementos.");
    }

    if ( iParseListaURLOperLogistico == -1 )
    {
        throw new TuxException(SERVICE "I0004","Lista de parâmetros de URL's de operadores logísticos "
                                               "com excesso de elementos.");
    }

    if ( iParseListaNmSOrigemOperLogistico != iParseListaURLOperLogistico )
    {
        throw new TuxException(SERVICE "I0005","Falha de sincronismo entre as "
                                               "listas de operadores logísticos.");
    }

    //==========================================================================
    // Processa a lista
    //==========================================================================
    xml_g->createTag("TrackingAparelhosVO");
    xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
    xml_g->createTag("ListaTrackingAparelhoVO");

    int retorno = cEtapaPedido.buscarLstPedidosPorDocumento(pDadosParametros);

    if ( retorno == RET_NAO_EXISTEM_DADOS )
    {
            xml_g->addItem(XML_PEDIDO_NMNOMERAZAOSOCIAL,pDadosParametros->nmPessoa);
            xml_g->addItem(XML_PEDIDO_CDCPFCNPJ,pDadosParametros->nrDoc);
            xml_g->addItem(XML_DSTIPODOCUMENTO,pDadosParametros->tpDoc);
    }

    xml_g->closeTag(); // ListaTrackingAparelhoVO

    if ( retorno == RET_NAO_EXISTEM_DADOS )
    {
            xml_g->addItem("msgErro","Não foram encontrados pedidos para o CPF ou CNPJ pesquisado.");
            //xml_g->addItem("msgErro","Lista de sistemas origem de operadores logísticos não encontrada.");
            xml_g->addItem("codErro",SERVICE"E9999");
            xml_g->addItem("tpRetorno","E");
    }
    else
    {
        xml_g->addItem("tpRetorno","L");
    }

    xml_g->closeTag(); // TrackingAparelhosVO
}

//============================================================================
void CarregarDados(DadosParametros *pDadosParametros,DOMNode* dnode)
{
    ULOG_START("CarregarDados()");

    char *p;
    TuxHelper tx;

    //===============================================================
    //==<idPessoaCliente>==
    pDadosParametros->idPessoaCliente[0] = 0;
    if (p=tx.walkTree( dnode, "idPessoaCliente", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->idPessoaCliente,p);
        XMLString::release(&p);

        if ( strlen(pDadosParametros->idPessoaCliente) < 1 )
        {
            throw new TuxException(SERVICE "I0000","Número do cliente vazio");
        }
    }
    //else
    //{
    //    throw new TuxException(SERVICE "I0000","Número do cliente não informado.");
    //}
    ULOG("<idPessoaCliente>='%s'",pDadosParametros->idPessoaCliente);

    //===============================================================
    //==<nrDocumento>==
    pDadosParametros->nrDoc[0] = 0;
    if (p=tx.walkTree( dnode, "nrDocumento", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->nrDoc,p);
        XMLString::release(&p);
    }
    ULOG("<nrDocumento>='%s'",pDadosParametros->nrDoc);

    //===============================================================
    //==<dsTipoDocumento>==
    pDadosParametros->tpDoc[0] = 0;
    if (p=tx.walkTree( dnode, "dsTipoDocumento", 0 ),p) 
    {
        SAFE_STRNCPY(pDadosParametros->tpDoc,p);
        strcat(pDadosParametros->tpDoc,":");
        XMLString::release(&p);
    }
    ULOG("<dsTipoDocumento>='%s'",pDadosParametros->tpDoc);

    //===============================================================
    //==<pageNumber>==
    if (p=tx.walkTree( dnode, "pageNumber", 0 ),p) 
    {
        if ( 0 == *p )
        {
            XMLString::release(&p);
            throw new TuxException(SERVICE "I0000","Número da página vazio");
        }
        
        pDadosParametros->pagina = atoi(p);
        XMLString::release(&p);
    }
    else
    {
        throw new TuxException(SERVICE "I0000","Número da página não informado.");
    }
    ULOG("<pageNumber>='%d'",pDadosParametros->pagina);

    ULOG_END("CarregarDados()");
}

int parseListaNmSOrigemOperLogistico(DadosParametros *pDadosParametros)
{
    char delimitador[] = ",";
    char *result = strtok(pDadosParametros->lstNmSOrigemOpeLog,delimitador);
    int it=0;
    int itMax = sizeof(pDadosParametros->operadoresLogisticos) / sizeof(OperadoresLogisticos);

    while( result )
    {
        if ( it >= itMax ) { return -1; }
        SAFE_STRNCPY(pDadosParametros->operadoresLogisticos[it].nmSistemaOrigem,result);
        result = strtok(NULL,delimitador);
        it++;
    }

    return it;
}

int parseListaURLOperLogistico(DadosParametros *pDadosParametros)
{
    char delimitador[] = ",";
    char *result = strtok(pDadosParametros->lstURLOpeLog,delimitador);
    int it=0;
    int itMax = sizeof(pDadosParametros->operadoresLogisticos) / sizeof(OperadoresLogisticos);

    while( result )
    {
        if ( it >= itMax ) { return -1; }
        SAFE_STRNCPY(pDadosParametros->operadoresLogisticos[it].url,result);
        result = strtok(NULL,delimitador);
        it++;
    }

    return it;
}
