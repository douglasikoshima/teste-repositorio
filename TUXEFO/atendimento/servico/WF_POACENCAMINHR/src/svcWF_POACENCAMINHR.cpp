/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/12/10 14:12:01 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
#include "../include/cWF_POACENCAMINHRPC.h"
using namespace std;

//------------------------------------------------------------------------------
// Implementa��o
//------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(POACENCAMINHR);

void implPOACENCAMINHR::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implPOACENCAMINHR::Execute()");

    string sCode = "04I0000";
    string mCode = "Execucao de POACENCAMINHR Concluida";

    cWF_POACENCAMINHR rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implPOACENCAMINHR::Execute()");
}

//------------------------------------------------------------------------------
// Construtor
//------------------------------------------------------------------------------

cWF_POACENCAMINHR::cWF_POACENCAMINHR(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef(dnode, xml_g);

     if ( user ) { User = user; }
}

//------------------------------------------------------------------------------
// Implementa o neg�cio ao qual o servi�o atende
// Caso de Uso: Iniciar reten��o de portout
//------------------------------------------------------------------------------

void cWF_POACENCAMINHR::Executar() 
{
    cWF_Acao::Executar();

    getAtendimento();

    // if ( strcmp(szSgFluxoAtendimento,"MC1")==0 )
    // {
    //     SetMessage( "Processo 'Meu Cliente 1' n�o pode ser encaminhado.", "N" ); 
    //     xml_g->closeTag();
    //     return;
    // }

    if ( UsuarioAtual.ToInt() == 0 )
    {
        SetMessage( "Usu�rio destino n�o informado", "N" );
        xml_g->closeTag();
        return;
    }

    getStatusUsuario(UsuarioAtual.ToInt());

    if ( idStatusUsuario != 1 )
    {
        SetMessage( "Usu�rio destino indispon�vel para recepcionar processos", "N" );
        xml_g->closeTag();
        return;
    }

    if ( getAtendimentoUsuarioAtual() )
    { // se j� esta com um usu�rio, diferente do atual, n�o permite continuar
        if ( UsuarioAtual.ToInt() != idUsuarioAtendimento && inSPN.ToInt() == 0 )
        {
            SetMessage( "Processo j� esta sendo tratado por outro analista de reten��o.", "N" );
            xml_g->closeTag();
            return;
        }
    }

    alterarAtendimentoGrupoAtual();

    //getAtendimento();

    atualizarAtendimentoUsuarioAtual(UsuarioAtual.ToInt(),false);

    if ( getGrupoTratamentoRetencaoPout() == false )
    {
        SetMessage( "Grupo de tratamento de reten��o de portout n�o especificado.", "N" );
        xml_g->closeTag();
        return;
    }

    alterarAtendimentoGrupoAtual();

    inserirAndamento();

    inserirAndamentoObservacao();

    // processo n�o ir� ser exibido na fila de portout por um per�odo parametrizado
    atualizarDataDelayFila();

    // Registro a a��o no historico de portabilidade
    gravarPessoaPortabilidadeHist("POACENCAMINHR");

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"POACENCAMINHR");
    // <== SM324--DPR--DEZ/2006--Cassio

    SetMessage("Encaminhamento concluido para o processo de portout","S");

    xml_g->closeTag();
}
