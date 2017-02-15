/*****************************************************************************
 *
 * Modulo:    FrdMncIncluir
 * Arquivo:   FrdMncIncluir.cpp
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
#define FrdMncIncluirCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"
#include "../../../negocio/admatdCmm/include/CFrdMnc.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdMncIncluir);

/**************************************************************************
 *  Funcao de Negocios:  FrdMncIncluir
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
void implFrdMncIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdMncIncluir::Execute()");
	CSafePointer oSafePointer;
	CFeriado oFeriado;
	CMunicipioFeriado oMunicipioFeriado;
	DOMNode* oNodeRelacionados;
	DOMNode* oNodeFeriado = walkDOM( dnode, "AdmFeriadoVO", 0 );
	char* cidUf;
	char* cidMunicipio;
	char* cidFeriado;
	char* cidUser = getUser();
	int   iRet = 0;
	int   iStatus = 0;

	if( oNodeFeriado == NULL )
	{
		setStatusCode("14E0001","Não foi encontrada a tag [AdmFeriadoVO]");
		ULOG_END("implFrdMncIncluir::Execute()");
		return;
	}

	cidUf = oSafePointer.getTag( oNodeFeriado, "idUf", 0 );
	if( strlennull( cidUf ) <= 0 )
	{
		setStatusCode("14E0002","idUf está nulo");
		ULOG_END("implFrdMncIncluir::Execute()");
		return;
	}
	cidFeriado = oSafePointer.getTag( oNodeFeriado, "idFeriado", 0 );
	if( strlennull( cidFeriado ) <= 0 )
	{
		setStatusCode("14E0003","idFeriado está nulo");
		ULOG_END("implFrdMncIncluir::Execute()");
		return;
	}
	oNodeRelacionados = walkDOM( dnode, "relacionados", 0 );
	if( oNodeRelacionados == NULL )
	{
		setStatusCode("14E0004","Não foi encontrada a tag [relacionados]");
		ULOG_END("implFrdMncIncluir::Execute()");
		return;
	}

	//Apaga todas as ligacoes de UF com este feriado
	oMunicipioFeriado.EraseFrd( cidFeriado, cidUf );
	for( int iCont = 0;; iCont++ )
	{
		cidMunicipio = oSafePointer.getTag( oNodeRelacionados, "idMunicipio", iCont );
		if( strlennull( cidMunicipio ) <= 0 )
			break;
		iRet = oMunicipioFeriado.Insert( cidMunicipio, cidFeriado, cidUser );
		if( iRet == 1 )//Id apagado por outra estacao (Warning)
			iStatus = 1;
		else if( iRet == 2 )//id duplicado na tabela de relacao (Erro)
		{
			iStatus = 2;
			break;
		}

	}

	if( iStatus != 2)
		oFeriado.RelacaoFeriadoMunicipio( cidUf, cidFeriado, xml_g );

	if( iStatus == 0 )
		setStatusCode("14I0000","Operação concluída com sucesso!");
	else if( iStatus == 1 )
		setStatusCode("14W0001","Um ou mais municípios foram apagados por outra estação e não puderam ser relacinados. Os demais foram relacionados com sucesso!");
	else
		setStatusCode("14E0005","Não foi possível relacionar os municípios. Problemas com a base em CALENDARIO.MUNICIPIOFERIADO");
	ULOG_END("implFrdMncIncluir::Execute()");
}
