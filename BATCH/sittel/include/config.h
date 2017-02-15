/*
 * config.h
 *
 *  Created on: 27/06/2013
 *      Author: Jones Randis
 */

#ifndef CONFIG_H_
#define CONFIG_H_


#define PROVEDOR "VIVONET"
#define LOCK_FILE ".lock"
#define DIR_SEP "/"
#define EXT_XML ".xml"
#define EXT_ZIP ".zip"

#define LOG_TIME_FORMAT "%Y-%m-%d %H:%M:%S"
#define LOG_ORADATE_FORMAT "DD/MM/YYYY HH24:MI:SS"

#define ORA_ENCODING "WE8ISO8859P1"
#define ORA_DATE_FORMAT "DDMMRRRR"

#define CDPARAMETRO_URI_ENC_RESULTADO_PROCESSAMENTO "PROCESSUM_URI_ENCAMINHAR_RESULTADO_PROCESSAMENTO"
#define CDPARAMETRO_USER_ENC_RESULTADO_PROCESSAMENTO "PROCESSUM_USER_ENCAMINHAR_RESULTADO_PROCESSAMENTO"
#define CDPARAMETRO_PASS_ENC_RESULTADO_PROCESSAMENTO "PROCESSUM_PASS_ENCAMINHAR_RESULTADO_PROCESSAMENTO"

#define PREFIXO_CDAREAREGISTRO "55"

#define XML_ENCODING "ISO-8859-1"
#define XML_BUFFER_SIZE 2097152

#define XMLNS "http://www.w3.org/2000/xmlns/"
#define XMLNS_SOAP11 "http://schemas.xmlsoap.org/soap/envelope/"

#define XMLNS_WSSE "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
#define XMLNS_WSU "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"
#define XMLATTR_WSUTP "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText"

#define XMLNS_ENC "http://www.vivo.com.br/SN/EncaminharResultadoProcessamento"
#define XMLNS_REQ "http://www.vivo.com.br/SN/RequisicaoJudicial"
#define XMLNS_GER "http://www.vivo.com.br/MC/Geral"

#define XMLNS_SITTEL "http://asspaweb.pgr.mpf.gov.br/sittel/schemas/sittel"
#define XMLNS_ASSINANTE "http://asspaweb.pgr.mpf.gov.br/sittel/schemas/assinante"
#define XMLNS_ASSINANTE_TERMINAL "http://asspaweb.pgr.mpf.gov.br/sittel/schemas/assinante-terminal"
#define XMLNS_INSTALACAO "http://asspaweb.pgr.mpf.gov.br/sittel/schemas/instalacoes"

#define SOAP_ENCODING "ISO-8859-1"

#define XSD_ENCODING "UTF-8"
#define XSD_ASSINANTE "assinante-1.0.xsd"
#define XSD_ASSINANTE_TERMINAL "assinante-terminal-1.0.xsd"
#define XSD_INSTALACAO "instalacoes-1.0.xsd"
#define XSD_ENCRESPROC "EncaminharResultadoProcessamento.xsd"

#define XMLNODE_ENC_REQ "EncaminharResultadoProcessamentoRequest"
#define XMLNODE_ENC_RES "EncaminharResultadoProcessamentoResponse"

#define CDERRO_PERIODO 14
#define DSERRO_PERIODO "Nada consta no periodo"

#define CDERRO_RETORNO 107
#define DSERRO_RETORNO "Numero de registros de retorno excede o maximo permitido, favor refinar a consulta"

#define CDERRO_TODOSERR 999
#define DSERRO_TODOSERR "Ocorreu erro(s) no processamento da solicitacao"

/* Recurso desabilitado
#define LIMITE_QUANTIDADE_RETORNO 100
*/

#include <oratypes.h>
#include <xmlctx.hpp>
typedef CXmlCtx TCtx;
typedef xmlnode Tnode;
typedef oratext* Tstr;


#endif /* CONFIG_H_ */
