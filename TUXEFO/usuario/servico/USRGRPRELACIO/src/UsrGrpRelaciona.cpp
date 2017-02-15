/*****************************************************************************
 *
 * Modulo:    UsrGrpRelaciona
 * Arquivo:   UsrGrpRelaciona.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDJMartins          Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define UsrGrpRelacionaCPP

//Header de Usr de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CUsr.h"
#include "../../../negocio/acessoCmm/include/CGrp.h"
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(USRGRPRELACIO);

/**************************************************************************
 *  Funcao de Negocios:  UsrGrpRelaciona
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
void implUSRGRPRELACIO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUSRGRPRELACIO::Execute()");
	
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CUsr oUsuario;
	CGrp oGrupoUsuarioNovo;
	CGrp oGrupoUsuarioOld;
	int   iCont;
	int   iPos;
	int   iGruposInseridos = 0;
	int   iSucesso = 0;
	char* cidUser = getUser();
	char* cidUsuario = oSafePointer.getTag( dnode, "idUsuario", 0 );
	char* cidGrupo;
	if( strlennull( cidUsuario ) <= 0 )
	{
		setStatusCode("08E0001","idUsuario esta nulo");
		ULOG_END("implUSRGRPRELACIO::Execute()");
		return;
	}
	//Recupera todos os grupos do usuario
	oGrupoUsuarioOld.GrupoUsuarioRelacao( cidUsuario );
	for( iCont=0;;iCont++ )
	{
		cidGrupo = oSafePointer.getTag( dnode, "idGrupo", iCont );
		if( strlennull( cidGrupo ) <= 0 )
			break;
		iGruposInseridos++;

		//Se nao achar na lista nova, entao insere
		if( ( iPos = oGrupoUsuarioOld.Find( cidGrupo ) ) == -1 )
		{
			//Alem de inserir o metodo salva os dados inseridos na estrutura interna
			oGrupoUsuarioNovo.GrupoUsuarioInsert( cidUsuario
				                                 ,cidGrupo
									             ,cidUser );
		}
		else
		{
			//Como foi achado na lista velha, entao salva na nova para que nao seja apagado
			oGrupoUsuarioNovo.GrupoUsuarioInsertFalso( oGrupoUsuarioOld.Registro(iPos)->cidPessoaUsuario
                                                      ,oGrupoUsuarioOld.Registro(iPos)->cidGrupo );
		}
	}
	//Soh realiza verificacoes se tiver removido todos os grupos
	if( iGruposInseridos == 0 )
	{
		//Soh verifica se usuario estiver ativo
		if( !oUsuario.MesmoStatusSigla( cidUsuario, "DESLIGADO" ) )
		{
			setStatusCode("08W0001","Somente usuário DESLIGADO pode ficar sem grupos associados.");
			ULOG_END("implUSRGRPRELACIO::Execute()");
			return;
		}
	}

	//Agora percorre a lista antiga e apaga os que nao estao na lista nova
	for( iCont=0;iCont<oGrupoUsuarioOld.Quantidade();iCont++ )
	{
		//Busca na lista nova, caso não encontre:
		//Verifica se tem atendimentos abertos, caso sim. 
		// Os mesmos são deletados mas, neste caso, são gravados na 
		// tabela de "INATIVOS" para que sejam usados 
		// pelo módulo de workflow
		if( oGrupoUsuarioNovo.Find( oGrupoUsuarioOld.Registro(iCont)->cidGrupo ) == -1 )
		{
			//Exclui da base/lista antiga
			oGrupoUsuarioOld.GrupoUsuarioDelete( oGrupoUsuarioOld.Registro(iCont)->cidUsuarioGrupo );

			if( oGrupoUsuarioOld.TemAtendimentosAbertos( oGrupoUsuarioOld.Registro(iCont)->cidGrupo, 
														 oGrupoUsuarioOld.Registro(iCont)->cidPessoaUsuario ) > 0 )
			{
				// Caso possua atendimento abertos grava na tabela de INATIVOS
				// Caso já exista na tabela de INATIVOS, apenas atualiza
				oGrupoUsuarioOld.GrupoUsuarioInativoInsert( cidUsuario
															,oGrupoUsuarioOld.Registro(iCont)->cidGrupo
															,cidUser );
			}else{
					// Caso não possua ATIVOS verificados no primeiro IF
					// e nem atendimentos abertos verificado no IF acima 
					// podemos excluir os INATIVOS
					oGrupoUsuarioOld.GrupoUsuarioInativoDelete( oGrupoUsuarioOld.Registro(iCont)->cidGrupo, 
																oGrupoUsuarioOld.Registro(iCont)->cidPessoaUsuario );
			}
		}
	}

	oUsuario.UsrGrpRelacao(dnode,xml_g); 
	
	setStatusCode("08I0000","Servico executado com sucesso.");
	ULOG_END("implUSRGRPRELACIO::Execute()");
}
