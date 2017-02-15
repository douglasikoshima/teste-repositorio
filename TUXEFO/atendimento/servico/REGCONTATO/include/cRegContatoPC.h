/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.5.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#ifndef SQLCA
    #define SQLCA_NONE

#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#endif

#include <tuxfw.h>
#include "../../../commons/queryMacro.h"
#include "stRegContato.h"
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoPessoa/include/cWFAtendimentoPessoa.h"
#include "../../AtendimentoLinha/include/cWFAtendimentoLinha.h"


class cRegContatoPC
{
    st_RegContato *dados;
    st_DadosEstadoProtocolo *dadosProtocolo;

    TuxHelper tx;

    public:
        cRegContatoPC(st_RegContato *dados,st_DadosEstadoProtocolo *dadosProtocolo)
        {
            this->dados = dados;
            this->dadosProtocolo = dadosProtocolo;
        }

        int buscarRegional(int prefixoPrm);
        int obtemPrazoANATEL();
        int obtemIdContato(char *nmPath);
        int obterGrupoTratamentoPortout(const char *sgTipoPortabilidade,const char *dsAcaoPortabilidade);

        void dataAtual(char* data/*,char * dataSMS*/);
        bool proCObterDadosProtocolo(const char *idAtendimentoProtocolo);
        void obtemPrazoAtendimentoSegmentado(double* horas,int _idContato, int _idSegmentacao, int _idProcedencia);
        bool obtemPesoAtendimento(char* peso,int _idContato,int _idSegmentacao,int _idProcedencia,int _idTipoCarteira,const char *sgTipoPortabilidade,int _idFormulario,bool _isVolE);
        void proCDataFechamento(long sIdAtendimento, char* dataFechamento);
        void proCDataFechamentoAnatel(long  sIdAtendimento, char* dataFechamento);
        void obtemDadosLinhaTelefonica(int _idPessoaDeParaCliente,int _idLinhaTelefonica,long* _idPessoaLinhaHistorico,int* _idEstadoLinha,int* _idTipoLinha);
        void obtemSequenciaAbertura(int* sequencia,int _idContato,int _idGrupoAbertura);
        void obtemTipoRetornoAtendimento(int* tipo, int* telefone);
        void obtemTipoRetornoAtendimento_11( char * idTipoRetornoPrm );
        void obterDadosContatoFolha(st_RegContato *dados);
        bool obtemDadosConta(int* idConta, char* conta, char* digitoConta);
        void obtemIdTipoPessoa(int* _idTipoPessoa,char *_inTipoPessoa);
        void AtualizarDadosPessoaDeParaDPR(int _idPessoaDePara,XMLDPR *xmlDpr);
        void AtualizarDadosPessoaDPR(int _idPessoaDePara,XMLDPR *xmlDpr);
        void AtualizarDadosPessoaLinhaHistorico(long _idPessoaLinhaHistorico,XMLDPR *xmlDpr);
        void alterarFilaSmsProtocolo(st_RegContato *dados);
        void gravarAtendimentoLinhas(long idAtendimento,Collection *atendimentoLinhas);
        void registraTabelaPriorizacao(int _idTipoReabertura
                                      ,int _idTipoPessoa
                                      ,st_Atendimento *dados
                                      ,st_vlAtendimento *status
                                      ,st_AtendimentoPessoa *dadosAtendimentoPessoa
                                      ,st_vlAtendimentoPessoa *statusAtendimentoPessoa);
        void gravaPessoaPortabilidadeHist(int idUsuarioAlteracao, 
                                          int idPessoaDePara, 
                                          int idTipoLinha, 
                                          int cdAreaRegistro, 
                                          const char *nrTelefone, 
                                          const char *sgTipoPortabilidade,
                                          const char *dsAcaoPortabilidade,
                                          const char *dsObservacao,
                                          const char *sgOperadoraSolicitante,
                                          const char *nrProtocoloPortabilidade,
                                          const char *dtJanelaPortout);
        void registraTerminalVOL(long * _idAtendimento, int* _idTerminal);
        bool proCOperadoraNaoCliente( const int _idPessoaDePara, int* _idUFOperadoraNaoCliente );
        bool ValidaPessoaDePara( const int idPessoaDeParaPrm );
        void registraErro(DOMNode *dnode,const char *_msgErro);
        bool proCObterConsultorRelacionamento(const int idConta
                                             ,const int idPessoaDeParaCliente
                                             ,int *idPessoaConta);
        bool proCObterIdUserUra(const char *userNN,char *idPessoaUsuario);
        bool proCVerificarUserNumericoSimNao(const char *userNN);

    private:
        void sql_error_WFcRegContatoPC( sqlca * sqlca );
        char *obterDescricaoPath(char *stringIn, char *stringOut);
};