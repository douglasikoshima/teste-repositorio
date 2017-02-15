/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:35 $
 **/

#include "../include/cWFObtFrmDinPC.h"
#include "../../AtendimentoFrm/include/cWFAtendimentoFrm.h"

#include "../../../commons/msgPadrao.h"
#include "../commons/SmallString.h"
#include "../../../commons/routerLib/include/RouterClient.h"


DECLARE_TUXEDO_SERVICE(WFOBTFRMDIN);

void implWFOBTFRMDIN::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFOBTFRMDIN::Execute()");
    long idAtendimento = -1;
    int idContato = -1;
	int idFase =-1;
    int idTipoProcesso = -1;
    int idFormaRetorno = -1;
    char dsTipoComunicacao[513];
    int  idTipoLinha = -1;
    char nrTelefoneContato[11];
    cWFObtFrmDinPC   rc;
    char            *pBuffer;
    char *p = 0;

    dsTipoComunicacao[0] = 0x0;
    nrTelefoneContato[0] = 0x0;

	

    pBuffer = walkTree( dnode, "idAtendimento", 0 );
    if ( pBuffer )
    {
        idAtendimento = atol( pBuffer );
        XMLString::release(&pBuffer);
    }

    pBuffer = walkTree( dnode, "idContato", 0 );
    if ( pBuffer )
    {
        idContato = atoi( pBuffer );
        XMLString::release(&pBuffer);
    }
	
	if ( idAtendimento == 0 )
    {


		rc.ProcObtemTipoContato( idContato,&idTipoProcesso );

		xml_g->createTag("ArvoreAtendimentoVO");
		xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
			xml_g->addItem( "idContato", idContato );
			xml_g->addItem( "idTipoContato", idTipoProcesso );
			xml_g->createTag("FormularioVO");
				xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
				xml_g->addProp("xmlns:ns1","workflow.fo.vivo.com.br/vo");

				if (idTipoProcesso > 1)
				{
					st_AtendimentoFrm m_stDadosAtendimentoFrm;
					st_vlAtendimentoFrm m_vlDadosAtendimentoFrm;

					memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
					memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

					cWFAtendimentoFrm obAtdFrmMassa(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, xml_g);
					obAtdFrmMassa.obtemFormularioMassa();
				}
			xml_g->closeTag();
		xml_g->closeTag();
    }
	else
	{
		int status = -1;
		rc.ProcObtemContato( idAtendimento,&idContato, &idFase );
		ULOG("Executou ProcObtemContato");

		rc.ProcFormaRetorno( idAtendimento,&idFormaRetorno,dsTipoComunicacao );
		ULOG("Executou ProcFormaRetorno");

		status = rc.ProcObtemDescricaoRetorno( idAtendimento,&idTipoLinha,nrTelefoneContato );
		ULOG("Executou ProcObtemDescricaoRetorno");

		xml_g->createTag("ArvoreAtendimentoVO");
		xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
			xml_g->addItem( "idContato", idContato );
			xml_g->addItem( "idTipoComunicacao", idFormaRetorno );
			xml_g->addItem( "dsTipoComunicacao", dsTipoComunicacao );
			xml_g->createTag("FormularioVO");
			xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
			xml_g->addProp("xmlns:ns1","workflow.fo.vivo.com.br/vo");

		if ( status != 0 )
		{
			int           tamSaida;
			char         *xml;
			char          buffer[512];
			SmallString   xmlOut;

			sprintf( buffer,
					"<BuscaFormulario>\n"
					"<idContato>%d</idContato>\n"
					"<nrTelefone>%s</nrTelefone>\n"
					"<idTipoLinha>%d</idTipoLinha>\n"
					"<idFaseProcesso>%d</idFaseProcesso>\n"
					"</BuscaFormulario>",
					 idContato,
					 nrTelefoneContato,
					 idTipoLinha,
					 idFase
				   );

			xmlOut = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
			xml = buffer;
			xmlOut += xml;
			tamSaida = xmlOut.Size();
	   
			DOMNode           *pDOMTmp;
			XercesDOMParser   *pParser;
			MemBufInputSource *pMemBuf;
			pParser = new XercesDOMParser;
			pMemBuf = new MemBufInputSource((const XMLByte*)xmlOut.c_str(),tamSaida,rc.gerarIDDom());

			if ( pParser && pMemBuf )
			{
				pParser->parse(*pMemBuf);
				pDOMTmp = pParser->getDocument();

				cWFAtendimentoFrm obAtdFrm( pDOMTmp,xml_g );

				obAtdFrm.obtemFormulario();
				xml_g->closeTag();
			}
			else
			{
				p = "FALHA: ERRO INTERNO";
				ULOGE("ERRO: NAO OBTEVE RECURSO PARA CRIAR DOMNode para o metodo obtemFormulario");
			}

			delete pParser;
			delete pMemBuf;
		}
		else
		{
			xml_g->closeTag();
		}
		
		xml_g->closeTag();

	}
	if ( p )
    {
        setStatusCode("07E0010",p);
    }
    else
    {
        setStatusCode("07I0000","Obtenção de Formulário Dinâmico Concluído");
    }
    ULOG_END("implWFOBTFRMDIN::Execute()");
}
