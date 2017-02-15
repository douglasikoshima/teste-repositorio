#include "PlugInNGIN.h"

#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/MemoryManager.h"

#include <ctime>
#include <cstdlib>
#include <cstring>

using namespace std;


PlugInNGIN::PlugInNGIN(DOMNode* dn, XMLGen* xg, char* pcIdLinhaSisOri, char* sgSistemaOrigem)
{
	tuxfw_getlogger()->debug("PlugInNGIN::PlugInNGIN");


	tuxhp = new CTuxHelperClever();
	dnode = dn;
	xml_g = xg;

	time_t secs;

	time(&secs);

	srand((unsigned int) secs);

	this->setIdLinhaSistemaOrigem(pcIdLinhaSisOri);

	this->setSgSistemaOrigem(sgSistemaOrigem);

	// Manager BackEndDOMNode
	m_pManangerDOMNode = new ManagerBackEndDOMNode();

	this->iIdPlano = 0;
	this->bOperacaoInterna = false;

}

PlugInNGIN::~PlugInNGIN()
{
	tuxfw_getlogger()->debug("PlugInNGIN::~PlugInNGIN()");

	if(tuxhp)
		delete tuxhp;

	//Manager BackEndDOMNode
	if(m_pManangerDOMNode)
		delete m_pManangerDOMNode;
}

void PlugInNGIN::getHistoricoAtendimento(){}
void PlugInNGIN::getAjustes(){}
void PlugInNGIN::getEstimativa(){}
void PlugInNGIN::getEstimativaSaldo(){}
void PlugInNGIN::getFormaPagamento(){}
void PlugInNGIN::getFidelizacao(){}
void PlugInNGIN::getHistoricoFaturamentos(){}
void PlugInNGIN::getImpedimentos(){}
void PlugInNGIN::getInfoContaCobranca(){}
void PlugInNGIN::getPagamentos(){}
void PlugInNGIN::getInfoFaturamento(){}
void PlugInNGIN::getNotaFiscal(){}
void PlugInNGIN::getServicosURA(){}

char* PlugInNGIN::getServiceNameExt()
{
	sprintf(this->mc_serviceName, "%s%s", this->getServiceName(), SERVICE_NGIN_EXTRATO);
	
	return this->mc_serviceName;
}

void PlugInNGIN::getURASegundaViaConta()
{
	xml_g->addItem("statCom", 1);
	xml_g->addItem("cdCodigoRetorno", "01");
}

void PlugInNGIN::getURABoletoFax()
{
	xml_g->addItem("statCom", 1);
	xml_g->addItem("cdCodigoRetorno", "01");
}

void PlugInNGIN::getURAConta()
{
	xml_g->addItem("statCom", 1);
	xml_g->addItem("cdCodigoRetorno", "01");
}

char* PlugInNGIN::traduzNomeToDescricao(char* pcNomeServico)
{
	if (pcNomeServico == NULL)
		return NULL;

	if (Util::cmp(pcNomeServico, "ChamEspera"))
		return "Chamada em Espera";
	
	else if (Util::cmp(pcNomeServico, "Waaap1X"))
		return "WAAAP";
	
	else if (Util::cmp(pcNomeServico, "IdentCham"))
		return "Identificador de chamada";
	
	else if (Util::cmp(pcNomeServico, "WAP"))
		return "Vivo WAP";
	
	else if (Util::cmp(pcNomeServico, "TransfCham"))
		return "Desvio de chamadas";
	
	else if (Util::cmp(pcNomeServico, "DesbloLD"))
		return "Bloqueio de DDD";
	
	else if (Util::cmp(pcNomeServico, "DesbloqDDI"))
		return "Bloqueio de DDI";
	
	else if (Util::cmp(pcNomeServico, "Correio"))
		return "Caixa Postal";
	
	else
		return pcNomeServico;
}

char* PlugInNGIN::traduzNomeServicoToDescricao(char* pcNomeServico)
{

	if (pcNomeServico == NULL)
		return NULL;
	else if (strcmp(pcNomeServico,"VOICEMAIL") == 0)
		return "Caixa Postal";
	else if (strcmp(pcNomeServico,"CALLID") == 0)
		return "Identificador de chamada";
   	else if (strcmp(pcNomeServico,"UNBLOCKDDD") == 0)
		return "Bloqueio de DDD";
	else if (strcmp(pcNomeServico,"UNBLOCKDDI") == 0) 
	    return "Bloqueio de DDI";
	else if (strcmp(pcNomeServico,"WAP") == 0)
		return "Vivo WAP";
    else if (strcmp(pcNomeServico,"CALLWAIT") == 0)	
		return "Chamada em Espera";
	else if (strcmp(pcNomeServico,"WARN") == 0)
	    return "VIVO AVISA";
	else if (strcmp(pcNomeServico,"CALLFWD") == 0)
	    return "Desvio de chamadas";
	else if (strcmp(pcNomeServico,"WAAAP") == 0)
	    return "WAAAP";
	else
		return pcNomeServico;
}

char* PlugInNGIN::getServicoStatus(char* pcStatus)
{
	if (Util::cmp(pcStatus, "ACT"))
		return "Ativado";
	
	else if (Util::cmp(pcStatus, "SUS"))
		return "Suspenso";
	
	else
		return pcStatus;
}

void PlugInNGIN::trataErro(DOMNode *po_response)
{
	bool   b_habilitaRelease = false;
	char * pc_attrValue = NULL;
	
	if(tuxhp->getRelease())
	{
		tuxhp->setRelease(false);
		b_habilitaRelease = true;
	}

	// Verifica se a resposta é um erro
	if (Util::cmp(pc_attrValue = tuxhp->getAttrValue(po_response, XML_NGN_OUT_RESPONSE_SUCCEED), XML_VAL_NO))
	{
		char vc_errMsg[BUFFER_SIZE_NGN_ERRMSG];
		char* errorCode = NULL;
		char* errorMess = NULL;

		free(pc_attrValue);

		// Obtem o codigo do erro
		errorCode = tuxhp->walkAttr(po_response, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_CODE, 0);

		// Obtem a mensagem do erro
		errorMess = tuxhp->walkAttr(po_response, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_DESC, 0);

		if (((errorCode != NULL) && (errorMess != NULL)) && ((strlen(errorCode) + strlen(errorMess)) < sizeof(vc_errMsg)))
		{
			memset(vc_errMsg, '\0', sizeof(vc_errMsg));

			sprintf(vc_errMsg, "Code: '%s' - Description: '%s'", errorCode, errorMess);
	
			if (errorCode != NULL) XMLString::release(&errorCode);
			if (errorMess != NULL) XMLString::release(&errorMess);

			throw new TuxBasicSvcException(ECD_NGN_GW_RETURN_ERROR, vc_errMsg);
		}
		else
		{
			if (errorCode != NULL) XMLString::release(&errorCode);
			if (errorMess != NULL) XMLString::release(&errorMess);
			
			throw new TuxBasicSvcException(ECD_NGN_GW_RETURN_ERROR, EMSG_NGN_GW_RETURN_ERROR);
		}
	}

	free(pc_attrValue);
	
	tuxhp->setRelease(b_habilitaRelease);
}


void PlugInNGIN::trataErroURA(DOMNode *po_response)
{
	bool   b_habilitaRelease = false;
	char * pc_attrValue = NULL;
	
	if(tuxhp->getRelease())
	{
		tuxhp->setRelease(false);
		b_habilitaRelease = true;
	}

	// Verifica se a resposta é um erro
	if (Util::cmp(pc_attrValue = tuxhp->getAttrValue(po_response, XML_NGN_OUT_RESPONSE_SUCCEED), XML_VAL_NO))
	{
		char vc_errMsg[BUFFER_SIZE_NGN_ERRMSG];
		char* errorCode = NULL;
		char* errorMess = NULL;

		free(pc_attrValue);

		// Obtem o codigo do erro
		errorCode = tuxhp->walkAttr(po_response, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_CODE, 0);

		// Obtem a mensagem do erro
		errorMess = tuxhp->walkAttr(po_response, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_DESC, 0);

		if (((errorCode != NULL) && (errorMess != NULL)) && ((strlen(errorCode) + strlen(errorMess)) < sizeof(vc_errMsg)))
		{
			memset(vc_errMsg, '\0', sizeof(vc_errMsg));
			sprintf(vc_errMsg, "%s|%s", errorCode, errorMess);	
			if (errorCode != NULL) XMLString::release(&errorCode);
			if (errorMess != NULL) XMLString::release(&errorMess);
			throw new TuxBasicSvcException(ECD_NGN_GW_RETURN_ERROR, vc_errMsg);
		}
		else
		{
			memset(vc_errMsg, '\0', sizeof(vc_errMsg));
			sprintf(vc_errMsg, "%s|", errorCode);		
			if (errorCode != NULL) XMLString::release(&errorCode);
			if (errorMess != NULL) XMLString::release(&errorMess);			
			throw new TuxBasicSvcException(ECD_NGN_GW_RETURN_ERROR, vc_errMsg);
		}
	}

	free(pc_attrValue);
	
	tuxhp->setRelease(b_habilitaRelease);
}


char* PlugInNGIN::traduzTipoEndereco(char *pc_tipoEnderecoFinal, char *pc_tipoEndereco)
{

	if(pc_tipoEndereco == NULL || strlen(pc_tipoEndereco) <= 0)
	{
		strcpy(pc_tipoEnderecoFinal, "");
		return pc_tipoEnderecoFinal;
	}

	Util::upper(pc_tipoEndereco);

	if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_AVENIDA)) || 
		(!strcmp(pc_tipoEndereco, "AVENIDA")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_AVENIDA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_PRACA)) || 
			 (!strcmp(pc_tipoEndereco, "PRACA")) || 
			 (!strcmp(pc_tipoEndereco, "PC")) || 
			 (!strcmp(pc_tipoEndereco, "PÇ")) ||
			 (!strcmp(pc_tipoEndereco, "PÇA")) || 
			 (!strcmp(pc_tipoEndereco, "PRAÇA")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_PRACA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_ALAMEDA)) || 
			 (!strcmp(pc_tipoEndereco, "ALAMEDA")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_ALAMEDA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_RUA)) || 
			 (!strcmp(pc_tipoEndereco, "RUA")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_RUA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_PASSAGEM)) || 
			 (!strcmp(pc_tipoEndereco, "PASSAGEM")) || 
			 (!strcmp(pc_tipoEndereco, "PSG")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_PASSAGEM);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_ESTRADA)) || 
			 (!strcmp(pc_tipoEndereco, "ESTRADA")) || 
			 (!strcmp(pc_tipoEndereco, "ETR")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_ESTRADA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_PARQUE)) || 
			 (!strcmp(pc_tipoEndereco, "PARQUE")) || 
			 (!strcmp(pc_tipoEndereco, "PRQ")) || 
			 (!strcmp(pc_tipoEndereco, "PQE")) || 
			 (!strcmp(pc_tipoEndereco, "PRQ MUN")) || 
			 (!strcmp(pc_tipoEndereco, "PRQ RES")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_PARQUE);
	
	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_LADEIRA)) || 
			 (!strcmp(pc_tipoEndereco, "LADEIRA")) || 
			 (!strcmp(pc_tipoEndereco, "LD")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_LADEIRA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_LARGO)) || 
			 (!strcmp(pc_tipoEndereco, "LARGO")) || 
			 (!strcmp(pc_tipoEndereco, "LGR")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_LARGO);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_GALERIA)) || 
			 (!strcmp(pc_tipoEndereco, "GALERIA")) || 
			 (!strcmp(pc_tipoEndereco, "GAL")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_GALERIA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_PONTE)) || 
			 (!strcmp(pc_tipoEndereco, "PONTE")) || 
			 (!strcmp(pc_tipoEndereco, "PT")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_PONTE);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_RODOVIA)) || 
			 (!strcmp(pc_tipoEndereco, "RODOVIA")) || 
			 (!strcmp(pc_tipoEndereco, "RDV")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_RODOVIA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_VIELA)) || 
		     (!strcmp(pc_tipoEndereco, "VIELA")) || 
			 (!strcmp(pc_tipoEndereco, "VEL")) || 
			 (!strcmp(pc_tipoEndereco, "V")) || 
			 (!strcmp(pc_tipoEndereco, "VLA")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_VIELA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_VIADUTO)) || 
			 (!strcmp(pc_tipoEndereco, "VIADUTO")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_VIADUTO);
	
	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_TRAVESSA)) || 
			 (!strcmp(pc_tipoEndereco, "TRAVESSA")) || 
		     (!strcmp(pc_tipoEndereco, "TR")) || 
			 (!strcmp(pc_tipoEndereco, "TV")) || 
			 (!strcmp(pc_tipoEndereco, "TRAVESS")) || 
			 (!strcmp(pc_tipoEndereco, "TV-PART")) || 
			 (!strcmp(pc_tipoEndereco, "TV VELH")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_TRAVESSA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_PRAIA)) || 
		     (!strcmp(pc_tipoEndereco, "PRAIA")) || 
			 (!strcmp(pc_tipoEndereco, "PR")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_PRAIA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_ACESSO)) || 
		     (!strcmp(pc_tipoEndereco, "ACESSO")) || 
			 (!strcmp(pc_tipoEndereco, "AC")) || 
			 (!strcmp(pc_tipoEndereco, "AC LOC")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_ACESSO);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_JARDIM)) || 
			 (!strcmp(pc_tipoEndereco, "JARDIM")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_JARDIM);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_TREVO)) || 
			 (!strcmp(pc_tipoEndereco, "TREVO")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_TREVO);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_VIA)) || 
			 (!strcmp(pc_tipoEndereco, "V COLET")) || 
		     (!strcmp(pc_tipoEndereco, "V-AC")) || 
			 (!strcmp(pc_tipoEndereco, "V-PED")) || 
			 (!strcmp(pc_tipoEndereco, "V-EVD")) || 
			 (!strcmp(pc_tipoEndereco, "V-EXP")) || 
			 (!strcmp(pc_tipoEndereco, "V LIT")) || 
			 (!strcmp(pc_tipoEndereco, "V LOCAL")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_VIA);

	else if( (!strcmp(pc_tipoEndereco, XML_NGN_VAL_BECO)) || 
			 (!strcmp(pc_tipoEndereco, "BECO")))
		strcpy(pc_tipoEnderecoFinal, XML_NGN_VAL_BECO);

	else
	{
		strcpy(pc_tipoEnderecoFinal, "");
	}

	return pc_tipoEnderecoFinal;

}

char* PlugInNGIN::convertESNDecToHex(char *pc_EsnHex, char *pc_EsnDec)
{
	char c_ESN_P1[ESN_DEC_P1 + 1] = "";
	char c_ESN_P2[ESN_DEC_P2 + 1] = "";

	long l_ESN_P1;
	long l_ESN_P2;

	if(strlen(pc_EsnDec) != ESN_DEC_LEN)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_ESN, EMSG_NGN_OUT_FLDNM_ESN);

	strncpy(c_ESN_P1, pc_EsnDec, ESN_DEC_P1);
	l_ESN_P1 = atol(c_ESN_P1);

	strncpy(c_ESN_P2, &pc_EsnDec[ESN_DEC_P1], ESN_DEC_P2);
	l_ESN_P2 = atol(c_ESN_P2);
	
	sprintf(pc_EsnHex, "%0.2lX%0.6lX", l_ESN_P1, l_ESN_P2);

	return pc_EsnHex;

}
