#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Pessoa/Pessoa.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(GETCADPESSOAPP);

void consultarSiglas( XMLGen* xml_g)
{
	CPessoa oPessoa;

	oPessoa.consultaSiglasCadastro(xml_g);

	
}

void consultarCadPessoaPP(DOMNode* dnode, XMLGen* xml_g)
{
	CTuxHelperClever helper;
	char*  pcTagXmlIn = NULL;
	char   cNrDocumento[256]="";
	int    iIdTipoDocumento = 0;
	CPessoa oPessoa;
	

	pcTagXmlIn = helper.walkTree(dnode,"nrDocumento", 0);

	ASSERT_STR(pcTagXmlIn, "nrDocumento");

	strcpy(cNrDocumento, pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode,"idTipoDocumento", 0);

	iIdTipoDocumento = atoi(pcTagXmlIn);

	try{

		oPessoa.consultaDadosPessoaPP(cNrDocumento, iIdTipoDocumento, xml_g);

	}

	catch ( ... )
	{	}

}

void implGETCADPESSOAPP::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	CTuxHelperClever helper;
	char*  pcTagXmlIn = NULL;
	CPessoa oPessoa;

	pcTagXmlIn = helper.walkTree(dnode, "operacao", 0);

	if (strcmp(pcTagXmlIn, "consultarSiglas") == 0)
		consultarSiglas(xml_g);

	else if (strcmp(pcTagXmlIn, "consultarDadosCadPrePago") == 0)
		consultarCadPessoaPP(dnode, xml_g);

	 setStatusCode("11I0000", "Consulta Efetuada com sucesso.");

}