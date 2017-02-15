/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/ 
 
#ifndef __CWF_ATENDIMENTOGRUPODEVOLUCAO_H__
#define __CWF_ATENDIMENTOGRUPODEVOLUCAO_H__

#include <tuxfw.h>
#include "stWFAtendimentoGrupoDevolucao.h"

class cWFAtendimentoGrupoDevolucao
{
    st_AtendimentoGrupoDevolucao    m_stDados;
    st_vlAtendimentoGrupoDevolucao  m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoGrupoDevolucao() {entrada=0;saida=0;}
        cWFAtendimentoGrupoDevolucao(DOMNode*dnode, XMLGen*xml_g);
        cWFAtendimentoGrupoDevolucao(st_AtendimentoGrupoDevolucao* dados,st_vlAtendimentoGrupoDevolucao* status,XMLGen*xml_g);

        bool atualizar(long idAtendimento,int idGrupo,int idUsuarioAlteracao);
        bool consultar();
        bool incluir();

        int getIdGrupo();
        int alterar();
        int excluir();

    private:
        void carregaDados();
};

#endif
