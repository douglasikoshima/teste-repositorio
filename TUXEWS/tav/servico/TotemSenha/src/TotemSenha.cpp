/*
 * Serviço TOTEMSENHA
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <DadosLinha/DadosLinha.hpp>
#include <DadosLinha/stLinha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(TOTEMSENHA);

void implTOTEMSENHA::Execute(DOMNode* dnode, XMLGen* xml_g) {
	char *pcDDD = NULL;
	char *pcFone = NULL;
	char szCodRetorno[3]; // Codigo de retorno
	struct stStatuslinha stLinha;
	struct stVlStatuslinha stVlLinha;
	CDadosLinha dadosLinha;
	memset( &stLinha, 0, sizeof( stLinha ) );
	memset( &stVlLinha, 0, sizeof( stVlLinha ) );
	
	// Busca pelas informações necessárias para execução do serviço
	// DDD
	pcDDD = walkTree(dnode, "cdDDD", 0);
	// Fone
	pcFone = walkTree(dnode, "numTelefone", 0);

	if ((!pcDDD) || (!pcFone) || (!*pcDDD) || (!*pcFone))
	{
		stLinha.ntStatus = ERR_IN_PARAM;
	}
	
	if (stLinha.ntStatus == RET_OK)
	{	
		stLinha.ntLinha = atoi( pcFone );
		stLinha.ntArea  = atoi( pcDDD  );
		strcpy( stLinha.chrTelefone, pcDDD );
		strcat( stLinha.chrTelefone, pcFone );
		stVlLinha.i_chrSenhacliente = -1;
		stVlLinha.i_chrSenhausuario = -1;
		stVlLinha.i_dblIdpessoalinha = -1;
		
		dadosLinha.RecuperarDados( &stLinha, &stVlLinha );
		tuxfw_getlogger()->information("Dados Recuperados");
	}

	// Seta flag de retorno
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
		case ERR_NAO_ATENDIDO_FO:
			{
				setStatusCode( COD_E_0006, MSG_E_0006 );
				strncpy(szCodRetorno,&COD_E_0006[5],2);
				break;
			}
		case ERR_SENHA_BLOQUEADA:
			{
				setStatusCode( COD_E_0007, MSG_E_0011 );
				strncpy(szCodRetorno,&COD_E_0007[5],2);				
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

	// Testa o codigo de retorno, se diferente de 0 nao monta a TAG de resposta.
	xml_g->addItem("statCom", 1);
	xml_g->addItem("cdCodigoRetorno",szCodRetorno);
	if(szCodRetorno[1]=='0' || szCodRetorno[1]=='7')
	{
		xml_g->addItem("sgIndicadorEstadoLinha",stLinha.chrEstadolinha);
		xml_g->addItem("cdCodigoRetornoTipoConta",stLinha.chrTipolinha);
		if (stLinha.dblIdcliente == 0) {
			xml_g->addItem("cdCodigoSegmentoCliente","");
			xml_g->addItem("cdCodigoCarteira","");
		} else {
			xml_g->addItem("cdCodigoSegmentoCliente",stLinha.ntSegmento);
			xml_g->addItem("cdCodigoCarteira",stLinha.ntCarteira);
		}

		xml_g->addItem("cdPrioridadeLojista",stLinha.chrPrioridadelojista);
		xml_g->addItem("dsNomeCliente",stLinha.chrNomeCliente);
		xml_g->addItem("dsNomeUsuario",stLinha.chrNomeUsuario);
		xml_g->addItem("CDFIXOMOVEL",stLinha.chFixo);
		
		tuxfw_getlogger()->debug("dadosLinha.Premium(&stLinha)");
		
		if ( dadosLinha.Premium(&stLinha) == true)
		{
			xml_g->addItem("dsPremium","PREMIUM");
		}
		else
		{
			xml_g->addItem("dsPremium","NÃO PREMIUM");
		}
	}
	XMLString::release(&pcDDD);
	XMLString::release(&pcFone);
}

