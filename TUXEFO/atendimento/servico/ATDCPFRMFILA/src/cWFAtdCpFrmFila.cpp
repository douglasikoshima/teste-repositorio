/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:37 $
 **/

#include "../include/cWFAtdCpFrmFila.h"

#include "../../../commons/msgPadrao.h"

extern void proCObterCamposFormulario(XMLGen *saida);
extern void proCObterTiposLinha(XMLGen *saida);
extern void proCObterUfOperadoras(XMLGen *saida);

cWFAtdCpFrmFila::cWFAtdCpFrmFila(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdCpFrmFila::executar(char **codErro,char **msgErro)
{
    saida->createTag("FormularioVO");
    saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo");


    proCObterCamposFormulario(saida);

    proCObterTiposLinha(saida);

    proCObterUfOperadoras(saida);

    saida->closeTag();

    return true;
}

