#include "TuxAtlysBE.h"

#include <tuxfw.h>
#include "../../../negocio/CreatorPlugInBE/include/CreatorPlugInBE.h"

#include <cstring>

using namespace std;


// Declaracao do do serviço
DECLARE_TUXEDO_SERVICE(TUXATLYSBE);


void implTUXATLYSBE::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	char*     pc_Linha    = NULL;
	char*     pc_Operacao = NULL;
	PlugInATLYS* po_PlugIn   = NULL;
	time_t    t_secStart;

	t_secStart = time(NULL);

	tuxfw_getlogger()->debug("Executando TuxAtlysBE");


	// Obtem o numero da linha
	pc_Linha = walkTree(dnode, TAG_XML_IN_PROXY_LINHA, 0);

	if (pc_Linha == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_PROXY_LINHA, EMSG_TAG_XML_IN_NE_PROXY_LINHA);

	/*	Alteração para assumir ATLYS se a linha nao for passada.

	if (*pc_Linha == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_PROXY_LINHA, EMSG_TAG_XML_IN_VV_PROXY_LINHA);

	if (strlen(pc_Linha) != 10)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_PROXY_LINHA, EMSG_TAG_XML_IN_VI_PROXY_LINHA);
	*/


	// Obtem a operacao
	pc_Operacao = walkTree(dnode, TAG_XML_IN_PROXY_OPERACAO, 0);

	if (pc_Operacao == NULL)
	{
		XMLString::release(&pc_Linha);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_PROXY_OPERACAO, EMSG_TAG_XML_IN_NE_PROXY_OPERACAO);
	}

	if (*pc_Operacao == '\0')
	{
		XMLString::release(&pc_Linha);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_PROXY_OPERACAO, EMSG_TAG_XML_IN_VV_PROXY_OPERACAO);
	}

	// Debug
	tuxfw_getlogger()->debug("TAG %s: '%s'", TAG_XML_IN_PROXY_LINHA, pc_Linha);
	tuxfw_getlogger()->debug("TAG %s: '%s'", TAG_XML_IN_PROXY_OPERACAO, pc_Operacao);


	try
	{
		// Obtem a Instancia do PlugIn
		po_PlugIn = CreatorPlugInBE::getPlugInATLYS(dnode, xml_g, pc_Linha);
		po_PlugIn->setUser(this->getUser());
		po_PlugIn->setServiceName(WRAPPER_ATLYS);

		// Operacoes 'Get'
		if(strcmp(pc_Operacao, opGetDetalheLinha) == 0)
			po_PlugIn->getDetalheLinha();

		else if(strcmp(pc_Operacao, opGetDetalhesSaldo) == 0)
			po_PlugIn->getDetalhesSaldo();

		else if(strcmp(pc_Operacao, opGetHistoricoMovimentos) == 0)
			po_PlugIn->getHistoricoMovimentos();
		
		else if(strcmp(pc_Operacao, opGetExtrato) == 0)
			po_PlugIn->getExtrato();
		
		else if(strcmp(pc_Operacao, opGetPromocoes) == 0)
			po_PlugIn->getPromocoes();
		
		else if(strcmp(pc_Operacao, opGetServicos) == 0)
			po_PlugIn->getServicos();
		
		else if(strcmp(pc_Operacao, opGetFavoritos) == 0)
			po_PlugIn->getFavoritos();
		
		else if(strcmp(pc_Operacao, opGetHistoricoAtendimento) == 0)		
			po_PlugIn->getHistoricoAtendimento();
		
		else if(strcmp(pc_Operacao, opGetAjustes) == 0)
			po_PlugIn->getAjustes();
		
		else if(strcmp(pc_Operacao, opGetEstimativa) == 0)
			po_PlugIn->getEstimativa();
		
		else if(strcmp(pc_Operacao, opGetEstimativaSaldo) == 0)
			po_PlugIn->getEstimativaSaldo();
		
		else if(strcmp(pc_Operacao, opGetFormaPagamento) == 0)
			po_PlugIn->getFormaPagamento();
		
		else if(strcmp(pc_Operacao, opGetFidelizacao) == 0)
			po_PlugIn->getFidelizacao();
		
		else if(strcmp(pc_Operacao, opGetHistoricoFaturamentos) == 0)
			po_PlugIn->getHistoricoFaturamentos();
		
		else if(strcmp(pc_Operacao, opGetImpedimentos) == 0)
			po_PlugIn->getImpedimentos();
		
		else if(strcmp(pc_Operacao, opGetInfoContaCobranca) == 0)
			po_PlugIn->getInfoContaCobranca();
		
		else if(strcmp(pc_Operacao, opGetPagamentos) == 0)
			po_PlugIn->getPagamentos();
		
		else if(strcmp(pc_Operacao, opGetInfoFaturamento) == 0)
			po_PlugIn->getInfoFaturamento();
		
		else if(strcmp(pc_Operacao, opGetURASegundaViaConta) == 0)
			po_PlugIn->getURASegundaViaConta();
		
		else if(strcmp(pc_Operacao, opGetURABoletoFax) == 0)
			po_PlugIn->getURABoletoFax();
		
		else if(strcmp(pc_Operacao, opGetURAConta) == 0)
			po_PlugIn->getURAConta();
		
		else if(strcmp(pc_Operacao, opGetESN) == 0)
			po_PlugIn->getESN();
		
		else if(strcmp(pc_Operacao, opGetNotaFiscal) == 0)
			po_PlugIn->getNotaFiscal();

		else if(strcmp(pc_Operacao, opGetPlano) == 0)
			po_PlugIn->getPlano();

		else if(strcmp(pc_Operacao, opGetTarifaReduzida) == 0)
			po_PlugIn->getTarifaReduzida();

		else if(strcmp(pc_Operacao, opGetDetalhesConsumo) == 0)
			po_PlugIn->getDetalhesConsumo();


		// Operacoes 'Set'
		else if(strcmp(pc_Operacao, opSetServico) == 0)
			po_PlugIn->setServico();
		
		else if(strcmp(pc_Operacao, opSetSuspendeCelular) == 0)			
			po_PlugIn->setSuspendeCelular();

		else if(strcmp(pc_Operacao, opSetReligueCelular) == 0)
			po_PlugIn->setReligueCelular();

		else if(strcmp(pc_Operacao, opSetFavorito) == 0)
			po_PlugIn->setFavorito();
		
		else if(strcmp(pc_Operacao, opSetInterceptacao) == 0)
			po_PlugIn->setInterceptacao();
		
		else if(strcmp(pc_Operacao, opSetCliente) == 0)
			po_PlugIn->setCliente();

		else if(strcmp(pc_Operacao, opSetPlano) == 0)
			po_PlugIn->setPlano();

		else if(strcmp(pc_Operacao, opSetTarifaReduzida) == 0)
			po_PlugIn->setTarifaReduzida();
		
		else if (strcmp(pc_Operacao, opSetCaixaPostal) == 0)
			po_PlugIn->setCaixaPostal();		

		else
			throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_OPERACAO, EMSG_TAG_XML_IN_VI_OPERACAO);

	}
	catch(...)
	{
		tuxfw_getlogger()->debug("TuxProxyBE Exception!");
	
		if (po_PlugIn != NULL)
			delete po_PlugIn;

		tuxfw_getlogger()->debug("TUX LIN %s OP %s TIME %ld", pc_Linha, pc_Operacao, time(NULL) - t_secStart);

		XMLString::release(&pc_Linha);
		XMLString::release(&pc_Operacao);

		throw;
	}
	
	delete po_PlugIn;

	setStatusCode(ICD_EXECUTION_OK, IMSG_EXECUTION_OK);

	tuxfw_getlogger()->debug("TUX LIN %s OP %s TIME %ld", pc_Linha, pc_Operacao, time(NULL) - t_secStart);

	XMLString::release(&pc_Linha);
	XMLString::release(&pc_Operacao);

	tuxfw_getlogger()->debug("TuxProxyBE Finalizado");
}



