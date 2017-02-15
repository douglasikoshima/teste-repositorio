#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;
 

void PlugInNGIN::getExtrato()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getExtrato</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<Periodo></Periodo>
			<dataIni>Data do Inicio</dataIni>
			<dataFim>Data do Fim</dataFim>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	

	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;
	char* pc_periodo =  NULL;
	char* pc_inStartDate = NULL;
	char* pc_inEndDate = NULL;
	bool byPeriodo = false;
	char* pc_idCanal = NULL;


	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if (pc_idUser == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_idUser == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);

	pc_periodo = tuxhp->walkTree(dnode, TAG_XML_IN_PERIODO, 0);

	if (pc_periodo == NULL)
	{
		byPeriodo = false;
		// Trata a TAG 'dataIni'
		pc_inStartDate = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_INI, 0);

		if (pc_inStartDate == NULL)
		{
			XMLString::release(&pc_idUser);
			
			throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_INI, EMSG_TAG_XML_IN_NE_DATA_INI);
		}

		if (*pc_inStartDate == '\0')
		{
			XMLString::release(&pc_idUser);

			throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_INI, EMSG_TAG_XML_IN_VV_DATA_INI);
		}


		// Verifica a TAG 'dataFim'
		pc_inEndDate = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_FIM, 0);

		if (pc_inEndDate == NULL)
		{
			XMLString::release(&pc_idUser);
			XMLString::release(&pc_inStartDate);
			
			throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_FIM, EMSG_TAG_XML_IN_NE_DATA_FIM);
		}

		if (*pc_inEndDate == '\0')
		{
			XMLString::release(&pc_idUser);
			XMLString::release(&pc_inStartDate);

			throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_FIM, EMSG_TAG_XML_IN_VV_DATA_FIM);
		}
	}
	else
	{
		byPeriodo = true;
		if (*pc_periodo == '\0')
			throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_PERIODO, EMSG_TAG_XML_IN_VV_PERIODO);
	}
		
/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<msg>	
		<msgHdr>		
			<service>mblGetDetailedReport</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="InSid">Identificação do Serviço</atribute>
			<atribute name="idUser">Usuário</atribute>
			<atribute name="idTrans">Identificador único do pedido</atribute>
			<atribute name="idLinha">Conta Privada no Care</atribute>
			<atribute name="inStartDate">Data Inicial</atribute>
			<atribute name="inEndDate">Data Final</atribute>
		</msgBody>
	</msg>
*/

	char vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char vc_idTrans[256];
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;	
	char vc_date[32];
	char chrDtInicio[12];
	char chrDtFim[12];
	int	 intPeriodo;
	CParametro oParametro;
	
	if(byPeriodo)
	{
		intPeriodo = atoi(pc_periodo);	

		Util::DtAtual(chrDtFim,"dd/mm/yyyy");

		Util::SubDiasData(chrDtFim, chrDtInicio, intPeriodo*30, "dd/mm/yyyy");
	}


	pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	char* pc_Linha   =   tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_GET_DETAIL_REPORT);

	// Obtem numero randomico para idTrans
	Util::rand(vc_idTrans);

	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idUser">idUser_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_USER, pc_idUser, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idTrans">idTrans_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_TRANS, vc_idTrans, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idLinha">idLinha_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_LINHA, pc_idLinha, XML_NGN_IN_ATTRIBUTE);

	if(byPeriodo)
	{
		// TAG '<attribute name="inStartDate">inStartDate_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_START_DT, chrDtInicio, XML_NGN_IN_ATTRIBUTE);

		// TAG '<attribute name="inEndDate">inEndDate_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_END_DT, chrDtFim, XML_NGN_IN_ATTRIBUTE);
	}
	else
	{
		// TAG '<attribute name="inStartDate">inStartDate_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_START_DT, Util::formateDate(vc_date, pc_inStartDate, DATE_FORMAT_JAVA, DATE_FORMAT_NGN_IN), XML_NGN_IN_ATTRIBUTE);

		// TAG '<attribute name="inEndDate">inEndDate_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_END_DT, Util::formateDate(vc_date, pc_inEndDate, DATE_FORMAT_JAVA, DATE_FORMAT_NGN_IN), XML_NGN_IN_ATTRIBUTE);		
	}
	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	XMLString::release(&pc_idUser);	
	XMLString::release(&pc_inStartDate);
	XMLString::release(&pc_inEndDate);

	/* Chama o serviço e verifica o retorno*/
	fromGWNGIN = callRemoteAPI(this->getServiceNameExt(), &toGWNGIN, XML_NGN_SVC_GET_DETAIL_REPORT);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	
	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);


	
	char *cSucceed = tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_RESPONSE, XML_NGN_OUT_RESPONSE_SUCCEED, 0);

	if (Util::cmp(cSucceed,"no"))
	{

		fromGWNGIN	= tuxhp->walkDOM(fromGWNGIN,  XML_NGN_OUT_RESPONSE, 0);
	
		char *cErrCode	= tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_CODE, 0);


		if (Util::cmp(cErrCode, ECD_NGN_PARAMETROS_INVALIDOS_EXTR)) 
			throw new TuxBasicSvcException(ECD_RECARGA_INDISPONIVEL_VISUALIZACAO, EMSG_RECARGA_INDISPONIVEL_VISUALIZACAO);

	
	}




/*	GWNGIN -> FO (exemplo)

	<?xml version="1.0" encoding="ISO-8859-1" ?>
	<EXTRACTO_DETALHADO>
		<CABECALHO>
			<INFO>
				<EMISSAO valor="11/02/2004" />
				<PERIODO valor="11/11/2003 A 11/02/2004" />
				<CELULAR valor="41-9112-3453" />
				<CONTA valor="1003" />
				<PERFIL valor="Livre" />
			</INFO>
			<MORADA>
				<LINHA>Av. Higienópolis 1365</LINHA>
				<LINHA>86015-010 - PR - Londrina</LINHA>
				<LINHA>AlôGlobal 0800-441000 ou *48 Send</LINHA>
				<LINHA>aloglobal@globaltelecom.com.br</LINHA>
				<LINHA>www.globaltelecom.com.br</LINHA>
				<LINHA>IE: 9015982664 - CNPJ: 02.449.992/0001-64</LINHA>
			</MORADA>
			<IMG_ESQ src="http://10.112.136.115//images/logo_gt.gif" />
			<IMG_DIR src="http://10.112.136.115/142_Logo Baby.jpg" />
		</CABECALHO>
		<TABELA id="PRSC_FORA" descricao="Detalhe das chamadas originadas fora do PR/SC">
			<CAMPOS>
				<COLUNA nome="Item" />
				<COLUNA nome="Data e Hora" />
				<COLUNA nome="Destino" />
				<COLUNA nome="Nº Destino" />
				<COLUNA nome="Tipo Saldo" />
				<COLUNA nome="Tipo Cham." />
				<COLUNA nome="Período" />
				<COLUNA nome="Duração Cobr." />
				<COLUNA nome="Valor Cham." />
				<COLUNA nome="Adicional" />
				<COLUNA nome="Valor Total" />
			</CAMPOS>
			<REGISTO>
				<COLUNA valor="1" />
				<COLUNA valor="20-06-2004 20:12:49" />
				<COLUNA valor="41" />
				<COLUNA valor="71" />
				<COLUNA valor="713865667" />
				<COLUNA valor="NOR" />
				<COLUNA valor="VC3" />
				<COLUNA valor="TR" />
				<COLUNA valor="00:30" />
				<COLUNA valor="1,01" />
			</REGISTO>
		</TABELA>
	</EXTRACTO_DETALHADO>
*/

/*	FO -> JAVA

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ExtratoVO>
				<buffer></buffer>
				<backEnd>NGIN<backEnd>
			</ExtratoVO>
		</msgBody>
	</msg>
*/

	xml_g->createTag(TAG_XML_OUT_EXTRATO_VO);

	char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

	if (pc_xmlns != NULL)
	{
		xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

		XMLString::release(&pc_xmlns);
	}
	
		xml_g->createTag(TAG_XML_OUT_BUFFER);

			xml_g->createTag(TAG_XML_OUT_EXTRACTO_DETALHADO);
			
				char* pc_xmlnsfo = tuxhp->walkAttr(fromGWNGIN,TAG_XML_OUT_EXTRACTO_DETALHADO,"xmlns:fo", 0);

				if (pc_xmlnsfo != NULL)
				{
					xml_g->addProp("xmlns:fo", pc_xmlnsfo);

					XMLString::release(&pc_xmlns);
				}
		
				DOMNode *auxDOMRegistro;
				char chrDataAnterior[11];
				int i = 0;
 
				xml_g->createTag(TAG_XML_OUT_CABECALHO);

					xml_g->createTag(TAG_XML_OUT_INFO);	
						auxDOMRegistro = tuxhp->walkDOM(fromGWNGIN, TAG_XML_OUT_INFO, 0);			
						xml_g->addItem(TAG_XML_OUT_EMISSAO, tuxhp->walkTree(auxDOMRegistro, TAG_XML_OUT_EMISSAO, 0));
						xml_g->addItem(TAG_XML_OUT_PERIODO, tuxhp->walkTree(auxDOMRegistro, TAG_XML_OUT_PERIODO, 0));
						xml_g->addItem(TAG_XML_OUT_CEL,     tuxhp->walkTree(auxDOMRegistro, TAG_XML_OUT_CEL, 0));
						xml_g->addItem(TAG_XML_OUT_CONTA,   tuxhp->walkTree(auxDOMRegistro, TAG_XML_OUT_CONTA, 0));
						xml_g->addItem(TAG_XML_OUT_PERFIL,  tuxhp->walkTree(auxDOMRegistro, TAG_XML_OUT_PERFIL, 0));
					xml_g->closeTag(); 

					DOMNode *dnodeRegistro = NULL;
					xml_g->createTag(TAG_XML_OUT_MORADA);
						
						i =	0; 
						while ((auxDOMRegistro = tuxhp->walkDOM(fromGWNGIN,TAG_XML_OUT_LINHA, i)) != NULL)	
							xml_g->addItem(TAG_XML_OUT_LINHA, tuxhp->walkTree(auxDOMRegistro,TAG_XML_OUT_LINHA,i++));
    
					xml_g->closeTag(); 


					xml_g->createTag(TAG_XML_OUT_IMG_ESQ);
	
						char *pc_imgesq = tuxhp->walkAttr(fromGWNGIN, TAG_XML_OUT_IMG_ESQ, "src", 0);

						if (pc_imgesq != NULL)
						{
							xml_g->addProp("src", pc_imgesq);
							XMLString::release(&pc_imgesq);
						}
					
					xml_g->closeTag(); 

					xml_g->createTag(TAG_XML_OUT_IMG_DIR);
	
						char *pc_imgdir = tuxhp->walkAttr(fromGWNGIN, TAG_XML_OUT_IMG_DIR, "src", 0);

						if (pc_imgdir != NULL)
						{
							xml_g->addProp("src", pc_imgdir);
							XMLString::release(&pc_imgdir);
						}
					xml_g->closeTag(); 				
					
				xml_g->closeTag(); 

				xml_g->createTag(TAG_XML_OUT_TABELA);
				
					i = 0;    
					dnodeRegistro = NULL;

					char *chrDataExp;
					while((dnodeRegistro=tuxhp->walkDOM(fromGWNGIN, TAG_XML_OUT_REGISTRO,i))!=NULL){
			
						xml_g->createTag(TAG_XML_OUT_REGISTRO);

							xml_g->addItem(TAG_XML_OUT_ID, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_ID, 0));
							xml_g->addItem(TAG_XML_OUT_ITEM, tuxhp->walkTree(dnodeRegistro, TAG_XML_OUT_ITEM, 0));	
							xml_g->addItem("ORIGEM", tuxhp->walkTree(dnodeRegistro, "ORIGEM", 0));	
							xml_g->addItem(TAG_XML_OUT_DATAHORA, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_DATAHORA, 0));
							xml_g->addItem(TAG_XML_OUT_MOTIVO, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_MOTIVO, 0));
							xml_g->addItem(TAG_XML_OUT_OPERACAO, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_OPERACAO, 0));
							xml_g->addItem(TAG_XML_OUT_DESCR, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_DESCR, 0));
							xml_g->addItem("VALOR", tuxhp->walkTree(dnodeRegistro,"VALOR", 0));											
							xml_g->addItem(TAG_XML_OUT_CDTIPOAJUSTE, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_CDTIPOAJUSTE, 0));					
							xml_g->addItem(TAG_XML_OUT_TIPOAJUSTE, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_TIPOAJUSTE, 0));					
							chrDataExp = tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_DTEXP, 0);					

							if (chrDataExp != NULL){

								if (strlen(chrDataExp) < 8) //algum caractere que nao significa data
									xml_g->addItem(TAG_XML_OUT_DTEXP, chrDataExp); 

								else { //se veio alguma data 
								
									Util::SubDiasData(chrDataExp, chrDataAnterior, 1, "dd-mm-yyyy");
								
									if (strlen(chrDataAnterior) != 0)
										xml_g->addItem(TAG_XML_OUT_DTEXP, chrDataAnterior);
									else
										xml_g->addItem(TAG_XML_OUT_DTEXP, chrDataExp); //Mantém  data invalida retornada pelo ngin

								}
							}
							xml_g->addItem(TAG_XML_OUT_CDTIPOBONUS, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_CDTIPOBONUS, 0));
							xml_g->addItem(TAG_XML_OUT_TIPOBONUS, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_TIPOBONUS, 0));
							xml_g->addItem(TAG_XML_OUT_VLBONUS, tuxhp->walkTree(dnodeRegistro, TAG_XML_OUT_VLBONUS, 0));
							xml_g->addItem(TAG_XML_OUT_TOTALRECARGAS, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_TOTALRECARGAS, 0));					
							xml_g->addItem(TAG_XML_OUT_CDTIPOSALDO, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_CDTIPOSALDO, 0));					
							xml_g->addItem(TAG_XML_OUT_TIPOSALDO, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_TIPOSALDO, 0));							
							xml_g->addItem(TAG_XML_OUT_DEBITOS, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_DEBITOS, 0));
							xml_g->addItem(TAG_XML_OUT_CREDITOS, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_CREDITOS, 0));        
							xml_g->addItem(TAG_XML_OUT_SALDOATUAL, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_SALDOATUAL, 0));
							xml_g->addItem(TAG_XML_OUT_NRDESTINO, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_NRDESTINO, 0));
							xml_g->addItem(TAG_XML_OUT_TIPOCHAMADA, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_TIPOCHAMADA, 0));
							xml_g->addItem(TAG_XML_OUT_DURACAO, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_DURACAO, 0));
							xml_g->addItem("DESTINO", tuxhp->walkTree(dnodeRegistro,"DESTINO", 0));
							xml_g->addItem(TAG_XML_OUT_TOTAL, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_TOTAL, 0));
							xml_g->addItem(TAG_XML_OUT_PERIODO, tuxhp->walkTree(dnodeRegistro,TAG_XML_OUT_PERIODO, 0));
							// adicionado para tratar descrição de SMS
							xml_g->addItem("TIPOSMS", tuxhp->walkTree(dnodeRegistro,"TIPOSMS", 0));

							i++;
							
						xml_g->closeTag();
					       
					}

			xml_g->closeTag(); 
    
			i = 0;
			xml_g->createTag(TAG_XML_OUT_MENSAGENS);			
				
				while ((auxDOMRegistro = tuxhp->walkDOM(fromGWNGIN,TAG_XML_OUT_MENSAGEM, i)) != NULL)	
					xml_g->addItem(TAG_XML_OUT_MENSAGEM, tuxhp->walkTree(auxDOMRegistro,TAG_XML_OUT_MENSAGEM,i++));				

			xml_g->closeTag();

		xml_g->closeTag();
	
	xml_g->closeTag();
		
	xml_g->addItem(TAG_XML_OUT_BACKEND, "NGIN");

	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();
 

}
