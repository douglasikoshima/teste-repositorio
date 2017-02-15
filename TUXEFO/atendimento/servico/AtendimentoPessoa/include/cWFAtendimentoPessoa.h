/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#ifndef __CWF_ATENDIMENTOPESSOA_H__
#define __CWF_ATENDIMENTOPESSOA_H__

#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoPessoa.h"

class cWFAtendimentoPessoa
{

    st_AtendimentoPessoa m_stDados;
    st_vlAtendimentoPessoa m_vlDados;

    st_AtendimentoConsultaPessoa    m_stPessoa;
    st_vlAtendimentoConsultaPessoa  m_vlPessoa;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoPessoa() {entrada=0;saida=0;}
        cWFAtendimentoPessoa(st_AtendimentoPessoa* dados, st_vlAtendimentoPessoa* indicator, XMLGen*xml_g);
        cWFAtendimentoPessoa(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoPessoa() {}
        long incluir(XMLDPR *xmlDpr);
        int incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        int alterar();
        int excluir();
        int ObtemIdTipoRelacionamento(long idAtendimento);
        int ObtemIdTipoRelacionamentoEx( long idAtendimento );
        bool consultar();
        bool ObtemAtendPessoa();
        bool ObtemAtendPessoa(AtendimentoPessoa *ap);
        bool ObtemAtendPessoaEx(AtendimentoPessoa *ap);
        bool ObtemAtendPessoa(long idAtendimento,AtendimentoPessoa *ap);
        bool ObtemAtendPessoaEx(long idAtendimento,AtendimentoPessoa *ap);
        bool ObtemAtendPessoa(long idAtendimento,AtendimentoPessoa *ap,XMLGen* saida);
        bool ObtemAtendPessoaEx(long idAtendimento,AtendimentoPessoa *ap,XMLGen* saida);
        bool ObtemAtendPessoa(long idAtendimento,XMLGen* saida);
        bool ObtemDocumentoPessoa();
        bool ObtemEnderecoPessoa();
        bool ObtemEnderecoPessoa(long idPessoa,XMLGen* saida);

    private:
        void carregaDados();
        void carregaDadosConsultaPessoa();

};

#endif
