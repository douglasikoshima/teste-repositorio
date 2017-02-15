/*****************************************************************************
 *
 * Modulo:    USRLOGINID
 * Arquivo:   USRLOGINID.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDJMartins          Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define USRLOGINIDCPP

//Header de Usr de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CUsr.h"
//#include <CDoc.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(USRLOGINID);

/**************************************************************************
 *  Funcao de Negocios:  USRLOGINID
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
void implUSRLOGINID::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUSRLOGINID::Execute()");
	
	CSafePointer oSafePointer;
	CUsr oUsuario;
	//CDocumento oDocumento;
	
	char* cLogin = oSafePointer.getTag( dnode, "login", 0 );
	if( strlennull( cLogin ) <= 0 )
	{
		setStatusCode("08E0001","login esta nulo");
		ULOG_END("implUSRLOGINID::Execute()");
		return;
	}
	
	if( oUsuario.ListLogin( cLogin ) > 0 )
	{
		if( strcmp( oUsuario.Registro(0)->csiglaStatusAtual, "ATIVO" ) == 0 )
		{
			xml_g->createTag("UsuarioVO");
			xml_g->addProp(	"xmlns", "usuario.fo.vivo.com.br/vo" );
			xml_g->addProp(	"xmlns:ns1", "cliente.fo.vivo.com.br/vo" );

			xml_g->addItem( "idUsuario", oUsuario.Registro(0)->cidUsuario );
			xml_g->addItem( "nome", oUsuario.Registro(0)->cnome );
			xml_g->addItem( "sobrenome", oUsuario.Registro(0)->csobrenome );
			xml_g->addItem( "email", oUsuario.Registro(0)->cemail );
			xml_g->addItem( "idDDD", oUsuario.Registro(0)->cidDDD );
			xml_g->addItem( "dsDDD", oUsuario.Registro(0)->cdsDDD );
			xml_g->addItem( "dsTelefone", oUsuario.Registro(0)->cTelefone );
			xml_g->addItem( "login", oUsuario.Registro(0)->clogin );
			xml_g->addItem( "loginCti", oUsuario.Registro(0)->cloginCti );
			xml_g->addItem( "idStatusAtual", oUsuario.Registro(0)->cidStatusAtual );
			xml_g->addItem( "descricaoStatusAtual",oUsuario.Registro(0)->cdescricaoStatusAtual );
			xml_g->addItem( "idMotivo", oUsuario.Registro(0)->cidMotivo );
			xml_g->addItem( "dsMotivo", oUsuario.Registro(0)->cdsMotivo );
			xml_g->addItem( "dtInicio", oUsuario.Registro(0)->cdtInicio );
			xml_g->addItem( "dtRetorno", oUsuario.Registro(0)->cdtRetorno );
			xml_g->addItem( "idCargoAtual", oUsuario.Registro(0)->cidCargoAtual );
			xml_g->addItem( "descricaoCargoAtual",oUsuario.Registro(0)->cdescricaoCargoAtual );
			xml_g->addItem( "loginChefe", oUsuario.Registro(0)->cloginChefe );
			xml_g->addItem( "nomeChefe", oUsuario.Registro(0)->cnomeChefe );
			xml_g->addItem( "idUF", oUsuario.Registro(0)->cidUF );
			xml_g->addItem( "nmUF", oUsuario.Registro(0)->cnmUF );
			xml_g->addItem( "dtInclusao", oUsuario.Registro(0)->cdtInclusao );
			xml_g->addItem( "dtExclusao", oUsuario.Registro(0)->cdtExclusao );
			xml_g->addItem( "inConsultor", oUsuario.Registro(0)->cinConsultor );
			xml_g->addItem( "idPerfilConsultorAtdAtual", oUsuario.Registro(0)->cidPerfilConsultorAtd );
			xml_g->addItem( "idFornecedorConsultorAtdAtual", oUsuario.Registro(0)->cidFornecedorConsultorAtd );
			xml_g->addItem( "idSiteConsultorAtdAtual", oUsuario.Registro(0)->cidSiteConsultorAtd );
			xml_g->addItem( "dsFornecedorConsultorAtdAtual", oUsuario.Registro(0)->cdsFornecedorConsultorAtd );
			xml_g->addItem( "dsSiteConsultorAtdAtual", oUsuario.Registro(0)->cdsSiteConsultorAtd );

			xml_g->createTag("DocumentoSimpVO");
			xml_g->addProp(	"xmlns", "usuario.fo.vivo.com.br/vo" );
			xml_g->addItem( "idDocumento", 0 );
			xml_g->addItem( "dsDocumento", "" );
			xml_g->createTag("ns1:TipoDocumentoVO");
			xml_g->addItem( "ns1:idTipoDocumento", 0 );
			xml_g->addItem( "ns1:sgTipoDocumento", "" );
			xml_g->addItem( "ns1:dsTipoDocumento", "" );
			xml_g->closeTag(); //ns1:UFVO
			xml_g->createTag("ns1:UFVO");
			xml_g->addItem( "ns1:idUF", 0 );
			xml_g->addItem( "ns1:sgUF", "" );
			xml_g->addItem( "ns1:dsUF", "" );
			xml_g->closeTag(); //ns1:UFVO
			xml_g->createTag("ns1:PaisVO");
			xml_g->addItem( "ns1:idPais", 0 );
			xml_g->addItem( "ns1:sgPais", "" );
			xml_g->addItem( "ns1:dsPais", "" );
			xml_g->closeTag(); //ns1:PaisVO
			xml_g->closeTag();//DocumentoSimpVO

			xml_g->closeTag();//UsuarioVO
				
				
			setStatusCode("08I0000","Execucao com sucesso");
		}// if( strcmp( oUsuario.Registro(0)->csiglaStatusAtual, "ATIVO" ) == 0 )
		else
		{
			setStatusCode("08W0002", oUsuario.GetErro() );
		}// else if( strcmp( oUsuario.Registro(0)->csiglaStatusAtual, "ATIVO" ) == 0 )
	}
	else
	{
		setStatusCode("08W0001", oUsuario.GetErro() );
	}
	
	ULOG_END("implUSRLOGINID::Execute()");
}
