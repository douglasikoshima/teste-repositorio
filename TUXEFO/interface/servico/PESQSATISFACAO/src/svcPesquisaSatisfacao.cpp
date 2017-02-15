/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:18 $
 **/ 

/*
 * Serviço de registro de pesquisa de satisfação, ativado via URA
 */

#include <time.h>
#include <string.h>
#include <tuxfw.h>
#include "PesquisaSatisfacao.hpp"
#include "../../../negocio/commons/include/Commom.hpp"
#include "../../../negocio/commons/include/stLinha.hpp"
#include "cWFAtendimentoPesquisaURA.h"

// Funções Pro*C
void buscaIdPessoa(stStatuslinha *);
bool jaExisteNota(stStatuslinha *, int);

DECLARE_TUXEDO_SERVICE(PESQSATISFACAO);

void implPESQSATISFACAO::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	char *pcDDD = NULL;
	char *pcFone = NULL;
	char *pcData = NULL;
	char *pcHora = NULL;
	char *pcTitularidade = NULL;
	char *pcIdSenha = NULL;
	char *pcNroProcessoAtdo = NULL;
	char *pcNota = NULL;
	bool blSucesso = false;
	char szCodRetorno[3]; // Codigo de retorno

	char cInput[2048];
	int iSz;
	const char *pMemBufId = "inputInfo";
	MemBufInputSource *poMemBuf;
	XercesDOMParser* poParser;
	DOMNode* poDoc;
	XMLGen oXmlResult;

	struct stStatuslinha stLinha;
	memset( &stLinha, 0, sizeof( stLinha ) );

	// Busca pelas informações necessárias para execução do serviço	
	pcDDD = walkTree(dnode, TAG_DDD, 0);			// DDD
	pcFone = walkTree(dnode, TAG_FONE, 0);			// Fone
	pcData = walkTree(dnode, TAG_DATA, 0);			// Data
	pcHora = walkTree(dnode, TAG_HORA, 0);			// Hora
	pcTitularidade = walkTree(dnode, TAG_IND_TITULARIDADE, 0);	// Indicador de titularidade
	pcIdSenha = walkTree(dnode, TAG_CLI_IDENT_SENHA, 0);	// Cliente identificado por senha
	pcNroProcessoAtdo = walkTree(dnode, TAG_NRO_PROCESSO_ATENDIMENTO, 0);	// Nro Processo de atendimento
	pcNota = walkTree(dnode, TAG_NOTA_CLIENTE, 0);	// Nota do cliente

	if ((!pcDDD) ||	
		(!pcFone) ||
		(!pcData) ||
		(!pcHora) ||
		(!pcTitularidade) ||
		(!pcIdSenha) ||
		(!pcNroProcessoAtdo) ||
		(!pcNota))
	{
		stLinha.ntStatus = ERR_IN_PARAM;
	}

        if ((!pcDDD) || (!pcFone) || (!*pcDDD) || (!*pcFone) || (!IsNumeric(pcDDD)) || (!IsNumeric(pcFone)) || (!IsNumeric(pcNota)) || (atoi(pcNota) < 0) || (atoi(pcNota) > 10))
        {
                stLinha.ntStatus = ERR_IN_PARAM;
        }

	if (stLinha.ntStatus == RET_OK)
	{	
		stLinha.ntArea = atoi( pcDDD );
		stLinha.ntLinha = atoi( pcFone );
		strcpy( stLinha.chrTelefone, pcDDD );
		strcat( stLinha.chrTelefone, pcFone );
		strcpy( stLinha.chrTitularidadeterminal, pcTitularidade );	

		buscaIdPessoa(&stLinha);

		if (stLinha.ntStatus == RET_OK) {
			XMLGen oXMLResult;
			oXMLResult.createTag("ROOT");
			oXMLResult.addItem("idAtendimento", pcNroProcessoAtdo);
			oXMLResult.addItem("vlNota", pcNota);
			oXMLResult.closeTag();
			
			sprintf(cInput,"%s",oXMLResult.retrieveXML(&iSz));
			poParser = new XercesDOMParser();
			poMemBuf = new MemBufInputSource((const XMLByte*)cInput, strlen(cInput), pMemBufId);
			poParser->parse(*poMemBuf);
			poDoc = poParser->getDocument();

			XMLGen xml_Temp;
			cWFAtendimentoPesquisaURA cs(poDoc, &xml_Temp);

			try {
				// Checa se já existe nota para esse atendimento.
				if (jaExisteNota(&stLinha, atoi(pcNroProcessoAtdo))) 
					blSucesso = cs.alterar();
				else
					blSucesso = cs.incluir();
				if( blSucesso )
					stLinha.ntStatus = RET_OK;
				else
					stLinha.ntStatus = ERR_SUBROUTINES;
				delete poParser;
				delete poMemBuf;
			} catch( ... ) {
				stLinha.ntStatus = ERR_SUBROUTINES;
				delete poParser;
				delete poMemBuf;
			}
		}
	}

	// Seta flag de retorno.
	if (stLinha.ntStatus == RET_OK)
	{
		setStatusCode(COD_I_0000, MSG_I_0000);
		strncpy(szCodRetorno,&COD_I_0000[5],2);
	}
	else
	{
		switch( stLinha.ntStatus )
		{
		case ERR_NOT_FOUND_OR_DISABLED:
			{
				setStatusCode( COD_E_0001, MSG_E_0001 );
				strncpy(szCodRetorno,&COD_E_0001[5],2);
				break;
			}
		case ERR_IN_PARAM:
			{
				setStatusCode( COD_E_0002, MSG_E_0002 );
				strncpy(szCodRetorno,&COD_E_0002[5],2);
				break;
			}
		case ERR_DATABASE:
			{
				setStatusCode( COD_E_0003, MSG_E_0003 );
				strncpy(szCodRetorno,&COD_E_0003[5],2);
				break;
			}
		case ERR_SUBROUTINES:
			{
				setStatusCode( COD_E_0004, MSG_E_0004 );
				strncpy(szCodRetorno,&COD_E_0004[5],2);
				break;
			}
		default:
			{
				setStatusCode( COD_E_0005,MSG_E_0005 );
				strncpy(szCodRetorno,&COD_E_0005[5],2);
				break;
			}
		}
	}

	szCodRetorno[2]=0;

	xml_g->addItem(TAG_STATCOM, 1);
	xml_g->addItem(TAG_CODIGORETORNO,szCodRetorno);

	XMLString::release(&pcDDD);
	XMLString::release(&pcFone);
	XMLString::release(&pcHora);
	XMLString::release(&pcTitularidade);
	XMLString::release(&pcIdSenha);
	XMLString::release(&pcNroProcessoAtdo);
	XMLString::release(&pcNota);
}
