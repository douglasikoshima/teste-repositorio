/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdHistorico.h"

cWFAtdHistorico::cWFAtdHistorico(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    carregaDados();
}

bool cWFAtdHistorico::executar(char **codErro,char **msgErro)
{
    ULOG_START("cWFAtdHistorico::executar()");
    
    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O valor de 'idAtendimento' é obrigatório";

        return false;
    }

    if ( m_vlDados.idSubEstado == 1 && m_vlDados.idEstado == -1 )
    {
        *msgErro = "O valor de 'idEstado' é obrigatório se fornecido valor para 'idSubEstado'";
 
        return false;
    }

    cWFAndamento cwfAndamento(&m_stDados,&m_vlDados,saida);

    saida->createTag("AtendimentoDetalheVO");
        saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns2", "admsistemas.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns3", "usuario.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns4", "cliente.fo.vivo.com.br/vo");

 	    saida->createTag("AtendimentoVO");
	        saida->addItem("idAtendimento", m_stDados.idAtendimento);

        cwfAndamento.ObtemHistAtendEstado();

	    saida->closeTag();

    saida->closeTag();

    ULOG_END("cWFAtdHistorico::executar()");
    
    return true;
}

void cWFAtdHistorico::carregaDados()
{
    ULOG_START("cWFAtdHistorico::carregaDados()");

    char *p;

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idEstado", 0 ),p) 
    {
        m_stDados.idEstado = atoi(p);
		if (m_stDados.idEstado != -1)
		{
			m_vlDados.idEstado = 1;
		}
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idSubEstado", 0 ),p) 
    {
		m_stDados.idSubEstado = atoi(p);
		if (m_stDados.idSubEstado != -1)
		{
			m_vlDados.idSubEstado = 1;
		}
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "inCRI", 0 ),p) 
    {
		m_stDados.inCRI = atoi(p);
        if ( m_stDados.inCRI ) { m_vlDados.inCRI = 1; }

		XMLString::release(&p);
    }

    ULOG("idAtendimento=%d",m_stDados.idAtendimento);
    ULOG("idEstado=%d",m_stDados.idEstado);
    ULOG("idSubEstado=%d",m_stDados.idSubEstado);

    ULOG_END("cWFAtdHistorico::carregaDados()");
}

