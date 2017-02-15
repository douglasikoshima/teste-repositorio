/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:55 $
 **/ 

#include <tuxfw.h>
#include "stWFAtendimentoPesquisaResp.h"

#ifndef __WFATENDIMENTOPESQUISARESP_H__
#define __WFATENDIMENTOPESQUISARESP_H__

class cWFAtendimentoPesquisaResp
{

    st_AtendimentoPesquisaResp m_stDados;
    st_vlAtendimentoPesquisaResp m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoPesquisaResp() { entrada=0;saida=0;}
        cWFAtendimentoPesquisaResp(st_AtendimentoPesquisaResp* dados, st_vlAtendimentoPesquisaResp* status, XMLGen*xml_g);
        cWFAtendimentoPesquisaResp(DOMNode*dnode, XMLGen*xml_g,char *user);
        bool incluir();
        bool incluir(st_AtendimentoPesquisaResp* dados, st_vlAtendimentoPesquisaResp* status);

        int alterar();
        int excluir();
        bool consultar();

    private:
        void carregaDados();

};

#endif