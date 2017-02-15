/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:00 $
 **/

#include <tuxfw.h>

#include "../include/stWFAtdPesqEstSub.h"
#include "../include/cWFAtdPesqEstSub.h"

extern void proCConsultaWFEstadoSubEstado(st_AtdPesqEstSub *dados
                                         ,st_vlAtdPesqEstSub *status
                                         ,XMLGen *saida);

extern void proCConsultaWFEstadoSubEstadoPo(st_AtdPesqEstSub *dados
                                           ,st_vlAtdPesqEstSub *status
                                           ,XMLGen *saida);

cWFAtdPesqEstSub::cWFAtdPesqEstSub(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdPesqEstSub::Executar()
{
    ULOG_START("cWFAtdPesqEstSub::Executar()");

    if ( inPortOut == false )
    {
        proCConsultaWFEstadoSubEstado(&m_stDados,&m_vlDados,saida);
    }
    else
    { // pesquisa estado/sub-estado especifico para port-out
        proCConsultaWFEstadoSubEstadoPo(&m_stDados,&m_vlDados,saida);
    }

    ULOG_END("cWFAtdPesqEstSub::Executar()");

    return true;
}

void cWFAtdPesqEstSub::carregaDados()
{
    ULOG_START("cWFAtdPesqEstSub::carregaDados()");

	char* p;

    memset(&m_stDados,0,sizeof(st_AtdPesqEstSub));
	memset(&m_vlDados,-1,sizeof(st_vlAtdPesqEstSub));

	if ( p = tx.walkTree(entrada,"idEstado",0), p ) 
	{
		m_stDados.idEstado = atoi( p );
		m_vlDados.idEstado = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree(entrada,"idSubEstado",0), p ) 
	{
		m_stDados.idSubEstado = atoi( p );
		m_vlDados.idSubEstado = 1;
		XMLString::release(&p);
	}

    inPortOut = false;

	if ( p=tx.walkTree( entrada, "inPortout", 0 ),p ) 
	{
        XMLString::release(&p);
        inPortOut = true;
	}

    ULOG_END("cWFAtdPesqEstSub::carregaDados()");
}
