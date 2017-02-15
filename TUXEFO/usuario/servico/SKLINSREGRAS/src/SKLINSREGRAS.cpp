//Definicao Global
#define SKLINSALTCPP

#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CGrupoSkill.h"

DECLARE_TUXEDO_SERVICE(SKLINSREGRAS);

void implSKLINSREGRAS::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    ULOG_START("implSKLINSREGRAS::Execute()");
   
    int iCont;
    char *pParam=NULL;
    DOMNode *DNVO=NULL;
    char szIdGrupoSkill[21 + 1];
    char szAux[21 + 1];


    try
    {
        CGrupoSkill clCGrupoSkill(getUser());

        // idGrupoSkill
        pParam = walkTree(dnode, "codigoGrupo", 0);
        if(!pParam) {
            setStatusCode("08E0001","codigoGrupo esta nulo");
            ULOG_END("implSKLINSREGRAS::Execute()");
            return;
        }
        if(!strlen(pParam)) {
            setStatusCode("08E0001","codigoGrupo esta vazio");
            XMLString::release(&pParam);
            ULOG_END("implSKLINSREGRAS::Execute()");
            return;
        }
        strcpy(szIdGrupoSkill, pParam);
        XMLString::release(&pParam);
        ULOG("szIdGrupoSkill[%s]", szIdGrupoSkill);



        ULOG("Inicio da delecao");
        clCGrupoSkill.apagaTpRelacionamentoGrupoSkill(szIdGrupoSkill);
        clCGrupoSkill.apagaTipoPessoaGrupoSkill(szIdGrupoSkill);
        clCGrupoSkill.apagaSegmentacaoGrupoSkill(szIdGrupoSkill);
        clCGrupoSkill.apagaUfOperadoraGrupoSkill(szIdGrupoSkill);
        clCGrupoSkill.apagaProcedenciaGrupoSkill(szIdGrupoSkill);
        clCGrupoSkill.apagaGrupoAberturaGrupoSkill(szIdGrupoSkill);
        clCGrupoSkill.apagaTipoCarteiraGrupoSkill(szIdGrupoSkill);
        clCGrupoSkill.apagaTipoLinhaGrupoSkill(szIdGrupoSkill);
        clCGrupoSkill.apagaCanalGrupoSkill(szIdGrupoSkill);
        ULOG("Final da delecao");


        // TipoLinhaVO
        for(iCont=0; (DNVO = walkDOM(dnode, "TipoLinhaVO", iCont)) != NULL; iCont++) {
            ULOG("iCont(%d)", iCont);
            pParam = walkTree(DNVO, "id", 0);
            if(!pParam) {
                setStatusCode("08E0001","TipoLinhaVO.id esta nulo");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            if(!strlen(pParam)) {
                XMLString::release(&pParam);
                setStatusCode("08E0001","TipoLinhaVO.id esta vazio");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            strcpy(szAux, pParam);
            XMLString::release(&pParam);

            ULOG("szAux[%s]", szAux);
            clCGrupoSkill.gravaTipoLinhaGrupoSkill(szIdGrupoSkill, szAux);
        }



        // SegmentacaoVO
        for(iCont=0; (DNVO = walkDOM(dnode, "SegmentacaoVO", iCont)) != NULL; iCont++) {
            ULOG("iCont(%d)", iCont);
            pParam = walkTree(DNVO, "codigo", 0);
            if(!pParam) {
                setStatusCode("08E0001","SegmentacaoVO.codigo esta nulo");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            if(!strlen(pParam)) {
                XMLString::release(&pParam);
                setStatusCode("08E0001","SegmentacaoVO.codigo esta vazio");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            strcpy(szAux, pParam);
            XMLString::release(&pParam);

            ULOG("szAux[%s]", szAux);
            clCGrupoSkill.gravaSegmentacaoGrupoSkill(szIdGrupoSkill, szAux);
        }


        // CarterizacaoVO
        for(iCont=0; (DNVO = walkDOM(dnode, "CarterizacaoVO", iCont)) != NULL; iCont++) {
            ULOG("iCont(%d)", iCont);
            pParam = walkTree(DNVO, "codigo", 0);
            if(!pParam) {
                setStatusCode("08E0001","CarterizacaoVO.codigo esta nulo");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            if(!strlen(pParam)) {
                XMLString::release(&pParam);
                setStatusCode("08E0001","CarterizacaoVO.codigo esta vazio");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            strcpy(szAux, pParam);
            XMLString::release(&pParam);

            ULOG("szAux[%s]", szAux);
            clCGrupoSkill.gravaTipoCarteiraGrupoSkill(szIdGrupoSkill, szAux);
        }


        // ProcedenciaVO
        for(iCont=0; (DNVO = walkDOM(dnode, "ProcedenciaVO", iCont)) != NULL; iCont++) {
            ULOG("iCont(%d)", iCont);
            pParam = walkTree(DNVO, "codigo", 0);
            if(!pParam) {
                setStatusCode("08E0001","ProcedenciaVO.codigo esta nulo");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            if(!strlen(pParam)) {
                XMLString::release(&pParam);
                setStatusCode("08E0001","ProcedenciaVO.codigo esta vazio");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            strcpy(szAux, pParam);
            XMLString::release(&pParam);

            ULOG("szAux[%s]", szAux);
            clCGrupoSkill.gravaProcedenciaGrupoSkill(szIdGrupoSkill, szAux);
        }


        // TipoClienteVO
        for(iCont=0; (DNVO = walkDOM(dnode, "TipoClienteVO", iCont)) != NULL; iCont++) {
            ULOG("iCont(%d)", iCont);
            pParam = walkTree(DNVO, "codigo", 0);
            if(!pParam) {
                setStatusCode("08E0001","TipoClienteVO.codigo esta nulo");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            if(!strlen(pParam)) {
                XMLString::release(&pParam);
                setStatusCode("08E0001","TipoClienteVO.codigo esta vazio");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            strcpy(szAux, pParam);
            XMLString::release(&pParam);

            ULOG("szAux[%s]", szAux);
            clCGrupoSkill.gravaTpRelacionamentoGrupoSkill(szIdGrupoSkill, szAux);
        }


        // TipoPessoaVO
        for(iCont=0; (DNVO = walkDOM(dnode, "TipoPessoaVO", iCont)) != NULL; iCont++) {
            ULOG("iCont(%d)", iCont);
            pParam = walkTree(DNVO, "idtipopessoa", 0);
            if(!pParam) {
                setStatusCode("08E0001","TipoPessoaVO.idtipopessoa esta nulo");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            if(!strlen(pParam)) {
                XMLString::release(&pParam);
                setStatusCode("08E0001","TipoPessoaVO.idtipopessoa esta vazio");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            strcpy(szAux, pParam);
            XMLString::release(&pParam);

            ULOG("szAux[%s]", szAux);
            clCGrupoSkill.gravaTipoPessoaGrupoSkill(szIdGrupoSkill, szAux);
        }


        // GrupoVO
        for(iCont=0; (DNVO = walkDOM(dnode, "GrupoVO", iCont)) != NULL; iCont++) {
            ULOG("iCont(%d)", iCont);
            pParam = walkTree(DNVO, "codigo", 0);
            if(!pParam) {
                setStatusCode("08E0001","GrupoVO.codigo esta nulo");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            if(!strlen(pParam)) {
                setStatusCode("08E0001","GrupoVO.codigo esta vazio");
                XMLString::release(&pParam);
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            strcpy(szAux, pParam);
            XMLString::release(&pParam);

            ULOG("szAux[%s]", szAux);
            clCGrupoSkill.gravaGrupoAberturaGrupoSkill(szIdGrupoSkill, szAux);
        }


        // CanalVO
        for(iCont=0; (DNVO = walkDOM(dnode, "CanalVO", iCont)) != NULL; iCont++) {
            ULOG("iCont(%d)", iCont);
            pParam = walkTree(DNVO, "idCanal", 0);
            if(!pParam) {
                setStatusCode("08E0001","CanalVO.idCanal esta nulo");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            if(!strlen(pParam)) {
                setStatusCode("08E0001","CanalVO.idCanal esta vazio");
                XMLString::release(&pParam);
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            strcpy(szAux, pParam);
            XMLString::release(&pParam);

            ULOG("szAux[%s]", szAux);
            clCGrupoSkill.gravaCanalGrupoSkill(szIdGrupoSkill, szAux);
        }


        // RegionalVO
        for(iCont=0; (DNVO = walkDOM(dnode, "RegionalVO", iCont)) != NULL; iCont++) {
            ULOG("iCont(%d)", iCont);
            pParam = walkTree(DNVO, "idRegional", 0);
            if(!pParam) {
                setStatusCode("08E0001","RegionalVO.idRegional esta nulo");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            if(!strlen(pParam)) {
                XMLString::release(&pParam);
                setStatusCode("08E0001","RegionalVO.idRegional esta vazio");
                ULOG_END("implSKLINSREGRAS::Execute()");
                return;
            }
            strcpy(szAux, pParam);
            XMLString::release(&pParam);

            ULOG("szAux[%s]", szAux);
            clCGrupoSkill.gravaUfOperadoraGrupoSkill(szIdGrupoSkill, szAux);
        }
        
	}
    catch(...)
    {
        throw;
    }

    setStatusCode("60I0000", "Procedimento encerrado com sucesso");
    ULOG_END("implSKLINSREGRAS::Execute()");
}
