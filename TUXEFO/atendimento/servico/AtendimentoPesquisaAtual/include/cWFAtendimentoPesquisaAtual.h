/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:32 $
 **/ 

#include <tuxfw.h>
#include "stWFAtendimentoPesquisaAtual.h"

#ifndef __WFATENDIMENTOPESQUISAATUAL_H__
#define __WFATENDIMENTOPESQUISAATUAL_H__

class cWFAtendimentoPesquisaAtual
{

    st_AtendimentoPesquisaAtual m_stDados;
    st_vlAtendimentoPesquisaAtual m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoPesquisaAtual() {entrada=0;saida=0;}
        cWFAtendimentoPesquisaAtual(DOMNode*dnode, XMLGen*xml_g,char *user);
        cWFAtendimentoPesquisaAtual(st_AtendimentoPesquisaAtual* dados, st_vlAtendimentoPesquisaAtual* status, XMLGen*xml_g);

    public:
        bool incluir();
        bool incluir(st_AtendimentoPesquisaAtual* dados, st_vlAtendimentoPesquisaAtual* status);
        long  alterar(st_AtendimentoPesquisaAtual* dados, st_vlAtendimentoPesquisaAtual* status);
        long  alterar();
        long  excluir();
        bool consultar();
        bool ObterPesquisaAtendimentoAtual(long idAtendimento,long  * idAtendimentoPesquisaSatisfa);

    private:
        void carregaDados();

};

#endif