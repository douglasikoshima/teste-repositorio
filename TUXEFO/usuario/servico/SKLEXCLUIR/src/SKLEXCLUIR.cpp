//Definicao Global
#define SKLEXCLUIRCPP

#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CGrupoSkill.h"

DECLARE_TUXEDO_SERVICE(SKLEXCLUIR);

void implSKLEXCLUIR::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    ULOG_START("implSKLEXCLUIR::Execute()");
    
    char *pParam=NULL;
    char szIdGrupoSkill[21 + 1];
    CGrupoSkill *pclCGrupoSkill;

    try
    {
        pParam = walkTree(dnode, "idSkill", 0);
        if(!pParam) {
            setStatusCode("08E0001","idSkill esta nulo");
            ULOG_END("implSKLEXCLUIR::Execute()");
            return;
        }
        if(!strlen(pParam)) {
            setStatusCode("08E0001","idSkill esta vazio");
            ULOG_END("implSKLEXCLUIR::Execute()");
            return;
        }
        strcpy(szIdGrupoSkill, pParam);
        XMLString::release(&pParam);

        pclCGrupoSkill = new CGrupoSkill;

        pclCGrupoSkill->apagaTpRelacionamentoGrupoSkill(szIdGrupoSkill);
        pclCGrupoSkill->apagaTipoPessoaGrupoSkill(szIdGrupoSkill);
        pclCGrupoSkill->apagaSegmentacaoGrupoSkill(szIdGrupoSkill);
        pclCGrupoSkill->apagaUfOperadoraGrupoSkill(szIdGrupoSkill);
        pclCGrupoSkill->apagaProcedenciaGrupoSkill(szIdGrupoSkill);
        pclCGrupoSkill->apagaGrupoAberturaGrupoSkill(szIdGrupoSkill);
        pclCGrupoSkill->apagaTipoCarteiraGrupoSkill(szIdGrupoSkill);
        pclCGrupoSkill->apagaTipoLinhaGrupoSkill(szIdGrupoSkill);
        pclCGrupoSkill->apagaContatoFolhaUsuario(szIdGrupoSkill);

        pclCGrupoSkill->apagaCanalGrupoSkill(szIdGrupoSkill);


        pclCGrupoSkill->excluirSkill(szIdGrupoSkill);
        
	}
    catch(...)
    {
        delete pclCGrupoSkill;
        throw;
    }

    delete pclCGrupoSkill;
    setStatusCode("60I0000", "Procedimento encerrado com sucesso");
    
    ULOG_END("implSKLEXCLUIR::Execute()");
}
