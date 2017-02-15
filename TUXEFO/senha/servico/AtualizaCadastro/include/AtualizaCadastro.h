/* $Id: AtualizaCadastro.h,v 1.1.2.4 2011/02/22 13:42:20 a5116174 Exp $ */

#ifndef ATUALIZACADASTRO
	#define ATUALIZACADASTRO

#include <tuxfw.h>
#include "stAlteraSenha.h"

typedef struct 
{

	int  idSenha;
	long  idPessoaLinha;
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
		bool criaSenhaPessoa(int idPessoa, long idPessoaLinha, char* telefone, char* senha, int idTipoHistoricoSenha, int idCanal, int idTipoSistema, int idPessoaUsuario);
		int alteraSenha(stAlteraSenha* m_stDados);
		int alteraFrase(stAlteraSenha* m_stDados);
		int consultarIdPessoa(char* telefone , int iIdTipoRelacionamento);
		
		// ******* OS 1511 INICIO ********** //
		bool verificaSenhaBloqueada(char* telefone);
		void desbloqueiaSenhas(char*, int, int, int, char*);
		// ******* OS 1511 FIM    ********** //
		
	private:
		int senhaValida( int idPessoa, char* telefone, char* senha, int tipo );
		int tipoPessoa( int idPessoa );

};


#endif