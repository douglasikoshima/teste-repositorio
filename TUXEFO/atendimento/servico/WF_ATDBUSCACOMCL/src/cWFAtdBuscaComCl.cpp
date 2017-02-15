/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:51 $
 **/

#include "../include/cWFAtdBuscaComCl.h"

#include "../../Atendimento/include/cWFAtendimento.h"

cWFAtdBuscaComCl::cWFAtdBuscaComCl(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdBuscaComCl::Executar(char **codErro,char **msgErro)
{
    bool retorno = false;

    saida->createTag("AtendimentoVO");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    retorno = ObterPessoaComunic();

    saida->closeTag();

    return retorno;
}

bool cWFAtdBuscaComCl::ObterPessoaComunic()
{
    ULOG_END("cWFAtdBuscaComCl::ObterPessoaComunic()"); 
    cWFAtendimento cwfAtendimento(entrada,saida);

    if ( !cwfAtendimento.ObtemPessoaComunic() )
    {
        SetarErro(NULL,"cWFAtendimento::ObtemPessoaComunic falhou execução");

        ULOGE(ObterMsgErro());
        ULOG_END("cWFAtdBuscaComCl::ObterPessoaComunic()"); 
        return false;
    }
    ULOG_END("cWFAtdBuscaComCl::ObterPessoaComunic()"); 
    return true;
}

