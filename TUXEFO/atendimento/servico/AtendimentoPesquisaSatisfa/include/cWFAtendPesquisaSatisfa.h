/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:03 $
 **/ 

#ifndef __WFATENDPESQUISASATISFA_H__
#define __WFATENDPESQUISASATISFA_H__

#include <tuxfw.h>
#include "stWFAtendPesquisaSatisfa.h"

class cWFAtendimentoPesquisaSatisfa
{

    st_AtendimentoPesquisaSatisfa   m_stDados;
    st_vlAtendimentoPesquisaSatisfa m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoPesquisaSatisfa() {entrada=0;saida=0;}
        cWFAtendimentoPesquisaSatisfa(DOMNode*dnode, XMLGen*xml_g);
        cWFAtendimentoPesquisaSatisfa(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status, XMLGen*xml_g);
        bool incluir();
        bool incluir(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status);
        int alterar();
        int excluir();
        bool consultar();
        bool consultar(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status);
        bool seExiste(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status);

    private:
        void carregaDados();

};

#endif