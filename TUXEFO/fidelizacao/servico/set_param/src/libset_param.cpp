
#include "../../negocio/fidutil/include/retencao.hpp"
#include <string>
#include <list>
#include <iterator>
using namespace std;


#define		TPOFERTA		"TPOFERTA"
#define		REGIONAL		"REGIONAL"
#define		TPCLIENTE		"TPCLIENTE"
#define		TPLINHA	    	"TPLINHA"
#define		TPBONUS  		"TPBONUS"
#define		SEGMENTACAO		"SEGMENTACAO"
#define		TPSERVICO		"TPSERVICO"
#define		INTENCAO		"INTENCAO"
#define		DESTINOS 		"DESTINOS"
#define		DESTINO 		"DESTINO"
#define		GRUPOS		    "GRUPOS"
#define		APARELHOS       "APARELHOS"
#define		OFERTAS         "OFERTAS"
#define		INTENCAO		"INTENCAO"


typedef list<int> LISTA_ID;
list<int>::iterator itRegional;
list<int>::iterator itCliente;
list<int>::iterator itSegmento;
list<int>::iterator itTpLinha;
list<int>::iterator itDestino;
list<int>::iterator itAparelho;
list<int>::iterator itGrupo;
list<int>::iterator itOfertas;
list<int>::iterator itRespostaUnidade;
	

    
extern void RemoveScriptFilhos( char * idUsuarioPrm, char * idScriptPrm, XMLGen * xml );
extern void HabilitacaoScript( char * idUsuarioPrm, char * idScriptPrm, char * inHabilitaPrm, XMLGen * xml );
extern int  InclusaoScript( char * idUsuarioPrm, char * idScriptPrm, char * nmScriptPrm, XMLGen *xml );
extern void InclusaoScriptGrupo( char * idUsuarioPrm, char * idScriptPrm, LISTA_ID * pGrupo, XMLGen * xml );
extern void InclusaoScriptOferta( char * idUsuarioPrm, char * idScriptPrm, LISTA_ID * pOfertas, XMLGen * xml );
extern void InclusaoScriptSegmentacao( char * idUsuarioPrm, char * idScriptPrm, LISTA_ID * pSegmento, XMLGen * xml );
extern void InclusaoScriptTpLinha( char * idUsuarioPrm, char * idScriptPrm, LISTA_ID * pTpLinha, XMLGen * xml );
extern void InclusaoScriptTpPessoa( char * idUsuarioPrm, char * idScriptPrm, LISTA_ID * pCliente, XMLGen * xml );
extern void InclusaoScriptUFOper( char * idUsuarioPrm, char * idScriptPrm, LISTA_ID * pRegional, XMLGen * xml );
extern void InclusaoScriptDestino( char * idUsuarioPrm, char * idScriptPrm, LISTA_ID * pDestino, XMLGen * xml );
extern void InclusaoScriptIntencao( char * idUsuarioPrm, char * idScriptPrm, LISTA_ID * pRespostaUnidade, XMLGen * xml );

    
//extern void BuscaUFOperadora(int idRegional,int * idUFOperadora);

extern void RemoveOfertas( int idOfertaPrm, XMLGen *xml );
extern void InclusaoOfertas( int idUsuarioPrm, char * nmOfertaPrm, char * sgClassifPrm, XMLGen *xml );
extern void AlteracaoOfertas( int idUsuarioPrm, int idOfertaPrm, char * nmOfertaPrm, char * sgClassifPrm, XMLGen *xml );
extern void InsereMatrizOferta( int nrElementosPrm ,
                                int nrTransacaoPrm ,
                                int idUsuarioPrm,
                                int idRespostaIntencaoPrm ,
                                LISTA_ID * pDestino ,
                                int inFidelizacao ,
                                LISTA_ID * pGrupo, 
                                LISTA_ID * pSegmento,
                                LISTA_ID * pOfertas,
                                LISTA_ID * pRespostaUnidade,
                                XMLGen *xml );

extern void RemovePlanos( int idPlanoPrm, XMLGen *xml );
extern int  InclusaoPlanos( int idUsuarioPrm, int idTipoServicoPrm, int  * idPlanoPrm , char * nmPlanoPrm , char * cdServicoPrm , XMLGen *xml );
extern void InclusaoMatrizPlanos( int idUsuarioPrm , LISTA_ID * pRegional, LISTA_ID * pCliente, int idTipoLinhaPrm , int idPlanoPrm , XMLGen *xml );
extern void BuscaRespostaUnidade( LISTA_ID * pRegional, LISTA_ID * pRespostaUnidade, int idRespostaIntencao, LISTA_ID * pDestino, LISTA_ID * pTpLinha, LISTA_ID * pCliente );
extern int  AlteracaoPlanos( int idPlanoPrm, int idTpServico, int idUsuarioPrm, char * nmPlanoPrm, char * cdServicoPrm, XMLGen *xml );
extern void RemocaoMatrizPlanos( int idPlanoPrm );


extern void RemoveBonus( int idBonusPrm, XMLGen *xml );
extern void InclusaoBonus(  int idUsuarioPrm, int  * idBonusPrm, char * vlBonus, char * nmBonusPrm, int qtDiasPrm, int idUnidadeOfertaPrm, int idTpServicoPrm, char * cdServicoPrm, XMLGen *xml );
extern int  AlteracaoBonus(  int idBonusPrm, int idUsuarioPrm, int idTipoBonusPrm, int qtDiasPrm, char * vlBonus, char * nmBonusPrm, char * cdServicoPrm, XMLGen *xml );
extern void InclusaoMatrizBonus( int idUsuarioPrm, LISTA_ID * pRegional, LISTA_ID * pCliente, LISTA_ID * pSegmento, int idBonusPrm, int idTpLinhaPrm, XMLGen *xml );
extern void RemocaoMatrizBonus( int idBonusPrm );


extern void RemoveMigracao( int idMigracaoPrm, XMLGen *xml  );
extern void InclusaoMigracao( int idUsuarioPrm ,
                       char * dsMigracaoPrm ,
                       char * vlBonusPrm ,
                       int    vlValidadePrm ,
                       int    idUFPrm ,
                       int    idClientePrm ,
                       int    idTipoLinhaPrm ,
                       XMLGen *xml );

extern int  AlteracaoMigracao( int idMigracaoPrm, int idUsuarioPrm, char * vlBonusPrm, int vlValidadePrm, char * dsMigracaoPrm, XMLGen *xml );
extern void InclusaoMatrizMigracao( int idUsuarioPrm, int pRegional, int pCliente, int pTpLinha, int idMigracaoPrm, XMLGen *xml );
extern void RemocaoMatrizMigracao( int idMigracaoPrm );
extern void InclusaoMatrizScript( int idUsuarioPrm, 
                                  LISTA_ID * pRegional, 
                                  LISTA_ID * pCliente, 
                                  LISTA_ID * pTpLinha, 
                                  LISTA_ID * pDestino, 
                                  int idIntencaoPrm, 
                                  XMLGen   * xml );

void InclusaoMatrizAparelho( int idUsuarioPrm, 
                             LISTA_ID * pCliente, 
                             LISTA_ID * pAparelho, 
                             LISTA_ID * pSegmento, 
                             LISTA_ID * pRegional, 
                             LISTA_ID * pGrupo, 
                             XMLGen   * xml );
                                  
extern void InclusaoMatrizOfertaFidelizacao( int idMatrizOfertaPrm );                                  
                                  

DECLARE_TUXEDO_SERVICE(SETPARAM);
void implSETPARAM::Execute( DOMNode *dnode, XMLGen *xml_g )
{
	ULOG_START( "SETPARAM" );
	int idUsr = get_idUsuario(getUser());
    
    char buffer[128];
    char idScriptPrm[40];
    char nmScriptPrm[128];
    char inHabilitadoPrm[2];
    int ret = 0;
	
	int idCliente;
	int idTipoLinha;
	int idUF;


	int idUFOperadora;
    int idRespostaIntencao = 0;
    int idRespostaDestino = 0;
    int idRespostaUnidade = 0;
    int inFidelizacao;
    int idTpServico;
    int idPlano;
    int nrTransacao;
    int nrElementos;
	int idOferta;
	int idCadastrado = 0;
	int idTpLinha;
	int idTpBonus;
	int qtDias;
	char vlBonus[256];
	int i,j,k;
	char param[64];
	char processo[64];
	char operacao[64];
	char dsOferta[256];
	char sgOferta[64];
	char dsPlano[256];
	char nmBonus[256];
	char cdServico[256];
	char dsMigracao[256];
    TuxHelper txh;
    DOMNode * dnCombo       = NULL;
    DOMNode * dnTabelas     = NULL;
    DOMNode * dnParam       = NULL;
    DOMNode * dnParamLinha  = NULL;
    DOMNode * dnFiltro      = NULL;
    DOMNode * dnListas      = NULL;
    DOMNode * dnSelecionado = NULL;
    DOMNode * dnIterador    = NULL;
    DOMNode * dnVariaveis   = NULL;
    LISTA_ID       pRegional;
    LISTA_ID       pRegionalCad;
    LISTA_ID       pCliente;
    LISTA_ID       pClienteCad;
    LISTA_ID       pSegmento;
    LISTA_ID       pSegmentoCad;
    LISTA_ID       pTpLinha;
    LISTA_ID       pTpLinhaCad;
	LISTA_ID       pDestino;
	LISTA_ID       pGrupo;
	LISTA_ID       pGrupoCad;
	LISTA_ID       pAparelho;
	LISTA_ID       pAparelhoCad;
	LISTA_ID       pOfertas;
	LISTA_ID       pRespostaUnidade;
	
	get_tag( processo, dnode, "nmProcesso", 0, -1 );
	get_tag( operacao, dnode, "tpProcesso", 0, -1 );
	
	if ( processo[0] != 0x0 )
	{

		if ( !memcmp(processo,"CFGMTZSCRIPTS",13) )
		{
           ULOG( "passou 1" );
           
			if ( !memcmp(operacao,"ALT",3) )
			{
           ULOG( "passou 2" );
                idScriptPrm[0] = 0x0;
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					get_tag( idScriptPrm, dnFiltro, "idCadastrado"  , 0, -1 );
                    ULOG( "idScriptPrm [%s]",idScriptPrm);
                }
			    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
			    if ( dnListas != NULL ) 
			    {
					for ( i=0;;i++ )
					{
					    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
					    if ( dnVariaveis == NULL ) 
					    	break;
					    if ( dnVariaveis != NULL ) 
					    {
							param[0] = 0x0;
							get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
							if ( param[0] == NULL )
								break;
							
						    // Destino
							if ( !memcmp(param,DESTINO,7) )
							{
								pDestino.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
								    
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pDestino.push_back(atoi(param));
									}
								}
							}

						    // Regionais
							if ( !memcmp(param,REGIONAL,8) )
							{
								pRegional.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pRegional.push_back(atoi(param));
									}
								}
							}
								
							// Tipos de Cliente
							if ( !memcmp(param,TPCLIENTE,9) )
							{
								pCliente.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pCliente.push_back(atoi(param));
									}
								}
							}
						
							// Tipo de Linha
							if ( !memcmp(param,TPLINHA,7) )
							{
								pTpLinha.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pTpLinha.push_back(atoi(param));
									}
								}
							}

							// Segmentos
							if ( !memcmp(param,SEGMENTACAO,11) )
							{
								pSegmento.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pSegmento.push_back(atoi(param));
									}
								}
							}

							// Grupos
							if ( !memcmp(param,GRUPOS,6) )
							{
								pGrupo.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pGrupo.push_back(atoi(param));
									}
								}
							}

							// Ofertas
							if ( !memcmp(param,OFERTAS,7) )
							{
    							pOfertas.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pOfertas.push_back(atoi(param));
									}
								}
							}

							// Intencao
							if ( !memcmp(param,INTENCAO,8) )
							{
    							pOfertas.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pRespostaUnidade.push_back(atoi(param));
									}
								}
							}

                        }
					}
					xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                    sprintf( buffer,"%d", idUsr );
                    RemoveScriptFilhos( buffer, idScriptPrm, xml_g );
                    InclusaoScriptGrupo( buffer, idScriptPrm, &pGrupo, xml_g );
                    InclusaoScriptOferta( buffer, idScriptPrm, &pOfertas, xml_g );
                    InclusaoScriptSegmentacao( buffer, idScriptPrm, &pSegmento, xml_g );
                    InclusaoScriptTpLinha( buffer, idScriptPrm, &pTpLinha, xml_g );
                    InclusaoScriptTpPessoa( buffer, idScriptPrm, &pCliente, xml_g );
                    InclusaoScriptUFOper( buffer, idScriptPrm, &pRegional, xml_g );
                    InclusaoScriptDestino( buffer, idScriptPrm, &pDestino, xml_g );
                    InclusaoScriptIntencao( buffer, idScriptPrm, &pRespostaUnidade, xml_g );
					xml_g->closeTag();
                }
            }

			if ( !memcmp(operacao,"INC",3) )
			{
           ULOG( "passou 3" );
                idScriptPrm[0] = 0x0;
                nmScriptPrm[0] = 0x0;
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					get_tag( nmScriptPrm, dnFiltro, "nmCadastrado"  , 0, -1 );
                    ULOG( "nmScriptPrm [%s]",nmScriptPrm);
                }
			    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
			    if ( dnListas != NULL ) 
			    {
					for ( i=0;;i++ )
					{
					    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
					    if ( dnVariaveis == NULL ) 
					    	break;
					    if ( dnVariaveis != NULL ) 
					    {
							param[0] = 0x0;
							get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
							if ( param[0] == NULL )
								break;
							
						    // Destino
							if ( !memcmp(param,DESTINO,7) )
							{
								pDestino.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
								    
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pDestino.push_back(atoi(param));
									}
								}
							}

						    // Regionais
							if ( !memcmp(param,REGIONAL,8) )
							{
								pRegional.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										//BuscaUFOperadora(atoi(param),&idUFOperadora);
										//pRegional.push_back(idUFOperadora);
										pRegional.push_back(atoi(param));
									}
								}
							}
								
							// Tipos de Cliente
							if ( !memcmp(param,TPCLIENTE,9) )
							{
								pCliente.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pCliente.push_back(atoi(param));
									}
								}
							}
						
							// Tipo de Linha
							if ( !memcmp(param,TPLINHA,7) )
							{
								pTpLinha.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pTpLinha.push_back(atoi(param));
									}
								}
							}

							// Segmentos
							if ( !memcmp(param,SEGMENTACAO,11) )
							{
								pSegmento.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pSegmento.push_back(atoi(param));
									}
								}
							}

							// Grupos
							if ( !memcmp(param,GRUPOS,6) )
							{
								pGrupo.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pGrupo.push_back(atoi(param));
									}
								}
							}

							// Ofertas
							if ( !memcmp(param,OFERTAS,7) )
							{
    							pOfertas.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pOfertas.push_back(atoi(param));
									}
								}
							}

							// Intencao
							if ( !memcmp(param,INTENCAO,8) )
							{
    							pOfertas.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pRespostaUnidade.push_back(atoi(param));
									}
								}
							}

                        }
					}
					xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                    sprintf( buffer,"%d", idUsr );
                    ret = InclusaoScript( buffer, idScriptPrm, nmScriptPrm, xml_g );
                    if ( ret == 0 )
                    {
                        InclusaoScriptGrupo( buffer, idScriptPrm, &pGrupo, xml_g );
                        InclusaoScriptOferta( buffer, idScriptPrm, &pOfertas, xml_g );
                        InclusaoScriptSegmentacao( buffer, idScriptPrm, &pSegmento, xml_g );
                        InclusaoScriptTpLinha( buffer, idScriptPrm, &pTpLinha, xml_g );
                        InclusaoScriptTpPessoa( buffer, idScriptPrm, &pCliente, xml_g );
                        InclusaoScriptUFOper( buffer, idScriptPrm, &pRegional, xml_g );
                        InclusaoScriptDestino( buffer, idScriptPrm, &pDestino, xml_g );
                        InclusaoScriptIntencao( buffer, idScriptPrm, &pRespostaUnidade, xml_g );
                    }
					xml_g->closeTag();
                }
            }

			if ( !memcmp(operacao,"HABSELSCRPT",11) )
			{
                idScriptPrm[0] = 0x0;
                inHabilitadoPrm[0] = 0x0;
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					get_tag( idScriptPrm, dnFiltro, "idCadastrado"  , 0, -1 );
                    ULOG( "idScriptPrm [%s]",idScriptPrm);

					get_tag( inHabilitadoPrm, dnFiltro, "inHabilitado"  , 0, -1 );
                    ULOG( "inHabilitado [%s]",inHabilitadoPrm);
                }
                xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                sprintf( buffer,"%d", idUsr );
                HabilitacaoScript( buffer, idScriptPrm, inHabilitadoPrm, xml_g );
                xml_g->closeTag();
            }
        }
        







		if ( !memcmp(processo,"CFGOFERTAS",10) )
		{

			// Chamada de consulta para efetuar alteracao
			if ( !memcmp(operacao,"ALT",3) )
			{
	            get_tag( param, dnode, "codError", 0, -1 );
	            nrTransacao = atoi(param);
	            get_tag( param, dnode, "msgError", 0, -1 );
	            nrElementos = atoi(param);
	            
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					get_tag( param, dnFiltro, "idIntencao"  , 0, -1 );
					idRespostaIntencao = atoi(param);
					get_tag( param, dnFiltro, "inFidelizacao"  , 0, -1 );
					inFidelizacao = atoi(param);
			    }
			    
			    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
			    if ( dnListas != NULL ) 
			    {
					for ( i=0;;i++ )
					{
					    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
					    if ( dnVariaveis == NULL ) 
					    	break;
					    if ( dnVariaveis != NULL ) 
					    {
							param[0] = 0x0;
							get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
							if ( param[0] == NULL )
								break;
							
						    // Destino
							if ( !memcmp(param,DESTINOS,8) )
							{
								pDestino.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
								    
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pDestino.push_back(atoi(param));
									}
								}
							}

						    // Regionais
							if ( !memcmp(param,REGIONAL,8) )
							{
								pRegional.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										//BuscaUFOperadora(atoi(param),&idUFOperadora);
										//pRegional.push_back(idUFOperadora);
                                        pRegional.push_back(atoi(param));
									}
								}
							}
								
							// Tipos de Cliente
							if ( !memcmp(param,TPCLIENTE,9) )
							{
								pCliente.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pCliente.push_back(atoi(param));
									}
								}
							}
						
							// Tipo de Linha
							if ( !memcmp(param,TPLINHA,7) )
							{
								pTpLinha.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pTpLinha.push_back(atoi(param));
									}
								}
							}

							// Segmentos
							if ( !memcmp(param,SEGMENTACAO,11) )
							{
								pSegmento.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pSegmento.push_back(atoi(param));
									}
								}
							}

							// Grupos
							if ( !memcmp(param,GRUPOS,6) )
							{
								pGrupo.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pGrupo.push_back(atoi(param));
									}
								}
							}

							// Ofertas
							if ( !memcmp(param,OFERTAS,7) )
							{
    							pOfertas.clear();
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pOfertas.push_back(atoi(param));
									}
								}
							}

					    }
					}

					xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
					BuscaRespostaUnidade( &pRegional, &pRespostaUnidade, idRespostaIntencao, &pDestino, &pTpLinha, &pCliente );
					ULOG( "Vai inserir MatrizOferta" );
					//char tmp[2048];
					InsereMatrizOferta( nrElementos,nrTransacao, idUsr, idRespostaIntencao, &pDestino, inFidelizacao, &pGrupo, &pSegmento, &pOfertas, &pRespostaUnidade, xml_g );
					xml_g->closeTag();
					
		        }


			} // ALT


		}
		
		if ( !memcmp(processo,"CFGAPARELHOS",12) )
		{
			
			// Chamada de consulta para efetuar alteracao
			if ( !memcmp(operacao,"ALT",3) )
			{
                //  Usado na antiga MatrizAparelhos
	            // get_tag( param, dnode, "codError", 0, -1 );
	            // nrTransacao = atoi(param);
	            // get_tag( param, dnode, "msgError", 0, -1 );
	            // nrElementos = atoi(param);
                
			    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
			    if ( dnListas != NULL ) 
			    {
					for ( i=0;;i++ )
					{
					    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
					    if ( dnVariaveis == NULL ) 
					    	break;
					    if ( dnVariaveis != NULL ) 
					    {
							param[0] = 0x0;
							get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
							if ( param[0] == NULL )
								break;
							
						    // Regionais
							if ( !memcmp(param,REGIONAL,8) )
							{
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
                                        pRegional.push_back(atoi(param));
									}
								}
							}
								
							// Tipos de Cliente
							if ( !memcmp(param,TPCLIENTE,9) )
							{
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pCliente.push_back(atoi(param));
									}
								}
							}
						
							// Segmentos
							if ( !memcmp(param,SEGMENTACAO,11) )
							{
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pSegmento.push_back(atoi(param));
									}
								}
							}

							// Grupos
							if ( !memcmp(param,GRUPOS,6) )
							{
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pGrupo.push_back(atoi(param));
									}
								}
							}

							// Aparelhos
							if ( !memcmp(param,APARELHOS,9) )
							{
								for ( j=0;;j++ )
								{
									dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
									if ( dnSelecionado == NULL )
									    break;
									for ( k=0;;k++ )
									{
								    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
										if ( dnIterador == NULL )
										    break;
										get_tag( param, dnIterador, "id", 0, -1 );
										pAparelho.push_back(atoi(param));
									}
								}
							}

					    }
					}
					
					xml_g->createTag("FidelizacaoVO");
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
					InclusaoMatrizAparelho( idUsr, &pCliente, &pAparelho, &pSegmento, &pRegional, &pGrupo, xml_g );
					xml_g->closeTag();
					
		        }

			}
		}


		if ( !memcmp(processo,"CFGSCRIPT",9) )
		{

			if ( !memcmp(operacao,"ALT",3) )
			{

			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					get_tag( param, dnFiltro, "idIntencao"  , 0, -1 );
					idCadastrado = atoi(param);
					//RemocaoMatrizIntencao( idCadastrado );
				    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
				    if ( dnListas != NULL ) 
				    {
						for ( i=0;;i++ )
						{
						    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
						    if ( dnVariaveis == NULL ) 
						    	break;
						    if ( dnVariaveis != NULL ) 
						    {
								param[0] = 0x0;
								get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
								if ( param[0] == NULL )
									break;
								
							    // Regionais
								if ( !memcmp(param,REGIONAL,8) )
								{
									for ( j=0;;j++ )
									{
										dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
										if ( dnSelecionado == NULL )
										    break;
										for ( k=0;;k++ )
										{
									    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
											if ( dnIterador == NULL )
											    break;
											get_tag( param, dnIterador, "id", 0, -1 );
											//BuscaUFOperadora(atoi(param),&idUFOperadora);
											pRegional.push_back(atoi(param));
										}
									}
								}
									
								// Tipos de Cliente
								if ( !memcmp(param,TPCLIENTE,9) )
								{
									for ( j=0;;j++ )
									{
										dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
										if ( dnSelecionado == NULL )
										    break;
										for ( k=0;;k++ )
										{
									    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
											if ( dnIterador == NULL )
											    break;
											get_tag( param, dnIterador, "id", 0, -1 );
											pCliente.push_back(atoi(param));
										}
									}
								}
							
								// Tipo de Linha
								if ( !memcmp(param,TPLINHA,7) )
								{
									for ( j=0;;j++ )
									{
										dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
										if ( dnSelecionado == NULL )
										    break;
										for ( k=0;;k++ )
										{
									    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
											if ( dnIterador == NULL )
											    break;
											get_tag( param, dnIterador, "id", 0, -1 );
											pTpLinha.push_back(atoi(param));
										}
									}
								}

								// Destino
								if ( !memcmp(param,DESTINO,7) )
								{
									for ( j=0;;j++ )
									{
										dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
										if ( dnSelecionado == NULL )
										    break;
										for ( k=0;;k++ )
										{
									    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
											if ( dnIterador == NULL )
											    break;
											get_tag( param, dnIterador, "id", 0, -1 );
											pDestino.push_back(atoi(param));
										}
									}
								}

                            }
						}
						xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
						xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
						InclusaoMatrizScript( idUsr, &pRegional, &pCliente, &pTpLinha, &pDestino, idCadastrado, xml_g );
						xml_g->closeTag();
					}
						
				}
			} // -- ALT

    	}




		if ( !memcmp(processo,"PARAMOFERTAS",12) )
		{
			if ( !memcmp(operacao,"EXC",3) )
			{
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
					get_tag( param, dnFiltro, "idCadastrado", 0, -1 );
					idOferta = atoi(param);
			    	RemoveOfertas( idOferta, xml_g );
					xml_g->closeTag();
				}
			}

			if ( !memcmp(operacao,"CAD",3) )
			{
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
					get_tag( dsOferta, dnFiltro, "dsOferta", 0, -1 );
					get_tag( sgOferta, dnFiltro, "sgOferta", 0, -1 );
			    	InclusaoOfertas( idUsr, dsOferta, sgOferta, xml_g );
					xml_g->closeTag();
				}
			}

			if ( !memcmp(operacao,"ALT",3) )
			{
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
					get_tag( param, dnFiltro,  "idCadastrado", 0, -1 );
					idOferta = atoi(param);
					get_tag( dsOferta, dnFiltro, "dsOferta", 0, -1 );
					get_tag( sgOferta, dnFiltro, "sgOferta", 0, -1 );
			    	AlteracaoOfertas( idUsr, idOferta, dsOferta, sgOferta, xml_g );
					xml_g->closeTag();
				}
			}

		}

		if ( !memcmp(processo,"MANPLANOS",9) )
		{
			if ( !memcmp(operacao,"EXC",3) )
			{
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
					get_tag( param, dnFiltro, "idCadastrado", 0, -1 );
					idPlano = atoi(param);
					RemocaoMatrizPlanos( idPlano );
			    	RemovePlanos( idPlano, xml_g );
					xml_g->closeTag();
				}
			}

			if ( !memcmp(operacao,"CAD",3) )
			{
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					get_tag( param, dnFiltro, "tpLinha"  , 0, -1 );
					idTpLinha = atoi(param);
					get_tag( dsPlano  , dnFiltro, "dsPlano"  , 0, -1 );
					get_tag( cdServico, dnFiltro, "cdServico", 0, -1 );
					get_tag( param, dnFiltro, "tpServico", 0, -1 );
					idTpServico = atoi(param);
					if ( InclusaoPlanos( idUsr, idTpServico, &idPlano, dsPlano, cdServico, xml_g ) == 0 )
					{
						ULOG( "idPlano gerado [%d]",idPlano );
					    
					    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
					    if ( dnListas != NULL ) 
					    {
							for ( i=0;;i++ )
							{
							    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
							    if ( dnVariaveis == NULL ) 
							    	break;
							    if ( dnVariaveis != NULL ) 
							    {
									param[0] = 0x0;
									get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
									if ( param[0] == NULL )
										break;
									
								    // Regionais
									if ( !memcmp(param,REGIONAL,8) )
									{
										for ( j=0;;j++ )
										{
											dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
											if ( dnSelecionado == NULL )
											    break;
											for ( k=0;;k++ )
											{
										    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
												if ( dnIterador == NULL )
												    break;
												get_tag( param, dnIterador, "id", 0, -1 );
												//BuscaUFOperadora(atoi(param),&idUFOperadora);
												//pRegional.push_back(idUFOperadora);
                                                pRegional.push_back(atoi(param));
											}
										}
									}
										
									// Tipos de Cliente
									if ( !memcmp(param,TPCLIENTE,9) )
									{
										for ( j=0;;j++ )
										{
											dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
											if ( dnSelecionado == NULL )
											    break;
											for ( k=0;;k++ )
											{
										    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
												if ( dnIterador == NULL )
												    break;
												get_tag( param, dnIterador, "id", 0, -1 );
												pCliente.push_back(atoi(param));
											}
										}
									}
								
	                            }
							}
							// Selecao de Regionais e Clientes
							xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
							xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
	
							ULOG( "Passando idPlano [%d]",idPlano );
	
							InclusaoMatrizPlanos( idUsr, &pRegional, &pCliente, idTpLinha, idPlano, xml_g );
							xml_g->closeTag();
						}
						
					}

						
				}
					
			}  // CAD




			if ( !memcmp(operacao,"ALT",3) )
			{


			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					get_tag( param, dnFiltro, "idCadastrado"  , 0, -1 );
					idCadastrado = atoi(param);
					get_tag( param, dnFiltro, "tpLinha"  , 0, -1 );
					idTpLinha = atoi(param);
					get_tag( dsPlano  , dnFiltro, "dsPlano"  , 0, -1 );
					get_tag( cdServico, dnFiltro, "cdServico", 0, -1 );
					get_tag( param, dnFiltro, "tpServico", 0, -1 );
					idTpServico = atoi(param);
					if ( AlteracaoPlanos( idCadastrado, idTpServico, idUsr, dsPlano, cdServico, xml_g ) == 0 )
					{
						RemocaoMatrizPlanos( idCadastrado );
					    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
					    if ( dnListas != NULL ) 
					    {
							for ( i=0;;i++ )
							{
							    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
							    if ( dnVariaveis == NULL ) 
							    	break;
							    if ( dnVariaveis != NULL ) 
							    {
									param[0] = 0x0;
									get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
									if ( param[0] == NULL )
										break;
									
								    // Regionais
									if ( !memcmp(param,REGIONAL,8) )
									{
										for ( j=0;;j++ )
										{
											dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
											if ( dnSelecionado == NULL )
											    break;
											for ( k=0;;k++ )
											{
										    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
												if ( dnIterador == NULL )
												    break;
												get_tag( param, dnIterador, "id", 0, -1 );
												//BuscaUFOperadora(atoi(param),&idUFOperadora);
												//pRegional.push_back(idUFOperadora);
                                                pRegional.push_back(atoi(param));
											}
										}
									}
										
									// Tipos de Cliente
									if ( !memcmp(param,TPCLIENTE,9) )
									{
										for ( j=0;;j++ )
										{
											dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
											if ( dnSelecionado == NULL )
											    break;
											for ( k=0;;k++ )
											{
										    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
												if ( dnIterador == NULL )
												    break;
												get_tag( param, dnIterador, "id", 0, -1 );
												pCliente.push_back(atoi(param));
											}
										}
									}
								
	                            }
							}
							
							// Selecao de Regionais e Clientes
							xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
							xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
							InclusaoMatrizPlanos( idUsr, &pRegional, &pCliente, idTpLinha, idCadastrado, xml_g );
							xml_g->closeTag();
	
						}
					}
						
				}
			} // -- ALT




	    }





		if ( !memcmp(processo,"MANBONUS",9) )
		{
			if ( !memcmp(operacao,"EXC",3) )
			{
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
					get_tag( param, dnFiltro, "idCadastrado", 0, -1 );
					idCadastrado = atoi(param);
					RemoveBonus( idCadastrado, xml_g );
					xml_g->closeTag();
				}
			}


			if ( !memcmp(operacao,"CAD",3) )
			{

			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
					get_tag( param, dnFiltro, "tpLinha"  , 0, -1 );
					idTpLinha = atoi(param);
					get_tag( nmBonus  , dnFiltro, "nmBonus"  , 0, -1 );
					get_tag( cdServico, dnFiltro, "cdServico", 0, -1 );
					get_tag( param, dnFiltro, "vdBonus", 0, -1 );
					qtDias = atoi(param);
					get_tag( param, dnFiltro, "vlBonus", 0, -1 );
					strcpy( vlBonus,param );

					get_tag( param, dnFiltro, "tpBonus", 0, -1 );
					idTpBonus = atoi(param);

					get_tag( param, dnFiltro, "tpServico", 0, -1 );
					idTpServico = atoi(param);
					
					InclusaoBonus( idUsr, &idCadastrado, vlBonus, nmBonus, qtDias, idTpBonus, idTpServico, cdServico, xml_g );

				    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
				    if ( dnListas != NULL ) 
				    {
						for ( i=0;;i++ )
						{
						    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
						    if ( dnVariaveis == NULL ) 
						    	break;
						    if ( dnVariaveis != NULL ) 
						    {
								param[0] = 0x0;
								get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
								if ( param[0] == NULL )
									break;
								
							    // Regionais
								if ( !memcmp(param,REGIONAL,8) )
								{
									for ( j=0;;j++ )
									{
										dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
										if ( dnSelecionado == NULL )
										    break;
										for ( k=0;;k++ )
										{
									    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
											if ( dnIterador == NULL )
											    break;
											get_tag( param, dnIterador, "id", 0, -1 );
											//BuscaUFOperadora(atoi(param),&idUFOperadora);
											//pRegional.push_back(idUFOperadora);
                                            pRegional.push_back(atoi(param));
										}
									}
								}
									
								// Tipos de Cliente
								if ( !memcmp(param,TPCLIENTE,9) )
								{
									for ( j=0;;j++ )
									{
										dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
										if ( dnSelecionado == NULL )
										    break;
										for ( k=0;;k++ )
										{
									    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
											if ( dnIterador == NULL )
											    break;
											get_tag( param, dnIterador, "id", 0, -1 );
											pCliente.push_back(atoi(param));
										}
									}
								}
							
								// Segmento
								if ( !memcmp(param,SEGMENTACAO,11) )
								{
									for ( j=0;;j++ )
									{
										dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
										if ( dnSelecionado == NULL )
										    break;
										for ( k=0;;k++ )
										{
									    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
											if ( dnIterador == NULL )
											    break;
											get_tag( param, dnIterador, "id", 0, -1 );
											pSegmento.push_back(atoi(param));
										}
									}
								}
                            }
						}

						xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
						xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
						InclusaoMatrizBonus( idUsr, &pRegional, &pCliente, &pSegmento, idCadastrado, idTpLinha, xml_g );
						xml_g->closeTag();

					}
						
				}

					
					
			} // -- CAD

			if ( !memcmp(operacao,"ALT",3) )
			{

			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					get_tag( param, dnFiltro, "idCadastrado"  , 0, -1 );
					idCadastrado = atoi(param);

					get_tag( param, dnFiltro, "tpLinha"  , 0, -1 );
					idTpLinha = atoi(param);
					get_tag( nmBonus  , dnFiltro, "nmBonus"  , 0, -1 );
					get_tag( cdServico, dnFiltro, "cdServico", 0, -1 );
					get_tag( param, dnFiltro, "vdBonus", 0, -1 );
					qtDias = atoi(param);
					get_tag( param, dnFiltro, "vlBonus", 0, -1 );
					strcpy( vlBonus,param );

					get_tag( param, dnFiltro, "tpBonus", 0, -1 );
					idTpBonus = atoi(param);


				    if ( AlteracaoBonus( idCadastrado, idUsr, idTpBonus, qtDias, vlBonus, nmBonus, cdServico, xml_g ) == 0 )
				    {
						RemocaoMatrizBonus( idCadastrado );
					    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
					    if ( dnListas != NULL ) 
					    {
							for ( i=0;;i++ )
							{
							    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
							    if ( dnVariaveis == NULL ) 
							    	break;
							    if ( dnVariaveis != NULL ) 
							    {
									param[0] = 0x0;
									get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
									if ( param[0] == NULL )
										break;
									
								    // Regionais
									if ( !memcmp(param,REGIONAL,8) )
									{
										for ( j=0;;j++ )
										{
											dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
											if ( dnSelecionado == NULL )
											    break;
											for ( k=0;;k++ )
											{
										    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
												if ( dnIterador == NULL )
												    break;
												get_tag( param, dnIterador, "id", 0, -1 );
												//BuscaUFOperadora(atoi(param),&idUFOperadora);
												//pRegional.push_back(idUFOperadora);
                                                pRegional.push_back(atoi(param));
											}
										}
									}
										
									// Tipos de Cliente
									if ( !memcmp(param,TPCLIENTE,9) )
									{
										for ( j=0;;j++ )
										{
											dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
											if ( dnSelecionado == NULL )
											    break;
											for ( k=0;;k++ )
											{
										    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
												if ( dnIterador == NULL )
												    break;
												get_tag( param, dnIterador, "id", 0, -1 );
												pCliente.push_back(atoi(param));
											}
										}
									}
								
									// Segmento
									if ( !memcmp(param,SEGMENTACAO,11) )
									{
										for ( j=0;;j++ )
										{
											dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
											if ( dnSelecionado == NULL )
											    break;
											for ( k=0;;k++ )
											{
										    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
												if ( dnIterador == NULL )
												    break;
												get_tag( param, dnIterador, "id", 0, -1 );
												pSegmento.push_back(atoi(param));
											}
										}
									}
	                            }
							}
	
							xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
							xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
							InclusaoMatrizBonus( idUsr, &pRegional, &pCliente, &pSegmento, idCadastrado, idTpLinha, xml_g );
							xml_g->closeTag();
						}
					}
						
				}
			} // -- ALT


		} //--  MANBONUS



		if ( !memcmp(processo,"CFGMIGRA",9) )
		{
			if ( !memcmp(operacao,"EXC",3) )
			{
			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
					xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
					get_tag( param, dnFiltro, "idCadastrado", 0, -1 );
					idCadastrado = atoi(param);
					RemoveMigracao( idCadastrado,xml_g );
					xml_g->closeTag();
				}
			}


			if ( !memcmp(operacao,"CAD",3) )
			{

			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					get_tag( dsMigracao, dnFiltro, "dsMigra", 0, -1 );
					get_tag( param, dnFiltro, "vdMigra", 0, -1 );
					qtDias = atoi(param);
					get_tag( param, dnFiltro, "vlBonus", 0, -1 );
					strcpy( vlBonus,param );


				    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
				    if ( dnListas != NULL ) 
				    {
						for ( i=0;;i++ )
						{
						    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
						    if ( dnVariaveis == NULL ) 
						    	break;
						    if ( dnVariaveis != NULL ) 
						    {
								param[0] = 0x0;
								get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
								if ( param[0] == NULL )
									break;
								
							    // Regionais
								if ( !memcmp(param,REGIONAL,8) )
								{
									for ( j=0;;j++ )
									{
										dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
										if ( dnSelecionado == NULL )
										    break;
										for ( k=0;;k++ )
										{
									    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
											if ( dnIterador == NULL )
											    break;
											get_tag( param, dnIterador, "id", 0, -1 );
                                            idUF = atoi(param);
											//BuscaUFOperadora(atoi(param),&idUF);
											//pRegional.push_back(idUFOperadora);
										}
									}
								}
									
								// Tipos de Cliente
								if ( !memcmp(param,TPCLIENTE,9) )
								{
									for ( j=0;;j++ )
									{
										dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
										if ( dnSelecionado == NULL )
										    break;
										for ( k=0;;k++ )
										{
									    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
											if ( dnIterador == NULL )
											    break;
											get_tag( param, dnIterador, "id", 0, -1 );
											idCliente = atoi(param);
											//pCliente.push_back(atoi(param));
										}
									}
								}
							
								// Tipo de Linha
								if ( !memcmp(param,TPLINHA,7) )
								{
									for ( j=0;;j++ )
									{
										dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
										if ( dnSelecionado == NULL )
										    break;
										for ( k=0;;k++ )
										{
									    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
											if ( dnIterador == NULL )
											    break;
											get_tag( param, dnIterador, "id", 0, -1 );
											idTipoLinha = atoi(param);
											//pTpLinha.push_back(atoi(param));
										}
									}
								}

                            }
						}
						xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
						xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
						InclusaoMigracao( idUsr, dsMigracao, vlBonus, qtDias, idUF, idCliente, idTipoLinha, xml_g );
						xml_g->closeTag();
					}
						
				}

					
					
			} // -- CAD

			if ( !memcmp(operacao,"ALT",3) )
			{

			    dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
			    if ( dnFiltro != NULL ) 
			    {
					get_tag( param, dnFiltro, "idCadastrado"  , 0, -1 );
					idCadastrado = atoi(param);

					get_tag( dsMigracao, dnFiltro, "dsMigra", 0, -1 );
					get_tag( param, dnFiltro, "vdMigra", 0, -1 );
					qtDias = atoi(param);
					get_tag( param, dnFiltro, "vlBonus", 0, -1 );
					strcpy( vlBonus,param );

					if ( AlteracaoMigracao( idCadastrado, idUsr, vlBonus, qtDias, dsMigracao, xml_g ) == 0 )
					{	
						RemocaoMatrizMigracao( idCadastrado );
						
					    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
					    if ( dnListas != NULL ) 
					    {
							for ( i=0;;i++ )
							{
							    dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
							    if ( dnVariaveis == NULL ) 
							    	break;
							    if ( dnVariaveis != NULL ) 
							    {
									param[0] = 0x0;
									get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
									if ( param[0] == NULL )
										break;
									
								    // Regionais
									if ( !memcmp(param,REGIONAL,8) )
									{
										for ( j=0;;j++ )
										{
											dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
											if ( dnSelecionado == NULL )
											    break;
											for ( k=0;;k++ )
											{
										    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
												if ( dnIterador == NULL )
												    break;
												get_tag( param, dnIterador, "id", 0, -1 );
                                                idUF = atoi(param);
												//BuscaUFOperadora(atoi(param),&idUF);
												//pRegional.push_back(idUFOperadora);
											}
										}
									}
										
									// Tipos de Cliente
									if ( !memcmp(param,TPCLIENTE,9) )
									{
										for ( j=0;;j++ )
										{
											dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
											if ( dnSelecionado == NULL )
											    break;
											for ( k=0;;k++ )
											{
										    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
												if ( dnIterador == NULL )
												    break;
												get_tag( param, dnIterador, "id", 0, -1 );
												idCliente = atoi(param);
												//pCliente.push_back(atoi(param));
											}
										}
									}
								
									// Tipo de Linha
									if ( !memcmp(param,TPLINHA,7) )
									{
										for ( j=0;;j++ )
										{
											dnSelecionado = txh.walkDOM( dnVariaveis, "Selecionado", j );
											if ( dnSelecionado == NULL )
											    break;
											for ( k=0;;k++ )
											{
										    	dnIterador = txh.walkDOM( dnSelecionado, "It", k );
												if ( dnIterador == NULL )
												    break;
												get_tag( param, dnIterador, "id", 0, -1 );
												idTipoLinha = atoi(param);
												//pTpLinha.push_back(atoi(param));
											}
										}
									}
	
	                            }
							}
							xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
							xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
							InclusaoMatrizMigracao( idUsr, idUF, idCliente, idTipoLinha, idCadastrado, xml_g );
							xml_g->closeTag();
						}
					}
						
				}
			} // -- ALT


		} // --  CFGMIGRA



	}
	
	setStatusCode(OKFID,OKMSG);
	
	ULOG_END("SETPARAM");
}
