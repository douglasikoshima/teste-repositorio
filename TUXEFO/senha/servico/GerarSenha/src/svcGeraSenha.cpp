/* $Id: svcGeraSenha.cpp,v 1.1.2.1 2010/07/14 23:42:04 a5114878 Exp $ */

#include <tuxfw.h>
#include "../../AtualizaCadastro/include/AtualizaCadastro.h"

DECLARE_TUXEDO_SERVICE(GeraSenha);

bool proCBuscaCPFUsuario(int idPessoa, char* cpf);

// Esses define são a tipologia dos dados constantes nas tabelas relacionadas
// para o tipo de servico a ser executado.
#define IDTIPOHISTORICOSENHA    1
#define IDCANAL         6
#define IDTIPOSISTEMA       1

/*******************************************************
* Esse sevico busca o cpf da pessoa informada, os quatro
* primeiros digitos são usados como senha.
******************************************************/
void implGeraSenha::Execute(DOMNode*dnode,XMLGen*xml_g)
{

   ULOG_START("implDesSenha::Execute()");
   
    char telefone[11];
    int idPessoa =  atoi(walkTree( dnode, "idPessoa", 0 ));
    long idPessoaLinha =     atol(walkTree( dnode, "idPessoaLinha", 0 ));
    int idPessoaUsr =   atoi(walkTree( dnode, "idPessoaUsr", 0 ));
    strcpy( telefone,     walkTree( dnode, "telefone", 0 ) );
    char cpf[5];
    int idPessoaCpf;

    if (idPessoaLinha > 0)
        idPessoaCpf = idPessoaUsr;
    else
        idPessoaCpf = idPessoa;
        
    if( proCBuscaCPFUsuario(idPessoaCpf, cpf) ) 
    {

        int idPessoaUsuario = atoi(getUser());

        AtualizaCadastro aC;
    
        if (aC.criaSenhaPessoa(idPessoa, idPessoaLinha, telefone, cpf, IDTIPOHISTORICOSENHA, IDCANAL, IDTIPOSISTEMA, idPessoaUsuario))
        {
            setStatusCode("07I0000","A senha foi gerada");
        }
        else
        {
            setStatusCode("07E0002","A senha não foi gerada.");
        }

    } else {

        setStatusCode("07E0001","Pessoa não possui CPF.");
    }
    
    ULOG_END("implDesSenha::Execute()");

}