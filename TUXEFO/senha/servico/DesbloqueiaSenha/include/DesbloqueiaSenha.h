/* $Id: DesbloqueiaSenha.h,v 1.1 2009/07/31 15:35:16 a5110702 Exp $ */

#ifndef DESBLOQUEIASENHA
	#define DESBLOQUEIASENHA

#include <tuxfw.h>

class DesbloqueiaSenha
{

	public:
		DesbloqueiaSenha();
		int desbloqueiaSenha(int idPessoa, int idPessoaUsr, char* telefone, int tpTitularidade, int idCanal, int idPessoaUsuario, int iIdTerminal, char* obsRegistro );

};
 
#endif

