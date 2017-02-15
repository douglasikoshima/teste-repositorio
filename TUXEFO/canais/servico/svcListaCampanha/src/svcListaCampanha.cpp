
#include <stdio.h>
#include <tuxfw.h>
#include <iterator>
#include <list>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"
#include "../../negocio/UtilCanais/include/UtilCanais/UtilCanais.hpp"

typedef list<int> LISTA_ID;

using namespace std;

extern void proCListaCampanha( int idCampanhaPrm, int tpCampanhaPrm, XMLGen * Saida, int idTipoPessoa );
extern void proCListaCampanhaFiltro( LISTA_ID * pidDDD, LISTA_ID * pidTipoLinha, LISTA_ID * pidSegmentacao, XMLGen * Saida, int TipoPessoa );

DECLARE_TUXEDO_SERVICE(LISTACAMPAN);

void implLISTACAMPAN::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{
   LISTA_ID       pidDDD;
   LISTA_ID       pidTipoLinha;
   LISTA_ID       pidSegmentacao;
   
   ULOG_START( "implLISTACAMPAN::Execute" );
   
   CTuxHelperClever helper;
   DOMNode *dn;
   int j = 0;
   
	char* cidCampanha = NULL;	
	int	idCampanha = 0;
	char* ctpCampanha = NULL;
	int   tpCampanha = 0;

	char* p0 = NULL;	
	int	id = 0;
	
	char* cIdTipoPessoa = NULL;
	int   idTipoPessoa = 0;	

	ctpCampanha = helper.walkTree( dnode, "tpCampanha", 0 );

	if (ctpCampanha != NULL)
	{		
		tpCampanha = atoi(ctpCampanha);
	}

	cidCampanha = helper.walkTree( dnode, "idCampanha", 0 );

	if ( cidCampanha != NULL )
   {
      idCampanha = atoi(cidCampanha);
   }

   if ( idCampanha == 0 )  // Pesquisa pelos filtros das variaveis
   {
      j = 0;  // Lista de DDDs
      while ( dn = helper.walkDOM(dnode,"DDDVO",j++ ) )
      {
         if ( p0 = helper.walkTree(dn,"idDDD",0),p0 )
         {
            id = atoi(p0);
            pidDDD.push_back( id );
         }
      }
      
      j = 0; // Lista de TipoLinha
      while ( dn = helper.walkDOM(dnode,"vo1:TipoLinhaVO",j++ ) )
      {
         if ( p0 = helper.walkTree(dn,"vo1:id",0),p0 )
         {
            id = atoi(p0);
            pidTipoLinha.push_back( id );
         }
      }
   
      j = 0;  // Lista de Segmentacoes
      while ( dn = helper.walkDOM(dnode,"vo1:SegmentacaoVO",j++ ) )
      {
         if ( p0 = helper.walkTree(dn,"vo1:idSegmentacao",0),p0 )
         {
            id = atoi(p0);
            pidSegmentacao.push_back( id );
         }
      }
   }
   
	cIdTipoPessoa = helper.walkTree( dnode, "tpPessoa", 0 );
	if (cIdTipoPessoa != NULL)
	{		
		idTipoPessoa = atoi(cIdTipoPessoa);
	}
   
   
   ULOG( "proCListaCampanha()" );
	xml_g->createTag("CampanhaVO");
	xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:ns3","admsistemas.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:ns2","cliente.fo.vivo.com.br/vo");
      if( idCampanha != 0 || tpCampanha )
      {
         proCListaCampanha( idCampanha, tpCampanha, xml_g, idTipoPessoa );
      }
      else
      {
         proCListaCampanhaFiltro( &pidDDD, &pidTipoLinha, &pidSegmentacao, xml_g, idTipoPessoa );
      }
   xml_g->closeTag();

   setStatusCode("13I0000","Sucesso");
   
}
