#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::setCliente()
{
/*	JAVA -> FO

<msg>
	<msgHdr>
		<user>2</user>
		<service>TUXPROXYBE</service>
	</msgHdr>
	<msgBody>
		<ProxyOperacao>setCliente</ProxyOperacao>
		<ProxyLinha></ProxyLinha>
		<tipoCliente>{P,E}</tipoCliente>
		<nome></nome>
		<nomeAbreviado></nomeAbreviado>
		<Propriedade></Propriedade>
		<confidencial>{1,0}</confidencial>
		<CPF></CPF>
		<tipoCPF>{D,P}</tipoCPF>
		<RG></RG>
		<tipoRG></tipoRG>
		<dataExpiracao></dataExpiracao>
		<orgaoExpeditor></orgaoExpeditor>
		<estadoExpedicao></estadoExpedicao>
		<dataNascimento></dataNascimento>
		<estadoCivil>{C,S,D,V}</estadoCivil>
		<codSexo>{M:F}</codSexo>
		<telefone></telefone>
		<fax></fax>
		<eMail></eMail>
		<numDepend></numDepend>
		<passaporte></passaporte>
		<tipoPassaporte>{D,P}</tipoPassaporte>
		<cartaConducao></cartaConducao>
		<tipoCartaCond>{D,P}</tipoCartaCond>
		<aoCuidadoDe></aoCuidadoDe>
		<obs></obs>
		<conservatoriaRegistro></conservatoriaRegistro>
		<CNPJ></CNPJ>
		<CNAE></CNAE>
		<habilitacoes></habilitacoes>
		<outroCelular></outroCelular>
		<tipoConta></tipoConta>
		<subTipo></subTipo>
		<rendaMensal></rendaMensal>
		<contaCorrent></contaCorrent>
		<banco></banco>
		<IE></IE>
		<nomeSufixo></nomeSufixo>
		<primeiroNome></primeiroNome>
		<sobreNome></sobreNome>
		<nomeContato></nomeContato>
		<carteiraTrabalho></carteiraTrabalho>
		<endereco></endereco>
		<complemento></complemento>
		<bairro></bairro>
		<CEP></CEP>
		<cidade></cidade>
		<estado></estado>
		<pais></pais>
		<logradouro></logradouro>
		<numero></numero>
	</msgBody>
</msg>
*/

	char* pc_CELULAR = NULL;

	char* pc_tipoCliente = NULL;
	char* pc_nome = NULL;
	char* pc_CPF = NULL;
	char* pc_RG = NULL;
	char* pc_dataExpiracao = NULL;
	char* pc_orgaoExpeditor = NULL;
	char* pc_estadoExpedicao = NULL;
	char* pc_estadoCivil = NULL;
	char* pc_codSexo = NULL;
	char* pc_CNPJ = NULL;
	char* pc_CNAE = NULL;
	char* pc_endereco = NULL;
	char* pc_complemento = NULL;
	char* pc_bairro = NULL;
	char* pc_CEP = NULL;
	char* pc_cidade = NULL;
	char* pc_estado = NULL;
	char* pc_logradouro = NULL;
	char* pc_numero = NULL;

	// A verificacao ja foi realizada
	pc_CELULAR = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	
	// Trata a tag tipoCliente
	pc_tipoCliente = tuxhp->walkTree(dnode, TAG_XML_IN_TIPO_CLIENTE, 0);
	
	if (pc_tipoCliente == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_TIPO_CLIENTE, EMSG_TAG_XML_IN_NE_TIPO_CLIENTE);
	}

	if (*pc_tipoCliente == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_TIPO_CLIENTE, EMSG_TAG_XML_IN_VV_TIPO_CLIENTE);
	}

	if ((! Util::cmp(pc_tipoCliente, "P")) && (! Util::cmp(pc_tipoCliente, "E")))
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_tipoCliente);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_TIPO_CLIENTE, EMSG_TAG_XML_IN_VI_TIPO_CLIENTE);
	}

	
	// Associacao dos valores
	pc_nome                  = tuxhp->walkTree(dnode, TAG_XML_IN_NOME, 0);
	pc_CPF                   = tuxhp->walkTree(dnode, TAG_XML_IN_CPF, 0);
	pc_RG                    = tuxhp->walkTree(dnode, TAG_XML_IN_RG, 0);
	pc_dataExpiracao         = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_EXPIRACAO, 0);
	pc_orgaoExpeditor        = tuxhp->walkTree(dnode, TAG_XML_IN_ORGAO_EXPEDITOR, 0);
	pc_estadoExpedicao       = tuxhp->walkTree(dnode, TAG_XML_IN_ESTADO_EXPEDICAO, 0);
	pc_estadoCivil           = tuxhp->walkTree(dnode, TAG_XML_IN_ESTADO_CIVIL, 0);
	pc_codSexo               = tuxhp->walkTree(dnode, TAG_XML_IN_COD_SEXO, 0);
	pc_CNPJ                  = tuxhp->walkTree(dnode, TAG_XML_IN_CNPJ, 0);
	pc_CNAE                  = tuxhp->walkTree(dnode, TAG_XML_IN_CNAE, 0);
	pc_endereco              = tuxhp->walkTree(dnode, TAG_XML_IN_ENDERECO, 0);
	pc_complemento           = tuxhp->walkTree(dnode, TAG_XML_IN_COMPLEMENTO, 0);
	pc_bairro                = tuxhp->walkTree(dnode, TAG_XML_IN_BAIRRO, 0);
	pc_CEP                   = tuxhp->walkTree(dnode, TAG_XML_IN_CEP, 0);
	pc_cidade                = tuxhp->walkTree(dnode, TAG_XML_IN_CIDADE, 0);
	pc_estado                = tuxhp->walkTree(dnode, TAG_XML_IN_ESTADO, 0);
	pc_logradouro            = tuxhp->walkTree(dnode, TAG_XML_IN_LOGRADOURO, 0);
	pc_numero                = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO, 0);


/*	FO -> GW

	<ROOT>
		<TRANSACAO>2</TRANSACAO>
		<CELULAR>1171010160</CELULAR>
		<NOM>PTI Brasil</NOM>
		<LOGR>R</LOGR>
		<END>Cubatao</END>
		<NUM>123</NUM>
		<CMPL>2AnD sala 3</CMPL>
		<BRO>Paraiso</BRO>
		<MUNIC>SP</MUNIC>
		<CEP>05101010</CEP>
		<CID>SP</CID>
		<UF>SP</UF>
		<DOCTIPO1>RG</DOCTIPO1>
		<DOCTIPO2>CPF</DOCTIPO2>
		<DOCNUM1>222222221</DOCNUM1>
		<DOCNUM2>26778545229</DOCNUM2>
		<DTEXP>YYYY-MM-DD</DTEXP>
		<OE>SPSSP</OE>
		<ESTEXP>SP</ESTEXP>
		<CNAE>0</CNAE>
		<TIPO_PESSOA>F</TIPO_PESSOA>
		<LDILDN>D</LDILDN>
		<LOGIN>c_xprots</LOGIN>
	</ROOT>
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_SET_CLIENTE;
	XMLGen o_XMLtoGW;
	DOMNode* po_XMLfromGW = NULL;


    o_XMLtoGW.createTag(TAG_XML_PPS_IN_ROOT);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TRANS, vc_TRANSACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CELULAR, pc_CELULAR);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_NOM, (pc_nome != NULL)? pc_nome : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_LOGR, (pc_logradouro != NULL)? pc_logradouro : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_END, (pc_endereco != NULL)? pc_endereco : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_NUM, (pc_numero != NULL)? pc_numero : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CMPL, (pc_complemento != NULL)? pc_complemento : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_BRO, (pc_bairro != NULL)? pc_bairro : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_MUNIC, (pc_cidade != NULL)? pc_cidade : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CEP, (pc_CEP != NULL)? pc_CEP : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CID, (pc_cidade != NULL)? pc_cidade : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_UF, (pc_estado != NULL)? pc_estado : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DOCTIPO1, "RG");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DOCTIPO2, (Util::cmp(pc_tipoCliente, "P"))? "CPF" : "CNPJ");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DOCNUM1, (pc_RG != NULL)? pc_RG : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DOCNUM2, (Util::cmp(pc_tipoCliente, "P"))? pc_CPF : pc_CNPJ);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DTEXP, (pc_dataExpiracao != NULL)? pc_dataExpiracao : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_OE, (pc_orgaoExpeditor != NULL)? pc_orgaoExpeditor: "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_ESTEXP, (pc_estadoExpedicao != NULL)? pc_estadoExpedicao : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CNAE, (pc_CNAE != NULL)? pc_CNAE : "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TIPO_PESSOA, (Util::cmp(pc_tipoCliente, "P"))? "F" : "J");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_LDILDN, "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_LOGIN, TAG_XML_PPS_IN_VAL_LOGIN);
	o_XMLtoGW.closeTag();


	if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

	XMLString::release(&pc_tipoCliente);

	if (pc_nome != NULL)            XMLString::release(&pc_nome);
	if (pc_CPF != NULL)             XMLString::release(&pc_CPF);
	if (pc_RG != NULL)              XMLString::release(&pc_RG);
	if (pc_dataExpiracao != NULL)   XMLString::release(&pc_dataExpiracao);
	if (pc_orgaoExpeditor != NULL)  XMLString::release(&pc_orgaoExpeditor);
	if (pc_estadoExpedicao != NULL) XMLString::release(&pc_estadoExpedicao);
	if (pc_estadoCivil != NULL)     XMLString::release(&pc_estadoCivil);
	if (pc_codSexo != NULL)         XMLString::release(&pc_codSexo);
	if (pc_CNPJ != NULL)            XMLString::release(&pc_CNPJ);
	if (pc_CNAE != NULL)            XMLString::release(&pc_CNAE);
	if (pc_endereco != NULL)        XMLString::release(&pc_endereco);
	if (pc_complemento != NULL)     XMLString::release(&pc_complemento);
	if (pc_bairro != NULL)          XMLString::release(&pc_bairro);
	if (pc_CEP != NULL)             XMLString::release(&pc_CEP);
	if (pc_cidade != NULL)          XMLString::release(&pc_cidade);
	if (pc_estado != NULL)          XMLString::release(&pc_estado);
	if (pc_logradouro != NULL)      XMLString::release(&pc_logradouro);
	if (pc_numero != NULL)          XMLString::release(&pc_numero);

	
	// Faz a chamada remota e guarda o xml de retorno
	po_XMLfromGW = this->callRemoteAPI(SERVICE_PPS, &o_XMLtoGW, NULL);

	// Verifica o retorno
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromGW == NULL)
		throw new TuxBasicSvcException(ECD_PPS_GW_NOT_RESPOND, EMSG_PPS_GW_NOT_RESPOND);



/*	GW -> FO

	<ROOT>
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

/* FO -> JAVA

	<ClienteVO>
	</ClienteVO>
*/

	xml_g->createTag(TAG_XML_OUT_CLIENTE_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}

	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();

}
