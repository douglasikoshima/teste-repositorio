/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:54 $
 **/

#include "../include/cWFAtdPesqSubEstado.h"

#include "../../SubEstado/include/cWFSubEstado.h"

cWFAtdPesqSubEstado::cWFAtdPesqSubEstado(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;
}

bool cWFAtdPesqSubEstado::Executar(char **codErro,char **msgErro)
{
    ULOG_START("cWFAtdPesqSubEstado::Executar()");

    bool retorno = false;

    saida->createTag("AtendimentoPesquisaVO");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    retorno = ConsultarSubEstado(codErro,msgErro);

    saida->closeTag();

    ULOG_END("cWFAtdPesqSubEstado::Executar()");

    return retorno;
}

bool cWFAtdPesqSubEstado::ConsultarSubEstado(char **codErro,char **msgErro)
{
    ULOG_START("cWFAtdPesqSubEstado::ConsultarSubEstado()");

    //XMLGen saidaEst;
    //cWFSubEstado cwfSubEstado(entrada,&saidaEst);
    cWFSubEstado cwfSubEstado(entrada,saida);
    //int tamSaida;

    if ( !cwfSubEstado.consultarEstado() )
    {
        *msgErro = "cWFSubEstado::consultarEstado falhou execução";

        ULOG(*msgErro);
        
        ULOG_END("cWFAtdPesqSubEstado::ConsultarSubEstado()");

        return false;
    }

    //char *p = saidaEst.retrieveXML(&tamSaida);

    //if ( !tamSaida )
    //{
    //    ULOG("cWFSubEstado::consultarEstado não gerou xml de saida");
    //    ULOG_END("cWFAtdPesqSubEstado::ConsultarSubEstado()");
    //    return true;
    //}

    //ULOG("cWFSubEstado::consultarEstado gerou xml de saida com %d bytes",tamSaida);

    //saida->createTag("WFSubEstados");
    //saida->aggregateXML(p);
    //saida->closeTag();

    ULOG_END("cWFAtdPesqSubEstado::ConsultarSubEstado()");

    return true;
}
