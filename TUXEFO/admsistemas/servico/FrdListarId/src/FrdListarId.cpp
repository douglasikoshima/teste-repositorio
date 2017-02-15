/*****************************************************************************
 *
 * Modulo:    FrdListarId
 * Arquivo:   FrdListarId.cpp
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
#define FrdListarIdCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CFrd.h"
#include "../../../negocio/admatdCmm/include/CFrdNom.h"
#include "../../../negocio/admatdCmm/include/CFrdTip.h"
#include "../../../negocio/admatdCmm/include/CUF.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdListarId);

/**************************************************************************
 *  Funcao de Negocios:  FrdListarId
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
void implFrdListarId::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdListarId::Execute()");
	/* Chamada de Funcao de Negocios */
	CFeriado oFeriado;
	CUF oUF;
	CTipoFeriado oTipoFeriado;
	CNomeFeriado oNomeFeriado;
	
	int iDataIncial = 0;
	int iDataFinal = 0;

	xml_g->createTag( "AdmCalendarioContainerVO" );
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp( "xmlns:ns1", "cliente.fo.vivo.com.br/vo" );
	
	//Recupera a lista de UF
	if( oUF.ListAll() > 0 )
	{
		for(  int x=0; x<oUF.Quantidade();x++)
		{
			xml_g->createTag( "ns1:UFVO" );

			xml_g->addItem("ns1:idUF", oUF.Registro(x)->cidUF );
			xml_g->addItem("ns1:sgUF", oUF.Registro(x)->csgUF );
			xml_g->addItem("ns1:nmUF", oUF.Registro(x)->cnmUF );

			xml_g->closeTag();

		}
	}

	//Recupera a lista de Tipo de feriados
	oTipoFeriado.ListAll();
	oTipoFeriado.GetXml( "AdmTipoFeriadoVO", xml_g );

	//Recupera a lista de Tipo de feriados
	oNomeFeriado.ListAll();
	oNomeFeriado.GetXml( "AdmDescricaoFeriadoVO", xml_g );
	
	oFeriado.RangeAnos( iDataIncial, iDataFinal );
	for( int y=iDataIncial; y<=iDataFinal; y++ )
	{
		xml_g->createTag( "cmbCombo" );
			xml_g->addItem("descricao", y );
		xml_g->closeTag();//cmbCombo
	}
	
	xml_g->closeTag();//AdmCalendarioContainerVO
	setStatusCode("14I0000","Operacao concluída com sucesso!");
	ULOG_END("implFrdListarId::Execute()");
}
