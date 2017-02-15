/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:28 $
 **/

/*
 * Serviço de consulta de pontos, ativado via URA
 */

#include "../../../negocio/commons/include/PontosURA.h"
#include "../../../negocio/commons/include/Commom.hpp"
#include "../../../negocio/commons/include/stLinha.hpp"
#include <tuxfw.h>
#include "UREXPONTOS.h"
#include "../../../negocio/PlugInBE/include/Util.h"
#include "../../../negocio/PlugInBE/include/RegistroContato.h"

DECLARE_TUXEDO_SERVICE(UREXPONTOS);

void implUREXPONTOS::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	char *pcDDD     = NULL;
	char *pcFone    = NULL;
	char *pcDtPesq  = NULL;
	char *pcQtdPesq = NULL;
	char *pcCPF     = NULL;
	char *pcPontos  = NULL;
	char *pcMessage = NULL;

        struct stStatuslinha stLinha;
        memset( &stLinha, 0, sizeof(stLinha));
        stLinha.ntStatus = RET_OK;

	// Busca pelas informações necessárias para execução do serviço	
	pcDDD = walkTree(dnode, TAG_DDD, 0);		// DDD	
	pcFone = walkTree(dnode, "cdNumTelefone", 0);		// Fone
	pcDtPesq = walkTree(dnode, "dataPesquisa", 0);  // Data de pesquisa
	pcQtdPesq = walkTree(dnode, "qtdePesquisa", 0);	// Quantidade de lanctos
	pcCPF = walkTree(dnode, "cpf", 0);		// CPF

	tuxfw_getlogger()->debug("ddd: %s",pcDDD);
	tuxfw_getlogger()->debug("fone: %s",pcFone);
	tuxfw_getlogger()->debug("data: %s",pcDtPesq);
	tuxfw_getlogger()->debug("qtd: %s",pcQtdPesq);
	tuxfw_getlogger()->debug("cpf: %s",pcCPF);

        if ((!pcDDD) || (!pcFone) || (!pcDtPesq) || (!pcQtdPesq) || (!pcCPF) || 
	    (!*pcDDD) || (!*pcFone) || (!*pcDtPesq) || (!*pcQtdPesq) || (!*pcCPF) ||
	    (!IsNumeric(pcDDD)) || (!IsNumeric(pcFone)) || (!IsNumeric(pcQtdPesq))) 
        {
                 throw new TuxBasicSvcException(COD_E_0002, "Dados de entrada inválidos");;
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

	// Cria classe de pontos para URA
	CPontosURA oSPontos;
		
	// Popula dados para a chamada
	oSPontos.setDDD(pcDDD);
	oSPontos.setNrFone(pcFone);
	oSPontos.setNrLanctos(atoi(pcQtdPesq));
	oSPontos.setDtQuebra(pcDtPesq);
	oSPontos.setInExp(true);
	oSPontos.setInLinhas(true);

	oSPontos.ConsultaPontos();
	DOMNode* poSaida = oSPontos.getXMLRetorno();
	if (poSaida == NULL) 
		throw new TuxBasicSvcException(COD_E_0004, "Erro acessando sistema de pontos");
	DOMNode* conta = walkDOM(poSaida, "CONTA", 0);
	if (conta == NULL)
		throw new TuxBasicSvcException(COD_E_0004, "Erro acessando sistema de pontos");
	xml_g->aggregateXML(getNodeAsString(conta));

	setStatusCode(COD_I_0000, MSG_I_0000);

	XMLString::release(&pcDDD);
	XMLString::release(&pcFone);
	XMLString::release(&pcDtPesq);
	XMLString::release(&pcQtdPesq);
	XMLString::release(&pcCPF);
}
