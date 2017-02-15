/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/07/23 20:16:41 $
 **/

#include "../include/cWFAtendimentoRelPesqSatisfa.h"
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoPessoa/include/cWFAtendimentoPessoa.h"

//extern int proCGetUfOperadora( int sIdAtendimento);
extern void proCGetQuestionario( int sIdContato, int sIdTipoPessoa, int sIdUfOperadora, XMLGen* saida );

cWFAtdRelPSS::cWFAtdRelPSS(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;
}

void cWFAtdRelPSS::Executar()
{
    carregarDados();

    saida->createTag("AdmSatisfacaoContainerVO");
        saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo");

    ObterDadosAtd();

    ObterDadosQuestionario();

    saida->closeTag();
}

void cWFAtdRelPSS::ObterDadosAtd()
{
    ULOG_START( "cWFAtdRelPSS::ObterDadosAtd()" );

    cWFAtendimento cwfAtendimento;
    cWFAtendimentoPessoa cwfAtendimentoPessoa;
    DetalheAtendimento detalheAtendimento;
    AtendimentoPessoa atendimentoPessoa;

    if ( cwfAtendimento.ObtemDetalheAtend(m_stDados.idAtendimento,&detalheAtendimento) )
    {
        m_stDados.idContato = detalheAtendimento.idContato;
        m_vlDados.idContato = 1;

        m_stDados.idUfOperadora = detalheAtendimento.idUFOperadora;
        m_vlDados.idUfOperadora = 1;

        ULOG( "Pesquisa atendimento [%ld]",m_stDados.idAtendimento );
        if ( cwfAtendimentoPessoa.ObtemAtendPessoa(m_stDados.idAtendimento,&atendimentoPessoa) )
        {
            m_stDados.idTipoPessoa = atendimentoPessoa.idTipoPessoa;
            m_vlDados.idTipoPessoa = 1;
        }
        else
        {
            ULOGW( "Dados da pessoa do processo %ld nao encontrado",m_stDados.idAtendimento );
            throw new TuxException(TUXEXCE_ID_DADOS_OBRIGATORIO,"Uso de cWFAtendimento::incluir() indevido!");
        }
    }
    else
    {
        ULOGW( "Detalhe do processo %d nao encontrado",m_stDados.idAtendimento );
        throw new TuxException(TUXEXCE_ID_DADOS_OBRIGATORIO,"Detalhe do processo nao encontrado");
    }

    ULOG_END( "cWFAtdRelPSS::ObterDadosAtd()" );
}

void cWFAtdRelPSS::ObterDadosQuestionario()
{
    ULOG_START( "cWFAtdRelPSS::ObterDadosQuestionario()" );

    ULOG("IdContato: %d", m_stDados.idContato);
    ULOG("idTipoPessoa: %d", m_stDados.idTipoPessoa);
    ULOG("idUfOperadora: %d", m_stDados.idUfOperadora);
    
    proCGetQuestionario(m_stDados.idContato,m_stDados.idTipoPessoa,m_stDados.idUfOperadora,saida);

    ULOG_END( "cWFAtdRelPSS::ObterDadosQuestionario()" );
}

void cWFAtdRelPSS::carregarDados()
{
    ULOG_START( "cWFAtdRelPSS::carregarDados()" );

    if ( 0 == entrada )
    {
        throw new TuxException(TUXEXCE_PONTEIRO_INVALIDO,"xml de entrada com erro ou vazio.");
    }

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    char *p;

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
        ULOG("idAtendimento=%d", m_stDados.idAtendimento);
    }
    else
    {
        throw new TuxException(TUXEXCE_ID_OBRIGATORIO,"O código de 'idAtendimento' é obrigatório");
    }

    ULOG_END( "cWFAtdRelPSS::carregarDados()" );
}
