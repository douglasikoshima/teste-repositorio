/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.114.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#include "../include/cWFAtdInboxDispo.h"

#include "../../WF_ATDATTRUSUSEM/include/cWFAtdAttrUsuSem.h"
#include "../../Usuario/include/cWFUsuario.h"

cWFAtdInboxDispo::cWFAtdInboxDispo(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

cWFAtdInboxDispo::cWFAtdInboxDispo(DOMNode *dnode, XMLGen *xml_g,char *user)
{
    entrada = dnode;
    saida = xml_g;

    if (user) setarIdUsuario(atoi(user));

    carregaDados();
}

bool cWFAtdInboxDispo::Executar()
{
    ULOG_START("cWFAtdInboxDispo::Executar()");

    bool retorno = false;
    char *p = tx.walkTree(entrada,"inDisponivelWF",0);

    if ( p ) 
    {
        bool inDisponivelWF = atoi(p) ? true : false;

        XMLString::release(&p);

        retorno = AlterarInDisponivelWF(obterIdUsuario(),inDisponivelWF);

        if ( inDisponivelWF )
        {
            cWFAtdAttrUsuSem cwfAtdAttrUsuSem(entrada,saida,obterIdUsuario());

            retorno = cwfAtdAttrUsuSem.Executar();

            if ( !retorno )
            { // Se ocorreu erro na execução do objeto, transporta a mensagem para a saida
                SetarErro(cwfAtdAttrUsuSem.ObterCodErro(),cwfAtdAttrUsuSem.ObterMsgErro());
            }
        }
    }

    ULOG_END("cWFAtdInboxDispo::Executar()");

    return retorno;
}

bool cWFAtdInboxDispo::AlterarInDisponivelWF(int idPessoaUsuario,bool inDisponivelWF)
{
    ULOG_START("cWFAtdInboxDispo::AlterarInDisponivelWF()");

    cWFUsuario cwfUsuario;

    if ( !cwfUsuario.altInDisponivelWF(idPessoaUsuario,inDisponivelWF) )
    {
        SetarErro(NULL,mensagemSimples("cWFUsuario::altInDisponivelWF falhou execução"));

        ULOG(ObterMsgErro());
        
        ULOG_END("cWFAtdInboxDispo::AlterarInDisponivelWF()");

        return false;
    }

    ULOG_END("cWFAtdInboxDispo::AlterarInDisponivelWF()");

    return true;
}

//bool cWFAtdInboxDispo::AlterarStatusUsuario(long idPessoaUsuario,int idStatusUsuario)
//{
//    tuxfw_getlogger()->debug("%s",mensagemSimples("> AlterarStatusUsuario()"));
//
//    cWFUsuario cwfUsuario;
//
//    if ( !cwfUsuario.altStatusUsuario(idPessoaUsuario,idStatusUsuario) )
//    {
//        SetarErro(NULL,mensagemSimples("cWFUsuario::altStatusUsuario falhou execução"));
//
//        tuxfw_getlogger()->warning("%s",mensagemSimples(ObterMsgErro()));
//
//        return false;
//    }
//
//    tuxfw_getlogger()->debug("%s",mensagemSimples("< AlterarStatusUsuario()"));
//
//    return true;
//}
