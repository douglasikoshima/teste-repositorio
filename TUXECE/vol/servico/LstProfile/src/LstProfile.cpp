#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Assunto/Assunto.hpp>
#include <Pessoa/Pessoa.hpp>
#include <TuxHelperClever/TuxHelperClever.h>


DECLARE_TUXEDO_SERVICE(LSTPROFILE);

static void ProfileCompleto(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	CAssunto oAssunto;
	char* pcTagXmlIn = NULL;
	long   iIdPessoa  = -1;
	int   iIdCanal = -1;
	XMLGen   xmlBusca;
	int      iXMLLen;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idPessoa", 0);
	ASSERT_LONG(iIdPessoa, pcTagXmlIn, "idPessoa");

	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	ASSERT_INT(iIdCanal, pcTagXmlIn, "idCanal");
	
	xml_g->createTag("LSTPROFILEVO");
	xml_g->addProp("xmlns", "dados.vol.vivo.com.br/vo");

	try
	{
		oAssunto.montaArvorePessoaCanal(iIdPessoa, iIdCanal, &xmlBusca);
		
		xml_g->aggregateXML(xmlBusca.retrieveXML(&iXMLLen));
		
		statusCode.setCod("11I0000");
		statusCode.setMsg("CONSULTA EFETUADA COM SUCESSO");
		
	}
	catch(...)
	{
		statusCode.setCod("11W0001");
		statusCode.setMsg("NAO FOI POSSIVEL EFETUAR A CONSULTA");
	}

	xml_g->closeTag();

}

static void ConsultarCPF(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	CPessoa oPessoa;
	char*	pcTagXmlIn = NULL;
	long	iIdPessoa  = -1;
	XMLGen  xmlBusca;
	char	chrCPF[256];
	int    iIdDocumento = -1;

	strcpy(chrCPF,"");	

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idPessoa", 0);

	ASSERT_LONG(iIdPessoa, pcTagXmlIn, "idPessoa");

	oPessoa.setIdPessoa(iIdPessoa);

	try
	{
		oPessoa.consultaTbDocumento(chrCPF, &iIdDocumento);			
		statusCode.setCod("11I0000");
		statusCode.setMsg("CONSULTA EFETUADA COM SUCESSO");
		
	}
	catch(TuxBasicOraException)
	{
		statusCode.setCod("11W0001");
		statusCode.setMsg("NAO FOI POSSIVEL EFETUAR A CONSULTA");		
	}

	      
	xml_g->createTag("ConsultaCPFVO");
	xml_g->addProp("xmlns", "servicos.vol.vivo.com.br/vo");

	//if (strlen(chrCPF) > 0)

	if (iIdDocumento != -1)
		xml_g->addItem("idCPF",iIdDocumento );	

	if (strlen(chrCPF) > 0)
		xml_g->addItem("numCPF", chrCPF);	

	xml_g->closeTag();

}
void implLSTPROFILE::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;
	CStatusCode	  statusCode;
	char*         pcTagXmlIn = NULL;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"inOperacao", 0);
	ASSERT_STR(pcTagXmlIn, "inOperacao");

	// descobrindo qual operação executarProfileCompleto
	if(!strcmp(pcTagXmlIn, "ProfileCompleto"))
		ProfileCompleto(dnode, xml_g, statusCode);

	else if(!strcmp(pcTagXmlIn, "ConsultarCpf"))
		ConsultarCPF(dnode, xml_g, statusCode);	
	
	setStatusCode(statusCode.getCod(), statusCode.getMsg());	

}
