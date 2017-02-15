/* $Id: DesbloqueiaSenha.cpp,v 1.1 2009/07/31 15:33:39 a5110702 Exp $ */

#include <tuxfw.h>
#include "../include/DesbloqueiaSenha.h"
#include "../../ReiniciaSenha/include/ReiniciaSenha.h"

extern void proCDesbloqueiaSenhas(char*, int, int, int, char*);

DesbloqueiaSenha::DesbloqueiaSenha()
{

}

int DesbloqueiaSenha::desbloqueiaSenha(int idPessoa, int idPessoaUsr, char* telefone, int tpTitularidade, int idCanal, int idPessoaUsuario, int iIdTerminal, char* obsRegistro )
{

	ReiniciaSenhaC rS;

	if ( rS.reiniciarSenha( idPessoa, idPessoaUsr, telefone, tpTitularidade, idCanal, idPessoaUsuario, iIdTerminal, obsRegistro ) )
	{

		proCDesbloqueiaSenhas(telefone, idCanal, idPessoaUsuario, iIdTerminal, obsRegistro);

	}
	else
	{
		return -1;
	}

	return 1;
}
