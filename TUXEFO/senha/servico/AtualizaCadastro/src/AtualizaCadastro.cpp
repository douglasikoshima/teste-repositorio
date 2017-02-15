/* $Id: AtualizaCadastro.cpp,v 1.1.2.8.6.3 2011/09/15 17:22:18 a5116174 Exp $ */

#include <tuxfw.h>
#include "../include/AtualizaCadastro.h"

extern int  proCTipoPessoa(int idPessoa);
extern int  proCSenhaValida(int idPessoa, char* telefone, char* senha, int tipoPessoa);
extern bool proCCriaSenhaPessoa(int idPessoa, long idPessoaLinha, char* senha, int tipoPessoa, int idTipoHistoricoSenha, int idCanal, int idTipoSistema, int idPessoaUsuario);
extern bool proCAlteraSenha(stAlteraSenha *m_stDados);
extern int  proCAlteraFrase(stAlteraSenha *m_stDados);
extern bool proCVerificaSenhasIguaisCliUsu( char* telefone, char* senha, int tipo);
extern int  obterPessoaUsuario(int, int) ;
extern int  obterPessoaCliente(int, int) ;
extern bool proCVerificaUltimaSenha(char * strSenha, char * idPessoa, char * idTipoRelacionamento );

#include "../../DesbloqueiaSenha/include/DesbloqueiaSenha.h"
extern void proCDesbloqueiaSenhas(char*, int, int, int, char*);

extern bool verificaSenhaBloqueadaPC(char* telefone);


#define TIPOUSUARIO 1
#define TIPOCLIENTE 2
#define TIPOUSUCLI  3

AtualizaCadastro::AtualizaCadastro()
{

}
int  AtualizaCadastro::consultarIdPessoa(char* telefone , int iIdTipoRelacionamento)
{

	int prefixo = 0;
	int numero = 0;
	char cConv[3];


	if (telefone[0]=='0' ) 
	{
		strncpy(cConv, telefone+1,2);cConv[2]=0;
		prefixo = atoi(cConv);
		numero	= atoi(telefone+3);
	}
	else
	{
		strncpy(cConv, telefone,2);cConv[2]=0;
		prefixo = atoi(cConv);
		numero	= atoi(telefone+2);
	}

	
	if ( iIdTipoRelacionamento == 1 )
		return 	obterPessoaUsuario(prefixo, numero );

	else
		return 	obterPessoaCliente (prefixo, numero);


}


bool AtualizaCadastro::criaSenhaPessoa(int idPessoa, long idPessoaLinha, char* telefone, char* senha, int idTipoHistoricoSenha, int idCanal, int idTipoSistema, int idPessoaUsuario)
{

    int tipo = 0;
    if (idPessoaLinha > 0)
        tipo = 1;
    else
        tipo = 2;
    int valida = senhaValida( idPessoa, telefone, senha, tipo );

    if ( valida == 1) 
    {

        proCCriaSenhaPessoa(idPessoa, idPessoaLinha, senha, tipo, idTipoHistoricoSenha, idCanal, idTipoSistema, idPessoaUsuario);

        return true;

    } 

    return false;
}

int AtualizaCadastro::alteraSenha(stAlteraSenha* m_stDados) {

    ULOG("Telefone en CPP: %s", m_stDados->telefone);
    bool retorno;
    char idPessoa[41];
    char idTipoRelacionamento[2];
    sprintf( idPessoa, "%d", m_stDados->idPessoa );
    sprintf( idTipoRelacionamento, "%d", m_stDados->titularidadeSenha );
    
    retorno = proCVerificaUltimaSenha( m_stDados->cdSenha, idPessoa, idTipoRelacionamento ); // Verifica 5 ultimas senhas digitadas

    if ( retorno == false )
    {
        if (strlen (m_stDados->cdSenha) != 0){ //não precisa validar em caso de reinicializar senha com nulo

            int valida = senhaValida( m_stDados->idPessoa, m_stDados->telefone, m_stDados->cdSenha, m_stDados->titularidadeSenha );

            if (valida != 1)
            {
                ULOG("Valida (cpp): %d", valida);   
                return valida;
            }
        }
        return proCAlteraSenha(m_stDados);
    }
    else
       return -11;
}

int AtualizaCadastro::alteraFrase(stAlteraSenha* m_stDados) {

    ULOG("Telefone en CPP: %s", m_stDados->telefone);
    int valida = 1;

    if (valida != 1)
    {
        ULOG("Valida (cpp): %d", valida);   
        return valida;
    }
    return proCAlteraFrase(m_stDados);

}


/**
* Esse método é responsável por validar a senha informada de acordo
* com as regras especificadas.
*/
int AtualizaCadastro::senhaValida( int idPessoa, char* telefone, char* senha, int tipo ) {

    
    //Caso as pessoas (cliente e usuario) sejam distintas e senha iguais, então retorna senha inválida (-3)
    if (proCVerificaSenhasIguaisCliUsu(telefone, senha, tipo) == true)
        return -3;
        
        
    return proCSenhaValida(idPessoa, telefone, senha, tipo);


}



int AtualizaCadastro::tipoPessoa( int idPessoa )
{
    return proCTipoPessoa( idPessoa );
}

bool AtualizaCadastro::verificaSenhaBloqueada(char* telefone)
{
   ULOG_START("AtualizaCadastro::verificaSenhaBloqueada");
   
   bool bReturn = false;

		bReturn = verificaSenhaBloqueadaPC(telefone);

   ULOG_START("AtualizaCadastro::verificaSenhaBloqueada");
   
   return bReturn;
}

void AtualizaCadastro::desbloqueiaSenhas(char* telefone, int idCanal, int idPessoaUsuario, int iIdTerminal, char* obsRegistro)
{
	ULOG("Entrada em desbloqueiaSenhas(): telefone [%s], idCanal [%d], idPessoaUsuario [%d], iIdTerminal [%d], obsRegistro [%s]", telefone, idCanal, idPessoaUsuario, iIdTerminal, obsRegistro);
	proCDesbloqueiaSenhas(telefone, idCanal, idPessoaUsuario, iIdTerminal, obsRegistro);
	return;
}

