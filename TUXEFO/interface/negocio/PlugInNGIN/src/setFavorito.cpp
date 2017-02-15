#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;
string sTempLogValue;

void PlugInNGIN::setFavoritoPlanosNovos()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setFavorito</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario></usuario>
			<servico></servico>
			<numeroNovo></numeroNovo>
			<numeroAntigo></nomeroAntigo>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	// Obtem o numero da linha
	char       c_area [3] = "";
	char       c_linha[16] = "";
	char*      pc_Linha   = NULL;
	char*      pc_inNewNbr = NULL;
	char*      pc_inOldNbr = NULL;
	int        iIsValid;

	pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	if(pc_Linha == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_LINHA, EMSG_TAG_XML_IN_NE_LINHA);

	if (*pc_Linha == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_LINHA, EMSG_TAG_XML_IN_VV_LINHA);

	if (strlen(pc_Linha) < 10 || strlen(pc_Linha) > 11)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_LINHA, EMSG_TAG_XML_IN_VI_LINHA);
	
	strncpy(c_area, pc_Linha, 2);
	//strncpy(c_linha, &pc_Linha[2], 8);
	
	sprintf(c_linha, "%s", (char*)&pc_Linha[2] );

	tuxfw_getlogger()->debug("DDD: %s", c_area);
	tuxfw_getlogger()->debug("Linha: %s", c_linha);

	// Trata a TAG 'numeroNovo'
	pc_inNewNbr = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO_NOVO, 0);

	// Trata a TAG 'numeroAntigo'
	pc_inOldNbr = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO_ANTIGO, 0);

	int i_operacao = 0;

	// Verificação p/ inclusao do numero favorito
	if ( strlen(pc_inNewNbr) == 10 || strlen(pc_inNewNbr) == 11 )
	{
		i_operacao = 1;

		if (! (iIsValid = Util::isValidLine(pc_inNewNbr)))
			throw new TuxBasicSvcException(ECD_INVALID_LINE, EMSG_INVALID_LINE_ADD_FAVOR);

		else if(iIsValid < 0)
			throw new TuxBasicSvcException(ECD_DISABLED_LINE, EMSG_DISABLED_LINE_ADD_FAVOR);

	}
	
	// Verificação p/ alteracao do numero favorito
	if (strlen(pc_inOldNbr) == 10 || strlen(pc_inOldNbr) == 11 )
	{
		if(i_operacao == 1)
			// está havendo uma alteracao
			i_operacao = 2;
		else
			// está havendo uma exclusão
			i_operacao = 3;

		if (! (iIsValid = Util::isValidLine(pc_inOldNbr)))
			throw new TuxBasicSvcException(ECD_INVALID_LINE, EMSG_INVALID_LINE_ALTER_FAVOR);

		else if(iIsValid < 0)
			throw new TuxBasicSvcException(ECD_DISABLED_LINE, EMSG_DISABLED_LINE_ADD_FAVOR);

	}

	if(i_operacao == 0)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_NUMERO_NOVO, EMSG_TAG_XML_IN_NE_NUMERO_NOVO);

	tuxfw_getlogger()->debug("i_operacao: %d", i_operacao);

/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<msg>	
		<msgHdr>		
			<service>mblGetProfile</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="InSid">Identificação do Serviço</atribute>
			<atribute name="CDDD">Área Registro</atribute>
			<atribute name="NUMTELF">Linha Telefônica</atribute>
			<atribute name="OPERACAO">Operação a realizar</atribute>
			<atribute name="FAVORITOINCLUIR">Inclusão de Favorito</atribute>
			<atribute name="FAVORITOEXCLUIR">Exclusão de Favorito</atribute>  
		</msgBody>
	</msg>
*/

	char     vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char     vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen   toGWNGIN;
	DOMNode *fromGWNGIN = NULL;

	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%s</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, vc_InSid, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="CDDD">CDDD_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CDDD, c_area, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="NUMTELF">NUMTELF_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_NUM_TELF, c_linha, XML_NGN_IN_ATTRIBUTE);
	
	// TAG '<attribute name="OPERACAO">OPERACAO_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%d</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_OPERACAO, i_operacao, XML_NGN_IN_ATTRIBUTE);

	if(i_operacao == 1 || i_operacao == 2)
		// TAG '<attribute name="FAVORITOINCLUIR">FAVORITOINCLUIR_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_FAVORITO_INCLUIR, pc_inNewNbr, XML_NGN_IN_ATTRIBUTE);

	if(i_operacao == 2 || i_operacao == 3)
		// TAG '<attribute name="FAVORITOEXCLUIR">FAVORITOEXCLUIR_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_FAVORITO_EXCLUIR, pc_inOldNbr, XML_NGN_IN_ATTRIBUTE);

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_SET_FAVORITE);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body/>
	</response>

*/

	// Objeto para armazenar a resposta
	DOMNode *po_response = NULL;

	// Verifica a tag 'response'
	po_response = tuxhp->walkDOM(fromGWNGIN, XML_NGN_OUT_RESPONSE, 0);
	
	if (po_response == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RESPONSE, EMSG_NGN_OUT_TNE_RESPONSE);
	
	// trata erro genérico do NGIN
	this->trataErro(po_response);

/*	FO -> JAVA

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<FavoritoVO>
			</FavoritoVO>
		</msgBody>
	</msg>
*/
	
	xml_g->createTag(TAG_XML_OUT_FAVORITOS_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
	// Registro de contato
	this->registrarContato(xml_g,false);
	xml_g->closeTag();

}

void PlugInNGIN::setFavoritoRequest()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setFavorito</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario></usuario>
			<servico></servico>
			<numeroNovo></numeroNovo>
			<numeroAntigo></nomeroAntigo>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
			
			<qtMaxInclusao></qtMaxInclusao>
			<IND_POSSUIFAVORITOS></IND_POSSUIFAVORITOS>
			
		</msgBody>
	</msg>
*/

	// Obtem o numero da linha
	char       c_area [3] = "";
	char       c_linha[16] = "";
	char*      pc_Linha   = NULL;
	char*      pc_inNewNbr = NULL;
	char*      pc_inOldNbr = NULL;
	char*	   pc_idUser = NULL;
	int        iIsValid;

	sTempLogValue += "Entrada do serviço setFavoritos";
	
	/*
	string snrLinha2 = "11222222";
	string sdsMetodo_NGIN2 = "metodo lallalalalal";
	string sdsObservacao2 = "Retorno do Serviço";
	string sdsXML_IN2 = "<mam>testest<mam>";
	string sdsXML_OUT2 = " ";

	Util::logarChamadaNGIN(snrLinha2, sdsMetodo_NGIN2, sdsObservacao2, sdsXML_IN2, sdsXML_OUT2 );
	*/
	
	
	tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
	
	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	if(pc_Linha == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_LINHA, EMSG_TAG_XML_IN_NE_LINHA);

	if (*pc_Linha == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_LINHA, EMSG_TAG_XML_IN_VV_LINHA);

	if (strlen(pc_Linha) < 10 || strlen(pc_Linha) > 11)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_LINHA, EMSG_TAG_XML_IN_VI_LINHA);
	
	strncpy(c_area, pc_Linha, 2);
	//strncpy(c_linha, &pc_Linha[2], 8);
	sprintf(c_linha, "%s", (char*)&pc_Linha[2] );
	

	tuxfw_getlogger()->debug("DDD: %s", c_area);
	tuxfw_getlogger()->debug("Linha: %s", c_linha);

	// Trata a TAG 'numeroNovo'
	pc_inNewNbr = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO_NOVO, 0);

	// Trata a TAG 'numeroAntigo'
	pc_inOldNbr = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO_ANTIGO, 0);

	int i_operacao = 0;

	// Verificação p/ inclusao do numero favorito
	if (strlen(pc_inNewNbr) == 10 || strlen(pc_inNewNbr) == 11 )
	{
		i_operacao = 1;

		if (! (iIsValid = Util::isValidLine(pc_inNewNbr)))
			throw new TuxBasicSvcException(ECD_INVALID_LINE, EMSG_INVALID_LINE_ADD_FAVOR);

		else if(iIsValid < 0)
			throw new TuxBasicSvcException(ECD_DISABLED_LINE, EMSG_DISABLED_LINE_ADD_FAVOR);

	}

	// Verificação p/ alteracao do numero favorito
	if (strlen(pc_inOldNbr) == 10 || strlen(pc_inOldNbr) == 11 )
	{
		if(i_operacao == 1)
			// está havendo uma alteracao
			i_operacao = 2;
		else
			// está havendo uma exclusão
			i_operacao = 3;

		if (! (iIsValid = Util::isValidLine(pc_inOldNbr)))
			throw new TuxBasicSvcException(ECD_INVALID_LINE, EMSG_INVALID_LINE_ALTER_FAVOR);

		else if(iIsValid < 0)
			throw new TuxBasicSvcException(ECD_DISABLED_LINE, EMSG_DISABLED_LINE_ADD_FAVOR);

	}

	if(i_operacao == 0)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_NUMERO_NOVO, EMSG_TAG_XML_IN_NE_NUMERO_NOVO);

	
	string sqtMaxInclusao;
	string sInd_PossuiFavoritos;
	
	// Otem a TAG 'qtMaxInclusao' que corresponde ao parametro 'OutMaxPrefNbr' retornado pelo NGIN.
	sqtMaxInclusao = tuxhp->walkTree(dnode, TAG_XML_IN_QT_MAX_INCLUSAO, 0);
	
	// Otem a TAG 'IND_POSSUIFAVORITOS'
	sInd_PossuiFavoritos = tuxhp->walkTree(dnode, TAG_XML_IN_POSSUI_FAVORITOS, 0);
	
	int iqtMaxInclusao = atoi(sqtMaxInclusao.data());
	
	string sCdParametro;
	string sDsParametro;
	int iDsParametroAntigo, iDsParametroNovo;
	
	
	sCdParametro = "FAVORITO_ANTIGO";
	Util::lerApoioParametro(sCdParametro, sDsParametro);
	iDsParametroAntigo = atoi(sDsParametro.data());
	
	
	sCdParametro = "FAVORITO_NOVO";
	Util::lerApoioParametro(sCdParametro, sDsParametro);
	iDsParametroNovo = atoi(sDsParametro.data());
	
	string spc_ddd = c_area;	
	bool bRegraNova = true;

	
	if ( iDsParametroNovo == iDsParametroAntigo)
	{
		//REGRA NOVA - TABELA_DDD() - IND_POSSUI_FAVORITOS(1)
		sTempLogValue = "Condicion if ( iDsParametroNovo == iDsParametroAntigo) is true";
		tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
		bRegraNova = true;
	}
	else
	{
		if ( iDsParametroAntigo == iqtMaxInclusao /*NGIN()*/)
		{
		   //REGRA VELHA - TABELA_REGIONAL() - IND_POSSUI_FAVORITOS(0)
		sTempLogValue = "Condicion if ( iDsParametroAntigo == iqtMaxInclusao ) is true";
		tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
		   
			bRegraNova = false;	   
		} 
		else
		{
			//REGRA NOVA - TABELA_DDD() - IND_POSSUI_FAVORITOS(1)
			sTempLogValue = "Condicion else  bRegraNova = true; is true";
			tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
			
			bRegraNova = true;
		}
	}
	
	if( bRegraNova ==true )
	{
		if ( 1 == Util::VerificarlinhaFavoritaValida(spc_ddd, VALIDA_TABELA_DDD /*1=DDD 2=REGIONAL*/))
		{
			sTempLogValue = "Validacao de DDD para a Linha[" + spc_ddd + c_linha + *new string("] realizada com sucesso.");
			tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
		
			Util::trim(sInd_PossuiFavoritos);
			if ( sInd_PossuiFavoritos.compare("0") == 0)
			{
				XMLGen   toGWNGIN_transService;
				string sXMLToGWNGIN;
				DOMNode *fromGWNGIN_transService = NULL;
				
				int iNrInSid;
				//int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_TRANS_CHANGE_SERVICE);
				iNrInSid = 9500;
				sXMLToGWNGIN = "<field name=\"service\">";
				sXMLToGWNGIN += XML_NGN_SVC_TRANS_CHANGE_SERVICE;
				sXMLToGWNGIN += "</field>";
			
				sXMLToGWNGIN += "<field name=\"InSid\">";
				sXMLToGWNGIN += Util::intToStr(iNrInSid);
				sXMLToGWNGIN += "</field>";
				
				sXMLToGWNGIN += "<field name=\"InCellNumber\">";
				sXMLToGWNGIN += pc_Linha;
				sXMLToGWNGIN += "</field>";

				sXMLToGWNGIN += "<field name=\"InUsername\">";
				sXMLToGWNGIN += pc_idUser;
				sXMLToGWNGIN += "</field>";
				sXMLToGWNGIN += "<field name=\"InObs\"></field>";
				sXMLToGWNGIN += "<field name=\"InServiceId\">FAVBOAHORAII</field>";
				sXMLToGWNGIN += "<field name=\"InOper\">1</field>";
				sXMLToGWNGIN += "<field name=\"InDealer\">ITU100</field>";	

				toGWNGIN_transService.aggregateXML((char *)(sXMLToGWNGIN.data()));
				int len;
				
				// Logar um registro na tabela do XML para o NGIN.
				string snrLinha = pc_Linha;
				string sdsMetodo_NGIN = XML_NGN_SVC_TRANS_CHANGE_SERVICE;
				string sdsObservacao = "Enviando ao Serviço NGIN";
				string sdsXML_IN = toGWNGIN_transService.retrieveXML( &len );
				string sdsXML_OUT = " ";
				
				Util::logarChamadaNGIN(snrLinha, sdsMetodo_NGIN, sdsObservacao, sdsXML_IN, sdsXML_OUT );
				
				// Objeto para armazenar a resposta
				DOMNode *po_response_transService = NULL;
				try
				{
					// Chama o serviço e verifica o retorno
					fromGWNGIN_transService = callRemoteAPI(this->getServiceName(), &toGWNGIN_transService, XML_NGN_SVC_TRANS_CHANGE_SERVICE);

				if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
					throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

				if (fromGWNGIN_transService == NULL)
					throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

			/*	GWNGIN -> FO

				<?xml version="1.0" encoding="UTF-8"?>
				<response succeed="yes">
					<body/>
				</response>
			*/

				// Verifica a tag 'response'
				po_response_transService = tuxhp->walkDOM(fromGWNGIN_transService, XML_NGN_OUT_RESPONSE, 0);

				if (po_response_transService == NULL)
					throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RESPONSE, EMSG_NGN_OUT_TNE_RESPONSE);
					
				}
				catch(...)
				{
					sTempLogValue = "Falha na chamdada do serviço ["+*new string(XML_NGN_SVC_TRANS_CHANGE_SERVICE)+"] para a Linha[" + spc_ddd + c_linha + *new string("].");
					tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
				}

				snrLinha = pc_Linha;
				sdsMetodo_NGIN = XML_NGN_SVC_TRANS_CHANGE_SERVICE;
				sdsObservacao = "Retorno do Serviço NGIN";
				sdsXML_IN = " ";
				try
				{
					sdsXML_OUT = tuxhp->getNodeAsString(po_response_transService);
				}
				catch(...)
				{
				}

				Util::logarChamadaNGIN(snrLinha, sdsMetodo_NGIN, sdsObservacao, sdsXML_IN, sdsXML_OUT );				
				char *cSucceed = tuxhp->walkAttr(fromGWNGIN_transService, XML_NGN_OUT_RESPONSE, XML_NGN_OUT_RESPONSE_SUCCEED, 0);

				if (Util::cmp(cSucceed,"no"))
				{
					fromGWNGIN_transService	= tuxhp->walkDOM(fromGWNGIN_transService, XML_NGN_OUT_RESPONSE, 0);

					char *cErrCode	= tuxhp->walkAttr(fromGWNGIN_transService, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_CODE, 0);

					if ( Util::cmp(cErrCode, ECD_NGN_TRANS_FAVORITOS_SERVICE_JA_ATIVO))
					{
						sTempLogValue = "O serviço [" + *new string(XML_NGN_SVC_TRANS_CHANGE_SERVICE) + "] do NGIN que recebeu a linha ["+  *new string (pc_Linha)+ "] nao esta ativo. Erro codigo : " + *new string(cErrCode) ;
						tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
					}
				}
				
				
			}
		}
		else
		{
			sTempLogValue = "A Linha[" + spc_ddd + c_linha;
			sTempLogValue += "] tem não tem o codigo area cadastrado na tabela de DDDs AUTORIZADOS.";
			tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
			throw new TuxBasicSvcException(ECD_DDD_NAO_CADASTRADO_COMO_AUTORIZADO, EMSG_DDD_NAO_CADASTRADO_COMO_AUTORIZADO);
		}
	}
	else
	{
		if ( 1 != Util::VerificarlinhaFavoritaValida(spc_ddd, VALIDA_TABELA_REGIONAL /*1=DDD 2=REGIONAL*/))
		{
			sTempLogValue = "A Linha[" + spc_ddd + c_linha;
			sTempLogValue += "] não tem o codigo area cadastrado na tabela de REGIONAIS AUTORIZADAS";
			tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
			throw new TuxBasicSvcException(ECD_REGIONAL_NAO_CADASTRADA_COMO_AUTORIZADA, EMSG_REGIONAL_NAO_CADASTRADA_COMO_AUTORIZADA);			
		}
		else
		{
			sTempLogValue = "Validacao de Regional para a Linha[" + spc_ddd + c_linha + *new string("] realizada com sucesso.");
			tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
		}
	}
	
	
	
	

	tuxfw_getlogger()->debug("i_operacao: %d", i_operacao);

/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<request service="mbChangeFavoriteNumbers" sid="Identificacao do serviço" timestamp="2004-01-01">
		<record>
			<field name="inUsername">Usuário</field>
			<field name="inOperator">Aplicação responsável pela requisição</field>
			<field name="inMedia">Mídia Utilizada</field>
			<field name="inSid">Identificação do sistema externo</field>
			<field name="inService">Serviço utilizado</field>
			<field name="inCellNumber">Número do telefone</field>
			<field name="inNewNbr">Novo destino especial</field>
			<field name="inOldNbr">Novo destino especial</field>
		</record>
	</request>

*/

	char     vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char     vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	char     timeStamp[20];
	char     inCellNumber[12];
	XMLGen   toGWNGIN;
	DOMNode *fromGWNGIN = NULL;
	CParametro oParametro;

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);	

	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_SET_FAVORITE_NUMBERS);

	//Util::DtAtualStamp(timeStamp, "RRRR-MM-DD");
	sprintf(inCellNumber,"%s%s",c_area,c_linha);

	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	sprintf(vc_XMLToGWNGIN,"<field name=\"service\">%s</field>",XML_NGN_SVC_SET_FAVORITE_NUMBERS);
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"InSid\">%d</field>",vc_XMLToGWNGIN, iNrInSid);
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"InUsername\">%s</field>",vc_XMLToGWNGIN,pc_idUser);
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"inOperator\">%s</field>",vc_XMLToGWNGIN,"");
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"inMedia\">%s</field>",vc_XMLToGWNGIN,"");
	
	if( bRegraNova == true )
	{
		sprintf(vc_XMLToGWNGIN,"%s<field name=\"inService\">%s</field>",vc_XMLToGWNGIN,"FAVBOAHORAII");
	}
	else
	{
		sprintf(vc_XMLToGWNGIN,"%s<field name=\"inService\">%s</field>",vc_XMLToGWNGIN,"FAVBOAHORA");
	}
	
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"inCellNumber\">%s</field>",vc_XMLToGWNGIN,inCellNumber);

	if(i_operacao == 1)
	{
		sprintf(vc_XMLToGWNGIN,"%s<field name=\"inNewNbr\">%s</field>",vc_XMLToGWNGIN,pc_inNewNbr);
		sprintf(vc_XMLToGWNGIN,"%s<field name=\"inOldNbr\"></field>",vc_XMLToGWNGIN);
	}
	else
	if(i_operacao == 2)
	{
		sprintf(vc_XMLToGWNGIN,"%s<field name=\"inNewNbr\">%s</field>",vc_XMLToGWNGIN,pc_inNewNbr);
		sprintf(vc_XMLToGWNGIN,"%s<field name=\"inOldNbr\">%s</field>",vc_XMLToGWNGIN,pc_inOldNbr);
	}
	else
	if(i_operacao == 3)
	{
		sprintf(vc_XMLToGWNGIN,"%s<field name=\"inNewNbr\"></field>",vc_XMLToGWNGIN);
		sprintf(vc_XMLToGWNGIN,"%s<field name=\"inOldNbr\">%s</field>",vc_XMLToGWNGIN,pc_inOldNbr);
	}
/*
	if(i_operacao == 1 || i_operacao == 2)
	{
		sprintf(vc_XMLToGWNGIN,"%s<field name=\"inNewNbr\">%s</field>",vc_XMLToGWNGIN,pc_inNewNbr);
	}
	if(i_operacao == 2 || i_operacao == 3)
	{
		sprintf(vc_XMLToGWNGIN,"%s<field name=\"inOldNbr\">%s</field>",vc_XMLToGWNGIN,pc_inOldNbr);
	}
*/
	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);
	int len;
	// Logar um registro na tabela do XML para o NGIN.
	string snrLinha = pc_Linha;
	string sdsMetodo_NGIN = XML_NGN_SVC_SET_FAVORITE_NUMBERS;
	string sdsObservacao = "Enviando ao Serviço NGIN";
	string sdsXML_IN = toGWNGIN.retrieveXML( &len );
	string sdsXML_OUT = " ";
	
	Util::logarChamadaNGIN(snrLinha, sdsMetodo_NGIN, sdsObservacao, sdsXML_IN, sdsXML_OUT );	

	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_SET_FAVORITE_NUMBERS);
	
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);
		
	snrLinha = pc_Linha;
	sdsMetodo_NGIN = XML_NGN_SVC_SET_FAVORITE_NUMBERS;
	sdsObservacao = "Retorno do Serviço NGIN";
	sdsXML_IN = " ";
	try
	{
		sdsXML_OUT = tuxhp->getNodeAsString(fromGWNGIN);
	}
	catch(...)
	{
	}

	Util::logarChamadaNGIN(snrLinha, sdsMetodo_NGIN, sdsObservacao, sdsXML_IN, sdsXML_OUT );			

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body/>
	</response>

*/

	// Objeto para armazenar a resposta
	DOMNode *po_response = NULL;

	// Verifica a tag 'response'
	po_response = tuxhp->walkDOM(fromGWNGIN, XML_NGN_OUT_RESPONSE, 0);
	
	if (po_response == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RESPONSE, EMSG_NGN_OUT_TNE_RESPONSE);
	
	char *cSucceed = tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_RESPONSE, XML_NGN_OUT_RESPONSE_SUCCEED, 0);

	if (Util::cmp(cSucceed,"no"))
	{

		fromGWNGIN	= tuxhp->walkDOM(fromGWNGIN, XML_NGN_OUT_RESPONSE, 0);
	
		char *cErrCode	= tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_CODE, 0);

		if ( Util::cmp(cErrCode, ECD_NGN_CONF_FAVORITOS_NAO_EXISTENTE)  || Util::cmp(cErrCode, ECD_NGN_CUSTO_OPERACAO_NAO_CONFIGURADO) || Util::cmp(cErrCode, ECD_NGN_CUSTO_OPERACAO_NAO_CONFIGURADO))
			throw new TuxBasicSvcException(ECD_CLIENTE_SEM_PLANO_BOA_HORA, EMSG_CLIENTE_SEM_PLANO_BOA_HORA);
		
		else if ( Util::cmp(cErrCode, ECD_NGN_SALDO_INSUFICIENTE) )
			throw new TuxBasicSvcException(ECD_SALDO_INSUFICIENTE_ATIVACAO, EMSG_SALDO_INSUFICIENTE_ATIVACAO);

		else if ( Util::cmp(cErrCode, ECD_NGN_SET_FAVORITOS_SERVICE_NGIN_N_SUBSCRITO) )
			throw new TuxBasicSvcException(ECD_SERVICO_NAO_SUBSCRITO, EMSG__SERVICO_NAO_SUBSCRITO);
			
		else
			this->trataErro(po_response);	//trata erro genérico do NGIN

	}
	

/*	FO -> JAVA

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<FavoritoVO>
			</FavoritoVO>
		</msgBody>
	</msg>
*/
	
	xml_g->createTag(TAG_XML_OUT_FAVORITOS_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
	// Registro de contato
	this->registrarContato(xml_g,false);
	xml_g->closeTag();

}

void PlugInNGIN::setFavoritoPlanosAntigos()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setFavorito</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario></usuario>
			<servico></servico>
			<numeroNovo></numeroNovo>
			<numeroAntigo></nomeroAntigo>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;
	char* pc_idServico = NULL;
	char* pc_inNewNbr = NULL;
	char* pc_inOldNbr = NULL;
	int   iIsValid;


	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if (pc_idUser == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_idUser == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);


	// Trata a TAG 'servico'
	pc_idServico = tuxhp->walkTree(dnode, TAG_XML_IN_SERVICO, 0);

	if (pc_idServico == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_SERVICO, EMSG_TAG_XML_IN_NE_SERVICO);

	if (*pc_idServico == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_SERVICO, EMSG_TAG_XML_IN_VV_SERVICO);
	
	// Trata a TAG 'numeroNovo'
	pc_inNewNbr = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO_NOVO, 0);

	/*
	if (pc_inNewNbr == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_NUMERO_NOVO, EMSG_TAG_XML_IN_NE_NUMERO_NOVO);
	
	if (*pc_inNewNbr == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_NUMERO_NOVO, EMSG_TAG_XML_IN_VV_NUMERO_NOVO);
	*/


	// Trata a TAG 'numeroAntigo'
	pc_inOldNbr = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO_ANTIGO, 0);

	/*
	if (pc_inOldNbr == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_NUMERO_ANTIGO, EMSG_TAG_XML_IN_NE_NUMERO_ANTIGO);
	
	if (! *pc_inOldNbr)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_NUMERO_ANTIGO, EMSG_TAG_XML_IN_VV_NUMERO_ANTIGO);
	*/	


	// Verificação p/ inclusao do numero favorito
	if (strlen(pc_inNewNbr) == 10 || strlen(pc_inNewNbr) == 11)
	{
		if (! (iIsValid = Util::isValidLine(pc_inNewNbr)))
			throw new TuxBasicSvcException(ECD_INVALID_LINE, EMSG_INVALID_LINE_ADD_FAVOR);

		else if(iIsValid < 0)
			throw new TuxBasicSvcException(ECD_DISABLED_LINE, EMSG_DISABLED_LINE_ADD_FAVOR);

	}

	// Verificação p/ alteracao do numero favorito
	if (strlen(pc_inOldNbr) == 10 || strlen(pc_inOldNbr) == 11)
	{
		if (! (iIsValid = Util::isValidLine(pc_inOldNbr)))
			throw new TuxBasicSvcException(ECD_INVALID_LINE, EMSG_INVALID_LINE_ALTER_FAVOR);

		else if(iIsValid < 0)
			throw new TuxBasicSvcException(ECD_DISABLED_LINE, EMSG_DISABLED_LINE_ADD_FAVOR);

	}

/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<msg>	
		<msgHdr>		
			<service>mblChangeFavoriteNbr</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="InSid">Identificação do Serviço</atribute>
			<atribute name="idUser">Usuário</atribute>
			<atribute name="idTrans">Identificador único do pedido</atribute>
			<atribute name="idLinha">Conta Privada no Care</atribute>
			<atribute name="idServico">Código do Serviço</atribute>
			<atribute name="inNewNbr">Novo destino especial</atribute>
			<atribute name="inOldNbr">Destino especial antigo</atribute>
		</msgBody>
	</msg>
*/

	char vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char vc_idTrans[256];
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_SET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;


	// Obtem numero randomico para idTrans
	Util::rand(vc_idTrans);

	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%s</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, vc_InSid, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idUser">idUser_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_USER, pc_idUser, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idTrans">idTrans_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_TRANS, vc_idTrans, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idLinha">idLinha_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_LINHA, pc_idLinha, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idServico">idServico_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_SERVICO, pc_idServico, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inNewNbr">inNewNbr_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_NEW_NR, ((pc_inNewNbr == NULL) || (*pc_inNewNbr == '\0'))? "" : pc_inNewNbr, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inOldNbr">inOldNbr_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_OLD_NR, ((pc_inOldNbr == NULL) || (*pc_inOldNbr == '\0'))? "" : pc_inOldNbr, XML_NGN_IN_ATTRIBUTE);
    
	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_CHANGE_FAVORITE_NR);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="outResponseTime"     value="Tempo de resposta local"/>
			</record>
		</body>
	</response>
*/

	// Objeto para armazenar a resposta
	DOMNode *po_response = NULL;

	// Verifica a tag 'response'
	po_response = tuxhp->walkDOM(fromGWNGIN, XML_NGN_OUT_RESPONSE, 0);
	
	if (po_response == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RESPONSE, EMSG_NGN_OUT_TNE_RESPONSE);
	
	// trata erro genérico do NGIN
	this->trataErro(po_response);

	// Objeto para armazenar o corpo da resposta
	DOMNode *po_body = NULL;

	// Verifica a tag 'body'
	po_body = tuxhp->walkDOM(po_response, XML_NGN_OUT_BODY, 0);

	if (po_body == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_BODY, EMSG_NGN_OUT_TNE_BODY);

/*	FO -> JAVA

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<FavoritoVO>
			</FavoritoVO>
		</msgBody>
	</msg>
*/
	
	xml_g->createTag(TAG_XML_OUT_FAVORITOS_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
	// Registro de contato
	this->registrarContato(xml_g,false);
	xml_g->closeTag();

}

void PlugInNGIN::setFavorito()
{
	//habilitando o controle de memória
	tuxhp->setRelease(true);

/*  30/05/2005.	Desabilitado temporariamente a verificação do Plano e chamada a planos novos, devido a regional RS não possuir essas API´s.
	// validando plano atual*/
	/*this->bOperacaoInterna = true;
	this->getPlano();
	
	if (this->iIdPlano == XML_NGN_VAL_PLANO_DIA || 
		this->iIdPlano == XML_NGN_VAL_PLANO_NOITE || 
		this->iIdPlano == XML_NGN_VAL_PLANO_TODA_HORA)	
		this->setFavoritoRequest();	
	else
		this->setFavoritoPlanosAntigos();
	*/

	if(strcmp(this->getSgSistemaOrigem(),"NSP")==0)
	{
		tuxfw_getlogger()->debug("Chamando a API setFavoritoRequest");
		this->setFavoritoRequest();
	}
	else
	{
		tuxfw_getlogger()->debug("Chamando a API setFavoritoPlanosAntigos");
		this->setFavoritoPlanosAntigos();
	}

	

}