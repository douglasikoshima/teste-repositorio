/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:09 $
 **/

#include "../include/cWFAtdRelNaoEncam.h"
#include "../../../commons/msgPadrao.h"


extern bool proCExecutarRelatorio( st_AtendimentoRel *dados
                                  , st_vlAtendimentoRel *status
                                  , XMLGen *saida);

cWFAtdRelNaoEncam::cWFAtdRelNaoEncam(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdRelNaoEncam::Executar()
{
    return proCExecutarRelatorio( &m_stDados
                                 , &m_vlDados
                                 , saida );
}

void cWFAtdRelNaoEncam::carregaDados()
{
    char *p;

    // Inicializa as estruturas de dados para armazenar os valores
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if (p=tx.walkTree( entrada, "idGrupo", 0 ),p) 
    {
	    if (strlen(p) > 0)
	    {
	            m_stDados.idGrupo = atoi(p);
	            m_vlDados.idGrupo = 1;
	    }
	    XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idContato", 0 ),p) 
    {
	    if (strlen(p) > 0)
	    {
	            m_stDados.idContato = atoi(p);
	            m_vlDados.idContato = 1;
	    }
	    XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "dtInicio", 0 ),p) 
    {
	    if (strlen(p) > 0)
	    {
		    sprintf(m_stDados.dtInicio,"%.*s",sizeof(m_stDados.dtInicio)-1, p);
	            m_vlDados.dtInicio = 1;
	    }
	    XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "dtFim", 0 ),p) 
    {
	    if (strlen(p) > 0)
	    {
	            sprintf(m_stDados.dtFim,"%.*s",sizeof(m_stDados.dtFim)-1, p);
	            m_vlDados.dtFim = 1;
	    }
	    XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "inTotal", 0 ),p) 
    {
        m_stDados.bloco = atoi(p);
        m_vlDados.bloco = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "qtdLinhasBloco", 0 ),p) // num linhas no bloco
    {
        m_stDados.qtdLinhasBloco = atoi(p);
        m_vlDados.qtdLinhasBloco = 1;
        XMLString::release(&p);
    }
    else
    {
        m_stDados.qtdLinhasBloco = NUM_LINHAS;
    }
    
    ULOG( "idGrupo=%d",m_vlDados.idGrupo == 1 ? m_stDados.idGrupo: -1);

    ULOG( "idContato=%d",m_vlDados.idContato == 1 ? m_stDados.idContato: -1);

    ULOG( "dtInicio=%s",m_vlDados.dtInicio == 1 ? m_stDados.dtInicio: "data inicial nao informada");

    ULOG( "dtFim=%s",m_vlDados.dtFim == 1 ? m_stDados.dtFim: "data final nao informada");
}
