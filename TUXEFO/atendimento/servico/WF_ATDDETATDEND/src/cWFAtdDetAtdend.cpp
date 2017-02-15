/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/10/05 22:32:17 $
 **/

#include "../include/cWFAtdDetAtdend.h"

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

cWFAtdDetAtend::cWFAtdDetAtend(DOMNode *dnode, XMLGen *xml_g)
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

bool cWFAtdDetAtend::executar(char **codErro,char **msgErro)
{
    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O código de 'idAtendimento' é obrigatório";

        return false;
    }

    saida->createTag("RWFAtendimentoVO");
        saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

		char idAtd[40];
		sprintf( idAtd, "%lu", m_stDados.idAtendimento );
		ULOG( "*** idAtd [%s]\n", idAtd );
		
		ULOG( "*** idAtendimento [%lu]\n", m_stDados.idAtendimento );
		
		saida->addItem("idAtendimento", idAtd );

		ObterDetalheAtd(saida);

		ULOG( "*** idAtendimento 2 [%lu]\n", m_stDados.idAtendimento );

		ObterQtdeAtdOrigem(saida);

		ULOG( "*** idAtendimento 3 [%lu]\n", m_stDados.idAtendimento );

		ObterAtdCtoConsultar(saida);

		ULOG( "*** idAtendimento 4 [%lu]\n", m_stDados.idAtendimento );

		ObterAtendPessoa(saida);

		ULOG( "*** idAtendimento 5 [%lu]\n", m_stDados.idAtendimento );

	saida->closeTag();
	
		ULOG( "*** idAtendimento 6 [%lu]\n", m_stDados.idAtendimento );
	
    return true;
}

void cWFAtdDetAtend::ObterAtdCtoConsultar(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetAtend::ObterAtdCtoConsultar()");

    cWFAtendimentoContato cwfatendimentocontato;
    AtendimentoContato atendimentocontato;

    cwfatendimentocontato.ObterAtendimentoContato(m_stDados.idAtendimento,&atendimentocontato);

    _montagem->addItem("nrTelefone", atendimentocontato.nrTelefoneContato);

    ULOG_END("cWFAtdDetAtend::ObterAtdCtoConsultar()");
}

void cWFAtdDetAtend::ObterQtdeAtdOrigem(XMLGen* _montagem)
{
    cWFAtendimentoOrigem cwfAtdOrigem;

    _montagem->addItem("nrAtendimentosOrigem", cwfAtdOrigem.ObterAtdOrigem(m_stDados.idAtendimento));
}

void cWFAtdDetAtend::ObterDetalheAtd(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetAtend::ObterDetalheAtd()");

    cWFAtendimento cwfAtendimento;
    DetalheAtendimento detalheatendimento;
    char c_idAtendimentoOrigem[256];
    memset( c_idAtendimentoOrigem, 0x0, sizeof(c_idAtendimentoOrigem) );

    if ( !cwfAtendimento.ObtemDetalheAtend(m_stDados.idAtendimento,&detalheatendimento, c_idAtendimentoOrigem ) )
    {
        ULOG("idAtendimento=%lu nao encontrado ",m_stDados.idAtendimento);

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

        /*
        if ( !detalheatendimento.idAtendimentoOrigem )
        {
            _montagem->addItem("idAtendimentoOrigem", "");
        }
        else
        {
            ULOG( "@@@ Imprimindo idAtendimentoOrigem [%ld]", detalheatendimento.idAtendimentoOrigem );
            _montagem->addItem( "idAtendimentoOrigem", detalheatendimento.idAtendimentoOrigem );
        }
        */

        if ( c_idAtendimentoOrigem[0] == 0x0 )
        {
            _montagem->addItem("idAtendimentoOrigem", "");
        }
        else
        {
            ULOG( "@@@ Imprimindo idAtendimentoOrigem [%s]", c_idAtendimentoOrigem );
            _montagem->addItem( "idAtendimentoOrigem", c_idAtendimentoOrigem );
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

    ULOG_END("cWFAtdDetAtend::ObterDetalheAtd()");
}

void cWFAtdDetAtend::ObterAtendPessoa(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetAtend::ObterAtendPessoa()");

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

    ULOG_END("cWFAtdDetAtend::ObterAtendPessoa()");
}

void cWFAtdDetAtend::carregaDados()
{
    ULOG_START("cWFAtdDetAtend::carregaDados()");

    char *p;

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

    ULOG("idAtendimento=%lu",m_stDados.idAtendimento);
    ULOG("idEstado=%d",m_stDados.idEstado);
    ULOG("idSubEstado=%d",m_stDados.idSubEstado);

    ULOG_END("cWFAtdDetAtend::carregaDados()");
}

