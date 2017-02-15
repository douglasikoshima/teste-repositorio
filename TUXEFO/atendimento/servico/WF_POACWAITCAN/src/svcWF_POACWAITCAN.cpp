/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:32 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
#include "../../../commons/definesAtendimento.h"
#include "../include/cWF_POACWAITCANPC.h"
using namespace std;

//------------------------------------------------------------------------------
// Implementação
//------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(POACWAITCAN);

void implPOACWAITCAN::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implPOACWAITCAN::Execute()");

    string sCode = "04I0000";
    string mCode = "Execucao de POACWAITCAN Concluida";

    cWF_POACWAITCAN rc(dnode, xml_g, getUser());

    try
    {
        rc.Executar(); 
    }
    catch (TuxBasicOraException *ex)
    {
        ULOGE("sqlca.sqlcode %d:%s",ex->eCode,ex->pMsg);

        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
        sCode = codErro;
        mCode = ex->pMsg;

        delete ex;
    }
    catch (TuxException *ex)
    {
        ULOGE("Erro %s:%s",ex->getCode(),ex->getMessage());

        sCode = ex->getCode();
        mCode = ex->getMessage();

        delete ex;
    }
    catch (char *errMsg)
    {
        ULOGE("%s",errMsg);

        sCode = "04E0097";
        mCode = errMsg;
    }
    catch (const char *errMsg)
    {
        ULOGE("%s",errMsg);

        sCode = "04E0098";
        mCode = errMsg;
    }
    catch (...)
    {
        ULOGE("erro desconhecido");

        sCode = "04E9999";
        mCode = "erro desconhecido";
    }

    setStatusCode((char*)sCode.c_str(),(char*)mCode.c_str());
    
    ULOG_END("implPOACWAITCAN::Execute()");
}

//------------------------------------------------------------------------------
// Construtor
//------------------------------------------------------------------------------

cWF_POACWAITCAN::cWF_POACWAITCAN(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef(dnode, xml_g);

     if ( user ) { User = user; }
}

//------------------------------------------------------------------------------
// Implementa o negócio ao qual o serviço atende
// Caso de Uso: Aguardar cancelamento de processo de portout
//------------------------------------------------------------------------------

void cWF_POACWAITCAN::Executar() 
{
    cWF_Acao::Executar();

    if ( User.ToInt() == 0 )
    {
        SetMessage( "Usuário destino não informado", "N" );
        xml_g->closeTag();
        return;
    }

    getStatusUsuario(User.ToInt());

    if ( idStatusUsuario != 1 )
    {
        SetMessage( "Usuário destino indisponível para recepcionar processos", "N" );
        xml_g->closeTag();
        return;
    }

    if ( getAtendimentoUsuarioAtual() )
    { // se já esta com um usuário, diferente do atual, não permite continuar
        if ( User.ToInt() != idUsuarioAtendimento && inSPN.ToInt() == 0 )
        {
            SetMessage( "Processo já esta sendo tratado por outro analista de retenção.", "N" );
            xml_g->closeTag();
            return;
        }
    }

    getAtendimento();

    removeAtendimentoUsuarioAtual();

    if ( getGrupoTratamentoRetencaoPout() == false )
    {
        SetMessage( "Grupo de tratamento de retenção de portout não especificado.", "N" );
        xml_g->closeTag();
        return;
    }

    alterarAtendimentoGrupoAtual();

    inserirAndamento();

    inserirAndamentoObservacao();

    // processo não irá ser exibido na fila de portout por um período parametrizado
    atualizarDataDelayFila();

    // Registro a ação no historico de portabilidade
    gravarPessoaPortabilidadeHist("POACWAITCAN");

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"POACWAITCAN");
    // <== SM324--DPR--DEZ/2006--Cassio

    SetMessage("Processo de portout aguardando cancelamento","S");

    xml_g->closeTag();
}
