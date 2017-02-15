/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

//#include "cWfAtdAlterProtPC.h"

#include <tuxfw.h>
#include "stWfAtdAlterProt.h"

class cWfAtdAlterProt : public TuxBasicSvc
{
    public:
        TuxHelper tx;

    private:
        DOMNode *entrada;
        XMLGen *saida;
        char idPessoaUsuarioWeb[39];
        st_DadosEntradaAltProt dados;
        st_StatusEntradaAltProt status;

    public:

        cWfAtdAlterProt()
        {
            saida = 0;
            entrada = 0;
            memset(&dados,0,sizeof(dados));
            memset(&status,-1,sizeof(status));
        }

        cWfAtdAlterProt(DOMNode *dnode,XMLGen *xml_g) 
        {
            saida = xml_g;
            entrada = dnode;
            memset(&dados,0,sizeof(dados));
            memset(&status,-1,sizeof(status));
        }

        ~cWfAtdAlterProt() {}

        char *getIdAtendimentoProtocolo() {return dados.idAtendimentoProtocolo;}
        void setIdPessoaUsuarioWeb(const char *dado) { SAFE_STRNCPY(idPessoaUsuarioWeb,dado); }
        const char *getIdPessoaUsuarioWeb() { return idPessoaUsuarioWeb; }

    public:
        void Executar();

        void AlterarDadosProtocolo();
        void DecrementarPendentes(const char *idAtendimentoProtocolo,const char *incPendente,const char *idPessoaUsuario);
        void IncrementarQuantidades(const char *idAtendimentoProtocolo,const char *incAberto,const char *incPendente);

        void IncrementarQuantidades(const char *idAtendimentoProtocolo,const char *incAberto,const char *incPendente,const char *idUsuarioAlteracao);
        void DecrementarPendentes(const char *idAtendimentoProtocolo,const char *incPendente,const char *idPessoaUsuario,const char *idUsuarioAlteracao);

        void setOperacao(const char *dado) { SAFE_STRNCPY(dados.operacao,dado); if (dado&&*dado){ status.operacao=1; } }
        void setIdAtendimentoProtocolo(const char *dado) { SAFE_STRNCPY(dados.idAtendimentoProtocolo,dado); if (dado&&*dado){ status.idAtendimentoProtocolo=1; } }
        void setCdAreaRegistro(const char *dado) { SAFE_STRNCPY(dados.cdAreaRegistro,dado); if (dado&&*dado){ status.cdAreaRegistro=1; } }
        void setNrTelefone(const char *dado) { SAFE_STRNCPY(dados.nrTelefone,dado); if (dado&&*dado){ status.nrTelefone=1; } }
        void setIdSistemaOrigem(const char *dado) { SAFE_STRNCPY(dados.idSistemaOrigem,dado); if (dado&&*dado){ status.idSistemaOrigem=1; } }
        void setIdPessoaDePara(const char *dado) { SAFE_STRNCPY(dados.idPessoaDePara,dado); if (dado&&*dado){ status.idPessoaDePara=1; } }
        void setCdConta(const char *dado) { SAFE_STRNCPY(dados.cdConta,dado); if (dado&&*dado){ status.cdConta=1; } }
        void setDtAbertura(const char *dado) { SAFE_STRNCPY(dados.dtAbertura,dado); if (dado&&*dado){ status.dtAbertura=1; } }
        void setDtEncerramento(const char *dado) { SAFE_STRNCPY(dados.dtEncerramento,dado); if (dado&&*dado){ status.dtEncerramento=1; } }
        void setQtProcessoAberto(const char *dado) { SAFE_STRNCPY(dados.qtProcessoAberto,dado); if (dado&&*dado){ status.qtProcessoAberto=1; } }
        void setQtProcessoPendente(const char *dado) { SAFE_STRNCPY(dados.qtProcessoPendente,dado); if (dado&&*dado){ status.qtProcessoPendente=1; } }
        void setIdLinhaTelefonica(const char *dado) { SAFE_STRNCPY(dados.idLinhaTelefonica,dado); if (dado&&*dado){ status.idLinhaTelefonica=1; } }
        void setCdAreaRegistroSMS(const char *dado) { SAFE_STRNCPY(dados.cdAreaRegistroSMS,dado); if (dado&&*dado){ status.cdAreaRegistroSMS=1; } }
        void setNrTelefoneSMS(const char *dado) { SAFE_STRNCPY(dados.nrTelefoneSMS,dado); if (dado&&*dado){ status.nrTelefoneSMS=1; } }
        void setDtEnvioSMS(const char *dado) { SAFE_STRNCPY(dados.dtEnvioSMS,dado); if (dado&&*dado){ status.dtEnvioSMS=1; } }
        void setDsErroSMS(const char *dado) { SAFE_STRNCPY(dados.dsErroSMS,dado); if (dado&&*dado){ status.dsErroSMS=1; } }
        void setIdTipoAberturaProtocolo(const char *dado) { SAFE_STRNCPY(dados.idTipoAberturaProtocolo,dado); if (dado&&*dado){ status.idTipoAberturaProtocolo=1; } }
        void setIdUsuarioAlteracao(const char *dado) { SAFE_STRNCPY(dados.idUsuarioAlteracao,dado); if (dado&&*dado){ status.idUsuarioAlteracao=1; } }
        void setDtUltimaAlteracao(const char *dado) { SAFE_STRNCPY(dados.dtUltimaAlteracao,dado); if (dado&&*dado){ status.dtUltimaAlteracao=1; } }
        void setIncAberto(const char *dado) { SAFE_STRNCPY(dados.incAberto,dado); if (dado&&*dado){ status.incAberto=1; } }
        void setIncPendente(const char *dado) { SAFE_STRNCPY(dados.incPendente,dado); if (dado&&*dado){ status.incPendente=1; } }

    private:
        void CarregarDados();
};
