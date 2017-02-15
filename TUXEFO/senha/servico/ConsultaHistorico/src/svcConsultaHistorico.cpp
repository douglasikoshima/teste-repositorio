/* $Id: svcConsultaHistorico.cpp,v 1.1 2009/07/31 15:34:23 a5110702 Exp $ */

#include <tuxfw.h>
#include "../include/ConsultaHistorico.h"

DECLARE_TUXEDO_SERVICE(ConsHistorico);

void implConsHistorico::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implConsHistorico::Execute()");
	char *p;

	int  indPesquisa;
	int  idPessoa;
	int  idPessoaUsr;
	int  idPessoaCli;
	
	char telefone[11];
	char titularidade[2];

	if (p=walkTree( dnode, "indPesquisa", 0 ),p) 
    	{
        	indPesquisa = atoi(p);
		XMLString::release(&p);
    	}

	if (p=walkTree( dnode, "idPessoa", 0 ),p) 
    	{
        	idPessoa = atoi(p);
        	XMLString::release(&p);
    	}

 	if (p=walkTree( dnode, "idPessoaUsr", 0 ),p) 
    	{
        	idPessoaUsr = atoi(p);
        	XMLString::release(&p);
    	}

	if (p=walkTree( dnode, "idPessoaCli", 0 ),p) 
    	{
        	idPessoaCli = atoi(p);
        	XMLString::release(&p);
    	}

   	if (p=walkTree( dnode, "telefone", 0 ),p) 
    	{
        	strcpy(telefone, p);
		XMLString::release(&p);
    	}

    	if (p=walkTree( dnode, "titularidadeSenha", 0 ),p) 
    	{
        	strcpy(titularidade, p);
		XMLString::release(&p);
    	}

	int tpTitularidade = 0;

	if (titularidade[0] == 'U')
		tpTitularidade = 1;
	else
		tpTitularidade = 2;
	

	xml_g->createTag("SenhaMovimentosVO");
	xml_g->addProp("xmlns","senha.fo.vivo.com.br/vo");

	ConsultaHistorico cH;
	if (indPesquisa > 0)
		cH.consultarHistorico( idPessoa, telefone, tpTitularidade, xml_g);
	else{
		cH.consultarNome( idPessoaUsr, idPessoaCli, xml_g);
		xml_g->addItem("inBloqueado", cH.consultarListaRestritiva(telefone) == true ? 1 :0);
	}



	xml_g->closeTag();

	setStatusCode("07I0000","Pesquisa realizada.");
	
	ULOG_END("implConsHistorico::Execute()");

}
