/*
 * Serviço de Correspondencia devolvida - Insere correspondencia devolvida
 * Versão inicial, 28/05/2004
 */

#ifndef CORRDEVINSEREHH
#define CORRDEVINSEREHH

#define XML_IN_ID_PESSOA					"idPessoa"
#define XML_IN_ID_TIPO_CORR					"idTipoCorrespondencia"
#define XML_IN_ID_MOT_DEV					"idMotivoDevolucao"
#define XML_IN_ID_UF						"idUF"
#define XML_IN_ID_PAIS						"idPais"
#define XML_IN_ID_USUARIO					"idPessoaUsuario"
#define XML_IN_ID_LINHA						"idLinha"
#define XML_IN_ID_CONTA						"idConta"
#define XML_IN_DT_DEVOLUCAO					"dtDevolucao"
#define XML_IN_DS_END						"dsEndereco"
#define XML_IN_NR_END						"nrEndereco"
#define XML_IN_DS_COMPLEMENTO				"dsComplemento"
#define XML_IN_DS_BAIRRO					"dsBairro"
#define XML_IN_DS_CIDADE					"dsCidade"
#define XML_IN_NR_CEP						"nrCEP"

#define NRO_ERR_NO_ID_PESSOA				"24E0370"
#define MSG_ERR_NO_ID_PESSOA				"ID da pessoa não encontrado"
#define NRO_ERR_ID_INVALIDO_PESSOA			"24E0371"
#define MSG_ERR_ID_INVALIDO_PESSOA			"ID da pessoa inválido"
#define NRO_ERR_NO_ID_TIPO_CORR				"24E0372"
#define MSG_ERR_NO_ID_TIPO_CORR				"ID de tipo de correspondência não encontrado"
#define NRO_ERR_ID_INVALIDO_TIPO_CORR		"24E0372"
#define MSG_ERR_ID_INVALIDO_TIPO_CORR		"ID de tipo de correspondência inválido"
#define NRO_ERR_NO_ID_MOT_DEV				"24E0372"
#define MSG_ERR_NO_ID_MOT_DEV				"ID de motivo de devolução não encontrado"
#define NRO_ERR_ID_INVALIDO_MOT_DEV			"24E0372"
#define MSG_ERR_ID_INVALIDO_MOT_DEV			"ID de motivo de devolução inválido"
#define NRO_ERR_NO_ID_UF					"24E0372"
#define MSG_ERR_NO_ID_UF					"ID de estado não encontrado"
#define NRO_ERR_ID_INVALIDO_UF				"24E0372"
#define MSG_ERR_ID_INVALIDO_UF				"ID de estado inválido"
#define NRO_ERR_NO_ID_PAIS					"24E0372"
#define MSG_ERR_NO_ID_PAIS					"ID de país não encontrado"
#define NRO_ERR_ID_INVALIDO_PAIS			"24E0372"
#define MSG_ERR_ID_INVALIDO_PAIS			"ID de país inválido"
#define NRO_ERR_NO_ID_USUARIO				"24E0372"
#define MSG_ERR_NO_ID_USUARIO				"ID de usuário não encontrado"
#define NRO_ERR_ID_INVALIDO_USUARIO			"24E0372"
#define MSG_ERR_ID_INVALIDO_USUARIO			"ID de pessoa inválido"
#define NRO_ERR_OK							"24I0000"
#define MSG_ERR_OK							"Execução OK"

#endif
