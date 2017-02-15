/* $Id: AtualizaCadastro.h,v 1.1 2009/07/31 15:33:35 a5110702 Exp $ */

#ifndef ATUALIZACADASTRO
	#define ATUALIZACADASTRO

#include <tuxfw.h>
#include "stAlteraSenha.h"

typedef struct 
{

	int  idSenha;
	int  idPessoaLinha;
	int  idTipoStatusSenha;
	int  idTipoSenha;
	int  idPessoa;
	char cdSenha[255];
	char dtPrimeiroAcesso[9]; // yyyymmdd
	char dtUltimoAcesso[9];   
	char dtUltimaAtualizacao[9];

} stAtualizaCadastro;

class AtualizaCadastro
{
		
	public:
		stAtualizaCadastro	m_stAtlys;
		AtualizaCadastro();
		bool criaSenhaPessoa(int idPessoa, int idPessoaLinha, char* telefone, char* senha, int idTipoHistoricoSenha, int idCanal, int idTipoSistema, int idPessoaUsuario);
		int alteraSenha(stAlteraSenha* m_stDados);
	private:
		int senhaValida( int idPessoa, char* telefone, char* senha, int tipo );
		int tipoPessoa( int idPessoa );

};


#endif