/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:23 $
 **/

#include "../include/cWFTabTipoCarteira.h"

extern bool proCPesquisaTabelaTipoCarteira( DOMNode*entrada,XMLGen* saida );

cWFTabTipoCarteira::cWFTabTipoCarteira(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFTabTipoCarteira::PesquisarTabelaTipoCarteira(DOMNode*entrada, XMLGen* saida)
{
    return proCPesquisaTabelaTipoCarteira( entrada, saida );
}

bool cWFTabTipoCarteira::PesquisarTabelaTipoCarteira()
{
    return proCPesquisaTabelaTipoCarteira( entrada, saida );
}

