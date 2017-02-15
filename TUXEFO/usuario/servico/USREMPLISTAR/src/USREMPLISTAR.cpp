//Definicao Global
#define USREMPLISTARCPP

#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CGrupoUsuario.h"

DECLARE_TUXEDO_SERVICE(USREMPLISTAR);

void implUSREMPLISTAR::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    ULOG_START("implUSREMPLISTAR::Execute()");
    
    CGrupoUsuario *pclCGrupoUsuario;
    char *pParam=NULL;
    char szAcao[25 + 1];
    char szIdGrupo[21 + 1];

    try
    {
       
        pclCGrupoUsuario = new CGrupoUsuario;

        // acao
        pParam = walkTree(dnode, "acao", 0);
        if(!pParam) {
            setStatusCode("08E0001","acao esta nulo");
            ULOG_END("implUSREMPLISTAR::Execute()");
            return;
        }
        if(!strlen(pParam)) {
            setStatusCode("08E0001","acao esta vazio");
            ULOG_END("implUSREMPLISTAR::Execute()");
            return;
        }
        strcpy(szAcao, pParam);
        XMLString::release(&pParam);
        ULOG("szAcao[%s]", szAcao);

        if(!strcmp(szAcao, "LISTARGRUPOS")) {
            ULOG("LISTARGRUPOS");
            pclCGrupoUsuario->carregaComboGrupo(xml_g);
        }
        else if(!strcmp(szAcao, "LISTARUSUARIOS")) {

            // idGrupoOrigemSelecionado
            pParam = walkTree(dnode, "idGrupoOrigemSelecionado", 0);
            if(!pParam) {
                setStatusCode("08E0001","idGrupoOrigemSelecionado esta nulo");
                ULOG_END("implUSREMPLISTAR::Execute()");
                return;
            }
            if(!strlen(pParam)) {
                setStatusCode("08E0001","idGrupoOrigemSelecionado esta vazio");
                ULOG_END("implUSREMPLISTAR::Execute()");
                return;
            }
            strcpy(szIdGrupo, pParam);
            XMLString::release(&pParam);
            ULOG("szIdGrupo[%s]", szIdGrupo);

            xml_g->createTag("AdmEmprestimoUsuariosVO");
            xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo");

            ULOG("A. LISTARUSUARIOS");
            pclCGrupoUsuario->carregaListaUsuario(xml_g, szIdGrupo);
            ULOG("B. LISTARUSUARIOS");
            pclCGrupoUsuario->carregaListaUsuarioEmprestado(xml_g, szIdGrupo);


            xml_g->closeTag();
        }
        else {
            ULOG("EXCEPTION");
            setStatusCode("60E0000", "Procedimento encerrado com erro");
            ULOG_END("implUSREMPLISTAR::Execute()");
            return;
        }

	}
    catch(...)
    {
        delete pclCGrupoUsuario;
        throw;
    }

    delete pclCGrupoUsuario;
    setStatusCode("60I0000", "Procedimento encerrado.");
    
    ULOG_END("implUSREMPLISTAR::Execute()");
}
