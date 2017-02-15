/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdDetFrArvEx.h"

#include "../../AtendimentoFrmCampoValor/include/cWFAtendimentoFrmCampoValor.h"

extern void obtemObservacaoAtendimento(long _idAtendimento, XMLGen* xml_g);

cWFAtdDetFrArvEx::cWFAtdDetFrArvEx(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    carregaDados();
}

bool cWFAtdDetFrArvEx::executar(char **codErro,char **msgErro)
{
    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O código de 'idAtendimento' é obrigatório";

        return false;
    }

    ObterAtdFrmCpVl();

    return true;
}

void cWFAtdDetFrArvEx::ObterAtdFrmCpVl()
{
    ULOG_START("cWFAtdDetFrArvEx::ObterAtdFrmCpVl()");

    cWFAtendimentoFrmCampoValor cwfAtendimentoFrmCampoValor(entrada,saida);

    saida->createTag( "FormularioProcessoVO" );
        saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns2","admsistemas.fo.vivo.com.br/vo");

    obtemObservacaoAtendimento(m_stDados.idAtendimento, saida);

    if ( false == cwfAtendimentoFrmCampoValor.ObtemAtdFrmCpVlEx() )
    {
        ULOG("ObterAtdFrmCpVl() nao gerou xml de saida");
    }

    saida->closeTag();

    ULOG_END("cWFAtdDetFrArvEx::ObterAtdFrmCpVl()");
}

void cWFAtdDetFrArvEx::carregaDados()
{
    ULOG_START("cWFAtdDetFrArvEx::carregaDados()");

    char *p;

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p)
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
    }

    ULOG("idAtendimento=%d",m_stDados.idAtendimento);

    ULOG_END("cWFAtdDetFrArvEx::carregaDados()");
}

