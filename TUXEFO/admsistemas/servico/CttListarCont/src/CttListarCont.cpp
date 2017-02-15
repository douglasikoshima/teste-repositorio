/*****************************************************************************
 *
 * Modulo:    CttListarCont
 * Arquivo:   CttListarCont.cpp
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
#define CttListarContCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCtt.h"
#include "../../../negocio/admatdCmm/include/CCttNom.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttListarCont);

/**************************************************************************
 *  Funcao de Negocios:  CttListarCont
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
void implCttListarCont::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implCttListarCont::Execute()");

	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContato     oContato;
	CNomeContato oNomeContato;
	int          iNivel;
	int          iContTag;
    bool         hasNext = true;
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char* cidGrupo   = oSafePointer.getTag( dnode, "idGrupo", 0 );
	char* cidSkill   = oSafePointer.getTag( dnode, "idSkill", 0 );
    char* cidTipoArvore = oSafePointer.getTag( dnode, "idTipoArvore", 0 );
    int pageNumber   = atoi(oSafePointer.getTag( dnode, "pageNumber", 0 ));

	//Tag principal (container)
	xml_g->createTag("AdmArvoreContainerVO");
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );

	xml_g->createTag("AdmNomeContatoVO");
	xml_g->closeTag();//"AdmNomeContatoVO

	// Caso idGrupo ou idSkil tenham sido passados, chama a lista especializada
	if( (strlennull(cidSkill) > 0) || (strlennull(cidGrupo) > 0) )
    {
		oContato.ListAllSkill(cidContato, cidGrupo, cidSkill);
    }
	else
    {
        hasNext = oContato.ListAll(cidContato,pageNumber,cidTipoArvore) == 0 ? false : true;
    }

	xml_g->createTag("Paginacao");
	xml_g->addProp("xmlns", "cliente.fo.vivo.com.br/vo" );
	xml_g->addItem("pageNumber", pageNumber );
	xml_g->addItem("hasNext", hasNext );
	xml_g->closeTag(); //"Paginacao

	if( oContato.Quantidade() > 0 )
	{
		iNivel   = 0;
		iContTag = 0;
		
		//Monta o menu raiz
		xml_g->createTag("AdmContatoFolhaVO");
		xml_g->addItem("idContato", oContato.Registro(0)->cidContato );
		xml_g->addItem("idNomeContato", oContato.Registro(0)->cidNomeContato );
		xml_g->addItem("idContatoPai", oContato.Registro(0)->cidContatoPai );
		xml_g->addItem("nmContato", oContato.Registro(0)->cnmContato );
		xml_g->addItem("inDisponibilidade", oContato.Registro(0)->cinDisponibilidade );
		xml_g->addItem("nrNivel", oContato.Registro(0)->iLevel );
		xml_g->addItem("dsPath", oContato.Registro(0)->cdsPath );
		xml_g->addItem("inFolha", 0 );

        // Os686 Portabilidade - Contatos de portabilidade não podem ser parametrizados
        if ( strcmp(oContato.Registro(0)->cdsTipoProcesso,"PORTOUT") == 0 )
        {
			xml_g->addItem("permitirParametrizacao", "0" );
        }
        else
        {
			xml_g->addItem("permitirParametrizacao", "1" );
        }

		//O laço continua apos o raiz
		for( int x = 1; x < oContato.Quantidade(); x++ )
		{
			if( oContato.Registro( x ) != NULL )
			{

				if( oContato.Registro(x)->iLevel > iNivel )
				{
					iNivel = oContato.Registro(x)->iLevel;
					xml_g->createTag("AdmContatoFolhaVO");
					iContTag++;
				}//if( Registro(x)->iLevel > iNivel )
				else
				{
					xml_g->closeTag();
					if( oContato.Registro(x)->iLevel < iNivel )
					{
						while( iNivel > oContato.Registro(x)->iLevel )
						{
							xml_g->closeTag();
							iNivel--;
						}
						iNivel = oContato.Registro(x)->iLevel;
					}
					xml_g->createTag("AdmContatoFolhaVO");
				}// else if( Registro(x)->iLevel > iNivel )

				xml_g->addItem("idContato", oContato.Registro(x)->cidContato );
				xml_g->addItem("idNomeContato", oContato.Registro(x)->cidNomeContato );
				xml_g->addItem("idContatoPai", oContato.Registro(x)->cidContatoPai );
				xml_g->addItem("nmContato", oContato.Registro(x)->cnmContato );
				xml_g->addItem("inDisponibilidade", oContato.Registro(x)->cinDisponibilidade );
				xml_g->addItem("nrNivel", oContato.Registro(x)->iLevel );
				xml_g->addItem("dsPath", oContato.Registro(x)->cdsPath );
				xml_g->addItem("inFolha", oContato.Registro(x)->iFolha );

                // Os686 Portabilidade - Contatos de portabilidade não podem ser parametrizados
                if ( strcmp(oContato.Registro(x)->cdsTipoProcesso,"PORTOUT") == 0 )
                {
				    xml_g->addItem("permitirParametrizacao", "0" );
                }
                else
                {
				    xml_g->addItem("permitirParametrizacao", "1" );
                }
				
			}// if( Registro( x ) != NULL )
		}// for( int x = 0; x < Quantidade(); x++ )
		// Fecha todas as tags
		while( iContTag > 0 )
		{
			xml_g->closeTag();
			iContTag--;
		}
		setStatusCode("14I0000","Operacao concluída com sucesso!");
	}// if( oContato.Quantidade() > 0 )
	else
		setStatusCode("14W0001","Sem dados para retorno");

	xml_g->closeTag();//xml_g->createTag("AdmArvoreContainerVO");
	
	ULOG_START("implCttListarCont::Execute()");
}
