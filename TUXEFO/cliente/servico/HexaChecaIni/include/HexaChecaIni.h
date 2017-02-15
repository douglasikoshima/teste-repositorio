/*
 * Serviço de batimento de HEXA entre HLR, Billing e FO 
 * Versão inicial, 28/05/2004
 */

#ifndef HEXACHECAINIHH
#define HEXACHECAINIHH

#define XML_IN_ID_LINHA				"idLinha"
#define XML_IN_ID_USUARIO			"idPessoaUsuario"
#define XML_IN_HEXA_FO				"cdHexaFront"

#define XML_OUT_ROOT				"ConsultaHexaVO"			
#define XML_OUT_PROP_XMLNS			"xmlns"
#define XML_OUT_PROP_XMLNS_VALUE	"cliente.fo.vivo.com.br/vo"

#define XML_OUT_RECV				"Recebimento"
#define XML_OUT_BILLING_HLR			"BillingHLR"
#define XML_OUT_HLR_FO				"HLRFront"
#define XML_OUT_BILLING_FO			"BillingFront"
#define XML_OUT_TRUE				"1"
#define XML_OUT_FALSE				"0"

#endif
