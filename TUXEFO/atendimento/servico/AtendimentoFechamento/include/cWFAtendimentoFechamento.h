/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#ifndef __CWF_ATENDIMENTOFECHAMENTO_H__
#define __CWF_ATENDIMENTOFECHAMENTO_H__

#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoFechamento.h"

class cWFAtendimentoFechamento
{

    st_AtendimentoFechamento*   m_stDados;
    st_vlAtendimentoFechamento* m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoFechamento();
        cWFAtendimentoFechamento(st_AtendimentoFechamento* dados, st_vlAtendimentoFechamento* indicator, XMLGen*xml_g);
        cWFAtendimentoFechamento(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoFechamento();
        long incluir(XMLDPR *xmlDpr);
        int incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        int alterar();
        int excluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        int excluir(XMLDPR *xmlDpr);
        bool consultar();
        bool existeAtendimentoFechamento(long idAtendimento);

    private:
        bool locado;
        void carregaDados();

};

#endif
