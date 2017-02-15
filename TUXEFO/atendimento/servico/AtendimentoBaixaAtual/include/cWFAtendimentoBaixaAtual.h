/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#ifndef __CWF_ATENDIMENTOBAIXAATUAL_H__
#define __CWF_ATENDIMENTOBAIXAATUAL_H__

#include <tuxfw.h>
#include "stWFAtendimentoBaixaAtual.h"

class cWFAtendimentoBaixaAtual
{

    st_AtendimentoBaixaAtual*   m_stDados;
    st_vlAtendimentoBaixaAtual* m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoBaixaAtual() {entrada=0;saida=0;locado = false;}
        cWFAtendimentoBaixaAtual(st_AtendimentoBaixaAtual* dados, st_vlAtendimentoBaixaAtual* indicator,XMLGen*xml_g);
        cWFAtendimentoBaixaAtual(DOMNode*dnode,XMLGen*xml_g);
        ~cWFAtendimentoBaixaAtual();
        long incluir();
        long alterar();
        long excluir();
        bool consultar();
        bool obterAtdBxA();
        bool obterIdAtendimentoBaixaHistorico(long idAtendimento,long *idBaixaAtual);

    private:
        bool locado;

        void carregaDados();
};

#endif