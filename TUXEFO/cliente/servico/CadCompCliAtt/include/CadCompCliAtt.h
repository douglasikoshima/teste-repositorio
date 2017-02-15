/*
 * Serviço de cadastro de dados comportamentais de cliente - Traz todos os atributos com 
 * filhos (valorLivre, vlor possível selecionado e não selecionado)
 * Versão inicial, 09/06/2004
 */

#ifndef CADCOMPCLIATTHH
#define CADCOMPCLIATTHH

#define XML_IN_ID_PESSOA			"idPessoa"
#define XML_IN_ID_ID_SUB_ASS		"idSubAssunto"
#define XML_IN_ID_GRUPO     		"idGrupo"
#define XML_IN_ID_CANAL     		"idCanal"

#define XML_OUT_ROOT				"ManterDadosComportamentaisVO"
#define XML_OUT_PROP_XMLNS			"xmlns"
#define XML_OUT_PROP_XMLNS_VALUE	"cliente.fo.vivo.com.br/vo"

#define XML_OUT_ATRIBUTO			"Atributos"
#define XML_OUT_ID_ATRIBUTO			"idAtributo"
#define XML_OUT_DS_ATRIBUTO			"dsAtributo"
#define XML_OUT_ID_TP_APRES			"idTipoApresentacao"
#define XML_OUT_SQ_APRES			"sqApresentacao"

#define XML_OUT_VALOR_LIVRE			"ValorLivre"
#define XML_OUT_ID_VALOR_LIVRE		"idValorLivre"
#define XML_OUT_DS_VALOR_LIVRE		"dsValorLivre"

#define XML_OUT_VALOR_POSS			"ValoresPossiveis"
#define XML_OUT_ID_VALOR_POSS		"idValorPossivel"
#define XML_OUT_DS_VALOR_POSS		"dsValorPossivel"
#define XML_OUT_VP_IN_SEL			"inSelecionado"

#endif
