/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Dados básicos das tabelas de Pedidos
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.118.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/07/26 14:53:22 $
 **/

#ifndef __GLOBALPEDIDO_H__
#define __GLOBALPEDIDO_H__

#include "Global.h"

/*******************************************************************************
 * Insere 0x00 ao final da string 'binded' retornada pelo oracle
 *******************************************************************************/
#define CONVIND(O,I) \
{\
    if (I == -1) { \
        ##O.arr[0]=0; \
    } else { \
        ##O.arr[##O.len]=0; \
    } \
}

/*******************************************************************************
 * Cópia segura de string "C"
 *******************************************************************************/
#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;

/*******************************************************************************
 * Nomes de tags de retorno associadas às tabelas de origem
 *******************************************************************************/

// APOIO.SISTEMAORIGEM
#define XML_SISTEMAORIGEM_IDSISTEMAORIGEM   "idSistemaOrigem"
#define XML_SISTEMAORIGEM_NMSISTEMAORIGEM   "dsOrigem"

// APOIO.UF
#define XML_UF_SGUF                         "sgUF"

// RETENCAO.PEDIDO
#define XML_PEDIDO_CDAGENTEFILIAL           "cdAgenteFilial"
#define XML_PEDIDO_CDCPFCNPJ                "nrDoc"
#define XML_PEDIDO_DSCANALORIGEM            "dsCanalOrigem"
#define XML_PEDIDO_DTABERTURAPEDIDO         "dtAbertura"
#define XML_PEDIDO_DTPEDIDO                 "dtPedido"
#define XML_PEDIDO_DTPREVISAOENTREGA        "dtPrevistaEntrega"
#define XML_PEDIDO_ENDERECOENTREGA          "dsEnderecoEntrega"
#define XML_PEDIDO_FORMAPAGAMENTO           "dsPagamento"
#define XML_PEDIDO_IDPEDIDO                 "nrPedido"
#define XML_PEDIDO_IDSISTEMAORIGEM          XML_SISTEMAORIGEM_IDSISTEMAORIGEM
#define XML_PEDIDO_IDTIPOLINHA              "idTipoLinha"
#define XML_PEDIDO_NRORDEMVENDA             "nrOrdemVenda"
#define XML_PEDIDO_OBSERVACAOPEDIDO         "dsObservacao"
#define XML_PEDIDO_QTPONTORESGATADO         "qtdPontosResgatados"
#define XML_PEDIDO_SGUF                     XML_UF_SGUF
#define XML_PEDIDO_VLPARCELA                "vlParcelas"
#define XML_PEDIDO_VLTOTALPEDIDO            "vlTotalPedido"
#define XML_PEDIDO_NMNOMERAZAOSOCIAL        "nmCliente"
#define XML_PEDIDO_NMGESTOR                 "nmGestor"
#define XML_PEDIDO_NRTELEFONEGESTOR         "nrTelefoneGestor"

// RETENCAO.ITEMPEDIDO
#define XML_ITEMPEDIDO_NRITEMPEDIDO         "nrItem"
#define XML_ITEMPEDIDO_IDITEMPEDIDO         "cdItem"
#define XML_ITEMPEDIDO_DSITEMPEDIDO         "dsItem"
#define XML_ITEMPEDIDO_CDPRODUTO            "cdProduto"
#define XML_ITEMPEDIDO_QTITEM               "qtItem"
#define XML_ITEMPEDIDO_VLITEM               "vlItem"
#define XML_ITEMPEDIDO_NRLINHA              "nrLinha"
#define XML_ITEMPEDIDO_PLANOLINHA           "planoLinha"
#define XML_ITEMPEDIDO_TECNOLOGIALINHA      "tecnologiaLinha"
#define XML_ITEMPEDIDO_OBSERVACAOITEM       "dsObservacao"

// RETENCAO.ETAPAPEDIDO
#define XML_ETAPAPEDIDO_IDSISTEMAORIGEMETAPA "dsSistema"
#define XML_ETAPAPEDIDO_CDCPFCNPJ            "nrDoc"
#define XML_ETAPAPEDIDO_IDORDEMVENDA         "idOrdemVenda"
#define XML_ETAPAPEDIDO_IDPEDIDO             "nrPedido"
#define XML_ETAPAPEDIDO_IDSISTEMAORIGEMPEDIDO XML_SISTEMAORIGEM_IDSISTEMAORIGEM
#define XML_ETAPAPEDIDO_DTETAPAPEDIDO       "dtEtapa"
#define XML_ETAPAPEDIDO_DTATUALIZACAOETAPA  "dtAtualizacao"
#define XML_ETAPAPEDIDO_OBSERVACAOETAPAPEDIDO "dsObservacao"
#define XML_ETAPAPEDIDO_DSTIPOOCORRENCIA    "nrOcorrencia"
#define XML_ETAPAPEDIDO_DSETAPA             "dsEtapa"
#define XML_ETAPAPEDIDO_IDPRODUTOENTREGA    "idProdutoEntrega"
#define XML_ETAPAPEDIDO_LOGINUSUARIO        "nmUsuario"

// RETENCAO.ORDEMVENDA
#define XML_ORDEMVENDA_IDORDEMVENDA         "idOrdemVenda"
#define XML_ORDEMVENDA_SERIENOTAFISCAL      "serieNotaFiscal"
#define XML_ORDEMVENDA_NRPEDIDO             "nrPedido"
#define XML_ORDEMVENDA_DTABERTURAPEDIDO     "dtAberturaPedido"
#define XML_ORDEMVENDA_DTORDEMVENDA         "dtOrdemVenda"
#define XML_ORDEMVENDA_CDCPFCNPJ            "nrDoc"
#define XML_ORDEMVENDA_SGSTATUSORDEMVENDA   "sgStatus"
#define XML_ORDEMVENDA_DSSTATUSORDEMVENDA   "dsStatus"
#define XML_ORDEMVENDA_DSMOTIVOBLOQUEIO     "dsMotivoBloqueio"
#define XML_ORDEMVENDA_TPMOTIVOBLOQUEIO     "tpMotivoBloqueio"
#define XML_ORDEMVENDA_UFORDEM              "ufOrdem"
#define XML_ORDEMVENDA_VLPARCELA            "vlParcelas"
#define XML_ORDEMVENDA_VLTOTALNOTAFISCAL    "vlTotalNota"
#define XML_ORDEMVENDA_DSTIPOORDEMVENDA     "dsTipo"
#define XML_ORDEMVENDA_DSMOTIVOORDEM        "dsMotivo"
#define XML_ORDEMVENDA_NRFORNECIMENTO       "nrFornecimento"
#define XML_ORDEMVENDA_DTFORNECIMENTO       "dtFornecimento"
#define XML_ORDEMVENDA_NRPICKING            "nrPicking"
#define XML_ORDEMVENDA_DTPICKING            "dtPicking"
#define XML_ORDEMVENDA_DTCONFIRMACAOPICKING "dtConfPicking"
#define XML_ORDEMVENDA_DTSAIDAMERCADORIA    "dtRegSaida"
#define XML_ORDEMVENDA_NORMAEXPEDICAO       "dsNormaExpedicao"
#define XML_ORDEMVENDA_ESCRITORIOVENDA      "dsEscritorioVenda"
#define XML_ORDEMVENDA_GRUPOVENDA           "dsGrupoVendedores"
#define XML_ORDEMVENDA_NMRESPONSAVELORDEMVENDA "dsResponsavel"
#define XML_ORDEMVENDA_CANALVENDA           "dsCanalDist"
#define XML_ORDEMVENDA_ENDERECOENTREGA      "dsEnderecoEntrega"
#define XML_ORDEMVENDA_MEIOPAGAMENTO        "meioPagamento"
#define XML_ORDEMVENDA_IDSISTEMAORIGEM      XML_SISTEMAORIGEM_IDSISTEMAORIGEM
#define XML_ORDEMVENDA_FORMAPAGAMENTO       "dsPagamento"
#define XML_ORDEMVENDA_NRNOTAFISCAL         "nrNotaFiscal"
#define XML_ORDEMVENDA_DTFORNECIMENTO       "dtFornecimento"
#define XML_ORDEMVENDA_DTPICKING            "dtPicking"
#define XML_ORDEMVENDA_DTNOTAFISCAL         "dtNotaFiscal"
#define XML_ORDEMVENDA_DTPREVISAOENTREGA    "dtPrevisaoEntrega"
#define XML_ORDEMVENDA_NRORDEMVENDA         "nrOrdemVenda"
#define XML_ORDEMVENDA_INCORROMPIDO         "isOVParcial"


// RETENCAO.ITEMORDEMVENDA
#define XML_ITEMOVENDA_IDITEMPEDIDO         "nrItem"
#define XML_ITEMOVENDA_CDCPFCNPJ            "nrDoc"
#define XML_ITEMOVENDA_IDPEDIDO             "nrPedido"
#define XML_ITEMOVENDA_IDSISTEMAORIGEM      XML_SISTEMAORIGEM_IDSISTEMAORIGEM
#define XML_ITEMOVENDA_DSITEMPEDIDO         "dsItem"
#define XML_ITEMOVENDA_CDPRODUTO            "cdItem"
#define XML_ITEMOVENDA_QTITEM               "qtItem"
#define XML_ITEMOVENDA_VLITEM               "vlItem"
#define XML_ITEMOVENDA_NRLINHA              "nrLinha"
#define XML_ITEMOVENDA_PLANOLINHA           "planoLinha"
#define XML_ITEMOVENDA_TECNOLOGIALINHA      "tecnologiaLinha"
#define XML_ITEMOVENDA_OBSERVACAOITEM       "dsObservacao"

// RETENCAO.PRODUTOENTREGA
#define XML_PRODUTOENTREGA_IDPRODUTOENTREGA "idProdutoEntrega"
#define XML_PRODUTOENTREGA_CDCPFCNPJ        "nrDoc"
#define XML_PRODUTOENTREGA_NRNOTAFISCAL     "nrNotaFiscal"
#define XML_PRODUTOENTREGA_NRORDEMVENDA     "nrOrdemVenda"
#define XML_PRODUTOENTREGA_DSCANALORIGEM    "dsCanalOrigem"
#define XML_PRODUTOENTREGA_UFNOTAFISCAL     "ufNotaFiscal"
#define XML_PRODUTOENTREGA_NRFORNECIMENTO   "nrFornecimento"
#define XML_PRODUTOENTREGA_DTNOTAFISCAL     "dtNotaFiscal"
#define XML_PRODUTOENTREGA_DSTIPOENTREGA    "tipo"
#define XML_PRODUTOENTREGA_STATUSENTREGA    "dsStatusAtual"
#define XML_PRODUTOENTREGA_ENDERECOENTREGA  "dsEnderecoEntrega"
#define XML_PRODUTOENTREGA_DTPREVISAOENTREGA "dtPrevisao"
#define XML_PRODUTOENTREGA_NMRECEBEDORPEDIDO "nmRecebedor"
#define XML_PRODUTOENTREGA_NRDOCUMENTORECEBEDOR "nrRG"
#define XML_PRODUTOENTREGA_DSCOMPROVANTEENTREGA "tpComprovante"
#define XML_PRODUTOENTREGA_IDSISTEMAORIGEM  XML_SISTEMAORIGEM_IDSISTEMAORIGEM
#define XML_PRODUTOENTREGA_OBSERVACAOENTREGA "dsObservacao"
#define XML_PRODUTOENTREGA_DSMOTIVOSTATUS    "dsMotivoStatus"

// DIVERSOS
#define XML_FLAGCOMPARAR                    "flagComparar"
#define XML_DSTIPODOCUMENTO                 "tpDoc"

/*******************************************************************************
 * Retornos das operações ProC
 *******************************************************************************/
#define RET_SUCESSO                 0
#define RET_FIM_LEITURA             1
#define RET_NAO_EXISTEM_DADOS       2
#define RET_NAO_EXISTEM_DADOS_NMSO  3
#define RET_NAO_EXISTEM_DADOS_URL   4

/*******************************************************************************
 * Tamanho geral das colunas das tabelas de tracking
 *******************************************************************************/
#define LEN_CANALVENDA              30
#define LEN_CDAGENTEFILIAL          14
#define LEN_CDCPFCNPJ               16
#define LEN_CDPRODUTO               20
#define LEN_DSCANALORIGEM           65
#define LEN_DSCANALVENDAS           LEN_CANALVENDA
#define LEN_DSCOMPROVANTEENTREGA    7 // 30 ???
#define LEN_DSETAPA                 200
#define LEN_DSITEMORDEM             255
#define LEN_DSITEMPEDIDO            255
#define LEN_DSMOTIVOORDEM           80
#define LEN_DSTIPOENTREGA           15
#define LEN_DSTIPONOTAFISCAL        9
#define LEN_DSTIPOOCORRENCIA        60
#define LEN_DSTIPOORDEMVENDA        30
#define LEN_DTABERTURAPEDIDO        LEN_DATE_HOUR_FORM_ORA
#define LEN_DTATUALIZACAOETAPA      LEN_DATE_HOUR_FORM_ORA
#define LEN_DTETAPAPEDIDO           LEN_DATE_HOUR_FORM_ORA
#define LEN_DTNOTAFISCAL            LEN_DATE_HOUR_FORM_ORA
#define LEN_DTPREVISAOENTREGA       LEN_DATE_HOUR_FORM_ORA
#define LEN_ENDERECOENTREGA         400
#define LEN_ESCRITORIOVENDA         30
#define LEN_FORMAPAGAMENTO          100
#define LEN_GRUPOVENDEDOR           100
#define LEN_GRUPOVENDA              LEN_GRUPOVENDEDOR
#define LEN_IDITEMPEDIDO            10
#define LEN_IDNOTAFISCAL            LEN_NRNOTAFISCAL
#define LEN_IDORDEMVENDA            LEN_ID
#define LEN_IDPEDIDO                LEN_ID
#define LEN_IDPRODUTOENTREGA        LEN_ID
#define LEN_INCANCELADA             1
#define LEN_INDESTINO               10
#define LEN_LOGINUSUARIO            20
#define LEN_DSMOTIVOBLOQUEIO        50
#define LEN_TPMOTIVOBLOQUEIO        1
#define LEN_NMGESTOR                60
#define LEN_NMNOMERAZAOSOCIAL       80
#define LEN_NMRECEBEDORPEDIDO       50
#define LEN_NORMAEXPEDICAO          30
#define LEN_NRDOCUMENTORECEBEDOR    20
#define LEN_NRFORNECIMENTO          15
#define LEN_NRITEMPEDIDO            8
#define LEN_NRLINHAORDEMVENDA       11
#define LEN_NRNOTAFISCAL            15
#define LEN_NRORDEMVENDA            15

#define LEN_DTFORNECIMENTO          LEN_DATE_HOUR_FORM_ORA
#define LEN_DTPICKING               LEN_DATE_HOUR_FORM_ORA
#define LEN_DTNOTAFISCAL            LEN_DATE_HOUR_FORM_ORA
#define LEN_DTPREVISAOENTREGA       LEN_DATE_HOUR_FORM_ORA

#define LEN_DTCONFIRMACAOPICKING    LEN_DATE_HOUR_FORM_ORA
#define LEN_DTSAIDAMERCADORIA       LEN_DATE_HOUR_FORM_ORA

#define LEN_NRPEDIDO                16
#define LEN_NRPICKING               12
#define LEN_NRTELEFONEGESTOR        20
#define LEN_OBSERVACAOENTREGA       60
#define LEN_OBSERVACAOETAPA         1000
#define LEN_OBSERVACAOITEM          60
#define LEN_ORDEM                   2
#define LEN_OBSERVACAOPEDIDO        255
#define LEN_PLANOLINHAPEDIDO        100
#define LEN_QTITEM                  3
#define LEN_QTPONTORESGATADO        8
#define LEN_SERIENOTAFISCAL         14
#define LEN_SGUF                    255
#define LEN_SISTEMAORIGEM           3
#define LEN_SISTEMARESPONSAVEL      30
#define LEN_NMRESPONSAVELORDEMVENDA LEN_SISTEMARESPONSAVEL
#define LEN_STATUSENTREGA           50
#define LEN_STATUSITEM              LEN_STATUSORDEMVENDA
#define LEN_STATUSORDEMVENDA        30
#define LEN_TECNOLOGIAITEMPEDIDO    20
#define LEN_TECNOLOGIALINHA         LEN_TECNOLOGIAITEMPEDIDO
#define LEN_TPCLIENTE               20
#define LEN_UFNOTAFISCAL            2
#define LEN_DSMOTIVOENTREGA         80
#define LEN_UFORDEM                 LEN_UFNOTAFISCAL
#define LEN_UFPEDIDO                LEN_UFNOTAFISCAL
#define LEN_VLITEM                  LEN_CURRENCY_ORA
#define LEN_VLPARCELA               LEN_CURRENCY_ORA
#define LEN_VLTOTALNOTAFISCAL       LEN_CURRENCY_ORA
#define LEN_VLTOTALPEDIDO           LEN_CURRENCY_ORA

#define LEN_DSVALORPARAMETRO        255

/*******************************************************************************
 * Parametros de entradas para as rotinas ProC
 *******************************************************************************/
struct OperadoresLogisticos
{
    char nmSistemaOrigem[256];
    char url[256];
};

struct DadosParametros
{
    char nrDoc[LEN_NRDOCUMENTO+LEN_EOS];
    char tpDoc[LEN_DSTIPODOCUMENTO + LEN_EOS];
    char nrPedido[LEN_NUMBER+LEN_EOS];
    char nrNotaFiscal[LEN_NUMBER+LEN_EOS];
    char idNotaFiscal[LEN_NUMBER+LEN_EOS];
    char idPessoaCliente[LEN_NUMBER+LEN_EOS];
    char nrOrdemVenda[LEN_NUMBER+LEN_EOS];
    char nrFornecimento[LEN_NUMBER+LEN_EOS];
    char nrPicking[LEN_NUMBER+LEN_EOS];
    const char *idProdutoEntrega;
    char idSistemaOrigem[LEN_NUMBER+LEN_EOS];
    char nmSistemaOrigem[LEN_NM_SISTEMA_ORIGEM+LEN_EOS];
    char idOrdemVenda[LEN_NUMBER+LEN_EOS];
    char nmPessoa[LEN_NMPESSOA+LEN_EOS];
    char sgUF[LEN_SGUF+LEN_EOS];
    char lstURLOpeLog[LEN_DSVALORPARAMETRO+LEN_EOS];
    char lstNmSOrigemOpeLog[LEN_DSVALORPARAMETRO+LEN_EOS];
    struct OperadoresLogisticos operadoresLogisticos[9];
    int pagina;
};

#endif /* __GLOBALPEDIDO_H__ */
