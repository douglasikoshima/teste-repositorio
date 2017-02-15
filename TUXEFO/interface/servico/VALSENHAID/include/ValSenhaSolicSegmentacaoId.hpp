/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1.2.6.16.2.10.1 $
 * @CVS     $Author: jones $ - $Date: 2014/05/10 00:51:47 $
 **/

/**
 * Serviço de validação de senha e solicitação de segmetação de cliente, ativado via URA
 **/
 #include <tuxfw.h>

#ifndef VALSENHASOLICSEGMENTACAOIDHPP
#define VALSENHASOLICSEGMENTACAOIDHPP

/* TAGs do xml de entrada
 */
#define TAG_DDD						"cdDDD"
#define TAG_FONE					"numTelefone"

/* TAGs do xml de saída
 */
#define TAG_CODIGORETORNO           "cdCodigoRetorno"
#define TAG_CARTEIRA				"cdCodigoCarteira"
#define TAG_SEGMENTO				"cdCodigoSegmentoCliente"
#define TAG_ESTADOLINHA				"sgIndicadorEstadoLinha"
#define TAG_TIPOLINHA				"cdCodigoRetornoTipoConta"
#define TAG_REINIC_SENHA_CLIENTE	"sgIndicadorSenhaReinicioCliente"
#define TAG_REINIC_SENHA_USUARIO	"sgIndicadorSenhaReinicioUsuario"
#define TAG_SENHA_CLIENTE			"dsSenhaCriptografadaCliente"
#define TAG_SENHA_USUARIO			"dsSenhaCriptografadaUsuario"
#define TAG_RG_CLIENTE				"dsNumeroRGCliente"
#define TAG_RG_USUARIO				"dsNumeroRGUsuario"
#define TAG_CEP_CLIENTE				"dsNumeroCEPCliente"
#define TAG_CEP_USUARIO				"dsNumeroCEPUsuario"
#define TAG_NASCIMENTO_CLIENTE		"dtDataNascimentoCliente"
#define TAG_NASCIMENTO_USUARIO		"dtDataNascimentoUsuario"
#define TAG_CPF_CLIENTE				"dsNumeroCPFCliente"
#define TAG_CPF_USUARIO				"dsNumeroCPFUsuario"
#define TAG_SENHA_LOJISTA			"dsSenhaLojista"
#define TAG_PRIORIDADE_LOJISTA		"cdPrioridadeLojista"
#define TAG_SITUACAO_CADASTRO		"sgIndicadorSituacaoCadastro"
#define TAG_ATENDIMENTO_PESSOAL		"sgIndicadorAtendimentoPessoal"
#define TAG_TITULARIDADE		    "titularidade"
#define TAG_NOME_CLIENTE			"dsNomeCliente"
#define TAG_NOME_USUARIO			"dsNomeUsuario"
#define TAG_PLANO_LINHA				"planoLinha"
#define TAG_CODRESTRICAO			"CODRESTRICAO"
#define TAG_CLUSTERLINHA			"CLUSTERLINHA"
#define TAG_PROMOCAOIPHONE			"promocaoiphone"
#define TAG_INDLINHAOFERTA			"INDLINHAOFERTA"
#define TAG_C0DCLASSIFICACAOPJ			"codClassificacaoPJ"
#define TAG_CNPJPJ						"CNPJPJ"
#define TAG_CODLOGINPACONSULTORIAPJ1	"codLoginPAConsultoriaPJ1"
#define TAG_CODSITECONSULTORIAPJ1		"codSiteConsultoriaPJ1"
#define TAG_EMAILCONSULTORIAPJ1			"EmailConsultoriaPJ1"
#define TAG_CODLOGINPACONSULTORIAPJ2	"codLoginPAConsultoriaPJ2"
#define TAG_CODSITECONSULTORIAPJ2		"codSiteConsultoriaPJ2"
#define TAG_PLANOMODEM				"PLANOMODEM"
#define TAG_CODSUBSEGMENTOCLIENTE 	"cdCodigoSubSegmentoCliente"


/* TAGs comuns ao XML de entrada e saída
 */
#define TAG_TRANSACTION_CODE		"codigoTransacao"
#define TAG_USER_CODE				"codigoUsuario"

void sql_error(struct stStatuslinha *stLinha, int Status);
void sql_error(struct stStatuslinha *stLinha, int Status, int sqlcode);

void RecuperarDados( struct stStatuslinha *stLinha, struct stVlStatuslinha *stVlLinha );
void VerificaLinha( struct stStatuslinha *stLinha, struct stVlStatuslinha *stVlLinha );
void DecodeEstadoLinha(struct stStatuslinha *stLinha);
bool GetSenhaBloqueada(struct stStatuslinha *stLinha);
void GetPlano(struct stStatuslinha *stLinha);
void GetLinhaRestrita( struct stStatuslinha *stLinha );
bool GetBloqueioPreAtivacao( struct stStatuslinha *stLinha );
void IsLinhaOferta( struct stStatuslinha *stLinha );
bool IsPlanoModem( struct stStatuslinha *stLinha );

int getPlanoZap(struct stStatuslinha *stLinha);
void setPlanoDadosFixo(struct stStatuslinha *stLinha);
void getDadosPortabilidade(struct stStatuslinha *stLinha,XMLGen* xml_g);
void setDadosPortabilidade(struct stStatuslinha *stLinha,XMLGen* xml_g);

int isMailing(struct stStatuslinha *stLinha);

void setCadastroIPhone(struct stStatuslinha *stLinha,XMLGen* xml_g);

int linhaPossuiGestor( struct stStatuslinha *stLinha );

int getCnl(char *pCEP, char* pCNL );

void getComboFixo( struct stStatuslinha *stLinha );

int consultarRegraComboFixo( struct stStatuslinha *stLinha );

void CodigoSubSegmentoCliente(struct stStatuslinha *stLinha, struct stVlStatuslinha *stVlLinha);

void CodigoSubSegmentoGestor(struct stStatuslinha *stLinha, struct stVlStatuslinha *stVlLinha);


#endif
