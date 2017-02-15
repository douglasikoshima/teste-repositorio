// ControleConsumo.cpp: implementation of the ControleConsumo class.
//
//////////////////////////////////////////////////////////////////////

#include "ControleConsumo.h"
#include "Constants.h"

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

ControleConsumo::ControleConsumo()
{
	this->m_habilidaPercentual = false;
	this->m_sbscrpId = NULL;
	this->m_svcId = NULL;
}

ControleConsumo::~ControleConsumo()
{

}

int ControleConsumo::getControleConsumo(XMLGen *xmlgen,char*accessNbr)
{
	tuxfw_getlogger()->debug("ControleConsumo::getControleConsumo");
	ListDOMNodes list;
	int i = 0;
	int j = 0;	
	this->searchSubscriptionByAttribute(&list,"ACCESS_NBR",accessNbr,NULL,false);
	for(i=0;i<list.sizeOf();i++)
	{
		DOMNode*node = (DOMNode*)list.get(i);
		DOMNode*nodeOutput = tuxhp.walkDOM(node,XML_TAG_OUTPUT_SEARCH_SUBS_BY_ATTR,0);
		char*nbr = NULL;
		for(j=0;(nbr = tuxhp.walkAttr(nodeOutput,XML_TAG_SBSCRP_SEARCH,XML_ATTR_ACCESSNBR,j)) != NULL;j++)
		{
			if(!strcmp(nbr,accessNbr))
			{
				tuxfw_getlogger()->debug("achou subscriptionid para a linha");
				this->m_sbscrpId = tuxhp.walkAttr(nodeOutput,XML_TAG_SBSCRP_SEARCH,XML_ATTR_SBSCRPID,j);
				break;
			}
		}		
	}
	
	if(this->m_sbscrpId == NULL)
		throw new TuxBasicSvcException("00W0001","Subscri��o n�o encontrada para linha");

	// Controle de consumo como membro do pacote
	DOMNode*node = this->getSubscriptionInfo(this->m_sbscrpId);

	if(node == NULL)
		throw new TuxBasicSvcException("00W1001","Retorno da API getSubscriptionInfo NULA");

	DOMNode*nodeSubscription = tuxhp.walkDOM(node,XML_TAG_OUTPUT_GET_SUBS_INFO,0);
	DOMNode*nodeSvcAsgmInfo = NULL;
	DOMNode*nodeAtributos = NULL;
	i=0;
	while( (nodeSvcAsgmInfo = tuxhp.walkDOM(nodeSubscription,XML_TAG_SVC_ASGM_INFO,i)) != NULL)
	{
		// Controle de consumo como membro do pacote
		j=0;
		DOMNode*nodeMemberSvcs = NULL;
		while( (nodeMemberSvcs = tuxhp.walkDOM(nodeSvcAsgmInfo,XML_TAG_MEMBER_SVCS,j)) != NULL)
		{
			char*svcTyp = tuxhp.getAttrValue(nodeMemberSvcs,XML_TAG_SVC_TYP);
			if(svcTyp!=NULL && !strcmp(svcTyp,"MV"))
			{
				tuxfw_getlogger()->debug("achou o Controle de consumo como membro do pacote");			
				this->m_svcId = tuxhp.getAttrValue(nodeMemberSvcs,XML_ATTR_SVC_ID);						
				nodeAtributos = tuxhp.walkDOM(nodeMemberSvcs,XML_TAG_SVC_ATTR,0);
				break;
			}
			j++;
		}
		// Controle de consumo como servi�o prim�rio
		DOMNode*primarySvc = NULL;
		int n = 0;
		while( (primarySvc = tuxhp.walkDOM(nodeSvcAsgmInfo,XML_TAG_PRIMARY_SVC,n)) != NULL)
		{
			char*svcTyp = tuxhp.getAttrValue(primarySvc,XML_TAG_SVC_TYP);
			if(svcTyp!=NULL && !strcmp(svcTyp,"MV"))
			{
				tuxfw_getlogger()->debug("achou o Controle de consumo como servi�o prim�rio");			
				this->m_svcId = tuxhp.getAttrValue(primarySvc,XML_ATTR_SVC_ID);						
				nodeAtributos = tuxhp.walkDOM(primarySvc,XML_TAG_SVC_ATTR,0);
				break;
			}
			n++;
		}
		i++;
	}		

	// Verifica o membersvc BASICO N�MERO
	i=0;
	bool basicoNumero = false;
	while( (nodeSvcAsgmInfo = tuxhp.walkDOM(nodeSubscription,XML_TAG_SVC_ASGM_INFO,i)) != NULL)
	{
		j=0;
		DOMNode*nodeMemberSvcs = NULL;
		while( (nodeMemberSvcs = tuxhp.walkDOM(nodeSvcAsgmInfo,XML_TAG_MEMBER_SVCS,j)) != NULL)
		{
			char*svcTyp = tuxhp.getAttrValue(nodeMemberSvcs,XML_ATTR_SVC_TYP);
			if(svcTyp!=NULL && !strcmp(svcTyp,"V1"))
			{
				tuxfw_getlogger()->debug("BASICO NUM�RO encontrado");	
				basicoNumero = true;
				break;
			}
			j++;
		}
		if(basicoNumero) break;
		i++;
	}
	// se encontrou o basico n�mero
	if(basicoNumero)
	{
		DOMNode*nodeCharge = NULL;
		int l = 0;
		while( (nodeCharge = tuxhp.walkDOM(nodeSvcAsgmInfo,XML_TAG_CHARGE,l)) != NULL)
		{
			char*chgTypeCd = tuxhp.getAttrValue(nodeCharge,XML_ATTR_CHG_TYPE_CD);
			if(chgTypeCd!=NULL && !strcmp(chgTypeCd,"F"))
			{
				//tuxfw_getlogger()->debug("Encontrou o chgTypeCd F");
				this->m_habilidaPercentual = true;
			}
			l++;
		}
	}


	if(this->m_svcId == NULL)
		throw new TuxBasicSvcException("00W0002","Servi�o MOVISTAR n�o est� ativo");

	// Verificar os atributos do servi�o
	DOMNode*nodeAddlAttrs = NULL;
	int k=0;
	xmlgen->createTag("ListaControleConsumoVO");
	xmlgen->addProp("xmlns","servicos.ura/vo");	
	while( (nodeAddlAttrs = tuxhp.walkDOM(nodeAtributos,XML_TAG_ADD_ATTRS,k)) != NULL)
	{
		char*attrNm = tuxhp.getAttrValue(nodeAddlAttrs,XML_ATTR_ATTR_NM);
		char*attrValue = tuxhp.getAttrValue(nodeAddlAttrs,XML_ATTR_ATTR_VALUE);		
		// servi�o passado
		if(attrNm!=NULL && strcmp(attrNm,"")!=0)
		{
			xmlgen->createTag("ControleConsumoItem");
			xmlgen->addItem("nome",attrNm);
			xmlgen->addItem("valor",attrValue);
			xmlgen->closeTag();
			m_servicos.add(new Servico(attrNm,attrValue));
		}
		k++;
	}
	xmlgen->closeTag();
	xmlgen->addItem("IndTarifa","");
	xmlgen->addItem("ValorTarifa","");

	return 1;
}

int ControleConsumo::setControleConsumo(XMLGen *xmlgen,char*accessNbr,char*serv,char*operacao,char*dia)
{
	tuxfw_getlogger()->debug("ControleConsumo::setControleConsumo");
	XMLGen gen;
	this->getControleConsumo(&gen,accessNbr);
	int i = 0;
	char*valor = NULL;
	bool existeServico = false;
	bool oper;
	for(i=0;i<m_servicos.sizeOf();i++)
	{
		Servico*servico = (Servico*)m_servicos.get(i);
		if(!strcmp(servico->getNome(),serv))
		{
			valor = servico->getValor();
			existeServico = true;
		}			
	}
	if(!existeServico)
		throw new TuxBasicSvcException("00W0003","Servi�o Inexistente");

	if(valor == NULL || !strcmp(valor,""))
		throw new TuxBasicSvcException("00W0006","status do servi�o vazio ou inexistente");

	// desativar o servi�o e servi�o j� desativado
	if(!strcmp(operacao,"0") && !strcmp(valor,"OFF"))
			throw new TuxBasicSvcException("00W0004","Servi�o j� se encontra desativado");

	// ativar servi�o e servi�o j� est� ativado
	if(!strcmp(operacao,"1") && strcmp(valor,"OFF")!=0)
	{
		if(!strcmp(serv,"MV_DAYOFWEEK"))
		{
			if(dia!=NULL && valor!=NULL && !strcmp(dia,valor))
				throw new TuxBasicSvcException("00W0005","Servi�o j� se encontra ativado");
		}
		else
			throw new TuxBasicSvcException("00W0005","Servi�o j� se encontra ativado");
	}

	if((!strcmp(serv,"MV_THRESHOLD") || !strcmp(serv,"MV_HOTLINETHRESHOLD"))
		&& !m_habilidaPercentual)
		throw new TuxBasicSvcException("00W0007","Pacote n�o cont�m uso inclu�do");


	if(!strcmp(operacao,"1"))
		oper = true;
	else
		oper = false;

	this->maintainSubscriptionServices(&m_servicos,m_sbscrpId,serv,oper,m_svcId,dia);

	return 1;
}

int ControleConsumo::avisoSobreDemanda(char*accessNbr)
{
	tuxfw_getlogger()->debug("ControleConsumo::avisoSobreDemanda()");
	XMLGen gen;
	this->getControleConsumo(&gen,accessNbr);
	this->onDemandMovistarNtfctn(accessNbr);
	return 1;
}


int ControleConsumo::registrarContato(char*ddd,char*linha,char*cdContato,char*idUser)
{
	tuxfw_getlogger()->debug("ControleConsumo::registrarContato()");
	int retorno = 1;
	try
	{
		CRegistroContato o_RegContato;

		o_RegContato.setInRegistrarContato("1");
		o_RegContato.setCdAreaRegistro(ddd);
		o_RegContato.setNrLinha(linha);
		o_RegContato.setIdTipoRelacionamento("2");
		o_RegContato.setCdContato(cdContato);
		o_RegContato.setIdCanal("9");
		o_RegContato.setIdUser(idUser);
		o_RegContato.setIdGrupoAbertura("1534");

		retorno = o_RegContato.registrarContato();
	}
	catch(...)
	{
		retorno = -1;
	}
	return retorno;
}