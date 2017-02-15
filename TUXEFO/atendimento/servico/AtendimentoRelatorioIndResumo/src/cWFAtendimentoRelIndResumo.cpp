/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:44 $
 **/

#include "../include/cWFAtendimentoRelIndResumo.h"
#include "../../../commons/msgPadrao.h"

extern bool proCExecutarRelatorioRes( XMLGen *saida, DOMNode *dnode );

cWFAtdRelRes::cWFAtdRelRes(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdRelRes::Executar()
{
    return proCExecutarRelatorioRes( saida, entrada );
}
