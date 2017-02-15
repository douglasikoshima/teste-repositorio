/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1.118.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/07/30 17:36:31 $
 **/

/**
 * Serviço de troca de senha, ativado via URA
 **/

#include "TrocaSenha.hpp"
#include "../../../negocio/commons/include/Commom.hpp"
#include "../../../negocio/commons/include/stLinha.hpp"
#include "AtualizaCadastro.h"
#include <tuxfw.h>

// Declaração das funções Pro*C
void buscaIdPessoa(struct stStatuslinha*, char);
int validarSenha(double idPessoa,double idPessoaLinha,char *senhaAtual,char cTitularidade);

DECLARE_TUXEDO_SERVICE(TROCASENHA);

void implTROCASENHA::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	char *pcDDD = NULL;
	char *pcFone = NULL;
	char *pcSenhaAnterior = NULL;
	char *pcNovaSenha = NULL;
	char *pcTitularidade = NULL;
	bool blSenhaAlterada = true;

	AtualizaCadastro oAtCad;

	struct stAlteraSenha m_stDados;
	struct stStatuslinha stLinha;

	memset(   &stLinha, 0, sizeof( stLinha   ) );
	memset( &m_stDados, 0, sizeof( m_stDados ) );
	
	// Busca pelas informações necessárias para execução do serviço	
	pcDDD = walkTree(dnode, TAG_DDD, 0);				// DDD
	pcFone = walkTree(dnode, TAG_FONE, 0);				// Fone
	pcSenhaAnterior = walkTree(dnode, TAG_SENHA_ANTERIOR, 0);	// Senha anterior
	pcNovaSenha = walkTree(dnode, TAG_NOVA_SENHA, 0);		// Nova senha
	pcTitularidade = walkTree(dnode, TAG_TITULARIDADE, 0);		// Titularidade

	// Verifica se conseguiu obter todos os parametros de entrada
	if ((!pcDDD) ||
		(!pcFone) ||
		(!pcSenhaAnterior) ||
		(!pcNovaSenha) ||
		(!pcTitularidade))
	{
		stLinha.ntStatus = ERR_IN_PARAM;
	}

        if ((!pcDDD) || (!pcFone) || (!*pcDDD) || (!*pcFone))
        {
                stLinha.ntStatus = ERR_IN_PARAM;
        }

		if((strcmp(pcSenhaAnterior, pcNovaSenha)==0))
		{
			stLinha.ntStatus = 11;
		}
	    

	if (stLinha.ntStatus == RET_OK)
	{
		// Monta objeto de linha.
		stLinha.ntArea = atoi(pcDDD);
		stLinha.ntLinha = atoi(pcFone);
		// Busca Id de pessoa.
		buscaIdPessoa(&stLinha, pcTitularidade[0]);
		// Verificar se a senha anterior está correta somente se o status estiver RET_OK
		if(stLinha.ntStatus == RET_OK &&
			validarSenha(stLinha.dblIdcliente,stLinha.dblIdpessoalinha,pcSenhaAnterior,pcTitularidade[0])!=1)
		{
			tuxfw_getlogger()->debug("Senha nova igual a senha anterior");
			stLinha.ntStatus = ERR_SENHA_INVALIDA;
		}
		if (stLinha.ntStatus == RET_OK) {
			// Troca a senha
			m_stDados.idPessoa = stLinha.dblIdcliente;
			m_stDados.idCanal = 9; // Canal URA
			m_stDados.idTipoSistema = 2; // Default
			m_stDados.idTipoHistoricoSenha = 4; // Default
			m_stDados.registraHistorico = 1; // Registra Histórico de Senha
			m_stDados.idPessoaUsuario = 0; // Usuário URA não é numérico
			m_stDados.inTrocaSenha = 0; // Default
			strncpy(m_stDados.telefone,pcDDD,2);
			strcat(m_stDados.telefone,pcFone);		
			//m_stDados.telefone[10] = '\0';
			if (pcTitularidade[0]=='U') {
				m_stDados.titularidadeSenha=1;
				m_stDados.tipoTitularidade[0]='U';
			} else {
                m_stDados.titularidadeSenha=2;
                m_stDados.tipoTitularidade[0]='C';
			}
			m_stDados.tipoTitularidade[1] = '\0';	
			strcpy(m_stDados.cdSenha, pcNovaSenha);
			tuxfw_getlogger()->debug("Telefone com DDD: %s", m_stDados.telefone);
			int ret = oAtCad.alteraSenha(&m_stDados);
			tuxfw_getlogger()->debug("Retorno sistema senha: %d", ret);
			if (ret == -2)
				stLinha.ntStatus = ERR_SENHA_CLI_USU;
			else if(ret == -4)
				stLinha.ntStatus = ERR_SEQ_INVALIDA;
			else if(ret == -3)
				stLinha.ntStatus = ERR_DIG_IGUAIS;
			else if(!ret) 
				stLinha.ntStatus = ERR_SUBROUTINES;
		}	
	}

	// Seta flag de retorno.
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
		case ERR_SEQ_INVALIDA:
			{
				setStatusCode( COD_E_0007, MSG_E_0007 );
				break;
			}
		case ERR_DIG_IGUAIS:
			{
				setStatusCode( COD_E_0008, MSG_E_0008 );
				break;
			}
		case ERR_SENHA_INVALIDA:
			{
				setStatusCode( COD_E_0010, MSG_E_0010 );
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
		case ERR_SENHA_CLI_USU:
			{
				stLinha.ntStatus = 13;
				setStatusCode( COD_E_0005, MSG_E_SENHA );
				break;
			}
		case 11:
			{
				setStatusCode("24W0011","Senha nova igual a anterior");
				break;
			}
		default:
			{
				setStatusCode( COD_E_0004, MSG_E_0004 );
				break;
			}
		}
	}

	// Monta o XML de saída.
	char cErrSaida[3];
	if(stLinha.ntStatus < 10)
	{
		sprintf(cErrSaida, "0%i", stLinha.ntStatus);
	}else{
		sprintf(cErrSaida, "%i", stLinha.ntStatus);
	}
	xml_g->addItem(TAG_STATCOM,1);
	xml_g->addItem(TAG_ERROR_CODE, cErrSaida);

	XMLString::release(&pcDDD);
	XMLString::release(&pcFone);
	XMLString::release(&pcSenhaAnterior);
	XMLString::release(&pcNovaSenha);
	XMLString::release(&pcTitularidade);
}
