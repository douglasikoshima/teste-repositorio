/*****************************************************************************
 * Arquivo:    GRPSKLGET.cpp
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 07/10/2005  C_edmartins           Criacao
 * 30/01/2006  c_mfcsoares           inclusão da paginação para a consulta de usuarios de um grupo
 *
 ****************************************************************************/

//Definicao Global
#define GRPSKLGETCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"
#include "../../../negocio/acessoCmm/include/CContatoFolhaUsuario.h"
#include "../../../negocio/acessoCmm/include/CGrp.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GRPSKLGET);

void implGRPSKLGET::Execute(DOMNode*dnXmlIn,XMLGen*XmlSaida)
{
	ULOG_START("implGRPSKLGET::Execute()");
	int iCont;

	CSafePointer oSafePointer;
	CContatoFolhaUsuario* pzoContatoFolhaUsuario;
	CGrp* pzoGrp;
	char* pzclogin        = oSafePointer.getTag( dnXmlIn, "login" );
	char* pzcinOperacao   = oSafePointer.getTag( dnXmlIn, "inOperacao" );
	char* pzcidGrupo      = oSafePointer.getTag( dnXmlIn, "grupoSelecionado" );
	char* pzcidGrupoSkill = oSafePointer.getTag( dnXmlIn, "skillSelecionado" );
	int   inOperacao = atoi(pzcinOperacao);

	// Tags relativas a consulta com paginação
	char* pzcPaginaAtual = oSafePointer.getTag( dnXmlIn, "paginaAtual" );			// Esta tag não é obrigatória
	char* pzcregistrosPPagina = oSafePointer.getTag( dnXmlIn, "registrosPPagina" ); // Esta tag não é obrigatória

	// converte as informacoes de pagina e numero de registros
	int iPaginaAtual = 0;		if( pzcPaginaAtual ) { iPaginaAtual = atoi(pzcPaginaAtual); } 
	int iRegistrosPPagina = 0;	if( pzcregistrosPPagina ) { iRegistrosPPagina = atoi(pzcregistrosPPagina); } 

    ULOG("implGRPSKLGET::Execute: pzcPaginaAtual=[%s]", pzcPaginaAtual);
    ULOG("implGRPSKLGET::Execute: pzcregistrosPPagina=[%s]", pzcregistrosPPagina);
	
	XmlSaida->createTag( "AdmSkillUsuarioVO" );
	XmlSaida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );

	//
	// Quando a consulta for por página, realizamos este desvio
	//
	if( iRegistrosPPagina > 0 ) 
	{
        pzoGrp = new CGrp();

        if(inOperacao == 2) //lista usuarios disponiveis do skill
        {
            if( strlennull( pzclogin ) <= 0 )
            {
                ULOG("Iniciando operacao 2 para listagem de usuarios disponiveis.");
                ULOG("pzcregistrosPPagina = %s ", pzcregistrosPPagina);
                ULOG(">>> CGrp::GrpListaUsuariosNaoAssociadosSkill");
                pzoGrp->GrpListaUsuariosNaoAssociadosSkill( pzcidGrupoSkill, iPaginaAtual, iRegistrosPPagina );
                ULOG("<<< CGrp::GrpListaUsuariosNaoAssociadosSkill");
                for( iCont = 0; iCont < pzoGrp->Quantidade(); iCont++ )
                {
                    XmlSaida->createTag( "UsuarioExistenteVO" );
                    XmlSaida->addItem( "idUsuario", pzoGrp->Registro(iCont)->cidGrupo );
                    XmlSaida->addItem( "nmUsuario", pzoGrp->Registro(iCont)->cnmGrupo );
                    XmlSaida->closeTag();//AdmGrupoVO
                }
            }
            else
            {
                ULOG("Iniciando operacao 2 para listagem de usuarios disponiveis, por login.");
                ULOG("pzcregistrosPPagina = %s ", pzcregistrosPPagina);
                ULOG(">>> CGrp::GrpListaUsuariosNaoAssociadosSkillPorLogin");
                pzoGrp->GrpListaUsuariosNaoAssociadosSkillPorLogin( pzcidGrupoSkill, pzclogin, iPaginaAtual, iRegistrosPPagina );
                ULOG("<<< CGrp::GrpListaUsuariosNaoAssociadosSkillPorLogin");
                for( iCont = 0; iCont < pzoGrp->Quantidade(); iCont++ )
                {
                    XmlSaida->createTag( "UsuarioExistenteVO" );
                    XmlSaida->addItem( "idUsuario", pzoGrp->Registro(iCont)->cidGrupo );
                    XmlSaida->addItem( "nmUsuario", pzoGrp->Registro(iCont)->cnmGrupo );
                    XmlSaida->closeTag();//AdmGrupoVO
                }
            }
        }		
        else
        if(inOperacao == 3) // lista usuarios associados ao skill
        {
            ULOG("Iniciando operacao 3 para listagem de usuarios associados.");
            ULOG("pzcregistrosPPagina = %s ", pzcregistrosPPagina);
            //pzoGrp = new CGrp();
            ULOG(">>> CGrp::GrpListaUsuariosAssociadosSkill");
            pzoGrp->GrpListaUsuariosAssociadosSkill( pzcidGrupoSkill, iPaginaAtual, iRegistrosPPagina );
            ULOG("<<< CGrp::GrpListaUsuariosAssociadosSkill");
            for( iCont = 0; iCont < pzoGrp->Quantidade(); iCont++ )
            {
                XmlSaida->createTag( "UsuarioSelecionadoVO" );
                XmlSaida->addItem( "idUsuario", pzoGrp->Registro(iCont)->cidGrupo );
                XmlSaida->addItem( "nmUsuario", pzoGrp->Registro(iCont)->cnmGrupo );
                XmlSaida->closeTag();//AdmGrupoVO
            }
        }
        else if(inOperacao == 4) // lista contatos nao associados (disponiveis)
        {
            ULOG("Iniciando operacao 4 para listagem de contatos disponiveis.");
            ULOG("pzcregistrosPPagina = %s ", pzcregistrosPPagina);
            //pzoGrp = new CGrp();
            ULOG(">>> CGrp::GrpListaContatosNaoAssociadosSkill");
            pzoGrp->GrpListaContatosNaoAssociadosSkill( pzcidGrupoSkill, iPaginaAtual, iRegistrosPPagina );
            ULOG("<<< CGrp::GrpListaContatosNaoAssociadosSkill");
            for( iCont = 0; iCont < pzoGrp->Quantidade(); iCont++ )
            {
                XmlSaida->createTag( "ContatoExistenteVO" );
                XmlSaida->addItem( "idContato", pzoGrp->Registro(iCont)->cidGrupo );
                XmlSaida->addItem( "nmPath", pzoGrp->Registro(iCont)->cnmGrupo );
                XmlSaida->closeTag();//AdmGrupoVO
            }
        }
        else if(inOperacao == 5)
        { // lista contatos associados

            ULOG("Iniciando operacao 8 para listagem de contatos associados.");
            ULOG("pzcregistrosPPagina = %s ", pzcregistrosPPagina);
            //pzoGrp = new CGrp();
            ULOG(">>> CGrp::GrpListaContatosAssociadosSkill");
            pzoGrp->GrpListaContatosAssociadosSkill( pzcidGrupoSkill, iPaginaAtual, iRegistrosPPagina );
            ULOG("<<< CGrp::GrpListaContatosAssociadosSkill");
            for( iCont = 0; iCont < pzoGrp->Quantidade(); iCont++ )
            {
                XmlSaida->createTag( "ContatoSelecionadoVO" );
                XmlSaida->addItem( "idContato", pzoGrp->Registro(iCont)->cidGrupo );
                XmlSaida->addItem( "nmPath", pzoGrp->Registro(iCont)->cnmGrupo );
                XmlSaida->closeTag();//AdmGrupoVO
            }
        }

        // Acrescenta a informação de paginação.
        XmlSaida->addItem( "paginaAtual", iPaginaAtual );
        XmlSaida->addItem( "registrosPPagina", iRegistrosPPagina );
        setStatusCode("14I0000","Serviço finalizado com sucesso!");
        delete pzoGrp;									

	} // if( iRegistrosPPagina > 0 ) 
	else  if( inOperacao != 2 )
	{
	    pzoGrp = new CGrp();

	    if( strlennull( pzcidGrupo ) <= 0 )
	    {
		    pzoGrp->GrpListaTodos();
		    for(iCont = 0; iCont < pzoGrp->Quantidade(); iCont++ )
		    {
			    //XmlSaida->createTag( "tns:AdmGrupoVO" );
			    XmlSaida->createTag( "AdmGrupoVO" );
			    //XmlSaida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
				    XmlSaida->addItem( "idGrupo", pzoGrp->Registro(iCont)->cidGrupo );
				    XmlSaida->addItem( "nmGrupo", pzoGrp->Registro(iCont)->cnmGrupo );
			    XmlSaida->closeTag();//AdmGrupoVO
		    }
	    }//if( strlennull( pzcidGrupo ) <= 0 )
	    else
	    {
		    //Soh recupera a lista de skill se não for uma busca por usuario
		    if(inOperacao == 1) //lista os skills do grupo  
		    {
			    if( strlennull( pzclogin ) <= 0 )
			    {
				    pzoGrp->GrpListaGrupoSkill( pzcidGrupo );
				    for(iCont = 0; iCont < pzoGrp->Quantidade(); iCont++ )
				    {
					    //XmlSaida->createTag( "tns:AdmSkillSimplVO" );
					    XmlSaida->createTag( "AdmSkillSimplVO" );
					    //XmlSaida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
						    XmlSaida->addItem( "idSkill", pzoGrp->Registro(iCont)->cidGrupo );
						    XmlSaida->addItem( "nmSkill", pzoGrp->Registro(iCont)->cnmGrupo );
					    XmlSaida->closeTag();//AdmSkillSimplVO
				    }
			    }
		    }

	    }//else if( strlennull( pzcidGrupo ) <= 0 )

	    // Acrescenta a informação de paginação.
	    ULOG("Acrescentando a informação de paginacao...");
	    XmlSaida->addItem( "paginaAtual", iPaginaAtual );
	    XmlSaida->addItem( "registrosPPagina", iRegistrosPPagina );
	    setStatusCode("14I0000","Serviço finalizado com sucesso!");
	    delete pzoGrp;

	}//if( inOperacao != 2 )

	// Acrescenta a informação de paginação.
	XmlSaida->addItem( "paginaAtual", iPaginaAtual );
	XmlSaida->addItem( "registrosPPagina", iRegistrosPPagina );

	XmlSaida->closeTag();//AdmSkillUsuarioVO
	ULOG_END("implGRPSKLGET::Execute()");
}
