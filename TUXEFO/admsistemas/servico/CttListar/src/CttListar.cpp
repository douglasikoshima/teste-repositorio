/*****************************************************************************
 *
 * Modulo:    CttListar
 * Arquivo:   CttListar.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define CttListarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCtt.h"
//#include<CCttInf.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttListar);

/**************************************************************************
 *  Funcao de Negocios:  CttListar
 *
 *  Descricao: chama funcao de negocios
 *
 *  Parametros de Entrada e Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de entrada
 *
 *  Parametros de Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de saida
 *
 *************************************************************************/
void implCttListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttListar::Execute()");
	CSafePointer oSafePointer;
   CContato     oContato;

   int          idGrupoAux;
   int          idUFOperadoraAux;
   int          idTipoLinhaAux;
   int          idTipoCarteiraAux;
   int          idSegmentacaoAux;
   int          idContatoAux;

   char idTpRelac[ 23 ];


   try
   {
      idTpRelac[0] = 0x0;
      char* cidGrupo = oSafePointer.getTag( dnode, "idGrupo", 0 );
      char* cidUFOperadora = oSafePointer.getTag( dnode, "idUFOperadora", 0 );
      char* cidTipoLinha = oSafePointer.getTag( dnode, "idTipoLinha", 0 );
      char* cidTipoCarteira = oSafePointer.getTag( dnode, "idTipoCarteira", 0 );
      char* cidSegmentacao = oSafePointer.getTag( dnode, "idSegmentacao", 0 );
      char* cidContato =  oSafePointer.getTag( dnode, "idContato", 0 );
      char* cidTpRelacionamento = oSafePointer.getTag( dnode, "idTipoRelacionamento", 0 );

      strcpy( idTpRelac,cidTpRelacionamento );

      if ( cidGrupo != NULL )
         idGrupoAux = atoi(cidGrupo);
      else
         idGrupoAux = 0;

      if ( cidUFOperadora != NULL )
         idUFOperadoraAux = atoi(cidUFOperadora);
      else
         idUFOperadoraAux = 0;

      if ( cidTipoLinha != NULL )
         idTipoLinhaAux = atoi(cidTipoLinha);
      else
         idTipoLinhaAux = 0;

      if ( cidTipoCarteira != NULL )
         idTipoCarteiraAux = atoi(cidTipoCarteira);
      else
         idTipoCarteiraAux = 0;

      if ( cidSegmentacao != NULL )
         idSegmentacaoAux = atoi(cidSegmentacao);
      else
         idSegmentacaoAux = 0;

      if ( cidContato != NULL )
         idContatoAux = atoi(cidContato);
      else
         idContatoAux = 0;

      if ( idGrupoAux == 0 )  // Sem Grupo
      {
         if ( idContatoAux == 0 )  // Sem contato
         {
            if ( oContato.ListaSemContato(  cidUFOperadora
                                            ,cidTipoLinha
                                            ,cidTipoCarteira
                                            ,cidSegmentacao 
                                          ) <= 0 )
            {
                  setStatusCode("14E0000", oContato.GetErro() );
                  ULOG_END("implCttListar::Execute()");
                  return;
            }
         }
         else   //  Com Contato
         {
            if ( oContato.ListaSemGrupo(  cidContato
                                          ,cidUFOperadora
                                          ,cidTipoLinha
                                          ,cidTipoCarteira
                                          ,cidSegmentacao 
                                        ) <= 0 )
            {
         		   setStatusCode("14E0000", oContato.GetErro() );
         		   ULOG_END("implCttListar::Execute()");
         		   return;
            }
         }
	   }
	   else       //   COM GRUPO
	   {
	      if ( idContatoAux == 0 )  // SEM CONTATO
	      {
   		   if ( oContato.ListaComGrupo(  cidGrupo
                                          ,cidUFOperadora  
                                          ,cidTipoLinha    
                                          ,cidTipoCarteira 
                                          ,cidSegmentacao  
                                        ) <= 0 )
            {                                                
         		   setStatusCode("14E0000", oContato.GetErro() );
         		   ULOG_END("implCttListar::Execute()");
         		   return;
            }
         }
         else       //  COM CONTATO
         {
            if ( oContato.ListaComContato(  cidContato
                                            ,cidGrupo
                                            ,cidUFOperadora  
                                            ,cidTipoLinha    
                                            ,cidTipoCarteira 
                                            ,cidSegmentacao  
                                          ) <= 0 )

            {
         		   setStatusCode("14E0000", oContato.GetErro() );
         		   ULOG_END("implCttListar::Execute()");
         		   return;
            }
         }
	   }
	   
	   if( oContato.Quantidade() > 0 )
	   {
  			xml_g->createTag( "AdmContatoFolhaVO" );
         xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );

         oContato.GetXmlWF( "AdmContatoFolhaVO", idTpRelac, idContatoAux, xml_g );

         xml_g->closeTag();

       }// if( oContato.Quantidade() > 0 )
	   setStatusCode("14I0000","Operacao concluida com sucesso!");
	   ULOG_END("implCttListar::Execute()");
   }
   catch(...)
   {
      throw;
   }
}
