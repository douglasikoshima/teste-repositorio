/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:06 $
 **/

#include "../include/cWFAtExcFisica.h"

#include "../../AtendimentoAndamentoAtual/include/cWFAtendimentoAndamentoAtual.h"

cWFAtExcFisica::cWFAtExcFisica(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtExcFisica::Executar()
{
    return ExcluirAtdAndaAtual();
}

bool cWFAtExcFisica::ExcluirAtdAndaAtual()
{
    ULOG_START("cWFAtExcFisica::ExcluirAtdAndaAtual()");
    
    cWFAtendimentoAndamentoAtual cwfAtendimentoAndamentoAtual(entrada,saida);

	int retorno = cwfAtendimentoAndamentoAtual.excluir();

    if ( retorno )
    {
        if ( retorno == -1 )
        {
            SetarErro(NULL,"Valor do 'idAtendimento' é obrigatório e não foi fornecido");
        }
        else
        {
            SetarErro(NULL,"cWFAtendimentoAndamentoAtual::ExcluirAtdAndaAtual falhou!");
        }

        ULOG(ObterMsgErro());
    }

    ULOG_END("cWFAtExcFisica::ExcluirAtdAndaAtual()");
    return retorno ? false : true;
}
