/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:56 $
 **/

#include "../include/cAndaObservacao.h"
#include "../../AndamentoObservacao/include/cWFAndamentoObservacao.h" 

#include "../../../commons/msgPadrao.h"

cAndaObs::cAndaObs(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;
}

bool cAndaObs::executar(char **codErro,char **msgErro)
{
    //int tamSaida;

    ULOG( "Executando cAndaObs::executar()" );

    cWFAndamentoObservacao cwfAndamentoObservacao(entrada, saida);

    return cwfAndamentoObservacao.consultarComentarioHist();

    // Refactoring - 30/11/2004
    //if ( cwfAndamentoObservacao.consultar() )
    //{
    //    char *result = saida->retrieveXML(&tamSaida);

    //    if ( tamSaida < 1 )
    //    {
    //        saida->createTag("WFAndamentoObservacaoVO");
    //            saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
    //            saida->createTag("dsAndamentoObservacao");
    //            saida->closeTag();
    //        saida->closeTag();

    //   }

    //   return true;
    //}

    //return false;
}
