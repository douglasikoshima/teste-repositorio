/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:35 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
#include "../../../commons/definesAtendimento.h"
#include "../include/cWF_POCLINATENDEUPC.h"
using namespace std;

//------------------------------------------------------------------------------
// Implementa��o
//------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(POCLINATENDEU);

void implPOCLINATENDEU::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implPOCLINATENDEU::Execute()");

    string sCode = "04I0000";
    string mCode = "Execucao de POCLINATENDEU Concluida";

    cWF_POCLINATENDEU rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implPOCLINATENDEU::Execute()");
}

//------------------------------------------------------------------------------
// Construtor
//------------------------------------------------------------------------------

cWF_POCLINATENDEU::cWF_POCLINATENDEU(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef(dnode, xml_g);

     if ( user ) { User = user; }
}

//------------------------------------------------------------------------------
// Implementa o neg�cio ao qual o servi�o atende
// Caso de Uso: Cliente n�o atendeu liga��o de analista de reten��o
//------------------------------------------------------------------------------

void cWF_POCLINATENDEU::Executar() 
{
    cWF_Acao::Executar();

    if ( User.ToInt() == 0 )
    {
        SetMessage( "Usu�rio destino n�o informado", "N" );
        xml_g->closeTag();
        return;
    }

    getStatusUsuario(User.ToInt());

    if ( idStatusUsuario != 1 )
    {
        SetMessage( "Usu�rio destino indispon�vel para recepcionar processos", "N" );
        xml_g->closeTag();
        return;
    }

    if ( getAtendimentoUsuarioAtual() )
    { // se j� esta com um usu�rio diferente do atual, n�o permite continuar
        if ( User.ToInt() != idUsuarioAtendimento && inSPN.ToInt() == 0 )
        {
            SetMessage( "Processo j� esta sendo tratado por outro analista de reten��o.", "N" );
            xml_g->closeTag();
            return;
        }
    }

    getAtendimento();

    removeAtendimentoUsuarioAtual();

    if ( getGrupoTratamentoRetencaoPout() == false )
    {
        SetMessage( "Grupo de tratamento de reten��o de portout n�o especificado.", "N" );
        xml_g->closeTag();
        return;
    }

    removeAtendimentoGrupoAtual();

    inserirAndamento();

    inserirAndamentoObservacao();

    // processo n�o ir� ser exibido na fila de portout por um per�odo parametrizado
    // atualizarDataDelayFila();

    // Registro a a��o no historico de portabilidade
    gravarPessoaPortabilidadeHist("POCLINATENDEU");

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"POCLINATENDEU");
    // <== SM324--DPR--DEZ/2006--Cassio

    SetMessage("Registro do cliente n�o ter atendido foi executado","S");

    xml_g->closeTag();
}
