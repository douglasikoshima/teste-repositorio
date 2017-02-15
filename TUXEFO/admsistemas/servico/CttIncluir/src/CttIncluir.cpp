/*****************************************************************************
 *
 * Modulo:    CttIncluir
 * Arquivo:   CttIncluir.cpp
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:41:29 $ 
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
 *  setStatusCode("14E0020","vlPrazoAnatel está nulo"); ultimo numero de erro
 **************************************************************************/

//Definicao Global
#define CttIncluirCPP

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
DECLARE_TUXEDO_SERVICE(CttIncluir);

/**************************************************************************
 *  Funcao de Negocios:  CttIncluir
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
void implCttIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttIncluir::Execute()");
    setStatusCode("12I0000", "Inicio do Servico CttIncluir");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContato oContato;
	CIndicadorContatoFolha oIndicadorContatoFolha;
	CContatoFolhaMensagemAviso oContatoFolhaMensagemAviso;
	CMensagemAviso oMensagemAviso;
	CContatoFolha oContatoFolha;
    CContatoGrupoRC oContatoGrupoRC;

	DOMNode* nNodeAuxiliar;
	DOMNode* nNodeSMP;
	DOMNode* nNodeSMS;
	DOMNode* nNodeCanais;
	int iWarningNome = 0;

	char* cidContato;
	char* cinDisponibilidade = "1";//oSafePointer.getTag( dnode, "inDisponibilidade", 0 );
	char* cidContatoPai = oSafePointer.getTag( dnode, "idContatoPai", 0 );
	char* cnmContato = oSafePointer.getTag( dnode, "nmContato", 0 );
	char* cidNomeContato = oSafePointer.getTag( dnode, "idNomeContato", 0 );
	char* cinFolha = oSafePointer.getTag( dnode, "inFolha", 0 );
    char* cidTipoArvore = oSafePointer.getTag( dnode, "idTipoArvore", 0 );
	char* cidUser = getUser();
	if( strlennull( cidContatoPai ) <= 0 )
	{
		setStatusCode("14E0001","idContatoPai está nulo");
		ULOG_END("implCttIncluir::Execute()");
		return;
	}
	if( strlennull( cinFolha ) <= 0 )
	{
		setStatusCode("14E0002","inFolha está nulo");
		ULOG_END("implCttIncluir::Execute()");
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
		ULOG_END("implCttIncluir::Execute()");
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
	
	switch( oContato.Insert( cidContatoPai,
						     cidNomeContato,
						     cnmContato,
						     cinDisponibilidade, 
							 cinFolha,
						     cidUser,
                             cidTipoArvore) )
	{
		case 0://Sucesso
		{
			cidContato = oContato.Registro(0)->cidContato;
			oContato.atualizaPath( cidContato );
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
                    char* cInexibeProtocolo ="0";
                    char* cInAberturaContato = "1"; // valor default na base
					char* cDsContatoCanais = 0x0;
					char* cDsMsgExcecao = 0x0;
					char* cInCancelamento = 0x0;
                    char* csgRegraEncaminhamento = 0x0;
                    char* csgFluxoAtendimento = 0x0;
					
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
					if( strlennull( cidTipoRetorno ) <= 0 )
					{
						setStatusCode("14E0005","idTipoRetornoAtual está nulo");
						ULOG_END("implCttIncluir::Execute()");
						return;
					}
					if( strlennull( cidTipoFechamento ) <= 0 )
					{
						setStatusCode("14E0006","idTipoFechamento está nulo");
						ULOG_END("implCttIncluir::Execute()");
						return;
					}
					if( strlennull( cidTipoProcesso ) <= 0 )
					{
						setStatusCode("14E0007","idTipoProcessoAtual está nulo");
						ULOG_END("implCttIncluir::Execute()");
						return;
					}
					if( strlennull( cqtHorasPrazo ) <= 0 )
					{
						setStatusCode("14E0008","qtDiasPrazoContato está nulo");
						ULOG_END("implCttIncluir::Execute()");
						return;
					}

					if( strlennull( cinFechamentoImediato ) <= 0 )
					{
						setStatusCode("14E0009","inFechamentoImediato está nulo");
						ULOG_END("implCttIncluir::Execute()");
						return;
					}
					if( strlennull( cvlPeso ) <= 0 )
					{
						setStatusCode("14E00010","vlPeso está nulo");
						ULOG_END("implCttIncluir::Execute()");
						return;
					}
					if( strlennull( cinProcessoTecnico ) <= 0 )
					{
						setStatusCode("14E0011","inProcessoTecnico está nulo");
						ULOG_END("implCttIncluir::Execute()");
						return;
					}

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
    				    ULOG( "Achou SMP..." );

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
                        csgRegraEncaminhamento = oSafePointer.getTag( nNodeAuxiliar, "sgRegraEncaminhamento", 0 );
                        csgFluxoAtendimento = oSafePointer.getTag( nNodeAuxiliar, "sgFluxoAtendimento", 0 );

    					if( strlennull( cInSms ) <= 0 )
    					{
    						setStatusCode("14E0012","cInSms está nulo");
    						ULOG_END("implCttIncluir::Execute()");
    						return;
    					}
    					if( strlennull( cInRelacionamento ) <= 0 )
    					{
    						setStatusCode("14E0015","cInRelacionamento está nulo");
    						ULOG_END("implCttIncluir::Execute()");
    						return;
    					}
    					if( strlennull( cInProtocolo ) <= 0 )
    					{
    						setStatusCode("14E0015","cInProtocolo está nulo");
    						ULOG_END("implCttIncluir::Execute()");
    						return;
    					}
    					if( strlennull( cInCancelamento ) <= 0 )
    					{
    						setStatusCode("14E0015","cInCancelamento está nulo");
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
					if( oContatoFolha.Insert( cidContato
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
                                             ,cInexibeProtocolo
                                             ,cDsContatoCanais
                                             ,cDsMsgExcecao
											 ,cInCancelamento
											 ,cidClassificacao
                                             ,cInAberturaContato
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
							
							oIndicadorContatoFolha.Insert( cidContato ,cidIndicador ,cidUser );

						}
						//Relacina a mensagem ao contato
						if( strlennull( cidMensagemAviso ) > 0 )
						{
							oContatoFolhaMensagemAviso.Insert( cidContato ,cidMensagemAviso ,cidUser );
						}
						if( iWarningNome == 1 )
							setStatusCode("14W0002","O nome digitado foi localizado no repositório de nomes e foi usado na criação do item da árvore, porém ele difere em letras maúsculas e minúsculas daquele que foi digitado");
						else
							setStatusCode("14I0000","Operação concluída com sucesso!");

                        // charles resposta ao cliente
                        // Grupos associado
     					setStatusCode("14I0000","Criando Grupo");
						nNodeAuxiliar = walkDOM( dnode, "gruposAssociados", 0 );
						char* cidGrupo;
                        // deleta os registros ja existentes na tavela contatogrupoRC
						oContatoGrupoRC.Delete( cidContato );
						for( iCont = 0;; iCont++ )
						{
							cidGrupo = oSafePointer.getTag( nNodeAuxiliar, "idGrupo", iCont );
							if( strlennull( cidGrupo ) <= 0 )
								break;
							
							oContatoGrupoRC.Insert( cidContato ,cidGrupo ,cidUser,iCont+1 );

						}
                        setStatusCode("14I0000","Grupo Criado");

					}
					else
                    {
						setStatusCode("14E0012", "Falha no processo de update da folha" );
                    }

                    // Pré-habilita o contato em alguns casos
                    char dsTipoProcesso[256];
                    oContato.ObterTipoProcesso(cidContato,dsTipoProcesso);
                    
                    if ( strcmp(dsTipoProcesso,"PORTOUT") == 0 )
                    {
                        oContato.HabilitarContato(cidContato,cidUser);
                    }

				}//if( nNodeAuxiliar != NULL )
				else
					setStatusCode("14E0013","Está faltando a TAG AdmDadosBasicosVO");

			}//if( strcmp( cinFolha, "1" ) == 0 )
			else
			{
				if( iWarningNome == 1 )
					setStatusCode("14W0002","O nome digitado foi localizado no repositório de nomes e foi usado na criação do item da árvore, porém ele difere em letras maúsculas e minúsculas daquele que foi digitado");
				else
					setStatusCode("14I0000","Operação concluída com sucesso!");
			}
		}//case 0:
		break;
		case 1:
			setStatusCode("14E0014", oContato.GetErro() ); break;
		case 2:
			setStatusCode("14W0001", oContato.GetErro() ); break;
		default:
			setStatusCode("14E9999", "Erro não listado" ); break;
	}//switch( oContato.Insert(...)
	
	ULOG_END("implCttIncluir::Execute()");
		
}
