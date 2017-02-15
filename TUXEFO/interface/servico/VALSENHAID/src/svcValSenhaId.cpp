/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1.2.12.16.5.8.6.2.1.20.1 $
 * @CVS     $Author: a5110705 $ - $Date: 2016/04/08 20:26:40 $
 **/

/**
 * Serviço de validação de senha e solicitação de segmetação de cliente, ativado via URA
 **/

#include "ValSenhaSolicSegmentacaoId.hpp"
#include "../../../negocio/commons/include/Commom.hpp"
#include "../../../negocio/commons/include/stLinha.hpp"
#include <tuxfw.h>
#include "time.h"

bool IsControlN( char * idLinhaTelefonicaPrm );
bool IsVivoNext( char * idLinhaTelefonicaPrm );
bool IsVoip( char * idLinhaTelefonicaPrm );
bool IsCombo4P( char * idLinhaTelefonicaPrm );
extern void CodigoTecnologia( char * idLinhaTelefonicaPrm, char * idTipoTecnologiaPrm );
extern void CodigoSistOrigem( char * idLinhaTelefonicaPrm, char * idSistOrigemPrm );

DECLARE_TUXEDO_SERVICE(VALSENHAID);

int getTimeNow()
{
	srand ( time(NULL));
	struct tm *data;
	time_t ltime;
    time( &ltime );
	data = localtime ( &ltime );
	char dataInicio[50];
	memset(&dataInicio,0,50);

	int mes = data->tm_mon;
	int ano = data->tm_year;
	int dia = data->tm_mday;
	int hora = data->tm_hour;
	int minuto = data->tm_min;
	int segundo = data->tm_sec;
	char cano[4+1];
	char cmes[2+1];
	char cdia[2+1];
	char chora[2+1];
	char cminuto[2+1];
	char csegundo[2+1];


	if(mes+1 < 10)
		sprintf(cmes,"0%d",mes+1);
	else
		sprintf(cmes,"%d",mes+1);

	sprintf(cano,"%d",(ano+1900));

	if(dia < 10)
		sprintf(cdia,"0%d",dia);
	else
		sprintf(cdia,"%d",dia);

	sprintf(dataInicio,"%s%s%s",cano,cmes,cdia);

	int timenow = atoi(dataInicio);
	timenow += (hora*60*60+minuto*60+segundo);

	return timenow;

}

int adicionarTagGestor(XMLGen* xml_g,struct stStatuslinha *stLinha){
	// adicionar tags de gestor
    int ret;
    ret = linhaPossuiGestor(stLinha);
	if ( ret == 2 )
	{
		xml_g->addItem( TAG_C0DCLASSIFICACAOPJ,"02" );   // Gestor de Conta
	}
	else if ( ret == 1 )
	{
		xml_g->addItem( TAG_C0DCLASSIFICACAOPJ,"01" );   // Gestor Master
	}		
	else
	{
		xml_g->addItem( TAG_C0DCLASSIFICACAOPJ,"00" );
	}
    
	xml_g->addItem(TAG_CNPJPJ,"");
	xml_g->addItem(TAG_CODLOGINPACONSULTORIAPJ1,"");
	xml_g->addItem(TAG_CODSITECONSULTORIAPJ1,"");
	xml_g->addItem(TAG_EMAILCONSULTORIAPJ1,"");
	xml_g->addItem(TAG_CODLOGINPACONSULTORIAPJ2,"");
	xml_g->addItem(TAG_CODSITECONSULTORIAPJ2,"");

   return ret;
}

void implVALSENHAID::Execute(DOMNode* dnode, XMLGen* xml_g) {

   char buffer[128];
	char *pcDDD = NULL;
	char *pcFone = NULL;
	char szCNL[20];
	char szCodRetorno[3]; // Codigo de retorno
   char idLinhaTelefonicaPrm[81];
   char idTipoTecnologiaPrm[81];
   char idSistOrigemPrm[81];
	struct stStatuslinha stLinha;
	struct stVlStatuslinha stVlLinha;



	memset( &stLinha, 0, sizeof( stLinha ) );
   memset( &stVlLinha, -1, sizeof( stVlLinha ) );
	int timeInicial = getTimeNow();	
	memset( szCNL, 0x0, sizeof(szCNL) );
   memset( idLinhaTelefonicaPrm, 0x0, sizeof(idLinhaTelefonicaPrm) );
   memset( idTipoTecnologiaPrm, 0x0, sizeof(idTipoTecnologiaPrm) );
   memset( idSistOrigemPrm, 0x0, sizeof(idSistOrigemPrm) );

	
	// Busca pelas informações necessárias para execução do serviço
	// DDD
	pcDDD = walkTree(dnode, TAG_DDD, 0);
	// Fone
	pcFone = walkTree(dnode, TAG_FONE, 0);

	if ((!pcDDD) || (!pcFone) || (!*pcDDD) || (!*pcFone) || (!IsNumeric(pcDDD)) || (!IsNumeric(pcFone)))
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
		
		RecuperarDados( &stLinha, &stVlLinha );
		tuxfw_getlogger()->information("Dados Recuperados");
	}

	// Caso a linha não for encontrada vamos procurar na portabilidade
	if(	stLinha.ntStatus == ERR_NOT_FOUND_OR_DISABLED)
	{
		// zerando o status
		stLinha.ntStatus = 0;
		getDadosPortabilidade(&stLinha,xml_g);
		// se a consulta de portabilidade não retornar vamos retornar erro de linha não encontrada
		if(stLinha.ntStatus == ERR_NOT_FOUND_OR_DISABLED || stLinha.ntStatus == -1)
		{
			setStatusCode( COD_E_0001, MSG_E_0001 );		
			xml_g->addItem(TAG_STATCOM, 1);
			xml_g->addItem(TAG_CODIGORETORNO,"01");
			xml_g->addItem(TAG_ESTADOLINHA,"14");
		}
		else
		{
			// adicionar tags de portabilidade
			setDadosPortabilidade(&stLinha,xml_g);
			setStatusCode(COD_I_0000, MSG_I_0000);
		}

		// adicionar tag de gestor
      int iGestor = adicionarTagGestor(xml_g,&stLinha);

      // Se gestor encontrado
      if (iGestor > 0) {
    	  CodigoSubSegmentoGestor(&stLinha, &stVlLinha);
    	  xml_g->addItem(TAG_CODSUBSEGMENTOCLIENTE, stLinha.cdCodigoSubSegmentoGestor);
    	  xml_g->addItem(TAG_CLUSTERLINHA, stLinha.idClusterLinhaGestor);
      }

		XMLString::release(&pcDDD);
		XMLString::release(&pcFone);
		tuxfw_getlogger()->debug("TEMPO URIN = %d",(getTimeNow()-timeInicial));
		return;
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

   sprintf( idLinhaTelefonicaPrm, "%.0f", stLinha.dblIdlinhatelefonica );
   CodigoTecnologia( idLinhaTelefonicaPrm, idTipoTecnologiaPrm );
   CodigoSistOrigem( idLinhaTelefonicaPrm, idSistOrigemPrm );

	// Testa o codigo de retorno, se diferente de 0 nao monta a TAG de resposta.
	xml_g->addItem(TAG_STATCOM, 1);
	xml_g->addItem(TAG_CODIGORETORNO,szCodRetorno);
	if(szCodRetorno[1]=='0' || szCodRetorno[1]=='7')
	{		
		if (stLinha.dblIdcliente == 0)
                        xml_g->addItem(TAG_SITUACAO_CADASTRO,"N");
                else
                        xml_g->addItem(TAG_SITUACAO_CADASTRO,"S");
      
		xml_g->addItem(TAG_ESTADOLINHA,stLinha.chrEstadolinha);

		if(!strcmp(stLinha.dsPlanoControle,"S"))
		{
			if(!strcmp(stLinha.chrTipolinha,"00"))
				strcpy(stLinha.chrTipolinha,"03");
         else if(!strcmp(stLinha.chrTipolinha,"04"))
				strcpy(stLinha.chrTipolinha,"07");
		}

		// Verifica se o Plano da linha é Básico Cartão e troca o Tipo de Linha para 08 (zap). 
		// Essa regra é para redirecionar o atendimento do cliente para uma ilha específica
		int dados = 0;
		dados = getPlanoZap(&stLinha);
		
		if(dados > 0)
		{
			tuxfw_getlogger()->information("achou o serviço BASICO CARTAO, OU PLANO BASE INTERNET PF OU PJ, countZap = %d",dados);
			// strcpy(stLinha.chrTipolinha,"08");
		}	
			
		// verificar se tem combo fixo
		getComboFixo(&stLinha);		
		
		// tipo de linha
		char tpLinha[2+1];
		memset(tpLinha,0,sizeof(tpLinha));
		strcpy(tpLinha, stLinha.chrTipolinha);
		
		// se o tipo de combo for fixo, vamos alterar o tipo de linha		
		if (!strcmp(stLinha.chrCdFixoMovel,"F")) {
			tuxfw_getlogger()->information("Tipo de linha: %s", stLinha.chrTipolinha);
			
			if(!strcmp(stLinha.chrTipolinha,"00") || !strcmp(stLinha.chrTipolinha,"04")) {
            tuxfw_getlogger()->information("### Linha FIXO, mudando para 09 = Pre Pago Fixo");				
				memset(tpLinha,0,sizeof(tpLinha));
				strcpy(tpLinha, "09");
			} else if(!strcmp(stLinha.chrTipolinha,"01") || !strcmp(stLinha.chrTipolinha,"05")) {
            memset(tpLinha, 0x0, sizeof(tpLinha));
            if ( strcmp(idTipoTecnologiaPrm,"FWTFSP") && strcmp(idTipoTecnologiaPrm,"FWTDSP") )
            {
               tuxfw_getlogger()->information("### tecnologia diferente de FWTFSP e FWTDSP mudando para 10 = Pos Pago Fixo (FWT FSP)");
				strcpy(tpLinha, "10");
            }
            if ( !strcmp(idTipoTecnologiaPrm,"FWTFSP") && atoi(idSistOrigemPrm) == 1 )
            {
               tuxfw_getlogger()->information("### tecnologia FWTFSP e sistemaorigem 1 -> mudando para 10 - Pos Pago Fixo (FWT FSP)");
               strcpy(tpLinha, "10");
            }   
            if ( !strcmp(idTipoTecnologiaPrm,"FWTDSP") && atoi(idSistOrigemPrm) == 1 )
            {
               tuxfw_getlogger()->information("### tecnologia FWTDSP e sistemaorigem 1 -> mudando para 20 - FWT DSP Pós-Pago");
               strcpy(tpLinha, "20");
            }   
			}  else if(!strcmp(stLinha.chrTipolinha,"03") || !strcmp(stLinha.chrTipolinha,"07")) {
            memset(tpLinha,0,sizeof(tpLinha));
            if ( strcmp(idTipoTecnologiaPrm,"FWTFSP") && strcmp(idTipoTecnologiaPrm,"FWTDSP") )
            {
               tuxfw_getlogger()->information("### tecnologia diferente de FWTFSP e FWTDSP mudando para 11 = Controle Fixo (FWT FSP)");            
               strcpy(tpLinha, "11");
            }
            if ( !strcmp(idTipoTecnologiaPrm,"FWTFSP") && atoi(idSistOrigemPrm) == 1 )
            {
               tuxfw_getlogger()->information("### tecnologia FWTFSP e sistemaorigem 1 -> mudando para 11 = Controle Fixo (FWT FSP)");            
               strcpy(tpLinha, "11");
            }
            if ( !strcmp(idTipoTecnologiaPrm,"FWTDSP") && atoi(idSistOrigemPrm) == 1 )
            {
               tuxfw_getlogger()->information("### tecnologia FWTDSP e sistemaorigem 1 -> mudando para 21 = FWT DSP Controle");
               strcpy(tpLinha, "21");
            }   
         }  
			
			if(stLinha.iInCorp == 1 || !strcmp(stLinha.chrTipopessoa, "PJ")) {
            tuxfw_getlogger()->information("### Linha PJ, mudando para 12 = Corporativo Fixo");				
				memset(tpLinha,0,sizeof(tpLinha));
				strcpy(tpLinha, "12");
			} 
			
			// trocar o tipo de linha quando for plano PL FIXO INTERNET PF ou PL FIXO INTERNET PJ
			//setPlanoDadosFixo(&stLinha);
			/*
			if(!strcmp(stLinha.chrTipolinha, "13")) {			
				memset(tpLinha,0,sizeof(tpLinha));
				strcpy(tpLinha, "13");
			} */
			
			if (dados > 0 && !strcmp(tpLinha,"10")) {
				memset(tpLinha,0,sizeof(tpLinha));
				strcpy(tpLinha, "13");
			}
			
			if (dados > 0 && !strcmp(tpLinha,"09")) {
				memset(tpLinha,0,sizeof(tpLinha));
				strcpy(tpLinha, "14");
			}			
			

		} else if (!strcmp(stLinha.chrCdFixoMovel,"N")) {
			tuxfw_getlogger()->information("Tipo de linha: %s", stLinha.chrTipolinha);			
			memset(tpLinha,0,sizeof(tpLinha));
			strcpy(tpLinha, "16");			
		} else {
			if (dados > 0) {
				if(!strcmp(stLinha.chrTipolinha,"00") || !strcmp(stLinha.chrTipolinha,"04")) {
					tuxfw_getlogger()->information("mudando o tipo de linha para 15");				
					memset(tpLinha,0,sizeof(tpLinha));
					strcpy(tpLinha, "15");
				} else if(!strcmp(stLinha.chrTipolinha,"01") || !strcmp(stLinha.chrTipolinha,"05")) {
					tuxfw_getlogger()->information("mudando o tipo de linha para 08");
					memset(tpLinha,0,sizeof(tpLinha));
					strcpy(tpLinha, "08");
				}	
			}
		}
		
      sprintf( buffer, "%.0f", stLinha.dblIdlinhatelefonica );
      
      if ( IsControlN( buffer ) )   // Eh uma linha CONTROLE N
      {
         strcpy( tpLinha, "17" );
      }
      
	  if ( IsVivoNext( buffer ) )   // Eh uma linha Vivo Next
      {
         strcpy( tpLinha, "18" );
      }
	  
	  if ( IsVoip( buffer ) )   // Eh uma linha Voip
      {
         strcpy( tpLinha, "19" );
      }
		/* 
			Regra nova da FIXA mandatória sobre as demais regras anteriores. 
			Se o sistema origem for ATS ou CSO e o campo cdfixomovel for F
			vamos retornar código 16 no tipo de conta.			
		*/

		if (!strcmp(stLinha.chrCdFixoMovel,"F") && 
		(atoi(idSistOrigemPrm) == 1487 || atoi(idSistOrigemPrm) == 1486)) {
			memset(tpLinha,0,sizeof(tpLinha));
			strcpy(tpLinha, "16");
		}	  
	  
	  /*
       * Removido por Solicitacao da Vivo
       *
      if ( IsCombo4P( buffer ) )   // Eh uma linha promocao 4P
      {	
		 tuxfw_getlogger()->information("Eh uma linha promocao 4P");	
         strcpy( tpLinha, "22" );
      }
      */
      
		xml_g->addItem(TAG_TIPOLINHA,tpLinha);

		// recuperar da tabela de mailing e caso encontre registro
		// vamos alterar a carteira para 41 mesmo quando não existir cliente
		int cadastroMailing = isMailing(&stLinha);

		if (stLinha.dblIdcliente == 0) {
			xml_g->addItem(TAG_SEGMENTO,"");
			if(cadastroMailing > 0)
				xml_g->addItem(TAG_CARTEIRA,"41");
			else
			xml_g->addItem(TAG_CARTEIRA,"");
		} else {
			xml_g->addItem(TAG_SEGMENTO,stLinha.ntSegmento);
			if(cadastroMailing > 0)
				xml_g->addItem(TAG_CARTEIRA,"41");
			else
			xml_g->addItem(TAG_CARTEIRA,stLinha.ntCarteira);
		}
		tuxfw_getlogger()->information("Tipo Pessoa: %s", stLinha.chrTipopessoa);
		if (strcmp(stLinha.chrTipopessoa, "PJ") == 0)
		{
			tuxfw_getlogger()->information("PJ");
			strcpy(stLinha.chrReinicsenhacliente,"N");
			strcpy(stLinha.chrReinicsenhausuario,"N");
			xml_g->addItem(TAG_REINIC_SENHA_CLIENTE,"N");
			xml_g->addItem(TAG_REINIC_SENHA_USUARIO,"N");
		} else {
			xml_g->addItem(TAG_REINIC_SENHA_CLIENTE,stLinha.chrReinicsenhacliente);
			xml_g->addItem(TAG_REINIC_SENHA_USUARIO,stLinha.chrReinicsenhausuario);
		}
		xml_g->addItem(TAG_SENHA_CLIENTE,stLinha.chrSenhacliente);
		xml_g->addItem(TAG_SENHA_USUARIO,stLinha.chrSenhausuario);

		// formata cep
		char cepCliente[9+1];
		int k=0;
		for(int i=0;i<strlen(stLinha.chrCEPcliente);i++)
		{
			if(stLinha.chrCEPcliente[i] != '-')
				cepCliente[k++] = stLinha.chrCEPcliente[i];
		}
		cepCliente[k] = 0;
		strcpy(stLinha.chrCEPcliente,cepCliente);

		char cepUsuario[9+1];
		k=0;
		for(int i=0;i<strlen(stLinha.chrCEPusuario);i++)
		{
			if(stLinha.chrCEPusuario[i] != '-')
				cepUsuario[k++] = stLinha.chrCEPusuario[i];
		}
		cepUsuario[k] = 0;
		strcpy(stLinha.chrCEPusuario,cepUsuario);

		GetLinhaRestrita(&stLinha );

		xml_g->addItem(TAG_RG_CLIENTE,stLinha.chrRGcliente);
		xml_g->addItem(TAG_RG_USUARIO,stLinha.chrRGusuario);
		xml_g->addItem(TAG_CEP_CLIENTE,stLinha.chrCEPcliente);
		getCnl(stLinha.chrCEPcliente, szCNL);
		xml_g->addItem("CNL",szCNL);
		xml_g->addItem(TAG_CEP_USUARIO,stLinha.chrCEPusuario);
		xml_g->addItem(TAG_NASCIMENTO_CLIENTE,stLinha.chrNascimentocliente);
		xml_g->addItem(TAG_NASCIMENTO_USUARIO,stLinha.chrNascimentousuario);
		xml_g->addItem(TAG_CPF_CLIENTE,stLinha.chrCPFcliente);
		xml_g->addItem(TAG_CPF_USUARIO,stLinha.chrCPFusuario);
		xml_g->addItem(TAG_SENHA_LOJISTA,stLinha.chrSenhalojista);
		xml_g->addItem(TAG_PRIORIDADE_LOJISTA,stLinha.chrPrioridadelojista);
		xml_g->addItem(TAG_ATENDIMENTO_PESSOAL,stLinha.chrAtendimentoPessoal);
		// agora retorna tambem o nome de cliente e usuario
		xml_g->addItem(TAG_NOME_CLIENTE,stLinha.chrNomeCliente);
		xml_g->addItem(TAG_NOME_USUARIO,stLinha.chrNomeUsuario);
		xml_g->addItem(TAG_PLANO_LINHA,stLinha.dsPlano);
		xml_g->addItem(TAG_CODRESTRICAO,stLinha.iLinhaRestrita);

		if (stLinha.dblIdcliente != 0) {
			if (stLinha.dblIdcliente == stLinha.dblIdusuario)
				xml_g->addItem(TAG_TITULARIDADE,"C");
			else
				xml_g->addItem(TAG_TITULARIDADE,"U");
		} else {
			xml_g->addItem(TAG_TITULARIDADE,"");
		}
		// criar tags de portabilidade caso o paremetro esteja configurado na APOIO.PARAMATRO
		setDadosPortabilidade(&stLinha,xml_g);
		// nova tag de cluster
		if(stLinha.idClusterLinha == 0)
			xml_g->addItem(TAG_CLUSTERLINHA,"");
		else
			xml_g->addItem(TAG_CLUSTERLINHA,stLinha.idClusterLinha);
		
		// adicionar tag de iphone
		setCadastroIPhone(&stLinha,xml_g);

		//adicionar tag de oferta
		IsLinhaOferta(&stLinha );
		xml_g->addItem(TAG_INDLINHAOFERTA,stLinha.chrLinhaOferta);

		// adicionar tag de gestor
      int iGestor = adicionarTagGestor(xml_g,&stLinha);
		
		// adicionar tag de combofixo
		int regraCombo = consultarRegraComboFixo(&stLinha);
		if (regraCombo) {
			xml_g->addItem("ComboComFixo","1");
		} else {
			xml_g->addItem("ComboComFixo","0");
		}
		
		char planoModem[2];
		memset(planoModem,0,sizeof(planoModem));
		if (strcmp(stLinha.chrTipopessoa, "PF") == 0) {
			if ( IsPlanoModem(&stLinha) ) {
				strcpy(planoModem,"S");
			} else {
				strcpy(planoModem,"N");
			}
		}
		tuxfw_getlogger()->information("linha %s",planoModem);
		
		xml_g->addItem(TAG_PLANOMODEM, planoModem);

	  // obtem subsegmento associado ao cliente
	  CodigoSubSegmentoCliente(&stLinha, &stVlLinha);
      xml_g->addItem(TAG_CODSUBSEGMENTOCLIENTE, stLinha.cdCodigoSubSegmentoCliente);

	}
	XMLString::release(&pcDDD);
	XMLString::release(&pcFone);
	
	tuxfw_getlogger()->debug("TEMPO URIN = %d",(getTimeNow()-timeInicial));

}
