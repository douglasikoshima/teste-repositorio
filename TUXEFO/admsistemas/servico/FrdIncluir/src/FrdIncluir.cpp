/*****************************************************************************
 *
 * Modulo:    FrdIncluir
 * Arquivo:   FrdIncluir.cpp
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
#define FrdIncluirCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdUF.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"
#include "../../../negocio/admatdCmm/include/CUF.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdIncluir);

/**************************************************************************
 *  Funcao de Negocios:  FrdIncluir
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
void implFrdIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdIncluir::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CUFFeriado oUFFeriado;
	CFeriado oFeriado;
	CUF oUF;
	int x;
	int iNacional = 0;
	char* cAuxinRelatorio = "0";
	DOMNode* oNodeFeriado = walkDOM( dnode, "AdmFeriadoVO", 0 );
	if( oNodeFeriado != NULL )
	{
		char* cdtFeriado = oSafePointer.getTag( oNodeFeriado, "dtFeriado", 0 );
		char* cidDsFeriado = oSafePointer.getTag( oNodeFeriado, "idDsFeriado", 0 );
		char* cdsFeriado = oSafePointer.getTag( oNodeFeriado, "dsFeriado", 0 );
		char* cidTipoFeriado = oSafePointer.getTag( oNodeFeriado, "idTipoFeriado", 0 );
		char* cindMovel = oSafePointer.getTag( oNodeFeriado, "indMovel", 0 );
		char* cinRelatorio = oSafePointer.getTag( oNodeFeriado, "inRelatorio", 0 );
		char* cidUser = getUser();
		if( strlennull( cdtFeriado ) <= 0 )
		{
			setStatusCode("14E0001","dtFeriado está nulo");
			ULOG_END("implFrdIncluir::Execute()");
			return;
		}
		if( strlennull( cidDsFeriado ) <= 0 )
		{
			if( strlennull( cdsFeriado ) <= 0 )
			{
				setStatusCode("14E0002","dsFeriado está nulo");
				ULOG_END("implFrdIncluir::Execute()");
				return;
			}
		}
		if( strlennull( cidTipoFeriado ) <= 0 )
		{
			setStatusCode("14E0003","idTipoFeriado está nulo");
			ULOG_END("implFrdIncluir::Execute()");
			return;
		}
		if( strlennull( cindMovel ) <= 0 )
		{
			setStatusCode("14E0004","indMovel está nulo");
			ULOG_END("implFrdIncluir::Execute()");
			return;
		}
		if( strlennull( cinRelatorio ) <= 0 )
			cinRelatorio = cAuxinRelatorio;

		if( ( strcmp( cidTipoFeriado, "1" ) == 0 ) || ( strcmp( cidTipoFeriado, "4" ) == 0 ) )
			iNacional = 1;
		switch( oFeriado.Insert( cidTipoFeriado, 
								 cidDsFeriado,
								 cdsFeriado,
								 cindMovel, 
								 cdtFeriado,
								 cinRelatorio,
								 cidUser ) )
		{
			case 0://Erro
				setStatusCode("14E0005","Falha na tentativa de INSERT");
				break;
			case 1://Sucesso
				if( iNacional )
				{
					oUF.ListAll();
					for( x=0; x<oUF.Quantidade(); x++ )
					{
						oUFFeriado.Insert( oUF.Registro(x)->cidUF, oFeriado.Registro(0)->cidFeriado, cidUser );
					}
				}
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
				
				if( ( iNacional > 0 ) &&  ( oUF.Quantidade() <= 0 ) )
					setStatusCode("14W0001","Feriádo cadastrado, mas não existem UF cadastradas");
				else
					setStatusCode("14I0000","Operação concluída com sucesso!");
				break;
			case 2://Feriado foi apagado em outra estacao
				setStatusCode("14W0003","Tipo de feriado ou descrição do feriado foi apagado por outra estação");
				break;
			case 3://Feriado ja existe
				setStatusCode("14W0002","Feriado já existe");
				break;
			default://Erro nao esperado
				setStatusCode("14E0006","Erro não listado");
				break;
		}
	}
	else
		setStatusCode("14E0007","Não foi encontrada a tag AdmFeriadoVO");
		
	ULOG_END("implFrdIncluir::Execute()");
}
