/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.5.20.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/12/05 14:20:39 $
 **/

//#include "cWfAtdAbreProtPC.h"

#include <tuxfw.h>
#include "stWfAtdAbreProt.h"

class cWfAtdAbreProt : public TuxBasicSvc
{
    public:
        TuxHelper tx;

    private:
        DOMNode *entrada;
        XMLGen *saida;
        char idPessoaUsuarioWeb[64];
        st_DadosEntrada dados;
        st_StatusEntrada status;

    public:

        cWfAtdAbreProt()
        {
            saida = 0;
            entrada = 0;
            memset(&dados,0,sizeof(dados));
            memset(&status,-1,sizeof(status));
        }

        cWfAtdAbreProt(DOMNode *dnode,XMLGen *xml_g) 
        {
            saida = xml_g;
            entrada = dnode;
            memset(&dados,0,sizeof(dados));
            memset(&status,-1,sizeof(status));
        }

        ~cWfAtdAbreProt() {}

        //char *getIdAtendimentoProtocolo() {return dados.idAtendimentoProtocolo;}
        bool idAtendimentoProtocoloVazioSimNao() {return dados.idAtendimentoProtocolo[0]==0?true:false;}
        void setIdPessoaUsuarioWeb(const char *dado){ SAFE_STRNCPY(idPessoaUsuarioWeb,dado); }
        const char *getIdPessoaUsuarioWeb() { return idPessoaUsuarioWeb; }

        char *getIdAtendimentoProtocolo() {return dados.idAtendimentoProtocolo;}
        char *getCdAreaRegistro() {return dados.cdAreaRegistro;}
        char *getNrTelefone() {return dados.nrTelefone;}
        char *getIdSistemaOrigem() {return dados.idSistemaOrigem;}
        char *getIdPessoaDePara() {return dados.idPessoaDePara;}
        char *getCdConta() {return dados.cdConta;}
        char *getDtAbertura() {return dados.dtAbertura;}
        char *getDtEncerramento() {return dados.dtEncerramento;}
        char *getQtProcessoAberto() {return dados.qtProcessoAberto;}
        char *getQtProcessoPendente() {return dados.qtProcessoPendente;}
        char *getIdLinhaTelefonica() {return dados.idLinhaTelefonica;}
        char *getCdAreaRegistroSMS() {return dados.cdAreaRegistroSMS;}
        char *getNrTelefoneSMS() {return dados.nrTelefoneSMS;}
        char *getDtEnvioSMS() {return dados.dtEnvioSMS;}
        char *getDsErroSMS() {return dados.dsErroSMS;}
        char *getIdTipoAberturaProtocolo() {return dados.idTipoAberturaProtocolo;}
        char *getIdUsuarioAlteracao() {return dados.idUsuarioAlteracao;}
        char *getDtUltimaAlteracao() {return dados.dtUltimaAlteracao;}

    public:
        void Executar();
        void AbrirProtocolo();
        void AbrirProtocolo(char * idLinhaTelefonica);
        bool AbrirProtocoloCondicional(const char *idAtendimentoProtocolo);
        bool AbrirProtocoloCondicional(const char *idAtendimentoProtocolo, char * idLinhaTelefonicaPRM );

        void setQtProcessoAberto(const char *dado) { SAFE_STRNCPY(dados.qtProcessoAberto,dado); if (dado&&*dado){ status.qtProcessoAberto=1; } else { status.qtProcessoAberto=-1; } }
        void setQtProcessoPendente(const char *dado) { SAFE_STRNCPY(dados.qtProcessoPendente,dado); if (dado&&*dado){ status.qtProcessoPendente=1; } else { status.qtProcessoPendente=-1; } }

        void setIdAtendimentoProtocolo(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.idAtendimentoProtocolo,dado);
                status.idAtendimentoProtocolo=1;
            }
            else
            {
                status.idAtendimentoProtocolo=-1;
            }
        }

        void setCdAreaRegistro(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.cdAreaRegistro,dado);
                status.cdAreaRegistro=1;
            }
            else
            {
                status.cdAreaRegistro=-1;
            }
            
        }

        void setNrTelefone(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.nrTelefone,dado);
                status.nrTelefone=1;
            }
            else
            {
                status.nrTelefone=-1;
            }
            
        }

        void setIdSistemaOrigem(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.idSistemaOrigem,dado);
                status.idSistemaOrigem=1;
            }
            else
            {
                status.idSistemaOrigem=-1;
            }
        }

        void setIdPessoaDePara(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.idPessoaDePara,dado);
                status.idPessoaDePara=1;
            }
            else
            {
                status.idPessoaDePara=-1;
            }
        }

        void setCdConta(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.cdConta,dado);
                status.cdConta=1;
            }
            else
            {
                status.cdConta=-1;
            }
        }

        void setDtAbertura(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.dtAbertura,dado);
                status.dtAbertura=1;
            }
            else
            {
                status.dtAbertura=-1;
            }
        }

        void setDtEncerramento(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.dtEncerramento,dado);
                status.dtEncerramento=1;
            }
            else
            {
                status.dtEncerramento=-1;
            }
        }

        void setIdLinhaTelefonica(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.idLinhaTelefonica,dado);
                status.idLinhaTelefonica=1;
            }
            else
            {
                status.idLinhaTelefonica=-1;
            }
        }

        void setCdAreaRegistroSMS(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.cdAreaRegistroSMS,dado);
                status.cdAreaRegistroSMS=1;
            }
            else
            {
                status.cdAreaRegistroSMS=-1;
            }
        }

        void setNrTelefoneSMS(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.nrTelefoneSMS,dado);
                status.nrTelefoneSMS=1;
            }
            else
            {
                status.nrTelefoneSMS=-1;
            }
        }

        void setDtEnvioSMS(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.dtEnvioSMS,dado);
                status.dtEnvioSMS=1;
            }
            else
            {
                status.dtEnvioSMS=-1;
            }
        }

        void setDsErroSMS(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.dsErroSMS,dado);
                status.dsErroSMS=1;
            }
            else
            {
                status.dsErroSMS=-1;
            }
        }

        void setIdTipoAberturaProtocolo(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.idTipoAberturaProtocolo,dado);
                status.idTipoAberturaProtocolo=1;
            }
            else
            {
                status.idTipoAberturaProtocolo=-1;
            }
        }

        void setIdUsuarioAlteracao(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.idUsuarioAlteracao,dado);
                status.idUsuarioAlteracao=1;
            }
            else
            {
                status.idUsuarioAlteracao=-1;
            }
        }

        void setDtUltimaAlteracao(const char *dado)
        {
            if (dado&&*dado&&strcmp(dado,"0"))
            {
                SAFE_STRNCPY(dados.dtUltimaAlteracao,dado);
                status.dtUltimaAlteracao=1;
            }
            else
            {
                status.dtUltimaAlteracao=-1;
            }
        }

        void setForceUsoProtocoloNaoAberto(bool dado) { dados.forceUsoProtocoloNaoAberto = dado; }

    private:
        void ValidarDados();
        void CarregarDados();
};
