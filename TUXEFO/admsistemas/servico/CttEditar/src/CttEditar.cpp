/*****************************************************************************
 *
 * Modulo:    CttEditar
 * Arquivo:   CttEditar.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDMartins           Criacao
 *             C_CMSantos
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *  Modificado po charles
 *  ultimo numero de erro  ---->>>> setStatusCode("14E0015","vlPrazoAnatel está nulo"); 
 *
 **************************************************************************/

//Definicao Global
#define CttEditarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCtt.h"
#include "../../../negocio/admatdCmm/include/CFlh.h"
#include "../../../negocio/admatdCmm/include/CFlhAnt.h"
#include "../../../negocio/admatdCmm/include/CFlhAvs.h"
#include "../../../negocio/admatdCmm/include/CAvs.h"
#include "../../../negocio/admatdCmm/include/CContatoGrupoRC.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttEditar);

/**************************************************************************
 *  Funcao de Negocios:  CttEditar
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
void implCttEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_END("implCttEditar::Execute()");
   setStatusCode("12I0000", "Inicio do Servico CttEditar");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContato oContato;
	CIndicadorContatoFolha oIndicadorContatoFolha;
	CContatoFolhaMensagemAviso oContatoFolhaMensagemAviso;
	CMensagemAviso oMensagemAviso;
	CContatoFolha oContatoFolha;
	

//  a teg  gruposAssociados esta vindo vazia por este motivo esta parte do codigo foi comentada
//  CContatoGrupoRC oContatoGrupoRC;


	DOMNode* nNodeAuxiliar;
	DOMNode* nNodeSMP;
	DOMNode* nNodeSMS;
	DOMNode* nNodeCanais;
	int iWarningNome = 0;

	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char* cinDisponibilidade = "1";//oSafePointer.getTag( dnode, "inDisponibilidade", 0 );
	char* cnmContato = oSafePointer.getTag( dnode, "nmContato", 0 );
	char* cidNomeContato = oSafePointer.getTag( dnode, "idNomeContato", 0 );
	char* cinFolha = oSafePointer.getTag( dnode, "inFolha", 0 );
	char* cidUser = getUser();
	if( strlennull( cinFolha ) <= 0 )
	{
		setStatusCode("14E0001","inFolha está nulo");
		ULOG_END("implCttEditar::Execute()");
		return;
	}
	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("14E0002","idContato está nulo");
		ULOG_END("implCttEditar::Execute()");
		return;
	}

	//if( strlennull( cinDisponibilidade ) <= 0 )
	//{
	//	setStatusCode("14E0003","inDisponibilidade esta nulo");
	//	return;
	//}
	if( ( strlennull( cnmContato ) <= 0 ) && 
		( strlennull( cidNomeContato ) <= 0 ) )
	{
		setStatusCode("14E0004","idNomeContato e nmContato estão nulos");
		ULOG_END("implCttEditar::Execute()");
		return;
	}
	
	//soh verifaca um nome se nao veio cidNomeContato e veio o nome
	if( ( strlennull( cnmContato ) > 0 ) && 
		( strlennull( cidNomeContato ) <= 0 ) )
	{
		switch( oContato.ProcuraNome( cnmContato ) )
		{
			case 0://Nao achou o nome
				break;
			case 1://Achou o nome, mas eh diferente (maisculas e minisculas)
				iWarningNome++;
			case 2://Achou o nome exatamente igual (maisculas e minisculas)
				cidNomeContato = oContato.getIdNome();
				break;
			default://Nao era pra acontecer, mas....
				break;
		}
	}

	switch( oContato.Update( cidContato, 
						     cidNomeContato,
 						     cnmContato,
						     cinDisponibilidade,
							 cinFolha,
						     cidUser) )
	{
		case 0://Sucesso
		{
		
			if( strcmp( cinFolha, "1" ) == 0 )
			{
				nNodeAuxiliar = walkDOM( dnode, "AdmDadosBasicosVO", 0 );
				if( nNodeAuxiliar != NULL )
				{
					char* cInSms = 0x0;
					char* cDsSms = 0x0;
					char* cidClassificacao = 0x0;
					char* cInRelacionamento = 0x0;
					char* cInProtocolo = 0x0;
					//char* cInexibeProtocolo = 0x0;
					char* cDsContatoCanais = 0x0;
					char* cDsMsgExcecao = 0x0;
					char* cInCancelamento = 0x0;

					char* cidPagina = "0";
					char* cidTipoRetorno  = oSafePointer.getTag( nNodeAuxiliar, "idTipoRetornoAtual", 0 );
					char* cidTipoFechamento = "1";//oSafePointer.getTag( nNodeAuxiliar, "idTipoFechamentoAtual", 0 );
					char* cidTipoProcesso = oSafePointer.getTag( nNodeAuxiliar, "idTipoProcessoAtual", 0 );
					char* cqtHorasPrazo = oSafePointer.getTag( nNodeAuxiliar, "qtDiasPrazoContato", 0 );
                    // resposta ao cliente cms
                    char* cqtHorasPrazoAnatel = oSafePointer.getTag( nNodeAuxiliar, "vlPrazoAnatel", 0 );

					char* cinFechamentoImediato = "0";
					char* cvlPeso = oSafePointer.getTag( nNodeAuxiliar, "vlPesoContato", 0 );
					char* cinProcessoTecnico = oSafePointer.getTag( nNodeAuxiliar, "inProcessoTecnico", 0 );
					char* cidMensagemAviso = oSafePointer.getTag( nNodeAuxiliar, "idMensagemAviso", 0 );
					char* cnmMensagemAviso = oSafePointer.getTag( nNodeAuxiliar, "nmMensagemAviso", 0 );
                    char* csgRegraEncaminhamento = oSafePointer.getTag( nNodeAuxiliar, "sgRegraEncaminhamento", 0 );
                    char* csgFluxoAtendimento = oSafePointer.getTag( nNodeAuxiliar, "sgFluxoAtendimento", 0 );
					if( strlennull( cidTipoRetorno ) <= 0 )
					{
						setStatusCode("14E0005","idTipoRetorno está nulo");
						ULOG_END("implCttEditar::Execute()");
						return;
					}
					if( strlennull( cidTipoFechamento ) <= 0 )
					{
						setStatusCode("14E0006","idTipoFechamento esta nulo");
						ULOG_END("implCttEditar::Execute()");
						return;
					}
					if( strlennull( cidTipoProcesso ) <= 0 )
					{
						setStatusCode("14E0007","idTipoProcesso está nulo");
						ULOG_END("implCttEditar::Execute()");
						return;
					}
					if( strlennull( cqtHorasPrazo ) <= 0 )
					{
						setStatusCode("14E0008","qrDiasPrazoContato está nulo");
						ULOG_END("implCttEditar::Execute()");
						return;
					}
					if( strlennull( cinFechamentoImediato ) <= 0 )
					{
						setStatusCode("14E0009","inFechamentoImediato está nulo");
						ULOG_END("implCttEditar::Execute()");
						return;
					}
					if( strlennull( cvlPeso ) <= 0 )
					{
						setStatusCode("14E0010","vlPeso está nulo");
						ULOG_END("implCttEditar::Execute()");
						return;
					}
					if( strlennull( cinProcessoTecnico ) <= 0 )
					{
						setStatusCode("14E0011","inProcessoTecnico está nulo");
						ULOG_END("implCttEditar::Execute()");
						return;
					}
					if( strlennull( cqtHorasPrazoAnatel ) <= 0 )
					{
						setStatusCode("14E0015","vlPrazoAnatel está nulo");
						ULOG_END("implCttEditar::Execute()");
						return;
					}
                    if( strlennull( csgRegraEncaminhamento) <=0 )
                    {
						setStatusCode("14E0016","sgRegraEncaminhamento está nulo");
						ULOG_END("implCttEditar::Execute()");
						return;
                    }
                    //if( strlennull( csgFluxoAtendimento) <=0 )
                    //{
					//	setStatusCode("14E0016","sgFluxoAtendimento está nulo");
					//	ULOG_END("implCttEditar::Execute()");
					//	return;
                    //}

					if( strlennull( cidMensagemAviso ) <= 0 )
					{
						if( strlennull( cnmMensagemAviso ) > 0 )
						{
							oMensagemAviso.Insert( cnmMensagemAviso
												  ,cidUser );
							cidMensagemAviso = oMensagemAviso.Registro(0)->cidMensagemAviso;
						}
					}

    				nNodeSMP = walkDOM( dnode, "SmpVO", 0 );
    				if( nNodeSMP != NULL )
    				{
    				    ULOG_END( "Achou SMP..." );
    				    
    				    nNodeSMS = walkDOM( nNodeSMP, "sms", 0 );
    					cInSms = oSafePointer.getTag( nNodeSMS, "inSMS", 0 );
    					cDsSms = oSafePointer.getTag( nNodeSMS, "dsSMS", 0 );
    					cidClassificacao = oSafePointer.getTag( nNodeSMS, "idClassificacao", 0 );
    					
    				    nNodeCanais = walkDOM( nNodeSMP, "canais", 0 );
    					cInRelacionamento = oSafePointer.getTag( nNodeCanais, "inRelacionamento", 0 );
    					cInProtocolo = oSafePointer.getTag( nNodeCanais, "inProtocolo", 0 );
						cInCancelamento = oSafePointer.getTag( nNodeCanais, "inComprovanteCancelamento", 0 );
    					//cInexibeProtocolo = oSafePointer.getTag( nNodeCanais, "inExibeProtocolo", 0 );
    					cDsContatoCanais = oSafePointer.getTag( nNodeCanais, "dsContatoCanais", 0 );
    					cDsMsgExcecao = oSafePointer.getTag( nNodeCanais, "dsMsgExcecao", 0 );
    					if( strlennull( cInSms ) <= 0 )
    					{
    						setStatusCode("14E0012","cInSms está nulo");
    						ULOG_END("implCttIncluir::Execute()");
    						return;
    					}
    					if( strlennull( cInProtocolo ) <= 0 )
    					{
    						setStatusCode("14E0015","cInProtocolo está nulo");
    						ULOG_END("implCttIncluir::Execute()");
    						return;
    					}
    					//if( strlennull( cInexibeProtocolo ) <= 0 )
    					//{
    					//	setStatusCode("14E0015","cInexibeProtocolo está nulo");
    					//	ULOG_END("implCttIncluir::Execute()");
    					//	return;
    					//}
    					/*
    					if( strlennull( cDsContatoCanais ) <= 0 )
    					{
    						setStatusCode("14E0015","cDsContatoCanais está nulo");
    						ULOG_END("implCttIncluir::Execute()");
    						return;
    					}
    					if( strlennull( cDsMsgExcecao ) <= 0 )
    					{
    						setStatusCode("14E0016","cDsMsgExcecao está nulo");
    						ULOG_END("implCttIncluir::Execute()");
    						return;
    					}
    					*/
    				}

					ULOG("cInSms [%s]",cInSms);
					if( oContatoFolha.Update( cidContato
											 ,cidPagina
											 ,cidTipoRetorno
											 ,cidTipoFechamento
											 ,cidTipoProcesso
											 ,cqtHorasPrazo
											 ,cinFechamentoImediato
											 ,cvlPeso
											 ,cinProcessoTecnico
											 ,cidUser 
                                             ,cqtHorasPrazoAnatel
                                             ,cInSms
                                             ,cDsSms
                                             ,cInRelacionamento
                                             ,cInProtocolo
                                             ,cDsContatoCanais 
                                             ,cDsMsgExcecao
											 ,cInCancelamento
											 ,cidClassificacao
                                             ,csgRegraEncaminhamento
                                             ,csgFluxoAtendimento) )
					{
						nNodeAuxiliar = walkDOM( dnode, "indicadoresAssociados", 0 );
						char* cidIndicador;
						int   iCont;
						oIndicadorContatoFolha.EraseCtt( cidContato );
						for( iCont = 0;; iCont++ )
						{
							cidIndicador = oSafePointer.getTag( nNodeAuxiliar, "idIndicador", iCont );
							if( strlennull( cidIndicador ) <= 0 )
								break;
							
							oIndicadorContatoFolha.Insert( cidContato
														  ,cidIndicador
														  ,cidUser );

						}
						//Verifica se existe mensagem, se existe, altera, caso contrario, relaciona
						if( strlennull( cidMensagemAviso ) > 0 )
						{
							if( oContatoFolhaMensagemAviso.ListId( cidContato ) > 0 )
							{
								oContatoFolhaMensagemAviso.Update( cidContato
																  ,cidMensagemAviso
																  ,cidUser );
							}
							else
							{
								oContatoFolhaMensagemAviso.Insert( cidContato
																  ,cidMensagemAviso
																  ,cidUser );
							}
						}
						else
						{
							oContatoFolhaMensagemAviso.Delete( cidContato );
						}//if( strlennull( cidMensagemAviso ) > 0 )

						//Apaga os nomes nao utilizados
						oContato.LimpaNomeContato();

                        // charles resposta ao cliente
                        // Grupos associado
                        //  a teg  gruposAssociados esta vindo vazia por este motivo esta parte do codigo foi comentada
     					//  setStatusCode("14I0000","Criando Grupo");
						//  nNodeAuxiliar = walkDOM( dnode, "gruposAssociados", 0 );
						//  char* cidGrupo;
                        //  // deleta os registros ja existentes na tavela contatogrupoRC
						//  oContatoGrupoRC.Delete( cidContato );
						//  for( iCont = 0;; iCont++ )
						//  {
						//  	cidGrupo = oSafePointer.getTag( nNodeAuxiliar, "idGrupo", iCont );
						//  	if( strlennull( cidGrupo ) <= 0 )
						//  		break;
						//  	
						//  	oContatoGrupoRC.Insert( cidContato ,cidGrupo ,cidUser );
                        //  
						//  }
                        //  setStatusCode("14I0000","Grupo Criado");
                        
						if( iWarningNome == 1 )
							setStatusCode("14W0002","O nome digitado foi localizado no repositório de nomes e foi usado na criação do item da árvore, porém ele difere em letras maúsculas e minúsculas daquele que foi digitado");
						else
							setStatusCode("14I0000","Operação concluída com sucesso!");
					}//if( oContatoFolha.Update(...)
					else
						setStatusCode("14E0012", "Falha no processo de update da folha" );
				}//if( nNodeAuxiliar != NULL )
				else
					setStatusCode("14E0013","Esta faltando a TAG AdmDadosBasicosVO");

			}//if( strcmp( cinFolha, "1" ) == 0 )
			else
			{
				if( iWarningNome == 1 )
					setStatusCode("14W0002","O nome digitado foi localizado no repositório de nomes e foi usado na criação do item da árvore, porém ele difere em letras maúsculas e minúsculas daquele que foi digitado");
				else
					setStatusCode("14I0000","Operacao concluída com sucesso!");
			}
		}//case 0
		break;
		case 1: setStatusCode("14E0014", oContato.GetErro() );break;
		case 2: setStatusCode("14W0001", oContato.GetErro() );break;
		default: setStatusCode("14E9999", "Erro não listado" );break;
	}//switch( oContato.Update(...)
	ULOG_END("implCttEditar::Execute()");
}
