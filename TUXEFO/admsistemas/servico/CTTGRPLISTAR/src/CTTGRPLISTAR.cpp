/*****************************************************************************
 *
 * Modulo:    CTTGRPLISTAR
 * Arquivo:   CTTGRPLISTAR.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 ****************************************************************************/

//Definicao Global
#define CTTGRPLISTARCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CContatoGrupoRC.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CTTGRPLISTAR);

/**************************************************************************
 *  Funcao de Negocios:  CTTGRPLISTAR
 *
 *  Descricao: chama funcao de negocios
 *
 *  Parametros de Entrada e Saida:
 *  DOMNode     *dnode    Nodos da Arvore DOM XML
 *  XMLGen      *xml_g    XML de entrada
 *
 *  Parametros de Saida:
 *  DOMNode     *dnode    Nodos da Arvore DOM XML
 *  XMLGen      *xml_g    XML de saida
 *
 *************************************************************************/
void implCTTGRPLISTAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implCTTGRPLISTAR::Execute()");
    try
    {
        /* Chamada de Funcao de Negocios */
        CSafePointer oSafePointer;
        CContatoGrupoRC oContatoGrupoRC;
    
       

        char* cidContato=oSafePointer.getTag(dnode, "idContato", 0 );
        if( strlennull( cidContato ) <= 0 ) {
            setStatusCode("14E0001","idContato está nulo");
            return;
        }
        ULOG("cidContato[%s]", cidContato);
    
        xml_g->createTag("AdmGrupoContatoContainerVO");
		xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );

				xml_g->addItem("idContato", cidContato);
    
                xml_g->createTag("gruposExistentes");
                    if( oContatoGrupoRC.ListIdContatoFolhaNaoRel( cidContato ) > 0 )
                        oContatoGrupoRC.GetXmlGrupo( "AdmGrupoVO", xml_g );
                xml_g->closeTag();
    

                xml_g->createTag("gruposAssociados");
                    if( oContatoGrupoRC.ListIdContatoFolhaRel( cidContato ) > 0 )
                        oContatoGrupoRC.GetXmlGrupo( "AdmGrupoVO", xml_g );
                xml_g->closeTag();

    
        xml_g->closeTag();
    
        
	}
    catch(...)
    {
        ULOGE("Final CTTGRPLISTAR com erro");
        throw;
    }


    setStatusCode("14I0000", "Operação realizada com sucesso" );
    ULOG_START("implCTTGRPLISTAR::Execute()");
    
}
