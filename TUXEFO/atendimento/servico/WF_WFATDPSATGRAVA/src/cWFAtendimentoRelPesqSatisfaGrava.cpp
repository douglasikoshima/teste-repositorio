/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:33 $
 **/

#include "../../AtendimentoPesquisaSatisfa/include/cWFAtendPesquisaSatisfa.h"
#include "../../AtendimentoPesquisaAtual/include/cWFAtendimentoPesquisaAtual.h"
#include "../../AtendimentoPesquisaResp/include/cWFAtendimentoPesquisaResp.h"
#include "../../WF_ACAO/include/cWF_AcaoPC.h"
//#include "../../AtendimentoTeste/include/cWFAtendimentoTeste.h"
#include "../../../commons/definesAtendimento.h"
#include "../../FluxoFase/include/cWFFluxoFase.h"

#include "../include/stWFAtendimentoRelPesqSatisfaGrava.h"
#include "../include/cWFAtendimentoRelPesqSatisfaGrava.h"
#include "../../../commons/msgPadrao.h"


cWFAtRelPqSatGrava::cWFAtRelPqSatGrava(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     User = user ? user : "1";
}

void cWFAtRelPqSatGrava::Executar() 
{
    try
    {
        // Faz carga dos dados do xml de entrada
	    InitPesquisa();

        // Número do processo é obrigatório
        if ( idAtendimento.ToLong() == 0 )
        {
            throw new TuxException("00E0002","Número do processo não fornecido.");
        }

        if (idAtividade.ToInt() > 0)
        {
		    getAtendimento();
		    getAtendimentoGrupoAtual();

		    if (getEstadoFuturo() > 0)
		    {
			    incluirAtendimentoTeste();
			    IncluiTesteResposta();
			    inserirAndamento();
			    inserirAndamentoObservacao();
                registrarAcaoDPR(User.ToInt(),idContato,"WFATDPSATGRAVA");

			    SetMessage( "Testes inclusos com sucesso.", "S" ); 
		    }
		    else
            {
			    SetMessage( "Operação não permitida para o processo.", "N" );
            }
        }
        else
        {
		    IncluiPesquisa();

		    if (ObterPesquisaAtendimentoAtual() > 0)
            {
                AlteraPesquisaAtendimentoAtual();
            }
		    else
            {
                IncluiPesquisaAtendimentoAtual();
            }

		    IncluiPesquisaResposta();

		    // Retorna processamento
		    SetMessage( "Pesquisa de satisfação concluida.", "S", "2" );
        }
    }
    catch ( TuxBasicOraException *tboe )
    {
        ULOGE("Erro Oracle %d:%s",tboe->eCode,tboe->pMsg);
		SetMessage( "Erro de persistência de dados.", "M", "2" );

        throw;
    }
    catch( TuxException* pTux )
    {
        ULOGE("%s",pTux->getMessage());
		SetMessage( pTux->getMessage(), "M", "2" );

        throw;
    }
    catch(...)
    {
        ULOGE("Erro desconhecido");
		SetMessage( "Erro desconhecido.", "M", "2" );

        throw;
    }
}

//------------------------------------------------------------------------------------------------------------------
// Obtendo Grupo Atual
//------------------------------------------------------------------------------------------------------------------
long cWFAtRelPqSatGrava::ObterPesquisaAtendimentoAtual()
{
    ULOG_START("cWFAtRelPqSatGrava::ObterPesquisaAtendimentoAtual()");

    cWFAtendimentoPesquisaAtual AtdPesquisaAtual;

	AtdPesquisaAtual.ObterPesquisaAtendimentoAtual(idAtendimento.ToLong(),&idPesquisaAtendimentoAtual);

    ULOG("idPesquisaAtendimentoAtual: %ld", idPesquisaAtendimentoAtual);

    ULOG_END("cWFAtRelPqSatGrava::ObterPesquisaAtendimentoAtual()");

    return idPesquisaAtendimentoAtual;
}

//------------------------------------------------------------------------------------------------------------------
// Insercao de Pesquisa Atendimento Atual
//------------------------------------------------------------------------------------------------------------------
void cWFAtRelPqSatGrava::IncluiPesquisaAtendimentoAtual()
{
    ULOG_START("cWFAtRelPqSatGrava::IncluiPesquisaAtendimentoAtual()");

    st_AtendimentoPesquisaAtual m_stDados;
    st_vlAtendimentoPesquisaAtual m_vlDados;
    XMLGen xmlObtemPsqAt;

    memset(&m_stDados, 0, sizeof(m_stDados));
    memset(&m_vlDados,-1, sizeof(m_vlDados));

    m_stDados.idAtendimento=idAtendimento.ToLong();
    m_vlDados.idAtendimento=1;
    m_stDados.idAtendimentoPesquisaSatisfa=idPesquisaAtendimento;
    m_vlDados.idAtendimentoPesquisaSatisfa=1;
    m_stDados.idPessoaUsuario = User.ToInt();
    m_vlDados.idPessoaUsuario = 1;

    cWFAtendimentoPesquisaAtual AtdPesquisaAtual(&m_stDados, &m_vlDados, &xmlObtemPsqAt);

    AtdPesquisaAtual.incluir();

    ULOG_END("cWFAtRelPqSatGrava::IncluiPesquisaAtendimentoAtual()");
}

//------------------------------------------------------------------------------------------------------------
// Alteracao de Pesquisa Atendimento Atual
//------------------------------------------------------------------------------------------------------------
void cWFAtRelPqSatGrava::AlteraPesquisaAtendimentoAtual()
{
    ULOG_START("cWFAtRelPqSatGrava::AlteraPesquisaAtendimentoAtual()");

    st_AtendimentoPesquisaAtual m_stDados;
    st_vlAtendimentoPesquisaAtual m_vlDados;

    memset(&m_stDados, 0, sizeof(m_stDados));
    memset(&m_vlDados,-1, sizeof(m_vlDados));
    
    m_stDados.idAtendimento=idAtendimento.ToLong();
    m_vlDados.idAtendimento=1;

    m_stDados.idAtendimentoPesquisaSatisfa=idPesquisaAtendimento;
    m_vlDados.idAtendimentoPesquisaSatisfa=1;

    m_stDados.idAtendimentoPesquisaSatisfaAtual=idPesquisaAtendimentoAtual;
    m_vlDados.idAtendimentoPesquisaSatisfaAtual=1;

    m_stDados.idPessoaUsuario=User.ToInt();
    m_vlDados.idPessoaUsuario=1;

    cWFAtendimentoPesquisaAtual AtdPesquisaAtual;

    AtdPesquisaAtual.alterar(&m_stDados, &m_vlDados);

    ULOG_END("cWFAtRelPqSatGrava::AlteraPesquisaAtendimentoAtual()");
}


//------------------------------------------------------------------------------------------------------------------    
// Inclusao de Pesquisa Resposta
//------------------------------------------------------------------------------------------------------------------    
void cWFAtRelPqSatGrava::IncluiPesquisaResposta()
{
    ULOG_START("cWFAtRelPqSatGrava::IncluiPesquisaResposta()");

    char *p;
    cWFAtendimentoPesquisaResp AtdPesquisaResp;
    DOMNode *registro;
    int numNode;
    int contador = 0;
    st_AtendimentoPesquisaResp m_stDados;
    st_vlAtendimentoPesquisaResp m_vlDados;
    XMLGen xmlObtemPsqResp;

    memset(&m_stDados, 0, sizeof(m_stDados));
    memset(&m_vlDados,-1, sizeof(m_vlDados));
 
    m_stDados.idAtendimentoPesquisaSatisfa = idPesquisaAtendimento;
    m_vlDados.idAtendimentoPesquisaSatisfa = 1;
    m_stDados.idPessoaUsuario = User.ToInt();
    m_vlDados.idPessoaUsuario = 1;

    m_vlDados.vlResposta = 1;

    while ( registro = walkDOM(dnode, "AdmPerguntaVO", contador++) )
    {
        if ( p=walkTree(registro,"idPergunta",0),p )
        {
            m_stDados.idPergunta = atoi(p);
            m_vlDados.idPergunta = 1;
            XMLString::release(&p);

            numNode = 0;
            while ( p=walkTree(registro, "vlResposta", numNode++),p )
            {
                SAFE_STRNCPY(m_stDados.vlResposta,p);
                XMLString::release(&p);

                AtdPesquisaResp.incluir(&m_stDados, &m_vlDados);
            }
        }
        else
        {
            ULOGW("idPergunta não informado, contador=%d",contador);
        }
    }

    ULOG_END("cWFAtRelPqSatGrava::IncluiPesquisaResposta()");
}


//------------------------------------------------------------------------------------------------------------------    
// Inclusao do Andamento 
//------------------------------------------------------------------------------------------------------------------    
void cWFAtRelPqSatGrava::IncluiPesquisa()
{
    ULOG_START("cWFAtRelPqSatGrava::IncluiPesquisa()");
    char *p;

    struct st_AtendimentoPesquisaSatisfa dados;
    struct st_vlAtendimentoPesquisaSatisfa status;

    memset (&dados, 0, sizeof(st_AtendimentoPesquisaSatisfa)); 
    memset (&status,-1,sizeof(st_vlAtendimentoPesquisaSatisfa));

    SAFE_STRNCPY( dados.dtPesquisa, sSysDate.c_str() ); 

    dados.idAtendimento = idAtendimento.ToLong();
    dados.idPessoaUsuario = User.ToInt();

    status.idAtendimento = 1;
    status.idPessoaUsuario = 1;
    status.dtPesquisa = 1;

	if (p = walkTree( dnode, "observacao", 0 ), p ) 
	{
		SAFE_STRNCPY(dados.observacao, p );
		status.observacao = 1;
		XMLString::release(&p);
	}

	if (p = walkTree( dnode, "vlNota", 0 ), p ) 
	{
		dados.vlNota = atoi( p );
		status.vlNota = 1;
		XMLString::release(&p);
	}

    cWFAtendimentoPesquisaSatisfa cwfAtdPesquisa;

    cwfAtdPesquisa.incluir(&dados,&status);

    // Salva a seqüênce gerada para a inclusão
    idPesquisaAtendimento = dados.idAtendimentoPesquisaSatisfa;

    ULOG("idPesquisaAtendimento=%d",idPesquisaAtendimento);

    ULOG_END("cWFAtRelPqSatGrava::IncluiPesquisa()");

    return;
}

//------------------------------------------------------------------------------------------------------------------    
// Obtencao do Estado Futuro
//------------------------------------------------------------------------------------------------------------------    
int cWFAtRelPqSatGrava::getEstadoFuturo()
{
    ULOG_START("cWFAtRelPqSatGrava::getEstadoFuturo()");
    char *p;

	int idEstadoFuturo = 0;

    st_FluxoFase dadosFluxoFase;
    st_vlFluxoFase statusFluxoFase;

    XMLGen xmlObtemFluxo;

    memset(&dadosFluxoFase,0,sizeof(dadosFluxoFase));
    memset(&statusFluxoFase,-1,sizeof(statusFluxoFase));

	dadosFluxoFase.idAtividade = idAtividade.ToInt();;
    dadosFluxoFase.idContato = idContato;
    dadosFluxoFase.idPessoaUsuario =  User.ToInt();
    dadosFluxoFase.idAgrupamentoEstadoTpProc = idAgrEstadoTProcTestes;

	ULOG("idAtividade: %d", dadosFluxoFase.idAtividade);
	ULOG("idContato: %d", dadosFluxoFase.idContato);
	ULOG("idPessoaUsuario: %d", dadosFluxoFase.idPessoaUsuario);
	ULOG("idAgrupamentoEstadoTpProc: %d", dadosFluxoFase.idAgrupamentoEstadoTpProc);

	statusFluxoFase.idAtividade = 1;
    statusFluxoFase.idContato = 1;
    statusFluxoFase.idPessoaUsuario = 1;
    statusFluxoFase.idAgrupamentoEstadoTpProc = 1;

    cWFFluxoFase cwfFluxoFase;
	
	cwfFluxoFase.ObtemWFFluxo(&dadosFluxoFase,&statusFluxoFase,&xmlObtemFluxo) ;
    
    if (wta.walkTreeXMLGen(&xmlObtemFluxo, "idAgEstTpProcFt", &p, 0 )==0)
    {
       idEstadoFuturo=atoi(p);
       XMLString::release(&p);         
    }

	idAgrEstTPrFt = idEstadoFuturo;
    ULOG_END("cWFAtRelPqSatGrava::getEstadoFuturo()");
    return idEstadoFuturo;
}

//------------------------------------------------------------------------------------------------------------------    
// Inclusao do Teste 
//------------------------------------------------------------------------------------------------------------------    
// void cWFAtRelPqSatGrava::IncluiTeste()
// {
//     ULOG_START("cWFAtRelPqSatGrava::IncluiTeste()");
//     char *p;
// 
//     struct st_AtendimentoTeste dados;
//     struct st_vlAtendimentoTeste status;
// 
//     memset (&dados, 0, sizeof(st_AtendimentoTeste)); 
//     memset (&status,-1,sizeof(st_vlAtendimentoTeste));
// 
//     SAFE_STRNCPY( dados.dtTeste, sSysDate.c_str() ); 
//     SAFE_STRNCPY( dados.dtUltimaAlteracao, sSysDate.c_str() ); 
//     SAFE_STRNCPY( dados.dsTeste, dsObservacao.c_str() ); 
//     dados.idAtendimento = idAtendimento.();
//     dados.idPessoaUsuario = User.ToInt();
// 
//     status.idAtendimento = 1;
//     status.idPessoaUsuario = 1;
//     status.dtTeste = 1;
//     status.dtUltimaAlteracao = 1;
//     status.dsTeste = 1;
// 
//     cWFAtendimentoTeste cwfAtdTeste;  
//     
//     idAtendimentoTeste =
//         cwfAtdTeste.incluir(&dados,&status,&xmlDpr );
// 
//     ULOG_END("cWFAtRelPqSatGrava::IncluiTeste()");
// }

//------------------------------------------------------------------------------------------------------------------    
// Inclusao das Respostas do Teste 
//------------------------------------------------------------------------------------------------------------------    
void cWFAtRelPqSatGrava::IncluiTesteResposta()
{
    ULOG_START("cWFAtRelPqSatGrava::IncluiTesteResposta()");

    DOMNode *registro;
    char *p;
    st_AtendimentoTeste dados;
    st_vlAtendimentoTeste status;
    XMLGen xmlObtemPsqResp;

    memset(&dados, 0, sizeof(dados));
    memset(&status,-1, sizeof(status));

    cWFAtendimentoTeste cwfAtdTeste;  
 
    dados.idAtendimentoTeste=idAtendimentoTeste;
    status.idAtendimentoTeste=1;

    SAFE_STRNCPY( dados.dtUltimaAlteracao, sSysDate.c_str() ); 
    status.dtUltimaAlteracao = 1;

    dados.idPessoaUsuario = User.ToInt();
    status.idPessoaUsuario = 1;

    ULOG("idAtendimentoTeste=%lu", dados.idAtendimentoTeste);

    int contador = 0;
    while (registro = walkDOM( dnode, "AtendimentoWorkflowTesteVO", contador++))
    {
        if ( p=walkTree(registro, "idTeste", 0),p )
        {
            dados.idTeste = strtoul(p, NULL, 0);
            status.idTeste=1;
            XMLString::release(&p);
		    ULOG("idTeste=%d", dados.idTeste);
        }
        else
        {
            dados.idTeste = 0;
            status.idTeste = -1;
		    ULOG("idTeste nao informado");
        }

		if ( p=walkTree(registro, "dsResposta", 0),p )
        {
            SAFE_STRNCPY(dados.dsResposta,p);
            status.dsResposta=1;
            XMLString::release(&p);
        }
        else
        {
            dados.dsResposta[0] = 0;
            status.dsResposta = -1;
        }
   
        cwfAtdTeste.incluirTesteResposta(&dados, &status, &xmlObtemPsqResp);
    }

    ULOG_END("cWFAtRelPqSatGrava::IncluiTesteResposta()");
}
