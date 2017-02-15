/*****************************************************************************
 *
 * Modulo:    UsrListaPar
 * Arquivo:   UsrListaPar.cpp
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
#define UsrListaParCPP

//Header de Usr de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CUsr.h"
#include "../../../negocio/acessoCmm/include/CSts.h"
#include "../../../negocio/acessoCmm/include/CCrg.h"
#include "../../../negocio/acessoCmm/include/CUFOpe.h"
#include "../../../negocio/acessoCmm/include/CDoc.h"
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

#include "../../../negocio/acessoCmm/include/CSiteConsultorAtd.h"
#include "../../../negocio/acessoCmm/include/CFornecedorConsultorAtd.h"
#include "../../../negocio/acessoCmm/include/CPerfilConsultorAtd.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(UsrListaPar);

/**************************************************************************
 *  Funcao de Negocios:  UsrListaPar
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
void implUsrListaPar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUsrListaPar::Execute()");
	
	CSafePointer oSafePointer;
	CSts oStatusUsuario;
	CCrg oCargoUsuario;

	CUsr oUsuario;
	CUFOperadora oUFOperadora;
	CDocumento oDocumento;

    CPerfilConsultorAtd oPerfilConsultorAtd;
    CFornecedorConsultorAtd oCFornecedorConsultorAtd;
    CSiteConsultorAtd oSiteConsultorAtd;

	char cnrDocumento[255+1];
	char* pzcnrDocumento = oSafePointer.getTag(dnode,"dsDocAtual",0);
	memset( cnrDocumento, 0, sizeof( cnrDocumento ) );
	if( strlennull( pzcnrDocumento ) > 0 )
	{
		oUsuario.RemoveEspecial( cnrDocumento, pzcnrDocumento );
	}

	char* cidUser = getUser();

	xml_g->createTag("ListaUsuariosVO");
	xml_g->addProp(	"xmlns", "usuario.fo.vivo.com.br/vo" );
	xml_g->addProp(	"xmlns:ns1", "cliente.fo.vivo.com.br/vo" );

    // Se receber esta tag irá retornar apenas uma lista de usuários superiores ao usuário informado.
	char* pzcidPerfilConsultorAtdAtual = oSafePointer.getTag(dnode,"idPerfilConsultorAtdAtual",0);
    char* pszLogin                     = oSafePointer.getTag(dnode,"login",0);

	if( pzcidPerfilConsultorAtdAtual == 0 || *pzcidPerfilConsultorAtdAtual == 0 )
    {
	    oUsuario.ListPar( oSafePointer.getTag(dnode,"nome",0)
					     ,oSafePointer.getTag(dnode,"nomeMeio",0)
					     ,oSafePointer.getTag(dnode,"sobrenome",0)
					     ,oSafePointer.getTag(dnode,"idUFOperadora",0)
					     ,oSafePointer.getTag(dnode,"login",0)
					     ,oSafePointer.getTag(dnode,"loginCti",0)
					     ,oSafePointer.getTag(dnode,"idStatusAtual",0)
					     ,oSafePointer.getTag(dnode,"idCargoAtual",0)
					     ,oSafePointer.getTag(dnode,"loginChefe",0)
					     ,oSafePointer.getTag(dnode,"sgTipoDocumento",0)
					     ,cnrDocumento
					     ,oSafePointer.getTag(dnode,"inConsultor",0)
					     ,oSafePointer.getTag(dnode,"paginaAtual",0)
					     ,oSafePointer.getTag(dnode,"registrosPPagina",0)
					     ,oSafePointer.getTag(dnode,"idNivel",0)
					     ,oSafePointer.getTag(dnode,"idCargo",0)
					     ,oSafePointer.getTag(dnode,"idOrganizacao",0)
					     ,oSafePointer.getTag(dnode,"idDepartamento",0)
					    );

	    xml_g->addItem( "paginaAtual", oUsuario.TemProximaPagina() );
		    xml_g->addItem( "registrosPPagina", oUsuario.TamanhoPagina() );

		for( int x = 0; x<oUsuario.Quantidade(); x++ )
		{
			xml_g->createTag("UsuarioVO");

			xml_g->addItem( "idUsuario", oUsuario.Registro(x)->cidUsuario );
			xml_g->addItem( "nome", oUsuario.Registro(x)->cnome );
			xml_g->addItem( "email", oUsuario.Registro(x)->cemail );
			xml_g->addItem( "idDDD", oUsuario.Registro(x)->cidDDD );
			xml_g->addItem( "dsDDD", oUsuario.Registro(x)->cdsDDD );
			xml_g->addItem( "dsTelefone", oUsuario.Registro(x)->cTelefone );
			xml_g->addItem( "login", oUsuario.Registro(x)->clogin );
			xml_g->addItem( "loginCti", oUsuario.Registro(x)->cloginCti );
			xml_g->addItem( "idStatusAtual", oUsuario.Registro(x)->cidStatusAtual );
			xml_g->addItem( "descricaoStatusAtual",oUsuario.Registro(x)->cdescricaoStatusAtual );
			xml_g->addItem( "idMotivo", oUsuario.Registro(x)->cidMotivo );
			xml_g->addItem( "dsMotivo", oUsuario.Registro(x)->cdsMotivo );
			xml_g->addItem( "dtInicio", oUsuario.Registro(x)->cdtInicio );
			xml_g->addItem( "dtRetorno", oUsuario.Registro(x)->cdtRetorno );
			xml_g->addItem( "idCargoAtual", oUsuario.Registro(x)->cidCargoAtual );
			xml_g->addItem( "descricaoCargoAtual",oUsuario.Registro(x)->cdescricaoCargoAtual );
			xml_g->addItem( "loginChefe", oUsuario.Registro(x)->cloginChefe );
			xml_g->addItem( "nomeChefe", oUsuario.Registro(x)->cnomeChefe );
			xml_g->addItem( "idUF", oUsuario.Registro(x)->cidUF );
			xml_g->addItem( "nmUF", oUsuario.Registro(x)->cnmUF );
			xml_g->addItem( "dtInclusao", oUsuario.Registro(x)->cdtInclusao );
			xml_g->addItem( "dtExclusao", oUsuario.Registro(x)->cdtExclusao );
			xml_g->addItem( "inConsultor", oUsuario.Registro(x)->cinConsultor );
			xml_g->addItem( "idPerfilConsultorAtdAtual", oUsuario.Registro(x)->cidPerfilConsultorAtd );
			xml_g->addItem( "idFornecedorConsultorAtdAtual", oUsuario.Registro(x)->cidFornecedorConsultorAtd );
			xml_g->addItem( "idSiteConsultorAtdAtual", oUsuario.Registro(x)->cidSiteConsultorAtd );
			xml_g->addItem( "dsLoginRoteamento", oUsuario.Registro(x)->cdsLoginRoteamento );

            //xml_g->addItem( "idUFOperadora", oUsuario.Registro(x)->cidUFOperadora );

			oUFOperadora.ListId( oUsuario.Registro(x)->cidUFOperadora );
			if( oUFOperadora.Quantidade() > 0 )
			{
				for( int y=0; y<oUFOperadora.Quantidade(); y++ )
				{
					xml_g->createTag("UFOperadoraPessoaVO");
					xml_g->addItem( "idUFOperadora", oUFOperadora.Registro(x)->cidUFOperadora );
					xml_g->addItem( "dsUFOperadora", oUFOperadora.Registro(x)->cdsUFOperadora );
					xml_g->createTag("ns1:UFVO");
					xml_g->addItem( "ns1:idUF", oUFOperadora.Registro(x)->cidUF );
					xml_g->addItem( "ns1:sgUF", oUFOperadora.Registro(x)->csgUF );
					xml_g->addItem( "ns1:nmUF", oUFOperadora.Registro(x)->cnmUF );
					xml_g->closeTag(); //ns1:UFVO
					xml_g->closeTag();//UFOperadoraPessoaVO
				}
			}

			oDocumento.ListIdUser( oUsuario.Registro(x)->cidUsuario );
			if( oDocumento.Quantidade() > 0 )
			{
				for( int z=0; z<oDocumento.Quantidade(); z++ )
				{
					xml_g->createTag("DocumentoSimpVO");
					xml_g->addProp(	"xmlns", "usuario.fo.vivo.com.br/vo" );
					xml_g->addItem( "idDocumento", oDocumento.Registro(z)->cidDocumento );
					xml_g->addItem( "dsDocumento", oDocumento.Registro(z)->cnrDocumento );
					xml_g->createTag("ns1:TipoDocumentoVO");
					xml_g->addItem( "ns1:idTipoDocumento", oDocumento.Registro(z)->cidTipoDocumento );
					xml_g->addItem( "ns1:sgTipoDocumento", oDocumento.Registro(z)->csgTipoDocumento );
					xml_g->addItem( "ns1:dsTipoDocumento", oDocumento.Registro(z)->cdsTipoDocumento );
					xml_g->closeTag(); //ns1:UFVO
					xml_g->createTag("ns1:UFVO");
					xml_g->addItem( "ns1:idUF", oDocumento.Registro(z)->cidUF );
					xml_g->addItem( "ns1:sgUF", oDocumento.Registro(z)->csgUF );
					xml_g->addItem( "ns1:nmUF", oDocumento.Registro(z)->cnmUF );
					xml_g->closeTag(); //ns1:UFVO
					xml_g->createTag("ns1:PaisVO");
					xml_g->addItem( "ns1:idPais", oDocumento.Registro(z)->cidPais );
					xml_g->addItem( "ns1:sgPais", oDocumento.Registro(z)->csgPais );
					xml_g->addItem( "ns1:nmPais", oDocumento.Registro(z)->cnmPais );
					xml_g->closeTag(); //ns1:PaisVO
					xml_g->closeTag();//UFOperadoraPessoaVO
				}
			}
			xml_g->closeTag();//UsuarioVO

		}//for( int x = 0; x<oUsuario.Quantidade(); x++ )

		oStatusUsuario.ListAll();
		oStatusUsuario.GetXml( "StatusUsuarioVO", xml_g );

        oPerfilConsultorAtd.ListAll();
        oPerfilConsultorAtd.GetXml( "PerfilConsultorAtdVO", xml_g );

        oCFornecedorConsultorAtd.ListAll();
        oCFornecedorConsultorAtd.GetXml( "FornecedorConsultorAtdVO", xml_g );

        oSiteConsultorAtd.ListAll();
        oSiteConsultorAtd.GetXml( "SiteConsultorAtdVO", xml_g );

		//oCargoUsuario.ListAll();
		//oCargoUsuario.GetXml( "CargoUsuarioVO", xml_g );

    }
    else
    { // Funcionalidade secundária deste serviço: retorna lista de usuários superiores ao 
      // perfil que é informado.
	    oUsuario.ListUsuariosSuperiores(cidUser
                                       ,pzcidPerfilConsultorAtdAtual
                                       ,pszLogin
                                       ,oSafePointer.getTag(dnode,"paginaAtual",0)
					                   ,oSafePointer.getTag(dnode,"registrosPPagina",0) );

        xml_g->addItem( "paginaAtual", oUsuario.TemProximaPagina() );
	        xml_g->addItem( "registrosPPagina", oUsuario.TamanhoPagina() );

		for( int x = 0; x<oUsuario.Quantidade(); x++ )
		{
			xml_g->createTag("UsuarioVO");

			    xml_g->addItem( "idUsuario", oUsuario.Registro(x)->cidUsuario );
			    xml_g->addItem( "nome", oUsuario.Registro(x)->cnome );
			    xml_g->addItem( "nomeMeio", oUsuario.Registro(x)->cnomeChefe );
			    xml_g->addItem( "sobrenome", oUsuario.Registro(x)->csobrenome );
			    xml_g->addItem( "login", oUsuario.Registro(x)->clogin );
			    //xml_g->addItem( "idPerfilConsultorAtdAtual", oUsuario.Registro(x)->cidPerfilConsultorAtd );
			    //xml_g->addItem( "idFornecedorConsultorAtdAtual", oUsuario.Registro(x)->cidFornecedorConsultorAtd );
			    //xml_g->addItem( "idSiteConsultorAtdAtual", oUsuario.Registro(x)->cidSiteConsultorAtd );

			xml_g->closeTag();//UsuarioVO

		}//for( int x = 0; x<oUsuario.Quantidade(); x++ )
	}

	xml_g->closeTag();//ListaUsuariosVO

	setStatusCode("08I0000","Execucao com sucesso");
	ULOG_END("implUsrListaPar::Execute()");
}
