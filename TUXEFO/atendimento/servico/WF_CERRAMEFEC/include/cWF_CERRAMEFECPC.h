/**
 * @modulo  Atendimento
 * @usecase Fechamento em massa
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#ifndef __CWF_CERRAMEFECPC_H__
#define __CWF_CERRAMEFECPC_H__
#define STRLENNULL( y ) ( y == NULL ? 0 : strlen( y )  )

#ifndef SQLCA
#define SQLCA_NONE
#   include <sqlca.h>
#   include <sqlda.h>
#   include <ctype.h>
#endif

#include "../../Andamento/include/cWFAndamento.h"
#include "../../AndamentoObservacao/include/cWFAndamentoObservacao.h"
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoBaixaHistorico/include/cWFAtendimentoBaixaHistorico.h"
#include "../../AtendimentoFechamento/include/cWFAtendimentoFechamento.h"
#include "../../AtendimentoFrm/include/cWFAtendimentoFrm.h"
#include "../../AtendimentoFrmCampo/include/cWFAtendimentoFrmCampo.h"
#include "../../AtendimentoUsuarioAtual/include/cWFAtendimentoUsuarioAtual.h"
#include "../../AtendimentoGerarXMLDPR/include/cWFAtendimentoGerarXMLDPR.h"
#include "../../AtendimentoGrupoDevolucao/include/cWFAtendimentoGrupoDevolucao.h"
#include "../../AtendimentoUsuarioDevolucao/include/cWFAtendUsuarioDevolucao.h"
#include "../../AtendimentoPausa/include/cWFAtendimentoPausa.h"
#include "../../WF_ATDALTERPROT/include/cWfAtdAlterProt.h"

#include <tuxfw.h>
#include "../include/stWF_CERRAMEFEC.h"
#include"../../../commons/emailUtil/include/cEmailUtil.h"
#include"../../../commons/smsUtil/include/cSMSUtil.h"

class cWF_CERRAMEFECPC
{
    public:
        cWF_CERRAMEFECPC();

        ~cWF_CERRAMEFECPC();

        //Recupera dados do sistema, NAO VAI até o ORACLE, pois na criação da classe
        //os dados são recuperado no ORACLE
        char* getData( void );
        char* getHota( void );
        char* getDataHora( void );
        //Metodos de utilidade geral
        char* alltrim(char *pszString);
        char* rtrim(char *pszString);
        char* ltrim(char *pszString);

    private:
        //Armazena data, hora e datahora
        XMLDPR *xmlDpr;
        struct ST_SYSTEMA stSistema;

        void sistema( void );
        void sqlErro( sqlca * sqlca );

    private:
        void alterarAtendimento(const char *idAtendimento,int idFase,const char *nrNivel,const char *qtInsistencia,const char *idUsuario,XMLDPR *xmlDpr);
        void decrementarPendentes(const char *idAtendimentoProtocolo,const char *idUsuarioAlteracao);
        void inserirAndamento(const char *idAtividadeMassa,const char *idAgrupamentoEstadoTProcFut,const char *idAtendimento,const char *idUsuario,const char *idGrupo,char *idAndamento,XMLDPR *xmlDpr);
        void inserirAndamentoObservacao(const char *idAndamento,const char *idUsuario,const char *dsObservacao,XMLDPR *xmlDpr);
        void inserirAtendimentoBaixaHistorico(const char *idAtendimento,const char *idBaixa,const char *idFase,const char *idUsuario,const char *idAndamento,char *idAtendimentoBaixaHistorico,XMLDPR *xmlDpr);
        void inserirAtendimentoFechamento(const char *idAtendimento,const char *idUsuario,const char *idAndamento,XMLDPR *xmlDpr);
        void inserirAtendimentoFrm(const char *idAtendimento,const char *idUsuario,const char *idCampo,char *idAtendimentoFrm,XMLDPR *xmlDpr);
        void inserirAtendimentoFrmCampo(const char *idDominio,const char *idAtendimentoFrm,const char *dsValor,const char *idUsuario,int idContato,XMLDPR *xmlDpr);
        void excluirAtendimentoUsuarioAtual(const char *idAtendimento,const char *idUsuario,XMLDPR *xmlDpr);
        void atualizarAtendimentoUsuarioAtual(const char *idAtendimento,const char *idUsuario,XMLDPR *xmlDpr);
        void excluirAtendimentoPausa(const char *idAtendimento);
        void atualizarAtendimentoGrupoReceptor(const char *idAtendimento,const char *idGrupo,const char *idUsuario);
        void atualizarAtendimentoUsuarioReceptor(const char *idAtendimento,const char *idPessoaUsuario,const char *idUsuario);
        void registrarAcaoDPR(int idUsuario,int /*idContato*/,char *idAtendimento,XMLDPR *xmlDpr);

    protected:
        bool sendMail( struct ST_DADOS_ENTRADA* stDados );
        void encerrarProcessos( struct ST_DADOS_ENTRADA* stDados );
        void removeAtendimentoGrupoAtual( struct ST_DADOS_ENTRADA* stDados );
        void removeCancelamentoSolicitado( struct ST_DADOS_ENTRADA* stDados );
        void removeAtendimentoSuspeito( struct ST_DADOS_ENTRADA* stDados );
        void incluirFormulario( char* cidAtendimento,struct ST_ATENDIMENTOFORMULARIOREG* stForm, struct ST_DADOS_ENTRADA* stDados );
        bool preProcessarRelEncFech( long idAtendimento,int idGrupoAtual,int idAgrEstTPrFt,int idPessoaUsuario,int idAtividade);
};

#endif
