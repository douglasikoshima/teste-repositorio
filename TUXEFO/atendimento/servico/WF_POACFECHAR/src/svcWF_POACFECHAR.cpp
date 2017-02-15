/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:38 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
#include "../include/cWF_POACFECHARPC.h"
using namespace std;

#define MIN(a,b) (a) < (b) ? (a) : (b)

//------------------------------------------------------------------------------
// Implementação
//------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(POACFECHAR);

void implPOACFECHAR::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implPOACFECHAR::Execute()");

    string sCode = "04I0000";
    string mCode = "Execucao de POACFECHAR Concluida";

    cWF_POACFECHAR rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implPOACFECHAR::Execute()");
}

//------------------------------------------------------------------------------
// Construtor
//------------------------------------------------------------------------------

cWF_POACFECHAR::cWF_POACFECHAR(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef(dnode, xml_g);

     if ( user ) { User = user; }
}

//------------------------------------------------------------------------------
// Implementa o negócio ao qual o serviço atende
// Caso de Uso: Fechar processo de portout
//------------------------------------------------------------------------------

void cWF_POACFECHAR::Executar() 
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

    atualizarAtendimentoUsuarioAtual(UsuarioAtual.ToInt(),false);

    if ( getGrupoTratamentoRetencaoPout() == false )
    {
        SetMessage( "Grupo de tratamento de retenção de portout não especificado.", "N" );
        xml_g->closeTag();
        return;
    }

    alterarAtendimentoGrupoAtual();

    inserirAtendimentoFechamento();

    DecrementarPendentes(); // Processos pendentes sob um protocolo

    idFaseAtendimento = RETORNO;
    alterarAtendimento();

    inserirAndamento();

    inserirAndamentoObservacao();
    
    proCEnfileirarEfetivacaoRetencaoNoLegado(idAtendimento,User);

    DadosRetCons dadosRetCons;
    TString nrProtocoloAberto;
    TString statusText;
    
    if ( proCObterDadosAberturaProcesso(idAtendimento.ToLong()
                                       ,idAtendimentoProtocolo
                                       ,&dadosRetCons) == true )
    { // se retornou dados, abre processo
        XMLGen saidaRC;
        XMLGen xmlGen;

        xmlGen.createTag("AtendimentoVO");
            xmlGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
            xmlGen.addItem("idGrupoAbertura","123");
            xmlGen.addItem("idLinhaAtendimento",dadosRetCons.idLinhaTelefonica.c_str());
            xmlGen.addItem("inResponsavelAbertura",dadosRetCons.idTipoRelacionamento.c_str());
            xmlGen.addItem("nmContato",dadosRetCons.nmPessoa.c_str());
            xmlGen.addItem("nmFalandoCom","");
            xmlGen.addItem("nrTelefone",dadosRetCons.nrLinha.c_str());
            xmlGen.addItem("observacao",dadosRetCons.dsObservacao.c_str());
            xmlGen.addItem("tpOperacao","2");
            xmlGen.addItem("sgOperadoraSolicitante","");
            xmlGen.addItem("dtJanelaPortout","");
            xmlGen.addItem("tipoPortabilidade","");
            xmlGen.createTag("ProcedenciaVO");
                xmlGen.addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
                xmlGen.addItem("idProcedencia",dadosRetCons.idProcedencia.c_str());
            xmlGen.closeTag();
            xmlGen.createTag("CanalVO");
                xmlGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
                xmlGen.addItem("idCanal",dadosRetCons.idCanal.c_str());
            xmlGen.closeTag();
            xmlGen.createTag("Contas");
                xmlGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
                xmlGen.createTag("ContasVO");
                    xmlGen.addItem("idConta","");
                    xmlGen.createTag("LinhaVO");
                        xmlGen.addItem("idPessoaLinhaHistorico",dadosRetCons.idLinhaTelefonica.c_str());
                    xmlGen.closeTag();
                xmlGen.closeTag();
            xmlGen.closeTag();
            xmlGen.createTag("PessoaVO");
                xmlGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
                xmlGen.addItem("idPessoa",dadosRetCons.idPessoaDePara.c_str());
            xmlGen.closeTag();
            xmlGen.createTag("UsuarioLinhaVO");
                xmlGen.addProp("xmlns","cliente.fo.vivo.com.br/vo");
                xmlGen.addItem("idPessoa",dadosRetCons.idPessoaDePara.c_str());
            xmlGen.closeTag();
            xmlGen.createTag("ArvoreAtendimentoVO");
                xmlGen.addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
                xmlGen.addItem("idContato",dadosRetCons.idContato.c_str());
                xmlGen.createTag("CarterizacaoVO");
                    xmlGen.addItem("idTipoCarteira",idTipoCarteira);
                xmlGen.closeTag();
                xmlGen.createTag("SegmentacaoVO");
                    xmlGen.addItem("idSegmentacao",idSegmentacao);
                xmlGen.closeTag();
            xmlGen.closeTag();
        xmlGen.closeTag();

        //RegistrarContato(&xmlGen,statusText,nrProtocoloAberto);
    }

    // Registro a ação no historico de portabilidade
    gravarPessoaPortabilidadeHist("POACFECHAR");

    // processo não irá ser exibido na fila de portout por um período parametrizado
    atualizarDataDelayFila();

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"POACFECHAR");
    // <== SM324--DPR--DEZ/2006--Cassio

    if ( nrProtocoloAberto.Length() > 0 )
    {
        char msg[256];
        sprintf(msg,"Processo de portout fechado e aberto protocolo '%s' para efetivação junto ao Sistema Legado",nrProtocoloAberto.c_str());
        SetMessage(msg,"S");
    }
    else if ( statusText.Length() > 0 )
    {
        SetMessage(statusText.c_str(),"N");
    }
    else
    {
        SetMessage("Processo de portout fechado","S");
    }

    xml_g->closeTag();
}

bool cWF_POACFECHAR::RegistrarContato(XMLGen *pXMLGen,TString &statusText,TString &idAtendimentoProtocolo)
{
    ULOG_START("cWF_POACFECHAR::RegistrarContato()");

    TuxRemoteService tuxrc;
    TuxMessage tmIn;

    try 
    {
        tmIn.setService("REGCONTATO");
        tmIn.setUser(User.c_str());
        //tmIn.setSequence( m_stDados.sequence );
        tmIn.setMessageBody( pXMLGen );

        tuxrc.setServiceName("REGCONTATO");
        tuxrc.setInputMessage( &tmIn );

        if ( tuxrc.remoteCall() != TUXFWRET_OK )
        {
            statusText = "Erro na chamada ao REGCONTATO (registro de processo) ";
        }
        else
        {
            TuxMessage *outTm = tuxrc.getOutputMessage();

            char codigoRetorno = *(outTm->getStatusCode()+2);
            statusText = outTm->getStatusText();

            if ( codigoRetorno == 'E' )
            {
                ULOGE("REGCONTATO retornou status code =\"%c\"",codigoRetorno);
            }
            else
            {
                char *p = outTm->getMessageBody();
                char *p0 = strstr(p,"<nrProtocolo>");
                char *p1 = strstr(p,"</nrProtocolo>");

                if ( p0 && p1 )
                {
                    char nrProtocoloTmp[256];

                    p0 += strlen("<nrProtocolo>");
                    memcpy(nrProtocoloTmp,p0,MIN(p1-p0,sizeof(nrProtocoloTmp)-1));
                    nrProtocoloTmp[MIN(p1-p0,sizeof(nrProtocoloTmp)-1)]=0;
                    idAtendimentoProtocolo = nrProtocoloTmp;
                }
            }

            delete outTm;
            outTm = 0;
        }
    }
    catch (...)
    {
        ULOGE("cWF_POACFECHAR::RegistrarContato()");
        throw;
    }

    ULOG_END("cWF_POACFECHAR::RegistrarContato()");

    return true;
}
