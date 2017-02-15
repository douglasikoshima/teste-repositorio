/* $Id: ValidaSenha.h,v 1.1.4.3 2008/11/01 17:54:54 akurak Exp $ */

#ifndef VALIDASENHA
	#define VALIDASENHA

#include <tuxfw.h>

typedef struct 
{

} stValidaSenha;

class ValidaSenhaC
{
		
	public:
		stValidaSenha	m_stAtlys;
		ValidaSenhaC();
		void validar(char* telefone, char* senha, int* resultado, char* titularidade, int idCanal, int idPessoaUsuario, int iIdTerminal);
		int  verificarTentativa(char* telefone, char* senha);
		int  consultarTipoPessoa(int nrLinha, int intCdAreaRegistro, int intTipoRelacionamento);
		int  consultarTipoLinha(int nrLinha, int intCdAreaRegistro);

};


#endif
 