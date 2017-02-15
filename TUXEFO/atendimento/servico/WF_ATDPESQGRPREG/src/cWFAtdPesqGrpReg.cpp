/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:30 $
 **/

#include "../include/cWFAtdPesqGrpReg.h"

#include "../../TabelaUFOperadora/include/cWFTabUFOperadora.h"
#include "../../Usuario/include/cWFUsuario.h"

cWFAtdPesqGrpReg::cWFAtdPesqGrpReg(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdPesqGrpReg::Executar()
{
    bool retorno = true;

    saida->createTag("WFIndicadoresPesquisaVO");

    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    if ( !PesquisarConsultaWFGrupos() )
    {
        SetarErro(NULL,"cWFAtdPesqGrpReg::PesquisarConsultaWFGrupos falhou execução");

        ULOG(ObterMsgErro());

        retorno = false;
    }

    if ( !PesquisarTabelaUFRegional() )
    {
        SetarErro(NULL,"cWFAtdPesqGrpReg::PesquisarTabelaUFRegional falhou execução");

        ULOG(ObterMsgErro());

        retorno = false;
    }

    saida->closeTag();

    return retorno;
}

bool cWFAtdPesqGrpReg::PesquisarConsultaWFGrupos()
{
    ULOG_START("cWFAtdPesqGrpReg::PesquisarConsultaWFGrupos()");

    cWFUsuario cwfUsuario;

    // return cwfUsuario.pesqConsultaWFGrupos(saida);

    // retorna os grupos do usuario
    bool retorno = cwfUsuario.consultarWFGruposFilaProcesso(obterIdUsuario(),saida);

    ULOG_END("cWFAtdPesqGrpReg::PesquisarConsultaWFGrupos()");

    return retorno;
}

bool cWFAtdPesqGrpReg::PesquisarTabelaUFRegional()
{
    ULOG_START("cWFAtdPesqGrpReg::PesquisarTabelaUFRegional()");

    cWFTabUFOper cwfTabUFOper;

    bool retorno = cwfTabUFOper.PesquisarTabelaUFRegional(entrada,saida);

    ULOG_END("cWFAtdPesqGrpReg::PesquisarTabelaUFRegional()");

    return retorno;
}

void cWFAtdPesqGrpReg::carregaDados()
{
    char *p;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( p=tx.walkTree(entrada,"idOperadora",0),p ) 
    {
        m_stDados.idOperadora = atoi(p);
        m_vlDados.idOperadora = 1;
        XMLString::release(&p);
    }

    ULOG("idOperadora=%d",m_stDados.idOperadora);
}
