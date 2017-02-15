/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:35 $
 **/

#ifndef CWFPPRELATORIOS
    #define CWFPPRELATORIOS

#include <tuxfw.h>
#include "stWFPPRelatorios.h"
#include "../../../commons/definesAtendimento.h"

#ifdef WIN32
#pragma warning(disable:4786)
#endif
#include <string>
using namespace std;

#define AGENDAR_A                5
#define AGENDAR_AR              16
#define FECHAR_F                10
#define FECHAR_DR               17
#define ENCERRAR_BKO_EB          8
#define ENCERRAR_BKO_EBR        13
#define ENCERRAR_BKO_EBS        14
#define ENCERRAR_BKO_MASSA_EBM  23

#define ABERTURA    1
#define TRATAMENTO  2
#define RETORNO     3

class cWFPPRelatorios
{
    st_PPRelatorios   *mDados;
    st_vlPPRelatorios *mStatus;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFPPRelatorios();
        cWFPPRelatorios(st_PPRelatorios* dados, st_vlPPRelatorios* indicator, XMLGen*xml_g);
        cWFPPRelatorios(DOMNode*dnode, XMLGen*xml_g);
        cWFPPRelatorios(st_PPRelatorios* dados,st_vlPPRelatorios* status);

        ~cWFPPRelatorios();

        bool atualizar();

        const char *obterCodErro() { return codErro.c_str(); }
        const char *obterMsgErro() { return msgErro.c_str(); }

        void setarIdAtendimento(long idAtendimento) { if (mDados) mDados->idAtendimento=idAtendimento;if (mStatus) mStatus->idAtendimento=1; }
        void setarIdGrupoAtual(int idGrupoAtual) { if (mDados) mDados->idGrupoAtual=idGrupoAtual;if (mStatus) mStatus->idGrupoAtual=1; }
        void setarIdPessoaUsuario(int idPessoaUsuario) { if (mDados) mDados->idPessoaUsuario=idPessoaUsuario;if (mStatus) mStatus->idPessoaUsuario=1; }
        void setarIdUFOperadora(int idUFOperadora) { if (mDados) mDados->idUFOperadora=idUFOperadora;if(mStatus) mStatus->idUFOperadora=1; }
        void setarIdGrupoOperadora(int idGrupoOperadora) { if (mDados) mDados->idGrupoOperadora=idGrupoOperadora;if(mStatus) mStatus->idGrupoOperadora=1; }
        void setarIdFase(int idFase) { if (mDados) mDados->idFase=idFase;if(mStatus) mStatus->idFase=1; }
        void setarCdAreaRegistro(int cdAreaRegistro) { if (mDados) mDados->cdAreaRegistro=cdAreaRegistro;if(mStatus) mStatus->cdAreaRegistro=1; }
        void setarIdMotivo(int idMotivo) { if (mDados) mDados->idMotivo=idMotivo;if(mStatus) mStatus->idMotivo=1; }
        void setarDtAbertura(const char *dtAbertura) { if (mDados) SAFE_STRNCPY(mDados->dtAbertura,dtAbertura);if(mStatus) mStatus->dtAbertura=1; }

        void setarQtFechados(int qtFechados) { if (mDados) mDados->qtFechados=qtFechados;if (mStatus) mStatus->qtFechados=1; }
        void setarQtRetorno(int qtRetorno) { if (mDados) mDados->qtRetorno=qtRetorno;if (mStatus) mStatus->qtRetorno=1; }
        void setarQtTratamento(int qtTratamento) { if (mDados) mDados->qtTratamento=qtTratamento;if (mStatus) mStatus->qtTratamento=1; }
        void setarQtEncerrados(int qtEncerrados) { if (mDados) mDados->qtEncerrados=qtEncerrados;if (mStatus) mStatus->qtEncerrados=1; }
        bool setarValoresRelProdRepresentante(int idAtividade,int idFase);

        int obterIdUFOperadora() {if (mDados && mStatus ) { return mStatus->idUFOperadora >=0 ? mDados->idUFOperadora : -1;} else { return -1; } }
        int obterIdGrupoOperadora() {if (mDados && mStatus ) { return mStatus->idGrupoOperadora >=0 ? mDados->idGrupoOperadora : -1;} else { return -1; } }
        int obterIdGrupoAtual() {if (mDados && mStatus ) { return mStatus->idGrupoAtual >=0 ? mDados->idGrupoAtual : -1;} else { return -1; } }
        int obterIdFase() {if (mDados && mStatus ) { return mStatus->idFase >=0 ? mDados->idFase : -1;} else { return -1; } }
        int obterIdMotivo() {if (mDados && mStatus ) { return mStatus->idMotivo >=0 ? mDados->idMotivo : -1;} else { return -1; } }
        const char *obterDtAbertura() {if (mDados && mStatus ) { return mStatus->dtAbertura >=0 ? mDados->dtAbertura : 0;} else { return 0; } }

        void resetarParametros() { if (mDados) memset(mDados,0,sizeof(st_PPRelatorios));
                                   if (mStatus) memset(mStatus,-1,sizeof(st_vlPPRelatorios)); }

    private:
        bool alocado;
        long idAtendimento;

        string codErro;
        string msgErro;

        void carregaDados();
};

#endif
