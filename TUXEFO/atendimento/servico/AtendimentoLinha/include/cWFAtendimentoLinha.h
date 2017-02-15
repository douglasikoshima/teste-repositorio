/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/ 

#ifndef __CWF_ATENDIMENTOLINHA_H__
#define __CWF_ATENDIMENTOLINHA_H__

#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoLinha.h"

class cWFAtendimentoLinha
{

    st_AtendimentoLinha m_stDados;
    st_vlAtendimentoLinha m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoLinha() { entrada=0; saida=0; }
        cWFAtendimentoLinha(st_AtendimentoLinha* dados, st_vlAtendimentoLinha* indicator, XMLGen*xml_g);
        cWFAtendimentoLinha(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoLinha() {}
        int incluir(); // nao usar, mantido apenas para compatibilidade com o SSKlunk
        long incluir(XMLDPR *xmlDpr);
        int alterar();
        int excluir();
        bool consultar();
        bool ObtemDetalheAtend();
        bool ObtemDetalheAtendEx();
        bool ObtemDetalheAtend(long idAtendimento,struct LinhaAtendimento *linhaatendimento);
        bool ObtemDetalheAtendEx(long idAtendimento,struct LinhaAtendimento *linhaatendimento);
        bool ObtemDetalheAtend(long idAtendimento,XMLGen *saida);
        bool ObtemDadosAtendLinha(long idAtendimento,XMLGen *saida);
        bool ObtemDadosAtendLinha(long idAtendimento,int *idTipoLinha);
        bool ObtemDadosAtendLinha();
	    bool ObtemDadosAtendLinhaCri();
    private:
        void carregaDados();

};

#endif
