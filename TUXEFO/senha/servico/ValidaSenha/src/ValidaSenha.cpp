/* $Id: ValidaSenha.cpp,v 1.1.2.1 2010/09/03 19:03:56 a5116174 Exp $ */

#include <tuxfw.h>
#include "../include/ValidaSenha.h"

extern void validaSenha(char* telefone, char* senha, int* resultado, char* titularidade, int idCanal, int idPessoaUsuario, int iIdTerminal);
extern int  verificarQuatidadeTentativa(char* telefone, char* senha);
extern int	consultarTipoPessoaDB(int nrLinha, int intCdAreaRegistro, int intTipoRelacionamento);
extern int  consultarTipoLinhaDB(int nrLinha, int intCdAreaRegistro);
extern void validarSenhaSemUsuarioDB(char* telefone, char* senha, int* resultado);
extern int recuperarInfCadastro(string & strNumTelefone, string & strDDD, ValidaSenhaC &vS);

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

void ValidaSenhaC::validarSenhaSemUsuario(char* telefone, char* senha, int* resultado)
{
	
	validarSenhaSemUsuarioDB(telefone, senha, resultado);
}

int ValidaSenhaC::recuperarInformcaoesCadastro(string & strNumTelefone, string & strDDD)
{
	//return 0;
	return recuperarInfCadastro(strNumTelefone, strDDD, *this);
}