#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Banco/Banco.hpp>

DECLARE_TUXEDO_SERVICE(SETIDTRANS);

static char *STR_11IOK                   = "11I0000";
static char *STR_11IOKMSG                = "Gravação Efetuada com Sucesso.";

static char *STR_11W0099                 = "11W0099";
static char *STR_11W0099MSG				 = "Gravação não efetuada.";

static char *STR_11W9999                 = "11W9999";
static char *STR_11WEXCDESC              = "ERRO DESCONHECIDO";

void implSETIDTRANS::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CStatusCode	  statusCode;
	char*         chave = NULL;
	char*		  codigoBarras = NULL;
	CBanco  banco;

	// Navega o XML e recupera as informacoes obrigatorias
	chave = helper.walkTree(dnode,"chave", 0);
	codigoBarras = helper.walkTree(dnode,"codigoBarras", 0);
	
	try	
	{
		// Operação de Negócio para inserir nova chave de acesso para pagamento de boleto
		banco.setTransacaoBanco(chave,codigoBarras);
		// seta mensagem de retorno - header	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKMSG);
	}
	catch(TuxBasicSvcException)
	{
				
		// seta mensagem de retorno - header
		statusCode.setCod(STR_11W0099);
		statusCode.setMsg(STR_11W0099MSG);
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_11W0099);
		statusCode.setMsg(STR_11W0099MSG);
	}
	catch(...)
	{
		statusCode.setCod(STR_11W9999);
		statusCode.setMsg(STR_11WEXCDESC);
	}


	// seta mensagem de retorno - header
    setStatusCode(statusCode.getCod(), statusCode.getMsg());

}
