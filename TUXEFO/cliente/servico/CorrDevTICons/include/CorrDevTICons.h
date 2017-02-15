/*
 * Serviço de Correspondencia devolvida - Consulta de correspondencias via TELA INICIAL
 * Versão inicial, 02/07/2004
 */

#ifndef CORRDEVTICONSHH
#define CORRDEVTICONSHH

#define XML_IN_ID_PESSOA					"idPessoa"
#define XML_IN_ID_TP_REL					"idTipoRelacionamento"
#define XML_IN_ID_STAT						"idStatus"
#define XML_IN_DT_REG_INI					"dtRegistroIni"
#define XML_IN_DT_REG_FIM					"dtRegistroFim"

#define XML_OUT_ROOT						"CorrespDevolvidaTelaInicialVO"
#define XML_OUT_PROP_XMLNS					"xmlns"
#define XML_OUT_PROP_XMLNS_VALUE			"cliente.fo.vivo.com.br/vo"

#define XML_OUT_LISTA_CORR_DEV				"ListaCorrespDevolvida"
#define XML_OUT_ID_CORR						"idCorrespondencia"
#define XML_OUT_NR_LINHA					"nrLinha"
#define XML_OUT_NR_CONTA					"nrConta"
#define XML_OUT_DS_TP_CORR					"dsTipoCorrespondencia"
#define XML_OUT_DS_MOT_DEV					"dsMotivoDevolucao"
#define XML_OUT_DT_DEV						"dtDevolucao"
#define XML_OUT_DT_REG						"dtRegistro"
#define XML_OUT_NM_PESSOA					"nmPessoa"
#define XML_OUT_ID_TP_REL					"idTipoRelacionamento"
#define XML_OUT_DS_TP_REL					"dsTipoRelacionamento"

#define NRO_ERR_OK							"24I0000"
#define MSG_ERR_OK							"Execução OK"

#endif
