#ifndef REINICIASENHA
	#define REINICIASENHA

#include <tuxfw.h>

class ReiniciaSenhaC
{

	public:
		ReiniciaSenhaC();
		int reiniciarSenha(int idPessoa, int idPessoaUsr, char* telefone, int tpTitularidade, int idCanal, int idPessoaUsuario, int iIdTerminal, char* obsRegistro );
		int reiniciarFrase(int idPessoa, int idPessoaUsr, char* telefone, int tpTitularidade, int idCanal, int idPessoaUsuario, int iIdTerminal, char* obsRegistro );

};
 
#endif

