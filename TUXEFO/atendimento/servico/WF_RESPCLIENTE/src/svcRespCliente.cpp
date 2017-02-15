/**
 * @modulo  Atendimento
 * @usecase Atendimento
 * @remark  Registra ação de resposta ao cliente
 * @author  Charles Machado dos Santos
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/ 

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>

using namespace std;

#include "../../WF_ACAO/include/cWF_AcaoPC.h"
#include "../../WF_FECHAMENTO/include/cWFFechamento.h"

#include "../include/RespClientePC.h"
#include "../../../commons/msgPadrao.h"

//******************************************************************************************************************
// cRespCliente - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(RESPCLIENTE);

void implRESPCLIENTE::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implRESPCLIENTE::Execute()");
    TString retCode = "04I0000";
    TString retMesg = "Execucao de RESPCLIENTE Concluida";

    cRespCliente rc(dnode, xml_g, getUser());

    try
    {
        rc.Executar(dnode, xml_g); 
    }
    catch ( TuxBasicOraException *tboe )
    {
        retMesg = tboe->pMsg;

        delete tboe;

        xml_g->createTag("WFAcaoRetornoVO");
	        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
	        xml_g->addItem("acaoExecucao", "N");
	        xml_g->addItem("mensagem", retMesg.c_str());
	        xml_g->addItem("urlDestino","1");
        xml_g->closeTag();
    }
    catch( TuxException* pTux )
    {
        retMesg = pTux->getMessage();

        delete pTux;

        xml_g->createTag("WFAcaoRetornoVO");
	        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
	        xml_g->addItem("acaoExecucao", "N");
	        xml_g->addItem("mensagem", retMesg.c_str());
	        xml_g->addItem("urlDestino","1");
        xml_g->closeTag();
    }
    catch(...)
    {
        retMesg = "Excecao desconhecida";

        xml_g->createTag("WFAcaoRetornoVO");
	        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
	        xml_g->addItem("acaoExecucao", "N");
	        xml_g->addItem("mensagem", retMesg.c_str());
	        xml_g->addItem("urlDestino","1");
        xml_g->closeTag();
    }

    setStatusCode(retCode.c_str(),retMesg.c_str());
    
    ULOG_END("implRESPCLIENTE::Execute()");
}

cRespCliente::cRespCliente(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

///////////////////////////////////////////////////////////////////////////////////////////

void cRespCliente::Executar(DOMNode *entrada, XMLGen *saida) 
{
    ULOG_START("cRespCliente::Executar()");

    bool processoEmRetorno;

    string msg;

    Resposta resp;

    XMLGen saidaTemp;

    // --------------------------------------------------------------------------
    // Procedimentos padrões
    // --------------------------------------------------------------------------
    cWF_Acao::Executar();

    // --------------------------------------------------------------------------
    // Obtém o id de grupo atual do processo
    // --------------------------------------------------------------------------
    if ( idGrupoAtual == 0 )
    {
        getAtendimentoGrupoAtual();

        if ( idGrupoAtual == 0 )
	    {
		    SetMessage( "\nNGrupo atual do processo não localizado.","M"); 

	        xml_g->closeTag();

		    return;
	    }
    }

    // --------------------------------------------------------------------------
    // Obtém o id de usuário c.prévio atual do processo
    // --------------------------------------------------------------------------
    getAtendimentoUsuarioAtual();

    // --------------------------------------------------------------------------
    // Incrementa as tentativas de resposta ao cliente e os dados de grupo e
    // usuário atuais
    // --------------------------------------------------------------------------
    bool retorno = AtualizarRespCliente(idAtendimento.ToLong(),UsuarioAtual.ToInt()
                                       ,idUsuarioAtendimento,idGrupoAtual,dsMotivo.c_str()
                                       ,resp);
    if ( retorno )
    {
        // --------------------------------------------------------------------------
        // Registra a resposta ao cliente executada
        // --------------------------------------------------------------------------
        idAgrEstTPrFt = idAgrEstTPrAt;

	    inserirAndamento(idGrupoAtual);
	    alterarAndamentoAtual();
	    inserirAndamentoObservacao();
	    inserirAndamentoMotivo();

        // --------------------------------------------------------------------------
        // Dependendo do motivo do contato prévio ou se processo em retorno
        // fecha o processo
        // --------------------------------------------------------------------------
        processoEmRetorno = ehRetorno(idAgrEstTPrAt.ToInt());

        if ( processoEmRetorno 
           || !stricmp(dsMotivo.c_str(),"Contato OK - Problema já não existe") )
        {
            cWFFechamento cwffechamento(entrada,&saidaTemp,NULL);

            cwffechamento.Executar();

            // Se processo estava sob controle de um usuário originalmente
            // informa ao usuario sobre o fechamento do processo
            if ( resp.idPessoaUsuarioOrigem )
            {
                inserirAndamentoMensagemRC(resp.idPessoaUsuarioOrigem);
            }

            if ( processoEmRetorno )
            {
                msg = "Contato Prévio Concluido. Processo em retorno foi fechado.";
            }
            else
            {
                msg = "Contato Prévio Concluido. Processo foi fechado.";
            }
        }    // como o processo sempre retornara ao BKO este if não tem mais sentido
        else // if ( resp.nrTentativas == NR_TENTATIVAS_MAX || resp.inContatoPrevioRealizado == 1 )
        {
             msg = "Resposta ao Cliente Realizada.";    	
            // --------------------------------------------------------------------------
            // Se atingiu limite de tentativas ou contato finalizado,
            // devolve o processo à origem
            // --------------------------------------------------------------------------
            if ( resp.idPessoaUsuarioOrigem || resp.idGrupoOrigem )
            {

                getStatusDispUsuario(resp.idPessoaUsuarioOrigem);

                // se usuario ativo e disponível ...
                //if ( inDisponivelWF != 0 || idStatusUsuario == 1 )

                // Se usuario bko responsavel ainda ativo, devolve o processo para ele
                if ( idStatusUsuario == 1 )
                {   // existe o usuario com idusuario == 0 que e migracao um usuario especial    
                    if ( resp.idPessoaUsuarioOrigem == 0 )  
                    {  
                        removeAtendimentoUsuarioAtual();
                    } 
                    else
                {
                    alterarAtendimentoUsuarioAtual(resp.idPessoaUsuarioOrigem,false);
                }
                }
                else
                { // se usuario origem fora de alcance, apenas tira o processo do controle do analista CP
                    removeAtendimentoUsuarioAtual();
                }

                // devolve ao grupo de origem
                if ( resp.idGrupoOrigem )
                {
                    alterarAtendimentoGrupoAtual(resp.idGrupoOrigem);
                }

                if ( resp.inContatoPrevioRealizado == 1 )
                {
                    msg += "Contato prévio finalizado. ";
                }

                if ( resp.nrTentativas == NR_TENTATIVAS_MAX )
                {
                    msg += "Limite de tentativas atingido. ";
                }

                msg += "Processo retornado a origem.";
            }
            else
            {
                if ( resp.inContatoPrevioRealizado == 1 )
                {
                    msg += "Contato prévio finalizado. ";
                }

                if ( resp.nrTentativas == NR_TENTATIVAS_MAX )
                {
                    msg += "Limite de tentativas atingido. ";
                }

                msg += "??? ERRO: PROCESSO SEM ORIGEM??";
            }
        }
    }
    else
    {
        msg = "Processo não encontrado sob controle de contato prévio";
    }

    // --------------------------------------------------------------------------
    // SM324 - DPR - Monitoração de Persistência em Tabelas -- DEZ/2006 -- Cassio
    // --------------------------------------------------------------------------
    getAtendimento();
    registrarAcaoDPR(User.ToInt(),idContato,"RESPCLIENTE");

    // --------------------------------------------------------------------------
    // Fim
    // --------------------------------------------------------------------------
	SetMessage( (char*)msg.c_str(), "S" ); 

    xml_g->closeTag();

    ULOG_END("cRespCliente::Executar()");
}
