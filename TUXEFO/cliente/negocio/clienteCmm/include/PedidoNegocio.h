/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Dados básicos das tabelas de Pedidos
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __PEDIDONEGOCIO_H__
#define __PEDIDONEGOCIO_H__

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <vector>

using namespace std;

#include "GlobalPedido.h"

//==============================================================================
// Lista de Pedidos x Ordens de Vendas
//==============================================================================
struct TPedidoComOrdem {

    char nmSistemaOrigem[LEN_NM_SISTEMA_ORIGEM+LEN_EOS];
    char idSistemaOrigem[LEN_ID_SISTEMA_ORIGEM+LEN_EOS];
    char sgUF[LEN_SGUF+LEN_EOS];
    char dtAberturaPedido[LEN_DTABERTURAPEDIDO+LEN_EOS];
    char idPedido[LEN_IDPEDIDO+LEN_EOS];
    char nmNomeRazaoSocial[LEN_NMNOMERAZAOSOCIAL+LEN_EOS];
    char nmGestor[LEN_NMGESTOR+LEN_EOS];
    char nrTelefoneGestor[LEN_NRTELEFONEGESTOR+LEN_EOS];
    char cdCpfCnpj[LEN_CDCPFCNPJ+LEN_EOS];
    char idOrdemVenda[LEN_IDORDEMVENDA+LEN_EOS];
    char nrOrdemVenda[LEN_NRORDEMVENDA+LEN_EOS];
    char nrFornecimento[LEN_NRFORNECIMENTO+LEN_EOS];
    char nrPicking[LEN_NRPICKING+LEN_EOS];
    char nrNotaFiscal[LEN_NRNOTAFISCAL+LEN_EOS];
    char statusOrdemVenda[LEN_STATUSORDEMVENDA+LEN_EOS];

    TPedidoComOrdem()
    {
        memset(this->nmSistemaOrigem,0,sizeof(this->nmSistemaOrigem));
        memset(this->idSistemaOrigem,0,sizeof(this->idSistemaOrigem));
        memset(this->sgUF,0,sizeof(this->sgUF));
        memset(this->dtAberturaPedido,0,sizeof(this->dtAberturaPedido));
        memset(this->idPedido,0,sizeof(this->idPedido));
        memset(this->nmNomeRazaoSocial,0,sizeof(this->nmNomeRazaoSocial));
        memset(this->nmGestor,0,sizeof(this->nmGestor));
        memset(this->nrTelefoneGestor,0,sizeof(this->nrTelefoneGestor));
        memset(this->cdCpfCnpj,0,sizeof(this->cdCpfCnpj));
        memset(this->nrOrdemVenda,0,sizeof(this->nrOrdemVenda));
        memset(this->nrFornecimento,0,sizeof(this->nrFornecimento));
        memset(this->nrPicking,0,sizeof(this->nrPicking));
        memset(this->nrNotaFiscal,0,sizeof(this->nrNotaFiscal));
        memset(this->statusOrdemVenda,0,sizeof(this->statusOrdemVenda));
    }

    void setNmSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->nmSistemaOrigem,(char*)valor); }
    char *getNmSistemaOrigem() { return this->nmSistemaOrigem; }

    void setIdSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->idSistemaOrigem,(char*)valor); }
    char *getIdSistemaOrigem() { return this->idSistemaOrigem; }

    void setSgUF(unsigned char *valor) { SAFE_STRNCPY(this->sgUF,(char*)valor); }
    char *getSgUF() { return this->sgUF; }

    void setDtAberturaPedido(unsigned char *valor) { SAFE_STRNCPY(this->dtAberturaPedido,(char*)valor); }
    char *getDtAberturaPedido() { return this->dtAberturaPedido; }

    void setNmNomeRazaoSocial(unsigned char *valor) { SAFE_STRNCPY(this->nmNomeRazaoSocial,(char*)valor); }
    char *getNmNomeRazaoSocial() { return this->nmNomeRazaoSocial; }

    void setNmGestor(unsigned char *valor) { SAFE_STRNCPY(this->nmGestor,(char*)valor); }
    char *getNmGestor() { return this->nmGestor; }

    void setNrTelefoneGestor(unsigned char *valor) { SAFE_STRNCPY(this->nrTelefoneGestor,(char*)valor); }
    char *getNrTelefoneGestor() { return this->nrTelefoneGestor; }

    void setCdCpfCnpj(unsigned char *valor) { SAFE_STRNCPY(this->cdCpfCnpj,(char*)valor); }
    char *getCdCpfCnpj() { return this->cdCpfCnpj; }

    void setIdPedido(unsigned char *valor) { SAFE_STRNCPY(this->idPedido,(char*)valor); }
    char *getIdPedido() { return this->idPedido; }

    void setNrOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->nrOrdemVenda,(char*)valor); }
    char *getNrOrdemVenda() { return this->nrOrdemVenda; }

    void setIdOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->idOrdemVenda,(char*)valor); }
    char *getIdOrdemVenda() { return this->idOrdemVenda; }

    void setNrFornecimento(unsigned char *valor) { SAFE_STRNCPY(this->nrFornecimento,(char*)valor); }
    char *getNrFornecimento() { return this->nrFornecimento; }

    void setNrPicking(unsigned char *valor) { SAFE_STRNCPY(this->nrPicking,(char*)valor); }
    char *getNrPicking() { return this->nrPicking; }

    void setNrNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->nrNotaFiscal,(char*)valor); }
    char *getNrNotaFiscal() { return this->nrNotaFiscal; }

    void setStatusOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->statusOrdemVenda,(char*)valor); }
    char *getStatusOrdemVenda() { return this->statusOrdemVenda; }
};

//==============================================================================
// Dados do Pedido
//==============================================================================
struct TPedido {

    char cdAgenteFilial[LEN_CDAGENTEFILIAL+LEN_EOS];
    char cdCpfCnpj[LEN_CDCPFCNPJ+LEN_EOS];
    char dsCanalOrigem[LEN_DSCANALORIGEM+LEN_EOS];
    char dtAberturaPedido[LEN_DTABERTURAPEDIDO+LEN_EOS];
    char EnderecoEntrega[LEN_ENDERECOENTREGA+LEN_EOS];
    char FormaPagamento[LEN_FORMAPAGAMENTO+LEN_EOS];
    char idPedido[LEN_IDPEDIDO+LEN_EOS];
    char idSistemaOrigem[LEN_IDSISTEMAORIGEM+LEN_EOS];
    char idTipoLinha[LEN_IDTIPOLINHA+LEN_EOS];
    char observacaoPedido[LEN_OBSERVACAOETAPA+LEN_EOS]; // dependendo da situação a obs da etapa do pedido é gravada neste campo.
    char qtPontoResgatado[LEN_QTPONTORESGATADO+LEN_EOS];
    char sgUF[LEN_SGUF+LEN_EOS];
    char vlParcela[LEN_VLPARCELA+LEN_EOS];
    char vlTotalPedido[LEN_VLTOTALPEDIDO+LEN_EOS];
    char nmNomeRazaoSocial[LEN_NMNOMERAZAOSOCIAL+LEN_EOS];
    char nmGestor[LEN_NMGESTOR+LEN_EOS];
    char nrTelefoneGestor[LEN_NRTELEFONEGESTOR+LEN_EOS];
    char dsTipoLinha[LEN_DSTIPOLINHA+LEN_EOS];
    char nmSistemaOrigem[LEN_NM_SISTEMA_ORIGEM+LEN_EOS];
    char dataPedidoDDMMYYYY[LEN_DTABERTURAPEDIDO+LEN_EOS];
    char idOrdemVenda[LEN_IDORDEMVENDA+LEN_EOS];

    TPedido()
    {
        memset(this->cdAgenteFilial,0,sizeof(this->cdAgenteFilial));
        memset(this->cdCpfCnpj,0,sizeof(this->cdCpfCnpj));
        memset(this->dsCanalOrigem,0,sizeof(this->dsCanalOrigem));
        memset(this->dtAberturaPedido,0,sizeof(this->dtAberturaPedido));
        memset(this->EnderecoEntrega,0,sizeof(this->EnderecoEntrega));
        memset(this->FormaPagamento,0,sizeof(this->FormaPagamento));
        memset(this->idPedido,0,sizeof(this->idPedido));
        memset(this->idSistemaOrigem,0,sizeof(this->idSistemaOrigem));
        memset(this->idTipoLinha,0,sizeof(this->idTipoLinha));
        memset(this->observacaoPedido,0,sizeof(this->observacaoPedido));
        memset(this->qtPontoResgatado,0,sizeof(this->qtPontoResgatado));
        memset(this->sgUF,0,sizeof(this->sgUF));
        memset(this->vlParcela,0,sizeof(this->vlParcela));
        memset(this->vlTotalPedido,0,sizeof(this->vlTotalPedido));
        memset(this->nmNomeRazaoSocial,0,sizeof(this->nmNomeRazaoSocial));
        memset(this->nmGestor,0,sizeof(this->nmGestor));
        memset(this->nrTelefoneGestor,0,sizeof(this->nrTelefoneGestor));
        memset(this->dsTipoLinha,0,sizeof(this->dsTipoLinha));
        memset(this->nmSistemaOrigem,0,sizeof(this->nmSistemaOrigem));
        memset(this->dataPedidoDDMMYYYY,0,sizeof(this->dataPedidoDDMMYYYY));
        memset(this->idOrdemVenda,0,sizeof(this->idOrdemVenda));
    }

    void setCdAgenteFilial(unsigned char *valor) { SAFE_STRNCPY(this->cdAgenteFilial,(char*)valor); }
    char *getCdAgenteFilial() { return this->cdAgenteFilial; }

    void setCdCpfCnpj(unsigned char *valor) { SAFE_STRNCPY(this->cdCpfCnpj,(char*)valor); }
    char *getCdCpfCnpj() { return this->cdCpfCnpj; }

    void setDsCanalOrigem(unsigned char *valor) { SAFE_STRNCPY(this->dsCanalOrigem,(char*)valor); }
    char *getDsCanalOrigem() { return this->dsCanalOrigem; }

    void setDtAberturaPedido(unsigned char *valor) { SAFE_STRNCPY(this->dtAberturaPedido,(char*)valor); }
    char *getDtAberturaPedido() { return this->dtAberturaPedido; }

    void setEnderecoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->EnderecoEntrega,(char*)valor); }
    char *getEnderecoEntrega() { return this->EnderecoEntrega; }

    void setFormaPagamento(unsigned char *valor) { SAFE_STRNCPY(this->FormaPagamento,(char*)valor); }
    char *getFormaPagamento() { return this->FormaPagamento; }

    void setIdPedido(unsigned char *valor) { SAFE_STRNCPY(this->idPedido,(char*)valor); }
    char *getIdPedido() { return this->idPedido; }

    void setIdSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->idSistemaOrigem,(char*)valor); }
    char *getIdSistemaOrigem() { return this->idSistemaOrigem; }

    void setIdTipoLinha(unsigned char *valor) { SAFE_STRNCPY(this->idTipoLinha,(char*)valor); }
    char *getIdTipoLinha() { return this->idTipoLinha; }

    void setObservacaoPedido(unsigned char *valor) { SAFE_STRNCPY(this->observacaoPedido,(char*)valor); }
    char *getObservacaoPedido() { return this->observacaoPedido; }

    void setQtPontoResgatado(unsigned char *valor) { SAFE_STRNCPY(this->qtPontoResgatado,(char*)valor); }
    char *getQtPontoResgatado() { return this->qtPontoResgatado; }

    void setSgUF(unsigned char *valor) { SAFE_STRNCPY(this->sgUF,(char*)valor); }
    char *getSgUF() { return this->sgUF; }

    void setVlParcela(unsigned char *valor) { SAFE_STRNCPY(this->vlParcela,(char*)valor); }
    char *getVlParcela() { return this->vlParcela; }

    void setVlTotalPedido(unsigned char *valor) { SAFE_STRNCPY(this->vlTotalPedido,(char*)valor); }
    char *getVlTotalPedido() { return this->vlTotalPedido; }

    void setNmNomeRazaoSocial(unsigned char *valor) { SAFE_STRNCPY(this->nmNomeRazaoSocial,(char*)valor); }
    char *getNmNomeRazaoSocial() { return this->nmNomeRazaoSocial; }

    void setNmGestor(unsigned char *valor) { SAFE_STRNCPY(this->nmGestor,(char*)valor); }
    char *getNmGestor() { return this->nmGestor; }

    void setNrTelefoneGestor(unsigned char *valor) { SAFE_STRNCPY(this->nrTelefoneGestor,(char*)valor); }
    char *getNrTelefoneGestor() { return this->nrTelefoneGestor; }

    void setDsTipoLinha(unsigned char *valor) { SAFE_STRNCPY(this->dsTipoLinha,(char*)valor); }
    char *getDsTipoLinha() { return this->dsTipoLinha; }

    void setNmSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->nmSistemaOrigem,(char*)valor); }
    char *getNmSistemaOrigem() { return this->nmSistemaOrigem; }

    char *getDtAberturaPedidoDDMMYYYY() 
    {
        memcpy(dataPedidoDDMMYYYY,this->dtAberturaPedido,10);
        dataPedidoDDMMYYYY[10] = 0;
        return this->dataPedidoDDMMYYYY;
    }

    void setIdOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->idOrdemVenda,(char*)valor); }
    char *getIdOrdemVenda() { return this->idOrdemVenda; }

};

//==============================================================================
// Itens do Pedido
//==============================================================================
struct TItemPedido {

    char nrItem[LEN_NUMBER+LEN_EOS];
    char idItemPedido[LEN_IDITEMPEDIDO+LEN_EOS];
    char cdCpfCnpj[LEN_CDCPFCNPJ+LEN_EOS];
    char idPedido[LEN_IDPEDIDO+LEN_EOS];
    char idSistemaOrigem[LEN_IDSISTEMAORIGEM+LEN_EOS];
    char dsItemPedido[LEN_DSITEMPEDIDO+LEN_EOS];
    char cdProduto[LEN_CDPRODUTO+LEN_EOS];
    char qtItem[LEN_QTITEM+LEN_EOS];
    char vlItem[LEN_VLITEM+LEN_EOS];
    char nrLinha[LEN_NRLINHAORDEMVENDA+LEN_EOS];
    char planoLinha[LEN_PLANOLINHAPEDIDO+LEN_EOS];
    char tecnologiaLinha[LEN_TECNOLOGIALINHA+LEN_EOS];
    char observacaoItem[LEN_OBSERVACAOITEM+LEN_EOS];

    TItemPedido()
    {
        memset(this->nrItem,0,sizeof(this->nrItem));
        memset(this->idItemPedido,0,sizeof(this->idItemPedido));
        memset(this->cdCpfCnpj,0,sizeof(this->cdCpfCnpj));
        memset(this->idPedido,0,sizeof(this->idPedido));
        memset(this->idSistemaOrigem,0,sizeof(this->idSistemaOrigem));
        memset(this->dsItemPedido,0,sizeof(this->dsItemPedido));
        memset(this->cdProduto,0,sizeof(this->cdProduto));
        memset(this->qtItem,0,sizeof(this->qtItem));
        memset(this->vlItem,0,sizeof(this->vlItem));
        memset(this->nrLinha,0,sizeof(this->nrLinha));
        memset(this->planoLinha,0,sizeof(this->planoLinha));
        memset(this->tecnologiaLinha,0,sizeof(this->tecnologiaLinha));
        memset(this->observacaoItem,0,sizeof(this->observacaoItem));
    }

    void setNrItem(unsigned char *valor) { SAFE_STRNCPY(this->nrItem,(char*)valor); }
    char *getNrItem() { return this->nrItem; }

    void setIdItemPedido(unsigned char *valor) { SAFE_STRNCPY(this->idItemPedido,(char*)valor); }
    char *getIdItemPedido() { return this->idItemPedido; }

    void setCdCpfCnpj(unsigned char *valor) { SAFE_STRNCPY(this->cdCpfCnpj,(char*)valor); }
    char *getCdCpfCnpj() { return this->cdCpfCnpj; }

    void setIdPedido(unsigned char *valor) { SAFE_STRNCPY(this->idPedido,(char*)valor); }
    char *getIdPedido() { return this->idPedido; }

    void setIdSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->idSistemaOrigem,(char*)valor); }
    char *getIdSistemaOrigem() { return this->idSistemaOrigem; }

    void setDsItemPedido(unsigned char *valor) { SAFE_STRNCPY(this->dsItemPedido,(char*)valor); }
    char *getDsItemPedido() { return this->dsItemPedido; }

    void setCdProduto(unsigned char *valor) { SAFE_STRNCPY(this->cdProduto,(char*)valor); }
    char *getCdProduto() { return this->cdProduto; }

    void setQtItem(unsigned char *valor) { SAFE_STRNCPY(this->qtItem,(char*)valor); }
    char *getQtItem() { return this->qtItem; }

    void setVlItem(unsigned char *valor) { SAFE_STRNCPY(this->vlItem,(char*)valor); }
    char *getVlItem() { return this->vlItem; }

    void setNrLinha(unsigned char *valor) { SAFE_STRNCPY(this->nrLinha,(char*)valor); }
    char *getNrLinha() { return this->nrLinha; }

    void setPlanoLinha(unsigned char *valor) { SAFE_STRNCPY(this->planoLinha,(char*)valor); }
    char *getPlanoLinha() { return this->planoLinha; }

    void setTecnologiaLinha(unsigned char *valor) { SAFE_STRNCPY(this->tecnologiaLinha,(char*)valor); }
    char *getTecnologiaLinha() { return this->tecnologiaLinha; }

    void setObservacaoItem(unsigned char *valor) { SAFE_STRNCPY(this->observacaoItem,(char*)valor); }
    char *getObservacaoItem() { return this->observacaoItem; }
};

//==============================================================================
// Dados de entrega
//==============================================================================
struct TProdutoEntrega {

    char cdCpfCnpj[LEN_CDCPFCNPJ+LEN_EOS];
    char dsCanalOrigem[LEN_DSCANALORIGEM+LEN_EOS];
    char dsComprovanteEntrega[LEN_DSCOMPROVANTEENTREGA+LEN_EOS];
    char dsTipoEntrega[LEN_DSTIPOENTREGA+LEN_EOS];
    char dtNotaFiscal[LEN_DTNOTAFISCAL+LEN_EOS];
    char dtPrevisaoEntrega[LEN_DTPREVISAOENTREGA+LEN_EOS];
    char enderecoEntrega[LEN_ENDERECOENTREGA+LEN_EOS];
    char idProdutoEntrega[LEN_IDPRODUTOENTREGA+LEN_EOS];
    char idSistemaOrigem[LEN_IDSISTEMAORIGEM+LEN_EOS];
    char nmRecebedorPedido[LEN_NMRECEBEDORPEDIDO+LEN_EOS];
    char nrDocumentoRecebedor[LEN_NRDOCUMENTORECEBEDOR+LEN_EOS];
    char nrNotaFiscal[LEN_NRNOTAFISCAL+LEN_EOS];
    char nrOrdemVenda[LEN_NRORDEMVENDA+LEN_EOS];
    char observacaoEntrega[LEN_OBSERVACAOENTREGA+LEN_EOS];
    char serieNotaFiscal[LEN_SERIENOTAFISCAL+LEN_EOS];
    char statusEntrega[LEN_STATUSENTREGA+LEN_EOS];
    char ufNotaFiscal[LEN_UFNOTAFISCAL+LEN_EOS];
    char dsMotivoEntrega[LEN_DSMOTIVOENTREGA+LEN_EOS];

    TProdutoEntrega()
    {
        memset(this->cdCpfCnpj,0,sizeof(this->cdCpfCnpj));
        memset(this->dsCanalOrigem,0,sizeof(this->dsCanalOrigem));
        memset(this->dsComprovanteEntrega,0,sizeof(this->dsComprovanteEntrega));
        memset(this->dsTipoEntrega,0,sizeof(this->dsTipoEntrega));
        memset(this->dtNotaFiscal,0,sizeof(this->dtNotaFiscal));
        memset(this->dtPrevisaoEntrega,0,sizeof(this->dtPrevisaoEntrega));
        memset(this->enderecoEntrega,0,sizeof(this->enderecoEntrega));
        memset(this->idProdutoEntrega,0,sizeof(this->idProdutoEntrega));
        memset(this->idSistemaOrigem,0,sizeof(this->idSistemaOrigem));
        memset(this->nmRecebedorPedido,0,sizeof(this->nmRecebedorPedido));
        memset(this->nrDocumentoRecebedor,0,sizeof(this->nrDocumentoRecebedor));
        memset(this->nrNotaFiscal,0,sizeof(this->nrNotaFiscal));
        memset(this->nrOrdemVenda,0,sizeof(this->nrOrdemVenda));
        memset(this->observacaoEntrega,0,sizeof(this->observacaoEntrega));
        memset(this->serieNotaFiscal,0,sizeof(this->serieNotaFiscal));
        memset(this->statusEntrega,0,sizeof(this->statusEntrega));
        memset(this->ufNotaFiscal,0,sizeof(this->ufNotaFiscal));
        memset(this->dsMotivoEntrega,0,sizeof(this->dsMotivoEntrega));
    }

    void setIdProdutoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->idProdutoEntrega,(char*)valor); }
    char *getIdProdutoEntrega() { return this->idProdutoEntrega; }

    void setCdCpfCnpj(unsigned char *valor) { SAFE_STRNCPY(this->cdCpfCnpj,(char*)valor); }
    char *getCdCpfCnpj() { return this->cdCpfCnpj; }

    void setNrNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->nrNotaFiscal,(char*)valor); }
    char *getNrNotaFiscal() { return this->nrNotaFiscal; }

    void setNrOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->nrOrdemVenda,(char*)valor); }
    char *getNrOrdemVenda() { return this->nrOrdemVenda; }

    void setDsCanalOrigem(unsigned char *valor) { SAFE_STRNCPY(this->dsCanalOrigem,(char*)valor); }
    char *getDsCanalOrigem() { return this->dsCanalOrigem; }

    void setUfNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->ufNotaFiscal,(char*)valor); }
    char *getUfNotaFiscal() { return this->ufNotaFiscal; }

    void setDtNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->dtNotaFiscal,(char*)valor); }
    char *getDtNotaFiscal() { return this->dtNotaFiscal; }

    void setDsTipoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->dsTipoEntrega,(char*)valor); }
    char *getDsTipoEntrega() { return this->dsTipoEntrega; }

    void setStatusEntrega(unsigned char *valor) { SAFE_STRNCPY(this->statusEntrega,(char*)valor); }
    char *getStatusEntrega() { return this->statusEntrega; }

    void setEnderecoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->enderecoEntrega,(char*)valor); }
    char *getEnderecoEntrega() { return this->enderecoEntrega; }

    void setDtPrevisaoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->dtPrevisaoEntrega,(char*)valor); }
    char *getDtPrevisaoEntrega() { return this->dtPrevisaoEntrega; }

    void setNmRecebedorPedido(unsigned char *valor) { SAFE_STRNCPY(this->nmRecebedorPedido,(char*)valor); }
    char *getNmRecebedorPedido() { return this->nmRecebedorPedido; }

    void setNrDocumentoRecebedor(unsigned char *valor) { SAFE_STRNCPY(this->nrDocumentoRecebedor,(char*)valor); }
    char *getNrDocumentoRecebedor() { return this->nrDocumentoRecebedor; }

    void setDsComprovanteEntrega(unsigned char *valor) { SAFE_STRNCPY(this->dsComprovanteEntrega,(char*)valor); }
    char *getDsComprovanteEntrega() { return this->dsComprovanteEntrega; }

    void setIdSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->idSistemaOrigem,(char*)valor); }
    char *getIdSistemaOrigem() { return this->idSistemaOrigem; }

    void setObservacaoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->observacaoEntrega,(char*)valor); }
    char *getObservacaoEntrega() { return this->observacaoEntrega; }

    void setSerieNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->serieNotaFiscal,(char*)valor); }
    char *getSerieNotaFiscal() { return this->serieNotaFiscal; }

    void setDsMotivoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->dsMotivoEntrega,(char*)valor); }
    char *getDsMotivoEntrega() { return this->dsMotivoEntrega; }
};

//==============================================================================
// Notas fiscais associadas a ordens de venda
//==============================================================================
struct TOrdemNotaFiscal {

    char cdCpfCnpj[LEN_CDCPFCNPJ+LEN_EOS];
    char idNotaFiscal[LEN_IDNOTAFISCAL+LEN_EOS];
    char idOrdemVenda[LEN_IDORDEMVENDA+LEN_EOS];
    char serieNotaFiscal[LEN_SERIENOTAFISCAL+LEN_EOS];
    char vlParcela[LEN_VLPARCELA+LEN_EOS];
    char vlTotalNotaFiscal[LEN_VLTOTALNOTAFISCAL+LEN_EOS];

    TOrdemNotaFiscal()
    {
        memset(this->idOrdemVenda,0,sizeof(this->idOrdemVenda));
        memset(this->idNotaFiscal,0,sizeof(this->idNotaFiscal));
        memset(this->serieNotaFiscal,0,sizeof(this->serieNotaFiscal));
        memset(this->vlParcela,0,sizeof(this->vlParcela));
        memset(this->vlTotalNotaFiscal,0,sizeof(this->vlTotalNotaFiscal));
        memset(this->cdCpfCnpj,0,sizeof(this->cdCpfCnpj));
    }

    void setCdCpfCnpj(unsigned char *valor) { SAFE_STRNCPY(this->cdCpfCnpj,(char*)valor); }
    char *getCdCpfCnpj() { return this->cdCpfCnpj; }

    void setIdNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->idNotaFiscal,(char*)valor); }
    char *getIdNotaFiscal() { return this->idNotaFiscal; }

    void setIdOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->idOrdemVenda,(char*)valor); }
    char *getIdOrdemVenda() { return this->idOrdemVenda; }

    void setSerieNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->serieNotaFiscal,(char*)valor); }
    char *getSerieNotaFiscal() { return this->serieNotaFiscal; }

    void setVlParcela(unsigned char *valor) { SAFE_STRNCPY(this->vlParcela,(char*)valor); }
    char *getVlParcela() { return this->vlParcela; }

    void setVlTotalNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->vlTotalNotaFiscal,(char*)valor); }
    char *getVlTotalNotaFiscal() { return this->vlTotalNotaFiscal; }

};

//==============================================================================
// Ordem de Venda
//==============================================================================
struct TOrdemVenda {

    char canalVenda[LEN_CANALVENDA+LEN_EOS];
    char cdCpfCnpj[LEN_CDCPFCNPJ+LEN_EOS];
    char dsMotivoOrdem[LEN_DSMOTIVOORDEM+LEN_EOS];
    char dsTipoOrdemVenda[LEN_DSTIPOORDEMVENDA+LEN_EOS];
    char dtAberturaPedido[LEN_DTABERTURAPEDIDO+LEN_EOS];
    char enderecoEntrega[LEN_ENDERECOENTREGA+LEN_EOS];
    char escritorioVenda[LEN_ESCRITORIOVENDA+LEN_EOS];
    char formaPagamento[LEN_FORMAPAGAMENTO+LEN_EOS];
    char grupoVenda[LEN_GRUPOVENDA+LEN_EOS];
    char idOrdemVenda[LEN_IDORDEMVENDA+LEN_EOS];
    char idSistemaOrigem[LEN_IDSISTEMAORIGEM+LEN_EOS];
    char inCancelada[LEN_INCANCELADA+LEN_EOS];

    char dsMotivoBloqueio[LEN_DSMOTIVOBLOQUEIO+LEN_EOS];
    char tpMotivoBloqueio[LEN_TPMOTIVOBLOQUEIO+LEN_EOS];

    char nmResponsavelOrdemVenda[LEN_NMRESPONSAVELORDEMVENDA+LEN_EOS];
    char normaExpedicao[LEN_NORMAEXPEDICAO+LEN_EOS];
    char nrFornecimento[LEN_NRFORNECIMENTO+LEN_EOS];

    char dtFornecimento[LEN_DTFORNECIMENTO+LEN_EOS];
    char dtPicking[LEN_DTPICKING+LEN_EOS];
    char dtConfirmacaoPicking[LEN_DTCONFIRMACAOPICKING+LEN_EOS];
    char dtSaidaMercadoria[LEN_DTSAIDAMERCADORIA+LEN_EOS];
    char dtNotaFiscal[LEN_DTNOTAFISCAL+LEN_EOS];

    char nrOrdemVenda[LEN_NRORDEMVENDA+LEN_EOS];
    char nrPedido[LEN_NRPEDIDO+LEN_EOS];
    char nrPicking[LEN_NRPICKING+LEN_EOS];
    char statusOrdemVenda[LEN_STATUSORDEMVENDA+LEN_EOS];
    char ufOrdem[LEN_UFORDEM+LEN_EOS];
    char vlTotalPedido[LEN_VLTOTALPEDIDO+LEN_EOS];
    char observacaoEtapaPedido[LEN_OBSERVACAOETAPA+LEN_EOS];
    char flagComparacao[1+LEN_EOS];
    char nmSistemaOrigem[LEN_NM_SISTEMA_ORIGEM+LEN_EOS];
    char inCorrompido[1+LEN_EOS];

    vector<TProdutoEntrega> vecTProdutoEntrega; // produtos entregues para a Ordem de Venda
    vector<TOrdemNotaFiscal> vecTOrdemNotaFiscal; // Notas fiscais atreladas a Ordem de Venda

    TOrdemVenda()
    {
        memset(this->canalVenda,0,sizeof(canalVenda));
        memset(this->cdCpfCnpj,0,sizeof(cdCpfCnpj));
        memset(this->dsMotivoOrdem,0,sizeof(dsMotivoOrdem));
        memset(this->dsTipoOrdemVenda,0,sizeof(dsTipoOrdemVenda));
        memset(this->dtAberturaPedido,0,sizeof(dtAberturaPedido));
        memset(this->enderecoEntrega,0,sizeof(enderecoEntrega));
        memset(this->escritorioVenda,0,sizeof(escritorioVenda));
        memset(this->flagComparacao,0,sizeof(flagComparacao));
        memset(this->formaPagamento,0,sizeof(formaPagamento));
        memset(this->grupoVenda,0,sizeof(grupoVenda));
        memset(this->idOrdemVenda,0,sizeof(idOrdemVenda));
        memset(this->idSistemaOrigem,0,sizeof(idSistemaOrigem));
        memset(this->inCancelada,0,sizeof(inCancelada));

        memset(this->dsMotivoBloqueio,0,sizeof(dsMotivoBloqueio));
        memset(this->tpMotivoBloqueio,0,sizeof(tpMotivoBloqueio));

        memset(this->nmResponsavelOrdemVenda,0,sizeof(nmResponsavelOrdemVenda));
        memset(this->nmSistemaOrigem,0,sizeof(nmSistemaOrigem));
        memset(this->normaExpedicao,0,sizeof(normaExpedicao));
        memset(this->nrFornecimento,0,sizeof(nrFornecimento));

        memset(this->dtFornecimento,0,sizeof(dtFornecimento));
        memset(this->dtPicking,0,sizeof(dtPicking));
        memset(this->dtConfirmacaoPicking,0,sizeof(dtConfirmacaoPicking));
        memset(this->dtSaidaMercadoria,0,sizeof(dtSaidaMercadoria));
        memset(this->dtNotaFiscal,0,sizeof(dtNotaFiscal));

        memset(this->nrOrdemVenda,0,sizeof(nrOrdemVenda));
        memset(this->nrPedido,0,sizeof(nrPedido));
        memset(this->nrPicking,0,sizeof(nrPicking));
        memset(this->observacaoEtapaPedido,0,sizeof(observacaoEtapaPedido));
        memset(this->statusOrdemVenda,0,sizeof(statusOrdemVenda));
        memset(this->ufOrdem,0,sizeof(ufOrdem));
        memset(this->vlTotalPedido,0,sizeof(vlTotalPedido));
        memset(this->inCorrompido,0,sizeof(inCorrompido));
    }

    void setCanalVenda(unsigned char *valor) { SAFE_STRNCPY(this->canalVenda,(char*)valor); }
    char *getCanalVenda() { return this->canalVenda; }

    void setCdCpfCnpj(unsigned char *valor) { SAFE_STRNCPY(this->cdCpfCnpj,(char*)valor); }
    char *getCdCpfCnpj() { return this->cdCpfCnpj; }

    void setDsMotivoOrdem(unsigned char *valor) { SAFE_STRNCPY(this->dsMotivoOrdem,(char*)valor); }
    char *getDsMotivoOrdem() { return this->dsMotivoOrdem; }

    void setDsTipoOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->dsTipoOrdemVenda,(char*)valor); }
    char *getDsTipoOrdemVenda() { return this->dsTipoOrdemVenda; }

    void setDtAberturaPedido(unsigned char *valor) { SAFE_STRNCPY(this->dtAberturaPedido,(char*)valor); }
    char *getDtAberturaPedido() { return this->dtAberturaPedido; }

    void setEnderecoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->enderecoEntrega,(char*)valor); }
    char *getEnderecoEntrega() { return this->enderecoEntrega; }

    void setEscritorioVenda(unsigned char *valor) { SAFE_STRNCPY(this->escritorioVenda,(char*)valor); }
    char *getEscritorioVenda() { return this->escritorioVenda; }

    void setFlagComparacao(unsigned char *valor) { SAFE_STRNCPY(this->flagComparacao,(char*)valor); }
    char *getFlagComparacao() { return this->flagComparacao; }

    void setFormaPagamento(unsigned char *valor) { SAFE_STRNCPY(this->formaPagamento,(char*)valor); }
    char *getFormaPagamento() { return this->formaPagamento; }

    void setGrupoVenda(unsigned char *valor) { SAFE_STRNCPY(this->grupoVenda,(char*)valor); }
    char *getGrupoVenda() { return this->grupoVenda; }

    void setIdOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->idOrdemVenda,(char*)valor); }
    char *getIdOrdemVenda() { return this->idOrdemVenda; }

    void setIdSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->idSistemaOrigem,(char*)valor); }
    char *getIdSistemaOrigem() { return this->idSistemaOrigem; }

    void setInCancelada(unsigned char *valor) { SAFE_STRNCPY(this->inCancelada,(char*)valor); }
    char *getInCancelada() { return this->inCancelada; }



    void setDsMotivoBloqueio(unsigned char *valor) { SAFE_STRNCPY(this->dsMotivoBloqueio,(char*)valor); }
    char *getDsMotivoBloqueio() { return this->dsMotivoBloqueio; }

    void setTpMotivoBloqueio(unsigned char *valor) { SAFE_STRNCPY(this->tpMotivoBloqueio,(char*)valor); }
    char *getTpMotivoBloqueio() { return this->tpMotivoBloqueio; }



    void setNmResponsavelOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->nmResponsavelOrdemVenda,(char*)valor); }
    char *getNmResponsavelOrdemVenda() { return this->nmResponsavelOrdemVenda; }

    void setNmSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->nmSistemaOrigem,(char*)valor); }
    char *getNmSistemaOrigem() { return this->nmSistemaOrigem; }

    void setNormaExpedicao(unsigned char *valor) { SAFE_STRNCPY(this->normaExpedicao,(char*)valor); }
    char *getNormaExpedicao() { return this->normaExpedicao; }

    void setNrFornecimento(unsigned char *valor) { SAFE_STRNCPY(this->nrFornecimento,(char*)valor); }
    char *getNrFornecimento() { return this->nrFornecimento; }



    void setDtFornecimento(unsigned char *valor) { SAFE_STRNCPY(this->dtFornecimento,(char*)valor); }
    char *getDtFornecimento() { return this->dtFornecimento; }

    void setDtPicking(unsigned char *valor) { SAFE_STRNCPY(this->dtPicking,(char*)valor); }
    char *getDtPicking() { return this->dtPicking; }

    void setDtConfirmacaoPicking(unsigned char *valor) { SAFE_STRNCPY(this->dtConfirmacaoPicking,(char*)valor); }
    char *getDtConfirmacaoPicking() { return this->dtConfirmacaoPicking; }

    void setDtSaidaMercadoria(unsigned char *valor) { SAFE_STRNCPY(this->dtSaidaMercadoria,(char*)valor); }
    char *getDtSaidaMercadoria() { return this->dtSaidaMercadoria; }

    void setDtNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->dtNotaFiscal,(char*)valor); }
    char *getDtNotaFiscal() { return this->dtNotaFiscal; }



    void setNrOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->nrOrdemVenda,(char*)valor); }
    char *getNrOrdemVenda() { return this->nrOrdemVenda; }

    void setNrPedido(unsigned char *valor) { SAFE_STRNCPY(this->nrPedido,(char*)valor); }
    char *getNrPedido() { return this->nrPedido; }

    void setNrPicking(unsigned char *valor) { SAFE_STRNCPY(this->nrPicking,(char*)valor); }
    char *getNrPicking() { return this->nrPicking; }

    void setObservacaoEtapaPedido(unsigned char *valor) { SAFE_STRNCPY(this->observacaoEtapaPedido,(char*)valor); }
    char *getObservacaoEtapaPedido() { return this->observacaoEtapaPedido; }

    void setStatusOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->statusOrdemVenda,(char*)valor); }
    char *getStatusOrdemVenda() { return this->statusOrdemVenda; }

    void setUfOrdem(unsigned char *valor) { SAFE_STRNCPY(this->ufOrdem,(char*)valor); }
    char *getUfOrdem() { return this->ufOrdem; }

    void setVlTotalPedido(unsigned char *valor) { SAFE_STRNCPY(this->vlTotalPedido,(char*)valor); }
    char *getVlTotalPedido() { return this->vlTotalPedido; }

    void setInCorrompido(unsigned char *valor) { SAFE_STRNCPY(this->inCorrompido,(char*)valor); }
    char *getInCorrompido() { return this->inCorrompido; }
};

//==============================================================================
// Itens de Ordem de Venda
//==============================================================================
struct TItemOrdemVenda {

    char idOrdemVenda[LEN_IDORDEMVENDA+LEN_EOS];
    char idItemOrdem[LEN_IDORDEMVENDA+LEN_EOS];
    char dsItemOrdem[LEN_DSITEMORDEM+LEN_EOS];
    char cdProduto[LEN_CDPRODUTO+LEN_EOS];
    char qtItem[LEN_QTITEM+LEN_EOS];
    char vlItem[LEN_VLITEM+LEN_EOS];
    char nrLinha[LEN_NRLINHAORDEMVENDA+LEN_EOS];
    char planoLinha[LEN_PLANOLINHAPEDIDO+LEN_EOS];
    char tecnologiaLinha[LEN_TECNOLOGIALINHA+LEN_EOS];
    char observacaoItem[LEN_OBSERVACAOITEM+LEN_EOS];
    char statusItem[LEN_STATUSITEM+LEN_EOS];

    TItemOrdemVenda()
    {
        memset(this->idOrdemVenda,0,sizeof(this->idOrdemVenda));
        memset(this->idItemOrdem,0,sizeof(this->idItemOrdem));
        memset(this->dsItemOrdem,0,sizeof(this->dsItemOrdem));
        memset(this->cdProduto,0,sizeof(this->cdProduto));
        memset(this->qtItem,0,sizeof(this->qtItem));
        memset(this->vlItem,0,sizeof(this->vlItem));
        memset(this->nrLinha,0,sizeof(this->nrLinha));
        memset(this->planoLinha,0,sizeof(this->planoLinha));
        memset(this->tecnologiaLinha,0,sizeof(this->tecnologiaLinha));
        memset(this->observacaoItem,0,sizeof(this->observacaoItem));
        memset(this->statusItem,0,sizeof(this->statusItem));
    }

    void setIdOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->idOrdemVenda,(char*)valor); }
    char *getIdOrdemVenda() { return this->idOrdemVenda; }

    void setNrItemOrdem(unsigned char *valor) { SAFE_STRNCPY(this->idItemOrdem,(char*)valor); }
    char *getNrItemOrdem() { return this->idItemOrdem; }

    void setDsItemOrdem(unsigned char *valor) { SAFE_STRNCPY(this->dsItemOrdem,(char*)valor); }
    char *getDsItemOrdem() { return this->dsItemOrdem; }

    void setCdProduto(unsigned char *valor) { SAFE_STRNCPY(this->cdProduto,(char*)valor); }
    char *getCdProduto() { return this->cdProduto; }

    void setQtItem(unsigned char *valor) { SAFE_STRNCPY(this->qtItem,(char*)valor); }
    char *getQtItem() { return this->qtItem; }

    void setVlItem(unsigned char *valor) { SAFE_STRNCPY(this->vlItem,(char*)valor); }
    char *getVlItem() { return this->vlItem; }

    void setNrLinha(unsigned char *valor) { SAFE_STRNCPY(this->nrLinha,(char*)valor); }
    char *getNrLinha() { return this->nrLinha; }

    void setPlanoLinha(unsigned char *valor) { SAFE_STRNCPY(this->planoLinha,(char*)valor); }
    char *getPlanoLinha() { return this->planoLinha; }

    void setTecnologiaLinha(unsigned char *valor) { SAFE_STRNCPY(this->tecnologiaLinha,(char*)valor); }
    char *getTecnologiaLinha() { return this->tecnologiaLinha; }

    void setObservacaoItem(unsigned char *valor) { SAFE_STRNCPY(this->observacaoItem,(char*)valor); }
    char *getObservacaoItem() { return this->observacaoItem; }

    void setStatusItem(unsigned char *valor) { SAFE_STRNCPY(this->statusItem,(char*)valor); }
    char *getStatusItem() { return this->statusItem; }
};


//==============================================================================
// Estrutura para simular os itens de um pedido usando itens de ordem de venda
//==============================================================================
struct TItemOrdemVendaSap {

    char nrItem[LEN_NUMBER+LEN_EOS];
    char idItemOrdem[LEN_IDORDEMVENDA+LEN_EOS];
    char cdCpfCnpj[LEN_CDCPFCNPJ+LEN_EOS];
    char nrPedido[LEN_NRPEDIDO+LEN_EOS];
    char idSistemaOrigem[LEN_IDSISTEMAORIGEM+LEN_EOS];
    char dsItemOrdem[LEN_DSITEMORDEM+LEN_EOS];
    char cdProduto[LEN_CDPRODUTO+LEN_EOS];
    char qtItem[LEN_QTITEM+LEN_EOS];
    char vlItem[LEN_VLITEM+LEN_EOS];
    char nrLinha[LEN_NRLINHAORDEMVENDA+LEN_EOS];
    char planoLinha[LEN_PLANOLINHAPEDIDO+LEN_EOS];
    char tecnologiaLinha[LEN_TECNOLOGIALINHA+LEN_EOS];
    char observacaoItem[LEN_OBSERVACAOITEM+LEN_EOS];

    TItemOrdemVendaSap()
    {
        memset(this->nrItem,0,sizeof(this->nrItem));
        memset(this->idItemOrdem,0,sizeof(this->idItemOrdem));
        memset(this->cdCpfCnpj,0,sizeof(this->cdCpfCnpj));
        memset(this->nrPedido,0,sizeof(this->nrPedido));
        memset(this->idSistemaOrigem,0,sizeof(this->idSistemaOrigem));
        memset(this->dsItemOrdem,0,sizeof(this->dsItemOrdem));
        memset(this->cdProduto,0,sizeof(this->cdProduto));
        memset(this->qtItem,0,sizeof(this->qtItem));
        memset(this->vlItem,0,sizeof(this->vlItem));
        memset(this->nrLinha,0,sizeof(this->nrLinha));
        memset(this->planoLinha,0,sizeof(this->planoLinha));
        memset(this->tecnologiaLinha,0,sizeof(this->tecnologiaLinha));
        memset(this->observacaoItem,0,sizeof(this->observacaoItem));
    }

    void setNrItem(unsigned char *valor) { SAFE_STRNCPY(this->nrItem,(char*)valor); }
    char *getNrItem() { return this->nrItem; }

    void setIdItemOrdem(unsigned char *valor) { SAFE_STRNCPY(this->idItemOrdem,(char*)valor); }
    char *getIdItemOrdem() { return this->idItemOrdem; }

    void setCdCpfCnpj(unsigned char *valor) { SAFE_STRNCPY(this->cdCpfCnpj,(char*)valor); }
    char *getCdCpfCnpj() { return this->cdCpfCnpj; }

    void setNrPedido(unsigned char *valor) { SAFE_STRNCPY(this->nrPedido,(char*)valor); }
    char *getNrPedido() { return this->nrPedido; }

    void setIdSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->idSistemaOrigem,(char*)valor); }
    char *getIdSistemaOrigem() { return this->idSistemaOrigem; }

    void setDsItemOrdem(unsigned char *valor) { SAFE_STRNCPY(this->dsItemOrdem,(char*)valor); }
    char *getDsItemOrdem() { return this->dsItemOrdem; }

    void setCdProduto(unsigned char *valor) { SAFE_STRNCPY(this->cdProduto,(char*)valor); }
    char *getCdProduto() { return this->cdProduto; }

    void setQtItem(unsigned char *valor) { SAFE_STRNCPY(this->qtItem,(char*)valor); }
    char *getQtItem() { return this->qtItem; }

    void setVlItem(unsigned char *valor) { SAFE_STRNCPY(this->vlItem,(char*)valor); }
    char *getVlItem() { return this->vlItem; }

    void setNrLinha(unsigned char *valor) { SAFE_STRNCPY(this->nrLinha,(char*)valor); }
    char *getNrLinha() { return this->nrLinha; }

    void setPlanoLinha(unsigned char *valor) { SAFE_STRNCPY(this->planoLinha,(char*)valor); }
    char *getPlanoLinha() { return this->planoLinha; }

    void setTecnologiaLinha(unsigned char *valor) { SAFE_STRNCPY(this->tecnologiaLinha,(char*)valor); }
    char *getTecnologiaLinha() { return this->tecnologiaLinha; }

    void setObservacaoItem(unsigned char *valor) { SAFE_STRNCPY(this->observacaoItem,(char*)valor); }
    char *getObservacaoItem() { return this->observacaoItem; }
};

//==============================================================================
// Etapas do Pedido
//==============================================================================
struct TEtapaPedido {

    char idSistemaOrigemEtapa[LEN_IDSISTEMAORIGEM+LEN_EOS];
    char nmSistemaOrigem[LEN_NM_SISTEMA_ORIGEM+LEN_EOS];
    char cdCpfCnpj[LEN_CDCPFCNPJ+LEN_EOS];
    char idOrdemVenda[LEN_IDORDEMVENDA+LEN_EOS];
    char idPedido[LEN_IDPEDIDO+LEN_EOS];
    char idSistemaOrigemPedido[LEN_IDSISTEMAORIGEM+LEN_EOS];
    char dtEtapaPedido[LEN_DTETAPAPEDIDO+LEN_EOS];
    char dtAtualizacaoEtapa[LEN_DTATUALIZACAOETAPA+LEN_EOS];
    char observacaoEtapaPedido[LEN_OBSERVACAOETAPA+LEN_EOS];
    char dsTipoOcorrencia[LEN_DSTIPOOCORRENCIA+LEN_EOS];
    char dsEtapa[LEN_DSETAPA+LEN_EOS];
    char idProdutoEntrega[LEN_IDPRODUTOENTREGA+LEN_EOS];
    char loginUsuario[LEN_LOGINUSUARIO+LEN_EOS];

    TEtapaPedido()
    {
        memset(this->idSistemaOrigemEtapa,0,sizeof(this->idSistemaOrigemEtapa));
        memset(this->nmSistemaOrigem,0,sizeof(this->nmSistemaOrigem));
        memset(this->cdCpfCnpj,0,sizeof(this->cdCpfCnpj));
        memset(this->idOrdemVenda,0,sizeof(this->idOrdemVenda));
        memset(this->idPedido,0,sizeof(this->idPedido));
        memset(this->idSistemaOrigemPedido,0,sizeof(this->idSistemaOrigemPedido));
        memset(this->dtEtapaPedido,0,sizeof(this->dtEtapaPedido));
        memset(this->dtAtualizacaoEtapa,0,sizeof(this->dtAtualizacaoEtapa));
        memset(this->observacaoEtapaPedido,0,sizeof(this->observacaoEtapaPedido));
        memset(this->dsTipoOcorrencia,0,sizeof(this->dsTipoOcorrencia));
        memset(this->dsEtapa,0,sizeof(this->dsEtapa));
        memset(this->idProdutoEntrega,0,sizeof(this->idProdutoEntrega));
        memset(this->loginUsuario,0,sizeof(this->loginUsuario));
    }

    void setIdSistemaOrigemEtapa(unsigned char *valor) { SAFE_STRNCPY(this->idSistemaOrigemEtapa,(char*)valor); }
    char *getIdSistemaOrigemEtapa() { return this->idSistemaOrigemEtapa; }

    void setNmSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->nmSistemaOrigem,(char*)valor); }
    char *getNmSistemaOrigem() { return this->nmSistemaOrigem; }

    void setCdCpfCnpj(unsigned char *valor) { SAFE_STRNCPY(this->cdCpfCnpj,(char*)valor); }
    char *getCdCpfCnpj() { return this->cdCpfCnpj; }

    void setIdOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->idOrdemVenda,(char*)valor); }
    char *getIdOrdemVenda() { return this->idOrdemVenda; }

    void setIdPedido(unsigned char *valor) { SAFE_STRNCPY(this->idPedido,(char*)valor); }
    char *getIdPedido() { return this->idPedido; }

    void setIdSistemaOrigemPedido(unsigned char *valor) { SAFE_STRNCPY(this->idSistemaOrigemPedido,(char*)valor); }
    char *getIdSistemaOrigemPedido() { return this->idSistemaOrigemPedido; }

    void setDtEtapaPedido(unsigned char *valor) { SAFE_STRNCPY(this->dtEtapaPedido,(char*)valor); }
    char *getDtEtapaPedido() { return this->dtEtapaPedido; }

    void setDtAtualizacaoEtapa(unsigned char *valor) { SAFE_STRNCPY(this->dtAtualizacaoEtapa,(char*)valor); }
    char *getDtAtualizacaoEtapa() { return this->dtAtualizacaoEtapa; }

    void setObservacaoEtapaPedido(unsigned char *valor) { SAFE_STRNCPY(this->observacaoEtapaPedido,(char*)valor); }
    char *getObservacaoEtapaPedido() { return this->observacaoEtapaPedido; }

    void setDsTipoOcorrencia(unsigned char *valor) { SAFE_STRNCPY(this->dsTipoOcorrencia,(char*)valor); }
    char *getDsTipoOcorrencia() { return this->dsTipoOcorrencia; }

    void setDsEtapa(unsigned char *valor) { SAFE_STRNCPY(this->dsEtapa,(char*)valor); }
    char *getDsEtapa() { return this->dsEtapa; }

    void setIdProdutoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->idProdutoEntrega,(char*)valor); }
    char *getIdProdutoEntrega() { return this->idProdutoEntrega; }

    void setLoginUsuario(unsigned char *valor) { SAFE_STRNCPY(this->loginUsuario,(char*)valor); }
    char *getLoginUsuario() { return this->loginUsuario; }
};

//==============================================================================
// Etapas de pedidos e ordens de venda e notas fiscais e status de entrega
//==============================================================================
struct TEtapaPedidoOrdem {

    char idPedido[LEN_IDPEDIDO+LEN_EOS];
    char nrOrdemVenda[LEN_NRORDEMVENDA+LEN_EOS];
    char nrFornecimento[LEN_NRFORNECIMENTO+LEN_EOS];
    char nrPicking[LEN_NRPICKING+LEN_EOS];
    char nrNotaFiscal[LEN_IDNOTAFISCAL+LEN_EOS];
    char statusEntrega[LEN_STATUSENTREGA+LEN_EOS];

    TEtapaPedidoOrdem()
    {
        memset(this->idPedido,0,sizeof(this->idPedido));
        memset(this->nrOrdemVenda,0,sizeof(this->nrOrdemVenda));
        memset(this->nrFornecimento,0,sizeof(this->nrFornecimento));
        memset(this->nrPicking,0,sizeof(this->nrPicking));
        memset(this->nrNotaFiscal,0,sizeof(this->nrNotaFiscal));
        memset(this->statusEntrega,0,sizeof(this->statusEntrega));
    }

    void setIdPedido(unsigned char *valor) { SAFE_STRNCPY(this->idPedido,(char*)valor); }
    char *getIdPedido() { return this->idPedido; }
    bool isEmptyIdPedido() { return *this->idPedido == 0 ? true : false; }

    void setNrOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->nrOrdemVenda,(char*)valor); }
    char *getNrOrdemVenda() { return this->nrOrdemVenda; }
    bool isEmptyNrOrdemVenda() { return *this->nrOrdemVenda == 0 ? true : false; }

    void setNrFornecimento(unsigned char *valor) { SAFE_STRNCPY(this->nrFornecimento,(char*)valor); }
    char *getNrFornecimento() { return this->nrFornecimento; }
    bool isEmptyNrFornecimento() { return *this->nrFornecimento == 0 ? true : false; }

    void setNrPicking(unsigned char *valor) { SAFE_STRNCPY(this->nrPicking,(char*)valor); }
    char *getNrPicking() { return this->nrPicking; }
    bool isEmptyNrPicking() { return *this->nrPicking == 0 ? true : false; }

    void setNrNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->nrNotaFiscal,(char*)valor); }
    char *getNrNotaFiscal() { return this->nrNotaFiscal; }
    bool isEmptyNrNotaFiscal() { return *this->nrNotaFiscal == 0 ? true : false; }

    void setStatusEntrega(unsigned char *valor) { SAFE_STRNCPY(this->statusEntrega,(char*)valor); }
    char *getStatusEntrega() { return this->statusEntrega; }
    bool isEmptyStatusEntrega() { return *this->statusEntrega == 0 ? true : false; }

};

//==============================================================================
// Lista consolidade de etapas de um pedido/ordem
//==============================================================================
struct TEtapaPedidoPorDoc {

    char nmSistemaOrigem[LEN_NM_SISTEMA_ORIGEM+LEN_EOS];
    char sgUF[LEN_SGUF+LEN_EOS];
    char dtAberturaPedido[LEN_DTABERTURAPEDIDO+LEN_EOS];
    char idPedido[LEN_IDPEDIDO+LEN_EOS];
    char idOrdemVenda[LEN_IDORDEMVENDA+LEN_EOS];
    char nrOrdemVenda[LEN_NRORDEMVENDA+LEN_EOS];

    char dtFornecimento[LEN_DTFORNECIMENTO+LEN_EOS];
    char dtPicking[LEN_DTPICKING+LEN_EOS];
    char dtNotaFiscal[LEN_DTNOTAFISCAL+LEN_EOS];
    char dtPrevisaoEntrega[LEN_DTPREVISAOENTREGA+LEN_EOS];
    char enderecoEntrega[LEN_ENDERECOENTREGA+LEN_EOS];

    char nrFornecimento[LEN_NRFORNECIMENTO+LEN_EOS];
    char nrPicking[LEN_NRPICKING+LEN_EOS];
    char nrNotaFiscal[LEN_IDNOTAFISCAL+LEN_EOS];
    char serieNotaFiscal[LEN_SERIENOTAFISCAL+LEN_EOS];
    char statusEntrega[LEN_STATUSENTREGA+LEN_EOS];
    char inCorrompido[1+LEN_EOS];
    char inCancelada[1+LEN_EOS];

    TEtapaPedidoPorDoc()
    {
        memset(this->nmSistemaOrigem,0,sizeof(this->nmSistemaOrigem));
        memset(this->sgUF,0,sizeof(this->sgUF));
        memset(this->dtAberturaPedido,0,sizeof(this->dtAberturaPedido));
        memset(this->idPedido,0,sizeof(this->idPedido));
        memset(this->idOrdemVenda,0,sizeof(this->idOrdemVenda));
        memset(this->nrOrdemVenda,0,sizeof(this->nrOrdemVenda));

        memset(this->dtFornecimento,0,sizeof(this->dtFornecimento));
        memset(this->dtPicking,0,sizeof(this->dtPicking));
        memset(this->dtNotaFiscal,0,sizeof(this->dtNotaFiscal));
        memset(this->dtPrevisaoEntrega,0,sizeof(this->dtPrevisaoEntrega));
        memset(this->enderecoEntrega,0,sizeof(this->enderecoEntrega));

        memset(this->nrFornecimento,0,sizeof(this->nrFornecimento));
        memset(this->nrPicking,0,sizeof(this->nrPicking));
        memset(this->nrNotaFiscal,0,sizeof(this->nrNotaFiscal));
        memset(this->serieNotaFiscal,0,sizeof(this->serieNotaFiscal));
        memset(this->statusEntrega,0,sizeof(this->statusEntrega));
        memset(this->inCorrompido,0,sizeof(this->inCorrompido));
        memset(this->inCancelada,0,sizeof(this->inCancelada));
    }

    void setNmSistemaOrigem(unsigned char *valor) { SAFE_STRNCPY(this->nmSistemaOrigem,(char*)valor); }
    char *getNmSistemaOrigem() { return this->nmSistemaOrigem; }

    void setSgUF(unsigned char *valor) { SAFE_STRNCPY(this->sgUF,(char*)valor); }
    char *getSgUF() { return this->sgUF; }

    void setDtAberturaPedido(unsigned char *valor) { SAFE_STRNCPY(this->dtAberturaPedido,(char*)valor); }
    char *getDtAberturaPedido() { return this->dtAberturaPedido; }

    void setIdPedido(unsigned char *valor) { SAFE_STRNCPY(this->idPedido,(char*)valor); }
    char *getIdPedido() { return this->idPedido; }

    void setNrOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->nrOrdemVenda,(char*)valor); }
    char *getNrOrdemVenda() { return this->nrOrdemVenda; }

    void setIdOrdemVenda(unsigned char *valor) { SAFE_STRNCPY(this->idOrdemVenda,(char*)valor); }
    char *getIdOrdemVenda() { return this->idOrdemVenda; }



    void setDtFornecimento(unsigned char *valor) { SAFE_STRNCPY(this->dtFornecimento,(char*)valor); }
    char *getDtFornecimento() { return this->dtFornecimento; }

    void setDtPicking(unsigned char *valor) { SAFE_STRNCPY(this->dtPicking,(char*)valor); }
    char *getDtPicking() { return this->dtPicking; }

    void setDtNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->dtNotaFiscal,(char*)valor); }
    char *getDtNotaFiscal() { return this->dtNotaFiscal; }

    void setDtPrevisaoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->dtPrevisaoEntrega,(char*)valor); }
    char *getDtPrevisaoEntrega() { return this->dtPrevisaoEntrega; }

    void setEnderecoEntrega(unsigned char *valor) { SAFE_STRNCPY(this->enderecoEntrega,(char*)valor); }
    char *getEnderecoEntrega() { return this->enderecoEntrega; }



    void setNrFornecimento(unsigned char *valor) { SAFE_STRNCPY(this->nrFornecimento,(char*)valor); }
    char *getNrFornecimento() { return this->nrFornecimento; }

    void setNrPicking(unsigned char *valor) { SAFE_STRNCPY(this->nrPicking,(char*)valor); }
    char *getNrPicking() { return this->nrPicking; }

    void setNrNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->nrNotaFiscal,(char*)valor); }
    char *getNrNotaFiscal() { return this->nrNotaFiscal; }

    void setSerieNotaFiscal(unsigned char *valor) { SAFE_STRNCPY(this->serieNotaFiscal,(char*)valor); }
    char *getSerieNotaFiscal() { return this->serieNotaFiscal; }

    void setStatusEntrega(unsigned char *valor) { SAFE_STRNCPY(this->statusEntrega,(char*)valor); }
    char *getStatusEntrega() { return this->statusEntrega; }

    void setInCorrompido(unsigned char *valor) { SAFE_STRNCPY(this->inCorrompido,(char*)valor); }
    char *getInCorrompido() { return this->inCorrompido; }

    bool isCorrompido() { return *this->inCorrompido == '0' ? false:isEmptyInCorrompido()?false:true; }

    void setInCancelada(unsigned char *valor) { SAFE_STRNCPY(this->inCancelada,(char*)valor); }
    char *getInCancelada() { return this->inCancelada; }

    bool isCancelada() { return *this->inCancelada == '0' ? false:isEmptyInCancelada()?false:true; }

    bool isEmptyNmSistemaOrigem() { return *(this->nmSistemaOrigem) == 0 ? true:false;}
    bool isEmptySgUF() { return *(this->sgUF) == 0 ? true:false;}
    bool isEmptyDtAberturaPedido() { return *(this->dtAberturaPedido) == 0 ? true:false;}
    bool isEmptyIdPedido() { return *(this->idPedido) == 0 ? true:false;}
    bool isEmptyNrOrdemVenda() { return *(this->nrOrdemVenda) == 0 ? true:false;}


    bool isEmptyDtFornecimento() { return *(this->dtFornecimento) == 0 ? true:false;}
    bool isEmptyDtPicking() { return *(this->dtPicking) == 0 ? true:false;}
    bool isEmptyDtNotaFiscal() { return *(this->dtNotaFiscal) == 0 ? true:false;}
    bool isEmptyDtPrevisaoEntrega() { return *(this->dtPrevisaoEntrega) == 0 ? true:false;}
    bool isEmptyEnderecoEntrega() { return *(this->enderecoEntrega) == 0 ? true:false;}


    bool isEmptyNrFornecimento() { return *(this->nrFornecimento) == 0 ? true:false;}
    bool isEmptyNrPicking() { return *(this->nrPicking) == 0 ? true:false;}
    bool isEmptyNrNotaFiscal() { return *(this->nrNotaFiscal) == 0 ? true:false;}
    bool isEmptySerieNotaFiscal() { return *(this->serieNotaFiscal) == 0 ? true:false;}
    bool isEmptyStatusEntrega() { return *(this->statusEntrega) == 0 ? true:false;}
    bool isEmptyInCorrompido() { return *this->inCorrompido == 0 ? true : false; }
    bool isEmptyInCancelada() { return *this->inCancelada == 0 ? true : false; }

};

//==============================================================================
// Containers
//==============================================================================
typedef vector<TPedidoComOrdem> VEC_TPEDIDOCOMORDEM;
typedef vector<TPedido> VEC_TPEDIDO;
typedef vector<TItemPedido> VEC_TITEMPEDIDO;
typedef vector<TOrdemVenda> VEC_TORDEMVENDA;
typedef vector<TProdutoEntrega> VEC_TPRODUTOENTREGA;
typedef vector<TEtapaPedido> VEC_TETAPAPEDIDO;
typedef vector<TItemOrdemVenda> VEC_TITEMORDEMVENDA;
typedef vector<TItemOrdemVendaSap> VEC_TITEMORDEMVENDASAP;
typedef vector<TEtapaPedidoOrdem> VEC_TETAPAPEDIDOORDEM;
typedef vector<TEtapaPedidoPorDoc> VEC_TETAPAPEDIDOPORDOC;
typedef vector<TOrdemNotaFiscal> VEC_TORDEMNOTAFISCAL;

#endif /* __PEDIDONEGOCIO_H__ */
