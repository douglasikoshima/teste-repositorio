/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:16 $
 **/

/**
 * Serviço de validação de senha e solicitação de segmetação de 
 * cliente, sem identificação, ativado via URA
 **/

#include "ValSenhaSolicSegmentacaoSemId.hpp"
#include "../../../negocio/commons/include/Commom.hpp"
#include "../../../negocio/commons/include/stLinha.hpp"
#include <tuxfw.h>

DECLARE_TUXEDO_SERVICE(VALSENHASEMID);

void implVALSENHASEMID::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	char *pcDDD = NULL;
	char *pcFone = NULL;
	struct stStatuslinha stLinha;
	char szCodRetorno[3]; // Codigo de retorno
	stLinha.ntStatus = RET_OK;
	memset( &stLinha, 0, sizeof( stLinha ) );

	// Busca pelas informações necessárias para execução do serviço
	// DDD
	pcDDD = walkTree(dnode, TAG_DDD, 0);
	// Fone
	pcFone = walkTree(dnode, TAG_FONE, 0);
        if ((!pcDDD) || (!pcFone) || (!*pcDDD) || (!*pcFone) || (!IsNumeric(pcDDD)) || (!IsNumeric(pcFone)))
        {
                stLinha.ntStatus = ERR_IN_PARAM;
        }

	tuxfw_getlogger()->debug("Número recebido: (%s)%s", pcDDD, pcFone);

	if (stLinha.ntStatus == RET_OK)
	{	
		stLinha.ntLinha = atoi( pcFone );
		stLinha.ntArea = atoi( pcDDD );
		strcpy( stLinha.chrTelefone, pcDDD );
		strcat( stLinha.chrTelefone, pcFone );
		RecuperarDados( &stLinha );
	}

	// Seta flag de retorno.
	if (stLinha.ntStatus == RET_OK)
	{
		setStatusCode(COD_I_0000, MSG_I_0000);
		strncpy(szCodRetorno,&COD_I_0000[5],2);
		tuxfw_getlogger()->information("OK!");
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

	// Monta xml resposta
	// Testa o codigo de retorno, se diferente de 0 nao monta a TAG de resposta.
	char sAux[3];
	sAux[2] = 0;
	xml_g->addItem(TAG_STATCOM, 1);
	xml_g->addItem(TAG_CODIGORETORNO,szCodRetorno);
	if(szCodRetorno[1]=='0')
	{
		xml_g->addItem(TAG_ESTADOLINHA,stLinha.chrEstadolinha);
		xml_g->addItem(TAG_TIPOLINHA,stLinha.chrTipolinha);
		sprintf(sAux, "%02d", stLinha.ntSegmento);
		xml_g->addItem(TAG_SEGMENTO,sAux);
		sprintf(sAux, "%02d", stLinha.ntCarteira);
		xml_g->addItem(TAG_CARTEIRA,sAux);
		xml_g->addItem(TAG_REINIC_SENHA_CLIENTE,stLinha.chrReinicsenhacliente);
		xml_g->addItem(TAG_REINIC_SENHA_USUARIO,stLinha.chrReinicsenhausuario);
		xml_g->addItem(TAG_SENHA_CLIENTE,stLinha.chrSenhacliente);
		xml_g->addItem(TAG_SENHA_USUARIO,stLinha.chrSenhausuario);
		xml_g->addItem(TAG_SENHA_LOJISTA,stLinha.chrSenhalojista);
		xml_g->addItem(TAG_PRIORIDADE,stLinha.chrPrioridadelojista);
		xml_g->addItem(TAG_TITULARIDADE,stLinha.chrTitularidadeterminal);
		xml_g->addItem(TAG_SITUACAO_CADASTRO_PRE,stLinha.chrSituacaocadastro);
	}
	XMLString::release(&pcDDD);
	XMLString::release(&pcFone);
}
