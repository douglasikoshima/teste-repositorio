/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Unknown
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:13 $
 **/


#include "../include/cWFInclChamTel.h"
#include "../../ChamadaTelefonica/include/cWFChamadaTelefonica.h"
#include "../../../commons/SmallString.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"


DECLARE_TUXEDO_SERVICE(INCLCHAMTEL);

void implINCLCHAMTEL::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START("implINCLCHAMTEL::Execute()");
    unsigned long idChamada;
	int idGrauSatisfacao;
	unsigned long idPessoaDePara;
    char szTipoPessoa[10];
	
    int tamSaida;
    char  *xml;
    char  sDataAtual[20];
    char  buffer[256];
    char  *p;

    DOMNode           *pDOCTmp;
    XercesDOMParser   *pParser;
    MemBufInputSource *pMemBuf;
    SmallString        xmlOut;

    sDataAtual[0] = 0x0;
    buffer[0] = 0x0;
    memset( szTipoPessoa, 0, sizeof( szTipoPessoa ) );
    cWFInclChamTel obInclChamTel;

    
    p = walkTree( dnode,"idChamadaTelefonica",0 );
    ULOG("idChamadaTelefonica[%s]", p ? p : "...NULL...");

    if ( p != NULL )
    {
        idChamada = atol(p);
        XMLString::release(&p);

        ULOG("Antes de AtualizaGrupoAtual");
        obInclChamTel.AtualizaGrupoAtual( idChamada );
        ULOG("Depois de AtualizaGrupoAtual");
		
		p = walkTree( dnode,"idGrauSatisfacao",0 );
        ULOG("idGrauSatisfacao[%s]", p ? p : "...NULL...");
		if ( p != NULL )
		{
			idGrauSatisfacao = atol(p);
			XMLString::release(&p);

            ULOG("idGrauSatisfacao(%d)", idGrauSatisfacao);

            ULOG("Antes de AtualizaChamada");
			obInclChamTel.AtualizaChamada( idChamada, idGrauSatisfacao );
            ULOG("Depois de AtualizaChamada");
        }
        setStatusCode("07I0000","Atualização efetuada com sucesso");
    }
    else
    {
		p = walkTree( dnode,"tipoPessoa", 0);
        ULOG("tipoPessoa[%s]", p ? p : "...NULL...");

		if(p != NULL) {
            if( !strcmp(p, "C") || !strcmp(p, "U") )
                strcpy( szTipoPessoa, p );
            else
                strcpy( szTipoPessoa, "C" );

			XMLString::release(&p);
		}

        ULOG("szTipoPessoa[%s]", szTipoPessoa);

        obInclChamTel.GetTime( sDataAtual,0 );
			
		p = walkTree( dnode,"inChamada",0 );
        ULOG("inChamada[%s]", p ? p : "...NULL...");

		if ( p != NULL )
		{
			idGrauSatisfacao = -1;
			XMLString::release(&p);
		}
		else
			idGrauSatisfacao = 0;

        ULOG("idGrauSatisfacao(%d)", idGrauSatisfacao);

		p = walkTree( dnode,"idPessoaDePara",0 );
        ULOG("idPessoaDePara[%s]", p ? p : "...NULL...");
		if ( p != NULL )
		{
			idPessoaDePara = atol(p);
			XMLString::release(&p);
		}
		else
			idPessoaDePara = 0;

        ULOG("idPessoaDePara(%lu)", idPessoaDePara);

        sprintf( buffer,
            "<CriaChamada>\n"
            "<dtChamada>%s</dtChamada>\n"
            "<idPessoaDePara>%lu</idPessoaDePara>\n"
            "<idGrauSatisfacao>%d</idGrauSatisfacao>\n"
            "<tipoPessoa>%s</tipoPessoa>\n"
            "</CriaChamada>\n",
            sDataAtual, idPessoaDePara, idGrauSatisfacao, szTipoPessoa
        );

 		ULOG("Xml Satisfacao: %s", buffer);

        xmlOut = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
        xml = buffer;
        xmlOut += xml;
        tamSaida = xmlOut.Size();
        
        pParser = new XercesDOMParser;
        pMemBuf = new MemBufInputSource((const XMLByte*)xmlOut.c_str(),tamSaida,obInclChamTel.gerarIDDom());
        
        if ( pParser && pMemBuf )
        {
            pParser->parse(*pMemBuf);
            pDOCTmp = pParser->getDocument();
            
            ULOG("Antes de incluir");

            cWFChamadaTelefonica obInclusao( pDOCTmp,xml_g );
            obInclusao.incluir();

            ULOG("Depois de incluir");
       }
        
        delete pMemBuf;
        delete pParser;

        setStatusCode("07I0000","Inclusão de Chamada efetuada com Sucesso");
    }
    ULOG_END("implINCLCHAMTEL::Execute()");
}
