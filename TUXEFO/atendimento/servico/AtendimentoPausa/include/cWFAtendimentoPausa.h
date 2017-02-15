/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  
 * @author  Charles Santos
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/

#ifndef __CWFATENDIMENTOPAUSA_H__
    #define __CWFATENDIMENTOPAUSA_H__

#include <tuxfw.h>
#include "stWFAtendimentoPausa.h"

class cWFAtendimentoPausa
{
    st_AtendimentoPausa    *m_stDados;
    st_vlAtendimentoPausa  *m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoPausa();
        cWFAtendimentoPausa(st_AtendimentoPausa* dados, st_vlAtendimentoPausa* status);
        cWFAtendimentoPausa(st_AtendimentoPausa* dados, st_vlAtendimentoPausa* status, XMLGen*xml_g);
        cWFAtendimentoPausa(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoPausa();

        void incluir();
        void incluir(st_AtendimentoPausa *dados,st_vlAtendimentoPausa *status);
        void alterar();
        void alterar(st_AtendimentoPausa *dados,st_vlAtendimentoPausa *status);
        void atualizar();
        void excluir();
        void consultar();
        bool obterRegistroPausa(long sIdAtendimento,st_AtendimentoPausa *dadosSaida);
        bool existePausa(long _idAtendimento);
        bool processoEmPausaSimNao(long idAtendimento);

    private:
        bool alocado;

        void carregaDados();
};

#endif
