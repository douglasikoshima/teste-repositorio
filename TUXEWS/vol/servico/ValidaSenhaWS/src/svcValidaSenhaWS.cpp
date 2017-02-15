/* $Id: svcValidaSenhaWS.cpp,v 1.1.4.1 2010/12/22 20:27:26 a5114878 Exp $ */

#include <tuxfw.h>
#include "../include/ValidaSenha.h"

#define PF		1
#define PJ		2
#define USUARIO 1
#define CLIENTE 2
#define NAO_ENCONTRADO -1 

DECLARE_TUXEDO_SERVICE(VALIDASENHAWS);

void implVALIDASENHAWS::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implValidaSenha::Execute()");
	char telefone[11];
	char senha[256];

	strcpy(telefone, walkTree( dnode, "telefone", 0 ));
	strcpy(senha,    walkTree( dnode, "cdSenha", 0 ));


	int  idCanal				= atoi(walkTree( dnode, "idCanal", 0 ));
	int  idPessoaUsuario		= atoi(getUser());
	int  iIdTerminal			= atoi(walkTree(dnode, "idTerminal", 0));

	int  resultado     = 0;
	char titulariadade[2] = " ";
	int  qtTentativaErro = 0;
	char c_area[2] ="";
	char c_linha[8]="";
	int	 iNrLinha = 0;
	int	 iCdAreaRegistro = 0;
	int	 iTipoPessoaCliente = 0;
	int  iTipoPessoaUsuario = 0;
	int	 iTipoLinha = 0;
	ValidaSenhaC vS;
	

	ULOG("ValidaSenha - idTerminal = %d\r\n", iIdTerminal);
	strncpy(c_area, telefone, 2);
	strncpy(c_linha, &telefone[2], 8);
	
	iNrLinha = atoi(c_linha);	
	iCdAreaRegistro = atoi(c_area);	

	vS.validar(telefone, senha, &resultado, titulariadade, idCanal, idPessoaUsuario, iIdTerminal);

	if (resultado == 0)
	{
		setStatusCode("07W0000","Senha não encontrada.");
	}
	else if (resultado == -1)
	{
		setStatusCode("07W0001","Linha não encontrada.");
	}
    else if ( resultado == -11 )  // PJ -> PJ -> Usuario e Cliente PJ
    {
        setStatusCode("07W0010","Linha PJ não possui acesso ao VOL.");
    }
	else if (resultado == -2)
	{
		qtTentativaErro = vS.verificarTentativa(telefone, senha);

		if (qtTentativaErro == 1)
			setStatusCode("07W0004","Senha inválida. 1a. Tentativa");
		else if (qtTentativaErro == 2)
			setStatusCode("07W0005","Senha inválida. 2a. Tentativa");
		else if (qtTentativaErro == 3)
			setStatusCode("07W0009","Usuário Bloqueado.");
		else
			setStatusCode("07W0002","Senha inválida.");
	}
	else if (resultado == -3)
	{
		setStatusCode("07W0003","Usuário Bloqueado.");
	}
	else if (resultado == -100)
	{
		setStatusCode("07W0006","Senha não cadastrada.");
	}
	else if (resultado == -101)
	{
		setStatusCode("07W0007","Senha reinicializada.");
	}
	else if (resultado > 0) 
	{

		setStatusCode("07I0000","Acesso Autorizado.");

		xml_g->createTag("ValidaSenha");
		xml_g->addItem("titularidadeSenha",titulariadade);
		
		if (resultado == 2)
			xml_g->addItem("alteraSenha","S");
	
		xml_g->closeTag();
	}

   ULOG_END("implValidaSenha::Execute()");
}


