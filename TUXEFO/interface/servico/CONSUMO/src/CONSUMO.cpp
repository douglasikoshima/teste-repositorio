#define CONSUMOCPP

#include<tuxfw.h>
#include"../../../negocio/ControleConsumo/include/ControleConsumo.h"
#include"../../../negocio/ControleConsumo/include/Util.h"

DECLARE_TUXEDO_SERVICE(CONSUMO);


void implCONSUMO::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
	ControleConsumo controle;
	char*operacao = this->walkTree(dnode,"opcao",0);
	char*status = this->walkTree(dnode,"status",0);
	char*servico = this->walkTree(dnode,"servico",0);
	char*dia = this->walkTree(dnode,"dia",0);
	char*cdDDD = this->walkTree(dnode,"cdDDD",0);
	char*cdNumTelefone = this->walkTree(dnode,"cdNumTelefone",0);
	char accessNbr[11+1];
	char nomeServico[256];
	char *pservico = NULL;
	char *cdContato = NULL;
	try
	{
		if(cdDDD==NULL || !strcmp(cdDDD,""))
			throw new TuxBasicSvcException("00W0052","Tag de ddd não encontrada");
		if(cdNumTelefone==NULL || !strcmp(cdNumTelefone,""))
			throw new TuxBasicSvcException("00W0053","Tag de telefone não encontrada");
		if(status==NULL || !strcmp(status,""))
			throw new TuxBasicSvcException("00W0054","Tag de status não encontrada");

		// verifica se a linha existe é atlys e não está bloqueada
		if(Util::verificaLinha(cdDDD,cdNumTelefone) == 0)
			throw new TuxBasicSvcException("00W0057","Conta bloqueada");

		memset(nomeServico,0,sizeof(nomeServico));
		memset(accessNbr,0,sizeof(accessNbr));
		strncpy(accessNbr,cdDDD,2);
		strcpy(accessNbr+2,cdNumTelefone);

		tuxfw_getlogger()->debug("accessNbr: [%s]",accessNbr);

		// converte a tag servico
		if(servico != NULL)
		{
			int numero = 0;
			try
			{
				numero = atoi(servico);
			}
			catch(...)
			{
				throw new TuxBasicSvcException("00W0056","Serviço inválido");
			}
			switch(numero)
			{
				case 1: strcpy(nomeServico,"DEMANDA");             
						cdContato="CONSUMO_DEMANDA_ATIVAR"; break;
				case 2: strcpy(nomeServico,"MV_DAILY");
						cdContato=(operacao!=NULL && !strcmp(operacao,"1"))?"CONSUMO_A_MV_DAILY":"CONSUMO_D_MV_DAILY"; break;
				case 3: strcpy(nomeServico,"MV_DAYOFWEEK");
						cdContato=(operacao!=NULL && !strcmp(operacao,"1"))?"CONSUMO_A_MV_DAYOFWEEK":"CONSUMO_D_MV_DAYOFWEEK"; break;
				case 4: strcpy(nomeServico,"MV_INVOICERELEASE");
						cdContato=(operacao!=NULL && !strcmp(operacao,"1"))?"CONSUMO_A_MV_INVOICERELEASE":"CONSUMO_D_MV_INVOICERELEASE"; break;
				case 5: strcpy(nomeServico,"MV_THRESHOLD");
						cdContato=(operacao!=NULL && !strcmp(operacao,"1"))?"CONSUMO_A_MV_THRESHOLD":"CONSUMO_D_MV_THRESHOLD";break;
				case 6: strcpy(nomeServico,"MV_HOTLINETHRESHOLD");
						cdContato=(operacao!=NULL && !strcmp(operacao,"1"))?"CONSUMO_A_MV_HOTLINETHRESHOLD":"CONSUMO_D_MV_HOTLINETHRESHOLD";break;
				default:
					throw new TuxBasicSvcException("00W0056","Serviço inválido");
			}
			pservico = nomeServico;
		}



		if(pservico!=NULL && !strcmp(pservico,"DEMANDA"))
		{
			controle.avisoSobreDemanda(accessNbr);
		}
		else
		{
			if(!strcmp(status,"P"))
			{
				tuxfw_getlogger()->debug("status de pesquisa");
				controle.getControleConsumo(xml_g,accessNbr);
			}
			else
			if(!strcmp(status,"C"))
			{
				tuxfw_getlogger()->debug("status de confirmação"); 
				if(operacao==NULL || !strcmp(operacao,""))
					throw new TuxBasicSvcException("00W0050","Tag de operação não encontrada");
				if(pservico==NULL || !strcmp(pservico,""))
					throw new TuxBasicSvcException("00W0051","Tag de serviço não encontrada");
				if(!strcmp(pservico,"MV_DAYOFWEEK") && dia == NULL)
					throw new TuxBasicSvcException("00W0055","Tag de dia não encontrada");
				controle.setControleConsumo(xml_g,accessNbr,pservico,operacao,dia);
			}
		}

		// registrar contato somente para a confirmação
		if(!strcmp(status,"C"))
		{			
			controle.registrarContato(cdDDD,cdNumTelefone,cdContato,getUser());
		}

	}
	catch(...)
	{
		XMLString::release(&operacao);
		XMLString::release(&status);
		XMLString::release(&servico);
		XMLString::release(&dia);
		XMLString::release(&cdDDD);
		XMLString::release(&cdNumTelefone);
		throw;
	}

	XMLString::release(&operacao);
	XMLString::release(&status);
	XMLString::release(&servico);
	XMLString::release(&dia);
	XMLString::release(&cdDDD);
	XMLString::release(&cdNumTelefone);

	setStatusCode("00I0000","Sucesso");
}