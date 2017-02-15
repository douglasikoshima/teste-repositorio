// ServiceAtlys.cpp: implementation of the ServiceAtlys class.
//
//////////////////////////////////////////////////////////////////////

#include "ServiceAtlys.h"
#include "Constants.h"
//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

ServiceAtlys::ServiceAtlys()
{

}

ServiceAtlys::~ServiceAtlys()
{

}

DOMNode* ServiceAtlys::searchSubscriptionByAttribute(ListDOMNodes *list,char*attrName,char*attrValue,
													 char*startKey,bool aremore)
{
	tuxfw_getlogger()->debug("ServiceAtlys::searchSubscriptionByAttribute");
	XMLGen gen;
	DOMNode*dom = NULL;
	gen.createTag(XML_TAG_INPUT_SEARCH_SUBS_BY_ATTR);
	gen.addProp(XML_ATTR_ATTR_NAME,attrName);
	gen.addProp(XML_ATTR_ATTR_VALUE,attrValue);
	// Caso tenha mais XML
	if(aremore)
	{
		gen.addProp(XML_ATTR_START_KEY,startKey);
	}
	gen.closeTag();
	// Chama a API remota
	tuxfw_getlogger()->debug("FAZ A CHAMADA REMOTA");
	dom = this->getRemoteAPI(SERVICE_ATLYS,&gen);
	// Faz tratamento de erro
	char*fault = m_mem.getTag(dom,XML_TAG_FAULT,0);
	if(fault != NULL)
		tratarErro(dom);
	// adiciona na lista os domnodes
	list->add(dom);
	// verifica se existe mais dados, se existir, chamar novamente a API
	char* bareMore = tuxhp.walkAttr(dom,XML_TAG_OUTPUT_SEARCH_SUBS_BY_ATTR,XML_ATTR_ARE_MORE,0);
	if(bareMore != NULL && strcmp(bareMore,"true") == 0)
	{
		tuxfw_getlogger()->debug("Tem mais dados, aremore = true");
		char *key = tuxhp.walkAttr(dom,XML_TAG_OUTPUT_SEARCH_SUBS_BY_ATTR,XML_ATTR_START_KEY,0);
		this->searchSubscriptionByAttribute(list,attrName,attrValue,key,true);
	}
	tuxfw_getlogger()->debug("Finalizar");
	return dom;
}

DOMNode* ServiceAtlys::getSubscriptionInfo(char*sbscrpId)
{
	tuxfw_getlogger()->debug("ServiceAtlys::getSubscriptionInfo");
	XMLGen gen;
	DOMNode*dom = NULL;
	gen.createTag(XML_TAG_INPUT_GET_SUBS_INFO);
	gen.addProp(XML_ATTR_SBSCRPID,sbscrpId);
	gen.closeTag();
	dom = this->getRemoteAPI(SERVICE_ATLYS,&gen);
	char*fault = m_mem.getTag(dom,XML_TAG_FAULT,0);
	if(fault != NULL)
		tratarErro(dom);
	return dom;
}

DOMNode* ServiceAtlys::maintainSubscriptionServices(ListServicos *list,char*sbscrpId,char* service,bool ativar,char*svcId,char* dia)
{
	tuxfw_getlogger()->debug("ServiceAtlys::maintainSubscriptionServices");
	XMLGen gen;
	DOMNode*dom = NULL;
	gen.createTag(XML_TAG_INPUT_MAINTAIN_SUBS_SERVICES);
	gen.addProp(XML_ATTR_SBSCRPID,sbscrpId);
	gen.createTag(XML_TAG_UPDATE_SVC_ATTRS);
	gen.addProp(XML_TAG_SVC_NM,SERVICO_CONTROLE_CONSUMO);
	gen.addProp(XML_TAG_SVC_TYP,"MV");
	gen.addProp(XML_TAG_SVC_ID,svcId);
	gen.createTag(XML_TAG_SVC_ATTR);

	if(list == NULL)
		throw new TuxBasicSvcException("00W7890","Lista de serviços inválida/NULA");

	// Ler a lista para montar o XML de entrada
	for(int i=0;i<list->sizeOf();i++)
	{
		Servico*servico = (Servico*)list->get(i);
		if(servico==NULL)
			break;
		if(!strcmp(servico->getNome(),service))
		{
			gen.createTag(XML_TAG_ADD_ATTRS);
			gen.addProp(XML_ATTR_ATTR_NM,service);
			if(!strcmp(service,"MV_DAILY") || !strcmp(service,"MV_INVOICERELEASE"))
				gen.addProp(XML_ATTR_ATTR_VALUE,(ativar)?"ON":"OFF"); 
			else
			if(!strcmp(service,"MV_DAYOFWEEK"))
				gen.addProp(XML_ATTR_ATTR_VALUE,(ativar)?dia:"OFF");  
			else
			if(!strcmp(service,"MV_THRESHOLD"))
				gen.addProp(XML_ATTR_ATTR_VALUE,(ativar)?"10":"OFF");
			else
			if(!strcmp(service,"MV_HOTLINETHRESHOLD"))
				gen.addProp(XML_ATTR_ATTR_VALUE,(ativar)?"9":"OFF"); 
			gen.closeTag();
		}
		else
		{
			gen.createTag(XML_TAG_ADD_ATTRS);
			gen.addProp(XML_ATTR_ATTR_NM,servico->getNome());
			if(!strcmp(servico->getNome(),"MV_INVOICERELEASE") ||
				!strcmp(service,"MV_INVOICERELEASE"))
				gen.addProp(XML_ATTR_ATTR_VALUE,servico->getValor());
			else
			if(ativar)
				gen.addProp(XML_ATTR_ATTR_VALUE,"OFF");
			else
				gen.addProp(XML_ATTR_ATTR_VALUE,servico->getValor());
			gen.closeTag();
		}
	}
	gen.closeTag();
	gen.closeTag();
	dom = this->getRemoteAPI(SERVICE_ATLYS,&gen);
	char*fault = m_mem.getTag(dom,XML_TAG_FAULT,0);
	if(fault != NULL)
		tratarErro(dom);

	return dom;
}

DOMNode* ServiceAtlys::onDemandMovistarNtfctn(char*accessNbr)
{
	tuxfw_getlogger()->debug("ServiceAtlys::onDemandMovistarNtfctn");	
	XMLGen gen;
	DOMNode*dom = NULL;
	gen.createTag(XML_TAG_INPUT_ON_DEMAND_MOVISTAR);
	gen.addProp(XML_ATTR_ACCESSNBR,accessNbr);
	gen.closeTag();
	dom = this->getRemoteAPI(SERVICE_ATLYS,&gen);
	char*fault = m_mem.getTag(dom,XML_TAG_FAULT,0);
	if(fault != NULL)
		tratarErro(dom);
	return dom;
}

void ServiceAtlys::tratarErro(DOMNode*domNode)
{
	char* errCd = tuxhp.walkAttr(domNode,XML_TAG_FAULT,XML_ATTR_ERRCD,0);
	char* msg = tuxhp.walkAttr(domNode,XML_TAG_FAULT,XML_ATTR_MSG,0);
	if(!strcmp(errCd,"1263"))
		throw new TuxBasicSvcException("00W1263","Tamanho de campo inválido");
	else
	if(!strcmp(errCd,"1269"))
		throw new TuxBasicSvcException("00W1269","Campo inválido");
	else
	if(!strcmp(errCd,"1277"))
		throw new TuxBasicSvcException("00W1277","Assinatura inválida");
	else
	if(!strcmp(errCd,"1289"))
		throw new TuxBasicSvcException("00W1289","Campo obrigatório não informado");
	else
	if(!strcmp(errCd,"3031"))
		throw new TuxBasicSvcException("00W3031","Erro de aplicação.");
	else
	if(!strcmp(errCd,"5109"))
		throw new TuxBasicSvcException("00W5109","Erro de leitura na base de dados do ATLYS");
	else
	if(!strcmp(errCd,"4275"))
		throw new TuxBasicSvcException("00W4275","A subscrição não possui o serviço/notificação Movistar ativo");
	else
	if(!strcmp(errCd,"4276"))
		throw new TuxBasicSvcException("00W4276","A notificação do Movistar falhou");
	else
	if(!strcmp(errCd,"9029"))
		throw new TuxBasicSvcException("00W9029","Está faltando um parâmetro na tabela SYSTEM_PARM");	
	else
	if(!strcmp(errCd,"4287"))
		throw new TuxBasicSvcException("00W4287","Serviço não pode ser ativo ao mesmo tempo");	
	else
	if(!strcmp(errCd,"2659"))
		throw new TuxBasicSvcException("00W2659","Valor de Atributo inválido");	
	else
	if(!strcmp(errCd,"3029"))
		throw new TuxBasicSvcException("00W3029","Erro não identificado na base de dados");		
	else
		throw new TuxBasicSvcException("00W3000","Erro desconhecido.");
}