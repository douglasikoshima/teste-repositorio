
#include "../include/cWF_DetPrcTstPrcPC.h"

DECLARE_TUXEDO_SERVICE(DETPRCINCL);

class TuxHelperImpl:public TuxHelper
{
public:
	DOMNode * walkDOMImpl( DOMNode*a , char*b , int*c , int d )
	{
		return walkDOM( a , b , c , d );
	}
};

void implDETPRCINCL::Execute( DOMNode*dnode,XMLGen*xml_g )
{
    ULOG_START("implDOCTECABERT::Execute()");
    char       *pBuffer;
    cWF_DetPrcTstPrcPC  rc;
    long   idAtendimento;
    int   idUsuario;
    int   idATeste;
    int   idTeste;
    char  dsObservacao[512];

    DOMNode        * pNoCodigo;
    TuxHelperImpl   tuxhelperIMPL;
    int   SubnoAtual = 0;
    int   Subno = 0;
    char  cResposta;

    pBuffer = walkTree( dnode, "idAtendimento", 0 );
    if ( pBuffer == NULL )
      idAtendimento = 0;
    else
    {
      idAtendimento = atol( pBuffer );
      XMLString::release(&pBuffer);
    }
    if ( idAtendimento == 0 )
    {
      char Msg[512];
      sprintf( Msg,"Nro. Atendimento Invalido:[%d]",idAtendimento );
      throw new TuxBasicSvcException( "09E0001",Msg );
    }
   
    pBuffer = getUser();
    if ( pBuffer == NULL )
      idUsuario = 0;
    else
    {
      idUsuario = atoi( pBuffer );
      XMLString::release(&pBuffer);
    }

    pBuffer = walkTree( dnode, "dsObservacao", 0 );
    if ( pBuffer == NULL )
         dsObservacao[0] = 0x0;
    else
    {
      strcpy( dsObservacao,pBuffer );
      XMLString::release(&pBuffer);
    }
   
    idATeste = rc.SaveAtendimentoTeste( idAtendimento,idUsuario,dsObservacao );

    for ( ;; )  
    {
      Subno = 0;
      pNoCodigo = tuxhelperIMPL.walkDOMImpl( dnode,"AtendimentoWorkflowTesteVO",&Subno,SubnoAtual++ );
      if ( pNoCodigo == NULL )
         break;

      pBuffer = walkTree( pNoCodigo,"idTeste",0 );
      if ( pBuffer == NULL )
         break;
      else
      {
         idTeste = atoi(pBuffer);
         XMLString::release(&pBuffer);
      }

      pBuffer = walkTree( pNoCodigo,"dsResposta",0 );
      if ( pBuffer == NULL )
         break;
      else
      {
         cResposta = pBuffer[0];
         XMLString::release(&pBuffer);
      }

      rc.SaveTesteResposta( cResposta,idTeste,idATeste,idUsuario );

    }

    xml_g->createTag("WFAcaoRetornoVO");
    xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );
      xml_g->addItem( "acaoExecucao","S" );
      xml_g->addItem( "mensagem","Testes inclusos com Sucesso" );
    xml_g->closeTag();


    setStatusCode("09I0000","Detalhe do Processo - Testes de Inclusao Finalizado");
    
    ULOG_END("implDOCTECABERT::Execute()");

}
