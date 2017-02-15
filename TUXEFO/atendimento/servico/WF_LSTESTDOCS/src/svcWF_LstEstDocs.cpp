
/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Lista Estado de Todos Dcoumentos
 * Migracao: Script 205
 * Revisao.: Nov-23-2004 - Marcelo Nunes
 * Alteracao: Jan-20-2005 - Marcelo Nunes
 *--------------------------------------------------------*/

#include "../include/cWF_LstEstDocsPC.h"
#include "../../ApoioProcedencia/include/cWFApoioProcedencia.h"
#include "../../AtendimentoDocTecnicoTipo/include/cWFAtendimentoDocTecnicoTipo.h"
#include "../../../commons/SmallString.h"

#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"


class TuxHelperImpl:public TuxHelper
{
public:
	DOMNode * walkDOMImpl( DOMNode * a , char * b , int * c , int d )
	{
		return walkDOM( a , b , c , d );
	}
};

DECLARE_TUXEDO_SERVICE(LSTESTDOCS);

void implLSTESTDOCS::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implLSTESTDOCS::Execute()");
    int   Node = 0;
    int   idProcedencia = -1;
    int   tamSaida = -1;    
    char *xml;
    char *p;
    char  dsProcedencia[1001];
    SmallString        xmlOut;
    SmallString        xmlOutAux;
    DOMNode           *pDOCTmp;
    DOMNode           *pDOMAux;
    DOMNode           *pDOM;
    XercesDOMParser   *pParser;
    MemBufInputSource *pMemBuf;
    XercesDOMParser   *pParserAux;
    MemBufInputSource *pMemBufAux;
    XMLGen             saida;
    cWF_LstEstDocs     obLstDocs;
    TuxHelperImpl      tuxhelperIMPL;

    xmlOut = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
    xmlOut += "<ListaEstadoTipoDocumentoVO>\n"
                    "<order>dsDocumentoTecnicoTipo</order>\n"
              "</ListaEstadoTipoDocumentoVO>\n";

    tamSaida = xmlOut.Size();

    xml_g->createTag("AtendimentoWorkflowTecnicoVO");
    xml_g->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
    xml_g->addProp( "xmlns:ns2","admsistemas.fo.vivo.com.br/vo" );
    
    pParser = new XercesDOMParser;
    pMemBuf = new MemBufInputSource((const XMLByte*)xmlOut.c_str(),tamSaida,obLstDocs.gerarIDDom());
    if ( pParser && pMemBuf )
    {
        pParser->parse(*pMemBuf);
        pDOCTmp = pParser->getDocument();
        cWFAtendimentoDocTecnicoTipo obTmp( pDOCTmp,xml_g );

        obTmp.consultar();
    }

    delete pMemBuf;
    delete pParser;

    xml_g->createTag("WFDocumentoTecnicoEstadoVO");
        xml_g->addItem("idDocTecEstado", 0 );
        xml_g->addItem("dsDocTecEstado", "Aberto" );
    xml_g->closeTag();
    xml_g->createTag("WFDocumentoTecnicoEstadoVO");
        xml_g->addItem("idDocTecEstado", 1 );
        xml_g->addItem("dsDocTecEstado", "Fechado" );
    xml_g->closeTag();

    xmlOut = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
    xmlOut += "<ListaEstadoTipoDocumentoVO>\n"
                   "<order>dsProcedencia</order>\n"
              "</ListaEstadoTipoDocumentoVO>\n";

    tamSaida = xmlOut.Size();

    pParser = new XercesDOMParser;
    pMemBuf = new MemBufInputSource((const XMLByte*)xmlOut.c_str(),tamSaida,obLstDocs.gerarIDDom());
    if ( pParser && pMemBuf )
    {
        pParser->parse(*pMemBuf);
        pDOCTmp = pParser->getDocument();
        saida.clearAndDestroy();
        cWFApoioProcedencia obAux( pDOCTmp,&saida );

        obAux.consultar();
        xml = saida.retrieveXML(&tamSaida);

        if ( tamSaida == 0 )
        {
            ULOGE( mensagemSimples("Erro na geracao de xml de saida") );
            setStatusCode( "07E0025","Erro na geracao de xml de saida" );
            return;
        }

        xmlOutAux = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
        xmlOutAux += xml;
        tamSaida = xmlOutAux.Size();
        pParserAux = new XercesDOMParser;
        pMemBufAux = new MemBufInputSource((const XMLByte*)xmlOutAux.c_str(),tamSaida,obLstDocs.gerarIDDom());
        if ( pParserAux && pMemBufAux )
        {
            pParserAux->parse( *pMemBufAux );
            pDOM = pParserAux->getDocument();
        
            int i = 0;
            for (;;)
            {
                Node = 0;
                
                pDOMAux = tuxhelperIMPL.walkDOMImpl( pDOM,"ProcedenciaVO",&Node,i++ );
                if ( pDOMAux == NULL )
                {
                    break;
                }

                p = walkTree( pDOMAux,"idProcedencia",0 );
                if ( p )
                {
                    idProcedencia = atoi(p);
                    XMLString::release(&p);
                }
                else
                {
                    break;
                }

                p = walkTree( pDOMAux,"dsProcedencia",0 );
                if ( p )
                {
                    strcpy( dsProcedencia,p );
                    XMLString::release(&p);
                }
                else
                {
                    dsProcedencia[0] = 0;
                }

                xml_g->createTag("ns2:ProcedenciaVO");
                    xml_g->addItem("ns2:idProcedencia",idProcedencia );
                    xml_g->addItem("ns2:descricao",dsProcedencia );
                xml_g->closeTag();

            }
        }
        delete pMemBufAux;
        delete pParserAux;
    }
    delete pMemBuf;
    delete pParser;

    xml_g->closeTag();

    setStatusCode("07I0000","Consulta de Documentos Tecnicos Concluida");
    ULOG_END("implLSTESTDOCS::Execute()");
}
