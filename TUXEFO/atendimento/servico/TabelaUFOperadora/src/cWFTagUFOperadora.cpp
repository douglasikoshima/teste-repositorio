/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:20 $
 **/


#include "../include/cWFTabUFOperadora.h"

extern bool proCPesquisaTabelaUFRegional(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaOperadoras(DOMNode*entrada,XMLGen* saida);

cWFTabUFOper::cWFTabUFOper(DOMNode* dnode, XMLGen* xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFTabUFOper::PesquisarTabelaUFRegional(DOMNode*entrada,XMLGen* saida)
{
    return proCPesquisaTabelaUFRegional(entrada,saida);
}

bool cWFTabUFOper::PesquisarTabelaUFRegional()
{
    return proCPesquisaTabelaUFRegional(entrada,saida);
}

bool cWFTabUFOper::PesquisarOperadoras(DOMNode*entrada,XMLGen* saida)
{
    return proCPesquisaOperadoras(entrada,saida);
}

bool cWFTabUFOper::PesquisarOperadoras()
{
    return proCPesquisaOperadoras(entrada,saida);
}
