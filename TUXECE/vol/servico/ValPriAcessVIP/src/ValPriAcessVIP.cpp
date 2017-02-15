#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Relacionamento/Relacionamento.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(VALPRIACESSVIP);



void implVALPRIACESSVIP::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	
	CTuxHelperClever helper;
	char*  pcTagXmlIn = NULL;
	CStatusCode	  statusCode;
	CRelacionamento oRelacionamento;
	int iNrLinha = 0;
	int iCdAreaRegistro = 0;

	pcTagXmlIn = helper.walkTree(dnode, "nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");

	pcTagXmlIn = helper.walkTree(dnode, "cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");
	
	xml_g->createTag("VALPRIACESSOVIP");	
	xml_g->addProp("xmlns", "dados.vol.vivo.com.br/vo");

	if (oRelacionamento.consultarUsoServicoVIP( iNrLinha, iCdAreaRegistro)  == true)	
		xml_g->addItem("inPrimeiroAcessoVIP", 0);
	else
		xml_g->addItem("inPrimeiroAcessoVIP", 1);
	
	xml_g->closeTag();

	setStatusCode("11I0000", "Consulta efetuada com sucesso." );

}