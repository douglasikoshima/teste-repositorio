/*
 * Serviço de Correspondencia devolvida - Manutenção de correspondencia, tela inicial.
 * Versão inicial, 26/05/2004
 */

#ifndef CORRDEVMANTINIHH
#define CORRDEVMANTINIHH

#define XML_IN_ID_CORR_DEV			"idCorrespondenciaDevolvida"

#define XML_OUT_ROOT				"TratarCorrespDevolvidaVO"
#define XML_OUT_PROP_XMLNS			"xmlns"
#define XML_OUT_PROP_XMLNS_VALUE	"cliente.fo.vivo.com.br/vo"

#define XML_OUT_ID_CORR_DEV			"idCorrespondenciaDevolvida"
#define XML_OUT_ID_PESSOA			"idPessoa"
#define XML_OUT_DT_DEVOLUCAO		"dtDevolucao"
#define XML_OUT_DS_TIPO_CORRESP		"dsTipoCorrespondencia"
#define XML_OUT_DS_MOT_DEV			"dsMotivoDevolucao"
#define XML_OUT_NM_CLIENTE			"nmCliente"
#define XML_OUT_NR_DOCTO			"nrDocumento"
#define XML_OUT_DT_TELEMENSAGEM		"dtTelemensagem"
#define XML_OUT_DT_SMS				"dtSMS"

#define XML_OUT_ENDERECO_BASE		"EnderecoBaseVO"
#define XML_OUT_DS_ENDERECO			"dsEndereco"
#define XML_OUT_NR_ENDERECO			"nrEndereco"
#define XML_OUT_DS_COMPLEMENTO		"dsComplemento"
#define XML_OUT_DS_BAIRRO			"dsBairro"
#define XML_OUT_DS_CIDADE			"dsCidade"
#define XML_OUT_NR_CEP				"nrCEP"

#define XML_OUT_UF					"UFVO"
#define XML_OUT_UF_ID				"idUF"
#define XML_OUT_UF_SG				"sgUF"
#define XML_OUT_UF_DS				"nmUF"

#define XML_OUT_PAIS				"PaisVO"
#define XML_OUT_PAIS_ID				"idPais"
#define XML_OUT_PAIS_SG				"sgPais"
#define XML_OUT_PAIS_DS				"nmPais"

#define XML_OUT_STATUS_CORRESP		"ListaStatus"
#define XML_OUT_ID_STATUS			"idStatus"
#define XML_OUT_DS_STATUS			"dsStatus"

#define XML_OUT_LISTA_HSTATUS_CORR	"ListaStatusCorresp"
#define XML_OUT_DS_HSTATUS			"dsStatus"
#define XML_OUT_DT_HSTATUS			"dtStatus"
#define XML_OUT_ID_HSTATUS          "idStatusAtual"
#define XML_OUT_NR_LINHA            "nrLinha"
#define XML_OUT_CD_CONTA            "nrConta"
#define XML_OUT_TP_DOCUMENTO    	"dsTipoDocumento"

#endif
