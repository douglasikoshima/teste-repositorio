/*****************************************************************************
 *
 * Modulo:    UsrLista
 * Arquivo:   UsrLista.cpp
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
#define UsrListaCPP

//Header de Usr de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSts.h"
#include "../../../negocio/acessoCmm/include/CCrg.h"
#include "../../../negocio/acessoCmm/include/CPais.h"
#include "../../../negocio/acessoCmm/include/CTipDoc.h"
#include "../../../negocio/acessoCmm/include/CUF.h"
#include "../../../negocio/acessoCmm/include/CAreReg.h"
#include "../../../negocio/acessoCmm/include/CUFOpe.h"
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(UsrLista);

/**************************************************************************
 *  Funcao de Negocios:  UsrLista
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
void implUsrLista::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUsrLista::Execute()");
	
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CSts oStatus;
	CCrg oCargo;
	CPais oPais;
	CUF oUF;
	//CTipDoc oTipDoc;
	CAreReg oAreaRegistro;
	CUFOperadora oUFOperadora;
	CTipDoc oTipoDocumento;
	int iCont;
	char *pszOpcao=NULL;

	pszOpcao=oSafePointer.getTag(dnode,"UF",0);

	xml_g->createTag("UsuariosVO");
	xml_g->addProp(	"xmlns", "usuario.fo.vivo.com.br/vo" );
	xml_g->addProp(	"xmlns:ns1", "cliente.fo.vivo.com.br/vo" );

/**
  * Este servico agora tem uma opcao de trazer para o xml de saida apenas
  * os dados da UFOperadora, caso o xml de entrada tiver a tag "UF", com 
  * algum valor preenchido apenas a UF operadora sera retornado, caso con-
  * trario a funcionalidade deste servico continua a mesma.
  */

	if(pszOpcao==NULL)
	{
		oStatus.ListAll();
		for( iCont=0; iCont<oStatus.Quantidade(); iCont++)
		{
			xml_g->createTag("StatusUsuarioVO");
			xml_g->addItem( "idStatus", oStatus.Registro(iCont)->cidStatusUsuario );
			xml_g->addItem( "sgStatus", oStatus.Registro(iCont)->csgStatusUsuario );
			xml_g->addItem( "nmStatus", oStatus.Registro(iCont)->cdsStatusUsuario );
			xml_g->closeTag();
		}
	
	//oCargo.ListAll();
	//for( iCont=0; iCont<oCargo.Quantidade(); iCont++)
	//{
	//	xml_g->createTag("CargoUsuarioVO");
	//	xml_g->addItem( "idCargo", oCargo.Registro(iCont)->cidCargo );
	//	xml_g->addItem( "nmCargo", oCargo.Registro(iCont)->cnmCargo );
	//	xml_g->closeTag();
	//}
	
		oPais.ListAll();
		for( iCont=0; iCont<oPais.Quantidade(); iCont++)
		{
			xml_g->createTag("ns1:PaisVO");
			xml_g->addItem( "ns1:idPais", oPais.Registro(iCont)->cidPais );
			xml_g->addItem( "ns1:sgPais", oPais.Registro(iCont)->csgPais );
			xml_g->addItem( "ns1:nmPais", oPais.Registro(iCont)->cdsPais );
			xml_g->addItem( "ns1:dsNacionalidade", oPais.Registro(iCont)->cdsNacionalidade );
			xml_g->closeTag();
		}

		oUF.ListAll();
		for( iCont=0; iCont<oUF.Quantidade(); iCont++)
		{
			xml_g->createTag("ns1:UFVO");
			xml_g->addItem( "ns1:idUF", oUF.Registro(iCont)->cidUF );
			xml_g->addItem( "ns1:sgUF", oUF.Registro(iCont)->csgUF );
			xml_g->addItem( "ns1:nmUF", oUF.Registro(iCont)->cnmUF );
			xml_g->closeTag();
		}

		oTipoDocumento.ListFiltrado();
		for( iCont=0; iCont<oTipoDocumento.Quantidade(); iCont++)
		{
			xml_g->createTag("ns1:TipoDocumentoVO");
			xml_g->addItem( "ns1:idTipoDocumento", oTipoDocumento.Registro(iCont)->cidTipoDocumento );
			xml_g->addItem( "ns1:sgTipoDocumento", oTipoDocumento.Registro(iCont)->csgTipoDocumento );
			xml_g->addItem( "ns1:dsTipoDocumento", oTipoDocumento.Registro(iCont)->cdsTipoDocumento );
			xml_g->closeTag();
		}
	
		oAreaRegistro.ListAll();
		for( iCont=0; iCont<oAreaRegistro.Quantidade(); iCont++)
		{
			xml_g->createTag("DDDVO");
			xml_g->addItem( "idDDD", oAreaRegistro.Registro(iCont)->cidAreaRegistro );
			xml_g->addItem( "dsDDD", oAreaRegistro.Registro(iCont)->ccdAreaRegistro );
			xml_g->closeTag();
		}
	}
	oUFOperadora.ListAll();
	for( iCont=0; iCont<oUFOperadora.Quantidade(); iCont++)
	{
		char cRegional[350+1];
		memset( cRegional, 0, sizeof( cRegional ) );
		sprintf( cRegional, "%s - %s", oUFOperadora.Registro(iCont)->csgUF, 
			oUFOperadora.Registro(iCont)->cdsUFOperadora );
		xml_g->createTag("UFOperadoraUsuarioVO");
		xml_g->addItem( "idUFOperadora", oUFOperadora.Registro(iCont)->cidUFOperadora );
		xml_g->addItem( "dsUFOperadora", cRegional );
		xml_g->closeTag();
	}
	/*
	oMotivo.ListAll();
	for( iCont=0; iCont<oMotivo.Quantidade(); iCont++)
	{
		xml_g->createTag("UFOperadoraUsuarioVO");
		xml_g->addProp(	"xmlns", "usuario.fo.vivo.com.br/vo" );
		xml_g->addItem( "idMotivo", oMotivo.Registro(iCont)->cidMotivo );
		xml_g->addItem( "dsMotivo", oMotivo.Registro(iCont)->cdsMotivo );
		xml_g->closeTag();	
	}
	*/
	xml_g->closeTag();// xml_g->createTag("UsuariosVO");
	setStatusCode("08I0000","Execucao com sucesso");
	
	ULOG_END("implUsrLista::Execute()");
}
