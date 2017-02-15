#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/MemoryManager.h"

#include <cstring>

using namespace std;


PlugInPPS::PlugInPPS(DOMNode* dn, XMLGen* xg)
{
	tuxfw_getlogger()->debug("PlugInPPS::PlugInPPS");


	tuxhp = new CTuxHelperClever();
	dnode = dn;
	xml_g = xg;

	// Manager BackEndDOMNode
	m_pManangerDOMNode = new ManagerBackEndDOMNode();
}

PlugInPPS::~PlugInPPS()
{  
	tuxfw_getlogger()->debug("PlugInPPS::~PlugInPPS()");


	if (tuxhp) 
	 delete tuxhp;

	//Manager BackEndDOMNode
	if(m_pManangerDOMNode)
		delete m_pManangerDOMNode;
}

void PlugInPPS::getAjustes(){}
void PlugInPPS::getEstimativa(){}
void PlugInPPS::getEstimativaSaldo(){}
void PlugInPPS::getFormaPagamento(){}
void PlugInPPS::getFidelizacao(){}
void PlugInPPS::getHistoricoFaturamentos(){}
void PlugInPPS::getImpedimentos(){}
void PlugInPPS::getInfoContaCobranca(){}
void PlugInPPS::getPagamentos(){}
void PlugInPPS::getInfoFaturamento(){}
void PlugInPPS::getServicosURA(){}

void PlugInPPS::getURASegundaViaConta()
{
	xml_g->addItem("statCom",1);
	xml_g->addItem("cdCodigoRetorno","01");
}

void PlugInPPS::getURABoletoFax()
{
	xml_g->addItem("statCom",1);
	xml_g->addItem("cdCodigoRetorno","01");
}

void PlugInPPS::getURAConta()
{
	xml_g->addItem("statCom",1);
	xml_g->addItem("cdCodigoRetorno","01");
}

char* PlugInPPS::traduzAtributoToNomeServico(char* pcAtributo)
{

	if (pcAtributo == NULL)
		return NULL;
	else if (strcmp(pcAtributo,"2") == 0)
		return "VOICEMAIL";
	else if (strcmp(pcAtributo,"7") == 0)
		return "CALLID";
   	else if (strcmp(pcAtributo,"36") == 0)
		return "UNBLOCKDDD";
	else if (strcmp(pcAtributo,"3") == 0) 
	    return "UNBLOCKDDI";
	else if (strcmp(pcAtributo,"17") == 0)
		return "WAP";
    else if (strcmp(pcAtributo,"8") == 0)	
		return "CALLWAIT";
	else if (strcmp(pcAtributo,"43") == 0)
	    return "WARN";
	else
		return pcAtributo;
}

char* PlugInPPS::traduzNomeServicoToDescricao(char* pcNomeServico)
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

char* PlugInPPS::getServicoStatus(char *pcStatus)
{
/*
1|2|Bar. Internacionais|
1|6|Desbloqueamento|
1|7|Prazo de Validade|
2|2|PIN inicializado|
2|3|Aviso de pagamento|
2|4|Aviso retirado|
10|1|Portugues|
10|2|Ingles|
11|1|ORGA|
11|2|IN CET|
11|3|IN SIEMENS|
12|0|Basico|
24|2|VINCULO SUSPENSO|
24|3|VINCULO TROCADO|
25|4|Alterado - Historico|
25|5|Atingiu Limite Lista|
25|6|Alterado-Back Office|
30|4|Alterado - historico|
30|5|Atingiu Limite Lista|
30|6|Alterado-Back Office|
31|3|Efetivar Desat|
31|4|Efetivar Ativ|
31|5|Efetivar Susp|
35|4|Alterado - Historico|
35|5|Atingiu Limite Lista|
35|6|Alterado-Back Office|
37|4|Alterado - Historico|
37|5|Atingiu Limite Lista|
39|4|Alterado - Historico|
39|5|Atingiu Limite Lista|
40|4|Alterado - Historico|
40|5|Atingiu Limite Lista|
42|4|Alterado - Historico|
42|5|Atingiu Limite Lista|
*/

	char ativado[][24] = {
		"Ativado",
		"ATIVADO",
		"Preativado",
		"Reativacao",
		"Reativação ADM",
		"Ativado Analisado",
		"Disponivel",
		"Liberado",
		"Ativo",
		"ATIVO",
		"Livre",
		"SAO PAULO ATIVADO",
		"PALMEIRAS ATIVADO",
		"SANTOS ATIVADO",
		"CORINTHIANS ATIVADO",
		"PORTUGUESA ATIVADO",
		"GUARANI ATIVADO",
		"PONTE PRETA ATIVADO",
		"VINCULO ATIVADO",
		"T Ilimitado ATIVO",
		"T Limitado ATIVO",
		"Ativado Cancelado",
		"Ativado Renovacao",
		"Ativ.Rest+BPT",
		"ATIVO v2 MIGRACAO",
		"ATIVO v2 ADESAO",
		"ATIVO PROMOCAO"
	};

	for (unsigned int i = 0; i < (sizeof(ativado) / 24); i++)
		if (Util::cmp(ativado[i], pcStatus))
			return "Ativado";


	char desativado[][24] = {
		"Desativado",
		"DESATIVADO",
		"Barrado",
		"Expirado",
		"DESATIVADO A PEDIDO",
		"Desativo",
		"Cancelado",
		"Desactivado",
		"DESATIVO",
		"T Cancelado",
		"Cancelado-Troca Area",
		"VINCULO DESATIVADO",
		"CANCELADO PROMOCIONA",
		"T Ilimitado DESATIVO",
		"T Limitado DESATIVO",
		"Desat.Rest+BPT",
		"DESATIVO v2 MIGRACAO",
		"DESATIVO v2 ADESAO"
	};

	for (unsigned int j = 0; j < (sizeof(desativado) / 24); j++)
		if (Util::cmp(desativado[j], pcStatus))
			return "Desativado";


	char suspenso[][24] = {
		"Suspenso",
		"SUSPENSO",
		"Suspenso Restricao",
		"SUSPENSO PROMOCIONAL"
	};

	for (unsigned int k = 0; k < (sizeof(suspenso) / 24); k++)
		if (Util::cmp(suspenso[k], pcStatus))
			return "Suspenso";


	return pcStatus;
}

void PlugInPPS::trataErro(DOMNode *po_ROOT)
{

	// Variavel para armazenar o possivel codigo de erro
	char *pc_ERR = NULL;

	// Verifica a tag ERR
	pc_ERR = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_ERR, 0);

	if (pc_ERR == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_PPS_OUT_NE_ERR, EMSG_TAG_XML_PPS_OUT_NE_ERR);

	// Se o GW retornou erro...
	if (atoi(pc_ERR) != 0)
	{
		char *pc_ERRMSG = NULL;

		// Verifica a tag ERR_MENS
		pc_ERRMSG = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_ERRMSG, 0);

		if (pc_ERRMSG == NULL)
		{
			XMLString::release(&pc_ERR);

			throw new TuxBasicSvcException(ECD_TAG_XML_PPS_OUT_NE_ERRMSG, EMSG_TAG_XML_PPS_OUT_NE_ERRMSG);
		}

		if ((strlen(pc_ERR) + strlen(pc_ERRMSG)) < BUFFER_SIZE_PPS_ERRMSG)
		{
			char vc_errMsg[BUFFER_SIZE_PPS_ERRMSG];

			memset(vc_errMsg, '\0', sizeof(vc_errMsg));

			sprintf(vc_errMsg, "Code: '%s' - Description: '%s'", pc_ERR, pc_ERRMSG);
	
			XMLString::release(&pc_ERR);
			XMLString::release(&pc_ERRMSG);

			throw new TuxBasicSvcException(ECD_PPS_GW_RETURN_ERROR, vc_errMsg);
		}
		else
		{
			XMLString::release(&pc_ERR);
			XMLString::release(&pc_ERRMSG);

			throw new TuxBasicSvcException(ECD_PPS_GW_RETURN_ERROR, EMSG_PPS_GW_RETURN_ERROR);
		}
	}

	XMLString::release(&pc_ERR);

}