#define IMGCONTACPP

#include<tuxfw.h>
#include "../../../negocio/InfoConta/include/MemoryManager.h"
#include "../../../negocio/InfoConta/include/ServiceRouter.h"
#include "../../../negocio/InfoConta/include/CFileXenos.h"
#include "../../../negocio/InfoConta/include/XenosPlugin.h"
#include "../../../negocio/InfoConta/include/Util.h"
#include "../../../negocio/InfoConta/include/Constants.h"

DECLARE_TUXEDO_SERVICE(IMGCONTA);


void implIMGCONTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
	ServiceBase*servico = NULL;
	int retorno = 0;
	try
	{
		// fazer o parse do XML de entrada
		ServiceRouter::parseXmlIN(dnode);
	}
	catch(XMLParseException e)
	{
		tuxfw_getlogger()->debug("%s",e.getCause());
	}
	try
	{
		// Recuperar uma instância de um ServiceBase
		servico = ServiceRouter::getServiceMethod(dnode);
	}
	catch(OperationNotFoundException oe)
	{
		tuxfw_getlogger()->debug("%s",oe.getCause());
		setStatusCode("00W0001","Operação inválida");
		return;
	}
	// executar a regra de negocio do servico

	try{
		servico->setUser(getUser());
		retorno = servico->execute(dnode,xml_g);
	}
	catch(TuxBasicSvcException tbse)
	{
		servico->finalize();
	}

	switch(retorno)
	{
		case 1: 
			setStatusCode("00I0001","Sucesso");
			break;
		case ERROR_PARSE_EXCEPTION: 
			setStatusCode("00W0001",ERROR_MSG_PARSE_EXCEPTION);
			break;
		case ERROR_OPERACAO_EXCEPTION: 
			setStatusCode("00W0002",ERROR_MSG_OPERACAO_EXCEPTION);
			break;
		case ERROR_LIMITE_XML: 
			setStatusCode("00W0003",ERROR_MSG_LIMITE_XML);
			break;
		case ERROR_DATA_NAO_ENCONTRADA: 
			setStatusCode("00W0004",ERROR_MSG_DATA_NAO_ENCONTRADA);
			break;
		case ERROR_ATLYS_FORA_DO_AR: 
			setStatusCode("00W0005",ERROR_MSG_ATLYS_FORA_DO_AR);
			break;
		case ERROR_BILL_IMAGE_FAULT: 
			setStatusCode("00W0006",ERROR_MSG_BILL_IMAGE_FAULT);
			break;
		case ERROR_BILL_DATE_FAULT: 
			setStatusCode("00W0006",ERROR_MSG_BILL_DATE_FAULT);
			break;
		case ERROR_PARSE_AFP: 
			setStatusCode("00W0007",ERROR_MSG_PARSE_AFP);
			break;
		case ERROR_INPUT_AFP: 
			setStatusCode("00W0008",ERROR_MSG_INPUT_AFP);
			break;
		case ERROR_OUTPUT_AFP: 
			setStatusCode("00W0009",ERROR_MSG_OUTPUT_AFP);
			break;
		case ERROR_DECODE_BASE64: 
			setStatusCode("00W0010",ERROR_MSG_DECODE_BASE64);
			break;
		case ERROR_DECODE_AFP:
			setStatusCode("00W0012",ERROR_MSG_DECODE_AFP);
			break;
		case ERROR_AGUARDANDO_XENOS:
			setStatusCode("00W0013",ERROR_MSG_AGUARDANDO_XENOS);
			break;
		case ERROR_XENOS_TIMEOUT:
			setStatusCode("00W0014",ERROR_MSG_XENOS_TIMEOUT);
			break;
		case ERROR_GRAVAR_IMAGEM:
			setStatusCode("00W0015",ERROR_MSG_GRAVAR_IMAGEM);
			break;
		default:
			setStatusCode("00W0011","Erro desconhecido.");
			break;
	}
	// Finalizar o serviceBase
	servico->finalize();
}