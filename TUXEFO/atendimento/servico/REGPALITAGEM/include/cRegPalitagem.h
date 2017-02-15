/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.4 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:16 $
 **/

#include "../../REGCONTATO/include/cRegContato.h"

class cRegPalitagem : public TuxBasicSvc
{
    XMLGen*  saida;

    public:
        st_RegContato           dados;
        st_DadosEstadoProtocolo dadosProtocolo;

        XMLDPR xmlDpr;

        cRegPalitagem(XMLGen*xml_g);
        cRegPalitagem(st_RegContato *st_regcontato);
        ~cRegPalitagem();
        void carregaDados(DOMNode*dnode);
        void registra();
        void registra( char * nrProtocoloPrm,long * idAtendimentoPrm );
        long obterIdAtendimento() { return m_stDadosAtendimento.idAtendimento; }
        char *obterNrProtocolo() { return m_stDadosAtendimento.idAtendimentoProtocolo; }

    private:

        // Funções principais da classe.
        void gravaAndamento();
        void gravaAtendimento();
        void gravaAtendimentoConta();
        void gravaAtendimentoContato();
        void gravaAtendimentoContatoComunic();
        void gravaAtendimentoObservacao();
        void gravaAtendimentoOrigem();
        void gravaAtendimentoPessoa();
        void gravaLinhaTelefonicaAtendimento();

        // Registra contato para o DPR
        void registrarAcaoDPR();

        // Chamadas externas
        void atualizarPalitagem();

#ifndef WIN32
        char* remoteCall(char* nomeServico, char* sMsgIn, int lFlags);
#endif

        cRegContatoPC* rcpc;

    private:
        st_Atendimento          m_stDadosAtendimento; // dados
        st_vlAtendimento        m_vlDadosAtendimento; // status

        st_AtendimentoPessoa    m_stDadosPessoa;
        st_vlAtendimentoPessoa  m_vlDadosPessoa;

        st_AtendimentoLinha     m_stDadosLinha;
        st_vlAtendimentoLinha   m_vlDadosLinha;
};
