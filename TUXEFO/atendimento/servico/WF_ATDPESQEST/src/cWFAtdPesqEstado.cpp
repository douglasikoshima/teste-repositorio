/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:14 $
 **/

#include "../include/cWFAtdPesqEstado.h"

#include "../../Estado/include/cWFEstado.h"

cWFAtdPesqEstado::cWFAtdPesqEstado(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;
}

bool cWFAtdPesqEstado::Executar(char **codErro,char **msgErro)
{
    bool retorno = false;

    saida->createTag("AtendimentoPesquisaVO");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    retorno = ConsultarEstado(codErro,msgErro);

    saida->closeTag();

    return retorno;
}

bool cWFAtdPesqEstado::ConsultarEstado(char **codErro,char **msgErro)
{
    ULOG_START("cWFAtdPesqEstado::ConsultarEstado()");
    // XMLGen saidaEst;
    // cWFEstado cwfEstado(entrada,&saidaEst);
    cWFEstado cwfEstado(entrada,saida);
    // int tamSaida;

    if ( !cwfEstado.consultar() )
    {
        *msgErro = "cWFEstado::consultar falhou execução";

        ULOG(*msgErro);

        return false;
    }

    // char *p = saidaEst.retrieveXML(&tamSaida);

    // if ( !tamSaida )
    // {
    //     ULOG ("cWFEstado::consultar não gerou xml de saida");
    //     ULOG_END("cWFAtdPesqEstado::ConsultarEstado()");
    //     return true;
    // }

    // ULOG("cWFEstado::consultar gerou xml de saida com %d bytes" ,tamSaida);

    // saida->createTag("WFEstados");
    // saida->aggregateXML(p);
    // saida->closeTag();
    
    ULOG_END("cWFAtdPesqEstado::ConsultarEstado()");
    
    return true;
}
