/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:36 $
 **/

#include "../include/cWFTabSegmentacao.h"

extern bool proCPesquisaTabelaSegmentacao( DOMNode*entrada, XMLGen* saida );

cWFTabSegment::cWFTabSegment(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFTabSegment::PesquisarTabelaSegmentacao(DOMNode*entrada, XMLGen* saida)
{
    return proCPesquisaTabelaSegmentacao( entrada, saida );
}

bool cWFTabSegment::PesquisarTabelaSegmentacao()
{
    return proCPesquisaTabelaSegmentacao( entrada, saida );
}

