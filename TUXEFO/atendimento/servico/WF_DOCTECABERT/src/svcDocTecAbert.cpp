
#include "../include/cDocTecAbert.h"
#include "../../DocumentoTecnico/include/cWFDocumentoTecnico.h"
#include "../../../commons/SmallString.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/TString.h"
#include "../../../commons/definesAtendimento.h"
#include "../../../commons/routerLib/include/RouterClient.h"


DECLARE_TUXEDO_SERVICE(DOCTECABERT);

void implDOCTECABERT::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START("implDOCTECABERT::Execute()");

    // int IdProced = -1;
	st_DocumentoTecnico	m_stDados;
	st_vlDocumentoTecnico m_vlDados;
	TString tNrDocumento;
    TString tDsDocumento;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

    char *p;
    if ( p=walkTree(dnode,"nrDocumento",0),p )
    {
        if ( strlen(p) > 0 )
		{
			tNrDocumento = p;
			tNrDocumento.Trim();
            SAFE_STRNCPY(m_stDados.nrDocumento,tNrDocumento.c_str());
			m_vlDados.nrDocumento = 1;
        }
        XMLString::release(&p);
    }
    
    if ( p = walkTree( dnode,"dtAbertura",0 ),p )
    {
        if ( strlen(p) > 0 )
		{
            SAFE_STRNCPY(m_stDados.dtAbertura,p);
			m_vlDados.dtAbertura = 1;
        }
        XMLString::release(&p);
    }
    
    if ( p = walkTree( dnode,"dtEstimadaFechamento",0 ),p )
    {
        if ( strlen(p) > 0 )
		{
            SAFE_STRNCPY(m_stDados.dtEstimadaFechamento,p);
			m_vlDados.dtEstimadaFechamento = 1;
        }
        XMLString::release(&p);
    }
    
    if ( p = walkTree( dnode,"dsDocumento",0 ),p )
	{
        if ( strlen(p) > 0 )
		{
			tDsDocumento = p;
			tDsDocumento.Trim();
            SAFE_STRNCPY( m_stDados.dsDocumento,tDsDocumento.c_str() );
			m_vlDados.dsDocumento = 1;
        }
        XMLString::release(&p);
    }
    
    if ( p = walkTree( dnode,"idTipoDocumentoProcesso",0 ),p )
	{
		m_stDados.idDocumentoTecnicoTipo = atoi(p);
		m_vlDados.idDocumentoTecnicoTipo = 1;
        XMLString::release(&p);
	}

    //if ( p = walkTree( dnode,"idProcedencia",0 ),p )
	//{
    //    IdProced = atoi(p);
    //    XMLString::release(&p);
	//}

	m_stDados.inEstadoTecnico = 0;		
	m_vlDados.inEstadoTecnico = 1;		

    XMLGen saida;
    cWFDocumentoTecnico obInclusao(&saida);
        
    xml_g->createTag("WFAcaoRetornoVO");
    xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    if ( obInclusao.ValidarNrDocumento(m_stDados.nrDocumento) )
    {
        obInclusao.incluir(m_stDados, m_vlDados);

        xml_g->addItem( "acaoExecucao","S" );
        xml_g->addItem( "mensagem","Abertura de Documento T�cnico Efetuada" );

        setStatusCode("07I0000","Inclus�o de Documento T�cnico efetuado com Sucesso");
    }
    else
    {
        xml_g->addItem( "acaoExecucao","N" );
        xml_g->addItem( "mensagem","Documento T�cnico j� existe na base de dados, opera��o n�o efetuada" );

        setStatusCode("07I0010","Documento T�cnico j� existe na base de dados, a inclus�o n�o foi efetivada");
    }

    xml_g->closeTag();

    ULOG_END("implDOCTECABERT::Execute()");
}
