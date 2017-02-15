/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:12 $
 **/ 

// dv-a1-estavel

/**
 * Serviço de registro de pesquisa de satisfação, ativado via URA
 **/

#ifndef PESQUISASATISFACAOHPP
#define PESQUISASATISFACAOHPP

/* TAGs do xml de entrada
 */
#define TAG_DDD							"cdDDD"
#define TAG_FONE						"numTelefone"
#define TAG_DATA						"dtData" //DDMMAAAA
#define TAG_HORA						"dsHora" //HHMMSS
#define TAG_IND_TITULARIDADE			"indTitularidade"
#define TAG_CLI_IDENT_SENHA				"clienteIdentificadoSenha"
#define TAG_NRO_PROCESSO_ATENDIMENTO	"nroProcessoAtendimento"
#define TAG_NOTA_CLIENTE				"dsNotaCliente"

/* TAGs do xml de saída
 */
#define TAG_COMM_STATUS			"status"
#define TAG_ERROR_CODE			"resultado"
#define TAG_CODIGORETORNO       "cdCodigoRetorno"

/* TAGs comuns aos xmls de entrada e saída
 */
#define TAG_TRANSACTION_CODE			"codigoTransacao"
#define TAG_USER_CODE					"codigoUsuario"

#endif
