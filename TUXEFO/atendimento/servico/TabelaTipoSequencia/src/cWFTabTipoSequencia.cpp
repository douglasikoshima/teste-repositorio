/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:06 $
 **/

#include "../include/cWFTabTipoSequencia.h"

extern bool proCPesquisaTabelaTipoSequencia( DOMNode*entrada,XMLGen* saida );

cWFTabTipoSequencia::cWFTabTipoSequencia(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFTabTipoSequencia::PesquisarTabelaTipoSequencia(DOMNode*entrada, XMLGen* saida)
{
    return proCPesquisaTabelaTipoSequencia( entrada, saida );
}

bool cWFTabTipoSequencia::PesquisarTabelaTipoSequencia()
{
    return proCPesquisaTabelaTipoSequencia( entrada, saida );
}
