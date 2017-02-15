/*****************************************************************************
 *
 * Modulo:    UsrAcesso
 * Arquivo:   UsrAcesso.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c 
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDJMartins          Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/

#define USRACESSOLDAPCPP

/*****************************************************************************
 * Header de Classe de Infra-Estrutura
 ****************************************************************************/

#include "../../../negocio/acessoCmm/include/CAcs.h"
#include <tuxfw.h> 
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

/*****************************************************************************
 * Macro de Definicao do Framework Tuxedo
 ****************************************************************************/

DECLARE_TUXEDO_SERVICE(USRACESSOLDAP);

/**************************************************************************
 *  Funcao de Negocios:  UsrAcesso
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

/*****************************************************************************
 * Funcao Local
 ****************************************************************************/

void implUSRACESSOLDAP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUSRACESSOLDAP::Execute()");
	
	CSafePointer oSafePointer;
	char*     cnmUrlPagina;
	char cdsSessaoUsuario[500+1];
	CAcs      oAcesso;

	cnmUrlPagina = oSafePointer.getTag( dnode, "pagina", 0 );

	if( strlen( cnmUrlPagina ) > 0 )
	{
		xml_g->createTag( "ControleAcessoRecebimentoVO" );
		xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );
		if( oAcesso.ListId( cnmUrlPagina, getUser() ) > 0 )
		{
			for( int x = 0; x < oAcesso.Quantidade(); x++ )
			{
				if( oAcesso.Registro(x) != NULL )
					xml_g->addItem("controle", oAcesso.Registro( x )->cnmAcessoUsuario );
			}
			if(oAcesso.GetSessaoUsuario(getUser(),cdsSessaoUsuario) > 0)
			{
				xml_g->addItem("dsSessaoUsuario",cdsSessaoUsuario);
			}
			setStatusCode("08I0000","Sucesso");
		}
		else
			setStatusCode("08W0000","Acesso negado");
		xml_g->closeTag();
	}//else if( strlen( cnmUrlPagina ) > 0 )
	else
		setStatusCode("08E0000","ID da pagina nulo");
	/************************************************************************
	 * Desalocacao de Ponteiros
	 ************************************************************************/
	 
	 ULOG_END("implUSRACESSOLDAP::Execute()");
}
