/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdDetalhe.h"

extern bool proCConsultaWFMigracaoAcsPC( char* sIdProcesso,  XMLGen *xml_g );

cWFAtdDetalhe::cWFAtdDetalhe(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    carregaDados();
}

bool cWFAtdDetalhe::executar(char **codErro,char **msgErro)
{
    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O código de 'idAtendimento' é obrigatório";

        return false;
    }

    proCConsultaWFMigracaoAcsPC( m_stDados.idProcesso, saida );

	return true;
}


void cWFAtdDetalhe::carregaDados()
{
    char *p;

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
        
		strcpy(m_stDados.idProcesso, p);
        m_vlDados.idProcesso = 1;
		
		XMLString::release(&p);
    }

    ULOG("idAtendimento=%d",m_stDados.idAtendimento);
}

