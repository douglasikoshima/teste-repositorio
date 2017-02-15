/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:32 $
 **/

#include "../include/cWFAtendimentoRel6ADet.h"
#include "../../../commons/msgPadrao.h"


extern bool proCExecutarRelatorio6ADet( st_AtendimentoRel *dados
                                      , st_vlAtendimentoRel *status
                                      , XMLGen *saida
                                      , DOMNode *dnode );

cWFAtdRel6ADet::cWFAtdRel6ADet(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdRel6ADet::Executar()
{
    return proCExecutarRelatorio6ADet( &m_stDados
                                     , &m_vlDados
                                     , saida
                                     , entrada );
}

void cWFAtdRel6ADet::carregaDados()
{
    char *p;

    // Inicializa as estruturas de dados para armazenar os valores
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if (p=tx.walkTree( entrada, "idOperadora", 0 ),p) 
    {
        m_stDados.idOperadora = atoi(p);
        m_vlDados.idOperadora = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idUF", 0 ),p) 
    {
        m_stDados.idUF = atoi(p);
        m_vlDados.idUF = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idGrupo", 0 ),p) 
    {
        m_stDados.idGrupo = atoi(p);
        m_vlDados.idGrupo = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idCarteira", 0 ),p) 
    {
        m_stDados.idCarteira = atoi(p);
        m_vlDados.idCarteira = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idTipoCarteira", 0 ),p) 
    {
        m_stDados.idCarteira = atoi(p);
        m_vlDados.idCarteira = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idRegional", 0 ),p) 
    {
        m_stDados.idRegional = atoi(p);
        m_vlDados.idRegional = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idSegmento", 0 ),p) 
    {
        m_stDados.idSegmentacao = atoi(p);
        m_vlDados.idSegmentacao = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idSegmentacao", 0 ),p) 
    {
        m_stDados.idSegmentacao = atoi(p);
        m_vlDados.idSegmentacao = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idRepresentante", 0 ),p) 
    {
        m_stDados.idRepresentante = atoi(p);
        m_vlDados.idRepresentante = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "id", 0 ),p) 
    {
        m_stDados.idRepresentante = atoi(p);
        m_vlDados.idRepresentante = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "prazo", 0 ),p) 
    {
        sprintf(m_stDados.prazo,"%.*s",sizeof(m_stDados.prazo)-1,p);
        m_vlDados.prazo = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "dtInicio", 0 ),p) 
    {
        sprintf(m_stDados.dtInicio,"%.*s",sizeof(m_stDados.dtInicio)-1, p);
        m_vlDados.dtInicio = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "dtFim", 0 ),p) 
    {
        sprintf(m_stDados.dtFim,"%.*s",sizeof(m_stDados.dtFim)-1, p);
        m_vlDados.dtFim = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "inTotal", 0 ),p) 
    {
        m_stDados.bloco = atoi(p);
        m_vlDados.bloco = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "qtdLinhasBloco", 0 ),p) // num linhas no bloco
    {
        m_stDados.qtdLinhasBloco = atoi(p);
        m_vlDados.qtdLinhasBloco = 1;
        XMLString::release(&p);
    }
    else
    {
        m_stDados.qtdLinhasBloco = NUM_LINHAS;
    }

    ULOG("idUF=%d",m_vlDados.idUF == 1 ? m_stDados.idUF: -1);
    ULOG("idGrupo=%d",m_vlDados.idGrupo == 1 ? m_stDados.idGrupo: -1);
    ULOG("idAtendimento=%d",m_vlDados.idAtendimento == 1 ? m_stDados.idAtendimento: -1);
    ULOG("idRegional=%d",m_vlDados.idRegional == 1 ? m_stDados.idRegional: -1);
    ULOG("idCarteira=%d",m_vlDados.idCarteira == 1 ? m_stDados.idCarteira: -1);
    ULOG("idSegmentacao=%d",m_vlDados.idSegmentacao == 1 ? m_stDados.idSegmentacao: -1);
    ULOG("idRepresentante=%c",m_vlDados.idRepresentante == 1 ? m_stDados.idRepresentante: '?');
    ULOG("prazo=%s",m_vlDados.prazo == 1 ? m_stDados.prazo: "?");
    ULOG("dtInicio=%s",m_vlDados.dtInicio == 1 ? m_stDados.dtInicio: "data inicial nao informada");
    ULOG("dtFim=%s",m_vlDados.dtFim == 1 ? m_stDados.dtFim: "data final nao informada");
}
