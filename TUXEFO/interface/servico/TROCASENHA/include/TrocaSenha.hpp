/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:35 $
 **/

// dv-a1-estavel

/**
 * Serviço de troca de senha, ativado via URA
 **/

#ifndef TROCASENHAHPP
#define TROCASENHAHPP

/* TAGs do xml de entrada
 */
#define TAG_DATE			"dtData"
#define TAG_TIME			"hrHora"
#define TAG_SENHA_ANTERIOR		"dsSenhaAnteriorCript"
#define TAG_NOVA_SENHA			"dsSenhaNovaCript"
#define TAG_TITULARIDADE		"sgTitularidade"

/* TAGs do xml de saída
 */

/* TAGs comuns ao XML de entrada e saída
 */
#define TAG_TRANSACTION_CODE		"cdTransacao"
#define TAG_USER_CODE			"cdUsuario"
#define TAG_DDD				"cdDDD"
#define TAG_FONE			"numTelefone"
#define TAG_COMM_STATUS			"cdStatusComunicacao"
#define TAG_ERROR_CODE			"cdCodigoRetorno"

void sql_error(struct stStatuslinha *stLinha, int Status);

#endif
