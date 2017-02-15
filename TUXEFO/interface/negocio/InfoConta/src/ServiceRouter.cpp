#include <ServiceRouter.h>
#include <ServiceBase.h>
#include<ImpressaoBoleto.h>
#include<ContaDetalhada.h>
#include<ContaResumida.h>

DOMNode* ServiceRouter::parseXmlIN(DOMNode* dnode) throw(XMLParseException)
{
	CMemoryManager mem;
	char*operacao =  mem.getTag(dnode,XML_TAG_INPUT_OPERACAO,0);
	char*canal =  mem.getTag(dnode,XML_TAG_INPUT_CANAL,0);
	char*conta =  mem.getTag(dnode,XML_TAG_INPUT_CONTA,0);
	char*data =  mem.getTag(dnode,XML_TAG_INPUT_DATA,0);
	char*retorno =  mem.getTag(dnode,XML_TAG_INPUT_RETORNO,0);

	if(operacao == NULL)
		throw XMLParseException("XMLParseException::Tag operacao n�o encontrada");
	else
	if(canal == NULL)
		throw XMLParseException("XMLParseException::Tag canal n�o encontrada");
	else
	if(conta == NULL)
		throw XMLParseException("XMLParseException::Tag conta n�o encontrada");

	return dnode;
}


ServiceBase* ServiceRouter::getServiceMethod(DOMNode* dnode) throw(OperationNotFoundException)
{
	ServiceBase* service = NULL;
	CMemoryManager mem;
	int action = 0;
	char *operacao = NULL;
	operacao = mem.getTag(dnode,XML_TAG_INPUT_OPERACAO,0);
	if(strcmp(operacao,OPERACAO_BOLETO_IMPRESSO_STRING)==0)
	{
		action = OPERACAO_BOLETO_IMPRESSO;
	}
	else
	if(strcmp(operacao,OPERACAO_CONTA_RESUMIDA_STRING)==0)
	{
		action = OPERACAO_CONTA_RESUMIDA;
	}
	else
	if(strcmp(operacao,OPERACAO_CONTA_DETALHADA_STRING)==0)
	{
		action = OPERACAO_CONTA_DETALHADA;
	}
	else
	if(strcmp(operacao,OPERACAO_CONTA_DETALHADA_LOCAL_STRING)==0)
	{
		action = OPERACAO_CONTA_DETALHADA_LOCAL;
	}
	// recupera uma instancia de um ServiceBase
	service = ServiceBase::getInstance(action);
	// caso for NULL, a opera��o n�o existe ou n�o foi implementada ainda
	if(service == NULL)
		throw OperationNotFoundException("OperationNotFoundException::Opera��o inv�lida / ou n�o implementada.");
	// retorna o service
	return service;
}

