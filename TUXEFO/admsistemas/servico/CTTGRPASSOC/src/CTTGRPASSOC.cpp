/*****************************************************************************
 *
 * Modulo:    CTTGRPASSOC
 * Arquivo:   CTTGRPASSOC.cpp
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:06 $ 
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 ****************************************************************************/

//Definicao Global
#define CTTGRPASSOCCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CContatoGrupoRC.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CTTGRPASSOC);

/**************************************************************************
 *  Funcao de Negocios:  CTTGRPASSOC
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
void implCTTGRPASSOC::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implCTTGRPASSOC::Execute()");
    try
    {
        /* Chamada de Funcao de Negocios */
        CSafePointer oSafePointer;
        CContatoGrupoRC oContatoGrupoRC;
        int iCont;
        char *cidGrupo=NULL;
    
        ULOG("Inicio CTTGRPASSOC");
    
        char* cidUser = getUser();
        char* cidContato=oSafePointer.getTag(dnode, "idContato", 0 );
        if( strlennull( cidContato ) <= 0 ){
            setStatusCode("14E0001","idContato está nulo");
            ULOG_END("implCTTGRPASSOC::Execute()");
            return;
        }
        ULOG("cidContato[%s]", cidContato);
    
        ULOG("Delete -> cidContato[%s]", cidContato);
        oContatoGrupoRC.Delete(cidContato);

        for(iCont=0;; iCont++)
        {
            ULOG("iCont[%s]", iCont);
    
            cidGrupo=oSafePointer.getTag(dnode, "idGrupo", iCont);
            ULOG("cidGrupo[%s]", cidGrupo);
            if( (strlennull(cidGrupo) <= 0 ) || (!strcmp(cidGrupo, "null")) ){
                break;
            }

            ULOG("Insert -> cidContato[%s] cidGrupo[%s] cidUser[%s]", cidContato, cidGrupo, cidUser);
            oContatoGrupoRC.Insert(cidContato, cidGrupo, cidUser,iCont+1);
        }
    
        
	}
    catch(...)
    {
        ULOGE("implCTTGRPASSOC::Execute()");
        throw;
    }
    setStatusCode("14I0000", "Operação realizada com sucesso" );
    ULOG_END("implCTTGRPASSOC::Execute()");
}
