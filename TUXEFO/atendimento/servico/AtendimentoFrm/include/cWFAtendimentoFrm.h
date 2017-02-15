/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/

#ifndef __CWF_ATENDIMENTOFRM_H__
#define __CWF_ATENDIMENTOFRM_H__

#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoFrm.h"

class cWFAtendimentoFrm
{

    st_AtendimentoFrm*      m_stDados;
    st_vlAtendimentoFrm*    m_vlDados;

    st_FormularioDinamico   m_stFormulario;
    st_vlFormularioDinamico m_vlFormulario;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoFrm(st_AtendimentoFrm* dados, st_vlAtendimentoFrm* indicator, XMLGen*xml_g);
        cWFAtendimentoFrm(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoFrm();
        long incluir(XMLDPR *xmlDpr);
        int incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        int alterar();
        int excluir();
        bool consultar();
        void obtemFormulario();
        void obtemFormularioPreview(int idUFOperadora);
        void obtemFormularioMassa();
        void obtemFormularioPesquisa();
        bool obtemCampoDinamico();

    private:
        bool locado;

        void carregaDados();
        void carregaDadosObtemFormulario();

};

#endif
