#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Defines/Defines.h>

DECLARE_TUXEDO_SERVICE(ENVEMAILSENHA);


void implENVEMAILSENHA::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	char	cDsEmail[100] ="";
	char*	pcTagXmlIn = NULL;
	char    operacao[10];
	CTuxHelperClever helper;
	CLinha oLinha;

	pcTagXmlIn = helper.walkTree(dnode, "dsEmail", 0);
	strcpy(cDsEmail, pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode, "operacao", 0);
	strcpy(operacao, pcTagXmlIn);

	
	try{

		oLinha.enviaEmailCriacaoSenha(cDsEmail, this->getUser(), operacao);

	}
	catch( ... )
	{	}

	setStatusCode("11I0000", "MENSAGEM ENVIADA COM SUCESSO");

}