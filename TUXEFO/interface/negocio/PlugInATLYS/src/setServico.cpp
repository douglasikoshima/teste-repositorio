#include "PlugInATLYS.h" 

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include "AvailableService.h"

#include "../../VolNegocio/include/VolNegocio.h"

#include "../../PlugInBE/include/Parametro.h"

#include <cstdlib>
#include <cstring>

using namespace std;


void PlugInATLYS::setServico()
{

	char* pcTagXmlIn = NULL;

	// Dados de Entrada
	char cSbscrpId[256];
	char cBasicSvcId[256];
	int  iOperacao;
	char cNmSrvATLYS[30];
	char cCanalVenda[10];
	char cDate[30];
	char cProxyLinha[12];

	int  iCdAreaRegistro = -1;
 	int  iNrLinha = -1;
	CLinha			oLinha;
	CServico		oServico;
	list<CServico>	lstServicos;
	CParametro		oParametro;


	// Passo 1 - TuxProxyBE

	/*
	<msg>
		<msgHdr>
			<user>2</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyLinha></ProxyLinha>
			<ProxyOperacao>setServico</ProxyOperacao>
			<operacao></operacao>
			<servico></servico>
			<canalVenda></canalVenda>
			<xmlns></xmlns>
		</msgBody>
	</msg>

	*/
                                                
	strcpy(cSbscrpId,getIdLinhaSistemaOrigem());
    tuxfw_getlogger()->debug("cSbscrpId:%s",cSbscrpId); 


	// Ler Tag <operacao>
	if( ( (pcTagXmlIn = tuxhp->walkTree(dnode,"operacao", 0)) == NULL) ){
		throw new TuxBasicSvcException("24E0000", "TAG_<operacao>_INEXISTENTE");
	}
	if(!*pcTagXmlIn){
		throw new TuxBasicSvcException("24E0000", "TAG_<operacao>_VALOR_VAZIO");
	}
	if (!Util::isNum(pcTagXmlIn) &&
		( strcmp(pcTagXmlIn,"0") != 0 ||
		  strcmp(pcTagXmlIn,"1") != 0 ||
		  strcmp(pcTagXmlIn,"2") != 0)) {
		throw new TuxBasicSvcException("24E0000", "TAG_<operacao>_VALOR_INVALIDO");
	}

	iOperacao = atoi(pcTagXmlIn);

    // Ler Tag <ProxyLinha> que já foi validade pelo Proxy.
    pcTagXmlIn = tuxhp->walkTree(dnode,"ProxyLinha", 0);
	strcpy(cProxyLinha, pcTagXmlIn);
	tuxfw_getlogger()->debug("CPROXULINHA: %s", cProxyLinha); 
    if ((iNrLinha = atoi(pcTagXmlIn + 2)) <= 0) {
            throw new TuxBasicSvcException(ECD_ATL_TVI_NRLINHA, EMSG_ATL_TVI_NRLINHA);
    }
    *(pcTagXmlIn + 2) = '\0';
    if ((iCdAreaRegistro = atoi(pcTagXmlIn)) <= 0) {
            throw new TuxBasicSvcException(ECD_ATL_TVI_CDAREAREGISTRO, EMSG_ATL_TVI_CDAREAREGISTRO);
    }

	// Ler Tag <servico>
	if( ( (pcTagXmlIn = tuxhp->walkTree(dnode,"servico", 0)) == NULL) ){
		throw new TuxBasicSvcException("24E0000", "TAG_<servico>_INEXISTENTE");
	}
	if(!*pcTagXmlIn){
		throw new TuxBasicSvcException("24E0000", "TAG_<servico>_VALOR_VAZIO");
	}

	// Recupera o Nome do Servico (svcNm) a ser Modificado .
	strcpy(cNmSrvATLYS, this->traduzNomeServicoToATLYS(pcTagXmlIn, iCdAreaRegistro));

	if(strcmp(cNmSrvATLYS,"")==0){
       throw new TuxBasicSvcException("24E0000", "TAG_<servico>_VALOR_INVALIDO");
	}

	// Ler Tag <canalVenda>
	if( ((pcTagXmlIn = tuxhp->walkTree(dnode,"canalVenda", 0)) == NULL) || (!*pcTagXmlIn)){
		strcpy( cCanalVenda, "00000" );
	}
	else{
		strcpy( cCanalVenda, pcTagXmlIn );
	}



	/* Desativação Bloqueio LDI
	   Quando for solicitado a desativação do Bloqueio LDI, verificar se o Bloqueio LDN está ATIVO, se sim lançar excessão.
	*/

	if (strcmp(cNmSrvATLYS, traduzNomeServicoToATLYS("UNBLOCKDDI", iCdAreaRegistro)) == 0 && iOperacao == 0){
		// Set os atributos pertinentes a Linha
		oLinha.setCdAreaRegistro(iCdAreaRegistro);
		oLinha.setNrLinha(iNrLinha);
		// Consultar a lista de todos servicos da linha
		oLinha.consultarServicos(lstServicos);		
	
		while( 0 < lstServicos.size() ) {
			oServico = lstServicos.front();
			if (Util::cmp(oServico.getSgServico(), traduzNomeServicoToATLYS("UNBLOCKDDD", iCdAreaRegistro)))
				throw new TuxBasicSvcException(ECD_BLOQUEIO_DDI_INVALIDO, EMSG_EXC_BLOQUEIO_DDI_INVALIDO);
			lstServicos.pop_front();
		}
	}



	/*
	   Acertar as operacoes dos Bloqueios (Apenas Inverter)
	*/

	//Verifica se o serviço possui conceito inverso através da tabela apoio.ServicoAtlys pelo campo inAtivo, caso o conteúdo seja 0 -> conceito inverso, 1 -> conceito igual a aplicação
	oParametro.setCdAreaRegistro(iCdAreaRegistro);
	oParametro.setChave(cNmSrvATLYS);
	oParametro.consultarInAtivoSvcName();
	if (oParametro.getInAtivo() == 0)
	{
		if(iOperacao == 0){// DESATIVAR
		  iOperacao = 1; //Ativar
		}
		else if(iOperacao == 1){// ATIVAR
		  iOperacao = 0; // Desativar
		}
	}


	/*Reinicia Caixa postal: Operacao == 2
	"Reiniciar" uma Caixa Postal significa desativá-la e ativá-la logo em seguida
	*/ 
	if(iOperacao == 2){

		// Checar se é VOICEMAIL
		if(strcmp(cNmSrvATLYS,traduzNomeServicoToATLYS("VOICEMAIL", iCdAreaRegistro)) == 0){

			char* xmlDesVOICEMAIL_FINAL=NULL;

			try{

				// DESATIVAR CAIXA POSTAL				
				char* pcXmlDesVOICEMAIL ="\
							<msg>\
									<msgHdr>\
											<user>2</user>\
											<service>TuxProxyBE</service>\
									</msgHdr>\
									<msgBody>\
											<ProxyLinha>%s</ProxyLinha>\
											<operacao>0</operacao>\
											<servico>VOICEMAIL</servico>\
									</msgBody>\
							</msg>";



				xmlDesVOICEMAIL_FINAL = new char[strlen(pcXmlDesVOICEMAIL) + 32];
				sprintf(xmlDesVOICEMAIL_FINAL, pcXmlDesVOICEMAIL, cProxyLinha);
				//tuxfw_getlogger()->debug("XML ENTRADA DESATIVA 1: %s", xmlDesVOICEMAIL_FINAL); 				

				callSetServico(xmlDesVOICEMAIL_FINAL);


				// ATIVAR CAIXA POSTAL
				char* pcXmlAtivVOICEMAIL ="\
							<msg>\
									<msgHdr>\
											<user>2</user>\
											<service>TuxProxyBE</service>\
									</msgHdr>\
									<msgBody>\
											<ProxyLinha>%s</ProxyLinha>\
											<operacao>1</operacao>\
											<servico>VOICEMAIL</servico>\
									</msgBody>\
							</msg>";
				
				if (xmlDesVOICEMAIL_FINAL)
					delete xmlDesVOICEMAIL_FINAL;

				xmlDesVOICEMAIL_FINAL = new char[strlen(pcXmlAtivVOICEMAIL) + 32];
				sprintf(xmlDesVOICEMAIL_FINAL, pcXmlAtivVOICEMAIL, cProxyLinha);
				//tuxfw_getlogger()->debug("XML ENTRADA DESATIVA 2: %s", xmlDesVOICEMAIL_FINAL); 				

				callSetServico(xmlDesVOICEMAIL_FINAL);


				// Reinicialização Ok
				xml_g->createTag("ServicoVO");

				if((pcTagXmlIn = tuxhp->walkTree(dnode,TAG_XML_IN_XMLNS, 0)) != NULL)
				{
					xml_g->addProp( PROP_XML_OUT_XMLNS, pcTagXmlIn );
				};	

				xml_g->closeTag();

				return;
			}
			catch(TuxBasicSvcException *e){
				if (xmlDesVOICEMAIL_FINAL)
					delete xmlDesVOICEMAIL_FINAL;

				//e->getCode
				tuxfw_getlogger()->debug("Cath Execption em PlugInATLYS::callSetServico(int,char*)"); 
				throw new TuxBasicSvcException(ECD_REINICIA_CXPOSTAL_DES, EMSG_REINICIA_CXPOSTAL_DES); 
			}
			catch(...){
				if (xmlDesVOICEMAIL_FINAL)
					delete xmlDesVOICEMAIL_FINAL;

				tuxfw_getlogger()->debug("Cath Execption em PlugInATLYS::callSetServico(int,char*)"); 
				throw new TuxBasicSvcException("24E0000", "Reinicializacao de Caixa Postal Nao Realizada"); 
			}

			if (xmlDesVOICEMAIL_FINAL)
				delete xmlDesVOICEMAIL_FINAL;
		}
		else{
			tuxfw_getlogger()->debug("Operacao Disponivel somente para VOICEMAIL"); 
			throw new TuxBasicSvcException("24E0000", "Operacao Disponivel somente para VOICEMAIL"); 
		}
	}

// Passa 2 - AcessoATLYS:

/*

<msg>
	<msgHdr>
		<user>2</user>
		<service>inputGetSubscriptionInfo</service>
	</msgHdr>
	<msgBody>
		<inputGetSubscriptionInfo sbscrpId="0035132738" />
	</msgBody>                    
</msg>

*/

	XMLGen oXmlEnvioGetSubscriptionInfo;
	
	oXmlEnvioGetSubscriptionInfo.createTag(XML_ATL_INPUT_SUBSCRIPTION_INFO);
	oXmlEnvioGetSubscriptionInfo.addProp(XML_ATL_ACCT_SBSCRID, cSbscrpId);
	oXmlEnvioGetSubscriptionInfo.closeTag();

	// Guarda o xml de retorno
	DOMNode* outputDOMNodeGetSubscriptionInfo;
	tuxfw_getlogger()->debug("PlugInATLYS::setServico():callRemoteAPI()");
	outputDOMNodeGetSubscriptionInfo =  callRemoteAPI(this->getServiceName(), &oXmlEnvioGetSubscriptionInfo, XML_ATL_INPUT_SUBSCRIPTION_INFO );
	
	if(outputDOMNodeGetSubscriptionInfo == NULL){
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 
		throw new TuxBasicSvcException(getLastStatusCode(),getLastStatusText());	
	}

	// Validar de a Operação  Remota retornou erro.
	if(strcmp(getLastStatusCode(),"24I0000") != 0){
	
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
		throw new TuxBasicSvcException("24E0000", getLastStatusText());
	}

	/*
	PASSO 1 - Define se a operação (ATIVACAO ou DESATIVACAO) poderá ser executada.
	
	Condição a ser verificada: 
	--------------------------
		O servico que se deseja modificar (cNmSrvATLYS),
		não pode ser "memberSvcs" de um "primarySvc" com "svcTyp=PK".
		ou seja: cNmSrvATLYS ! = "memberSvcs"."svcNm"
	*/
    
	bool bExecutaOperacao = true;
	DOMNode* auxDOMSvcAsgmInfo;
	DOMNode* auxDOMPrimarySvc;
	DOMNode* auxDOMMemberSvc;

	char *pcSrvNm;
	char *pcSrvTyp;
	int i =0, j=0;
	while(bExecutaOperacao && (auxDOMSvcAsgmInfo = tuxhp->walkDOM(outputDOMNodeGetSubscriptionInfo,"svcAsgmInfo", i)) != NULL )
	{
		if((auxDOMPrimarySvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"primarySvc", 0)) != NULL ){
		
			// lendo Atributo "svcTyp" da Tag <primarySvc>
			pcSrvTyp = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcTyp");		
			
			if(strcmp(pcSrvTyp, "PK") == 0 ){
				while(bExecutaOperacao &&  (auxDOMMemberSvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"memberSvcs", j)) != NULL )
				{		
					// lendo Atributo "svcNm" da Tag <memberSvcs>
					pcSrvNm  = tuxhp->getAttrValue(auxDOMMemberSvc,"svcNm");	
					if(strcmp(pcSrvNm, cNmSrvATLYS) == 0){
						bExecutaOperacao = false;
					}
					j++;
				}				
			}	
		}
		else{
			throw new TuxBasicSvcException("24E0000", "TAG_<primarySvc>_INEXISTENTE");
		}
		i++;
	}
	
	if(!bExecutaOperacao){
		throw new TuxBasicSvcException(ECD_ATL_TNE_SERVICO_PACOTE, "Servico já faz parte de um pacote.");
	}

	/*

	PASSO 2 -	Obter o Id do "BASICO NUMÈRO" do XML do GetSubscriptionInfo
	            Identificar se o Servico desejado está Ativado ou Desativado 
				Identificar se o Servico é um Pacote ou Não-Pacote(Simples)
				Preencher o XML de Envio para a API MaintainSubscriptionServices, de acordo com o 
				Tipo do Servico (Pacote ou Não-Pacote) e com a Operação (ATIVAVAO ou DESATIVACAO)

	
	*/

	// Procurar <basicSvcList basicSvcNm="BASICO NUMÈRO"> ou <memberSvcs svcNm="BASICO NUMÈRO">
	// e armazenar seu "basicSvcId" ou "svcId", respectivamente.

	bool bAchouBasicoNum = false;
	DOMNode* auxDOMBasicSrvList;
	
	/*
	   Procurar o Valor "BASICO NUMÈRO" nas TAGS:
		    - <primarySvc:basicSvcList> 
		    - <memberSvcs> 
		    - <memberSvcs:basicSvcList>
	*/
	//Procurando as TAGS <svcAsgmInfo>
	i=0;
	while(!bAchouBasicoNum && (auxDOMSvcAsgmInfo = tuxhp->walkDOM(outputDOMNodeGetSubscriptionInfo,"svcAsgmInfo", i)) != NULL )
	{	
	
  		// Procurando as TAGS <primarySvc>
		if( ((auxDOMPrimarySvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"primarySvc", 0)) !=NULL ) ){

			// Testar se a TAG <primarySvc> possui TAG interna <basicSvcList>
			if( (auxDOMBasicSrvList = tuxhp->walkDOM(auxDOMPrimarySvc,"basicSvcList", 0)) != NULL ){

				// Se basicSvcNm="BASICO NUMÈRO"
				if(strcmp(tuxhp->getAttrValue(auxDOMBasicSrvList,"basicSvcNm"),"BASICO NUMÈRO") == 0){
					//armazenar seu "basicSvcId"
					strcpy(cBasicSvcId,tuxhp->getAttrValue(auxDOMBasicSrvList,"basicSvcId"));		
					bAchouBasicoNum = true;  
				};
			}					
		}
		 
		// Procurando as TAGS <memberSvcs>
		j=0;
		while(!bAchouBasicoNum && ((auxDOMMemberSvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"memberSvcs", j)) !=NULL)){
			
			// Testar se <memberSvcs svcNm="BASICO NUMÈRO">
			if(strcmp(tuxhp->getAttrValue(auxDOMMemberSvc,"svcNm"),"BASICO NUMÈRO") == 0){
				// armazenar seu "svcId"
				strcpy(cBasicSvcId,tuxhp->getAttrValue(auxDOMMemberSvc,"svcId"));		
				bAchouBasicoNum = true;  
			}
            else//Testar se <memberSvcs> possui TAG interna <basicSvcList>
				if((auxDOMBasicSrvList = tuxhp->walkDOM(auxDOMMemberSvc,"basicSvcList", 0)) != NULL ){

					// Se basicSvcNm="BASICO NUMÈRO"
					if(strcmp(tuxhp->getAttrValue(auxDOMBasicSrvList,"basicSvcNm"),"BASICO NUMÈRO") == 0){
						//armazenar seu "basicSvcId"
						strcpy(cBasicSvcId,tuxhp->getAttrValue(auxDOMBasicSrvList,"basicSvcId"));		
						bAchouBasicoNum = true;  
					};					
			}
			j++;	
		}
		i++;
	}

	// Se não achou o Valor "BASICO NUMÈRO", termina o Metodo.
	if(!bAchouBasicoNum){
		throw new TuxBasicSvcException("24E0000", "Operacao nao Realizada. Valor (BASICO NUMÈRO) nao encontrado nas tags <primarySvc:basicSvcList> ou <memberSvcs> ou <memberSvcs:basicSvcList> ");
	}


	/*
        Iniciar o preenchimento do XML de Envio para a API MaintainSubscriptionServices, de acordo com o 
		Tipo do Servico (Pacote ou Não-Pacote) e com a Operação (ATIVAVAO ou DESATIVACAO)
	*/

	// Xml de Envio para MaintainSubscriptionServices
	XMLGen oXmlEnvioMainSubsServices;
	

	/*	   
	   DESATIVAÇÂO do Servico: 
	   =======================
       Verifica-se as condições para a Desativação e preenche o xml para MaintainSubscriptionServices

    */
	// Se o Servico está Ativo (foi encontrado no XML do GetSubscriptionInfo)
	// Ele pode ser DESATIVADO.

	/*
	    bSrcAtivo = true  :Significa que foi encontrado no XML de Retorno da API GetSubscriptionInfo
		bSrcAtivo = false :Significa que NÃO foi encontrado no XML de Retorno da API GetSubscriptionInfo
	*/
    bool bSrcAtivo = false;

	// Ler o XML do GetSubscriptionInfo e Preencher para a DESATIVAÇÂO 
	// Procurando as TAGS <svcAsgmInfo>
	i=0;
	while(!bSrcAtivo && (auxDOMSvcAsgmInfo = tuxhp->walkDOM(outputDOMNodeGetSubscriptionInfo,"svcAsgmInfo", i)) != NULL )
	{						   
  		// Procurando nas TAGS <primarySvc> o Servico Desejado (cNmSrvATLYS).
		if((auxDOMPrimarySvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"primarySvc", 0)) != NULL ){
	
			// lendo Atributo "svcNm" da Tag <primarySvc>
			pcSrvNm  = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcNm");	

			// Se Encontrou o Servico
			if(strcmp(pcSrvNm, cNmSrvATLYS) == 0){
			
				bSrcAtivo = true; // Servico Encntrado no xml de GetSubscriptionInfo, portanto ele está Ativado e
				                  // pode ser DEASITVADO.
				
				//Se a Operação for de DESATIVACAO, executar!
				if(iOperacao == 0){ 

					// Obter o tipo.

					// lendo Atributo "svcTyp" da Tag <primarySvc>
					pcSrvTyp = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcTyp");
					
					// Se o Servico for PACOTE
					if(strcmp(pcSrvTyp, "PK") == 0){ 
					
						// Se o Servico for PK, (Pacote)
						// Preparar o Xml para a Desativação de Pacote.
						oXmlEnvioMainSubsServices.createTag("inputMaintainSubscriptionServices");
							oXmlEnvioMainSubsServices.addProp("sbscrpId", cSbscrpId);
						
								// TAG updateSvcLst
								oXmlEnvioMainSubsServices.createTag("updateSvcLst");				
								// ler Atributo "svcEffDt" da TAG <svcAsgmInfo>
								pcTagXmlIn = tuxhp->getAttrValue(auxDOMSvcAsgmInfo,"svcEffDt");		
								oXmlEnvioMainSubsServices.addProp("svcEffDt", pcTagXmlIn); 
								oXmlEnvioMainSubsServices.addProp("svcExprDt", CAvailableService::getSysDateBD(cDate)); 

									// TAG salesChnl
									oXmlEnvioMainSubsServices.createTag("salesChnl");
										oXmlEnvioMainSubsServices.addProp("salesChnlId", cCanalVenda);
									oXmlEnvioMainSubsServices.closeTag(); // <salesChnl>

									// TAG primarySvc
									oXmlEnvioMainSubsServices.createTag("primarySvc");
										oXmlEnvioMainSubsServices.addProp("svcNm", cNmSrvATLYS);
										oXmlEnvioMainSubsServices.addProp("svcTyp", "PK" );
										// ler Atributo "svcId" da TAG <primarySvc>
										pcTagXmlIn = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcId");		
										oXmlEnvioMainSubsServices.addProp("svcId", pcTagXmlIn );
									oXmlEnvioMainSubsServices.closeTag(); // <primarySvc>
									/*	
									// Buscar os Members do Servico PK
									// TAGs  memberSvcs (loop para members)
									j=0;
									while((auxDOMMemberSvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"memberSvcs", j)) != NULL )
									{
										oXmlEnvioMainSubsServices.createTag("memberSvcs");												
											// ler Atributo "svcNm" da TAG <memberSvcs>
											pcTagXmlIn  = tuxhp->getAttrValue(auxDOMMemberSvc,"svcNm");	
											oXmlEnvioMainSubsServices.addProp("svcNm",pcTagXmlIn);													
											// ler Atributo "svcTyp" da TAG <memberSvcs>
											pcTagXmlIn  = tuxhp->getAttrValue(auxDOMMemberSvc,"svcTyp");
											oXmlEnvioMainSubsServices.addProp("svcTyp", pcTagXmlIn);													
											// ler Atributo "svcId" da TAG <memberSvcs>
											pcTagXmlIn = tuxhp->getAttrValue(auxDOMMemberSvc,"svcId");		
											oXmlEnvioMainSubsServices.addProp("svcId", pcTagXmlIn);

											// TAG basicSvcNm
											oXmlEnvioMainSubsServices.createTag("basicSvcList");
												oXmlEnvioMainSubsServices.addProp("basicSvcNm", "BASICO NUMÈRO");								
												oXmlEnvioMainSubsServices.addProp("basicSvcId", cBasicSvcId );
											oXmlEnvioMainSubsServices.closeTag(); // <basicSvcNm>

										oXmlEnvioMainSubsServices.closeTag(); // <memberSvcs>
										j++;
									}
									*/											

								oXmlEnvioMainSubsServices.closeTag(); // <updateSvcLst>

						oXmlEnvioMainSubsServices.closeTag();	// <inputMaintainSubscriptionServices>						
					}
					else{
							// Se o Servico for Não Pacote, (simples)
							// Preparar o Xml para a Desativação Não Pacote.
							oXmlEnvioMainSubsServices.createTag("inputMaintainSubscriptionServices");
								oXmlEnvioMainSubsServices.addProp("sbscrpId", cSbscrpId);
							
								    // TAG updateSvcLst
									oXmlEnvioMainSubsServices.createTag("updateSvcLst");				
										// ler Atributo "svcEffDt" da TAG <svcAsgmInfo>
										pcTagXmlIn = tuxhp->getAttrValue(auxDOMSvcAsgmInfo,"svcEffDt");		
										oXmlEnvioMainSubsServices.addProp("svcEffDt", pcTagXmlIn); 
										oXmlEnvioMainSubsServices.addProp("svcExprDt", CAvailableService::getSysDateBD(cDate)); 

										// TAG salesChnl
										oXmlEnvioMainSubsServices.createTag("salesChnl");
											oXmlEnvioMainSubsServices.addProp("salesChnlId", cCanalVenda);
										oXmlEnvioMainSubsServices.closeTag(); // <salesChnl>

										// TAG primarySvc
										oXmlEnvioMainSubsServices.createTag("primarySvc");
											oXmlEnvioMainSubsServices.addProp("svcNm", cNmSrvATLYS);
											oXmlEnvioMainSubsServices.addProp("svcTyp", pcSrvTyp );
											// ler Atributo "svcId" da TAG <primarySvc>
											pcTagXmlIn = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcId");		
											oXmlEnvioMainSubsServices.addProp("svcId", pcTagXmlIn );
								
												// TAG basicSvcNm
												//oXmlEnvioMainSubsServices.createTag("basicSvcList");
												//	oXmlEnvioMainSubsServices.addProp("basicSvcNm", "BASICO NUMÈRO");
												//	oXmlEnvioMainSubsServices.addProp("basicSvcId", cBasicSvcId);
												//oXmlEnvioMainSubsServices.closeTag(); // <basicSvcList>

										oXmlEnvioMainSubsServices.closeTag(); // <primarySvc>

									oXmlEnvioMainSubsServices.closeTag(); // <updateSvcLst>

							oXmlEnvioMainSubsServices.closeTag();	// <inputMaintainSubscriptionServices>			
					
					}// fim if PK
		
				} // fim if DESATIVACAO
				
			}// fim if (svcNm == cNmSrvATLYS)
		}
    	else{
			throw new TuxBasicSvcException("24E0000", "TAG_<primarySvc>_INEXISTENTE");
		}
		// next Tag <svcAsgmInfo>
		i++;
	}

	/* 
	   ATIVAÇÂO do Servico: 
       ====================
	   Verifica-se as condições para a Ativação e preenche o xml para MaintainSubscriptionServices
    */
	
	// Se o Servico não está Ativo (não foi encontrado no XML do GetSubscriptionInfo)
	// Ele pode ser ATIVADO.
	if(!bSrcAtivo && (iOperacao == 1) ){// ATIVACAO


		// Procurar o Tipo do Servico. (Pacote ou Não-Pacote)
		// A busca será na feita na Base de Dados do FO.
		char cTpSrvDB[30] = "";
		
		tuxfw_getlogger()->debug("CAvailableService::getTypeServiceDB(%s)",cNmSrvATLYS);
		CAvailableService::getTypeServiceDB(cNmSrvATLYS, cTpSrvDB);

		// Se for Pacote
		if(strcmp(cTpSrvDB,"PK") == 0){ 

			// Obtem os Servicos Membros do Pacote
			list<CAvailableService>  lstMembers;
			CAvailableService::getMembersServiceDB(cNmSrvATLYS, lstMembers);

			// Se não retornou menhem Membro, Termina metodo.
			if(lstMembers.size() == 0){
				throw new TuxBasicSvcException("24E0000", "Nenhum Servico membro encontrado no Pacote.");
			}

			// Preparar o Xml para a Ativação do Pacote.
			oXmlEnvioMainSubsServices.createTag("inputMaintainSubscriptionServices");
				oXmlEnvioMainSubsServices.addProp("sbscrpId", cSbscrpId);
			
					// TAG addSvcLst
					oXmlEnvioMainSubsServices.createTag("addSvcLst");		
					
						// TAG salesChnl
						oXmlEnvioMainSubsServices.createTag("salesChnl");
							oXmlEnvioMainSubsServices.addProp("salesChnlId", cCanalVenda);
						oXmlEnvioMainSubsServices.closeTag(); // <salesChnl>

						// TAG primarySvc
						oXmlEnvioMainSubsServices.createTag("primarySvc");
							oXmlEnvioMainSubsServices.addProp("svcNm", cNmSrvATLYS);
							oXmlEnvioMainSubsServices.addProp("svcTyp", "PK" );
						oXmlEnvioMainSubsServices.closeTag(); //<primarySvc>
						
						// Percorre a Lista de Servicos para Montar o XML 
						CAvailableService oSrcMember;
						while( 0 < lstMembers.size() ) {
							
							// Pega um item da lista
							oSrcMember = lstMembers.front();
		
							oXmlEnvioMainSubsServices.createTag("memberSvcs");					
								oXmlEnvioMainSubsServices.addProp("svcNm", oSrcMember.getName());
								oXmlEnvioMainSubsServices.addProp("svcTyp", oSrcMember.getType());

								// TAG basicSvcNm
								oXmlEnvioMainSubsServices.createTag("basicSvcList");
									oXmlEnvioMainSubsServices.addProp("basicSvcNm", "BASICO NUMÈRO");
									oXmlEnvioMainSubsServices.addProp("basicSvcId", cBasicSvcId );
								oXmlEnvioMainSubsServices.closeTag(); // <basicSvcNm>

							oXmlEnvioMainSubsServices.closeTag(); // <memberSvcs>

							lstMembers.pop_front();
						}

					oXmlEnvioMainSubsServices.closeTag(); // <addSvcLst/>

			oXmlEnvioMainSubsServices.closeTag(); // <inputMaintainSubscriptionServices/>

		}
		else{// Se o Servico for Não Pacote, (simples)
			 // Preparar o Xml para a Ativação Não Pacote.

			/*
			<inputMaintainSubscriptionServices sbscrpId="0035132738">
			 <addSvcLst>
			  <salesChnl salesChnlId="00000" /> 
			 <primarySvc svcNm="BLOQ PARCIAL FRAUDE" svcTyp="IO">
			  <basicSvcList basicSvcNm="BASICO NUMERO" basicSvcId="00000N45ESL808T:00000N45ESM008X" /> 
			  </primarySvc>
			  </addSvcLst>
			</inputMaintainSubscriptionServices>

			*/
			oXmlEnvioMainSubsServices.createTag("inputMaintainSubscriptionServices");
				oXmlEnvioMainSubsServices.addProp("sbscrpId", cSbscrpId);
			
					// TAG addSvcLst
					oXmlEnvioMainSubsServices.createTag("addSvcLst");				

						// TAG salesChnl
						oXmlEnvioMainSubsServices.createTag("salesChnl");
							oXmlEnvioMainSubsServices.addProp("salesChnlId", cCanalVenda);
						oXmlEnvioMainSubsServices.closeTag(); // <salesChnl>

						// TAG primarySvc
						oXmlEnvioMainSubsServices.createTag("primarySvc");
							oXmlEnvioMainSubsServices.addProp("svcNm", cNmSrvATLYS);
							oXmlEnvioMainSubsServices.addProp("svcTyp", cTpSrvDB );
					
								// TAG basicSvcNm
								oXmlEnvioMainSubsServices.createTag("basicSvcList");
									oXmlEnvioMainSubsServices.addProp("basicSvcNm", "BASICO NUMÈRO");		
									oXmlEnvioMainSubsServices.addProp("basicSvcId", cBasicSvcId );
								oXmlEnvioMainSubsServices.closeTag(); // <basicSvcList>

						oXmlEnvioMainSubsServices.closeTag(); // <primarySvc>

					oXmlEnvioMainSubsServices.closeTag(); // <addSvcLst>

			oXmlEnvioMainSubsServices.closeTag();	// <inputMaintainSubscriptionServices>				
		}
	}
	else if(!bSrcAtivo && (iOperacao == 0)){ // Não Ativo e é DESATIVACAO
		tuxfw_getlogger()->debug("Warnning: Operacao de DESATIVACAO Não realizada, Servico:%s já esta DESATIVADO", cNmSrvATLYS ); 		
		if(oParametro.getInAtivo() == 0) // conceito inverso
			throw new TuxBasicSvcException("24W0000", "Operacao de ATIVACAO Não realizada");
		else
		throw new TuxBasicSvcException("24W0000", "Operacao de DESATIVACAO Não realizada");
	}
	else if(bSrcAtivo && (iOperacao == 1)){// Ativo e é ATIVACAO
		tuxfw_getlogger()->debug("Warnning: Operacao de ATIVACAO Não realizada, Servico:%s já está ATIVADO.", cNmSrvATLYS ); 		
		if(oParametro.getInAtivo() == 0) // conceito inverso
			throw new TuxBasicSvcException("24W0000", "Operacao de DESATIVACAO Não realizada");
		else
			throw new TuxBasicSvcException("24W0000", "Operacao de ATIVACAO Não realizada");
	}
	
    // Passo 3 - Enviar o Xml para a API MaintainSubscriptionServices

	// Guarda o xml de retorno
	DOMNode* outputDOMNodeMainSubsServices;
	
	outputDOMNodeMainSubsServices =  callRemoteAPI(this->getServiceName(), &oXmlEnvioMainSubsServices, "inputMaintainSubscriptionServices" );
	
	this->trataErro(outputDOMNodeMainSubsServices);

	// Validar de a Operação  Remota retornou erro.
	if(strcmp(getLastStatusCode(),"24I0000") != 0){
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
		throw new TuxBasicSvcException("24E0000", getLastStatusText());
	}

	xml_g->createTag("ServicoVO");
	
	if((pcTagXmlIn = tuxhp->walkTree(dnode,TAG_XML_IN_XMLNS, 0)) != NULL)
	{
		xml_g->addProp( PROP_XML_OUT_XMLNS, pcTagXmlIn );
	};	
	this->registrarContato(xml_g,false);
	xml_g->closeTag();


	// Comunicar Usuario
	this->comunicarUsuario(); 

}
