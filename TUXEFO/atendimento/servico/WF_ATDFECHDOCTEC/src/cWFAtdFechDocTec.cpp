/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:27 $
 **/

#include "../include/cWFAtdFechDocTec.h"

#include "../../../commons/TString.h"

#include "../../../commons/SmallString.h"

extern bool WFGravaAtdAlerta( char *nrDocumentoPrm,int idUsuarioPrm, int idAtividadePrm );
extern int  WFValidaDocumentos( char *nrDocumentoPrm );
extern int  BuscaDataAbertura( char *nrDocumentoPrm, char *dtAbertura );

cWFAtdFechDocTec::cWFAtdFechDocTec(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdFechDocTec::Executar()
{
    bool retorno = false;
    char dtFechamento[32];
    const int idAtividade = 19;  //  id da Mensagem (Documento Tecnico Fechado)
    int idUsuario; /* = atoi(getUser());*/
    char *p;

    p = getUser();

    if ( !p )
        idUsuario = 0;
    else
        idUsuario = atoi(p);

    TString tNrDocumento;
    TString tDsDocumento;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if ( p = tx.walkTree( entrada, "nrDocumento", 0 ),p )
    {
		tNrDocumento = p;
		tNrDocumento.Trim();
        strcpy( m_stDados.nrDocumento,tNrDocumento.c_str());
		m_vlDados.nrDocumento = 1;		
        XMLString::release(&p);
    }

    if ( p = tx.walkTree( entrada, "dtFechamento", 0 ),p )
    {
        sprintf( dtFechamento,"%.4s%.2s%.2s",&p[6],&p[3],p );
        strcpy( m_stDados.dtFechamento,p );
		m_vlDados.dtFechamento = 1;		
        XMLString::release(&p);
    }

    if ( p = tx.walkTree( entrada, "dsDocumento", 0 ),p )
    {
		tDsDocumento = p;
		tDsDocumento.Trim();
        strcpy( m_stDados.ComentarioFechamento,tDsDocumento.c_str() );
		m_vlDados.ComentarioFechamento = 1;		

        XMLString::release(&p);
    }

	m_stDados.inEstadoTecnico = 1;		
	m_vlDados.inEstadoTecnico = 1;		

	int flagExec = ValidaDocumentos( m_stDados.nrDocumento );
	if ( flagExec == 0 )
	{
		saida->createTag("WFAcaoRetornoVO");
		saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
		saida->addItem("acaoExecucao","N");
		saida->addItem("mensagem","Documento Técnico Não Existe na Base de Dados" );
		saida->closeTag();
		retorno = true;
	}
	else
	{
		if ( flagExec == 1 )
		{
			char dtAbertura[11];
			dtAbertura[0]=0x0;

			BuscaDataAbertura( m_stDados.nrDocumento, dtAbertura );
			if ( atoi(dtFechamento) < atoi(dtAbertura) )
			{
				saida->createTag("WFAcaoRetornoVO");
				saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
					saida->addItem("acaoExecucao","N");
					saida->addItem("mensagem","Data de fechamento não pode ser menor que a data de abertura");
				saida->closeTag();
				retorno = true;
			}
			else
			{
				if ( FecharDocumentos() )
				{
					retorno = AtualizaAtdAlerta( m_stDados.nrDocumento,idAtividade,idUsuario );
					
					if ( retorno )
					{
						saida->createTag("WFAcaoRetornoVO");
						saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
						saida->addItem("acaoExecucao","S");
						saida->addItem("mensagem","Fechou Documento");
						saida->closeTag();
					}
				}
			}
		}
		else
		{
			if ( flagExec == 2 )
			{
				saida->createTag("WFAcaoRetornoVO");
				saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
				saida->addItem("acaoExecucao","N");
				saida->addItem("mensagem","Documento Técnico já está fechado");
				saida->closeTag();
				retorno = true;
			}
		}
	}

    return retorno;
}

bool cWFAtdFechDocTec::FecharDocumentos()
{
    ULOG_START("cWFAtdFechDocTec::FecharDocumentos()");
    cWFDocumentoTecnico cwfDocumentoTecnico(saida);

    
	bool retorno = cwfDocumentoTecnico.FecharDocumentos(m_stDados, m_vlDados);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFDocumentoTecnico::FecharDocumentos falhou!");

        ULOGE(ObterMsgErro());
    }
    ULOG_END("cWFAtdFechDocTec::FecharDocumentos()");
    return retorno ? true : false;
}

bool cWFAtdFechDocTec::AtualizaAtdAlerta( char *nrDocumento,int idAtividade,int idUsuario )
{

    ULOG_START("cWFAtdFechDocTec::AtualizaAtdAlerta()");
    
    bool retorno = WFGravaAtdAlerta( nrDocumento,idUsuario,idAtividade );
    
    if ( !retorno )
    {
        SetarErro(NULL,"cWFAtdFechDocTec::AtualizaAtdAlerta falhou!");
        
        ULOG(ObterMsgErro());
    }
    
    ULOG_END("cWFAtdFechDocTec::AtualizaAtdAlerta()");
    
    return retorno ? true : false;
}

int cWFAtdFechDocTec::ValidaDocumentos( char *nrDocumento )
{
    
    ULOG_END("cWFAtdFechDocTec::ValidaDocumentos()");
    
    int retorno = WFValidaDocumentos( nrDocumento );
    
    if ( retorno < 0)
    {
        SetarErro(NULL,"cWFAtdFechDocTec::ValidaDocumentos falhou!");
        
        ULOGE(ObterMsgErro());
        
    }
    
    ULOG_END("cWFAtdFechDocTec::ValidaDocumentos()");
    return retorno;
    
}
