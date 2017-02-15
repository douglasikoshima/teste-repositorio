/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:54 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
#include "../../../commons/definesAtendimento.h"
#include "../include/cWF_POALTDADOSPROPC.h"
using namespace std;

//------------------------------------------------------------------------------
// Implementação
//------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(POALTDADOSPRO);

void implPOALTDADOSPRO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implPOALTDADOSPRO::Execute()");

    string sCode = "04I0000";
    string mCode = "Execucao de POALTDADOSPRO Concluida";

    cWF_POALTDADOSPRO rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implPOALTDADOSPRO::Execute()");
}

//------------------------------------------------------------------------------
// Construtor
//------------------------------------------------------------------------------

cWF_POALTDADOSPRO::cWF_POALTDADOSPRO(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef(dnode, xml_g);

     if ( user ) { User = user; }
}

//------------------------------------------------------------------------------
// Implementa o negócio ao qual o serviço atende
// Caso de Uso: Atende solicitação de alteração de dados de processos vinda
//              do SPN.
//------------------------------------------------------------------------------

void cWF_POALTDADOSPRO::Executar() 
{
    cWF_Acao::Executar();

    bool trocouAlgumaCoisa = false;

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
    //
    //==========================================================================
    // DADOS COMUNS A TODAS AS AÇÕES EXECUTADAS POR ESTE MÓDULO
    //
    if ( getGrupoTratamentoRetencaoPout() == false )
    {
        SetMessage( "Grupo de tratamento de retenção de portout não especificado.", "N" );
        xml_g->closeTag();
        return;
    }

    int idProcedenciaNovo = idProcedencia;

    DetalheAtendimento detalheatendimento;
    
    getAtendimento(&detalheatendimento);

    idAgrEstTPrAt = idAgrEstTPrFt = detalheatendimento.idAgrEstadoTProc;
    //
    //==========================================================================
    // EXECUTA COMO SE FOSSE UM 'INSERIR COMENTÁRIO'
    //
    // Se além da alteração do dado também foi enviada uma observação
    // a mesma é gravada em uma ação em separado.
    if ( dsObservacao.Lenght() > 0 )
    {
        int idAtividadeAnt = idAtividade.ToInt(); // Salva antes de alterar...
        idAtividade = 24; // INSERIR COMENTÁRIO
        idMotivo = 200; // Motivo Automático

        inserirAndamento();
        inserirAndamentoObservacao();
        inserirAndamentoMotivo();

        idAtividade = idAtividadeAnt; // Volta valor original.
    }
    //
    //==========================================================================
    // ALTERAÇÃO DE DADOS DO PROCESSO
    //

    dsObservacao.Clear();
    char buffer[512];

    if ( dtJanelaPortout.Lenght() > 0 )
    { // esta alterando a data da janela de portout?
        if ( strcmp(detalheatendimento.dtJanelaPortout,dtJanelaPortout.c_str()) )
        {
            if ( dsObservacao.Lenght() == 0 ) { dsObservacao = "SISTEMA EXTERNO TROCOU "; }
            else { dsObservacao += ", "; }
            sprintf(buffer,"DATA DE JANELA DE '%s' PARA '%s' "
                        ,detalheatendimento.dtJanelaPortout,dtJanelaPortout.c_str());
            dsObservacao += buffer;

            trocouAlgumaCoisa = true;
        }
    }

    if ( sgOperadoraSolicitante.Lenght() > 0 )
    { // esta alterando o código da operadora solicitante?
        if ( strcmp(detalheatendimento.sgOperadoraSolicitante,sgOperadoraSolicitante.c_str()) )
        {
            if ( dsObservacao.Lenght() == 0 ) { dsObservacao = "SISTEMA EXTERNO TROCOU "; }
            else { dsObservacao += ", "; }
            sprintf(buffer,"OPERADORA SOLICITANTE DE '%s' PARA '%s' "
                   ,detalheatendimento.sgOperadoraSolicitante
                   ,sgOperadoraSolicitante.c_str());
            dsObservacao += buffer;

            trocouAlgumaCoisa = true;
        }
    }

    if ( idProcedenciaNovo > 0 )
    { // esta alterando o código da procedência?
        if ( idProcedencia != idProcedenciaNovo )
        {
            if ( dsObservacao.Lenght() == 0 ) { dsObservacao = "SISTEMA EXTERNO TROCOU "; }
            else { dsObservacao += ", "; }
            sprintf(buffer,"PROCEDÊNCIA DE %d PARA %d",idProcedencia,idProcedenciaNovo);
            dsObservacao += buffer;
            idProcedencia = idProcedenciaNovo;

            trocouAlgumaCoisa = true;
        }
    }

    if ( trocouAlgumaCoisa )
    { // algum dos dados permitidos foi realmente alterado?

        alterarAtendimento();
        inserirAndamento();
        inserirAndamentoObservacao();

        idMotivo = 300; // Alteração Automática
        inserirAndamentoMotivo();

        // processo não irá ser exibido na fila de portout por um período parametrizado
        // atualizarDataDelayFila();

        // Registro a ação no historico de portabilidade
        gravarPessoaPortabilidadeHist("POALTDADOSPRO");

        // ==> SM324--DPR--DEZ/2006--Cassio
        registrarAcaoDPR(User.ToInt(),idContato,"POALTDADOSPRO");
        // <== SM324--DPR--DEZ/2006--Cassio

        SetMessage("Dados do processo foram alterados","S");
    }
    else
    {
        SetMessage("Nenhum dado novo enviado para alteração, dados ignorados","S");
    }

    xml_g->closeTag();
}
