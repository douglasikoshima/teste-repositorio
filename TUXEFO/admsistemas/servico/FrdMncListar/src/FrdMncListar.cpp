/*****************************************************************************
 *
 * Modulo:    FrdMncListar
 * Arquivo:   FrdMncListar.cpp
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
#define FrdMncListarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdMncListar);

/**************************************************************************
 *  Funcao de Negocios:  FrdMncListar
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
void implFrdMncListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdMncListar::Execute()");
	CSafePointer oSafePointer;
	CFeriado oFeriado;
	DOMNode* oNodeFeriado = walkDOM( dnode, "AdmFeriadoVO", 0 );
	char* cidFeriado;
	char* cidUf;

	if( oNodeFeriado == NULL )
	{
		setStatusCode("14E0001","Não foi encontrada a tag AdmFeriadoVO");
		ULOG_END("implFrdMncListar::Execute()");
		return;
	}

	cidFeriado = oSafePointer.getTag( oNodeFeriado, "idFeriado", 0 );
	if( strlennull( cidFeriado ) <= 0 )
	{
		setStatusCode("14E0002","idFeriado está nulo");
		ULOG_END("implFrdMncListar::Execute()");
		return;
	}
	cidUf = oSafePointer.getTag( oNodeFeriado, "idUf", 0 );
	if( strlennull( cidUf ) <= 0 )
	{
		setStatusCode("14E0003","idMunicipio está nulo");
		ULOG_END("implFrdMncListar::Execute()");
		return;
	}

	oFeriado.RelacaoFeriadoMunicipio( cidUf, cidFeriado, xml_g );

	setStatusCode("14I0000","Operaçao concluida com sucesso!");
	ULOG_END("implFrdMncListar::Execute()");
}
