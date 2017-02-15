/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.10.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#include <tuxfw.h>

#include "cRegContatoPC.h"
#include "cMsgErro.h"
#include "cApoio.h"

class cRegContato : public TuxBasicSvc
{
    XMLGen*  saida;

    public:
        st_RegContato              dados;
        st_RegContatoErro          erro;
        st_DadosEstadoProtocolo    dadosProtocolo;

        XMLDPR xmlDpr;

        cRegContato(XMLGen*xml_g);
        cRegContato() {}
        ~cRegContato();
        void carregaDados(DOMNode*dnode);
        void registra();
        void EntradaValidacao(st_RegContato *dadosRCont);
        long obterIdAtendimento() { return m_stDadosAtendimento.idAtendimento; }
        char *obterNrProtocolo() { return m_stDadosAtendimento.idAtendimentoProtocolo; }
        bool VerificarUserNumericoSimNao(const char *userNN);
        bool ObterIdUserUra(const char *userNN,char *idPessoaUsuario);

        st_RegContato *obterDadosRC() { return &dados; }
        st_RegContato *obterStRegContato() { return &dados; }

        bool getAtendimentoPorConta() { return dados.atendimentoPorConta; }
        bool getForceUsoProtocoloNaoAberto() { return dados.forceUsoProtocoloNaoAberto; }
        bool getGravarLinhaTelefonica() { return dados.gravarLinhaTelefonica; }
        bool getIsVolE() { return dados.isVolE; }
        bool getNaoEnvProtocoloSMS() { return dados.naoEnvProtocoloSMS; }
        bool getNaoValidarDados() { return dados.naoValidarDados; }

        char *getCdAreaRegistroSZ() { return dados.cdAreaRegistroSZ; }
        char *getCdConta() { return dados.cdConta; }
        char *getCdDigitoConta() { return dados.cdDigitoConta; }
        char *getDataANATEL() { return dados.dataANATEL; }
        char *getDataAtual() { return dados.dataAtual; }
        char *getDataCPrevio() { return dados.dataCPrevio; }
        char *getDataFechamento() { return dados.dataFechamento; }
        char *getDddSMSProtocolo() { return dados.dddSMSProtocolo; }
        char *getDsAcaoPortabilidade() { return dados.dsAcaoPortabilidade; }
        char *getDsClassificacaoSMS() { return dados.dsClassificacaoSMS; }
        char *getDtJanelaPortout() { return dados.dtJanelaPortout; }
        char *getIdAtendimentoProtocolo() { return dados.idAtendimentoProtocolo; }
        char *getIdSistemaOrigemProtocolo() { return dados.idSistemaOrigemProtocolo; }
        char *getIdTipoAberturaProtocolo() { return dados.idTipoAberturaProtocolo; }
        char *getIdUsuarioBKOSZ() { return dados.idUsuarioBKOSZ; }
        char *getInTipoPessoa() { return dados.inTipoPessoa; }
        char *getMsgSMS() { return dados.msgSMS; }
        char *getNmContato() { return dados.nmContato; }
        char *getNmFalandoCom() { return dados.nmFalandoCom; }
        char *getNrLinhaSMSProtocolo() { return dados.nrLinhaSMSProtocolo; }
        char *getNrProtocoloPortabilidade() { return dados.nrProtocoloPortabilidade; }
        char *getNrTelefone() { return dados.nrTelefone; }
        char *getObservacao() { return dados.observacao; }
        char *getObservacaoFechamento() { return dados.observacaoFechamento; }
        char *getPeso() { return dados.peso; }
        char *getSgFluxoAtendimento() { return dados.sgFluxoAtendimento; }
        char *getSgOperadoraSolicitante() { return dados.sgOperadoraSolicitante; }
        char *getSgRegraEncaminhamento() { return dados.sgRegraEncaminhamento; }
        char *getSgTipoPortabilidade() { return dados.sgTipoPortabilidade; }
        char *getInConsultor() { return dados.inConsultor; }
        char *getNrDocumento() { return dados.nrDocumento; }
        char *getNrOrdemVenda() { return dados.nrOrdemVenda; }
        bool existeNrOrdemVenda() { return dados.nrOrdemVenda[0]==0?false:true; }

        Collection *getAtendimentoLinhas() { return &dados.atendimentoLinhas; }
        Collection *getContatoComunic() { return &dados.contatoComunic; }
        Collection *getFormularioDinamico() { return &dados.formularioDinamico; }

        double getHoras() { return dados.horas; }
        double getHoras2() { return dados.horas2; }
        double getHorasRC() { return dados.horasRC; }

        int getCdAreaRegistro() { return dados.cdAreaRegistro; }
        int getIdAgrupamentoEstadoTpProc() { return dados.idAgrupamentoEstadoTpProc; }
        int getIdAgrupamentoEstadoTpProcFt() { return dados.idAgrupamentoEstadoTpProcFt; }
        long getIdAndamento() { return dados.idAndamento; }
        long getIdAtendimento() { return dados.idAtendimento; }
        long getIdAtendimentoOrigem() { return dados.idAtendimentoOrigem; }
        int getIdBaixa() { return dados.idBaixa; }
        int getIdBaixaMensagem() { return dados.idBaixaMensagem; }
        int getIdCanal() { return dados.idCanal; }
        int getIdChamadaTelefonica() { return dados.idChamadaTelefonica; }
        int getIdConta() { return dados.idConta; }
        int getIdContato() { return dados.idContato; }
        int getIdEstadoLinha() { return dados.idEstadoLinha; }
        int getIdGrupoAbertura() { return dados.idGrupoAbertura; }
        int getIdGrupoAtual() { return dados.idGrupoAtual; }
        int getIdGrupoTratamento() { return dados.idGrupoTratamento; }
        int getIdLinhaAtendimento() { return dados.idLinhaAtendimento; }
        int getIdLinhaTelefonica() { return dados.idLinhaTelefonica; }
        int getIdMensagemBaixa() { return dados.idMensagemBaixa; }
        int getIdPessoaConta() { return dados.idPessoaConta; }
        int getIdPessoaDePara() { return dados.idPessoaDePara; }
        int getIdPessoaDeParaCliente() { return dados.idPessoaDeParaCliente; }
        int getIdPessoaDeParaUsuario() { return dados.idPessoaDeParaUsuario; }
        long getIdPessoaLinhaHistorico() { return dados.idPessoaLinhaHistorico; }
        int getIdProcedencia() { return dados.idProcedencia; }
        int getIdSegmentacao() { return dados.idSegmentacao; }
        int getIdTerminal() { return dados.idTerminal; }
        int getIdTipoCarteira() { return dados.idTipoCarteira; }
        int getIdTipoLinha() { return dados.idTipoLinha; }
        int getIdTipoPessoa() { return dados.idTipoPessoa; }
        int getIdTipoReaberturaProcesso() { return dados.idTipoReaberturaProcesso; }
        int getIdTipoRelacionamento() { return dados.idTipoRelacionamento; }
        int getIdTipoRetorno() { return dados.idTipoRetorno; }
        int getIdUFOperadora() { return dados.idUFOperadora; }
        int getIdUsuarioBKO() { return dados.idUsuarioBKO; }
        int getInSMS() { return dados.inSMS; }
        int getTpOperacao() { return dados.tpOperacao; }
        int getIdContatoFolhaCampo()  { return dados.idContatoFolhaCampo; }

        void setAtendimentoPorConta(bool valor) { dados.atendimentoPorConta=valor; }
        void setCdAreaRegistro(int valor) { dados.cdAreaRegistro=valor; }
        void setCdAreaRegistroSZ(char *valor) { SAFE_STRNCPY(dados.cdAreaRegistroSZ,valor); }
        void setCdConta(char *valor) { SAFE_STRNCPY(dados.cdConta,valor); }
        void setCdDigitoConta(char *valor) { SAFE_STRNCPY(dados.cdDigitoConta,valor); }
        void setDataANATEL(char *valor) { SAFE_STRNCPY(dados.dataANATEL,valor); }
        void setDataAtual(char *valor) { SAFE_STRNCPY(dados.dataAtual,valor); }
        void setDataCPrevio(char *valor) { SAFE_STRNCPY(dados.dataCPrevio,valor); }
        void setDataFechamento(char *valor) { SAFE_STRNCPY(dados.dataFechamento,valor); }
        void setDddSMSProtocolo(char *valor) { SAFE_STRNCPY(dados.dddSMSProtocolo,valor); }
        void setDsAcaoPortabilidade(char *valor) { SAFE_STRNCPY(dados.dsAcaoPortabilidade,valor); }
        void setDsClassificacaoSMS(char *valor) { SAFE_STRNCPY(dados.dsClassificacaoSMS,valor); }
        void setDtJanelaPortout(char *valor) { SAFE_STRNCPY(dados.dtJanelaPortout,valor); }
        void setForceUsoProtocoloNaoAberto(bool valor) { dados.forceUsoProtocoloNaoAberto=valor; }
        void setGravarLinhaTelefonica(bool valor) { dados.gravarLinhaTelefonica=valor; }
        void setHoras(double valor) { dados.horas=valor; }
        void setHoras2(double valor) { dados.horas2=valor; }
        void setHorasRC(double valor) { dados.horasRC=valor; }
        void setIdAgrupamentoEstadoTpProc(int valor) { dados.idAgrupamentoEstadoTpProc=valor; }
        void setIdAgrupamentoEstadoTpProcFt(int valor) { dados.idAgrupamentoEstadoTpProcFt=valor; }
        void setIdAndamento(long valor) { dados.idAndamento=valor; }
        void setIdAtendimento(unsigned long valor) { dados.idAtendimento=valor; }
        void setIdAtendimentoOrigem(unsigned long  valor) { dados.idAtendimentoOrigem=valor; }
        void setIdAtendimentoProtocolo(char *valor) { SAFE_STRNCPY(dados.idAtendimentoProtocolo,valor); }
        void setIdBaixa(int valor) { dados.idBaixa=valor; }
        void setIdBaixaMensagem(int valor) { dados.idBaixaMensagem=valor; }
        void setIdCanal(int valor) { dados.idCanal=valor; }
        void setIdChamadaTelefonica(int valor) { dados.idChamadaTelefonica=valor; }
        void setIdConta(int valor) { dados.idConta=valor; }
        void setIdContato(int valor) { dados.idContato=valor; }
        void setIdEstadoLinha(int valor) { dados.idEstadoLinha=valor; }
        void setIdGrupoAbertura(int valor) { dados.idGrupoAbertura=valor; }
        void setIdGrupoAtual(int valor) { dados.idGrupoAtual=valor; }
        void setIdGrupoTratamento(int valor) { dados.idGrupoTratamento=valor; }
        void setIdLinhaAtendimento(int valor) { dados.idLinhaAtendimento=valor; }
        void setIdLinhaTelefonica(int valor) { dados.idLinhaTelefonica=valor; }
        void setIdMensagemBaixa(int valor) { dados.idMensagemBaixa=valor; }
        void setIdPessoaConta(int valor) { dados.idPessoaConta=valor; }
        void setIdPessoaDePara(int valor) { dados.idPessoaDePara=valor; }
        void setIdPessoaDeParaCliente(int valor) { dados.idPessoaDeParaCliente=valor; }
        void setIdPessoaDeParaUsuario(int valor) { dados.idPessoaDeParaUsuario=valor; }
        void setIdPessoaLinhaHistorico(long valor) { dados.idPessoaLinhaHistorico=valor; }
        void setIdProcedencia(int valor) { dados.idProcedencia=valor; }
        void setIdSegmentacao(int valor) { dados.idSegmentacao=valor; }
        void setIdContatoFolhaCampo(int valor) { dados.idContatoFolhaCampo=valor; }
        void setIdSistemaOrigemProtocolo(char *valor) { SAFE_STRNCPY(dados.idSistemaOrigemProtocolo,valor); }
        void setIdTerminal(int valor) { dados.idTerminal=valor; }
        void setIdTipoAberturaProtocolo(char *valor) { SAFE_STRNCPY(dados.idTipoAberturaProtocolo,valor); }
        void setIdTipoCarteira(int valor) { dados.idTipoCarteira=valor; }
        void setIdTipoLinha(int valor) { dados.idTipoLinha=valor; }
        void setIdTipoPessoa(int valor) { dados.idTipoPessoa=valor; }
        void setIdTipoReaberturaProcesso(int valor) { dados.idTipoReaberturaProcesso=valor; }
        void setIdTipoRelacionamento(int valor) { dados.idTipoRelacionamento=valor; }
        void setIdTipoRetorno(int valor) { dados.idTipoRetorno=valor; }
        void setIdUFOperadora(int valor) { dados.idUFOperadora=valor; }
        void setIdUsuarioBKO(int valor) { dados.idUsuarioBKO=valor; }
        void setIdUsuarioBKOSZ(char *valor) { SAFE_STRNCPY(dados.idUsuarioBKOSZ,valor); }
        void setInSMS(int valor) { dados.inSMS=valor; }
        void setTpOperacao(int valor) { dados.tpOperacao=valor; }
        void setInTipoPessoa(char *valor) { SAFE_STRNCPY(dados.inTipoPessoa,valor); }
        void setIsVolE(bool valor) { dados.isVolE=valor; }
        void setMsgSMS(char *valor) { SAFE_STRNCPY(dados.msgSMS,valor); }
        void setNaoEnvProtocoloSMS(bool valor) { dados.naoEnvProtocoloSMS=valor; }
        void setNaoValidarDados(bool valor) { dados.naoValidarDados=valor; }
        void setNmContato(char *valor) { SAFE_STRNCPY(dados.nmContato,valor); }
        void setNmFalandoCom(char *valor) { SAFE_STRNCPY(dados.nmFalandoCom,valor); }
        void setNrLinhaSMSProtocolo(char *valor) { SAFE_STRNCPY(dados.nrLinhaSMSProtocolo,valor); }
        void setNrProtocoloPortabilidade(char *valor) { SAFE_STRNCPY(dados.nrProtocoloPortabilidade,valor); }
        void setNrTelefone(char *valor) { SAFE_STRNCPY(dados.nrTelefone,valor); }
        void setObservacao(char *valor) { SAFE_STRNCPY(dados.observacao,valor); }
        void setObservacaoFechamento(char *valor) { SAFE_STRNCPY(dados.observacaoFechamento,valor); }
        void setPeso(char *valor) { SAFE_STRNCPY(dados.peso,valor); }
        void setSgFluxoAtendimento(char *valor) { SAFE_STRNCPY(dados.sgFluxoAtendimento,valor); }
        void setSgOperadoraSolicitante(char *valor) { SAFE_STRNCPY(dados.sgOperadoraSolicitante,valor); }
        void setSgRegraEncaminhamento(char *valor) { SAFE_STRNCPY(dados.sgRegraEncaminhamento,valor); }
        void setSgTipoPortabilidade(char *valor) { SAFE_STRNCPY(dados.sgTipoPortabilidade,valor); }
        void setInConsultor(char *valor) { SAFE_STRNCPY(dados.inConsultor,valor); }
        void setNrDocumento(char *valor) { SAFE_STRNCPY(dados.nrDocumento,valor); }
        void setNrOrdemVenda(char *valor) { SAFE_STRNCPY(dados.nrOrdemVenda,valor); }

    private:

        // Funções principais da classe.
        void gravaAtendimento();
        void gravaAndamento();
        void gravaAndamentoObservacao();
        void gravaAtendimentoConta();
        void gravaAtendimentoOrigem();
        void gravaAtendimentoContato();
        void gravaAtendimentoContatoComunic();
        void gravaAtendimentoPessoa();
        void gravaAtendimentoFormularioDinamico();
        void gravaAtendimentoObservacao();
        void gravaLinhaTelefonicaAtendimento();
        void gravaChamadaAtendimento();
        void obterConsultorRelacionamento();
        void gravaAtendimentoLinhas();
        //void gravaSmsProtocolo();
        void gravaAtendimentoPriorizacao(st_Atendimento *dadosAtendimento
                                        ,st_vlAtendimento *statusAtendimento
                                        ,st_AtendimentoPessoa *dadosAtendimentoPessoa
                                        ,st_vlAtendimentoPessoa *statusAtendimentoPessoa
                                        //,st_AtendimentoLinha *dadosAtendimentoLinha
                                        //,st_vlAtendimentoLinha *statusAtendimentoLinha
                                        ,int idTipoLinha);
        void gravaPessoaPortabilidadeHist();
        // Funções de apoio.
        void obtemDataFechamento(double horas,char*dataFechamento,int incremento );
        void obtemDataFechamentoAnatel(char *dataFechamentoAnatel);

        // Registra contato para o DPR
        void registrarAcaoDPR();

        // Chamadas externas
        void atualizarPalitagem();

#ifndef WIN32
        char* remoteCall(char* nomeServico, char* sMsgIn, int lFlags);
#endif

        cRegContatoPC* rcpc;

    private:
        st_Atendimento          m_stDadosAtendimento;
        st_vlAtendimento        m_vlDadosAtendimento;

        st_AtendimentoPessoa    m_stDadosPessoa;
        st_vlAtendimentoPessoa  m_vlDadosPessoa;

        st_AtendimentoLinha     m_stDadosLinha;
        st_vlAtendimentoLinha   m_vlDadosLinha;
};
