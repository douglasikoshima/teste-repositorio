/* $Id: svcDesbloqueiaSenha.cpp,v 1.1 2009/07/31 15:33:39 a5110702 Exp $ */

#include <tuxfw.h>
#include "../include/DesbloqueiaSenha.h"

DECLARE_TUXEDO_SERVICE(DesSenha);

void implDesSenha::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implDesSenha::Execute()");
	char telefone[11];
	char titularidade[2];
	char obsRegistro[256];

	strcpy( telefone,     walkTree( dnode, "telefone", 0 ) );
	strcpy( titularidade, walkTree( dnode, "titularidadeSenha", 0 ) );
	strcpy( obsRegistro,  walkTree( dnode, "obsRegistro", 0 ) );

	int  idPessoa        = atoi( walkTree( dnode, "idPessoa", 0 ) );
	int  idPessoaUsr     = atoi( walkTree( dnode, "idPessoaUsr", 0 ) );
	int  idCanal         = atoi( walkTree( dnode, "idCanal", 0 ) );
	int  idPessoaUsuario = atoi(getUser());
	int  iIdTerminal	 = atoi( walkTree( dnode, "idTerminal", 0) );

	int tpTitularidade = 0;

	if (titularidade[0] == 'U')
		tpTitularidade = 1;
	else
		tpTitularidade = 2;
	
	
	DesbloqueiaSenha dS;
	
	int retorno = dS.desbloqueiaSenha( idPessoa, idPessoaUsr, telefone, tpTitularidade, idCanal, idPessoaUsuario, iIdTerminal, obsRegistro );
	
	if (retorno > 0)
	{
		setStatusCode("07I0000","Desbloqueio realizado.");
	}
	else if (retorno == -1)
	{
		setStatusCode("07W0002","A senha não pode ser reiniciada para desbloqueio.");
	}
	else 
	{
		setStatusCode("07W0003","A senha não pode desbloqueada.");
	}
   ULOG_END("implDesSenha::Execute()");
}
