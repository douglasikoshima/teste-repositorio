

#include "../include/cFiltroArvContato.h"



// Prototypes
extern void proCInsereFiltros( bool limpar, st_FiltroArvContato * pRegistro ,LISTA_STMT *xml_g );
extern void proCGravaFiltros( char * stmtPrm );
extern void proCSelFiltros( st_FiltroArvContato *pRegistro ,XMLGen *xml_g );
extern void proCSelArquivos( st_FiltroArvContato *pRegistro ,XMLGen *xml_g );


/*
 *-------------------------------------------------
 */
void CFiltroArvContato::Insere( char * idUser, DOMNode *dnode, st_FiltroArvContato *pRegistro ,XMLGen *xml_g )
{
    ULOG_START( "Insere()" );

    LISTA_STMT ltStmt;
    LISTA_ID   ltTipoLinha;   
	LISTA_ID   ltSegmentacao;
	LISTA_ID   ltTipoCliente;	    
	LISTA_ID   ltTipoCarteira;
	LISTA_ID   ltRegional;
	LISTA_ID   ltNatureza;
	LISTA_ID   ltCanal;
	LISTA_ID   ltTipoFechamento;
	LISTA_ID   ltGrupoAbertura;
	LISTA_ID   ltGrupoTratamento;
	LISTA_ID   ltGrupoRetorno;
	LISTA_ID   ltProcedencia;
	

    char *p = 0x0;
    int it = 0;
    int i = 0;
    bool flagGravar;
    bool limpar = true;
    
    static bool flagAjustado = false;

    char *sNodeBusca[] =
    {
        "tipoLinha",
        "segmentacao",
        "tipoCliente",
        "carteirizacao",
        "operadora",
        "natureza",
        "canal",
        "fechamento",
        "grupoAbertura",
        "grupoTratamento",
        "grupoRetorno",
        "procedencia",
        0x0
    };

    pRegistro->idUsuario = atoi(idUser);
    flagGravar = false;

    for ( i = 0; &sNodeBusca[i][0] != 0x0; i++ )
    {
    	memset( pRegistro,0x0,sizeof(pRegistro) );
        dnBusca = 0x0;
        dnBusca = tx.walkDOM( dnode,sNodeBusca[i],0 );
        if ( dnBusca != 0x0 ) 
        {
        
            switch(i) 
            {
                case  0 : for( it=0;;it++ )
						  {
                    	      if (p = tx.walkTree( dnBusca, "id", it ), p) 
                              {
                                  ltTipoLinha.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                              }
                              else break;
                          }
        	              break;
                                    
                case  1 : for( it=0;;it++ )
						  {
    						  if (p = tx.walkTree( dnBusca, "id", it ), p) 
                              {
                                  ltSegmentacao.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                              }
                              else break; 
                          }
        	              break; 
        	              
                case  2 : for( it=0;;it++ )
						  {
    						  if (p = tx.walkTree( dnBusca, "id", it ), p) 
                              {
                                  ltTipoCliente.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                              }
                              else break;
                          }
        	              break;

                case  3 : for( it=0;;it++ )
                	      {
                	      	if (p = tx.walkTree( dnBusca, "id", it ), p) 
                            {
                              ltTipoCarteira.push_back(atoi(p));
                              XMLString::release(&p);
                              flagGravar = true;
                            }
                            else break;
                          }
        	              break;

                case  4 : for( it=0;;it++ )
                	      {
                    	      if (p = tx.walkTree( dnBusca, "id", it ), p) 
                              {
                                  ltRegional.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                              }
                              else break;
                          }
        	              break;

                case  5 : for( it=0;;it++ )
                	      {
                    	      if (p = tx.walkTree( dnBusca, "id", it ), p) 
                              {
                                  ltNatureza.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                              }
                              else break;
                          }
        	              break;

                case  6 : for( it=0;;it++ )
                	      {
                    	      if (p = tx.walkTree( dnBusca, "id", it ), p) 
                              {
                                  ltCanal.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                              }
                              else break;
                          }
        	              break;

                case  7 : for( it=0;;it++ )
                	      {
                    	      if (p = tx.walkTree( dnBusca, "id", it ), p) 
                              {
                                  ltTipoFechamento.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                              }
                              else break;
                          }
        	              break;

                case  8 : for( it=0;;it++ )
                	      { 
                    	      if (p = tx.walkTree( dnBusca, "id", it ), p) 
                              {
                                  ltGrupoAbertura.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                              }
                              else break;
                          }
        	              break;

                case  9 : for( it=0;;it++ )
                	      {
                    	      if (p = tx.walkTree( dnBusca, "id", it ), p) 
                              {
                                  ltGrupoTratamento.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                              }
                              else break;
                          }
        	              break;

                case  10 : for( it=0;;it++ )
                	       {
                    	       if (p = tx.walkTree( dnBusca, "id", it ), p) 
                               {
                                  ltGrupoRetorno.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                               }
                               else break;
                           }
        	               break;

                case  11 : for( it=0;;it++ )
                	       {
                    	       if (p = tx.walkTree( dnBusca, "id", it ), p) 
                               {
                                  ltProcedencia.push_back(atoi(p));
                                  XMLString::release(&p);
                                  flagGravar = true;
                               }
                               else break;
                           }
        	               break;

            }
        
        }
    } // Fim  for( i = 0; sNodeBusca[i][0] != NULL; i++ )

        p = 0x0;
        if ( p = tx.walkTree( dnode, "inTipoConsulta", 0 ), p ) 
        {
            pRegistro->idTipoRelatorio = atoi( p );
            XMLString::release( &p );
            ULOG( "... Tipo de Relatorio [%d]",pRegistro->idTipoRelatorio );
			
			#ifdef WIN32
            printf( "... Tipo de Relatorio [%d]\n",pRegistro->idTipoRelatorio );
			#endif
        } 

        if ( p = tx.walkTree( dnode, "Download", 0 ), p ) 
        {
            strcpy( pRegistro->urlDownload,p );
            XMLString::release(&p);
        }

        if ( p = tx.walkTree( dnode, "inDisponivel", 0 ), p ) 
        {
            strcpy(pRegistro->sgDisponivel,(char *)p);
            XMLString::release(&p);
        }

        if ( p = tx.walkTree( dnode, "dtSolicitacao", 0 ), p ) 
        {
            strcpy(pRegistro->dtSolicitacao,(char *)p);
            XMLString::release(&p);
        }

        if ( p = tx.walkTree( dnode, "nmArquivo", 0 ), p ) 
        {
            strcpy(pRegistro->nmArquivo,(char *)p);
            XMLString::release(&p);
        }

    
    	SetTipoRelatorio( pRegistro );
        
        flagGravar = false;
        for( int ct=0;;ct++ )
        {
        	if (ct < ltTipoLinha.size())
        	{
        		pRegistro->idTipoLinha       = ltTipoLinha[ct];
		        flagGravar = true;
        	}
        	if (ct < ltSegmentacao.size())     
        	{
        		pRegistro->idSegmentacao     = ltSegmentacao[ct];
		        flagGravar = true;
        	}
        	if (ct < ltTipoCliente.size())     
        	{
        		pRegistro->idTipoCliente     = ltTipoCliente[ct];
		        flagGravar = true;
        	}
        	if (ct < ltTipoCarteira.size())    
        	{
        		pRegistro->idTipoCarteira    = ltTipoCarteira[ct];
		        flagGravar = true;
        	}
        	if (ct < ltRegional.size())        
        	{
        		pRegistro->idRegional        = ltRegional[ct];
		        flagGravar = true;
        	}
        	if (ct < ltNatureza.size())        
        	{
        		pRegistro->idNatureza        = ltNatureza[ct];
		        flagGravar = true;
        	}
        	if (ct < ltCanal.size())           
        	{
        		pRegistro->idCanal           = ltCanal[ct];
		        flagGravar = true;
        	}
        	if (ct < ltTipoFechamento.size())  
        	{
        		pRegistro->idTipoFechamento  = ltTipoFechamento[ct];
		        flagGravar = true;
        	}
        	if (ct < ltGrupoAbertura.size())   
        	{
        		pRegistro->idGrupoAbertura   = ltGrupoAbertura[ct];
		        flagGravar = true;
        	}
        	if (ct < ltGrupoTratamento.size()) 
        	{
        		pRegistro->idGrupoTratamento = ltGrupoTratamento[ct];
		        flagGravar = true;
        	}
        	if (ct < ltGrupoRetorno.size())    
        	{
        		pRegistro->idGrupoRetorno    = ltGrupoRetorno[ct];
		        flagGravar = true;
        	}
        	if (ct < ltProcedencia.size())     
        	{
        		pRegistro->idProcedencia     = ltProcedencia[ct];
		        flagGravar = true;
        	}
        	
	        if ( flagGravar == false ) break; // Nao existem mais filtros
	        	
	        proCInsereFiltros( limpar,pRegistro ,&ltStmt );
	        
			pRegistro->idTipoLinha       = 0;
			pRegistro->idSegmentacao     = 0;
			pRegistro->idTipoCliente     = 0;
			pRegistro->idTipoCarteira    = 0;
			pRegistro->idRegional        = 0;
			pRegistro->idNatureza        = 0;
			pRegistro->idCanal           = 0;
			pRegistro->idTipoFechamento  = 0;
			pRegistro->idGrupoAbertura   = 0;
			pRegistro->idGrupoTratamento = 0;
			pRegistro->idGrupoRetorno    = 0;
			pRegistro->idProcedencia     = 0;
	        flagGravar = false;

			limpar = false;
        }

	string bf;
	list<string>::iterator it_stmt;
		
	for ( it_stmt = ltStmt.begin(); it_stmt != ltStmt.end(); it_stmt++ )
	{
		bf = *it_stmt;
    	proCGravaFiltros( (char *)bf.c_str() );
    }
    ULOG_END( "Insere()" );

}



void CFiltroArvContato::Selecao( char * idUser, DOMNode *dnode, st_FiltroArvContato *pRegistro ,XMLGen *xml_g )
{
    ULOG_START( "Selecao()" );

    char *p = 0x0;  
         
    p = 0x0;
    if ( p = tx.walkTree( dnode, "idArquivo", 0 ), p ) 
    {
        strcpy( pRegistro->nmArquivo, (char *)p );
        XMLString::release( &p );
        ULOG( "... idArquivo [%s]",(char *)pRegistro->nmArquivo );
#ifdef WIN32
        printf( "... Tipo de Relatorio [%s]\n",(char *)pRegistro->nmArquivo );
#endif
    } 

    ULOG( "nmArquivo [%s]",(char *)pRegistro->nmArquivo );
    
    pRegistro->idUsuario = atoi(idUser);
    proCSelFiltros( pRegistro ,xml_g );


    ULOG_END( "Selecao()" );

}




void CFiltroArvContato::SelecaoArquivos( char * idUser, DOMNode *dnode, st_FiltroArvContato *pRegistro ,XMLGen *xml_g )
{

    ULOG_START( "SelecaoArquivos()" );

    pRegistro->idUsuario = atoi(idUser);
    proCSelArquivos( pRegistro ,xml_g );

    ULOG_END( "SelecaoArquivos()" );

}



void CFiltroArvContato::SetTipoRelatorio( st_FiltroArvContato *pRegistro )
{
    char *sTipoRelatorio[] =
    {
         "Contato"
        ,"Grupo"
        ,"Contato x Grupo"
        ,0x0
    };
    strcpy( pRegistro->nmTipoRelatorio,sTipoRelatorio[pRegistro->idTipoRelatorio-1] );
}
