#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;

void PlugInNGIN::getFavoritosPlanosNovos()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getFavorito</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	// Obtem o numero da linha
	char       c_area [3] = "";
	char       c_linha[10] = "";
	char*      pc_Linha   = NULL;

	pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	if(pc_Linha == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_LINHA, EMSG_TAG_XML_IN_NE_LINHA);

	if (*pc_Linha == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_LINHA, EMSG_TAG_XML_IN_VV_LINHA);

	if (strlen(pc_Linha) < 10 || strlen(pc_Linha) > 11 )
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_LINHA, EMSG_TAG_XML_IN_VI_LINHA);

	sprintf(c_area, "%.2s", pc_Linha );
	sprintf(c_linha, "%s", (char*)&pc_Linha[2] );

	tuxfw_getlogger()->debug("DDD: %s", c_area);
	tuxfw_getlogger()->debug("Linha: %s", c_linha);

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

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_GET_FAVORITE);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="VLFAVALT" value="5"/>
				<field name="VLFAVINCL" value="0"/>
				<field name="FAVVIG" value="4191271535|4191919101"/>
				<field name="QUANTCADREST" value="3"/>
				<field name="QUANTALTGRAT" value="0"/>
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
			<FavoritosVO>
				<qtIncRestantes></qtIncRestantes>
				<qtCadRestantes></qtCadRestantes>
				<qtAltGratuitas></qtAltGratuitas>
				<qtLinFavAtivas></qtLinFavAtivas>
				<qtLinFavJaCadastradas></qtLinFavJaCadastradas>
				<indTarifa></indTarifa>
				<valTarifaInclusao></valTarifaInclusao>
				<valTarifaAlteracao></valTarifaAlteracao>
				<qtMaxInclusao></qtMaxInclusao>
				<FavoritosItem>
					 <numero></numero>
					 <servico></servico>
				</FavoritosItem>
			</FavoritosVO>
		</msgBody>
	</msg>
*/

	unsigned int i;

	char    *pc_valAltFavorito = NULL;
	char    *pc_valIncFavorito = NULL;
	char    *pc_favoritos      = NULL;
	char    *pc_qtCadRestantes = NULL;
	char    *pc_qtAltGratuitas = NULL;

	DOMNode *po_record     = NULL;
	char    *pc_field_name = NULL;

	po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, 0);

	for (i = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, i)) != NULL; i++)
	{
		if (Util::cmp(pc_field_name, XML_NGN_FLDNM_VAL_ALT_FAVORITOS))
			pc_valAltFavorito = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

		else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_VAL_INC_FAVORITOS))			
			pc_valIncFavorito = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

		else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_FAVORITOS_VIGENTES))			
			pc_favoritos = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

		else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_QT_CAD_RESTANTES))			
			pc_qtCadRestantes = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

		else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_QT_ALT_GRATUIRAS))			
			pc_qtAltGratuitas = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
	}

	int i_qfMaxFavoritos = Util::getQtFavoritos(pc_Linha);

	xml_g->createTag(TAG_XML_OUT_FAVORITOS_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

		char c_nrLinha[12] = "";
		int  i_strFavLen = strlen(pc_favoritos);

		for(i = 0; i < i_strFavLen; i += 12)
		{
			strncpy(c_nrLinha, &pc_favoritos[i], 11);

			xml_g->createTag(TAG_XML_OUT_FAVORITOS_ITEM);

				// adicionando o número do telefone
				xml_g->addItem(TAG_XML_OUT_NUMERO, c_nrLinha);
				
				// adicionando o tipo de favorito						
				xml_g->addItem(TAG_XML_OUT_SERVICO, "");

			xml_g->closeTag();
		}

		xml_g->addItem(TAG_XML_OUT_QT_INC_RESTANTES, pc_qtCadRestantes);
		xml_g->addItem(TAG_XML_OUT_QT_CAD_RESTANTES, pc_qtCadRestantes);
		xml_g->addItem(TAG_XML_OUT_QT_ALT_GRATUITAS, pc_qtAltGratuitas);
		xml_g->addItem(TAG_XML_OUT_QT_LIN_FAV_ATIVAS, i_qfMaxFavoritos);
		xml_g->addItem(TAG_XML_OUT_QT_LIN_FAV_JA_CADASTRADAS, "");
		xml_g->addItem(TAG_XML_OUT_IND_TARIDA, atof(pc_valIncFavorito) || atof(pc_valAltFavorito) ? "S" : "N");
		xml_g->addItem(TAG_XML_OUT_VAL_TARIFA_INCLUSAO, pc_valIncFavorito);
		xml_g->addItem(TAG_XML_OUT_VAL_TARIFA_ALTERACAO, pc_valAltFavorito);
		xml_g->addItem(TAG_XML_OUT_QT_MAX_INCLUSAO, pc_qtCadRestantes);

	xml_g->closeTag();

}


void PlugInNGIN::getFavoritosRequest()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getFavorito</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	// Obtem o numero da linha
	char       c_area [3] = "";
	char       c_linha[16] = "";
	char*      pc_Linha   = NULL;
	char*      pc_idUser  = NULL;

	tuxfw_getlogger()->debug("Chegou ate aqui primeiro");

	pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);
	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if(pc_Linha == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_LINHA, EMSG_TAG_XML_IN_NE_LINHA);

	if (*pc_Linha == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_LINHA, EMSG_TAG_XML_IN_VV_LINHA);

	if (strlen(pc_Linha) < 10 || strlen(pc_Linha) > 11 )
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_LINHA, EMSG_TAG_XML_IN_VI_LINHA);

	sprintf( c_area, "%.2s", pc_Linha );
	sprintf(c_linha, "%s", (char*)&pc_Linha[2] );

	tuxfw_getlogger()->debug("DDD: %s", c_area);
	tuxfw_getlogger()->debug("Linha: %s", c_linha);

/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<request service="mblGetFavoriteNumbers" sid="Identificacao do serviço" timestamp="2004-01-01">
		<record>
			<field name="inUsername">Usuário</field>
			<field name="inCellNumber">4199865598</field>
			<field name="inMedia"></field>
			<field name="inService">FAMILYFRIENDS</field>
			<field name="inOperador"></field>
		</record>
	</request>

*/

	// Objeto para armazenar a resposta
	DOMNode *po_response = NULL;
	// Objeto para armazenar o corpo da resposta
	DOMNode *po_body = NULL;

	/*
	string sTmpLog;
	sTmpLog = "Chamando a funcao do NGIN [";
	sTmpLog += XML_NGN_SVC_GET_FAVORITE_NUMBERS;
	sTmpLog += "] com parametro inService [FAVBOAHORA] para a linha [";
	sTmpLog += pc_Linha;
	sTmpLog += "]";
	tuxfw_getlogger()->debug((char *)sTmpLog.data());
	*/
	tuxfw_getlogger()->debug("Chamando a funcao do NGIN [%s] com parametro inService [FAVBOAHORA] para a linha [%s]", XML_NGN_SVC_GET_FAVORITE_NUMBERS, pc_Linha);	
	getFavoritosbyService("FAVBOAHORA", &po_response, c_area, c_linha, pc_Linha, pc_idUser);
	
	

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
			<FavoritosVO>
				<qtIncRestantes></qtIncRestantes>
				<qtCadRestantes></qtCadRestantes>
				<qtAltGratuitas></qtAltGratuitas>
				<qtLinFavAtivas></qtLinFavAtivas>
				<qtLinFavJaCadastradas></qtLinFavJaCadastradas>
				<indTarifa></indTarifa>
				<valTarifaInclusao></valTarifaInclusao>
				<valTarifaAlteracao></valTarifaAlteracao>
				<qtMaxInclusao></qtMaxInclusao>
				<FavoritosItem>
					 <numero></numero>
					 <servico></servico>
				</FavoritosItem>
			</FavoritosVO>
		</msgBody>
	</msg>
*/

	unsigned int i;
	unsigned int j;
	unsigned int QuantidadeFavoritos;

	char    *pc_valAltFavorito = NULL;
	char    *pc_valIncFavorito = NULL;
	char    *pc_favoritos      = NULL;
	char    *pc_qtCadRestantes = NULL;
	char    *pc_qtAltGratuitas = NULL;	
	char    *pc_field_name = NULL;
	char    *pc_favorito = NULL;
	char    *pc_outChangesDone = NULL;
	char    *pc_outMaxPrefNbr = NULL;
	DOMNode *po_record     = NULL;	
	bool bFAVBOAHORAII = false;

	xml_g->createTag(TAG_XML_OUT_FAVORITOS_VO);

	char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

	if (pc_xmlns != NULL)
		xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

	// percorre e gera todas as tags de favoritos
	for (j = 0, QuantidadeFavoritos = 0; (po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, j)) != NULL ; j++)
	{
		pc_field_name = NULL;
		for (i = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, i)) != NULL; i++)
		{
			pc_favorito = NULL;
			if (Util::cmp(pc_field_name, "OutFriendNbr"))
			{
				pc_favorito = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
				xml_g->createTag(TAG_XML_OUT_FAVORITOS_ITEM);
				xml_g->addItem(TAG_XML_OUT_NUMERO,pc_favorito);
				xml_g->addItem(TAG_XML_OUT_SERVICO, "");
				xml_g->closeTag();
				QuantidadeFavoritos++;
			}
		}
	}
	tuxfw_getlogger()->debug("Quantidade de Favoritos [%d] para a linha [%s]", QuantidadeFavoritos, pc_Linha);
	
	if ( QuantidadeFavoritos == 0 )
	{
		bFAVBOAHORAII = true;
		po_response = NULL;
		po_body  = NULL;
		po_record = NULL;
		tuxfw_getlogger()->debug("Chamando a funcao do NGIN [%s] com parametro inService [FAVBOAHORAII] para a linha [%s]", XML_NGN_SVC_GET_FAVORITE_NUMBERS, pc_Linha);
		getFavoritosbyService("FAVBOAHORAII", &po_response, c_area, c_linha, pc_Linha, pc_idUser);
		
		// Verifica a tag 'body'
		po_body = tuxhp->walkDOM(po_response, XML_NGN_OUT_BODY, 0);

		if (po_body == NULL)
			throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_BODY, EMSG_NGN_OUT_TNE_BODY);
			
		// percorre e gera todas as tags de favoritos FAVBOAHORAII
		for (j = 0, QuantidadeFavoritos = 0; (po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, j)) != NULL ; j++)
		{
			pc_field_name = NULL;
			for (i = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, i)) != NULL; i++)
			{
				pc_favorito = NULL;
				if (Util::cmp(pc_field_name, "OutFriendNbr"))
				{
					pc_favorito = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
					xml_g->createTag(TAG_XML_OUT_FAVORITOS_ITEM);
					xml_g->addItem(TAG_XML_OUT_NUMERO,pc_favorito);
					xml_g->addItem(TAG_XML_OUT_SERVICO, "");
					xml_g->closeTag();
					QuantidadeFavoritos++;
				}
			}
		}
		tuxfw_getlogger()->debug("Quantidade de Favoritos [%d] para a linha [%s]", QuantidadeFavoritos, pc_Linha);
	}
	
	
	string spc_ddd = c_area;
	string sTempLogValue;
	if (bFAVBOAHORAII == true)
	{
		if ( 1 != Util::VerificarlinhaFavoritaValida(spc_ddd, VALIDA_TABELA_DDD /*1=DDD 2=REGIONAL*/))
		{
			sTempLogValue = "A Linha[" + spc_ddd + c_linha;
			sTempLogValue += "] tem não tem o codigo area cadastrado na tabela de DDDs AUTORIZADOS.";
			tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
			throw new TuxBasicSvcException(ECD_DDD_NAO_CADASTRADO_COMO_AUTORIZADO, EMSG_DDD_NAO_CADASTRADO_COMO_AUTORIZADO);		
		}
	}
	else // FAVBOAHORA
	{
		if ( 1 != Util::VerificarlinhaFavoritaValida(spc_ddd, VALIDA_TABELA_REGIONAL /*1=DDD 2=REGIONAL*/))
		{
			sTempLogValue = "A Linha[" + spc_ddd + c_linha;
			sTempLogValue += "] não tem o codigo area cadastrado na tabela de REGIONAIS AUTORIZADAS";
			tuxfw_getlogger()->debug((char *)(sTempLogValue.data()));
			throw new TuxBasicSvcException(ECD_REGIONAL_NAO_CADASTRADA_COMO_AUTORIZADA, EMSG_REGIONAL_NAO_CADASTRADA_COMO_AUTORIZADA);			
		}
	}
	
	
	// percorre e recupera as informações
	for (j = 0; (po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, j)) != NULL ; j++)
	{
		pc_field_name = NULL;
		for (i = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, i)) != NULL; i++)
		{
			if (Util::cmp(pc_field_name, "OutChangeCost"))
			{
				pc_valAltFavorito = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
				pc_valIncFavorito = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
			}
			else if (Util::cmp(pc_field_name, "OutMaxPrefNbr"))			
				pc_outMaxPrefNbr = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
			else if (Util::cmp(pc_field_name, "OutMaxChanges"))			
				pc_qtAltGratuitas = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
			else if (Util::cmp(pc_field_name, "OutChangesDone"))			
				pc_outChangesDone = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
		}
	}
	
	tuxfw_getlogger()->debug("XML retornado: <OutMaxPrefNbr> = [%s], <OutMaxChanges> = [%s], <OutChangesDone> = [%s]", pc_outMaxPrefNbr , pc_qtAltGratuitas, pc_outChangesDone );

	int i_qfMaxFavoritos = Util::getQtFavoritos(pc_Linha);
	char total[22];
	sprintf(total,"%d",(atoi(pc_outMaxPrefNbr) - QuantidadeFavoritos));

	pc_qtCadRestantes = total;

	xml_g->addItem(TAG_XML_OUT_QT_INC_RESTANTES, pc_qtCadRestantes);
	xml_g->addItem(TAG_XML_OUT_QT_CAD_RESTANTES, pc_qtCadRestantes);
	xml_g->addItem(TAG_XML_OUT_QT_ALT_GRATUITAS, pc_qtAltGratuitas);
	xml_g->addItem(TAG_XML_OUT_QT_LIN_FAV_ATIVAS, i_qfMaxFavoritos);
	xml_g->addItem(TAG_XML_OUT_QT_LIN_FAV_JA_CADASTRADAS, pc_outChangesDone);
	xml_g->addItem(TAG_XML_OUT_IND_TARIDA, atof(pc_valIncFavorito) || atof(pc_valAltFavorito) ? "S" : "N");
	xml_g->addItem(TAG_XML_OUT_VAL_TARIFA_INCLUSAO, pc_valIncFavorito);
	xml_g->addItem(TAG_XML_OUT_VAL_TARIFA_ALTERACAO, pc_valAltFavorito);
	xml_g->addItem(TAG_XML_OUT_QT_MAX_INCLUSAO, pc_outMaxPrefNbr);
	xml_g->closeTag();
}

void PlugInNGIN::getFavoritosPlanosAntigos()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getFavorito</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;

	//Trata a TAG ProxyLinha
	char* pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	if (pc_Linha == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_ID_CONTA_SIS_ORIGEM, EMSG_TAG_XML_IN_NE_ID_CONTA_SIS_ORIGEM);

	if (*pc_Linha == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_ID_CONTA_SIS_ORIGEM, EMSG_TAG_XML_IN_VV_ID_CONTA_SIS_ORIGEM);

	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if (pc_idUser == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_idUser == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);
	
/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<msg>	
		<msgHdr>		
			<service>mblGetFavoriteNbrs</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="InSid">Identificação do Serviço</atribute>
			<atribute name="idUser">Usuário</atribute>
			<atribute name="idTrans">Identificador único do pedido</atribute>
			<atribute name="idLinha">Conta Privada no Care</atribute>
		</msgBody>
	</msg>
*/

	char vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char vc_idTrans[256];
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
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

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_GET_FAVORITE_NRS);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	
	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="outResponseTime" value="Tempo de resposta local"/>
				<field name="outFavoriteNbr"  value="Numero da Linha"/>
				<field name="outIdServico"    value="Código do Serviço"/>
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
			<FavoritosVO>
				<qtIncRestantes></qtIncRestantes>
				<qtCadRestantes></qtCadRestantes>
				<qtAltGratuitas></qtAltGratuitas>
				<qtLinFavAtivas></qtLinFavAtivas>
				<qtLinFavJaCadastradas></qtLinFavJaCadastradas>
				<indTarifa></indTarifa>
				<valTarifaInclusao></valTarifaInclusao>
				<valTarifaAlteracao></valTarifaAlteracao>
				<qtMaxInclusao></qtMaxInclusao>
				<FavoritosItem>
					 <numero>Valor de outFavoriteNbr</numero>
					<servico>Valor de outIdServico</servico>
				</FavoritosItem>
			</FavoritosVO>
		</msgBody>
	</msg>
*/

	int   i_qfMaxFavoritos    = 0;
	int   i_qtFavCadastrados  = 0;

	xml_g->createTag(TAG_XML_OUT_FAVORITOS_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

		DOMNode *po_record = NULL;

		for (unsigned int i = 0; (po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, i)) != NULL; i++)
		{
			char* pc_responseTM = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, 0);

			if (Util::cmp(pc_responseTM, XML_NGN_FLDNM_RESPONSE_TM))
				continue;

			char *pc_field_name = NULL;
			
			char* pc_outFavoriteNbr;

			for (unsigned int j = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, j)) != NULL; j++)
			{
				if (Util::cmp(pc_field_name, XML_NGN_FLDNM_FAVORITE_NR))
				{
					pc_outFavoriteNbr = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
				}
				else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_ID_SERVICO))			
				{					
					char* pc_outIdServico = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

					if(Util::cmp(pc_outIdServico, XML_NGN_VAL_FAVORNOVPLN))
					{
						// somente se tipo FAVORNOVPLN
						xml_g->createTag(TAG_XML_OUT_FAVORITOS_ITEM);

						// adicionando o número do telefone
						xml_g->addItem(TAG_XML_OUT_NUMERO, (pc_outFavoriteNbr != NULL)? pc_outFavoriteNbr : "");
					
						// adicionando o tipo de favorito						
						xml_g->addItem(TAG_XML_OUT_SERVICO, (pc_outIdServico != NULL)? pc_outIdServico : "");

						xml_g->closeTag();

						i_qtFavCadastrados ++;

					}
					
				}

			}
			
		}
		
		i_qfMaxFavoritos   = Util::getQtFavoritos(pc_Linha);

		xml_g->addItem(TAG_XML_OUT_QT_INC_RESTANTES, i_qfMaxFavoritos - i_qtFavCadastrados);
		xml_g->addItem(TAG_XML_OUT_QT_CAD_RESTANTES, i_qfMaxFavoritos - i_qtFavCadastrados);
		xml_g->addItem(TAG_XML_OUT_QT_ALT_GRATUITAS, "");
		xml_g->addItem(TAG_XML_OUT_QT_LIN_FAV_ATIVAS, i_qfMaxFavoritos);
		xml_g->addItem(TAG_XML_OUT_QT_LIN_FAV_JA_CADASTRADAS, "");
		xml_g->addItem(TAG_XML_OUT_IND_TARIDA, "");
		xml_g->addItem(TAG_XML_OUT_VAL_TARIFA_INCLUSAO, "");
		xml_g->addItem(TAG_XML_OUT_VAL_TARIFA_ALTERACAO, "");
		xml_g->addItem(TAG_XML_OUT_QT_MAX_INCLUSAO, i_qfMaxFavoritos - i_qtFavCadastrados);

	xml_g->closeTag();

}

void PlugInNGIN::getFavoritos()
{
	//habilitando o controle de memória
	tuxhp->setRelease(true);


/*  30/05/2005.	Desabilitado temporariamente a verificação do Plano e chamada a planos novos, devido a regional RS não possuir essas API´s.
*/
	// validando plano atual
	/*
	this->bOperacaoInterna = true;
	this->getPlano();	
	if (this->iIdPlano == XML_NGN_VAL_PLANO_DIA || 
		this->iIdPlano == XML_NGN_VAL_PLANO_NOITE || 
		this->iIdPlano == XML_NGN_VAL_PLANO_TODA_HORA)	
		this->getFavoritosRequest();	//sao paulo	=>mblGetFavoriteNumbers : FAVBOAHORA
	else
		this->getFavoritosPlanosAntigos(); //pr/sc : mblGetFavoriteNbrs: FAMILYFRIENDS
		*/

	if(strcmp(this->getSgSistemaOrigem(),"NSP")==0)
	{
		tuxfw_getlogger()->debug("Chamando a API getFavoritosRequest");
		this->getFavoritosRequest();
	}
	else
	{
		tuxfw_getlogger()->debug("Chamando a API getFavoritosPlanosAntigos");
		this->getFavoritosPlanosAntigos();
	}

	// Registro de contato
	this->registrarContato();

}

void PlugInNGIN::getFavoritosbyService(const char* pcServiceName, DOMNode **po_response, char *c_area, char *c_linha, char* pc_Linha, char* pc_idUser)
{
/*
	char       c_area [3] = "";
	char       c_linha[9] = "";
	char*      pc_Linha   = NULL;
	char*      pc_idUser  = NULL;
*/

	char     vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char     vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	char     timeStamp[20];
	char     inCellNumber[12];
	XMLGen   toGWNGIN;
	DOMNode *fromGWNGIN = NULL;
	CParametro oParametro;
	//Util::DtAtualStamp(timeStamp, "RRRR-MM-DD");
	sprintf(inCellNumber,"%s%s",c_area,c_linha);
	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_GET_FAVORITE_NUMBERS);

	sprintf(vc_XMLToGWNGIN,"<field name=\"service\">%s</field>",XML_NGN_SVC_GET_FAVORITE_NUMBERS);
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"InSid\">%d</field>",vc_XMLToGWNGIN,iNrInSid);
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"InUsername\">%s</field>",vc_XMLToGWNGIN,pc_idUser);
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"inCellNumber\">%s</field>",vc_XMLToGWNGIN,inCellNumber);
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"inMedia\">%s</field>",vc_XMLToGWNGIN,"");
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"inService\">%s</field>",vc_XMLToGWNGIN, pcServiceName);
	sprintf(vc_XMLToGWNGIN,"%s<field name=\"inOperador\">%s</field>",vc_XMLToGWNGIN,"");
	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	
	int len;
	// Logar um registro na tabela do XML para o NGIN.
	string snrLinha = pc_Linha;
	string sdsMetodo_NGIN = XML_NGN_SVC_GET_FAVORITE_NUMBERS;
	string sdsObservacao = "Enviando ao Serviço NGIN";
	string sdsXML_IN = toGWNGIN.retrieveXML( &len );
	string sdsXML_OUT = " ";
	
	
	Util::logarChamadaNGIN(snrLinha, sdsMetodo_NGIN, sdsObservacao, sdsXML_IN, sdsXML_OUT );	
	
	// Chama o serviço e verifica o retorno
	tuxfw_getlogger()->debug("Antes da chamada callRemoteAPI()");
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_GET_FAVORITE_NUMBERS);
	tuxfw_getlogger()->debug("Depois da chamada callRemoteAPI()");

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);
		
	snrLinha = pc_Linha;
	sdsMetodo_NGIN = XML_NGN_SVC_GET_FAVORITE_NUMBERS;
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

	<?xml version="1.0" encoding="UTF-8" ?>
	<response succeed="yes">
		<body>
			<record>
				<field name=“OutFriendNbr” value=“4399112244”/>
			</record>
			<record>
				<field name=“OutFriendNbr” value=“4399112245”/>
			</record>
			<record>
				<field name=“OutFriendNbr” value=“4399112246”/>
			</record>
			<record>
				<field name=“OutFreeChangePeriod” value=“20020531”/>
				<field name=“OutChangesDone” value=“8”/>
				<field name=“OutMaxChanges” value=“3”/>
				<field name=“OutMaxPrefNbr” value=“4”/>
				<field name=“OutChangeCost” value=“1.5”/>
			</record>
		</body>
	</response>

*/

	// Verifica a tag 'response'
	*po_response = tuxhp->walkDOM(fromGWNGIN, XML_NGN_OUT_RESPONSE, 0);

	if (*po_response == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RESPONSE, EMSG_NGN_OUT_TNE_RESPONSE);

	char *cSucceed = tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_RESPONSE, XML_NGN_OUT_RESPONSE_SUCCEED, 0);

	if (Util::cmp(cSucceed,"no"))
	{

		fromGWNGIN	= tuxhp->walkDOM(fromGWNGIN,  XML_NGN_OUT_RESPONSE, 0);
	
		char *cErrCode	= tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_CODE, 0);

		if ( Util::cmp(cErrCode, ECD_NGN_CONF_FAVORITOS_NAO_EXISTENTE)  || Util::cmp(cErrCode, ECD_NGN_CUSTO_OPERACAO_NAO_CONFIGURADO) || Util::cmp(cErrCode, ECD_NGN_SERVICO_NAO_SUBSCRITO) )						
			throw new TuxBasicSvcException(ECD_CLIENTE_SEM_PLANO_BOA_HORA, EMSG_CLIENTE_SEM_PLANO_BOA_HORA);
		
		else if ( Util::cmp(cErrCode, ECD_NGN_SALDO_INSUFICIENTE) )
			throw new TuxBasicSvcException(ECD_SALDO_INSUFICIENTE_ATIVACAO, EMSG_SALDO_INSUFICIENTE_ATIVACAO);

		else
			this->trataErro(*po_response);	//trata erro genérico do NGIN

	}
}
