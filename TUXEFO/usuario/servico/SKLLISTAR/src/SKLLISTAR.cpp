//Definicao Global
#define SKLLISTARCPP

#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CGrupoSkill.h"

DECLARE_TUXEDO_SERVICE(SKLLISTAR);

void implSKLLISTAR::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    ULOG_START("implSKLLISTAR::Execute()");
    char *pParam=NULL;
    char szIdGrupo[21 + 1];
    char szDsGrupoSkill[255 + 1];
    CGrupoSkill *pclCGrupoSkill;

    try
    {
        // idGrupo
        pParam = walkTree(dnode, "idGrupo", 0);
        if(!pParam) {
            setStatusCode("08E0001","idGrupo esta nulo");
            ULOG_END("implSKLLISTAR::Execute()");
            return;
        }
        if(!strlen(pParam)) {
            setStatusCode("08E0001","idGrupo esta vazio");
            ULOG_END("implSKLLISTAR::Execute()");
            return;
        }
        strcpy(szIdGrupo, pParam);
        XMLString::release(&pParam);

        // dsGrupoSkill
        pParam = walkTree(dnode, "dsGrupoSkill", 0);
        if(!pParam) {
            setStatusCode("08E0001","dsGrupoSkill esta nulo");
            ULOG_END("implSKLLISTAR::Execute()");
            return;
        }
        strcpy(szDsGrupoSkill, pParam);
        XMLString::release(&pParam);


        pclCGrupoSkill = new CGrupoSkill;

        pclCGrupoSkill->listarSkill(szIdGrupo, szDsGrupoSkill, xml_g);

        
	}
    catch(...)
    {
        delete pclCGrupoSkill;
        throw;
    }

    delete pclCGrupoSkill;
    setStatusCode("60I0000", "Procedimento encerrado com sucesso");
    ULOG_END("implSKLLISTAR::Execute()");
}
