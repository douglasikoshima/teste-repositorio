/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Luiz Carlos Fonseca
 * @author  Bruno Pereira Soares
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:24 $
 **/

/**
 * Serviço de validação de senha e solicitação de segmetação de 
 * cliente, sem identificação, ativado via URA
 **/

#ifndef VALSENHASOLICSEGMENTACAOSEMIDHPP
#define VALSENHASOLICSEGMENTACAOSEMIDHPP

/* TAGs do xml de entrada
 */
#define TAG_DDD						"cdDDD"
#define TAG_FONE					"numTelefone"

/* TAGs do xml de saída
 */
#define TAG_CODIGORETORNO           "cdCodigoRetorno"
#define TAG_ESTADOLINHA				"sgIndicadorEstadoLinha"
#define TAG_TIPOLINHA				"cdCodigoRetornoTipoConta"
#define TAG_CARTEIRA				"cdCodigoCarteira"
#define TAG_SEGMENTO				"cdCodigoSegmentoCliente"
#define TAG_REINIC_SENHA_CLIENTE	"sgIndicadorSenhaReinicioCliente"
#define TAG_REINIC_SENHA_USUARIO	"sgIndicadorSenhaReinicioUsuario"
#define TAG_SENHA_CLIENTE			"dsSenhaCriptografadaCliente"
#define TAG_SENHA_USUARIO			"dsSenhaCriptografadaUsuario"
#define TAG_SENHA_LOJISTA			"dsSenhaLojista"
#define TAG_PRIORIDADE				"cdPrioridadeLojista"
#define TAG_TITULARIDADE			"sgIndicadorTitularidadeTerminal"
#define TAG_SITUACAO_CADASTRO_PRE	"sgIndicadorSituacaoCadastro"

/* TAGs comuns ao XML de entrada e saída
 */
#define TAG_TRANSACTION_CODE		"codigoTransacao"
#define TAG_USER_CODE			"codigoUsuario"

void sql_error(struct stStatuslinha *stLinha, int Status);

void RecuperarDados( struct stStatuslinha *stLinha );
void VerificaLinha( struct stStatuslinha *stLinha );
void DecodeEstadoLinha(struct stStatuslinha *stLinha);

#endif
