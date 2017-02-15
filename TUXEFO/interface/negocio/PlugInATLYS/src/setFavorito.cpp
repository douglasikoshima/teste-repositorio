#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include "CallCircle.h"
#include "AvailableService.h"

#include <cstring>

using namespace std;


void PlugInATLYS::setFavorito()
{


   char* pcTagXmlIn = NULL;
   char* pcTagNroAntigo = NULL;
   char* pcTagNroNovo = NULL;
	int   iCdAreaRegistro = -1;
 	int   iNrLinha = -1;
 
   // Dados de Entrada da API
   char  cSbscrpId[256];
   char  cAcao;
   char  cNroAntigo[256] = "";
   char  cNroNovo[256] = "";
   char  cDate[15];
   int   iIsValid;

/* Ler o XML de Entrada vindo da camada JAVA.

<msg>
	<msgHdr>
		<user>2</user>
		<service>TUXPROXYBE</service>
	</msgHdr>
	<msgBody>
		<ProxyLinha>1171010160</ProxyLinha>
		<servico>setFavorito</servico> 
		<numeroAntigo></numeroAntigo>
		<numeroNovo></numeroNovo>
	</msgBody>
</msg>

*/

    // Obtem o SbscrpId
   	strcpy(cSbscrpId,getIdLinhaSistemaOrigem());
    tuxfw_getlogger()->debug("cSbscrpId:%s",cSbscrpId); 

	// Ler as Tags <numeroAntigo> e <numeroNovo> 
	pcTagNroAntigo = tuxhp->walkTree(dnode,"numeroAntigo", 0);
	pcTagNroNovo = tuxhp->walkTree(dnode,"numeroNovo", 0);

	if( (pcTagNroAntigo == NULL) && (pcTagNroNovo == NULL) ){
		throw new TuxBasicSvcException("24E0000", "TAG_<numeroAntigo>_<numeroNovo>_INEXISTENTE");
	}


    // Ler Tag <ProxyLinha> que já foi validade pelo Proxy.
    pcTagXmlIn = tuxhp->walkTree(dnode,"ProxyLinha", 0);

    if ((iNrLinha = atoi(pcTagXmlIn + 2)) <= 0) {
            throw new TuxBasicSvcException(ECD_ATL_TVI_NRLINHA, EMSG_ATL_TVI_NRLINHA);
    }

    *(pcTagXmlIn + 2) = '\0';

    if ((iCdAreaRegistro = atoi(pcTagXmlIn)) <= 0) {
            throw new TuxBasicSvcException(ECD_ATL_TVI_CDAREAREGISTRO, EMSG_ATL_TVI_CDAREAREGISTRO);
    }


	// Definir os Valores condicionais para ACAO, TEXTO, ORIGEM e DESTINO
	  
	  
	if(((pcTagNroAntigo != NULL) && (*pcTagNroAntigo)) &&
	   ((pcTagNroNovo == NULL)   || (!*pcTagNroNovo))     )
	{
	   cAcao = 'E'; 
	   strcpy( cNroAntigo, pcTagNroAntigo );
	}	
	else if( ((pcTagNroAntigo == NULL)  || (!*pcTagNroAntigo)) &&
	         ((pcTagNroNovo != NULL)    && (*pcTagNroNovo)   )    )
	{

		if (! (iIsValid = Util::isValidLine(pcTagNroNovo)))
			throw new TuxBasicSvcException(ECD_INVALID_LINE, EMSG_INVALID_LINE_ADD_FAVOR);
		else  if(iIsValid < 0)
			throw new TuxBasicSvcException(ECD_DISABLED_LINE, EMSG_DISABLED_LINE_ADD_FAVOR);

	   cAcao = 'I';
	   strcpy( cNroNovo, pcTagNroNovo );  
	}
	else if( ((pcTagNroAntigo !=NULL) && (*pcTagNroAntigo)) &&
	         ((pcTagNroNovo != NULL) && (*pcTagNroNovo)   )    )
	{	   

		if (! (iIsValid = Util::isValidLine(pcTagNroNovo)))
			throw new TuxBasicSvcException(ECD_INVALID_LINE, EMSG_INVALID_LINE_ALTER_FAVOR);
		else  if(iIsValid < 0)
			throw new TuxBasicSvcException(ECD_DISABLED_LINE, EMSG_DISABLED_LINE_ADD_FAVOR);

       cAcao = 'A';
	   strcpy( cNroAntigo, pcTagNroAntigo );
	   strcpy( cNroNovo, pcTagNroNovo );
	}
    else
	{
        throw new TuxBasicSvcException("24E0000", "TAG_<numeroAntigo>_<numeroNovo>_VALOR_VAZIO");
	}


// Passa 2 - Acessar GetSubscriptionInfo

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

	int i;// indice para loop do parsing
	bool bAchouFAVORITOS  = false;
	DOMNode* auxDOMSvcAsgmInfo;
	DOMNode* auxDOMPrimarySvc;
	DOMNode* auxDOMCallCircle;

	// Chave de Retorno para a lista de CallCircle
	char cSeqNbr[15] = "";
	char cListTypeInd[3] = "";
	char cMethodInd[3] = "";

	// Recupera o Nome do Servico 
	char cNmSrvATLYS[30];
	strcpy(cNmSrvATLYS, this->traduzNomeServicoToATLYS("FAVORITOS", iCdAreaRegistro));
	
	//Contador do Nro de Tentativas em Chamar o setServico do ATLYS. 
	int iNroTentativasSetServico = 0;
    
	//Loop para Fazer as tentativas para Chamar setServico()
	do{
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

		// Faz o Parsing do PrimarySvc:srcNm="FAVORITOS" no XML de Retorno da API GetSubscriptionInfo
		// OBS: Se alguns dos valores de Retorno não forem encontrados no XML, será lancado uma Execçao.
		i=0; 
		while(!bAchouFAVORITOS && (auxDOMSvcAsgmInfo = tuxhp->walkDOM(outputDOMNodeGetSubscriptionInfo,"svcAsgmInfo", i)) != NULL )
		{
			if((auxDOMPrimarySvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"primarySvc", 0)) != NULL ){
			
				// lendo Atributo "svcNm" da Tag <primarySvc>
				pcTagXmlIn  = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcNm");	
				
				// Se Encontrou o "FAVORITOS"
				if(strcmp(pcTagXmlIn, cNmSrvATLYS) == 0){	

					// Se tem a TAG <callCircle>
					if((auxDOMCallCircle = tuxhp->walkDOM(auxDOMPrimarySvc,"callCircle", 0)) != NULL ){
					
						// lendo Atributo "seqNbr" da Tag <callCircle>
						pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"seqNbr");	
						strcpy(cSeqNbr,pcTagXmlIn);

						// lendo Atributo "listTypeInd" da Tag <callCircle>
						pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"listTypeInd");	
						strcpy(cListTypeInd,pcTagXmlIn);

						// lendo Atributo "methodInd" da Tag <callCircle>
						pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"methodInd");	
						strcpy(cMethodInd,pcTagXmlIn);

						bAchouFAVORITOS = true;
					}
					else{// Erro de consistencia do XML
						tuxfw_getlogger()->debug("TAG_<primarySvc:callCircle>_INEXISTENTE"); 
						throw new TuxBasicSvcException("24E0000", "TAG_<primarySvc:callCircle>_INEXISTENTE");
					}	
				}
			}
			else{// Erro de consistencia do XML
				tuxfw_getlogger()->debug("TAG_<primarySvc>_INEXISTENTE"); 
				throw new TuxBasicSvcException("24E0000", "TAG_<primarySvc>_INEXISTENTE");
			}
			i++;
		}// fim do while do Parsing.

		//Se não Achou o servico FAVORITOS
		if(!bAchouFAVORITOS){

			//Se não Achou o servico FAVORITOS, chamar setServico(),em apenas 1 unica tentativa,
			// para Ativar o FAVORITOS. O que fará com que a TAG <primarySvc svcNm="FAVORITOS" apareca no XML da API GetSubscriptoinInfo

			//Se não foi feita nenhuma Tentativa, faz agora.
			if(iNroTentativasSetServico == 0){
				// Será permitido apenas 1 Tentativa)
				
				// Incrementa o contador de Tentativas de Chamadas ao setServico "FAVORITOS"
				iNroTentativasSetServico++;

				// Chama o metodo setServico() de uma instancia Local do PlugInATLYS, para preservar o
				// contextro da chamada original que no caso é setFavotio().
				char* pcXmlInSetServico ="\
							<msg>\
									<msgHdr>\
											<user>2</user>\
											<service>TuxProxyBE</service>\
									</msgHdr>\
									<msgBody>\
											<ProxyLinha>%d%d</ProxyLinha>\
											<operacao>1</operacao>\
											<servico>FAVORITOS</servico>\
									</msgBody>\
							</msg>";
				/*
				// Preparar instancias de DOMNode e o XMLGen locais, 
				// para a instancia Local do PlugInATLYS. 
				DOMNode* dnodeIn = createDOMNode(cXmlInSetServico);
				XMLGen*  xmlRet  = new XMLGen();

				// Instancia Local do PlugInATLYS, para chamar setServico().
				IBackEnd* pPlug = new PlugInATLYS(dnodeIn , xmlRet, getIdLinhaSistemaOrigem());

				try{
					// Chamada do Metodo.
					pPlug->setServico();
				}
				catch(...){
					tuxfw_getlogger()->debug("catch na Chamada Local pPlug->setServico()"); 
					// destruir os objs criados neste escopo					
					tuxfw_getlogger()->debug("delete pPlug"); 
					delete pPlug;  // PlugInATLYS  
					delete xmlRet; // XMLGen					
					throw;
				}

				// Obter o Retorno
				TuxMessage* outXml = new TuxMessage();
				outXml->setMessageBody(xmlRet);

				DOMNode* dnodeOut = createDOMNode(outXml->getMessageBody());

				if((tuxhp->walkDOM(dnodeOut,"ServicoVO", 0)) == NULL){
					
					tuxfw_getlogger()->debug("Erro em setServico(), Nao Retornou TAG <ServicoVO>"); 

					// destruir os objs criados neste escopo
					delete outXml; // TuxMessage
					delete pPlug;  // PlugInATLYS  
					delete xmlRet; // XMLGen

					throw new TuxBasicSvcException("24E0000", "Nao foi possivel ativar FAVORITOS");
				}  	
				
			    // destruir os objs criados neste escopo
				delete outXml; // TuxMessage
				delete pPlug;  // PlugInATLYS  
				delete xmlRet; // XMLGen
				*/

				char cXmlInSetServico[strlen(pcXmlInSetServico) + 11];

				sprintf(cXmlInSetServico, pcXmlInSetServico, iCdAreaRegistro, iNrLinha);

				callSetServico(cXmlInSetServico);
			}
			else{				
				tuxfw_getlogger()->debug("Nao Encontrado o PrimarySvc:svcNm=%s",cNmSrvATLYS); 
				tuxfw_getlogger()->debug("Mesmo apos %d Chamada de setServico().", iNroTentativasSetServico); 									
				throw new TuxBasicSvcException("24E0000", "Nao Encontrado o PrimarySvc:svcNm requisitado.");
			}

		}
	}
	while(!bAchouFAVORITOS);

	/*
	<inputGetCallingCircle>
       <callCircleInfo methodInd="" listTypeInd="" seqNbr="291436050" /> 
    </inputGetCallingCircle>
	*/

	XMLGen oXmlEnvioGetCallingCircle;
	
	oXmlEnvioGetCallingCircle.createTag("inputGetCallingCircle");
		oXmlEnvioGetCallingCircle.createTag("callCircleInfo");
			oXmlEnvioGetCallingCircle.addProp("methodInd",cMethodInd);
			oXmlEnvioGetCallingCircle.addProp("listTypeInd", cListTypeInd);
			oXmlEnvioGetCallingCircle.addProp("seqNbr", cSeqNbr );
		oXmlEnvioGetCallingCircle.closeTag();
	oXmlEnvioGetCallingCircle.closeTag();

	// Guarda o xml de retorno
	DOMNode* outputDOMNodeGetCallingCircle;
	
	outputDOMNodeGetCallingCircle = callRemoteAPI(this->getServiceName(), &oXmlEnvioGetCallingCircle, "inputGetCallingCircle" );
	
	if(outputDOMNodeGetCallingCircle == NULL){
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 
		throw new TuxBasicSvcException(getLastStatusCode(),getLastStatusText());	
	}

	// Validar de a Operação  Remota retornou erro.
	if(strcmp(getLastStatusCode(),"24I0000") != 0){
	
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
		throw new TuxBasicSvcException("24E0000", getLastStatusText());
	}


	// Faz o Parsing da lista de CallCircle no XML de Retorno da API GetCallingCircle
	// e Monta uma lista com os CallCircles

	// Lista de Retorno
	list<CallCircle> olstCallCircle;
	CallCircle oCallCircle;

    //Montar a Lista
	i=0;	
	while((auxDOMCallCircle = tuxhp->walkDOM(outputDOMNodeGetCallingCircle,"callCircleInfo", i)) != NULL ){

		// lendo Atributo "accessNbr" da Tag <callCircleInfo>
		pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"accessNbr");	
		oCallCircle.setAccNbr(pcTagXmlIn);
		
		// lendo Atributo "effDt" da Tag <callCircleInfo>
		pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"effDt");	
		oCallCircle.setEffDt(pcTagXmlIn);

		// lendo Atributo "exprDt" da Tag <callCircleInfo>
		pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"exprDt");	
		oCallCircle.setExprDt(pcTagXmlIn);

 		olstCallCircle.push_back( oCallCircle );

		i++;
	}

	if(olstCallCircle.size() == 0){
		tuxfw_getlogger()->debug("LISTA VAZIA: Nenhum <callCircleInfo> Encontrado"); 

        // Só não gera Execção de Lista VAZIA se a Operação for de INSERCAO
		if(cAcao == 'I'){
			tuxfw_getlogger()->debug("Inserir o Priemiro Elemento <callCircleInfo> na LISTA."); 
		}
		else{
			tuxfw_getlogger()->debug("Operacao de INSERCAO deve ser chamada para criar a LISTA"); 
			throw new TuxBasicSvcException("24E0000", "Nenhum <callCircleInfo> Encontrado, Operacao de INSERCAO deve ser chamada.");
		}
	}

	// Efetua a Operação sobre a Lista Retornada.
	if(cAcao == 'I'){ // Insercao

		if(strcmp(cNroNovo,"") != 0){

			oCallCircle.setAccNbr(cNroNovo);
			oCallCircle.setEffDt(CAvailableService::getSysDateBD(cDate));
			oCallCircle.setExprDt("9999-12-31");

			olstCallCircle.push_back(oCallCircle);
		}
		else{
			tuxfw_getlogger()->debug("Erro: NroNovo VAZIO"); 		
			throw new TuxBasicSvcException("24E0000", "Insercao Nao Concluida.");
		}
	}
	else if(cAcao == 'A'){ // Alteracao

		if(strcmp(cNroAntigo,"") !=0 || strcmp(cNroNovo,"") !=0){

			// Iterator para a lista.
			list<CallCircle>::iterator oCallCircle_iter;

			//Setar  o Nro para Alteracao
			oCallCircle.setAccNbr(cNroAntigo);

			bool bAlterou = false;
			// Pesquisar na Lista de CallCircles
			for (oCallCircle_iter = olstCallCircle.begin();
			     oCallCircle_iter != olstCallCircle.end();
				 oCallCircle_iter++){
					 
				// Se o oCallCircle == oCallCircle da Lista.
				if (oCallCircle == *oCallCircle_iter){
					oCallCircle_iter->setAccNbr(cNroNovo);
					bAlterou =true;
				}
			}

			if(!bAlterou){
				tuxfw_getlogger()->debug("Erro: NroAntigo Nao Encontrado na lista de CallCircles"); 		
				throw new TuxBasicSvcException("24E0000", "Alteracao Nao Concluida.");
			}
		}
		else{
			tuxfw_getlogger()->debug("Erro: NroAntigo ou NroNovo VAZIO"); 		
			throw new TuxBasicSvcException("24E0000", "Alteracao Nao Concluida.");
		}
	}
	else if(cAcao == 'E'){ // Exclusao
		if(strcmp(cNroAntigo,"") !=0 ){
			
			oCallCircle.setAccNbr(cNroAntigo);

			olstCallCircle.remove(oCallCircle);
		}
		else{
			tuxfw_getlogger()->debug("Erro: NroAntigo VAZIO"); 		
			throw new TuxBasicSvcException("24E0000", "Exclusao Nao Concluida.");
		}
	};	


	// Enviar a Lista para a API replaceCallingCircle

	/*
	<inputReplaceCallingCircleList>
	  <callCircleInfo seqNbr="291436050" listTypeInd="19" methodInd="2" /> 
 	  <callCircleMemberInfo accessNbr="1196489249" effDt="2004-10-29" exprDt="9999-12-31" /> 
	   ...
	  <callCircleMemberInfo accessNbr="1196317696" effDt="2004-10-29" exprDt="9999-12-31" /> 
  </inputReplaceCallingCircleList>

	*/

	XMLGen oXmlEnvioReplaceCallingCircleList;
	
	oXmlEnvioReplaceCallingCircleList.createTag("inputReplaceCallingCircleList");
		oXmlEnvioReplaceCallingCircleList.createTag("callCircleInfo");
			oXmlEnvioReplaceCallingCircleList.addProp("seqNbr", cSeqNbr );
			oXmlEnvioReplaceCallingCircleList.addProp("listTypeInd", cListTypeInd);
			oXmlEnvioReplaceCallingCircleList.addProp("methodInd",cMethodInd);						
		oXmlEnvioReplaceCallingCircleList.closeTag();
        
		// Percorer a Lista e Montar as tags <callCircleMemberInfo>
		while( olstCallCircle.size() > 0){

			// Le o valor do elemento do topo.
			oCallCircle = olstCallCircle.front();

			oXmlEnvioReplaceCallingCircleList.createTag("callCircleMemberInfo");
				oXmlEnvioReplaceCallingCircleList.addProp("accessNbr", oCallCircle.getAccNbr() );
				oXmlEnvioReplaceCallingCircleList.addProp("effDt",  oCallCircle.getEffDt());
				oXmlEnvioReplaceCallingCircleList.addProp("exprDt", oCallCircle.getExprDt());						
			oXmlEnvioReplaceCallingCircleList.closeTag();

			// remove no topo da lista
			olstCallCircle.pop_front();
		};

	oXmlEnvioReplaceCallingCircleList.closeTag();

	// Guarda o xml de retorno
	DOMNode* outputDOMNodeReplaceCalling;
	
	outputDOMNodeReplaceCalling = callRemoteAPI(this->getServiceName(), &oXmlEnvioReplaceCallingCircleList, "inputReplaceCallingCircleList" );
	
	// Validar de a Operação  Remota retornou erro.
	if(strcmp(getLastStatusCode(),"24I0000") != 0){
	
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	}


	xml_g->createTag(TAG_XML_OUT_FAVORITOS_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
	// Registro de contato
	this->registrarContato(xml_g,false);
	xml_g->closeTag();

}
