/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:37 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
#include "../include/cWF_POACINIRETEPC.h"
using namespace std;

//------------------------------------------------------------------------------
// Implementação
//------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(POACINIRETE);

void implPOACINIRETE::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implPOACINIRETE::Execute()");

    string sCode = "04I0000";
    string mCode = "Execucao de POACINIRETE Concluida";

    cWF_POACINIRETE rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implPOACINIRETE::Execute()");
}

//------------------------------------------------------------------------------
// Construtor
//------------------------------------------------------------------------------

cWF_POACINIRETE::cWF_POACINIRETE(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef(dnode, xml_g);

     if ( user ) { User = user; }
}

//------------------------------------------------------------------------------
// Implementa o negócio ao qual o serviço atende
// Caso de Uso: Iniciar retenção de portout
//------------------------------------------------------------------------------

void cWF_POACINIRETE::Executar() 
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
    { // se já esta com um usuário diferente do atual, não permite continuar
        if ( User.ToInt() != idUsuarioAtendimento && inSPN.ToInt() == 0 )
        {
            SetMessage( "Processo já esta sendo tratado por outro analista de retenção.", "N" );
            xml_g->closeTag();
            return;
        }
    }

    if ( getGrupoTratamentoRetencaoPout() == false )
    {
        SetMessage( "Grupo de tratamento de retenção de portout não especificado.", "N" );
        xml_g->closeTag();
        return;
    }

    // Aplica a mesma regra de INICIAR RETENCAO a todos os 
    // atendimentos que estejam associados a este incluindo este
    if ( iniciarRetencaoAtendimentosAgrupados() )
    { // se rodou com sucesso, finaliza o iniciar retenção
        getAtendimento();

        // Registro a ação no historico de portabilidade
        gravarPessoaPortabilidadeHist("POACINIRETE");

        // processo não irá ser exibido na fila de portout por um período parametrizado
        atualizarDataDelayFila();

        // ==> SM324--DPR--DEZ/2006--Cassio
        registrarAcaoDPR(User.ToInt(),idContato,"POACINIRETE");
        // <== SM324--DPR--DEZ/2006--Cassio

        SetMessage("Retenção iniciada para o processo de portout","S");
    }

    xml_g->closeTag();
}
