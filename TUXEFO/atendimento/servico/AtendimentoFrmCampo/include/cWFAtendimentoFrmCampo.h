/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/

#ifndef __CWF_ATENDIMENTOFRMCAMPO_H__
#define __CWF_ATENDIMENTOFRMCAMPO_H__

#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoFrmCampo.h"

class cWFAtendimentoFrmCampo
{

    st_AtendimentoFrmCampo*     m_stDados;
    st_vlAtendimentoFrmCampo*   m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoFrmCampo(st_AtendimentoFrmCampo* dados, st_vlAtendimentoFrmCampo* indicator, XMLGen*xml_g);
        cWFAtendimentoFrmCampo(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoFrmCampo();
        long incluir(XMLDPR *xmlDpr);
        int incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        int alterar();
        int excluir();
        bool consultar();
        bool obtemDominio();
        
    private:
        bool locado;

        void carregaDados();

};

#endif
