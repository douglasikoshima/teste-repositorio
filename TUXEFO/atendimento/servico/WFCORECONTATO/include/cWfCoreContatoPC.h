/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:34 $
 **/

#ifndef __CWFCORECONTATOPC_H__
#define __CWFCORECONTATOPC_H__

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>

#include <string>
using namespace std;

#include <tuxfw.h>

#include "../../../commons/definesAtendimento.h"
#include "stWfCoreContato.h"

class cWfCoreContatoPC
{
    public:
        TuxHelper tx;

    private:
        st_Parametros dadosParam;
        struct sqlca sqlca;

    public:
        cWfCoreContatoPC() {}
        ~cWfCoreContatoPC() {}

    public:
        int VerificarValidadeAcaoPortabilidade(const char *idAcaoPortabilidade);
        int ExisteProtocoloPortabilidade(const char *nrProtocoloPortabilidade);

        bool ObterIdPessoaUsuarioPortabilidade();
        bool ObterIdAtendimento(const char *nrBilhetePortabilidade);
        bool VerificarIdProcedencia(const char *cdProcedencia);
        bool ObterContatoFuncionalidade(const char *idAcaoPortabilidade
                                       ,const char *sgTipoPortabilidade);

        void ObterDadosAtividadeWorkflow(const char *sgAtividade);
        void ObterAgrupamentoFuturo();
        void ObterDadosMotivo(const char *dsOperacao);
        void ObterSgOperadoraSolicitante(const char *idOperadoraSolicitante);
        //=================================================================================
        // Rotinas copiadas do REGCONTATOFO
        void ObterDadosLinPessoaCliente(const char *ddd,const char *nrLinha,const char *dsOperacao);
        void ObterIdConta(const char *_idLinhaTelefonica,const char *_idTipoRelacionamento);
        void ObterIdContato(const char *_cdContato, char **_idContato);
        void ObterDadosPessoaCliente(const char *dsOperacao);
        void ObterDsAcaoPortabilidade(const char *idAcaoPortabilidade);
        void ObterPrazosJanelaPortout(const char *dtJanelaPortout
                                     ,int *dsValorParametro
                                     ,int *prazoJanelaPortout);
        void VerificarProcedencia(const char *_idProcedencia);
        void VerificarCanal(const char *_idCanal);
        void VerificarContato(const char *_idContato);
        const char *getCdFuncionalidade() {return dadosParam.cdFuncionalidade.c_str();}
        const char *getDsAcaoPortabilidade() {return dadosParam.dsAcaoPortabilidade.c_str();}
        const char *getDsTipoAtividade() {return dadosParam.dsTipoAtividade.c_str();}
        const char *getDsPathContato() {return dadosParam.dsPathContato.c_str();}
        const char *getDtUltimaAlteracao() {return dadosParam.dtUltimaAlteracao.c_str();}
        const char *getIdAcaoPortabilidade() {return dadosParam.idAcaoPortabilidade.c_str();}
        const char *getIdAgrupamentoEstadoTpProc() {return dadosParam.idAgrupamentoEstadoTpProc.c_str();}
        const char *getIdAgrupamentoEstadoTpProcFt() {return dadosParam.idAgrupamentoEstadoTpProcFt.c_str();}
        const char *getIdAtendimento() {return dadosParam.idAtendimento.c_str();}
        const char *getIdAtividade() {return dadosParam.idAtividade.c_str();}
        const char *getIdCanal() {return dadosParam.idCanal.c_str();}
        const char *getIdConta() {return dadosParam.idConta.c_str();}
        const char *getIdContato() {return dadosParam.idContato.c_str();}
        const char *getIdFase() {return dadosParam.idFase.c_str();}
        const char *getIdGrupoAbertura() {return dadosParam.idGrupoAbertura.c_str();}
        const char *getIdGrupoTratamento() {return dadosParam.idGrupoTratamento.c_str();}
        const char *getIdLinhaTelefonica() {return dadosParam.idLinhaTelefonica.c_str();}
        const char *getIdLinhaTelefonicaCliente() {return dadosParam.idLinhaTelefonicaCliente.c_str();}
        const char *getIdMotivo() {return dadosParam.idMotivo.c_str();}
        const char *getIdPessoaCliente() {return dadosParam.idPessoaCliente.c_str();}
        const char *getIdPessoaDeParaCliente() {return dadosParam.idPessoaDeParaCliente.c_str();}
        const char *getIdPessoaLinhaHistorico() {return dadosParam.idPessoaLinhaHistorico.c_str();}
        const char *getIdPessoaUsuario() {return dadosParam.idPessoaUsuario.c_str();}
        const char *getIdProcedencia() {return dadosParam.idProcedencia.c_str();}
        const char *getIdSegmentacaoCliente() {return dadosParam.idSegmentacaoCliente.c_str();}
        const char *getIdTipoCarteiraCliente() {return dadosParam.idTipoCarteiraCliente.c_str();}
        const char *getIdTipoRelacionamentoCliente() {return dadosParam.idTipoRelacionamentoCliente.c_str();}
        const char *getIdUsuarioAlteracao() {return dadosParam.idUsuarioAlteracao.c_str();}
        const char *getInEnviaSms() {return dadosParam.inEnviaSms.c_str();}
        const char *getNmPessoaCliente() {return dadosParam.nmPessoaCliente.c_str();}
        const char *getNmServico() {return dadosParam.nmServico.c_str();}
        const char *getSgAtividade() {return dadosParam.sgAtividade.c_str();}
        const char *getSgSubSistema() {return dadosParam.sgSubSistema.c_str();}
        const char *getSgTipoPortabilidade() {return dadosParam.sgTipoPortabilidade.c_str();}
        const char *getTipoOperacao() {return dadosParam.tipoOperacao.c_str();}
        const char *getSgOperadoraSolicitante() {return dadosParam.sgOperadoraSolicitante.c_str();}
        const char *getDsTipoPessoa() {return dadosParam.dsTipoPessoa.c_str();}

        void setCdFuncionalidade(const char *dado) { dadosParam.cdFuncionalidade = dado; }
        void setDsAcaoPortabilidade(const char *dado) { dadosParam.dsAcaoPortabilidade = dado; }
        void setDsPathContato(const char *dado) { dadosParam.dsPathContato = dado; }
        void setDsTipoAtividade(const char *dado) { dadosParam.dsTipoAtividade = dado; }
        void setDtUltimaAlteracao(const char *dado) { dadosParam.dtUltimaAlteracao = dado; }
        void setIdAcaoPortabilidade(const char *dado) { dadosParam.idAcaoPortabilidade = dado; }
        void setIdAgrupamentoEstadoTpProc(const char *dado) { dadosParam.idAgrupamentoEstadoTpProc = dado; }
        void setIdAgrupamentoEstadoTpProcFt(const char *dado) { dadosParam.idAgrupamentoEstadoTpProcFt = dado; }
        void setIdAtendimento(const char *dado) { dadosParam.idAtendimento = dado; }
        void setIdAtividade(const char *dado) { dadosParam.idAtividade = dado; }
        void setIdCanal(const char *dado) { dadosParam.idCanal = dado; }
        void setIdConta(const char *dado) { dadosParam.idConta = dado; }
        void setIdContato(const char *dado) { dadosParam.idContato = dado; }
        void setIdFase(const char *dado) { dadosParam.idFase = dado; }
        void setIdGrupoAbertura(const char *dado) { dadosParam.idGrupoAbertura = dado; }
        void setIdGrupoTratamento(const char *dado) { dadosParam.idGrupoTratamento = dado; }
        void setIdLinhaTelefonica(const char *dado) { dadosParam.idLinhaTelefonica = dado; }
        void setIdLinhaTelefonicaCliente(const char *dado) { dadosParam.idLinhaTelefonicaCliente = dado; }
        void setIdMotivo(const char *dado) { dadosParam.idMotivo = dado; }
        void setIdPessoaCliente(const char *dado) { dadosParam.idPessoaCliente = dado; }
        void setIdPessoaDeParaCliente(const char *dado) { dadosParam.idPessoaDeParaCliente = dado; }
        void setIdPessoaLinhaHistorico(const char *dado) { dadosParam.idPessoaLinhaHistorico = dado; }
        void setIdPessoaUsuario(const char *dado) { dadosParam.idPessoaUsuario = dado; }
        void setIdProcedencia(const char *dado) { dadosParam.idProcedencia = dado; }
        void setIdSegmentacaoCliente(const char *dado) { dadosParam.idSegmentacaoCliente = dado; }
        void setIdTipoCarteiraCliente(const char *dado) { dadosParam.idTipoCarteiraCliente = dado; }
        void setIdTipoRelacionamentoCliente(const char *dado) { dadosParam.idTipoRelacionamentoCliente = dado; }
        void setIdUsuarioAlteracao(const char *dado) { dadosParam.idUsuarioAlteracao = dado; }
        void setInEnviaSms(const char *dado) { dadosParam.inEnviaSms = dado; }
        void setNmPessoaCliente(const char *dado) { dadosParam.nmPessoaCliente = dado; }
        void setNmServico(const char *dado) { dadosParam.nmServico = dado; }
        void setSgAtividade(const char *dado) { dadosParam.sgAtividade = dado; }
        void setSgSubSistema(const char *dado) { dadosParam.sgSubSistema = dado; }
        void setSgTipoPortabilidade(const char *dado) { dadosParam.sgTipoPortabilidade = dado; }
        void setTipoOperacao(const char *dado) { dadosParam.tipoOperacao = dado; }
        void setSgOperadoraSolicitante(const char *dado) { dadosParam.sgOperadoraSolicitante = dado; }
        void setDsTipoPessoa(const char *dado) { dadosParam.dsTipoPessoa = dado; }

        bool isEmptyCdFuncionalidade() {return dadosParam.cdFuncionalidade.size()==0?true:false;}
        bool isEmptyDsAcaoPortabilidade() {return dadosParam.dsAcaoPortabilidade.size()==0?true:false;}
        bool isEmptyDsPathContato() {return dadosParam.dsPathContato.size()==0?true:false;}
        bool isEmptyDsTipoAtividade() {return dadosParam.dsTipoAtividade.size()==0?true:false;}
        bool isEmptyDtUltimaAlteracao() {return dadosParam.dtUltimaAlteracao.size()==0?true:false;}
        bool isEmptyIdAcaoPortabilidade() {return dadosParam.idAcaoPortabilidade.size()==0?true:false;}
        bool isEmptyIdAgrupamentoEstadoTpProc() {return dadosParam.idAgrupamentoEstadoTpProc.size()==0?true:false;}
        bool isEmptyIdAgrupamentoEstadoTpProcFt() {return dadosParam.idAgrupamentoEstadoTpProcFt.size()==0?true:false;}
        bool isEmptyIdAtendimento() {return dadosParam.idAtendimento.size()==0?true:false;}
        bool isEmptyIdAtividade() {return dadosParam.idAtividade.size()==0?true:false;}
        bool isEmptyIdCanal() {return dadosParam.idCanal.size()==0?true:false;}
        bool isEmptyIdConta() {return dadosParam.idConta.size()==0?true:false;}
        bool isEmptyIdContato() {return dadosParam.idContato.size()==0?true:false;}
        bool isEmptyIdFase() {return dadosParam.idFase.size()==0?true:false;}
        bool isEmptyIdGrupoAbertura() {return dadosParam.idGrupoAbertura.size()==0?true:false;}
        bool isEmptyIdGrupoTratamento() {return dadosParam.idGrupoTratamento.size()==0?true:false;}
        bool isEmptyIdLinhaTelefonica() {return dadosParam.idLinhaTelefonica.size()==0?true:false;}
        bool isEmptyIdLinhaTelefonicaCliente() {return dadosParam.idLinhaTelefonicaCliente.size()==0?true:false;}
        bool isEmptyIdMotivo() {return dadosParam.idMotivo.size()==0?true:false;}
        bool isEmptyIdPessoaCliente() {return dadosParam.idPessoaCliente.size()==0?true:false;}
        bool isEmptyIdPessoaDeParaCliente() {return dadosParam.idPessoaDeParaCliente.size()==0?true:false;}
        bool isEmptyIdPessoaLinhaHistorico() {return dadosParam.idPessoaLinhaHistorico.size()==0?true:false;}
        bool isEmptyIdPessoaUsuario() {return dadosParam.idPessoaUsuario.size()==0?true:false;}
        bool isEmptyIdProcedencia() {return dadosParam.idProcedencia.size()==0?true:false;}
        bool isEmptyIdSegmentacaoCliente() {return dadosParam.idSegmentacaoCliente.size()==0?true:false;}
        bool isEmptyIdTipoCarteiraCliente() {return dadosParam.idTipoCarteiraCliente.size()==0?true:false;}
        bool isEmptyIdTipoRelacionamentoCliente() {return dadosParam.idTipoRelacionamentoCliente.size()==0?true:false;}
        bool isEmptyIdUsuarioAlteracao() {return dadosParam.idUsuarioAlteracao.size()==0?true:false;}
        bool isEmptyInEnviaSms() {return dadosParam.inEnviaSms.size()==0?true:false;}
        bool isEmptyNmPessoaCliente() {return dadosParam.nmPessoaCliente.size()==0?true:false;}
        bool isEmptyNmServico() {return dadosParam.nmServico.size()==0?true:false;}
        bool isEmptySgAtividade() {return dadosParam.sgAtividade.size()==0?true:false;}
        bool isEmptySgSubSistema() {return dadosParam.sgSubSistema.size()==0?true:false;}
        bool isEmptySgTipoPortabilidade() {return dadosParam.sgTipoPortabilidade.size()==0?true:false;}
        bool isEmptyTipoOperacao() {return dadosParam.tipoOperacao.size()==0?true:false;}
        bool isEmptySgOperadoraSolicitante() {return dadosParam.sgOperadoraSolicitante.size()==0?true:false;}

    private:
        void sql_error_WfCoreContatoPC();

};

#endif