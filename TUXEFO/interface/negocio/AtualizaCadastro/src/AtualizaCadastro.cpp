/* $Id: AtualizaCadastro.cpp,v 1.1 2009/07/31 15:33:41 a5110702 Exp $ */

#include <tuxfw.h>
#include "../include/AtualizaCadastro.h"

extern int  proCTipoPessoa(int idPessoa);
extern int  proCSenhaValida(int idPessoa, char* telefone, char* senha, int tipoPessoa);
extern bool proCCriaSenhaPessoa(int idPessoa, int idPessoaLinha, char* senha, int tipoPessoa, int idTipoHistoricoSenha, int idCanal, int idTipoSistema, int idPessoaUsuario);
extern bool proCAlteraSenha(stAlteraSenha *m_stDados);
extern int  proCAlteraFrase(stAlteraSenha *m_stDados);
extern bool proCVerificaSenhasIguaisCliUsu( char* telefone, char* senha, int tipo);

#define TIPOUSUARIO 1
#define TIPOCLIENTE 2
#define TIPOUSUCLI  3

AtualizaCadastro::AtualizaCadastro()
{

}

bool AtualizaCadastro::criaSenhaPessoa(int idPessoa, int idPessoaLinha, char* telefone, char* senha, int idTipoHistoricoSenha, int idCanal, int idTipoSistema, int idPessoaUsuario)
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

