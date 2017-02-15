/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:00 $
 **/

#include "../include/cWFAtdPesqEstadoSub.h"

#include "../../Estado/include/cWFEstado.h"
#include "../../SubEstado/include/cWFSubEstado.h"

cWFAtdPesqEstadoSub::cWFAtdPesqEstadoSub(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;
}

bool cWFAtdPesqEstadoSub::Executar(char **codErro,char **msgErro)
{
    bool retorno = false;
    char *p;

    saida->createTag("AtendimentoPesquisaVO");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

	if ( p=tx.walkTree( entrada, "idSubEstado", 0 ),p ) 
	{
        XMLString::release(&p);

        retorno = ConsultarSubEstado();
	}
    else
    {
        retorno = ConsultarEstado();
    }

    saida->closeTag();

    return retorno;
}

bool cWFAtdPesqEstadoSub::ConsultarEstado()
{
    ULOG_START("cWFAtdPesqEstadoSub::ConsultarEstado()");
    // XMLGen saidaEst;
    // cWFEstado cwfEstado(entrada,&saidaEst);
    cWFEstado cwfEstado(entrada,saida);
    // int tamSaida;

    if ( !cwfEstado.consultar() )
    {
        ULOG("cWFEstado::consultar falhou execução"));
        ULOG_END("cWFAtdPesqEstadoSub::ConsultarEstado()");
        return false;
    }

    // char *p = saidaEst.retrieveXML(&tamSaida);

    // if ( !tamSaida )
    // {
    //     ULOG("cWFEstado::consultar não gerou xml de saida");
    //     ULOG_END("cWFAtdPesqEstadoSub::ConsultarEstado()");
    //     return true;
    // }

    // saida->createTag("WFEstados");
    // saida->aggregateXML(p);
    // saida->closeTag();
    
    ULOG_END("cWFAtdPesqEstadoSub::ConsultarEstado()");

    return true;
}

bool cWFAtdPesqEstadoSub::ConsultarSubEstado()
{
    ULOG_END("cWFAtdPesqEstadoSub::ConsultarSubEstado()");
    // XMLGen saidaEst;
    // cWFSubEstado cwfSubEstado(entrada,&saidaEst);
    cWFSubEstado cwfSubEstado(entrada,saida);
    // int tamSaida;

    if ( !cwfSubEstado.consultarEstado() )
    {
        ULOG("cWFSubEstado::consultarEstado falhou execução");
        ULOG_END("cWFAtdPesqEstadoSub::ConsultarSubEstado()");
        return false;
    }

    // char *p = saidaEst.retrieveXML(&tamSaida);

    // if ( !tamSaida )
    // {
    //     ULOG("cWFSubEstado::consultarEstado não gerou xml de saida");
    //     ULOG_END("cWFAtdPesqEstadoSub::ConsultarSubEstado()");
    //     return true;
    // }

    // ULOG("cWFSubEstado::consultarEstado gerou xml de saida com %d bytes" ,tamSaida);

    // saida->createTag("WFSubEstados");
    // saida->aggregateXML(p);
    // saida->closeTag();

    ULOG_END("cWFAtdPesqEstadoSub::ConsultarSubEstado()");
    return true;
}
