/*****************************************************************************
 *
 * Modulo:    CttFrmRelacao
 * Arquivo:   CttFrmRelacao.cpp
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

#define CttFrmRelacaoCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFlhCmp.h"
#include "../../../negocio/admatdCmm/include/CLin.h"
#include "../../../negocio/admatdCmm/include/CUfo.h"
#ifdef WIN32
  #include <CCmp.h>
#else
  #include "../../negocio/admatdCmm/include/CCmp.h"
#endif

#include "../../../negocio/admatdCmm/include/CCmpCls.h"
#include "../../../negocio/admatdCmm/include/CCmpFse.h"

#include <list>
using namespace std;

typedef list<unsigned long> ListaUF;	
typedef list<unsigned long> ListaTipoLinha;
typedef list<unsigned long> ListCampos;
typedef list<unsigned long> ListGrupos;

ListaUF pUF;
ListaTipoLinha pTipoLinha;
ListCampos pCampos;	
ListGrupos pGrupos;



/*    Prototipos   */
void GetFases( DOMNode *dnode,XMLGen *xml_g );
void GetCampos( DOMNode *dnode,XMLGen *xml_g );
void GetDados( DOMNode *dnode,XMLGen* xml_g );
void GetCombos( DOMNode *dnode,XMLGen *xml_g );
void GetGrupoCampos( DOMNode * dnode,XMLGen * xml_g );
void ListaCampos( DOMNode * dnode,XMLGen * xml_g );
void InsereAgrupamento( char * cidUsuario,DOMNode * dnode,XMLGen * xml_g );
void CamposSelecionados( DOMNode * dnode,XMLGen * xml_g );
void AtualizaCampos( char * cidUsuario,DOMNode * dnode,XMLGen * xml_g );
void GrupoCamposDependentes( XMLGen * xml_g );
void CamposValores( DOMNode * dnode,XMLGen * xml_g );


DECLARE_TUXEDO_SERVICE(CttFrmRelacao);

/**************************************************************************
 *  Funcao de Negocios:  CttFrmRelacao
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
void implCttFrmRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttFrmRelacao::Execute()");

	CSafePointer oSafePointer;
	CCampoClassificador oCampoClassificador;

	int iListaInicial = 0;
	int iOperacao;

	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char* cidClassificadorCampoAtual = oSafePointer.getTag( dnode, "idClassificadorCampoAtual", 0 );
	char* cidFaseProcessoAtual = oSafePointer.getTag( dnode, "idFaseProcessoAtual", 0 );
	char* cidOperacao = oSafePointer.getTag( dnode, "inOperacao", 0 );
	char* cinCamposComponentes = oSafePointer.getTag( dnode, "inCamposComponentes", 0 );
	char* cidUsuario = getUser();
	ULOG( "Usuario [%s]",cidUsuario );
	
     
    try
    {
	if (!strcmp(cidOperacao,"GETFASES") )   iOperacao = 0;
	if (!strcmp(cidOperacao,"GETDADOS") )   iOperacao = 1;
	if (!strcmp(cidOperacao,"GETCAMPOS") )  iOperacao = 2;
	if (!strcmp(cidOperacao,"UPDATECOMBOS") ) iOperacao = 3;
	if (!strcmp(cidOperacao,"GETAGRUPARCAMPOS")) iOperacao = 4;
	if (!strcmp(cidOperacao,"AGRUCAMGETCAMPOSDISP")) iOperacao = 5;
	if (!strcmp(cidOperacao,"SETAGRUPARCAMPOS")) iOperacao = 6;
	if (!strcmp(cidOperacao,"AGRUCAMGETCAMPOSSEL")) iOperacao = 7;
	if (!strcmp(cidOperacao,"GRAVAR")) iOperacao = 8;

	ULOG( "iOperacao [%d]",iOperacao );
    switch(iOperacao)
	{
		case  0 : GetFases( dnode,xml_g );
			      break;

		case  1 : GetDados( dnode,xml_g );
			      break;

		case  2 : GetCampos( dnode,xml_g );
			      break;
        
		case  3 : GetCombos( dnode,xml_g );
			      break;

		case  4 : GetGrupoCampos( dnode,xml_g );
			      break;

		case  5 : ListaCampos( dnode,xml_g );
			      break;
	    
		case  6 : ULOG( "Parametro Usuario [%s]",cidUsuario );
			      InsereAgrupamento( cidUsuario,dnode,xml_g );
			      break;

		case  7 : CamposSelecionados( dnode,xml_g );
			      break;

		case  8 : AtualizaCampos( cidUsuario,dnode,xml_g );
			      break;

		case  9 : GrupoCamposDependentes( xml_g );
			      break;

		case 10 : CamposValores( dnode,xml_g );
			      break;
	}

	setStatusCode("14I0000","Operação concluída com sucesso!");
	ULOG_END("implCttFrmRelacao::Execute()");
    
    }
    catch( char  * MsgErro )
    {
		setStatusCode("14E0099",MsgErro);
		ULOG_END("implCttFrmRelacao::Execute()");
    }
}



void GetFases( DOMNode *dnode,XMLGen *xml_g )
{
	CSafePointer oSafePointer;
	CFaseProcesso oFaseProcesso;

	char *cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char *cidFaseProcessoAtual = oSafePointer.getTag( dnode, "idFaseProcessoAtual", 0 );

	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	xml_g->addItem("idContato", cidContato );
	xml_g->addItem("idFaseProcessoAtual", cidFaseProcessoAtual );

		xml_g->createTag( "AdmFaseProcessosVO" );
		oFaseProcesso.ListaFases( xml_g );
		xml_g->closeTag();

	xml_g->closeTag();

}



void GetDados( DOMNode *dnode,XMLGen *xml_g )
{
	ListaUF pUF_;
	ListaTipoLinha pTipoLinha_;

	CSafePointer oSafePointer;
	CTipoLinha oTipoLinha;
	CUFOperadora oUFOperadora;
	CCampoClassificador oCampoClassificador;
	CCampo oCampo;
static	char sIdContato[256];
	memset(sIdContato,0x0,sizeof(sIdContato)); 

	
	
	char *cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	strcpy(sIdContato,cidContato);
	ULOG( "idContato [%s]",sIdContato );
	char *cidFaseProcessoAtual = oSafePointer.getTag( dnode, "idFaseProcessoAtual", 0 );
	char *cinCamposComponentes = oSafePointer.getTag( dnode, "inCamposComponentes", 0 );
	
	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	xml_g->addItem("idContato", cidContato );
	xml_g->addItem("idFaseProcessoAtual", cidFaseProcessoAtual );

		xml_g->createTag( "AdmClassificadorCamposVO" );
			if ( !strcmp(cinCamposComponentes,"CAMPOS") )  
			{
				oCampoClassificador.ListAll();
				oCampoClassificador.GetXml( "AdmClassificadorCampoVO", xml_g );
		    }
		    else
		    {
				oCampo.ListaComponentes(xml_g);
		    }
		xml_g->closeTag();


		pUF_.clear();
		pTipoLinha_.clear();
		xml_g->createTag( "selecionados" );
			oUFOperadora.Relacionadas( cidContato, cidFaseProcessoAtual );
			if( oUFOperadora.Quantidade() > 0 )
			{
				for( int x = 0; x < oUFOperadora.Quantidade(); x++ )
				{
					if( oUFOperadora.Registro( x ) != NULL )
					{
						ULOG( "Inserindo Operadora [%s]",(char*)&oUFOperadora.Registro(x)->cidUFOperadora ); 
			           pUF_.push_back(strtoul((char *)&oUFOperadora.Registro(x)->cidUFOperadora,0,10));
			        }
			    }
			}
			oUFOperadora.GetXmlFrm( "ns1:UFOperadoraUsuarioVO", xml_g );
			oTipoLinha.Relacionadas( cidContato, cidFaseProcessoAtual );
			if( oTipoLinha.Quantidade() > 0 )
			{
				for( int x = 0; x < oTipoLinha.Quantidade(); x++ )
				{
					if( oTipoLinha.Registro( x ) != NULL )
					{ 
		               pTipoLinha_.push_back(strtoul((char *)&oTipoLinha.Registro(x)->cidTipoLinha,0,10));
		            }
		        }
		    }
			oTipoLinha.GetXml( "AdmTipoLinhaVO", xml_g );
              
              
            if ( oUFOperadora.Quantidade() > 0 || oTipoLinha.Quantidade() > 0 )
            {
			   CContatoFolhaCampo oContatoFolhaCampo( dnode,&pUF_,&pTipoLinha_ );
		   	   oContatoFolhaCampo.Selecao( xml_g );
		    }
		    else 
				xml_g->closeTag();
				

	ULOG( ">>>> idContato [%s]",sIdContato );
	
		pUF_.clear();
		pTipoLinha_.clear();
		xml_g->createTag( "disponiveis" );
				oUFOperadora.NaoRelacionadas( sIdContato, cidFaseProcessoAtual );
				oUFOperadora.GetXmlFrm( "ns1:UFOperadoraUsuarioVO", xml_g );
				oTipoLinha.NaoRelacionadas( sIdContato, cidFaseProcessoAtual );
				oTipoLinha.GetXml( "AdmTipoLinhaVO", xml_g );
		xml_g->closeTag();


	xml_g->closeTag();

}



void GetCampos( DOMNode *dnode,XMLGen *xml_g )
{
	ULOG_START("GetCampos()");
	               
	ListaUF pUF_;
	ListaTipoLinha pTipoLinha_;

	CSafePointer oSafePointer;
	CTipoLinha oTipoLinha;
	CUFOperadora oUFOperadora;
	
	char *cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
static char idContato[256];
    strcpy(idContato,cidContato);
    
	char *cidFaseProcessoAtual = oSafePointer.getTag( dnode, "idFaseProcessoAtual", 0 );
static char idFase[256];
    strcpy(idFase,cidFaseProcessoAtual);	
	char *cinCamposComponentes = oSafePointer.getTag( dnode, "inCamposComponentes", 0 );
	ULOG("cidContato [%s]- cidFaseProcessoAtual [%s]",cidContato,cidFaseProcessoAtual);
	oUFOperadora.Relacionadas( idContato, idFase );
	if( oUFOperadora.Quantidade() > 0 )
	{
		for( int x = 0; x < oUFOperadora.Quantidade(); x++ )
		{
			if( oUFOperadora.Registro( x ) != NULL )
			{ 
	           pUF_.push_back(strtoul((char *)&oUFOperadora.Registro(x)->cidUFOperadora,0,10));
	        }
	    }
	}
	oTipoLinha.Relacionadas( idContato, idFase );
	if( oTipoLinha.Quantidade() > 0 )
	{
		for( int x = 0; x < oTipoLinha.Quantidade(); x++ )
		{
			if( oTipoLinha.Registro( x ) != NULL )
			{ 
               pTipoLinha_.push_back(strtoul((char *)&oTipoLinha.Registro(x)->cidTipoLinha,0,10));
            }
        }
    }

	CCampo oCampo( dnode,&pUF_,&pTipoLinha_ );

	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	xml_g->addItem("idContato", cidContato );
	xml_g->addItem("idFaseProcessoAtual", cidFaseProcessoAtual );

		if ( !strcmp(cinCamposComponentes,"CAMPOS") )  
		{
	        oCampo.RelacionadosClassif( xml_g );
		}
		else
		{
			oCampo.ListaGrupoComponentes( xml_g );
		}

	xml_g->closeTag();

	ULOG_END("GetCampos()");
	
}



void GetCombos( DOMNode *dnode,XMLGen *xml_g )
{
	CSafePointer oSafePointer;
	CCampoClassificador oCampoClassificador;
	CCampo oCampo;
	
	
	char *cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char *cidFaseProcessoAtual = oSafePointer.getTag( dnode, "idFaseProcessoAtual", 0 );
	char *cinCamposComponentes = oSafePointer.getTag( dnode, "inCamposComponentes", 0 );
	
	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	xml_g->addItem("idContato", cidContato );
	xml_g->addItem("idFaseProcessoAtual", cidFaseProcessoAtual );

		xml_g->createTag( "AdmClassificadorCamposVO" );
		if ( !strcmp(cinCamposComponentes,"CAMPOS") )  
		{
			oCampoClassificador.ListAll();
			oCampoClassificador.GetXml( "AdmClassificadorCampoVO", xml_g );
	    }
	    else
	    {
			oCampo.ListaComponentes(xml_g);
	    }
		xml_g->closeTag();

	xml_g->closeTag();

}



void GetGrupoCampos( DOMNode * dnode,XMLGen * xml_g )
{
	CSafePointer oSafePointer;
	CCampoClassificador oCampoClassificador;
	CCampo oCampo;
	
	
	char *cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char *cidFaseProcessoAtual = oSafePointer.getTag( dnode, "idFaseProcessoAtual", 0 );
	
	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	xml_g->addItem("idContato", cidContato );
	xml_g->addItem("idFaseProcessoAtual", cidFaseProcessoAtual );

		xml_g->createTag( "AdmClassificadorCamposVO" );
			oCampoClassificador.ListAll();
			oCampoClassificador.GetXml( "AdmClassificadorCampoVO", xml_g );
		xml_g->closeTag();
		
		
		oCampo.ListaGrupoCampos( xml_g );
        
        
	xml_g->closeTag();

}



void ListaCampos( DOMNode * dnode,XMLGen * xml_g )
{
	CSafePointer oSafePointer;
	CCampoClassificador oCampoClassificador;
	CCampo oCampo( dnode );
	
	
	char *cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char *cidFaseProcessoAtual = oSafePointer.getTag( dnode, "idFaseProcessoAtual", 0 );
	char *cinCamposComponentes = oSafePointer.getTag( dnode, "inCamposComponentes", 0 );
	unsigned long idClassificadorPrm = strtoul((char *)oSafePointer.getTag(dnode,"idClassificadorCampoAtual",0),0,10);
	
	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	xml_g->addItem("idContato", cidContato );

		oCampo.CamposNaoRelac( xml_g );
        
	xml_g->closeTag();

}



void CamposSelecionados( DOMNode * dnode,XMLGen * xml_g )
{
	CSafePointer oSafePointer;
	CCampoClassificador oCampoClassificador;
	CCampo oCampo( dnode );
	
	
	char *cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char *cidFaseProcessoAtual = oSafePointer.getTag( dnode, "idFaseProcessoAtual", 0 );
	char *cinCamposComponentes = oSafePointer.getTag( dnode, "inCamposComponentes", 0 );
	unsigned long idClassificadorPrm = strtoul((char *)oSafePointer.getTag(dnode,"idClassificadorCampoAtual",0),0,10);
	
	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	xml_g->addItem("idContato", cidContato );

	oCampo.MostraCampos( xml_g );
        
        
	xml_g->closeTag();

}



void InsereAgrupamento( char * cidUsuario,DOMNode *dnode,XMLGen *xml_g )
{
	CCampo oCampo( dnode );
	oCampo.idUsuarioPrm = strtoul(cidUsuario,0,10);
	ULOG( "idUsuario [%lu]",oCampo.idUsuarioPrm );
	
	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	oCampo.GravaAgrupamento( xml_g );
	xml_g->closeTag();
}



void AtualizaCampos( char * cidUsuario,DOMNode * dnode,XMLGen * xml_g )
{
	CSafePointer oSafePointer;
	char * inCampo;
	unsigned long id;
	int i;
	
	pTipoLinha.clear();
	pUF.clear();
	pCampos.clear();
	pGrupos.clear();
	for ( i=0;;i++ )
	{
		id = strtoul((char *)oSafePointer.getTag( dnode, "idTipoLinha", i ),0,10);
		if ( !id ) break;
		ULOG( "Achou idTipoLinha [%d]",id );
		pTipoLinha.push_back( id );
	}                            
	pTipoLinha.unique();
	pTipoLinha.sort();
	for ( i=0;;i++ )
	{
		id = strtoul((char *)oSafePointer.getTag( dnode, "vo1:idUFOperadora", i ),0,10);
		if ( !id ) break;
		ULOG( "Achou idUFOperadora [%d]",id );
		pUF.push_back( id );
	}                            
	pUF.unique();
	pUF.sort();
	for ( i=0;;i++ )
	{
		id = strtoul((char *)oSafePointer.getTag( dnode, "idCampoObjeto", i ),0,10);
		if ( !id ) break;
		inCampo = oSafePointer.getTag( dnode, "inClassificadorComponente", i );

		if ( !strcmp(inCampo,"Campo") )
		   pCampos.push_back( id ); 
		else
		   pGrupos.push_back( id );
	}                            
	
	CCampo oCampo( dnode,&pUF,&pTipoLinha,&pCampos,&pGrupos );
    oCampo.idUsuarioPrm = strtoul(cidUsuario,0,10);
    
	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	oCampo.AtualizaCampos( xml_g );
	xml_g->closeTag();
}




void GrupoCamposDependentes( XMLGen * xml_g )
{
	CCampo oCampo;	
	
	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	oCampo.GrupoCamposDependentes( xml_g );
	xml_g->closeTag();
}



void CamposValores( DOMNode * dnode,XMLGen * xml_g )
{
	CSafePointer oSafePointer;
	CCampo oCampo( dnode );	
	
	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	oCampo.CamposValores( xml_g );
	xml_g->closeTag();
}


/*
void InsereValor( dnode,xml_g )
{
	CSafePointer oSafePointer;
	CCampo oCampo( dnode );	
	
	xml_g->createTag( "AdmCamposFormularioVO" );
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
	oCampo.InsereValor( xml_g );
	xml_g->closeTag();
}}
*/
