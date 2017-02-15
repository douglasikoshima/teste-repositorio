/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/ 

#ifndef __CWF_ATENDIMENTOUSUARIODEVOLUCAO_H__
#define __CWF_ATENDIMENTOUSUARIODEVOLUCAO_H__

#include <tuxfw.h>
#include "stWFAtendUsuarioDevolucao.h"

class cWFAtendimentoUsuarioDevolucao
{

    st_AtendimentoUsuarioDevolucao  m_stDados;
    st_vlAtendimentoUsuarioDevolucao    m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        int getIdPessoa();
        cWFAtendimentoUsuarioDevolucao(DOMNode*dnode, XMLGen*xml_g);
        cWFAtendimentoUsuarioDevolucao(st_AtendimentoUsuarioDevolucao* dados, st_vlAtendimentoUsuarioDevolucao* status, XMLGen*xml_g);
        bool incluir();
        int alterar();
        int excluir();
        void atualizar(long idAtendimento,int idPessoaUsuario,int idUsuarioAlteracao);
        bool consultar();

    private:
        void carregaDados();

};

#endif
