/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdDetAtdendex.h"

#include "../../Andamento/include/cWFAndamento.h"
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoBaixa/include/cWFAtendimentoBaixa.h"
#include "../../AtendimentoBaixaAtual/include/cWFAtendimentoBaixaAtual.h"
#include "../../AtendimentoConta/include/cWFAtendimentoConta.h"
#include "../../AtendimentoContato/include/cWFAtendimentoContato.h"
#include "../../AtendimentoContatoComunic/include/cWFAtendimentoContatoComunic.h"
#include "../../AtendimentoFechamento/include/cWFAtendimentoFechamento.h"
#include "../../AtendimentoFrmCampoValor/include/cWFAtendimentoFrmCampoValor.h"
#include "../../AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"
#include "../../AtendimentoLinha/include/cWFAtendimentoLinha.h"
#include "../../AtendimentoNivel/include/cWFAtendimentoNivel.h"
#include "../../AtendimentoOrigem/include/cWFAtendimentoOrigem.h"
#include "../../AtendimentoPessoa/include/cWFAtendimentoPessoa.h"

#include "../../CondicaoAparicao/include/cWFCondicaoAparicao.h"
#include "../../Estado/include/cWFEstado.h"
#include "../../Fase/include/cWFFase.h"
#include "../../FluxoFase/include/cWFFluxoFase.h"

cWFAtdDetAtendex::cWFAtdDetAtendex(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    memset(&m_stAtdDetAtend,0,sizeof(m_stAtdDetAtend));
    memset(&m_vlAtdDetAtend,-1,sizeof(m_vlAtdDetAtend));

    memset(&m_stAtdBaixa,0,sizeof(m_stAtdBaixa));
    memset(&m_vlAtdBaixa,-1,sizeof(m_vlAtdBaixa));

    memset(&m_stGrupoAtendimento,0,sizeof(m_stGrupoAtendimento));
    memset(&m_vlGrupoAtendimento,-1,sizeof(m_vlGrupoAtendimento));

    carregaDados();
}

bool cWFAtdDetAtendex::executar(char **codErro,char **msgErro)
{
    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O código de 'idAtendimento' é obrigatório";

        return false;
    }

    bool inMigracao = ObterMigracao();

    saida->createTag("RWFAtendimentoVO");
        saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

		saida->addItem("idAtendimento", m_stDados.idAtendimento);

        if ( inMigracao == false )     // Pesquisa comum
        {
		ObterDetalheAtdEx(saida);
            ObterQtdeAtdOrigemEx(saida);
            ObterAtdCtoConsultarEx(saida);
            ObterAtendPessoaEx(saida);
        }
        else                               // Pesquisa migracao
        {
            // ObterDetalheAtdEx(saida);
            ObterDetalheAtdEx_Migracao(saida);

		ObterQtdeAtdOrigemEx(saida);

            // ObterAtdCtoConsultarEx(saida);
            ObterAtdCtoConsultarEx_Migracao(saida);

		ObterAtendPessoaEx(saida);
            //ObterAtendPessoaEx_Migracao(saida);
        }

	saida->closeTag();
	
    return true;
}

void cWFAtdDetAtendex::ObterAtdCtoConsultar(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetAtend::ObterAtdCtoConsultar()");

    cWFAtendimentoContato cwfatendimentocontato;
    AtendimentoContato atendimentocontato;

    cwfatendimentocontato.ObterAtendimentoContato(m_stDados.idAtendimento,&atendimentocontato);

    _montagem->addItem("nrTelefone", atendimentocontato.nrTelefoneContato);

    ULOG_END("cWFAtdDetAtend::ObterAtdCtoConsultar()");
}



void cWFAtdDetAtendex::ObterAtdCtoConsultarEx(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetAtend::ObterAtdCtoConsultarEx()");

    cWFAtendimentoContato cwfatendimentocontato;
    AtendimentoContato atendimentocontato;

    cwfatendimentocontato.ObterAtendimentoContatoEx(m_stDados.idAtendimento,&atendimentocontato);

    _montagem->addItem("nrTelefone", atendimentocontato.nrTelefoneContato);

    ULOG_END("cWFAtdDetAtend::ObterAtdCtoConsultarEx()");
}



void cWFAtdDetAtendex::ObterAtdCtoConsultarEx_Migracao(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetAtend::ObterAtdCtoConsultarEx()");

    cWFAtendimentoContato cwfatendimentocontato;
    AtendimentoContato atendimentocontato;

    cwfatendimentocontato.ObterAtendimentoContatoEx_Migracao(m_stDados.idAtendimento,&atendimentocontato);

    _montagem->addItem("nrTelefone", atendimentocontato.nrTelefoneContato);

    ULOG_END("cWFAtdDetAtend::ObterAtdCtoConsultarEx()");
}



void cWFAtdDetAtendex::ObterQtdeAtdOrigem(XMLGen* _montagem)
{
    cWFAtendimentoOrigem cwfAtdOrigem;

    _montagem->addItem("nrAtendimentosOrigem", cwfAtdOrigem.ObterAtdOrigem(m_stDados.idAtendimento));
}



void cWFAtdDetAtendex::ObterQtdeAtdOrigemEx(XMLGen* _montagem)
{
    cWFAtendimentoOrigem cwfAtdOrigem;

    _montagem->addItem("nrAtendimentosOrigem", cwfAtdOrigem.ObterAtdOrigemEx(m_stDados.idAtendimento));
}



void cWFAtdDetAtendex::ObterDetalheAtdEx(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetAtendex::ObterDetalheAtdEx()");

    cWFAtendimento cwfAtendimento;
    DetalheAtendimento detalheatendimento;

    if ( !cwfAtendimento.ObtemDetalheAtendEx(m_stDados.idAtendimento,&detalheatendimento) )
    {
        ULOG("idAtendimento=%d nao encontrado ",m_stDados.idAtendimento);

        _montagem->addItem("dtAbertura", "??/??/????");
        _montagem->addItem("dtParaFechamento", "??/??/????");
        _montagem->addItem("dsEstado", "??");
        _montagem->addItem("dsSubEstado", "??");
        _montagem->addItem("contaPesquisa", "??");
        _montagem->addItem("linhaPesquisa", "??");
        _montagem->addItem("qtInsistencia", "??");
        _montagem->addItem("idPessoa", "??");
        _montagem->addItem("idAtendimentoBaixaHistorico", "??");
        _montagem->addItem("idAtendimentoOrigem", "??");
        _montagem->addItem("dtFechamento", "??/??/????");
        _montagem->addItem("nrProtocolo", "??");
        _montagem->addItem("inPortout", "??");
    }
    else
    {
        _montagem->addItem("dtAbertura", detalheatendimento.dataAbertura);
        _montagem->addItem("dtParaFechamento", detalheatendimento.dataPrazoFinalInterno);
        _montagem->addItem("dsEstado", detalheatendimento.dsEstado);
        _montagem->addItem("dsSubEstado", detalheatendimento.dsSubEstado);
        _montagem->addItem("contaPesquisa", detalheatendimento.cdConta);
        _montagem->addItem("linhaPesquisa", detalheatendimento.nrLinha);
        _montagem->addItem("qtInsistencia", detalheatendimento.qtInsistencia);
        _montagem->addItem("idPessoa", detalheatendimento.idPessoa);
        _montagem->addItem("nrProtocolo", detalheatendimento.idAtendimentoProtocolo);

        if ( strcmp(detalheatendimento.sgTipoPortabilidade,"PORTOUT") == 0 )
        {
            _montagem->addItem("inPortout", "1");
        }
        else
        {
            _montagem->addItem("inPortout", "0");
        }

        if ( !detalheatendimento.idAtendimentoBaixaHistorico )
        {
            _montagem->addItem("idAtendimentoBaixaHistorico", "");
        }
        else
        {
            _montagem->addItem("idAtendimentoBaixaHistorico", detalheatendimento.idAtendimentoBaixaHistorico);
        }

        if ( !detalheatendimento.idAtendimentoOrigem )
        {
            _montagem->addItem("idAtendimentoOrigem", "");
        }
        else
        {
            _montagem->addItem("idAtendimentoOrigem", detalheatendimento.idAtendimentoOrigem);
        }

        _montagem->addItem("dtFechamento", detalheatendimento.dataFechamento);
    }

    _montagem->addItem("nmURLDados", "");
    _montagem->addItem("dtFimPausaAtendimento", "");
    _montagem->addItem("tabPausa", "");
    _montagem->addItem("tpOperacao", "");
    _montagem->addItem("nmCor", "");
    _montagem->addItem("dtSuspeito", "");
    _montagem->addItem("idAtendimentoSuspeito", "");
    _montagem->addItem("dtSolicitacaoCancelamento", "");
    //_montagem->addItem("nrTelefone", "");

    ULOG_END("cWFAtdDetAtendex::ObterDetalheAtdEx()");
}


void cWFAtdDetAtendex::ObterDetalheAtdEx_Migracao(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetAtendex::ObterDetalheAtdEx_Migracao()");

    cWFAtendimento cwfAtendimento;
    DetalheAtendimento detalheatendimento;

    //if ( !cwfAtendimento.ObtemDetalheAtendEx(m_stDados.idAtendimento,&detalheatendimento) )
    if ( !cwfAtendimento.ObtemDetalheAtendEx_Migracao(m_stDados.idAtendimento,&detalheatendimento) )
    {
        ULOG("idAtendimento=%d nao encontrado ",m_stDados.idAtendimento);

        _montagem->addItem("dtAbertura", "??/??/????");
        _montagem->addItem("dtParaFechamento", "??/??/????");
        _montagem->addItem("dsEstado", "??");
        _montagem->addItem("dsSubEstado", "??");
        _montagem->addItem("contaPesquisa", "??");
        _montagem->addItem("linhaPesquisa", "??");
        _montagem->addItem("qtInsistencia", "??");
        _montagem->addItem("idPessoa", "??");
        _montagem->addItem("idAtendimentoBaixaHistorico", "??");
        _montagem->addItem("idAtendimentoOrigem", "??");
        _montagem->addItem("dtFechamento", "??/??/????");
        _montagem->addItem("nrProtocolo", "??");
        _montagem->addItem("inPortout", "??");
    }
    else
    {
        _montagem->addItem("dtAbertura", detalheatendimento.dataAbertura);
        _montagem->addItem("dtParaFechamento", detalheatendimento.dataPrazoFinalInterno);
        _montagem->addItem("dsEstado", detalheatendimento.dsEstado);
        _montagem->addItem("dsSubEstado", detalheatendimento.dsSubEstado);
        _montagem->addItem("contaPesquisa", detalheatendimento.cdConta);
        _montagem->addItem("linhaPesquisa", detalheatendimento.nrLinha);
        _montagem->addItem("qtInsistencia", detalheatendimento.qtInsistencia);
        _montagem->addItem("idPessoa", detalheatendimento.idPessoa);
        _montagem->addItem("nrProtocolo", detalheatendimento.idAtendimentoProtocolo);

        if ( strcmp(detalheatendimento.sgTipoPortabilidade,"PORTOUT") == 0 )
        {
            _montagem->addItem("inPortout", "1");
        }
        else
        {
            _montagem->addItem("inPortout", "0");
        }

        if ( !detalheatendimento.idAtendimentoBaixaHistorico )
        {
            _montagem->addItem("idAtendimentoBaixaHistorico", "");
        }
        else
        {
            _montagem->addItem("idAtendimentoBaixaHistorico", detalheatendimento.idAtendimentoBaixaHistorico);
        }

        if ( !detalheatendimento.idAtendimentoOrigem )
        {
            _montagem->addItem("idAtendimentoOrigem", "");
        }
        else
        {
            _montagem->addItem("idAtendimentoOrigem", detalheatendimento.idAtendimentoOrigem);
        }

        _montagem->addItem("dtFechamento", detalheatendimento.dataFechamento);
    }

    _montagem->addItem("nmURLDados", "");
    _montagem->addItem("dtFimPausaAtendimento", "");
    _montagem->addItem("tabPausa", "");
    _montagem->addItem("tpOperacao", "");
    _montagem->addItem("nmCor", "");
    _montagem->addItem("dtSuspeito", "");
    _montagem->addItem("idAtendimentoSuspeito", "");
    _montagem->addItem("dtSolicitacaoCancelamento", "");
    //_montagem->addItem("nrTelefone", "");

    ULOG_END("cWFAtdDetAtendex::ObterDetalheAtdEx_Migracao()");
}


void cWFAtdDetAtendex::ObterAtendPessoa(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetAtendex::ObterAtendPessoa()");

    cWFAtendimentoPessoa cwfatendimentopessoa;
    int idTipoRelacionamento = 
            cwfatendimentopessoa.ObtemIdTipoRelacionamento(m_stDados.idAtendimento);

    if ( idTipoRelacionamento != -1 )
    {
        _montagem->addItem("inResponsavelAbertura", idTipoRelacionamento);
    }
    else
    {
        _montagem->addItem("inResponsavelAbertura", "");
    }

    ULOG_END("cWFAtdDetAtendex::ObterAtendPessoa()");
}



void cWFAtdDetAtendex::ObterAtendPessoaEx(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetAtendex::ObterAtendPessoaEx()");

    cWFAtendimentoPessoa cwfatendimentopessoa;
    int idTipoRelacionamento = 
            cwfatendimentopessoa.ObtemIdTipoRelacionamentoEx(m_stDados.idAtendimento);

    if ( idTipoRelacionamento != -1 )
    {
        _montagem->addItem("inResponsavelAbertura", idTipoRelacionamento);
    }
    else
    {
        _montagem->addItem("inResponsavelAbertura", "");
    }

    ULOG_END("cWFAtdDetAtendex::ObterAtendPessoaEx()");
}



bool cWFAtdDetAtendex::ObterMigracao()
{
    return inMigracao;
}


void cWFAtdDetAtendex::carregaDados()
{
    ULOG_START("cWFAtdDetAtendex::carregaDados()");

    char *p;

    inMigracao = false;
    
    if (p=tx.walkTree( entrada, "inHistoricoMG", 0 ),p) 
    {
        if ( strcmp( p,"true" ) == 0 )
        {
            inMigracao = true;
        }
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idEstado", 0 ),p) 
    {
        m_stDados.idEstado = atoi(p);
        m_vlDados.idEstado = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idSubEstado", 0 ),p) 
    {
        m_stDados.idSubEstado = atoi(p);
        m_vlDados.idSubEstado = 1;
        XMLString::release(&p);
    }

    ULOG("idAtendimento=%d",m_stDados.idAtendimento);
    ULOG("idEstado=%d",m_stDados.idEstado);
    ULOG("idSubEstado=%d",m_stDados.idSubEstado);

    ULOG_END("cWFAtdDetAtendex::carregaDados()");
}

