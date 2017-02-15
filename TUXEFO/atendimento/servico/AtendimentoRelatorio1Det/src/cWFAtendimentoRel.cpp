/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:32 $
 **/

#include "../include/cWFAtendimentoRel1Det.h"
#include "../../../commons/msgPadrao.h"

#ifndef NUM_LINHAS
#define NUM_LINHAS 300
#endif

extern bool proCExecutarRelatorio1Det( st_AtendimentoRel *dados
                                     , st_vlAtendimentoRel *status
                                     , XMLGen *saida
                                     , DOMNode *dnode );

cWFAtdRel1Det::cWFAtdRel1Det(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdRel1Det::Executar()
{
    return proCExecutarRelatorio1Det( &m_stDados
                                    , &m_vlDados
                                    , saida
                                    , entrada );
}

void cWFAtdRel1Det::carregaDados()
{
    char *p;

    // Inicializa as estruturas de dados para armazenar os valores
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    // if (p=tx.walkTree( entrada, "idOperadora", 0 ),p) 
    // {
    //     m_stDados.idOperadora = atoi(p);
    //     m_vlDados.idOperadora = 1;
	// 	XMLString::release(&p);
    // }

    // if (p=tx.walkTree( entrada, "idUF", 0 ),p) 
    // {
    //     m_stDados.idUF = atoi(p);
    //     m_vlDados.idUF = 1;
	// 	XMLString::release(&p);
    // }

    // if (p=tx.walkTree( entrada, "idGrupo", 0 ),p) 
    // {
    //     m_stDados.idGrupo = atoi(p);
    //     m_vlDados.idGrupo = 1;
	// 	XMLString::release(&p);
    // }

    // if (p=tx.walkTree( entrada, "idCarteira", 0 ),p) 
    // {
    //     m_stDados.idCarteira = atoi(p);
    //     m_vlDados.idCarteira = 1;
	// 	XMLString::release(&p);
    // }

    // if (p=tx.walkTree( entrada, "idTipoCarteira", 0 ),p) 
    // {
    //     m_stDados.idCarteira = atoi(p);
    //     m_vlDados.idCarteira = 1;
	// 	XMLString::release(&p);
    // }

    // if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    // {
    //     m_stDados.idAtendimento = atol(p);
    //     m_vlDados.idAtendimento = 1;
	// 	XMLString::release(&p);
    // }

    // if (p=tx.walkTree( entrada, "idRegional", 0 ),p) 
    // {
    //     m_stDados.idRegional = atoi(p);
    //     m_vlDados.idRegional = 1;
	// 	XMLString::release(&p);
    // }

    // if (p=tx.walkTree( entrada, "idSegmento", 0 ),p) 
    // {
    //     m_stDados.idSegmentacao = atoi(p);
    //     m_vlDados.idSegmentacao = 1;
	// 	XMLString::release(&p);
    // }

    // if (p=tx.walkTree( entrada, "idSegmentacao", 0 ),p) 
    // {
    //     m_stDados.idSegmentacao = atoi(p);
    //     m_vlDados.idSegmentacao = 1;
	// 	XMLString::release(&p);
    // }

    // if (p=tx.walkTree( entrada, "nmGrupo", 0 ),p) 
    // {
    //     sprintf(m_stDados.nmGrupo,"%.*s",sizeof(m_stDados.nmGrupo)-1,p);
    //     m_vlDados.nmGrupo = 1;
	// 	XMLString::release(&p);
    // }

    if (p=tx.walkTree( entrada, "prazo", 0 ),p) 
    {
        sprintf(m_stDados.prazo,"%.*s",sizeof(m_stDados.prazo)-1,p);
        m_vlDados.prazo = 1;
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
        if ( m_stDados.qtdLinhasBloco > 20 ) { m_stDados.qtdLinhasBloco = 20; }
        m_vlDados.qtdLinhasBloco = 1;
		XMLString::release(&p);
    }
    else
    {
        m_stDados.qtdLinhasBloco = 20; /*NUM_LINHAS;*/
    }

    // ULOG("idOperadora=%d",m_vlDados.idOperadora == 1 ? m_stDados.idOperadora: -1);
    // ULOG("idUF=%d",m_vlDados.idUF == 1 ? m_stDados.idUF: -1);
    // ULOG("idGrupo=%d",m_vlDados.idGrupo == 1 ? m_stDados.idGrupo: -1);
    // ULOG("nmGrupo=%s",m_vlDados.nmGrupo == 1 ? m_stDados.nmGrupo: "-1");
    // ULOG("idAtendimento=%d",m_vlDados.idAtendimento == 1 ? m_stDados.idAtendimento: -1);
    // ULOG("idRegional=%d",m_vlDados.idRegional == 1 ? m_stDados.idRegional: -1);
    // ULOG("idCarteira=%d",m_vlDados.idCarteira == 1 ? m_stDados.idCarteira: -1);
    // ULOG("idSegmentacao=%d",m_vlDados.idSegmentacao == 1 ? m_stDados.idSegmentacao: -1);
    ULOG("         prazo=%s",m_vlDados.prazo == 1 ? m_stDados.prazo: "-1");
    ULOG("         bloco=%d",m_vlDados.bloco == 1 ? m_stDados.bloco: -1);
    // ULOG("qtdLinhasBloco=%d",m_vlDados.qtdLinhasBloco == 1 ? m_stDados.qtdLinhasBloco: -1);
}
