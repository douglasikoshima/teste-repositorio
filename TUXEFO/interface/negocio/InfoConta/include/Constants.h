#ifndef CONSTANTS_H
#define CONSTANTS_H

#include "ConstantsAtlys.h"
#include "ConstantsXenos.h"

////////////////////////////////////////////////////////////////
// Constantes de Operações

#define OPERACAO_BOLETO_IMPRESSO				100
#define OPERACAO_CONTA_RESUMIDA					101
#define OPERACAO_CONTA_DETALHADA				102
#define OPERACAO_CONTA_DETALHADA_LOCAL			103

#define OPERACAO_BOLETO_IMPRESSO_STRING			"IMPBOLETO"
#define OPERACAO_CONTA_RESUMIDA_STRING			"CONTARES"
#define OPERACAO_CONTA_DETALHADA_STRING			"CONTADET"
#define OPERACAO_CONTA_DETALHADA_LOCAL_STRING	"CONTALOC"

////////////////////////////////////////////////////////////////
// Constantes de erros

#define ERROR_PARSE_EXCEPTION					200
#define ERROR_OPERACAO_EXCEPTION				201
#define ERROR_LIMITE_XML						202
#define ERROR_DATA_NAO_ENCONTRADA				203
#define ERROR_ATLYS_FORA_DO_AR					204
#define ERROR_BILL_IMAGE_FAULT					205
#define ERROR_PARSE_AFP							206
#define ERROR_INPUT_AFP							207
#define ERROR_OUTPUT_AFP						208
#define ERROR_DECODE_BASE64						209
#define ERROR_DECODE_AFP						210
#define ERROR_BILL_DATE_FAULT					211
#define ERROR_AGUARDANDO_XENOS					212
#define ERROR_XENOS_TIMEOUT						213
#define ERROR_GRAVAR_IMAGEM						214

////////////////////////////////////////////////////////////////
// Constantes de mensagens de erros

#define ERROR_MSG_PARSE_EXCEPTION					"Dados de entrada inválidos"
#define ERROR_MSG_OPERACAO_EXCEPTION				"Operação inválida / ou não implementada"
#define ERROR_MSG_LIMITE_XML						"Limite de XML excedido (~250kb)"
#define ERROR_MSG_DATA_NAO_ENCONTRADA				"Data passada não localizada"
#define ERROR_MSG_ATLYS_FORA_DO_AR					"Sistema Atlys fora do ar"
#define ERROR_MSG_BILL_IMAGE_FAULT					"Erro na API inputGetBillImage do atlys"
#define ERROR_MSG_PARSE_AFP							"Erro ao fazer parser do AFP de entrada"
#define ERROR_MSG_INPUT_AFP							"Erro ao criar arquivo no diretório de input do XENOS"
#define ERROR_MSG_OUTPUT_AFP						"Erro ao criar arquivo no diretório de output do XENOS"
#define ERROR_MSG_DECODE_BASE64						"Erro ao decodificar AFP - base64"
#define ERROR_MSG_DECODE_AFP						"Xenos não conseguiu decodificar AFP"
#define ERROR_MSG_BILL_DATE_FAULT					"Erro na API inputGetBillDates"
#define ERROR_MSG_AGUARDANDO_XENOS					"Aguardando xenos processar imagem"
#define ERROR_MSG_XENOS_TIMEOUT						"O Xenos não decodificou a imagem a tempo [TIMEOUT]"
#define ERROR_MSG_GRAVAR_IMAGEM						"Erro ao inserir imagem no Oracle"

////////////////////////////////////////////////////////////////
// Constantes de tags de entrada

#define XML_TAG_INPUT_OPERACAO						"OPERACAO"
#define XML_TAG_INPUT_CANAL							"CANAL"
#define XML_TAG_INPUT_CONTA							"CONTA"
#define XML_TAG_INPUT_DATA							"DATA"
#define XML_TAG_INPUT_RETORNO						"RETORNO"
#define XML_TAG_INPUT_COD_AREA						"COD_AREA"
#define XML_TAG_INPUT_NUM_LINE						"NUM_LINE"

#define TIPO_RETORNO_HTML						1
#define TIPO_RETORNO_XML						2


////////////////////////////////////////////////////////////////
// PARAMETROS
#define MAX_SIZE_FILE							240000

////////////////////////////////////////////////////////////////
// parametros de extensão de arquivo
#define FILE_EXTENSION_AFP						".afp"
#define FILE_EXTENSION_HTML						".html"
#define FILE_EXTENSION_XML						".xml"

#define INT_FILE_EXTENSION_AFP					1
#define INT_FILE_EXTENSION_HTML					2
#define INT_FILE_EXTENSION_XML					3




#endif