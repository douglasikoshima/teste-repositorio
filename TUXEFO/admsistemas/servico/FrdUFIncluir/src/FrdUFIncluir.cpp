/*****************************************************************************
 *
 * Modulo:    FrdUFIncluir
 * Arquivo:   FrdUFIncluir.cpp
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
#define FrdUFIncluirCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"
#include "../../../negocio/admatdCmm/include/CFrdUF.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdUFIncluir);

/**************************************************************************
 *  Funcao de Negocios:  FrdUFIncluir
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
void implFrdUFIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdUFIncluir::Execute()");
	CSafePointer oSafePointer;
	CFeriado oFeriado;
	CUFFeriado oUFFeriado;
	DOMNode* oNodeRelacionados;
	DOMNode* oNodeFeriado = walkDOM( dnode, "AdmFeriadoVO", 0 );
	char* cidUF;
	char* cidFeriado;
	char* cidUser = getUser();

	if( oNodeFeriado == NULL )
	{
		setStatusCode("14E0001","Nao foi encontrada a tag [AdmFeriadoVO]");
		ULOG_END("implFrdUFIncluir::Execute()");
		return;
	}

	cidFeriado = oSafePointer.getTag( oNodeFeriado, "idFeriado", 0 );
	if( strlennull( cidFeriado ) <= 0 )
	{
		setStatusCode("14E0002","idFeriado esta nulo");
		ULOG_END("implFrdUFIncluir::Execute()");
		return;
	}
	oNodeRelacionados = walkDOM( dnode, "relacionados", 0 );
	if( oNodeRelacionados == NULL )
	{
		setStatusCode("14E0003","Nao foi encontrada a tag [relacionados]");
		ULOG_END("implFrdUFIncluir::Execute()");
		return;
	}

	//Apaga todas as ligacoes de UF com este feriado
	oUFFeriado.EraseFrd( cidFeriado );
	for( int iCont = 0;; iCont++ )
	{
		cidUF = oSafePointer.getTag( oNodeRelacionados, "vo1:idUF", iCont );
		if( strlennull( cidUF ) <= 0 )
			break;
		oUFFeriado.Insert( cidUF, cidFeriado, cidUser );
	}

	oFeriado.RelacaoFeriadoUF( cidFeriado, xml_g );

	setStatusCode("14I0000","Operacao concluida com sucesso!");
	
	ULOG_END("implFrdUFIncluir::Execute()");
}
