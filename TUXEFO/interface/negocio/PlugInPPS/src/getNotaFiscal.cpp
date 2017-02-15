#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::getNotaFiscal()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyLinha>1171010160</ProxyLinha>
			<ProxyOperacao>getNotaFiscal</ProxyOperacao> 
			<notaFiscal></notaFiscal>
			<data></data>
			<dataPedido></dataPedido>
			<classe></classe>
		</msgBody>
	</msg>
*/

	char* pc_CELULAR = NULL;
	char* pc_NOTAFISCAL = NULL;
	char* pc_DATA = NULL;
	char* pc_DATAPEDIDO = NULL;
	char* pc_CLASSE_MOVIMENTO = NULL;

	// A verificacao ja foi realizada
	pc_CELULAR = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	
	// Trata a tag NOTAFISCAL
	pc_NOTAFISCAL = tuxhp->walkTree(dnode, TAG_XML_IN_NOTA_FISCAL, 0);

	if (pc_NOTAFISCAL == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_NOTA_FISCAL, EMSG_TAG_XML_IN_NE_NOTA_FISCAL);
	}

	if (*pc_NOTAFISCAL == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_NOTA_FISCAL, EMSG_TAG_XML_IN_VV_NOTA_FISCAL);
	}


	// Trata a tag DATA
	pc_DATA = tuxhp->walkTree(dnode, TAG_XML_IN_DATA, 0);

	if (pc_DATA == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_NOTAFISCAL);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA, EMSG_TAG_XML_IN_NE_DATA);
	}

	if (*pc_DATA == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_NOTAFISCAL);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA, EMSG_TAG_XML_IN_VV_DATA);
	}


	// Trata a tag DATAPEDIDO
	pc_DATAPEDIDO = tuxhp->walkTree(dnode, TAG_XML_IN_DATAPEDIDO, 0);

	if (pc_DATAPEDIDO == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_NOTAFISCAL);
		XMLString::release(&pc_DATA);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATAPEDIDO, EMSG_TAG_XML_IN_NE_DATAPEDIDO);
	}

	if (*pc_DATAPEDIDO == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_NOTAFISCAL);
		XMLString::release(&pc_DATA);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATAPEDIDO, EMSG_TAG_XML_IN_VV_DATAPEDIDO);
	}


	// Trata a tag CLASSE_MOVIMENTO
	pc_CLASSE_MOVIMENTO = tuxhp->walkTree(dnode, TAG_XML_IN_CLASSE, 0);

	if (pc_CLASSE_MOVIMENTO == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_NOTAFISCAL);
		XMLString::release(&pc_DATA);
		XMLString::release(&pc_DATAPEDIDO);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_CLASSE, EMSG_TAG_XML_IN_NE_CLASSE);
	}

	if (*pc_CLASSE_MOVIMENTO == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_NOTAFISCAL);
		XMLString::release(&pc_DATA);
		XMLString::release(&pc_DATAPEDIDO);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_CLASSE, EMSG_TAG_XML_IN_VV_CLASSE);
	}

/*	FO -> GW

	<ROOT>
		<TRANSACAO>1006</TRANSACAO>
		<CELULAR>1171010160</CELULAR>
		<NOTAFISCAL>10001001</NOTAFISCAL>
		<DATA>yyyy-mm-dd hh:mm:ss</DATA>
		<DATAPEDIDO></DATAPEDIDO>
		<CLASSE_MOVIMENTO></CLASSE_MOVIMENTO>
	</ROOT>
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_GET_NOTA_FISCAL;
	XMLGen o_XMLtoGW;
	DOMNode* po_XMLfromGW = NULL;


    o_XMLtoGW.createTag(TAG_XML_PPS_IN_ROOT);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TRANS, vc_TRANSACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CELULAR, pc_CELULAR);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_NOTAFISCAL, pc_NOTAFISCAL);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DATA, pc_DATA);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DATAPEDIDO, pc_DATAPEDIDO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CLASSE_MOVIMENTO, pc_CLASSE_MOVIMENTO);
	o_XMLtoGW.closeTag();


	if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

	XMLString::release(&pc_NOTAFISCAL);
	XMLString::release(&pc_DATA);
	XMLString::release(&pc_DATAPEDIDO);
	XMLString::release(&pc_CLASSE_MOVIMENTO);

	
	// Faz a chamada remota e guarda o xml de retorno
	po_XMLfromGW = this->callRemoteAPI(SERVICE_PPS, &o_XMLtoGW, NULL);

	tuxfw_getlogger()->debug("PlugInPPS::getNotaFiscal::callRemoteAPI()");

	// Verifica o retorno
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	tuxfw_getlogger()->debug("PlugInPPS::getNotaFiscal::callRemoteAPI() - VERR 1");

	if (po_XMLfromGW == NULL)
		throw new TuxBasicSvcException(ECD_PPS_GW_NOT_RESPOND, EMSG_PPS_GW_NOT_RESPOND);

	tuxfw_getlogger()->debug("PlugInPPS::getNotaFiscal::callRemoteAPI() - VERR 2");


/*	GW -> FO

	<ROOT>
		<NF>10001001</NF>
		<SERIE>4</SERIE>
		<DTEMI>yyyy-mm-dd</DTEMI>
		<CFOP>XXXXXXXXXXX</CFOP>
		<NOME>FULANO</NOME>
		<END>RUA XPTO</END>
		<CEP>00000000</CEP>
		<MUNIC>SAO PAULO</MUNIC>
		<UF>SP</UF>
		<CELULAR>11-7101-0160</CELULAR>
		<LOCAL>SAO PAULO</LOCAL>
		<QUITACAO>Pagamento de Ap</QUITACAO>
		<VQUITACAO>R$ 10.00</VQUITACAO>
		<BASE_ICMS>R$ 100.00</BASE_ICMS>
		<ICMS>R$ 25.00</ICMS>
		<PER_ICMS>25%</PER_ICMS>
		<BASE_FUST>R$ 100.00</BASE_FUST>
		<FUST>R$ 0.001</FUST>
		<BASE_FUNTEL>R$ 100.00</BASE_FUNTEL>
		<FUNTEL>R$ 0.001</FUNTEL>
		<SERV1>Recarga de Celular</SERV1>
		<VSERV1>R$ 100.00</VSERV1>
		<TNOTA>R$ 100.00</TNOTA>
		<VPAGO>R$ 100.00</VPAGO>
		<HASHCODE>9a24.03q4.qw41.09e1.</HASHCODE>
		<IE>123.123.123.123</IE>
		<CPF>123.123.123.123</CPF>
		<CNPJ></CNPJ>
		<ERR>0</ERR>
		<ERR_MENS></ERR_MENS>
	</ROOT>
*/

	// Variavel para armazenar o DOM de 'ROOT'
	DOMNode* po_ROOT = NULL;

	// Verifica a tag ROOT
	po_ROOT = tuxhp->walkDOM(po_XMLfromGW, TAG_XML_PPS_OUT_ROOT, 0);

	if (po_ROOT == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_PPS_OUT_NE_ROOT, EMSG_TAG_XML_PPS_OUT_NE_ROOT);

	// trata erro genérico do PPS
	this->trataErro(po_ROOT);

/*	FO -> JAVA

notaFiscal	
notaSerie	
dataEmissao	
CFOP	
nomeCliente	
endCliente	
cepCliente	
municCliente	
ufCliente	
celular	
local	
quitacao	
vlQuitacao	
baseICMS	
ICMS	
perICMS	
baseFUST	
FUST	
baseFUNTEL	
FUNTEL	
servico
vlServico
totalNota
vlPago
hashcode
inscEstadual
CPF
CNPJ
*/

	xml_g->createTag(TAG_XML_OUT_NOTA_FISCAL_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}


		char* pc_notaFiscal   = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_NF, 0);
		char* pc_notaSerie    = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_SERIE, 0);
		char* pc_dataEmissao  = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_DTEMI, 0);
		char* pc_CFOP         = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_CFOP, 0);
		char* pc_nomeCliente  = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_NOME, 0);
		char* pc_endCliente   = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_END, 0);
		char* pc_cepCliente   = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_CEP, 0);
		char* pc_municCliente = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_MUNIC, 0);
		char* pc_ufCliente    = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_UF, 0);
		char* pc_celular      = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_CELULAR, 0);
		char* pc_local        = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_LOCAL, 0);
		char* pc_quitacao     = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_QUITACAO, 0);
		char* pc_vlQuitacao   = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_VQUITACAO, 0);
		char* pc_baseICMS     = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_BASE_ICMS, 0);
		char* pc_ICMS         = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_ICMS, 0);
		char* pc_perICMS      = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_PER_ICMS, 0);
		char* pc_baseFUST     = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_BASE_FUST, 0);
		char* pc_FUST         = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_FUST, 0);
		char* pc_baseFUNTEL   = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_BASE_FUNTEL, 0);
		char* pc_FUNTEL       = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_FUNTEL, 0);
		char* pc_servico      = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_SERV1, 0);
		char* pc_vlServico    = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_VSERV1, 0);
		char* pc_totalNota    = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_TNOTA, 0);
		char* pc_vlPago       = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_VPAGO, 0);
		char* pc_hashcode     = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_HASHCODE, 0);
		char* pc_inscEstadual = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_IE, 0);
		char* pc_CPF          = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_CPF, 0);
		char* pc_CNPJ         = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_CNPJ, 0);


		xml_g->addItem(TAG_XML_OUT_NOTA_FISCAL, (pc_notaFiscal != NULL)? pc_notaFiscal : "");

		xml_g->addItem(TAG_XML_OUT_NOTA_SERIE, (pc_notaSerie != NULL)? pc_notaSerie : "");	

		xml_g->addItem(TAG_XML_OUT_DATA_EMISSAO, (pc_dataEmissao != NULL)? pc_dataEmissao : "");

		xml_g->addItem(TAG_XML_OUT_CFOP, (pc_CFOP != NULL)? pc_CFOP : "");

		xml_g->addItem(TAG_XML_OUT_NOME_CLIENTE, (pc_nomeCliente != NULL)? pc_nomeCliente : "");

		xml_g->addItem(TAG_XML_OUT_END_CLIENTE, (pc_endCliente != NULL)? pc_endCliente : "");

		xml_g->addItem(TAG_XML_OUT_CEP_CLIENTE, (pc_cepCliente != NULL)? pc_cepCliente : "");

		xml_g->addItem(TAG_XML_OUT_MUNIC_CLIENTE, (pc_municCliente != NULL)? pc_municCliente : "");

		xml_g->addItem(TAG_XML_OUT_UF_CLIENTE, (pc_ufCliente != NULL)? pc_ufCliente : "");	

		xml_g->addItem(TAG_XML_OUT_CELULAR, (pc_celular != NULL)? pc_celular : "");	

		xml_g->addItem(TAG_XML_OUT_LOCAL, (pc_local != NULL)? pc_local : "");	

		xml_g->addItem(TAG_XML_OUT_QUITACAO, (pc_quitacao != NULL)? pc_quitacao : "");	

		xml_g->addItem(TAG_XML_OUT_VL_QUITACAO, (pc_vlQuitacao != NULL)? pc_vlQuitacao : "");

		xml_g->addItem(TAG_XML_OUT_BASE_ICMS, (pc_baseICMS != NULL)? pc_baseICMS : "");

		xml_g->addItem(TAG_XML_OUT_ICMS, (pc_ICMS != NULL)? pc_ICMS : "");

		xml_g->addItem(TAG_XML_OUT_PER_ICMS, (pc_perICMS != NULL)? pc_perICMS : "");	

		xml_g->addItem(TAG_XML_OUT_BASE_FUST, (pc_baseFUST != NULL)? pc_baseFUST : "");

		xml_g->addItem(TAG_XML_OUT_FUST, (pc_FUST != NULL)? pc_FUST : "");	

		xml_g->addItem(TAG_XML_OUT_BASE_FUNTEL, (pc_baseFUNTEL != NULL)? pc_baseFUNTEL : "");

		xml_g->addItem(TAG_XML_OUT_FUNTEL, (pc_FUNTEL != NULL)? pc_FUNTEL : "");	

		xml_g->addItem(TAG_XML_OUT_SERVICO, (pc_servico != NULL)? pc_servico : "");

		xml_g->addItem(TAG_XML_OUT_VL_SERVICO, (pc_vlServico != NULL)? pc_vlServico : "");

		xml_g->addItem(TAG_XML_OUT_TOTAL_NOTA, (pc_totalNota != NULL)? pc_totalNota : "");

		xml_g->addItem(TAG_XML_OUT_VL_PAGO, (pc_vlPago != NULL)? pc_vlPago : "");

		xml_g->addItem(TAG_XML_OUT_HASHCODE, (pc_hashcode != NULL)? pc_hashcode : "");

		xml_g->addItem(TAG_XML_OUT_INSC_ESTADUAL, (pc_inscEstadual != NULL)? pc_inscEstadual : "");

		xml_g->addItem(TAG_XML_OUT_CPF, (pc_CPF != NULL)? pc_CPF : "");

		xml_g->addItem(TAG_XML_OUT_CNPJ, (pc_CNPJ != NULL)? pc_CNPJ : "");

		
		if (pc_notaFiscal != NULL)   XMLString::release(&pc_notaFiscal);
		if (pc_notaSerie != NULL)    XMLString::release(&pc_notaSerie);
		if (pc_dataEmissao != NULL)  XMLString::release(&pc_dataEmissao);
		if (pc_CFOP != NULL)         XMLString::release(&pc_CFOP);
		if (pc_nomeCliente != NULL)  XMLString::release(&pc_nomeCliente);
		if (pc_endCliente != NULL)   XMLString::release(&pc_endCliente);
		if (pc_cepCliente != NULL)   XMLString::release(&pc_cepCliente);
		if (pc_municCliente != NULL) XMLString::release(&pc_municCliente);
		if (pc_ufCliente != NULL)    XMLString::release(&pc_ufCliente);
		if (pc_celular != NULL)      XMLString::release(&pc_celular);
		if (pc_local != NULL)        XMLString::release(&pc_local);
		if (pc_quitacao != NULL)     XMLString::release(&pc_quitacao);
		if (pc_vlQuitacao != NULL)   XMLString::release(&pc_vlQuitacao);
		if (pc_baseICMS != NULL)     XMLString::release(&pc_baseICMS);
		if (pc_ICMS != NULL)         XMLString::release(&pc_ICMS);
		if (pc_perICMS != NULL)      XMLString::release(&pc_perICMS);
		if (pc_baseFUST != NULL)     XMLString::release(&pc_baseFUST);
		if (pc_FUST != NULL)         XMLString::release(&pc_FUST);
		if (pc_baseFUNTEL != NULL)   XMLString::release(&pc_baseFUNTEL);
		if (pc_FUNTEL != NULL)       XMLString::release(&pc_FUNTEL);
		if (pc_servico != NULL)      XMLString::release(&pc_servico);
		if (pc_vlServico != NULL)    XMLString::release(&pc_vlServico);
		if (pc_totalNota != NULL)    XMLString::release(&pc_totalNota);
		if (pc_vlPago != NULL)       XMLString::release(&pc_vlPago);
		if (pc_hashcode != NULL)     XMLString::release(&pc_hashcode);
		if (pc_inscEstadual != NULL) XMLString::release(&pc_inscEstadual);
		if (pc_CPF != NULL)          XMLString::release(&pc_CPF);
		if (pc_CNPJ != NULL)         XMLString::release(&pc_CNPJ);

	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();

}
