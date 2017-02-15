/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.118.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#include "../include/cWFAtdInboxDisp.h"

#include "../../WF_ATDATTRUSUSEM/include/cWFAtdAttrUsuSem.h"
#include "../../Usuario/include/cWFUsuario.h"

cWFAtdInboxDisp::cWFAtdInboxDisp(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

cWFAtdInboxDisp::cWFAtdInboxDisp(DOMNode *dnode, XMLGen *xml_g,char *user)
{
    entrada = dnode;
    saida = xml_g;

    if (user) setarIdUsuario(atoi(user));

    carregaDados();
}

bool cWFAtdInboxDisp::Executar()
{
    ULOG_START("cWFAtdInboxDisp::Executar()");

    bool retorno = false;
    char *p = tx.walkTree(entrada,"inDisponivelWF",0);

    if ( p ) 
    {
        bool inDisponivelWF = atoi(p) ? true : false;

        XMLString::release(&p);

        retorno = AlterarInDisponivelWF(obterIdUsuario(),inDisponivelWF);

        // =======================================================================
        // Este serviço foi dividido em 2 a partir de 10/04/06 pois como possui
        // duas funcionalidades, uma usada pelo sistema como um todo para apenas
        // alterar o status do usuário e outra para inserir processos no inbox de
        // um usuário, sendo a primeira uma funcionalidade transacional leve e a 
        // segunda pesada. A parte leve ficou a cargo do serviço ATDINBOXDISP e a
        // pesada ficou a cargo da ATDINBOXDISPO. - Abril 2006, Cassio.
        // =======================================================================
        //
        //if ( inDisponivelWF )
        //{
        //    cWFAtdAttrUsuSem cwfAtdAttrUsuSem(entrada,saida,obterIdUsuario());
        //
        //    retorno = cwfAtdAttrUsuSem.Executar();
        //
        //    if ( !retorno )
        //    { // Se ocorreu erro na execução do objeto, transporta a mensagem para a saida
        //        SetarErro(cwfAtdAttrUsuSem.ObterCodErro(),cwfAtdAttrUsuSem.ObterMsgErro());
        //    }
        //}
    }

    ULOG_END("cWFAtdInboxDisp::Executar()");

    return retorno;
}

bool cWFAtdInboxDisp::AlterarInDisponivelWF(int idPessoaUsuario,bool inDisponivelWF)
{
    ULOG_START("cWFAtdInboxDisp::AlterarInDisponivelWF()");

    cWFUsuario cwfUsuario;

    if ( !cwfUsuario.altInDisponivelWF(idPessoaUsuario,inDisponivelWF) )
    {
        SetarErro(NULL,mensagemSimples("cWFUsuario::altInDisponivelWF falhou execução"));

        ULOG(ObterMsgErro());
        
        ULOG_END("cWFAtdInboxDisp::AlterarInDisponivelWF()");

        return false;
    }

    ULOG_END("cWFAtdInboxDisp::AlterarInDisponivelWF()");

    return true;
}

//bool cWFAtdInboxDisp::AlterarStatusUsuario(long idPessoaUsuario,int idStatusUsuario)
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
