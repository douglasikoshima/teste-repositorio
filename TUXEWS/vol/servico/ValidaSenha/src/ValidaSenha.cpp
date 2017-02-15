/* $Id: ValidaSenha.cpp,v 1.1 2007/07/06 22:01:23 akurak Exp $ */

#include <tuxfw.h>
#include "../include/ValidaSenha.h"

extern void validaSenha(char* telefone, char* senha, int* resultado, char* titularidade, int idCanal, int idPessoaUsuario, int iIdTerminal);
extern int  verificarQuatidadeTentativa(char* telefone, char* senha);
extern int	consultarTipoPessoaDB(int nrLinha, int intCdAreaRegistro, int intTipoRelacionamento);
extern int  consultarTipoLinhaDB(int nrLinha, int intCdAreaRegistro);

ValidaSenhaC::ValidaSenhaC()
{

}

void ValidaSenhaC::validar(char* telefone, char* senha, int* resultado, char* titularidade, int idCanal, int idPessoaUsuario, int iIdTerminal)
{
	validaSenha(telefone, senha, resultado, titularidade, idCanal, idPessoaUsuario, iIdTerminal);
}

int ValidaSenhaC::verificarTentativa(char* telefone, char* senha)
{

	return verificarQuatidadeTentativa(telefone, senha);

}


int ValidaSenhaC::consultarTipoPessoa(int nrLinha, int intCdAreaRegistro, int intTipoRelacionamento)
{
	return consultarTipoPessoaDB(nrLinha, intCdAreaRegistro, intTipoRelacionamento);
}



int ValidaSenhaC::consultarTipoLinha(int nrLinha, int intCdAreaRegistro)
{
	return consultarTipoLinhaDB(nrLinha, intCdAreaRegistro);
}
