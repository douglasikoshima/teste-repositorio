/* $Id: svcReiniciaSenha.cpp,v 1.1 2009/07/31 15:33:52 a5110702 Exp $ */

#include "../include/ReiniciaSenha.h"

DECLARE_TUXEDO_SERVICE(ReiniciaSenha);

void implReiniciaSenha::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implReiniciaSenha::Execute()");
	char *p;

	ReiniciaSenhaC rS; 

	char telefone[11];
	char obsRegistro[256];
	char titularidade[2];

	int  idPessoa        = 0;
	int  idPessoaUsr     = 0;
	int  idCanal         = 0;
	int  idPessoaUsuario = atoi(getUser());
	int  indFrase        = 0;
	int  iIdTerminal	 = 0;

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

    	if (p=walkTree( dnode, "obsRegistro", 0 ),p) 
	{
		strcpy(obsRegistro, p);
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

    	if (p=walkTree( dnode, "idCanal", 0 ),p) 
    	{
       		idCanal = atoi(p);
		XMLString::release(&p);
    	}

    	if (p=walkTree( dnode, "indFrase", 0 ),p) 
    	{
       		indFrase = atoi(p);
		XMLString::release(&p);
    	}


    	if (p=walkTree( dnode, "idTerminal", 0 ),p) 
    	{
       		iIdTerminal = atoi(p);
		XMLString::release(&p);
    	}



	int tpTitularidade = 0;

	if (titularidade[0] == 'U')
		tpTitularidade = 1;
	else
		tpTitularidade = 2;
	
	xml_g->createTag("SenhaOperarVO");
	xml_g->addProp("xmlns","senha.fo.vivo.com.br/vo");

	int retorno = 0;
	
	if (indFrase > 0)
	{
		retorno = rS.reiniciarFrase( idPessoa, idPessoaUsr, telefone, tpTitularidade, idCanal, idPessoaUsuario, iIdTerminal,obsRegistro );
		if (retorno > 0)
		{
			setStatusCode("07I0000","A frase secreta foi reiniciada.");
			xml_g->addItem("mensagem","A frase secreta foi reiniciada.");
		}
		else 
		{
			setStatusCode("07W0002","A frase secreta não pode ser reiniciada.");
			xml_g->addItem("mensagem","A frase secreta não pode ser reiniciada.");
		}
	}

	else
	{
		retorno = rS.reiniciarSenha( idPessoa, idPessoaUsr, telefone, tpTitularidade, idCanal, idPessoaUsuario, iIdTerminal, obsRegistro );
		if (retorno > 0)
		{
		   			setStatusCode("07I0000","A senha foi reiniciada.");
			xml_g->addItem("mensagem","A senha foi reiniciada.");
		}
		else if (retorno == -1)
		{
			setStatusCode("07W0001","CPF do cliente não encontrado.");
			xml_g->addItem("mensagem","CPF do cliente não encontrado.");
		}
		else if (retorno == -5)
		{
			setStatusCode("07W0002","Não é permitida a reinicialização de senha de pessoa jurídica");
			xml_g->addItem("mensagem","Não é permitida a reinicialização de senha de pessoa jurídica");
		}
		else 
		{
			setStatusCode("07W0002","A senha não pode ser reiniciada.");
			xml_g->addItem("mensagem","A senha não pode ser reiniciada.");
		}
	}


	xml_g->closeTag();
	
	ULOG_END("implReiniciaSenha::Execute()");

}
