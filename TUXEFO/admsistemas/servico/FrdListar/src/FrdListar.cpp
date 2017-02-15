/*****************************************************************************
 *
 * Modulo:    FrdListar
 * Arquivo:   FrdListar.cpp
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
#define FrdListarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdListar);

/**************************************************************************
 *  Funcao de Negocios:  FrdListar
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
void implFrdListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdListar::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CFeriado oFeriado;

	char* cdtDia;
	char* cdsFeriado;
	char* cidTipoFeriado;
	char* cinFeriadoMovel;
	char* cidUF;
	char* cidMunicipio;
	char* cinRelatorio;
	char* cdescricao;

	cdtDia = oSafePointer.getTag( dnode, "dtFeriado", 0 );
	cdsFeriado = oSafePointer.getTag( dnode, "dsFeriado", 0 );
	cidTipoFeriado = oSafePointer.getTag( dnode, "idTipoFeriado", 0 );
	cinFeriadoMovel = oSafePointer.getTag( dnode, "indMovel", 0 );
	cidUF = oSafePointer.getTag( dnode, "idUf", 0 );
	cidMunicipio = oSafePointer.getTag( dnode, "idMunicipio", 0 );
	cinRelatorio = oSafePointer.getTag( dnode, "inRelatorio", 0 );
	//Este campo contem o ano no formato YYYY
	cdescricao = oSafePointer.getTag( dnode, "descricao", 0 );

	// Tags relativas a consulta com paginação
	char* pzcPaginaAtual = oSafePointer.getTag( dnode, "paginaAtual" );			// Esta tag não é obrigatória
	char* pzcregistrosPPagina = oSafePointer.getTag( dnode, "registrosPPagina" ); // Esta tag não é obrigatória

	// converte as informacoes de pagina e numero de registros
	int iPaginaAtual = 0;		if( pzcPaginaAtual ) { iPaginaAtual = atoi(pzcPaginaAtual); } 
	int iRegistrosPPagina = 0;	if( pzcregistrosPPagina ) { iRegistrosPPagina = atoi(pzcregistrosPPagina); } 

    ULOG("FrdListar::Execute: pzcPaginaAtual=[%s](%d)", pzcPaginaAtual, iPaginaAtual);
    ULOG("FrdListar::Execute: pzcregistrosPPagina=[%s](%d)", pzcregistrosPPagina, iRegistrosPPagina);


	oFeriado.ListPag(cdtDia,
				  cdsFeriado,
		          cidTipoFeriado,
				  cinFeriadoMovel,
				  cinRelatorio,
				  cidUF,
				  cidMunicipio,
				  cdescricao,
                  iPaginaAtual,
                  iRegistrosPPagina );

	xml_g->createTag( "AdmCalendarioContainerVO" );
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );

    // Acrescenta a informação de paginação.
    xml_g->addItem( "paginaAtual", iPaginaAtual );
    xml_g->addItem( "registrosPPagina", iRegistrosPPagina );


	if( oFeriado.Quantidade() > 0 )
	{
		for( int x = 0; x<oFeriado.Quantidade(); x++ )
		{
			xml_g->createTag( "AdmFeriadoVO" );

			xml_g->addItem("idFeriado", oFeriado.Registro(x)->cidFeriado );
			xml_g->addItem("dtFeriado", oFeriado.Registro(x)->cdtDia );
			xml_g->addItem("idDsFeriado", oFeriado.Registro(x)->cidNomeFeriado );
			xml_g->addItem("dsFeriado", oFeriado.Registro(x)->cdsFeriado );
			xml_g->addItem("idTipoFeriado", oFeriado.Registro(x)->cidTipoFeriado );
			xml_g->addItem("relDlri", "" );
			xml_g->addItem("indMovel", oFeriado.Registro(x)->cinFeriadoMovel );
			xml_g->addItem("idUf", oFeriado.Registro(x)->cidUF );
			xml_g->addItem("idMunicipio", oFeriado.Registro(x)->cidMunicipio );
			xml_g->addItem("dsTipoFeriado", oFeriado.Registro(x)->cdsTipoFeriado );
			xml_g->addItem("nmUF", oFeriado.Registro(x)->cnmUF );
			xml_g->addItem("inRelatorio", oFeriado.Registro(x)->cinRelatorio );
			
			xml_g->createTag( "relacionados" );
			xml_g->closeTag();

			xml_g->createTag( "existentes" );
			xml_g->closeTag();

			xml_g->closeTag();
		}
	}

	xml_g->closeTag();
	setStatusCode("14I0000","Operacao concluída com sucesso!");
	
	ULOG_END("implFrdListar::Execute()");
}
