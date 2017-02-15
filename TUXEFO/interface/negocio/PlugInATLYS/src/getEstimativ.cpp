#include "PlugInATLYS.h"

#include <tuxfw.h>

#include <map>
#include <string>

using namespace std;


void PlugInATLYS::getEstimativa()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyLinha></ProxyLinha>
			<ProxyOperacao>getEstimativa</ProxyOperacao>
			<idcontasistemaorigem></idcontasistemaorigem>
		</msgBody>
	</msg>
*/ 	

   char* pcTagXmlIn = NULL;
   // Dados de Entrada
   char  cAcctNbr[256];

	if((pcTagXmlIn = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0)) == NULL)
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_NE, MSG_ID_CONTA_SIS_ORG_NE);		

	strcpy( cAcctNbr, pcTagXmlIn );

    tuxfw_getlogger()->debug("TAG_XML_IN_ID_CONTA_SIS_ORIGEM:%s", cAcctNbr); 

	// CARREGAMENTO DE TABELA DE DOMINIO ( DE->PARA) para a tradução do 
	// atributo <cycleCd> da Tag <bill> API GetBillV2

	//Acesso Remoto a API getReferenceDataV2 
    /*
         <inputGetReferenceDataV2>
                 <tableNm val="xxx"/>
         </inputGetReferenceDataV2>

	*/
	
	// Obter o Valor para 
	XMLGen oXmlEnvioGetReferenceDataV2;
	
	oXmlEnvioGetReferenceDataV2.createTag("inputGetReferenceDataV2");
	oXmlEnvioGetReferenceDataV2.createTag("tableNm");
	oXmlEnvioGetReferenceDataV2.addProp("val", "PAYMENT_TYPE");
	oXmlEnvioGetReferenceDataV2.closeTag();
	oXmlEnvioGetReferenceDataV2.closeTag();

	// Guarda o xml de retorno
	DOMNode* outputDOMNodeGetReferenceDataV2;

    /*
	<outputGetReferenceDataV2>
	  <refDataLst tableNm="xxx" code="xx" desc="xx" xlateDesc="xxx" default="xxx" langCd="xxx"/>
	  <refDataLst tableNm="xxx" code="xxx" desc="xxx" xlateDesc="xxx" default="xxx" langCd="xxx"/>
	</outputGetReferenceDataV2>
    */
	outputDOMNodeGetReferenceDataV2 =  callRemoteAPI(this->getServiceName(), &oXmlEnvioGetReferenceDataV2, "inputGetReferenceDataV2");

	if(outputDOMNodeGetReferenceDataV2 == NULL){
		tuxfw_getlogger()->debug("Chamada Remota GetReferenceDataV2:  Erro:%d | Text: %s", getLastStatusCode() , getLastStatusText() ); 
	}

	// Montar o Container MAP com os <Codigos,Desc>.
	map<string,string> mapTbPAYMENT_TYPE; 
    string sCode;
	string sDesc;

	int j=0;
	DOMNode* auxDOMNodeGetReferenceDataV2;
	
	// Ler as Tag <refDataLst>
	tuxfw_getlogger()->debug("CARREGAR a MAP TABLE");
	while( (auxDOMNodeGetReferenceDataV2 = tuxhp->walkDOM(outputDOMNodeGetReferenceDataV2,"refDataLst", j)) != NULL )
	{	
		// ler Atributo code da Tag <refDataLst> 
		if((pcTagXmlIn = tuxhp->getAttrValue(auxDOMNodeGetReferenceDataV2,"code")) == NULL){
			throw new TuxBasicSvcException(ECD_ATL_TNE_REFDATALST_CODE, EMSG_ATL_TNE_REFDATALST_CODE);
		}	
		sCode = pcTagXmlIn; 

		// ler Atributo desc da Tag <refDataLst> 
		if((pcTagXmlIn = tuxhp->getAttrValue(auxDOMNodeGetReferenceDataV2,"desc")) == NULL){
			throw new TuxBasicSvcException(ECD_ATL_TNE_REFDATALST_DESC, EMSG_ATL_TNE_REFDATALST_DESC);
		}
		sDesc = pcTagXmlIn; 

		// Guarda para <Code,Desc> na MAP 
		mapTbPAYMENT_TYPE[sCode] = sDesc;

		j++;
	}
		
// DEBUG - MOSTRAR A MAP TABLE NO LOG

	typedef map<string,string>::const_iterator CI;
	CI p;

	if(mapTbPAYMENT_TYPE.begin() == mapTbPAYMENT_TYPE.end()){
		tuxfw_getlogger()->debug("MAP TABLE VAZIA");
	}
	else
	{
		tuxfw_getlogger()->debug("DUMP da MAP TABLE");
		for (p = mapTbPAYMENT_TYPE.begin();  p != mapTbPAYMENT_TYPE.end(); ++p){ 
			tuxfw_getlogger()->debug("<Code=%s,Desc=%s>",(char*)p->first.c_str(),(char*)p->second.c_str());		
		}
	}

//
// Inicio da Operação.

/* Monta o xml de Envio para o Servico Remoto.

    <body>
		<inputGetPaymentMethod acctNbr="xxx"/>
	</body>
*/

	XMLGen oXmlEnvioGetPaymentMethod;
	
	oXmlEnvioGetPaymentMethod.createTag("inputGetPaymentMethod");
	oXmlEnvioGetPaymentMethod.addProp("acctNbr", cAcctNbr);
	oXmlEnvioGetPaymentMethod.closeTag();

	// Guarda o xml de retorno
	DOMNode* outputDOMNodeGetPaymentMethod;
	
	outputDOMNodeGetPaymentMethod =  callRemoteAPI(this->getServiceName(), &oXmlEnvioGetPaymentMethod, "inputGetPaymentMethod");
	
	if(outputDOMNodeGetPaymentMethod == NULL){
		tuxfw_getlogger()->debug("Chamada Remota GetPaymentMethod:  Erro:%d | Text: %s", getLastStatusCode() , getLastStatusText() ); 
	}
	

/*
    <body>
			<inputGetAccountInfoV3 acctNbr="xxx"/>
	</body>

*/
 	XMLGen oXmlEnvioGetAccountInfoV3;
	
	oXmlEnvioGetAccountInfoV3.createTag("inputGetAccountInfoV3");
	oXmlEnvioGetAccountInfoV3.addProp("acctNbr", cAcctNbr);
	oXmlEnvioGetAccountInfoV3.closeTag();

	// Guarda o xml de retorno
	DOMNode* outputDOMNodeGetAccountInfoV3;
	
	outputDOMNodeGetAccountInfoV3 =  callRemoteAPI(this->getServiceName(), &oXmlEnvioGetAccountInfoV3, "inputGetAccountInfoV3");
	
	if(outputDOMNodeGetAccountInfoV3 == NULL){
		tuxfw_getlogger()->debug("Chamada Remota GetAccountInfoV3:  Erro:%d | Text: %s", getLastStatusCode() , getLastStatusText() ); 
	}
	

	// XML de retorno para o FO
	/*
	<LupaFaturamentoPosVO>
	<LFPagamento>
		<dsFormaPagamento>
		<dsEstimativaSaldo>
	</LFPagamento>
	</LupaFaturamentoPosVO>

    */

	xml_g->createTag("LupaFaturamentoPosVO");

	if((pcTagXmlIn = tuxhp->walkTree(dnode,TAG_XML_IN_XMLNS, 0)) != NULL)
	{
		xml_g->addProp( PROP_XML_OUT_XMLNS, pcTagXmlIn );
	};

	// <LFPagamento>
	xml_g->createTag("LFPagamento");


	//<dsFormaPagamento>

	/* Le o XML de Retorno do GetPaymentMethod.

	<body>
     <outputGetPaymentMethod  acctNbr="xxx" effDt="xxx" exprDt="xxx" pymtTypeCd="xxx" pymtCategoryCode ="xxx" authCd="xxx" authDt="xxx" rjctnDt="xxx" preNotifyInd="xxx" currencyNbr="xxx"/>
    </body>
   */

    /*DOMNode* auxDOMNodeGetPaymentMethod;

	auxDOMNodeGetPaymentMethod = tuxhp->walkDOM(outputDOMNodeGetPaymentMethod,"", 0);
	*/
	// lendo Atributo "pymtTypeCd" da Tag <outputGetPaymentMethod> API GetPaymentMethod
	if( (pcTagXmlIn = tuxhp->getAttrValue(outputDOMNodeGetPaymentMethod,"pymtTypeCd")) == NULL){
		throw new TuxBasicSvcException(ECD_ATL_TNE_OUTGETPAYMENTMETHOD_PYMTTYPECD, EMSG_ATL_TNE_OUTGETPAYMENTMETHOD_PYMTTYPECD);	
	}	

    // Valor q sera traduzido.
	sCode = pcTagXmlIn;

	tuxfw_getlogger()->debug("TAG:Atributo <pymtTypeCd> para traducao : %s" , (char*)sCode.c_str());
	
			
	// Tradução do valor em "pymtTypeCd"
	sDesc = mapTbPAYMENT_TYPE[sCode]; 

	// Se não acho nenhuma Tag refDataLst, nao Traduz
	if(sDesc != ""){
		xml_g->addItem("dsFormaPagamento", (char*)sDesc.c_str() );
		tuxfw_getlogger()->debug("TAG:Atributo <pymtTypeCd> FOI TRADUZIDA: %s:->%s" , (char*)sCode.c_str(), (char*)sDesc.c_str());
	}
	else{
		xml_g->addItem("dsFormaPagamento", (char*)sCode.c_str());
		tuxfw_getlogger()->debug("TAG:Atributo <pymtTypeCd> NAO FOI TRADUZIDA: %s:->%s" , (char*)sCode.c_str(), (char*)sDesc.c_str());
	}


	//<dsEstimativaSaldo>

	/*  Le o XML de Retorno do GetAccountInfoV3.
	<outputGetAccountInfoV3>
	 .....
	  <estimatedBalance>
		<estBal>
		  <base crncyCd="xxx" amntDue="xxx"/>
		  ......
		</estBal>
		 ........
		 ........
	  </estimatedBalance>
	 ........
	</outputGetAccountInfoV3>
	*/
	DOMNode* auxDOMNodeGetAccountInfoV3;

	auxDOMNodeGetAccountInfoV3 = tuxhp->walkDOM(outputDOMNodeGetAccountInfoV3,"estimatedBalance", 0);
    auxDOMNodeGetAccountInfoV3 = tuxhp->walkDOM(auxDOMNodeGetAccountInfoV3,"estBal", 0);
	auxDOMNodeGetAccountInfoV3 = tuxhp->walkDOM(auxDOMNodeGetAccountInfoV3,"base", 0);

	// lendo Atributo "amntDue" da Tag <base> API GetAccountInfoV3
	if( (pcTagXmlIn = tuxhp->getAttrValue(auxDOMNodeGetAccountInfoV3,"amntDue")) == NULL){
		throw new TuxBasicSvcException(ECD_ATL_TNE_ESTIMATEDBALANCE_ESTBAL_BASE_AMNTDUE, EMSG_ATL_TNE_ESTIMATEDBALANCE_ESTBAL_BASE_AMNTDUE);	
	}
	xml_g->addItem("dsEstimativaSaldo", pcTagXmlIn);
	

	xml_g->closeTag();// </LFPagamento>
	xml_g->closeTag();// </LupaFaturamentoPosVO>

}
