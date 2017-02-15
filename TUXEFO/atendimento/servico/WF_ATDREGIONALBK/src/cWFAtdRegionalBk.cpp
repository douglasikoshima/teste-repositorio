/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:00 $
 **/

#include "../include/cWFAtdRegionalBk.h"

#include "../../TabelaUFOperadora/include/cWFTabUFOperadora.h"

cWFAtdRegionalBk::cWFAtdRegionalBk(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdRegionalBk::Executar()
{
    saida->createTag("WFRelatoriosFiltrosVO");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    bool retorno = PesquisarTabelaUFRegional();

    saida->closeTag();

    return retorno;
}

bool cWFAtdRegionalBk::PesquisarTabelaUFRegional()
{
    cWFTabUFOper cwfTabUFOper;

    

	bool retorno = cwfTabUFOper.PesquisarTabelaUFRegional(entrada,saida);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFTabUFOper::PesquisarTabelaUFRegional falhou!");
        ULOGE(ObterMsgErro());
    }

    return retorno;
}
