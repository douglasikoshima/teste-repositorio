/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.4.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#ifndef __C_ENC_CONTATO_PC_H__
#define __C_ENC_CONTATO_PC_H__

#ifndef SQLCA
    #define SQLCA_NONE

#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#endif

#include <tuxfw.h>
#include"../../../commons/queryMacro.h"
#include "stEncContato.h"

class cEncContatoPC
{
    public:
        cEncContatoPC() {}
        ~cEncContatoPC() {}

        void dataAtual(char* data);
        void dataAndamento(char* data);
        void proCDataEntrada(long  sIdAtendimento, char* telefone, char* data);
        void registraContatoLinha(long  _idAtendimento, int _idContato, long _idPessoaLinhaHistorico, int _idUsuarioAlteracao);
        bool existeContatoLinha(int _idContato, long _idPessoaLinhaHistorico);
        void atualizarContatoLinha(long  _idAtendimento, int _idContato, long _idPessoaLinhaHistorico, int _idUsuarioAlteracao);

        // bool proCAssociarUsuariosDisponiveis( const unsigned long idAtdCriadoPrm,const unsigned long idPessoaLinhaHistPrm,const unsigned long idGrupoPrm );
        bool proCListaGrupoAssociadoCRI(const unsigned long idPessoaLinhaHistoricoPrm,unsigned long *idGrupoPrm,unsigned long idUFOperadoraPrm,unsigned long idGrupoAberturaPrm);
        bool proCComprovaVariaveis(const unsigned long idPessoaPrm,st_CRI * dadosPrm,unsigned long * idGrupoCRI);
        bool proCComprovaVariaveisUsuario( const long idAtendimentoPrm,const unsigned long idPessoaPrm,const char * dtEntradaPrm,st_CRI * dadosPrm,long * idAtendimentoAnterior,unsigned long * idGrupoCRI,unsigned long idPessoaUsuarioPrm);
        bool proCBuscaConsultorDentroPrazo(unsigned long idPessoaPrm,unsigned long idContato,long * idAtendimentoAnterior,unsigned long * idGrupoCRI);
        bool proCObterGrupoUsuario( unsigned long idGrupoCRIPrm,unsigned long idPessoaUsuarioPrm,st_CRI * dadosPrm,unsigned long *idGrupoCRI);

        int obtemSequencia(int idGrupo,int idContato);

        // void proCInsereAtendimentoGrupoAtual(const long idAtendimentoPrm,const unsigned long idGrupoPrm,const char * dtEntradaPrm);
        void proCInsereTratamentoCRI(const int idPessoaUsuarioPrm,const long idAtendimentoPrm);
        void proCInsereAtendimentoCRI(const long idAtendimentoPrm,const unsigned long idPessoaUsuarioPrm,const unsigned long idPessoaLinhaHistPrm);
        // void proCInsereAtendimentoUsuarioAtual(const long idAtendimentoPrm,const unsigned long idPessoaUsuarioPrm );
        void proCLstUsuarioGrupoCRI( const long idAtdAnteriorPrm,const unsigned long idPessoaLinhaHistorico,unsigned long *idPessoaUsuarioPrm );
        void proCInsereAtendimentoGrupoCRI(const long idAtendimentoPrm,const unsigned long idGrupoPrm,const unsigned long idPessoaLinhaHistoricoPrm);
        void proCInsereAtdTratamentoGrupoCRI(const long idAtendimentoPrm,const unsigned long idGrupoPrm);
        void proCAtualizarTipoRetornoContatoCRI(long idAtendimentoPrm);
        bool proCObtemGestorPJ(const char *nrDocumento, int *idPessoaUsuarioGestorPJ,int *idGrupoAtualGestorPJ);
        bool proCObtemGerentePJ(int idPessoaDeParaCliente,int *idPessoaUsuarioGerentePJ,int *idGrupoAtualGerentePJ);
        void proCDestinoPossivel(int idFormulario,int idContato,int idTipoCarteira,int idSegmentacao,int idUfOperadora,char *idDestPossFormulario);
        int proCConsultaProximoAgrupamento(int idAtividade,int idAgrupamentoEstadoTpProc);
        // (inc.#3288 HV) void proCInsereAtendimentoPorContaCRI(const long idAtendimentoPrm);

    private:
        void sql_error_WFEncContato( sqlca * sqlca );
};

#endif
