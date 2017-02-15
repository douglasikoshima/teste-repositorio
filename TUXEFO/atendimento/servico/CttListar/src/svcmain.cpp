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
#include "../include/CCttWF.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CTTLSTTESTE);

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
void implCTTLSTTESTE::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCTTLSTTESTE::Execute()");     
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
      char* cidGrupo = walkTree( dnode, "idGrupo", 0 );
      char* cidUFOperadora = walkTree( dnode, "idUFOperadora", 0 );
      char* cidTipoLinha = walkTree( dnode, "idTipoLinha", 0 );
      char* cidTipoCarteira = walkTree( dnode, "idTipoCarteira", 0 );
      char* cidSegmentacao = walkTree( dnode, "idSegmentacao", 0 );
      char* cidContato =  walkTree( dnode, "idContato", 0 );
      char* cidTpRelacionamento = walkTree( dnode, "idTipoRelacionamento", 0 );

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
                  if ( cidGrupo ) XMLString::release(&cidGrupo);
                  if ( cidUFOperadora ) XMLString::release(&cidUFOperadora);
                  if ( cidTipoLinha ) XMLString::release(&cidTipoLinha);
                  if ( cidTipoCarteira ) XMLString::release(&cidTipoCarteira);
                  if ( cidSegmentacao ) XMLString::release(&cidSegmentacao);
                  if ( cidContato ) XMLString::release(&cidContato);
                  if ( cidTpRelacionamento ) XMLString::release(&cidTpRelacionamento);

                  setStatusCode("00E0000", oContato.GetErro() );
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
                  if ( cidGrupo ) XMLString::release(&cidGrupo);
                  if ( cidUFOperadora ) XMLString::release(&cidUFOperadora);
                  if ( cidTipoLinha ) XMLString::release(&cidTipoLinha);
                  if ( cidTipoCarteira ) XMLString::release(&cidTipoCarteira);
                  if ( cidSegmentacao ) XMLString::release(&cidSegmentacao);
                  if ( cidContato ) XMLString::release(&cidContato);
                  if ( cidTpRelacionamento ) XMLString::release(&cidTpRelacionamento);

         		  setStatusCode("00E0000", oContato.GetErro() );
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
                  if ( cidGrupo ) XMLString::release(&cidGrupo);
                  if ( cidUFOperadora ) XMLString::release(&cidUFOperadora);
                  if ( cidTipoLinha ) XMLString::release(&cidTipoLinha);
                  if ( cidTipoCarteira ) XMLString::release(&cidTipoCarteira);
                  if ( cidSegmentacao ) XMLString::release(&cidSegmentacao);
                  if ( cidContato ) XMLString::release(&cidContato);
                  if ( cidTpRelacionamento ) XMLString::release(&cidTpRelacionamento);

        		  setStatusCode("00E0000", oContato.GetErro() );
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
                  if ( cidGrupo ) XMLString::release(&cidGrupo);
                  if ( cidUFOperadora ) XMLString::release(&cidUFOperadora);
                  if ( cidTipoLinha ) XMLString::release(&cidTipoLinha);
                  if ( cidTipoCarteira ) XMLString::release(&cidTipoCarteira);
                  if ( cidSegmentacao ) XMLString::release(&cidSegmentacao);
                  if ( cidContato ) XMLString::release(&cidContato);
                  if ( cidTpRelacionamento ) XMLString::release(&cidTpRelacionamento);

         		  setStatusCode("00E0000", oContato.GetErro() );
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

		 setStatusCode("00I0000","Operacao concluida com sucesso!");
      }
      else
      {
         setStatusCode("00I0000","Sem dados para retorno");
      }

      if ( cidGrupo ) XMLString::release(&cidGrupo);
      if ( cidUFOperadora ) XMLString::release(&cidUFOperadora);
      if ( cidTipoLinha ) XMLString::release(&cidTipoLinha);
      if ( cidTipoCarteira ) XMLString::release(&cidTipoCarteira);
      if ( cidSegmentacao ) XMLString::release(&cidSegmentacao);
      if ( cidContato ) XMLString::release(&cidContato);
      if ( cidTpRelacionamento ) XMLString::release(&cidTpRelacionamento);
      
      ULOG_END("implCTTLSTTESTE::Execute()");     
      

   }
   catch(...)
   {
      ULOG_END("implCTTLSTTESTE::Execute()SAIDA COM ERRO ");     
      throw;
   }
}
