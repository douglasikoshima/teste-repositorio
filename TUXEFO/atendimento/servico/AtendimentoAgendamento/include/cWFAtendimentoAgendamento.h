/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:53 $
 **/

#ifndef __CWF_ATENDIMENTOAGENDAMENTO_H__
#define __CWF_ATENDIMENTOAGENDAMENTO_H__

#include <tuxfw.h>
#include "stWFAtendimentoAgendamento.h"

class cWFAtendimentoAgendamento
{

    st_AtendimentoAgendamento   m_stDados;
    st_vlAtendimentoAgendamento m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoAgendamento(DOMNode*dnode, XMLGen*xml_g);
        cWFAtendimentoAgendamento(st_AtendimentoAgendamento* , st_vlAtendimentoAgendamento* , XMLGen* );
        bool incluir();
        int alterar();
        int excluir();
        bool consultar();

    private:
        void carregaDados();

};

#endif
