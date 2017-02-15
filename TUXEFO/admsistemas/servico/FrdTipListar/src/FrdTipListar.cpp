/*****************************************************************************
 *
 * Modulo:    FrdTipListar
 * Arquivo:   FrdTipListar.cpp
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
#define FrdTipListarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdTipListar);

/**************************************************************************
 *  Funcao de Negocios:  FrdTipListar
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
void implFrdTipListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdTipListar::Execute()");
   
	CSafePointer oSafePointer;
	CFeriado oFeriado;
	char* cAnoBase = oSafePointer.getTag( dnode, "anoBase", 0 );
	char* cidUser = getUser();
	if( strlennull( cAnoBase ) <= 0 )
	{
		setStatusCode("14E0000","anoBase esta nulo");
		ULOG_END("implFrdTipListar::Execute()");
		return;
	}
	oFeriado.ListaPonte( cAnoBase );

	xml_g->createTag( "AdmCalendarioContainerVO" );
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	for( int x=0; x<oFeriado.Quantidade(); x++ )
	{

		xml_g->createTag( "AdmFeriadoVO" );

		xml_g->addItem("idFeriado", "" );
		xml_g->addItem("dtFeriado", oFeriado.Registro(x)->cdtDia );
		xml_g->addItem("idDsFeriado", oFeriado.Registro(x)->cidNomeFeriado );
		xml_g->addItem("dsFeriado", oFeriado.Registro(x)->cdsFeriado );
		xml_g->addItem("idTipoFeriado", oFeriado.Registro(x)->cidTipoFeriado );
		xml_g->addItem("relDlri", "" );
		xml_g->addItem("indMovel", oFeriado.Registro(x)->cinFeriadoMovel );
		xml_g->addItem("idUf", "" );
		xml_g->addItem("idMunicipio", "" );
		xml_g->addItem("dsTipoFeriado", oFeriado.Registro(x)->cdsTipoFeriado );
		xml_g->addItem("nmUF", oFeriado.Registro(x)->cnmUF );
		xml_g->addItem("inRelatorio", oFeriado.Registro(x)->cinRelatorio );

		xml_g->createTag( "relacionados" );
		xml_g->closeTag();

		xml_g->createTag( "existentes" );
		xml_g->closeTag();

		xml_g->closeTag();//AdmFeriadoVO

	}
	xml_g->closeTag();//AdmCalendarioContainerVO
	setStatusCode("14I0000","Operacao concluida com sucesso!");
	
	ULOG_END("implFrdTipListar::Execute()");

}
