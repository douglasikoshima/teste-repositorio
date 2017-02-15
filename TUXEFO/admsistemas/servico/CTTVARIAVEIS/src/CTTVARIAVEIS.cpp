/*****************************************************************************
 *
 * Modulo:    CTTVARIAVEIS
 * Arquivo:   CTTVARIAVEIS.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 12/11/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define CTTVARIAVEISCPP

#define MAX_XML 50000 //( 1024 * 60 )
//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
   
#include "../../../negocio/admatdCmm/include/CCtt.h"
#include "../../../negocio/admatdCmm/include/CCttFtr.h"
#include "../../../negocio/admatdCmm/include/CTipPes.h"
#include "../../../negocio/admatdCmm/include/CUfo.h"
#include "../../../negocio/admatdCmm/include/CContatoTipoRetornoRelacoes.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CTTVARIAVEIS);

/**************************************************************************
 *  Funcao de Negocios:  CTTVARIAVEIS
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
#define CTT_SERVICO_COMBO   1
#define CTT_SERVICO_ARVORE  2
#define CTT_SERVICO_GRID    3

void implCTTVARIAVEIS::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	 
	ULOG_START("implCTTVARIAVEIS::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CSafePointer osfidTipoRelacionamento;
	CSafePointer osfidSegmentacao;
	CSafePointer osfidTipoCarteira;
	CSafePointer osfidGrupoAbertura;
	CSafePointer osfidGrupoTratamento;
	CSafePointer osfidGrupoRetorno;
	CSafePointer osfidTipoLinha;
	CSafePointer osfidFechamento;
	CSafePointer osfidDisponivel;
	CSafePointer osfidOperadora;
	CSafePointer osfdtInicio;
	CSafePointer osfdtFim;

	CContato     oContato;
	CTipoPessoa  oTipoPessoa;
	CContatoFiltro oContatoFiltro;
	CContatoTipoRetornoRelacoes oContatoTipoRetornoRelacoes;
	CContatoTipoRetornoRelacoes oContatoTipoRetornoRelacoesGeral;
	CUFOperadora oUFOperadora;
    
	int   iCont;
	int   iNivel;
	int   iContTag;
	int   inServico;
	char* cidContato;
	char* cinServico = oSafePointer.getTag( dnode, "inServico", 0 );
	DOMNode* pzdnDados = walkDOM( dnode, "dadosAtuais", 0 );

	if( strlennull( cinServico ) <= 0 )
	{
		setStatusCode("14E0001","inServico está nulo");
		ULOG_END("implCTTVARIAVEIS::Execute()");
		return;
	}
	try
	{
		inServico = atoi( cinServico );
	}
	catch(...)
	{
		setStatusCode("14E0002","inServico contém um valor não numerico");
		ULOG_END("implCTTVARIAVEIS::Execute()");
		return;
	}
	if( pzdnDados == NULL )
	{
		setStatusCode("14E0003","Está falando a TAG dadosAtuais");
		ULOG_END("implCTTVARIAVEIS::Execute()");
		return;
	}
	cidContato = oSafePointer.getTag( pzdnDados, "idContato", 0 );

	xml_g->createTag("AdmArvoreParametroVO");
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addItem("inServico", inServico );

	ULOG("variavel  inServico -> [%d]",inServico);

	switch( inServico )
	{
//INICIO case CTT_SERVICO_COMBO: 
		case CTT_SERVICO_COMBO: 
			ULOG("Inicio CTT_SERVICO_COMBO");
			xml_g->createTag("AdmArvoreParametroCombosVO");

			oContatoFiltro.ListAllTipoRelacionamento();
			oContatoFiltro.GetXmlTipoRelacionamento2("AdmTipoRelacionamentoVO", xml_g);

			oContatoFiltro.ListAllSegmentacao();
			oContatoFiltro.GetXmlSegmentacao("AdmTipoSegmentacaoVO", xml_g);

			oContatoFiltro.ListAllTipoCarteira();
			oContatoFiltro.GetXmlTipoCarteira("AdmTipoCarteiraSimplVO", xml_g);

			oContatoFiltro.ListGrupoAbertura();
			oContatoFiltro.GetXmlGrupo("AdmGrupoAberturaVO", xml_g);

			oContatoFiltro.ListGrupoTratamento();
			oContatoFiltro.GetXmlGrupo("AdmGrupoTratamentoVO", xml_g);

			oContatoFiltro.ListGrupoRetorno();
			oContatoFiltro.GetXmlGrupo("AdmGrupoRetornoVO", xml_g);

			oContatoFiltro.ListAllTipoLinha();
			oContatoFiltro.GetXmlTipoLinha("AdmTipoLinhaSimplVO", xml_g);

			oUFOperadora.ListAll();
			oUFOperadora.GetXmlPadrao("AdmUFOperadoraSimplVO", xml_g);

			oContatoFiltro.ListAllCanal();
			oContatoFiltro.GetXmlCanal("AdmCanalVO", xml_g);

			oTipoPessoa.ListAll();
			oTipoPessoa.GetXmlNatureza("AdmNaturezaVO", xml_g);

			oContatoFiltro.ListTipoFechamento();
			oContatoFiltro.GetXmlTipoFechamento("AdmTipoFechamentoVO", xml_g);

	// Agregando Filtros por Procedencia
			oContatoFiltro.ListTipoProcedencia();
			oContatoFiltro.GetXmlTipoProcedencia("AdmProcedenciaVO", xml_g);

			xml_g->closeTag();//AdmArvoreParametroCombosVO
			setStatusCode("14I0000","Operacao concluída com sucesso!");
			ULOG("Fim CTT_SERVICO_COMBO");
			break;
//FIM case CTT_SERVICO_COMBO: 

//INICIO case CTT_SERVICO_ARVORE: 
		case CTT_SERVICO_ARVORE:
			{
				ULOG("Inicio CTT_SERVICO_ARVORE");

				for( iCont = 0;;iCont++ )
				{
					if( osfidTipoRelacionamento.getTag( pzdnDados, "idTipoRelacionamentoArray", iCont ) == NULL )
						break;
				}
				for( iCont = 0;;iCont++ )
				{
					if( osfidSegmentacao.getTag( pzdnDados, "idSegmentacaoArray", iCont ) == NULL )
						break;
				}
				for( iCont = 0;;iCont++ )
				{
					if( osfidTipoCarteira.getTag( pzdnDados, "idTipoCarteiraArray", iCont ) == NULL )
						break;
				}
				for( iCont = 0;;iCont++ )
				{
					if( osfidGrupoAbertura.getTag( pzdnDados, "idGrupoAberturaArray", iCont ) == NULL )
						break;
				}
				for( iCont = 0;;iCont++ )
				{
					if( osfidGrupoTratamento.getTag( pzdnDados, "idGrupoTratamentoArray", iCont ) == NULL )
						break;
				}
				for( iCont = 0;;iCont++ )
				{
					if( osfidGrupoRetorno.getTag( pzdnDados, "idGrupoRetornoArray", iCont ) == NULL )
						break;
				}
				for( iCont = 0;;iCont++ )
				{
					if( osfidTipoLinha.getTag( pzdnDados, "idTipoLinhaArray", iCont ) == NULL )
						break;
				}
				for( iCont = 0;;iCont++ )
				{
					if( osfidFechamento.getTag( pzdnDados, "idFechamentoArray", iCont ) == NULL )
						break;
				}
				for( iCont = 0;;iCont++ )
				{
					if( osfidDisponivel.getTag( pzdnDados, "idDisponivelArray", iCont ) == NULL )
						break;
				}
				for( iCont = 0;;iCont++ )
				{
					if( osfidOperadora.getTag( pzdnDados, "idOperadoraArray", iCont ) == NULL )
						break;
				}
				osfdtInicio.getTag( pzdnDados, "dtInicio", 0 );
				osfdtFim.getTag( pzdnDados, "dtFim", 0 );

				//Tag principal (container, esta tag nao tem nenhum valor, a nao ser a raiz AdmContatoFolhaVO)
				xml_g->createTag("AdmContatoFolhaVO");

				if ( 
					 (osfidTipoRelacionamento.getQuantidade() == 0 ) &&
					 (osfidSegmentacao.getQuantidade() == 0 ) &&
					 (osfidTipoCarteira.getQuantidade() == 0 ) &&
					 (osfidGrupoAbertura.getQuantidade() == 0 ) &&
					 (osfidGrupoTratamento.getQuantidade() == 0 ) &&
					 (osfidGrupoRetorno.getQuantidade() == 0 ) &&
					 (osfidTipoLinha.getQuantidade() == 0 ) &&
					 (osfidFechamento.getQuantidade() == 0 ) &&
					 (osfidDisponivel.getQuantidade() == 0 ) &&
					 (osfidOperadora.getQuantidade() == 0 ) &&
					 (osfdtInicio.getQuantidade()== 0 ) &&
					 (osfdtFim.getQuantidade()== 0 ) 
					)
				{
					oContato.ListAll ( cidContato ) ;
				}
                else
				{
				
				oContato.ListAllFiltrado( cidContato,
										  &osfidTipoRelacionamento,
										  &osfidSegmentacao,
										  &osfidTipoCarteira,
										  &osfidGrupoAbertura,
										  &osfidGrupoTratamento,
										  &osfidGrupoRetorno,
										  &osfidTipoLinha,
										  &osfidFechamento,
										  &osfidDisponivel,
										  &osfidOperadora,
										  osfdtInicio.getTag(0),
										  osfdtFim.getTag(0) );
				}
				

				if( oContato.Quantidade() > 0 )
				{
					iNivel   = 0;
					iContTag = 0;
					
					//Monta o menu raiz
					xml_g->createTag("AdmContatoFolhaVO");
					xml_g->addItem("idContato", oContato.Registro(0)->cidContato );
					xml_g->addItem("idNomeContato", oContato.Registro(0)->cidNomeContato );
					xml_g->addItem("idContatoPai", oContato.Registro(0)->cidContatoPai );
					xml_g->addItem("nmContato", oContato.Registro(0)->cnmContato );
					xml_g->addItem("inDisponibilidade", oContato.Registro(0)->cinDisponibilidade );
					xml_g->addItem("dsPath", oContato.Registro(0)->cdsPath );
					xml_g->addItem("nrNivel", oContato.Registro(0)->iLevel );
					xml_g->addItem("inFolha", 0 );

					//O laco continua apos o raiz
					for( int x = 1; x < oContato.Quantidade(); x++ )
					{
						if( oContato.Registro( x ) != NULL )
						{

							if( oContato.Registro(x)->iLevel > iNivel )
							{
								iNivel = oContato.Registro(x)->iLevel;
								xml_g->createTag("AdmContatoFolhaVO");
								iContTag++;
							}//if( Registro(x)->iLevel > iNivel )
							else
							{
								xml_g->closeTag();
								if( oContato.Registro(x)->iLevel < iNivel )
								{
									while( iNivel > oContato.Registro(x)->iLevel )
									{
										xml_g->closeTag();
										iNivel--;
									}
									iNivel = oContato.Registro(x)->iLevel;
								}
								xml_g->createTag("AdmContatoFolhaVO");
							}// else if( Registro(x)->iLevel > iNivel )

							xml_g->addItem("idContato", oContato.Registro(x)->cidContato );
							xml_g->addItem("idNomeContato", oContato.Registro(x)->cidNomeContato );
							xml_g->addItem("idContatoPai", oContato.Registro(x)->cidContatoPai );
							xml_g->addItem("nmContato", oContato.Registro(x)->cnmContato );
							xml_g->addItem("inDisponibilidade", oContato.Registro(x)->cinDisponibilidade );
							xml_g->addItem("dsPath", oContato.Registro(x)->cdsPath );
							xml_g->addItem("nrNivel", oContato.Registro(x)->iLevel );
							xml_g->addItem("inFolha", oContato.Registro(x)->iFolha );

						}// if( Registro( x ) != NULL )
					}// for( int x = 0; x < Quantidade(); x++ )
					// Fecha todas as tags
					while( iContTag > 0 )
					{
						xml_g->closeTag();
						iContTag--;
					}
					setStatusCode("14I0100","Operacao concluída com sucesso!");
				}// if( oContato.Quantidade() > 0 )
				else
					setStatusCode("14W0101","Sem dados para retorno");

				xml_g->closeTag();//xml_g->createTag("AdmContatoFolhaVO");

                ULOG("Fim CTT_SERVICO_ARVORE");
			}
			break;
//FIM case CTT_SERVICO_ARVORE: 

//INICIO case CTT_SERVICO_GRID: 
		case CTT_SERVICO_GRID: 
			ULOG("Inicio CTT_SERVICO_GRID");
			if( strlennull( cidContato ) > 0 )
			{
				if( oContato.ListContato( cidContato ) > 0 )
				{   
					// objeto  XMLGen temporario
					XMLGen oXml;
					XMLGen *pooXml = new XMLGen();
					int iTotal_XML=0,iSz = 0;
					pooXml = &oXml;
					bool erro_xml = false ;
					char* cinArvore = oSafePointer.getTag( pzdnDados, "inArvore", 0 );
					char* cinGrupos = oSafePointer.getTag( pzdnDados, "inGrupos", 0 );
					char* cinVariaveis = oSafePointer.getTag( pzdnDados, "inVariaveis", 0 );
					char* cinRetorno = oSafePointer.getTag( pzdnDados, "inRetorno", 0 );
					char* cPonteiroVazio = ""; //Para evitar erros no strcmp
					if( strlennull( cinArvore ) <= 0 )
						cinArvore = cPonteiroVazio;
					if( strlennull( cinGrupos ) <= 0 )
						cinGrupos = cPonteiroVazio;
					if( strlennull( cinVariaveis ) <= 0 )
						cinVariaveis = cPonteiroVazio;
					if( strlennull( cinRetorno ) <= 0 )
						cinRetorno = cPonteiroVazio;
					int x;
					xml_g->createTag("AdmArvoreParametroRetornoVO");
					xml_g->addItem("idContato", oContato.Registro()->cidContato );

					if(  strcmp( cinArvore, "1" ) == 0 )
						xml_g->addItem("nmContato", oContato.Registro()->cdsPath );
					else
						xml_g->addItem("nmContato", oContato.Registro()->cnmContato );

					if( (  strcmp( cinGrupos, "1" ) == 0 ) || ( strcmp( cinVariaveis, "1" ) == 0 ) )
					{
						oContatoTipoRetornoRelacoesGeral.ListGrupo( cidContato );
						for( x = 0; x < oContatoTipoRetornoRelacoesGeral.Quantidade(); x++ )
						{   

							try
						{
							
							pooXml->createTag("AdmGrupoParametroVO");
							pooXml->addItem("idGrupo", oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
							pooXml->addItem("nmGrupo", oContatoTipoRetornoRelacoesGeral.Registro(x)->cdsContatoRelacao );


							
							if( strcmp( cinGrupos, "1" ) == 0 )
							{
								oContatoTipoRetornoRelacoes.ListTipoSequencia( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao, cidContato );
								oContatoTipoRetornoRelacoes.GetXml( "AdmGrupoFaseVO", "idTipoSequencia", "dsTipoSequencia", pooXml );

								oContatoTipoRetornoRelacoes.ListGrupoCanal( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
								oContatoTipoRetornoRelacoes.GetXml( "AdmCanalEntradaVO", "idCanalEntrada", "nmCanalEntrada", pooXml );
							}

							if( strcmp( cinVariaveis, "1" ) == 0 )
							{
								oContatoTipoRetornoRelacoes.ListGrupoTpRelacionamento( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
								oContatoTipoRetornoRelacoes.GetXmlSigla( "AdmTipoRelacionamentoVO", "idTipoRelacionamento", "sgTipoRelacionamento", "nmTipoRelacionamento", pooXml );

								oContatoTipoRetornoRelacoes.ListGrupoSegmentacao( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
								oContatoTipoRetornoRelacoes.GetXmlSigla( "AdmSegmentacaoSimplVO", "idSegmentacao", "sgSegmentacao", "dsSegmentacao", pooXml );

								oContatoTipoRetornoRelacoes.ListGrupoProcedencia( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
								oContatoTipoRetornoRelacoes.GetXml( "AdmProcedenciaVO", "idProcedencia", "nmProcedencia", pooXml );

								oContatoTipoRetornoRelacoes.ListGrupoTipoLinha( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
								oContatoTipoRetornoRelacoes.GetXmlSigla( "AdmTipoLinhaSimplVO", "idTipoLinha", "sgTipoLinha", "dsTipoLinha", pooXml );

								oContatoTipoRetornoRelacoes.ListGrupoTipoCarteira( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
								oContatoTipoRetornoRelacoes.GetXml( "AdmCarteirizacaoVO", "idCarteirizacao", "nmCarteirizacao", pooXml );

								oContatoTipoRetornoRelacoes.ListGrupoTipoPessoa( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
								oContatoTipoRetornoRelacoes.GetXmlSigla( "AdmTipoPessoaVO", "idTipoPessoa", "sgTipoPessoa", "dsTipoPessoa", pooXml );

								oContatoTipoRetornoRelacoes.ListGrupoAbertura( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
								oContatoTipoRetornoRelacoes.GetXml( "AdmGrupoAberturaVO", "idGrupo", "nmGrupo", pooXml );

							}

							pooXml->closeTag();   //AdmGrupoParametroVO
							
							
                            char *pc;
							iSz = 0 ;
							pc = pooXml->retrieveXML(&iSz);
							ULOG("Tam pooXml = [%d]",iSz);
							// Verifica se o xml chegou ao seu tamanho determinado
                     if ( ( iTotal_XML + iSz ) > MAX_XML ) 
                     {
                        ULOG("Gerado estouro");
                        ULOG("iTotal_XML + iSz = [%d]",iTotal_XML + iSz);
								throw 1 ;
							}
							// copia xml_g  para pooXml
							xml_g->aggregateXML(pc);
							// limpa o pooXml temporario
				         pooXml->clearAndDestroy();
							iTotal_XML +=  iSz ;
							ULOG("Tam xml_g = [%d]",iTotal_XML);
							}
							catch ( ... )
							{  
							   ULOG("CRIANDO MSG DE ERRO"); 
								//seta a varial erro com tru  
								erro_xml = true ;
								// cria msg de erro 
								
 							   // xml_g->addItem("msgStatus","MSG DE ERRO");
								// sai do loop
								break ;
							}



							
						}//for(...)
					}//if( (  strcmp( cinGrupos, "1" ) == 0 ) || ( strcmp( cinVariaveis, "1" ) == 0 ) )
					
					if( ( strcmp( cinRetorno, "1" ) == 0 ) && ( erro_xml == false ) )
					{
						oContatoTipoRetornoRelacoesGeral.ListContatoTipoRetorno( cidContato );
						for( x = 0; x < oContatoTipoRetornoRelacoesGeral.Quantidade(); x++ )
						{
							try
							{
//							   1
							pooXml->createTag("AdmVariavelRetornoVO");
							pooXml->addItem("idTipoRetornoContato", oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
							pooXml->addItem("dsTipoRetornoContato", oContatoTipoRetornoRelacoesGeral.Registro(x)->cdsContatoRelacao );
//                        2

							oContatoTipoRetornoRelacoes.ListCanal( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
							oContatoTipoRetornoRelacoes.GetXml( "AdmCanalEntradaVO", "idCanalEntrada", "nmCanalEntrada", pooXml );
//                        3
                        
							oContatoTipoRetornoRelacoes.ListTpRelacionamento( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
							oContatoTipoRetornoRelacoes.GetXmlSigla( "AdmTipoRelacionamentoVO", "idTipoRelacionamento", "sgTipoRelacionamento", "nmTipoRelacionamento", pooXml );
//                        4

							oContatoTipoRetornoRelacoes.ListSegmentacao( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
							oContatoTipoRetornoRelacoes.GetXmlSigla( "AdmSegmentacaoSimplVO", "idSegmentacao", "sgSegmentacao", "dsSegmentacao", pooXml );
//                        5

							oContatoTipoRetornoRelacoes.ListProcedencia( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
							oContatoTipoRetornoRelacoes.GetXml( "AdmProcedenciaVO", "idProcedencia", "nmProcedencia", pooXml );
//                        6

							oContatoTipoRetornoRelacoes.ListTipoLinha( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
							oContatoTipoRetornoRelacoes.GetXmlSigla( "AdmTipoLinhaSimplVO", "idTipoLinha", "sgTipoLinha", "dsTipoLinha", pooXml );
//                        7
                        
							oContatoTipoRetornoRelacoes.ListTipoCarteira( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
							oContatoTipoRetornoRelacoes.GetXml( "AdmCarteirizacaoVO", "idCarteirizacao", "nmCarteirizacao", pooXml );
//                        8
							oContatoTipoRetornoRelacoes.ListTipoPessoa( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
							oContatoTipoRetornoRelacoes.GetXmlSigla( "AdmTipoPessoaVO", "idTipoPessoa", "sgTipoPessoa", "dsTipoPessoa", pooXml );
//                        9
							oContatoTipoRetornoRelacoes.ListTipoGrupoAbertura( oContatoTipoRetornoRelacoesGeral.Registro(x)->cidContatoRelacao );
							oContatoTipoRetornoRelacoes.GetXml( "AdmGrupoAberturaVO", "idGrupo", "nmGrupo", pooXml );


							pooXml->closeTag();//AdmGrupoParametroVO

					 	   char *pc;
						   iSz = 0 ;
							pc = pooXml->retrieveXML(&iSz);
							ULOG("Tam pooXml = [%d]",iSz);
							// Verifica se o xml chegou ao seu tamanho determinado
                     if ( ( iTotal_XML + iSz ) > MAX_XML) 
                     {
								throw 1 ;
							}
							// copia xml_g  para pooXml
							xml_g->aggregateXML(pc);
							// limpa o pooXml temporario
				            pooXml->clearAndDestroy();
							iTotal_XML +=  iSz ;
							ULOG("Tam xml_g = [%d]",iTotal_XML);
							}
							catch ( ... )
							{   
							   ULOG("CRIANDO MSG DE ERRO"); 
							   erro_xml = true ;
								// cria msg de erro 
 							   // xml_g->addItem("msgStatus","MSG DE ERRO");
								// sai do loop
								break ;
							}

							
						}//for(...)
					}//if( strcmp( cinRetorno, "1" ) == 0 )
					
					xml_g->closeTag();//AdmArvoreParametroRetornoVO
					// verifica se estouro de xml 
					if (erro_xml == true )
					{  
					   // cria msg de erro 
					   xml_g->addItem("msgStatus","MSG DE ERRO");
					}

					setStatusCode("14I0200","Operacao concluída com sucesso!");
				}//if( oContato.ListContato( cidContato ) > 0 )
				else
					setStatusCode("14E0201", oContato.GetErro() );
			}//if( strlennull( cidContato ) <= 0 )
			else
				setStatusCode("14E0200","idContato não pode estar nulo para retornar os parâmetros");

            ULOG("Fim CTT_SERVICO_GRID");
			break;
     //FIM case CTT_SERVICO_GRID: 

	}//switch( inServico )
	
	xml_g->closeTag();//AdmArvoreParametroVO
   ULOG_END("implCTTVARIAVEIS::Execute()");
}
