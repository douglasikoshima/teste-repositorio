//Definicao Global
#define SKLINSALTCPP

#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CGrupoSkill.h"

DECLARE_TUXEDO_SERVICE(SKLINSALT);

void implSKLINSALT::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    ULOG_START("implSKLINSALT::Execute()");
    
    char szIdGrupoSkill[21 + 1];
    char szIdGrupo[21 + 1];
    char szDsGrupoSkill[255 + 1];
    char *pParam=NULL;
    CGrupoSkill *pclCGrupoSkill;

    try
    {
        memset(szIdGrupoSkill, 0x00, sizeof(szIdGrupoSkill));
        memset(szIdGrupo, 0x00, sizeof(szIdGrupo));
        memset(szDsGrupoSkill, 0x00, sizeof(szDsGrupoSkill));

        // Regra do servico:
        // se vier o idSkill eh uma atualizacao senao uma insercao

        // Obtem idSkill do xml
        pParam = walkTree(dnode, "idSkill", 0);
        if(!pParam) {
            setStatusCode("08E0001","idSkill esta nulo");
            ULOG_END("implSKLINSALT::Execute()");
            return;
        }
        if(strlen(pParam))
            strcpy(szIdGrupoSkill, pParam);

        if(pParam) XMLString::release(&pParam);
        ULOG("szIdGrupoSkill[%s]", szIdGrupoSkill);

        // Obtem idGrupo do xml
        pParam = walkTree(dnode, "idGrupo", 0);
        if(!pParam) {
            setStatusCode("08E0001","idGrupo esta nulo");
            ULOG_END("implSKLINSALT::Execute()");
            return;
        }
        if(strlen(pParam))
            strcpy(szIdGrupo, pParam);

        if(pParam) XMLString::release(&pParam);
        ULOG("szIdGrupo[%s]", szIdGrupo);


        // Obtem dsSkill do xml
        pParam = walkTree(dnode, "dsSkill", 0);
        if(!pParam) {
            setStatusCode("08E0001","dsSkill esta nulo");
            ULOG_END("implSKLINSALT::Execute()");
            return;
        }
        if(strlen(pParam))
            strcpy(szDsGrupoSkill, pParam);

        if(pParam) XMLString::release(&pParam);
        ULOG("szDsGrupoSkill[%s]", szDsGrupoSkill);


        pclCGrupoSkill = new CGrupoSkill;

        if(szIdGrupoSkill[0] == 0x00) {
            if( (szIdGrupo[0] == 0x00) || (szDsGrupoSkill[0] == 0x00) ) {
                setStatusCode("08E0001","IdGrupo ou dsSkill está vazio para insercao!");
                ULOG_END("implSKLINSALT::Execute()");
                return;
            }

            if(pclCGrupoSkill->existeSkill(szDsGrupoSkill) == true) {
                setStatusCode("08W0001","Nome de skill já existente!");
                ULOG_END("implSKLINSALT::Execute()");
                return;
            }

            pclCGrupoSkill->inserirSkill(szIdGrupoSkill, szIdGrupo, szDsGrupoSkill);
        }
        else if(szIdGrupo[0] == 0x00) {
            if( (szIdGrupoSkill[0] == 0x00) || (szDsGrupoSkill[0] == 0x00) ) {
                setStatusCode("08E0001","IdSkill ou dsSkill está vazio para atualizacao!");
                ULOG_END("implSKLINSALT::Execute()");
                return;
            }

            if(pclCGrupoSkill->existeSkill(szDsGrupoSkill) == true) {
                setStatusCode("08W0001","Nome de skill já existente!");
                ULOG_END("implSKLINSALT::Execute()");
                return;
            }

            pclCGrupoSkill->alterarSkill(szIdGrupoSkill, szDsGrupoSkill);
        }
        else {
            setStatusCode("08E0001","Erro de consistencia do servico");
            ULOG_END("implSKLINSALT::Execute()");
            return;
        }

        xml_g->createTag("ManterSkillVO");
        xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo");
            xml_g->createTag("Skill");
                xml_g->addItem("idSkill", szIdGrupoSkill);
                xml_g->addItem("dsSkill", szDsGrupoSkill);
                xml_g->addItem("idGrupo", szIdGrupo);
            xml_g->closeTag();
        xml_g->closeTag();
        
	}
    catch(...)
    {
        delete pclCGrupoSkill;
        throw;
    }

    delete pclCGrupoSkill;
    setStatusCode("60I0000", "Procedimento encerrado com sucesso");
    ULOG_END("implSKLINSALT::Execute()");
}
