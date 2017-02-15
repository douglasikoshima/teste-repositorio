
#include "../../negocio/fidutil/include/retencao.hpp"
#include <string>
#include <list>
#include <iterator>
using namespace std;

#define     OFERTAS         "OFERTAS"
#define     TPOFERTA        "TPOFERTA"
#define     REGIONAL        "REGIONAL"
#define     TPCLIENTE       "TPCLIENTE"
#define     TPLINHA         "TPLINHA"
#define     TPBONUS         "TPBONUS"
#define     SEGMENTACAO     "SEGMENTACAO"
#define     TPSERVICO       "TPSERVICO"
#define     INTENCAO        "INTENCAO"
#define     DESTINO         "DESTINO"
#define     GRUPOS          "GRUPOS"
#define     GRPPACOTE       "GRPPACOTE"

typedef list<int> LISTA_ID;
list<int>::iterator itRegional;
list<int>::iterator itCliente;
list<int>::iterator itSegmento;
list<int>::iterator itTpLinha;
list<int>::iterator itDestino;
list<int>::iterator itGrupo;
list<int>::iterator itRespostaUnidade;



DECLARE_TUXEDO_SERVICE(GETLISTAS);
extern void get_listaGruposPacote( XMLGen *xml );
extern void sel_Script( char * idScriptPrm, XMLGen * xml );
extern void sel_TipoLinhaScript( char * idScriptPrm, XMLGen * xml );
extern void sel_TipoPessoaScript( char * idScriptPrm, XMLGen * xml );
extern void sel_SegmentacaoScript( char * idScriptPrm, XMLGen * xml );
extern void sel_UFScript( char * idScriptPrm, XMLGen * xml );
extern void sel_GrupoScript( char * idScriptPrm, XMLGen * xml );
extern void sel_OfertaScript( char * idScriptPrm, XMLGen * xml );
extern void sel_RDestinoScript( char * idScriptPrm, XMLGen * xml );
extern void sel_RIntencaoScript( char * idScriptPrm, XMLGen * xml );
extern void get_listaOferta( XMLGen *xml );
extern void GetScripts( XMLGen *xml );
extern void BuscaRespostaUnidade( LISTA_ID * pRegional, LISTA_ID * pRespostaUnidade, int idRespostaIntencao, LISTA_ID * pDestino, LISTA_ID * pTpLinha, LISTA_ID * pCliente );
//extern void BuscaUFOperadora(int idRegionalPrm, int * idUFOperadoraPrm );
extern void get_listaTipoCliente( XMLGen *xml );
extern void get_listaTipoLinha( XMLGen *xml );
extern void get_listaRegionais( XMLGen *xml );
extern void get_listaSegmentacao( XMLGen *xml );
extern void sel_Regionais( int idPlanoPrm, XMLGen *xml );
extern void get_listaTipoOferta( XMLGen *xml );
extern void get_listaTipoServico( XMLGen *xml );
extern void get_listaIntencao( XMLGen *xml );
extern void get_listaDestino( XMLGen *xml );
extern void get_listaGrupo( XMLGen *xml );
extern void get_ListaIntencaoOfertas( LISTA_ID * pRegional, 
                                      LISTA_ID * pCliente, 
                                      LISTA_ID * pTpLinha, 
                                      LISTA_ID * pGrupo, 
                                      LISTA_ID * pSegmento, 
                                      XMLGen   * xml );

extern void sel_Destinos( int idIntencao,
                          LISTA_ID * pRegional, 
                          LISTA_ID * pCliente, 
                          LISTA_ID * pTpLinha, 
                          LISTA_ID * pGrupo, 
                          LISTA_ID * pSegmento, 
                          XMLGen   * xml );

extern void sel_Ofertas( int idIntencao,
                  		 LISTA_ID * pGrupo, 
                  		 LISTA_ID * pSegmento, 
                  		 LISTA_ID * pDestino, 
                  		 XMLGen   * xml );


extern void ListaOfertas( char * sgOferta, char * nmOferta, XMLGen *xml );

extern void ListaPlanos( int idRegionalPrm, char * idTipoClientePrm, int idTipoLinhaPrm, XMLGen *xml );
extern void ConsultaPlano( int idPlanoPrm, XMLGen *xml );

extern void ListaBonus( char * idRegionalPrm, char * idTipoClientePrm, char * idTipoLinhaPrm, char * idSegmentacaoPrm, char * idTipoBonusPrm, XMLGen *xml );
extern void get_listaTipoBonus( XMLGen *xml );
extern void sel_RegionaisBonus( int idBonusPrm, XMLGen *xml );
extern void sel_TipoClienteBonus( int idBonusPrm, XMLGen *xml );
extern void sel_SegmentacaoBonus( int idBonusPrm, XMLGen *xml );
extern void ConsultaBonus( int idBonusPrm, XMLGen *xml );

extern void RemoveOfertas( int idOfertaPrm, XMLGen *xml );
extern void sel_Regionais( int idPlanoPrm, XMLGen *xml );
extern void sel_TipoCliente( int idPlanoPrm, XMLGen *xml );


extern void ListaMigracao( int idRegionalPrm, char * idTipoClientePrm, int idTipoLinhaPrm, XMLGen *xml );
extern void ConsultaMigracao( int idPlanoPrm, XMLGen *xml );
extern void sel_RegionaisMigracao( int idMigracaoPrm, XMLGen *xml );
extern void sel_TipoClienteMigracao( int idMigraPrm, XMLGen *xml );
extern void sel_TipoLinhaMigracao( int idMigracaoPrm, XMLGen *xml );


extern void sel_RegionaisIntencao( int idIntencaoPrm, XMLGen *xml );
extern void sel_TipoClienteIntencao( int idIntencaoPrm, XMLGen *xml );
extern void sel_TipoLinhaIntencao( int idIntencaoPrm, XMLGen *xml );
extern void sel_DestinoIntencao( int idIntencaoPrm, XMLGen *xml );
extern void ConsultaIntencao( int idIntencaoPrm, XMLGen *xml );
extern void sel_Aparelhos( LISTA_ID * pRegional, 
                           LISTA_ID * pCliente, 
                           LISTA_ID * pGrupo, 
                           LISTA_ID * pSegmento, 
                           XMLGen   * xml );

void implGETLISTAS::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START( "GETLISTAS" );
    // int idUsr = get_idUsuario(getUser());

    LISTA_ID       pRegional;
    LISTA_ID       pCliente;
    LISTA_ID       pSegmento;
    LISTA_ID       pTpLinha;
    LISTA_ID       pDestino;
    LISTA_ID       pGrupo;
    LISTA_ID       pRespostaUnidade;

    int idIntencao;
    int achou = 0;  
    int i,j,k;
    int idOferta;
    int idRegional;
    int idTipoCliente;
    int idTipoLinha;
    int idSegmentacao;
    int idTipoBonus;
    int idCadastrado;
    int idUFOperadora;
                    
    char idScript[64];
    char szidRegional[64];
    char szidTipoCliente[64];
    char szidSegmentacao[64];
    char szidTipoBonus[64];
    char szidTipoLinha[64];

    szidRegional[0] = 0x0;
    szidTipoCliente[0] = 0x0;
    szidSegmentacao[0] = 0x0;
    szidTipoBonus[0] = 0x0;
    szidTipoLinha[0] = 0x0;


    char param[64];
    char processo[64];
    char operacao[64];
    char nmOferta[256];
    char sgOferta[256];
    nmOferta[0] = 0x0;
    sgOferta[0] = 0x0;

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

    get_tag( processo, dnode, "nmProcesso", 0, -1 );
    get_tag( operacao, dnode, "tpProcesso", 0, -1 );
    ULOG( "processo [%s]",processo );
    ULOG( "operacao [%s]",operacao );

    if ( processo[0] != 0x0 )
    {
      ULOG( "passou 1" );
        if ( !memcmp(processo,"CFGMTZSCRIPTS",13) )
        {
          ULOG( "log 1" );
            if ( !memcmp(operacao,"SELSCRPT",8) )
            {
                dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
                if ( dnFiltro != NULL ) 
                {
                    get_tag( idScript, dnFiltro, "idCadastrado"  , 0, -1 );


                    dnCombo = txh.walkDOM( dnode, "Combos", 0 );
                    if ( dnCombo != NULL ) 
                    {
                        xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                        xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                        sel_Script( idScript, xml_g );
                        xml_g->createTag("ListasVO");
                        for ( i=0;;i++ )
                        {
                            param[0] = 0x0;
                            get_tag( param, dnCombo, "nmSelect", i, -1 );
                            ULOG( "param log 3[%s]",param );
                            if ( param[0] == NULL )
                                break;

                            // Tipo Linha
                            if ( !memcmp(param,TPLINHA,7) )
                                sel_TipoLinhaScript( idScript, xml_g );

                            // Tipo Cliente
                            if ( !memcmp(param,TPCLIENTE,9) )
                                sel_TipoPessoaScript( idScript, xml_g );
                                
                            // Segmentacao
                            if ( !memcmp(param,SEGMENTACAO,11) )
                                sel_SegmentacaoScript( idScript, xml_g );
                            
                            // Regionais
                            if ( !memcmp(param,REGIONAL,8) )
                                sel_UFScript( idScript, xml_g );
                                
                            // Lista de Grupos
                            if ( !memcmp(param,GRUPOS,6) )
                                sel_GrupoScript( idScript, xml_g );
                            
                            // Oferta
                            if ( !memcmp(param,OFERTAS,7) )
                                sel_OfertaScript( idScript, xml_g );
                            
                            // Lista de Destinos
                            if ( !memcmp(param,DESTINO,7) )
                                sel_RDestinoScript( idScript, xml_g );

                            // Lista de Intencoes
                            if ( !memcmp(param,INTENCAO,8) )
                                sel_RIntencaoScript( idScript, xml_g );
                        }
                        xml_g->closeTag();
                    xml_g->closeTag();
                    }
                    achou = 1;
                }
            }

            if ( !memcmp(operacao,"GETSCRPT",8) )
            {
          ULOG( "log 2" );
                xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                    xml_g->createTag("ListasVO");
                        GetScripts( xml_g );
                    xml_g->closeTag();
                xml_g->closeTag();
                achou = 1;
            }

            if ( !memcmp(operacao,"GETNEWSCRPT",11) )
            {
                ULOG( "log 3" );
                dnCombo = txh.walkDOM( dnode, "Combos", 0 );
                if ( dnCombo != NULL ) 
                {
                    xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                    xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                    xml_g->createTag("ListasVO");
                    for ( i=0;;i++ )
                    {
                        param[0] = 0x0;
                        get_tag( param, dnCombo, "nmSelect", i, -1 );
                        ULOG( "param log 3[%s]",param );
                        if ( param[0] == NULL )
                            break;

                        // Oferta
                        if ( !memcmp(param,OFERTAS,7) )
                            get_listaOferta( xml_g );
                        
                        // Tipo Cliente
                        if ( !memcmp(param,TPCLIENTE,9) )
                            get_listaTipoCliente( xml_g );

                        // Tipo Linha
                        if ( !memcmp(param,TPLINHA,7) )
                            get_listaTipoLinha( xml_g );

                        // Regionais
                        if ( !memcmp(param,REGIONAL,8) )
                            get_listaRegionais( xml_g );
                        
                        // Tipo Bonus
                        if ( !memcmp(param,TPBONUS,7) )
                            get_listaTipoBonus( xml_g );


                        // Segmentacao
                        if ( !memcmp(param,SEGMENTACAO,11) )
                            get_listaSegmentacao( xml_g );

                        // Tipo Servico
                        if ( !memcmp(param,TPSERVICO,9) )
                            get_listaTipoServico( xml_g );

                        // Lista de Intencoes
                        if ( !memcmp(param,INTENCAO,8) )
                            get_listaIntencao( xml_g );

                        // Lista de Destinos
                        if ( !memcmp(param,DESTINO,7) )
                            get_listaDestino( xml_g );

                        // Lista de Grupos
                        if ( !memcmp(param,GRUPOS,6) )
                            get_listaGrupo( xml_g );
                                        
                    }
                    achou = 1;
                    xml_g->closeTag();
                    xml_g->closeTag();
                }
            }                   
            
        }
        
        if ( !memcmp(processo,"PARAMOFERTAS",12) )
        {
          ULOG( "log 4" );
            if ( !memcmp(operacao,"PSQ",3) )
            {
          ULOG( "log 5" );
                dnFiltro = txh.walkDOM( dnode, "Filtro", 0 );
                if ( dnFiltro != NULL ) 
                {
          ULOG( "log 6" );
                    xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                    xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                    get_tag( param, dnFiltro, "dsOferta", 0, -1 );
                    if ( param[0] != 0x0 ) 
                       strcpy( nmOferta,param );
                    get_tag( param, dnFiltro, "sgTpOferta", 0, -1 );
                    if ( param[0] != 0x0 ) 
                       strcpy( sgOferta,param );
                    ListaOfertas( sgOferta, nmOferta, xml_g );
                    xml_g->closeTag();
                    achou = 1;
                }
            }

        }

        if ( !memcmp(processo,"CFGAPARELHOS",12) )
        {
            
          ULOG( "log 7" );
            // Chamada de consulta para efetuar alteracao
            if ( !memcmp(operacao,"PSQ",3) )
            {
          ULOG( "log 8" );
                    dnListas = txh.walkDOM( dnode, "ListasVO", 0 );
                    if ( dnListas != NULL ) 
                    {
          ULOG( "log 9" );
                        for ( i=0;;i++ )
                        {
                            dnVariaveis = txh.walkDOM( dnListas, "Lista", i );
                            if ( dnVariaveis == NULL ) 
                                break;
                            if ( dnVariaveis != NULL ) 
                            {
          ULOG( "log 10" );
                                param[0] = 0x0;
                                get_tag( param, dnVariaveis, "nmSelect", 0, -1 );
                                if ( param[0] == NULL )
                                    break;
                                
                                // Regionais
                                if ( !memcmp(param,REGIONAL,8) )
                                {
          ULOG( "log 11" );
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
          ULOG( "log 12" );
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
          ULOG( "log 14" );
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
          ULOG( "log 15" );
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

                            }
                        }
                        
                    }

                    xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                    xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                    xml_g->createTag("ListasVO");

                    sel_Aparelhos( &pRegional, &pCliente, &pGrupo, &pSegmento, xml_g );

                    xml_g->closeTag();
                    xml_g->closeTag();
                    achou = 1;

            }
        }
        
        if ( !memcmp(processo,"CFGSCRIPT",9) )
        {
            ULOG( "log 16" );
            // Chamada de consulta para efetuar alteracao
            if ( !memcmp(operacao,"CON",3) )
            {
                ULOG( "log 17" );
                xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");

                dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
                if ( dnFiltro != NULL ) 
                {
                    get_tag( param, dnFiltro, "idIntencao"  , 0, -1 );
                    idCadastrado = atoi(param);
                }
                                    
                ConsultaIntencao( idCadastrado, xml_g );
            
                xml_g->closeTag();
                achou = 1;
            }
        }



        if ( !memcmp(processo,"MANPLANOS",9) )
        {
            ULOG( "log 18" );
            if ( !memcmp(operacao,"PSQ",3) )
            {
                ULOG( "log 19" );
                dnFiltro = txh.walkDOM( dnode, "Filtro", 0 );
                if ( dnFiltro != NULL ) 
                {
                    xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                    xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                    
                    get_tag( param, dnFiltro, "idRegional", 0, -1 );
                    idRegional = atoi(param);
                    get_tag( param, dnFiltro, "tpCliente", 0, -1 );
                    if ( param[0] != 0x0 )
                       strcpy(szidTipoCliente,param);
                    get_tag( param, dnFiltro, "tpLinha", 0, -1 );
                    idTipoLinha = atoi(param);
                    
                    ListaPlanos( idRegional, szidTipoCliente, idTipoLinha, xml_g );
                    xml_g->closeTag();
                    achou = 1;
                }
            }

            // Chamada de consulta para efetuar alteracao
            if ( !memcmp(operacao,"CON",3) )
            {
                ULOG( "log 20" );
                xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");

                dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
                if ( dnFiltro != NULL ) 
                {
                    get_tag( param, dnFiltro, "idCadastrado"  , 0, -1 );
                    idCadastrado = atoi(param);
                }
                                    
                ConsultaPlano( idCadastrado, xml_g );
            
                xml_g->closeTag();
                achou = 1;
            }

        }



        if ( !memcmp(processo,"CFGOFERTAS",10) )
        {
            
            ULOG( "log 21" );
            // Chamada de consulta para efetuar alteracao
            if ( !memcmp(operacao,"PSQ",3) )
            {
                ULOG( "log 22" );
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
                            if ( !memcmp(param,GRUPOS,11) )
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

                        }
                    }
                    
                }

                xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                xml_g->createTag("ListasVO");

                get_ListaIntencaoOfertas( &pRegional, &pCliente, &pTpLinha, &pGrupo, &pSegmento, xml_g );

                xml_g->closeTag();
                xml_g->closeTag();
                achou = 1;

            } // PSQ


            // Chamada de consulta para efetuar alteracao
            if ( !memcmp(operacao,"DST",3) )   // Destinos
            {

                dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
                if ( dnFiltro != NULL ) 
                {
                    get_tag( param, dnFiltro, "idIntencao"  , 0, -1 );
                    idIntencao = atoi(param);
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
                            if ( !memcmp(param,GRUPOS,11) )
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

                        }
                    }
                    
                }

                xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                xml_g->createTag("ListasVO");

                sel_Destinos( idIntencao, &pRegional, &pCliente, &pTpLinha, &pGrupo, &pSegmento, xml_g );

                xml_g->closeTag();
                xml_g->closeTag();
                achou = 1;

            } // DST

            // Chamada de consulta para efetuar alteracao
            if ( !memcmp(operacao,"CON",3) )
            {

                dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
                if ( dnFiltro != NULL ) 
                {
                    get_tag( param, dnFiltro, "idIntencao"  , 0, -1 );
                    idIntencao = atoi(param);
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

                            // Grupos
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
                    
                }

                xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                xml_g->createTag("ListasVO");

                BuscaRespostaUnidade( &pRegional, &pRespostaUnidade, idIntencao, &pDestino, &pTpLinha, &pCliente );    
                sel_Ofertas( idIntencao, &pGrupo, &pSegmento, &pRespostaUnidade, xml_g );

                xml_g->closeTag();
                xml_g->closeTag();
                achou = 1;
            } // CON
        }



        if ( !memcmp(processo,"CFGMIGRA",8) )
        {
            if ( !memcmp(operacao,"PSQ",3) )
            {
                dnFiltro = txh.walkDOM( dnode, "Filtro", 0 );
                if ( dnFiltro != NULL ) 
                {
                    xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                    xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                    
                    get_tag( param, dnFiltro, "idRegional", 0, -1 );
                    idRegional = atoi(param);
                    get_tag( param, dnFiltro, "tpCliente", 0, -1 );
                    if ( param[0] != 0x0 )
                       strcpy(szidTipoCliente,param);
                    get_tag( param, dnFiltro, "tpLinha", 0, -1 );
                    idTipoLinha = atoi(param);
                    
                    ListaMigracao( idRegional, szidTipoCliente, idTipoLinha, xml_g );
                    xml_g->closeTag();
                    achou = 1;
                }
            }

            // Chamada de consulta para efetuar alteracao
            if ( !memcmp(operacao,"CON",3) )
            {
                xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");

                dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
                if ( dnFiltro != NULL ) 
                {
                    get_tag( param, dnFiltro, "idCadastrado"  , 0, -1 );
                    idCadastrado = atoi(param);
                }
                                    
                ConsultaMigracao( idCadastrado, xml_g );
            
                xml_g->closeTag();
                achou = 1;
            }
        }






        if ( !memcmp(processo,"MANBONUS",9) )
        {
            if ( !memcmp(operacao,"PSQ",3) )
            {
                dnFiltro = txh.walkDOM( dnode, "Filtro", 0 );
                if ( dnFiltro != NULL ) 
                {
                    xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                    xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                    
                    get_tag( param, dnFiltro, "idRegional", 0, -1 );
                    if ( param[0] != 0x0 )
                       strcpy( szidRegional,param );
                       
                    get_tag( param, dnFiltro, "tpCliente", 0, -1 );
                    if ( param[0] != 0x0 )
                       strcpy( szidTipoCliente,param );

                    get_tag( param, dnFiltro, "tpLinha", 0, -1 );
                    if ( param[0] != 0x0 )
                       strcpy( szidTipoLinha,param );
                    
                    get_tag( param, dnFiltro, "idSegmento", 0, -1 );
                    if ( param[0] != 0x0 )
                       strcpy( szidSegmentacao,param );
                    
                    get_tag( param, dnFiltro, "tpBonus", 0, -1 );
                    if ( param[0] != 0x0 )
                       strcpy( szidTipoBonus,param );

                    ListaBonus( szidRegional,szidTipoCliente,szidTipoLinha,szidSegmentacao,szidTipoBonus,xml_g );
                    xml_g->closeTag();
                    achou = 1;
                }
            }

            // Chamada de consulta para efetuar alteracao
            if ( !memcmp(operacao,"CON",3) )
            {
                xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
                xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");

                dnFiltro = txh.walkDOM( dnode, "Manter", 0 );
                if ( dnFiltro != NULL ) 
                {
                    get_tag( param, dnFiltro, "idCadastrado"  , 0, -1 );
                    idCadastrado = atoi(param);
                }
                                    
                ConsultaBonus( idCadastrado, xml_g );
            
                xml_g->closeTag();
                achou = 1;
            }

        }

    }

    if ( achou == 0) 
    {
        dnCombo = txh.walkDOM( dnode, "Combos", 0 );
        if ( dnCombo != NULL ) 
        {
            achou = 1;
            xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
            xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
                xml_g->createTag("ListasVO");
                for ( i=0;;i++ )
                {
                    param[0] = 0x0;
                    get_tag( param, dnCombo, "nmSelect", i, -1 );
                    if ( param[0] == NULL )
                        break;
        
                    // Tipo Cliente
                    if ( !memcmp(param,TPCLIENTE,9) )
                        get_listaTipoCliente( xml_g );

                    // Tipo Linha
                    if ( !memcmp(param,TPLINHA,7) )
                        get_listaTipoLinha( xml_g );

                    // Tipo Oferta
                    if ( !memcmp(param,TPOFERTA,8) )
                        get_listaTipoOferta( xml_g );
                    
                    // Regionais
                    if ( !memcmp(param,REGIONAL,8) )
                        get_listaRegionais( xml_g );
                    
                    // Tipo Bonus
                    if ( !memcmp(param,TPBONUS,7) )
                        get_listaTipoBonus( xml_g );


                    // Segmentacao
                    if ( !memcmp(param,SEGMENTACAO,11) )
                        get_listaSegmentacao( xml_g );

                    // Tipo Servico
                    if ( !memcmp(param,TPSERVICO,9) )
                        get_listaTipoServico( xml_g );

                    // Lista de Intencoes
                    if ( !memcmp(param,INTENCAO,8) )
                        get_listaIntencao( xml_g );

                    // Lista de Destinos
                    if ( !memcmp(param,DESTINO,7) )
                        get_listaDestino( xml_g );

                    // Lista de Grupos
                    if ( !memcmp(param,GRUPOS,6) )
                        get_listaGrupo( xml_g );
                                    
                    // Lista de Grupos
                    if ( !memcmp(param,GRPPACOTE,9) )
                        get_listaGruposPacote( xml_g );

                }
                xml_g->closeTag();
            xml_g->closeTag();
        }
    }

    if ( !achou )
    {
        xml_g->createTag("FidelizacaoVO");  // Servico acionado para trazer os filtros combo
        xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
            xml_g->addItem( "msgError","Parãmetros de entrada inválidos" );
            xml_g->addItem( "codError",1 );
        xml_g->closeTag();
    }

    setStatusCode(OKFID,OKMSG);

    ULOG_END("GETLISTAS");
}
