/*****************************************************************************
 *
 * Modulo:    BXAUFOEDITAR
 * Arquivo:   BXAUFOEDITAR.cpp
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
#define BXAUFOEDITARCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxaUfo.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXAUFOEDITAR);

/**************************************************************************
 *  Funcao de Negocios:  BXAUFOEDITAR
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
void implBXAUFOEDITAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBXAUFOEDITAR::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixaUfoperadora oBaixaUfoperadora;
	char* cidBaixaUFOperadora = ""; //oSafePointer.getTag( dnode, "idBaixaUFOperadora", 0 );
	char* cidBaixa = oSafePointer.getTag( dnode, "idBaixa", 0 );
	char* cidUFOperadora = oSafePointer.getTag( dnode, "idUFOperadora", 0 );
	char* cdtInicioVigencia = oSafePointer.getTag( dnode, "dtInicioVigencia", 0 );
	char* cdtFimVigencia = oSafePointer.getTag( dnode, "dtFimVigencia", 0 );
	char* cinDisponibilidade = oSafePointer.getTag( dnode, "inDisponibilidade", 0 );
	char* cidUser = getUser();
	char  cindeterminado[1+1];
	memset(cindeterminado, 0, sizeof( cindeterminado ) );
	
	//if( strlennull( cidBaixaUFOperadora ) <= 0 )
	//{
	//	setStatusCode("14E0000","idBaixaUFOperadora está nulo");
	//	return;
	//}
	if( strlennull( cidBaixa ) <= 0 )
	{
		setStatusCode("14E0001","idBaixa está nulo");
		ULOG_END("implBXAUFOEDITAR::Execute()");
		return;
	}
	if( strlennull( cidUFOperadora ) <= 0 )
	{
		setStatusCode("14E0002","idUFOperadora está nulo");
		ULOG_END("implBXAUFOEDITAR::Execute()");
		return;
	}
	if( strlennull( cinDisponibilidade ) <= 0 )
	{
		setStatusCode("14E0003","inDisponibilidade está nulo");
		ULOG_END("implBXAUFOEDITAR::Execute()");
		return;
	}
	if( strlennull( cdtInicioVigencia ) <= 0 )
		cdtInicioVigencia = '\0';
	
	if( oBaixaUfoperadora.Update( cidBaixaUFOperadora
	                               ,cidBaixa
	                               ,cidUFOperadora
	                               ,cdtInicioVigencia
	                               ,cdtFimVigencia
	                               ,cinDisponibilidade
	                               ,cidUser ) )
		setStatusCode("14I0000","Serviço executado com sucesso");
	else
		setStatusCode("14E0004","Falha ao tentar realizar a edição de Baixa Operadoras");
		
	ULOG_END("implBXAUFOEDITAR::Execute()");
}
