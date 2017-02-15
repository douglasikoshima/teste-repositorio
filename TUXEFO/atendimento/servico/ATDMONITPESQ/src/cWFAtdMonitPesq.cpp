/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:35 $
 **/

#include "../include/cWFAtdMonitPesq.h"

#include "../../../commons/msgPadrao.h"

extern void proCObterMonitPesq( st_AtdMonitPesq *dados,st_vlAtdMonitPesq *status,XMLGen *saida );

cWFAtdMonitPesq::cWFAtdMonitPesq(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdMonitPesq::executar(char **codErro,char **msgErro)
{
    if ( m_vlDados.dtInicio == -1 )
    {
        *msgErro = "O valor de 'dtInicio' é obrigatório";

        return false;
    }

    m_stDados.idUsuario = obterIdUsuario();
    m_vlDados.idUsuario = 1;

    proCObterMonitPesq(&m_stDados,&m_vlDados,saida);

    return true;
}

void cWFAtdMonitPesq::carregaDados()
{
    ULOG_START( "cWFAtdMonitPesq::carregaDados()" );

    char *p;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if (p=tx.walkTree( entrada, "dtInicio", 0 ),p) 
    {
        sprintf(m_stDados.dtInicio,"%.*s",sizeof(m_stDados.dtInicio)-1,p);
        m_vlDados.dtInicio = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "dtFim", 0 ),p) 
    {
        sprintf(m_stDados.dtFim,"%.*s",sizeof(m_stDados.dtFim)-1,p);
        m_vlDados.dtFim = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idGrupo", 0 ),p) 
    {
        m_stDados.idGrupo = atoi(p);
        m_vlDados.idGrupo = 1;
        XMLString::release(&p);
    }

    ULOG( "dtInicio='%s'",m_stDados.dtInicio );
    ULOG( "dtFim='%s'",m_stDados.dtFim );
    ULOG( "idGrupo=%d",m_stDados.idGrupo );

    ULOG_END( "cWFAtdMonitPesq::carregaDados()" );

}

