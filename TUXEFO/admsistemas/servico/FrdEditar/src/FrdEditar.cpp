/*****************************************************************************
 *
 * Modulo:    FrdEditar
 * Arquivo:   FrdEditar.cpp
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
#define FrdEditarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdEditar);

/**************************************************************************
 *  Funcao de Negocios:  FrdEditar
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
void implFrdEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CFeriado oFeriado;
	DOMNode* oNodeFeriado = walkDOM( dnode, "AdmFeriadoVO", 0 );
	char* cAuxinRelatorio = "0";
	if( oNodeFeriado != NULL )
	{
		char* cidFeriado = oSafePointer.getTag( oNodeFeriado, "idFeriado", 0 );
		char* cdtFeriado = oSafePointer.getTag( oNodeFeriado, "dtFeriado", 0 );
		char* cidDsFeriado = oSafePointer.getTag( oNodeFeriado, "idDsFeriado", 0 );
		char* cdsFeriado = oSafePointer.getTag( oNodeFeriado, "dsFeriado", 0 );
		char* cidTipoFeriado = oSafePointer.getTag( oNodeFeriado, "idTipoFeriado", 0 );
		char* cindMovel = oSafePointer.getTag( oNodeFeriado, "indMovel", 0 );
		char* cinRelatorio = oSafePointer.getTag( oNodeFeriado, "inRelatorio", 0 );
		char* cUserId = getUser();
		if( strlennull( cidFeriado ) <= 0 )
		{
			setStatusCode("14E0001","idFeriado está nulo");
			ULOG_END("implFrdEditar::Execute()");
			return;
		}
		if( strlennull( cdtFeriado ) <= 0 )
		{
			setStatusCode("14E0002","dtFeriado está nulo");
			ULOG_END("implFrdEditar::Execute()");
			return;
		}
		if( strlennull( cidDsFeriado ) <= 0 )
		{
			if( strlennull( cdsFeriado ) <= 0 )
			{
				setStatusCode("14E0003","dsFeriado está nulo");
				ULOG_END("implFrdEditar::Execute()");
				return;
			}
		}
		if( strlennull( cidTipoFeriado ) <= 0 )
		{
			setStatusCode("14E0004","idTipoFeriado está nulo");
			ULOG_END("implFrdEditar::Execute()");
			return;
		}
		if( strlennull( cindMovel ) <= 0 )
		{
			setStatusCode("14E0005","indMovel está nulo");
			ULOG_END("implFrdEditar::Execute()");
			return;
		}
		if( strlennull( cinRelatorio ) <= 0 )
			cinRelatorio = cAuxinRelatorio;
		switch( oFeriado.Update( cidFeriado, 
								 cidTipoFeriado, 
								 cidDsFeriado,
								 cdsFeriado,
								 cindMovel, 
								 cdtFeriado,
								 cinRelatorio,
								 cUserId ) )
		{
			case 0://Erro
				setStatusCode("14E0006","Falha na tentativa de UPDATE");
				
				break;
			case 1://Sucesso
				xml_g->createTag( "AdmCalendarioContainerVO" );
				xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );

				xml_g->createTag( "AdmFeriadoVO" );

				xml_g->addItem("idFeriado", oFeriado.Registro(0)->cidFeriado );
				xml_g->addItem("dtFeriado", oFeriado.Registro(0)->cdtDia );
				xml_g->addItem("idDsFeriado", oFeriado.Registro(0)->cidNomeFeriado );
				xml_g->addItem("dsFeriado", oFeriado.Registro(0)->cdsFeriado );
				xml_g->addItem("idTipoFeriado", oFeriado.Registro(0)->cidTipoFeriado );
				xml_g->addItem("relDlri", "" );
				xml_g->addItem("indMovel", oFeriado.Registro(0)->cinFeriadoMovel );
				xml_g->addItem("idUf", oFeriado.Registro(0)->cidUF );
				xml_g->addItem("idMunicipio", oFeriado.Registro(0)->cidMunicipio );
				xml_g->addItem("dsTipoFeriado", oFeriado.Registro(0)->cdsTipoFeriado );
				xml_g->addItem("nmUF", oFeriado.Registro(0)->cnmUF );
				xml_g->addItem("inRelatorio", oFeriado.Registro(0)->cinRelatorio );
				
				xml_g->createTag( "relacionados" );
				xml_g->closeTag();

				xml_g->createTag( "existentes" );
				xml_g->closeTag();

				xml_g->closeTag();

				xml_g->closeTag();

				setStatusCode("14I0000","Operação concluída com sucesso!");
				break;
			case 2://Feriado ja existe
				setStatusCode("14W0001","Feriádo já existe");
				break;
			case 3://Feriado foi apagado em outra estacao
				setStatusCode("14W0002","Tipo de feriado ou descrição do feriado foi apagado por outra estação");
				break;
			default://Erro nao esperado
				setStatusCode("14E0006","Erro não listado");
				break;
		}//switch( oFeriado.Update( ... )
	}
	else
		setStatusCode("14E0007","Não foi encontrada a tag AdmFeriadoVO");
		
	ULOG_END("implFrdEditar::Execute()");
}
