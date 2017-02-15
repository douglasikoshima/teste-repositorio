/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 


#include "../include/cWFDocumentoTecnico.h"

extern bool proCIncluirWFDocumentoTecnico(st_DocumentoTecnico* dados, st_vlDocumentoTecnico* status, XMLGen* saida);
extern bool proCAlterarWFDocumentoTecnico(st_DocumentoTecnico* dados, st_vlDocumentoTecnico* status, XMLGen* saida);
extern bool proCExcluirWFDocumentoTecnico(st_DocumentoTecnico* dados, st_vlDocumentoTecnico* status, XMLGen* saida);
extern bool proCConsultaWFDocumentoTecnico(st_DocumentoTecnico* dados, st_vlDocumentoTecnico* status, char* order, XMLGen* saida);
extern bool proCConsultaWFDocumentoAtendimento(long idAtendimento, XMLGen* saida);
extern bool proCTodosDocumentosTecnicos(XMLGen* saida);
extern bool proCSelecaoDocumentosTecnicos(st_DocumentoTecnico* dados, st_vlDocumentoTecnico* status, char * dtInicio , char * dtFinal, char *idAssociados, XMLGen * saida );
extern bool proCFecharWFDocumentoTecnico(st_DocumentoTecnico* dados, st_vlDocumentoTecnico* status, XMLGen*saida);
extern bool proCValidarWFDocumentoTecnico( char *sNrDocumento );


cWFDocumentoTecnico::cWFDocumentoTecnico(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cWFDocumentoTecnico::cWFDocumentoTecnico(XMLGen*xml_g)
{
	saida   = xml_g;
}

bool cWFDocumentoTecnico::incluir()
{
	return proCIncluirWFDocumentoTecnico(&m_stDados, &m_vlDados, saida);
}

bool cWFDocumentoTecnico::incluir(st_DocumentoTecnico dados, st_vlDocumentoTecnico status)
{
	return proCIncluirWFDocumentoTecnico(&dados, &status, saida);
}

int cWFDocumentoTecnico::alterar()
{

	if (m_vlDados.idDocumentoTecnico == -1) 
	{
		return -1;
	}

	return proCAlterarWFDocumentoTecnico(&m_stDados, &m_vlDados, saida);
}

int cWFDocumentoTecnico::excluir()
{

	if (m_vlDados.idDocumentoTecnico == -1) 
	{
		return -1;
	}

	return proCExcluirWFDocumentoTecnico(&m_stDados, &m_vlDados, saida);
}

bool cWFDocumentoTecnico::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
        char *p;
	    if ( p=tx.walkTree( entrada, "order", 0 ),p )
        {
            sprintf(order,"%.*s",sizeof(order)-1,p);
            XMLString::release(&p);
        }
    }

	return proCConsultaWFDocumentoTecnico(&m_stDados, &m_vlDados, order, saida);
}


bool cWFDocumentoTecnico::consultarAtendimento()
{
    char *p = tx.walkTree( entrada, "idAtendimento", 0 );

    long idAtendimento = p ? strtoul(p, NULL, 0) : 0;

    if ( p ) XMLString::release(&p);

	return proCConsultaWFDocumentoAtendimento(idAtendimento, saida);
}




bool cWFDocumentoTecnico::ValidarNrDocumento( char *sNrDocumento )
{
    return proCValidarWFDocumentoTecnico( sNrDocumento );
}

bool cWFDocumentoTecnico::consultarTodosDocumentos()
{
	return proCTodosDocumentosTecnicos(saida);
}

bool cWFDocumentoTecnico::FecharDocumentos(st_DocumentoTecnico dados, st_vlDocumentoTecnico status)
{
	return proCFecharWFDocumentoTecnico(&dados, &status, saida);
}

bool cWFDocumentoTecnico::consultarSelecaoDocumentos()
{
    char dtInicio[256];
    char dtFinal[256];
    char idAssociados[20];
    char *p;

    dtInicio[0] = 0x0;
    dtFinal[0] = 0x0;
    idAssociados[0] = 0x0;

    if ( p=tx.walkTree( entrada, "dtPeriodo1", 0 ),p )
    {
	    strcpy( dtInicio, p );
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "dtPeriodo2", 0 ),p )
    {
	    strcpy( dtFinal, p );
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "Associados", 0 ),p )
    {
	    strcpy( idAssociados, p );
        XMLString::release(&p);
    }

    return proCSelecaoDocumentosTecnicos(&m_stDados, &m_vlDados, dtInicio, dtFinal, idAssociados, saida );
}

void cWFDocumentoTecnico::carregaDados()
{
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if (p=tx.walkTree( entrada, "idDocumentoTecnicoTipo", 0 ),p) 
	{
		m_stDados.idDocumentoTecnicoTipo = atoi(p);
		m_vlDados.idDocumentoTecnicoTipo = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "nrDocumento", 0 ),p) 
	{
		strcpy(m_stDados.nrDocumento, p);
		m_vlDados.nrDocumento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dtAbertura", 0 ),p) 
	{
		strcpy(m_stDados.dtAbertura, p);
		m_vlDados.dtAbertura = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dtEstimadaFechamento", 0 ),p) 
	{
		strcpy(m_stDados.dtEstimadaFechamento, p);
		m_vlDados.dtEstimadaFechamento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dtFechamento", 0 ),p) 
	{
		strcpy(m_stDados.dtFechamento, p);
		m_vlDados.dtFechamento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dsDocumento", 0 ),p) 
	{
		strcpy(m_stDados.dsDocumento, p);
		m_vlDados.dsDocumento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dsResposta", 0 ),p) 
	{
		strcpy(m_stDados.dsResposta, p);
		m_vlDados.dsResposta = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idUsuarioAlteracao", 0 ),p) 
	{
		m_stDados.idUsuarioAlteracao = atoi(p);
		m_vlDados.idUsuarioAlteracao = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dtUltimaAlteracao", 0 ),p) 
	{
		strcpy(m_stDados.dtUltimaAlteracao, p);
		m_vlDados.dtUltimaAlteracao = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "ComentarioAbertura", 0 ),p) 
	{
		strcpy(m_stDados.ComentarioAbertura, p);
		m_vlDados.ComentarioAbertura = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "ComentarioFechamento", 0 ),p) 
	{
		strcpy(m_stDados.ComentarioFechamento, p);
		m_vlDados.ComentarioFechamento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "inEstadoTecnico", 0 ),p) 
	{
		m_stDados.inEstadoTecnico = atoi(p);
		m_vlDados.inEstadoTecnico = 1;
        XMLString::release(&p);
	}

	ULOG("Numero Documento: %s",m_stDados.nrDocumento);
    ULOG("DTFECHAMENTO: %s",m_stDados.dtFechamento);
	ULOG("COMENTARIOFECHAMENT: %s",m_stDados.ComentarioFechamento);

}

