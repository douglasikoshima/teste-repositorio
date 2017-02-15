/*****************************************************************************
 *
 * Modulo:    FrdNomListar
 * Arquivo:   FrdNomListar.cpp
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
#define FrdNomListarCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdNomListar);

/**************************************************************************
 *  Funcao de Negocios:  SrvEditar
 *
 *  Descricao: chama funcao de negocios
 *
 *  Parametros de Entrada e Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de e	ntrada
 *
 *  Parametros de Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de saida
 *
 *************************************************************************/
void implFrdNomListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdNomListar::Execute()");
	CSafePointer oSafePointer;
	CFeriado oFeriado;
	char* cAnoBase = oSafePointer.getTag( dnode, "anoBase", 0 );
	char* cAnoCopia = oSafePointer.getTag( dnode, "anoCopia", 0 );
	char* cidUser = getUser();
	char  cidFeriado[21+1];
	int   iPrimeiro = 0;
	if( strlennull( cAnoBase ) <= 0 )
	{
		setStatusCode("14E0000","anoBase está nulo");
		ULOG_END("implFrdNomListar::Execute()");
		return;
	}
	if( strlennull( cAnoCopia ) <= 0 )
	{
		setStatusCode("14E0000","anoCopia está nulo");
		ULOG_END("implFrdNomListar::Execute()");
		return;
	}

	memset( cidFeriado, 0, sizeof( cidFeriado ) );

	switch( oFeriado.CopiaCalendario( cAnoBase, cAnoCopia, cidUser ) )
	{
		case 0:
			setStatusCode("14W0001","Não existem datas para copiar");
			ULOG_END("implFrdNomListar::Execute()");
			return;
		case -1:
			setStatusCode("14W0002","O calendário não pode se copiado para este ano porque já existem datas cadastradas.");
			ULOG_END("implFrdNomListar::Execute()");
			return;
		default:
			//Nao faz nada, apenas permite o codigo a continuar
			break;
	}
	xml_g->createTag( "AdmCalendarioContainerVO" );
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp( "xmlns:ns1", "cliente.fo.vivo.com.br/vo" );
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
		//Se for feriado nacional nao retorno UF nem municipio
		if( strcmp( oFeriado.Registro(x)->cidTipoFeriado, FO_FRD_NACIONAL ) == 0 )
		{
			xml_g->addItem("idUf", "" );
			xml_g->addItem("idMunicipio", "" );
			xml_g->addItem("nmUF", "" );
		}
		else
		{
			if( strcmp( oFeriado.Registro(x)->cidTipoFeriado, FO_FRD_ESTADUAL ) == 0 )
			{
				xml_g->addItem("idMunicipio", "" );
				xml_g->addItem("idUf", oFeriado.Registro(x)->cidUF );
				xml_g->addItem("nmUF", oFeriado.Registro(x)->cnmUF );
			}
			else
			{
				if( strcmp( oFeriado.Registro(x)->cidMunicipio, "0" ) == 0 )
					xml_g->addItem("idMunicipio", "" );
				else
					xml_g->addItem("idMunicipio", oFeriado.Registro(x)->cidMunicipio );

				xml_g->addItem("idUf", "" );
				xml_g->addItem("nmUF", "" );
			}
		}
		xml_g->addItem("dsTipoFeriado", oFeriado.Registro(x)->cdsTipoFeriado );
		xml_g->addItem("inRelatorio", oFeriado.Registro(x)->cinRelatorio );
		
		xml_g->createTag( "relacionados" );
		strcpy( cidFeriado, oFeriado.Registro(x)->cidFeriado );
		iPrimeiro = 1;
		for(;;)
		{
			if( strcmp( oFeriado.Registro(x)->cidTipoFeriado, FO_FRD_NACIONAL ) == 0 )
			{
				if( iPrimeiro )
				{
					iPrimeiro = 0;
					xml_g->createTag( "ns1:UFVO" );
					xml_g->closeTag();
					xml_g->createTag( "MunicipioVO" );
					xml_g->closeTag();
				}
			}
			else
			{
				if( strcmp( oFeriado.Registro(x)->cidTipoFeriado, FO_FRD_ESTADUAL ) == 0 )
				{
					xml_g->createTag( "ns1:UFVO" );
					xml_g->addItem("ns1:idUF", oFeriado.Registro(x)->cidUF );
					xml_g->addItem("ns1:sgUF", "" );
					xml_g->addItem("ns1:nmUF", "" );
					xml_g->closeTag();
				}
				else
				{
					if( strcmp( oFeriado.Registro(x)->cidMunicipio, "0" ) != 0 )
					{
						xml_g->createTag( "MunicipioVO" );
						xml_g->addItem("idUf", "" );
						xml_g->addItem("idMunicipio", oFeriado.Registro(x)->cidMunicipio );
						xml_g->addItem("nmMunicipio", "" );
						xml_g->closeTag();
					}
				}
			}

			x++;

			if( x >= oFeriado.Quantidade() )
				break;

			if( strcmp( cidFeriado, oFeriado.Registro(x)->cidFeriado ) != 0 )
			{
				x--;
				break;
			}
			
		}

		xml_g->closeTag();

		xml_g->createTag( "existentes" );
		xml_g->closeTag();

		xml_g->closeTag();//AdmFeriadoVO

	}
	xml_g->closeTag();//AdmCalendarioContainerVO
	setStatusCode("14I0000","Operação concluída com sucesso!");
	ULOG_END("implFrdNomListar::Execute()");
}
