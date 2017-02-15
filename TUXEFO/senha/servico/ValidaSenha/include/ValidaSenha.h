/* $Id: ValidaSenha.h,v 1.1.2.1 2010/09/03 19:03:50 a5116174 Exp $ */

#ifndef VALIDASENHA
	#define VALIDASENHA

#include <tuxfw.h>
#include <string>

#define ERR_SENHA_NAO_ENCONTRADA		0
#define ERR_SENHA_NAO_CADASTRADA		-100
#define SENHA_ENCONTRADA				1
#define ERR_SENHA_REINICIALIZADA		-101
#define ERR_USUARIO_BLOQUEADO			-3
#define ERR_SENHA_INVALIDA				-2
#define ERR_LINHA_NAO_ENCONTRADA		-1
#define ERR_SENHA_EXPIRADA				-4

using namespace std;

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
		void validarSenhaSemUsuario(char* telefone, char* senha, int* resultado);
		
		
		//informacoes de cadastro:
		int recuperarInformcaoesCadastro(string & strNumTelefone, string & strDDD);
		string m_strPrimeiroNome;
		string m_iTipoLinha;
		string m_strNome;
		string m_strEmail;
		string m_strSexo;
		string m_strDtNascimento;


};


#endif
 