/*****************************************************************************
 * Arquivo:   CttTpArvore.cpp
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 16/10/2007  C_Cgarcia             Criacao
 *
 ****************************************************************************/

//Definicao Global
#define CTTTPARVORECPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CCtpa.h"
#include "../../../negocio/admatdCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CTTTPARVORE);

void implCTTTPARVORE::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implCTTTPARVORE::Execute()");
   
    try
    {
	    CCTipoArvore oCTipoArvore;
	    CSafePointer oSafePointer;

        char* dsTipoArvore = oSafePointer.getTag( dnode, "dsTipoArvore", 0 );
	    
	    xml_g->createTag("AdmTipoArvoreVO");
	    xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );

	    oCTipoArvore.ListTipoArvore( dsTipoArvore );
	    oCTipoArvore.GetXml( "TipoArvore", xml_g );

	    xml_g->closeTag();//AdmTiposArvoreVO
	    
	    setStatusCode("14I0000","Serviço finalizado com sucesso!");
    }
    catch (TuxBasicOraException *ex)
    {
        char codErro[25];
        sprintf(codErro,"14E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);

        setStatusCode(codErro,ex->pMsg);

        delete ex;
    }
    catch(TuxException *pTux)
    {
        char *pCode = pTux->getCode();
        if ( !pCode ) pCode = "14E9999";

        char *pMessage = pTux->getMessage();
        if ( !pMessage ) pMessage = "Erro não identificado.";

        setStatusCode(pCode,pMessage);

        delete pTux;
    }
    catch( char *MsgErro )
    {
        setStatusCode("14E0099",MsgErro);
    }
    catch (...) 
    {
        setStatusCode("14E9999","Execução com erro"); 
    }
	
	ULOG_END("implCTTTPARVORE::Execute()");
}
