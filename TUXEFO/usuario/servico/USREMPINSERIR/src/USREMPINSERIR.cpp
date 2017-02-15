//Definicao Global
#define USREMPINSERIRCPP

#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CGrupoUsuario.h"

DECLARE_TUXEDO_SERVICE(USREMPINSERIR);

void implUSREMPINSERIR::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    ULOG_START("implUSREMPINSERIR::Execute()");
    
    char szDtInicio[12 + 1];
    char szDtFim[12 + 1];
    char szIdGrupo[21 + 1];
    char szOperacao[1 + 1];
    char szIdPessoaUsuario[21 + 1];

    char *pParam=NULL;
    CGrupoUsuario *pclCGrupoUsuario;

    DOMNode *DNUsuarioDestino=NULL;
    int iCont;

    try
    {
        pclCGrupoUsuario = new CGrupoUsuario;

        for(iCont=0; (DNUsuarioDestino = walkDOM(dnode, "usuarioDestino", iCont))!= NULL; iCont++) {

            ULOG("iCont(%d)", iCont);


            // operacao
            pParam = walkTree(DNUsuarioDestino, "operacao", 0);
            if(!pParam) {
                setStatusCode("08E0001","operacao esta nulo");
                ULOG_END("implUSREMPINSERIR::Execute()");
                return;
            }
            strcpy(szOperacao, pParam);
            XMLString::release(&pParam);
            ULOG("szOperacao[%s]", szOperacao);


            if(!strcmp(szOperacao, "D")) {
                // IdPessoaUsuario
                pParam = walkTree(DNUsuarioDestino, "idUsuario", 0);
                if(!pParam) {
                    setStatusCode("08E0001","idUsuario esta nulo");
                    ULOG_END("implUSREMPINSERIR::Execute()");
                    return;
                }
                if(!strlen(pParam)) {
                    setStatusCode("08E0001","idUsuario esta vazio");
                    ULOG_END("implUSREMPINSERIR::Execute()");
                    return;
                }
                strcpy(szIdPessoaUsuario, pParam);
                XMLString::release(&pParam);
                ULOG("szIdPessoaUsuario[%s]", szIdPessoaUsuario);

                pclCGrupoUsuario->apagaUsuarioEmprestado(szIdPessoaUsuario);
            }
            else if(!strcmp(szOperacao, "I")){
                // DtInicio
                pParam = walkTree(DNUsuarioDestino, "dtInicio", 0);
                if(!pParam) {
                    setStatusCode("08E0001","dtInicio esta nulo");
                    ULOG_END("implUSREMPINSERIR::Execute()");
                    return;
                }
                if(!strlen(pParam)) {
                    setStatusCode("08E0001","dtInicio esta vazio");
                    ULOG_END("implUSREMPINSERIR::Execute()");
                    return;
                }
                strcpy(szDtInicio, pParam);
                XMLString::release(&pParam);
                ULOG("szDtInicio[%s]", szDtInicio);
        
    
                // DtFinal
                pParam = walkTree(DNUsuarioDestino, "dtFim", 0);
                if(!pParam) {
                    setStatusCode("08E0001","dtFim esta nulo");
                    ULOG_END("implUSREMPINSERIR::Execute()");
                    return;
                }
                if(!strlen(pParam)) {
                    setStatusCode("08E0001","dtFim esta vazio");
                    ULOG_END("implUSREMPINSERIR::Execute()");
                    return;
                }
                strcpy(szDtFim, pParam);
                XMLString::release(&pParam);
                ULOG("szDtFim[%s]", szDtFim);
        
    
    
                // IdGrupo
                pParam = walkTree(DNUsuarioDestino, "idGrupoDestino", 0);
                if(!pParam) {
                    setStatusCode("08E0001","idGrupoDestino esta nulo");
                    ULOG_END("implUSREMPINSERIR::Execute()");
                    return;
                }
                if(!strlen(pParam)) {
                    setStatusCode("08E0001","idGrupoDestino esta vazio");
                    ULOG_END("implUSREMPINSERIR::Execute()");
                    return;
                }
                strcpy(szIdGrupo, pParam);
                XMLString::release(&pParam);
                ULOG("szIdGrupo[%s]", szIdGrupo);
    
    
    
                // IdPessoaUsuario
                pParam = walkTree(DNUsuarioDestino, "idUsuario", 0);
                if(!pParam) {
                    setStatusCode("08E0001","idUsuario esta nulo");
                    ULOG_END("implUSREMPINSERIR::Execute()");
                    return;
                }
                if(!strlen(pParam)) {
                    setStatusCode("08E0001","idUsuario esta vazio");
                    ULOG_END("implUSREMPINSERIR::Execute()");
                    return;
                }
                strcpy(szIdPessoaUsuario, pParam);
                XMLString::release(&pParam);
                ULOG("szIdPessoaUsuario[%s]", szIdPessoaUsuario);

                pclCGrupoUsuario->insereUsuarioEmprestado(szDtInicio, szDtFim, szIdPessoaUsuario, getUser(), szIdPessoaUsuario, szIdGrupo);
            }
        }

        ULOG("B.iCont(%d)", iCont);

        if(iCont==0) {
            setStatusCode("08E0001","usuarioDestino esta nulo");
            ULOG_END("implUSREMPINSERIR::Execute()");
            return;
        }
        
	    ULOG_END("implUSREMPINSERIR::Execute()");
	}
    catch(...)
    {
        throw;
    }

    setStatusCode("60I0000", "Procedimento encerrado.");
    
}
