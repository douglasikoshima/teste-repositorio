/* $Id: ReiniciaSenha.cpp,v 1.1.118.1 2012/07/30 17:36:22 a5114878 Exp $ */

#include <tuxfw.h>
#include "../include/ReiniciaSenha.h"
#include "../../AtualizaCadastro/include/stAlteraSenha.h"
#include "../../AtualizaCadastro/include/AtualizaCadastro.h"
#include "../../ValidaSenha/include/ValidaSenha.h"

extern bool proCCPFUsuario(int idPessoa, char *cpf);
extern void proCDesbloqueiaSenhas(char*, int, int, int, char*);
#define PJ	2

ReiniciaSenhaC::ReiniciaSenhaC()
{

}

int ReiniciaSenhaC::reiniciarSenha(int idPessoa, int idPessoaUsr, char* telefone, int tpTitularidade, int idCanal, int idPessoaUsuario, int iIdTerminal, char* obsRegistro )
{
   ULOG_START("ReiniciaSenhaC::reiniciarSenha()");
   int iTipoPessoa = 0;
   int iNrLinha = 0;
   int iCdAreaRegistro  = 0;
   char c_area[2] = "";
   char c_linha[16] = "";
//	char cpf[5];

//	if (proCCPFUsuario(idPessoaUsr, cpf)) 
//	{

		struct stAlteraSenha m_stDados;

		memset(&m_stDados,0,sizeof(m_stDados));

//		strcpy(m_stDados.cdSenha, cpf);
		strcpy(m_stDados.cdSenha, "" );
		strcpy(m_stDados.telefone, telefone);

		m_stDados.titularidadeSenha		= tpTitularidade;
		m_stDados.idPessoa				= idPessoa;
		m_stDados.idCanal				= idCanal;
		m_stDados.idTipoSistema			= 2;
		m_stDados.idTipoHistoricoSenha	= 5;
		m_stDados.registraHistorico		= 1; // Registra essa operação de forma direta.
		m_stDados.idPessoaUsuario		= idPessoaUsuario;
		m_stDados.inTrocaSenha   		= 1;
		m_stDados.iIdTerminal			= iIdTerminal;
		strcpy( m_stDados.obsRegistro, obsRegistro );

		AtualizaCadastro aC;

		ValidaSenhaC vS;
		
		sprintf( c_area, "%.2s", telefone );
		sprintf( c_linha, "%s", (char*)&telefone[2] );
	
		iNrLinha = atoi(c_linha);	
		iCdAreaRegistro = atoi(c_area);	
	
		iTipoPessoa = vS.consultarTipoPessoa(iNrLinha, iCdAreaRegistro, tpTitularidade);	

		if (iTipoPessoa == PJ){ //não é permitido reinicializar senha de cliente ou usuário (PJ)
			ULOG("não é permitido reinicializar senha de cliente ou usuário PJ");
			ULOG_END("ReiniciaSenhaC::reiniciarSenha()");
			return -5;
		}
				
		if ( aC.alteraSenha( &m_stDados ) < 0 )
		{
			ULOG("Valida (cpp) - Para retorno");	
			ULOG_END("ReiniciaSenhaC::reiniciarSenha()");
			return -2;
		}

		// Realiza o desbloqueio dos usuários.
		proCDesbloqueiaSenhas(telefone, idCanal, idPessoaUsuario, iIdTerminal, obsRegistro);

//	}
//	else
//	{
//		return -1;
//	}

   ULOG_END("ReiniciaSenhaC::reiniciarSenha()");
	return 1;
}

int ReiniciaSenhaC::reiniciarFrase(int idPessoa, int idPessoaUsr, char* telefone, int tpTitularidade, int idCanal, int idPessoaUsuario, int iIdTerminal, char* obsRegistro )
{
   ULOG_START("ReiniciaSenhaC::reiniciarFrase()");

	struct stAlteraSenha m_stDados;

	memset(&m_stDados,0,sizeof(m_stDados));

	strcpy(m_stDados.telefone, telefone);

	m_stDados.titularidadeSenha		= tpTitularidade;
	m_stDados.idPessoa				= idPessoa;
	m_stDados.idCanal				= idCanal;
	m_stDados.idTipoSistema			= 2;
	m_stDados.idTipoHistoricoSenha	= 13;
	m_stDados.registraHistorico		= 1; // Registra essa operação de forma direta.
	m_stDados.idPessoaUsuario		= idPessoaUsuario;
	m_stDados.iIdTerminal			= iIdTerminal;
	m_stDados.inTrocaSenha   		= 1;
	strcpy( m_stDados.obsRegistro, obsRegistro );

	AtualizaCadastro aC;
		
	if ( aC.alteraFrase( &m_stDados ) < 0 )
	{
		ULOG("Valida (cpp) - Para retorno");	
		ULOG_END("ReiniciaSenhaC::reiniciarFrase()");
		return -2;
	}
	ULOG_END("ReiniciaSenhaC::reiniciarFrase()");
	return 1;
}
