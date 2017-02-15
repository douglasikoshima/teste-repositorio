/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:31 $
 **/

/*
 * Serviço de consulta de pontos, ativado via URA
 */

#include "../../../negocio/commons/include/PontosURA.h"
#include "../../../negocio/commons/include/Commom.hpp"
#include "../../../negocio/commons/include/stLinha.hpp"
#include <tuxfw.h>
#include "URSPPONTOS.h"
#include "../../../negocio/PlugInBE/include/Util.h"
#include "../../../negocio/PlugInBE/include/RegistroContato.h"

DECLARE_TUXEDO_SERVICE(URSPPONTOS);

void implURSPPONTOS::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	char *pcDDD     = NULL;
	char *pcFone    = NULL;
	char *pcPontos  = NULL;
	char *pcMessage = NULL;
	DOMNode *poSaida   = NULL;
	XMLGen *entrada = NULL;
	TuxMessage*saida = NULL; 

	struct stStatuslinha stLinha;
	memset( &stLinha, 0, sizeof(stLinha));
	stLinha.ntStatus = RET_OK;

	time_t curr;
	tm local;
	
	int iAvailability = 0;
	
	XMLGen xmlPts;
	XMLGen xmlPtsFilled;

	// Busca pelas informações necessárias para execução do serviço	
	pcDDD = walkTree(dnode, TAG_DDD, 0);		// DDD	
	pcFone = walkTree(dnode, TAG_FONE, 0);		// Fone

        if ((!pcDDD) || (!pcFone) || (!*pcDDD) || (!*pcFone) || (!IsNumeric(pcDDD)) || (!IsNumeric(pcFone)))
        {
                stLinha.ntStatus = ERR_IN_PARAM;
        }

	if (stLinha.ntStatus == RET_OK)
	{
		// Cria classe de saldo de pontos
		CPontosURA oSPontos;
		
		// Popula dados para a chamada
		oSPontos.setDDD(pcDDD);
		oSPontos.setNrFone(pcFone);
		oSPontos.setNrLanctos(6);
		oSPontos.setDtQuebra("01/01/2004");
		oSPontos.setInExp(false);
		oSPontos.setInLinhas(true);
	
		try
		{
			oSPontos.ConsultaPontos();
			poSaida = oSPontos.getXMLRetorno();
			if (poSaida == NULL) 
				stLinha.ntStatus = ERR_SUBROUTINES;
		}
		catch( ... )
		{
			stLinha.ntStatus = ERR_SUBROUTINES;
		}
	}

	// Registrar o Contato
	CRegistroContato o_RegContato(dnode);
	int retorno = 0;
	try{
		retorno = o_RegContato.registrarContato();
	}catch(...){
		retorno = -1;
	}
	if (retorno != 0)
		tuxfw_getlogger()->debug("Registrar Contato - FALHOU");
	else
		tuxfw_getlogger()->debug("Registrar Contato - OK");

	// Seta flag de retorno
	if (stLinha.ntStatus == RET_OK)
	{
		setStatusCode(COD_I_0000, MSG_I_0000);
	}
	else
	{
		switch( stLinha.ntStatus )
		{
		case ERR_NOT_FOUND_OR_DISABLED:
			{
				setStatusCode( COD_E_0001, MSG_E_0001 );
				break;
			}
		case ERR_IN_PARAM:
			{
				setStatusCode( COD_E_0002, MSG_E_0002 );
				break;
			}
		case ERR_DATABASE:
			{
				setStatusCode( COD_E_0003, MSG_E_0003 );
				break;
			}
		case ERR_SUBROUTINES:
			{
				setStatusCode( COD_E_0004, MSG_E_0004 );
				break;
			}
		default:
			{
				setStatusCode( COD_E_0005,MSG_E_0005 );
				break;
			}
		}
	}

	// Monta XML de retorno
	char sCdRetorno[3];
	sprintf(sCdRetorno, "0%i", stLinha.ntStatus);
	xml_g->addItem(TAG_STATCOM, 1);
	xml_g->addItem(TAG_CODIGORETORNO, sCdRetorno);
	
	if (stLinha.ntStatus == RET_OK) {
		char *pcSubSaldo = NULL;
		char *pcDataSaldo = NULL;
		
		pcSubSaldo = walkTree(poSaida, TAG_SUB_SALDO,0);
		pcDataSaldo = walkTree(poSaida, "DATA_SALDO", 0);

		xml_g->addItem(TAG_FONE, pcFone);
		xml_g->addItem(TAG_SALDO, pcSubSaldo);
		char sDtSaldo[11];
		memset(sDtSaldo, 0, 11);
		if (pcDataSaldo != NULL)
			strncpy(sDtSaldo, pcDataSaldo, 10);
		xml_g->addItem(TAG_DATA, sDtSaldo);

		// Busca a quantidade de extratos
		int nrExtr = 0;
		for (; walkDOM(poSaida, "EXTRATO", nrExtr) != NULL; nrExtr++);
		xml_g->addItem("nrExtratos", nrExtr);

		// Busca cada um dos extratos.
		DOMNode* poNode = NULL;
		nrExtr = 0;
		while ((poNode = walkDOM(poSaida, "EXTRATO", nrExtr)) != NULL) {
			char *pcDataLancamento = NULL;
			char *pcValorLancamento = NULL;
			char *pcCodLancamento = NULL;
			char *pcDebitoCredito = NULL;
			char sDtLancto[256];
			char sVlLancto[256];
			char sCdLancto[256];
			char sInCredDeb[2];

			memset(sDtLancto, 0, 256);
			memset(sVlLancto, 0, 256);
			memset(sCdLancto, 0, 256);
			memset(sInCredDeb, 0, 2);

			pcDataLancamento = walkTree(poNode, "DATA_LANCAMENTO", 0);
			pcValorLancamento = walkTree(poNode, "VLR_LANCAMENTO", 0);
			pcCodLancamento = walkTree(poNode, "COD_LANCAMENTO", 0);
			pcDebitoCredito = walkTree(poNode, "DEBITO_CREDITO", 0);

			if (pcDataLancamento != NULL)
				strncpy(sDtLancto, pcDataLancamento, 255);
			if (pcValorLancamento != NULL)
				strncpy(sVlLancto, pcValorLancamento, 255);
            if (pcCodLancamento != NULL)
                strncpy(sCdLancto, pcCodLancamento, 255);
            if (pcDebitoCredito != NULL)
                strncpy(sInCredDeb, pcDebitoCredito, 255);

			xml_g->createTag("extrato");
			xml_g->addItem("dtLancamento", sDtLancto);
			xml_g->addItem("cdCreditoDebito", sInCredDeb);
			xml_g->addItem("cdLancamento", sCdLancto);
			xml_g->addItem("pontos", sVlLancto);
			xml_g->closeTag();

			XMLString::release(&pcDataLancamento);
			XMLString::release(&pcValorLancamento);
			XMLString::release(&pcCodLancamento);
			XMLString::release(&pcDebitoCredito);

			nrExtr++;
		}
		XMLString::release(&pcSubSaldo);
		XMLString::release(&pcDataSaldo);
	}
	XMLString::release(&pcDDD);
	XMLString::release(&pcFone);
}
