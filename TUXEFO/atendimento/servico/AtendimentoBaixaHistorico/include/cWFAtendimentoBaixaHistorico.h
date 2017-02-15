/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#ifndef __CWF_ATENDIMENTOBAIXAHISTORICO_H__
#define __CWF_ATENDIMENTOBAIXAHISTORICO_H__

#include <tuxfw.h>
#include "stWFAtendimentoBaixaHistorico.h"
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"

class cWFAtendimentoBaixaHistorico
{
    st_AtendimentoBaixaHistorico m_stDados;
    st_vlAtendimentoBaixaHistorico m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoBaixaHistorico(st_AtendimentoBaixaHistorico* dados, st_vlAtendimentoBaixaHistorico* indicator, XMLGen*xml_g);
        cWFAtendimentoBaixaHistorico(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoBaixaHistorico() {}
        long incluir(XMLDPR *xmlDpr);
        int incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        int alterar();
        int excluir();
        bool consultar();
        bool incluirBaixaMensagem();

    private:
        void carregaDados();

};

#endif