/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.3.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFCoreWorkflow.h"

#include "../../../../atendimento/servico/Atendimento/include/cWFAtendimento.h"
#include "../../FluxoFase/include/cWFFluxoFase.h"
#include "../../FluxoFuncao/include/cWFFluxoFuncao.h"

#define STU_USUARIO_ESTRANHO_AO_GRUPO   0
#define STU_USUARIO_SUPERVISOR          1
#define STU_USUARIO_COMUM               2

#define CORE_STARTADO_PELO_INBOX_CRI    1
#define CORE_STARTADO_PELA_FILA_CRI     2

//============================================================================
// Tabela de serviços suportados pelo core workflow
struct TabServicos
{
    int idServico;
    char *nomeServico;
    char *descricao;
};

//============================================================================
// Declarações de classes e protótipos
class ObterValorTag : public TuxBasicSvc
{
    public:
        // ObterValorTag(SmallString *ss,const char *idTag)
        //                                         { _ObterValorTag(ss,idTag); }

        ObterValorTag(char *ss,const char *idTag)
                                                { _ObterValorTag(ss,idTag); }

        ObterValorTag(DOMNode *entrada,const char *dnode,const char *idTag)
                                                { _ObterValorTag(entrada,dnode,idTag); }

        ObterValorTag(DOMNode *dn,const char *idTag)
                                                { _ObterValorTag(dn,idTag); }

        ObterValorTag() { this->valor = 0; }

        ~ObterValorTag() { if ( this->valor ) delete this->valor; }

    public:
        operator SmallString*() { return (SmallString*)this->valor; }
        operator SmallString() { return (SmallString)this->valor; }
        operator char*() { return this->valor ? this->valor : ""; }
        operator char() { return this->valor ? *this->valor: '\0'; }
        operator int() { return this->valor ? atoi(this->valor) : 0; }
        operator long() { return this->valor ? atol(this->valor) : 0; }

    private:
        TuxHelper tx;
        char *gerarIDDom() { static int idDOM=0;static char stID[32]; sprintf(stID,"ID_%d",++idDOM); return stID; }
        
        //char *_ObterValorTag(SmallString *ss,const char *idTag);
        char *_ObterValorTag(DOMNode *entrada,const char *dnode,const char *idTag);
        char *_ObterValorTag(DOMNode *dn,const char *idTag);
        char *_ObterValorTag(char *ss,const char *idTag);

    private:
        char *valor;
} ;

class cWFCoreWorkflow : public TuxBasicSvc
{
    static int tuxSeqRemoteCall;

    public:
        cWFCoreWorkflow();

        cWFCoreWorkflow(DOMNode* entrada,XMLGen* saida);
        ~cWFCoreWorkflow() { delete pParser; }

    public:
        void Executar();
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }
        inline char *obterIdUsuarioStr() { sprintf(idUsuarioStr,"%d",idUsuario); return idUsuarioStr; }

        inline char *ObterMsgErro() { return (char*)msgErro; }
        inline int ObterTamMsgErro() { return msgErro.Size(); }
        inline char *ObterCodErro() { return (char*)codErro; }
        inline int ObterTamCodErro() { return codErro.Size(); }


    public:
        void SetarErro(SmallString *ce,SmallString *me) { if (ce) codErro=*ce;msgErro = *me; }
        void SetarErro(char *ce,char *me) { if (ce) codErro=ce;msgErro = me; }

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        bool grupoEncaminhamentoFornecido;
        bool grupoEncaminhamentoIsCRI;
        bool isAnalistaFO;
        char idUsuarioStr[32];

        DetalheAtendimento detalheAtendimento;

        st_CoreWorkflow   m_stDados;
        st_vlCoreWorkflow m_vlDados;

        st_FluxoFase dadosFluxoFase;
        st_vlFluxoFase statusFluxoFase;

        st_FluxoFuncao dadosFluxoFuncao;
        st_vlFluxoFuncao statusFluxoFuncao;
        XercesDOMParser *pParser;

        bool inAcaoPortout;
        int idUsuario;
        int idAtividade;
        int idAtividadeFl;
        int idFaseAtividade;
        long idAtendimento;
        int idAgEstTpPFtAt;
        int idAgEstTpPFt;
        int idRtScript;
        int idRtScTra;
        int aTratar;
        int inCRI;
        int inRC;
        int idUsuarioCri;
        int idPessoaUsuarioDestino;
        int idGrupoEncaminhamento;
        int idGrupoAtual;
        int idPessoaUsuarioWeb;
        int idMotivo;
        char idAtendimentoProtocolo[32];
        char dsTipoAtividade[256];

        SmallString txtOperNaoPermitida;
        SmallString txtFalhaPermissao;
        SmallString txtUsuarioNaoFazParteGrupo;
        SmallString txtUsuarioInativo;
        SmallString txtSemGrupoFase;
        SmallString txtGrupoNaoTratamento;
        SmallString txtGrupoAtualComErro;
        SmallString txtGrupoNaoRetorno;
        SmallString txtGrupoNaoTratRetorno;
        SmallString txtSemRegistro;
        SmallString txtResultado;
        SmallString txtFluxoFuncao;
        SmallString txtAcaoInibidaCRI;
        SmallString txtAcaoIndisponivel;
        SmallString txtSemSkillParaEncaminhar;
        SmallString txtUsuarioCorrenteNaoInformado;
        SmallString txtTagAtendimentoErr;
        SmallString txtUsuarioDestinoInativo;
        SmallString txtHoraInvalida;
        SmallString txtUsuarioWebInativo;
        SmallString txtUsuarioGrupoInvalido;
        SmallString txtUsuarioNaoAdministrador;

        SmallString strRecorFluxo;
        SmallString strDomScr;
        SmallString strRecorScTr;
        SmallString strRecorScr;
        SmallString xmlObtemScr;

        SmallString codErro;
        SmallString msgErro;

    private:
        char *gerarIDDom() { static int idDOM=0;static char stID[32]; sprintf(stID,"ID_%d",++idDOM); return stID; }
        //bool Call(XMLGen *xmlGen,char *labelServico,const char *nomeDoServico);
        bool AcaoTrafegaGrupo();
        void Call(char *labelServico,const char *nomeDoServico);
        void ProcessarDomAtdWFVO();
        //bool ProcessarFluxoAtd(SmallString *xmlDetalheAtd);
        void ProcessarFluxoAtd();
        void SubProcessarFluxoAtd();
        void ExecutarRouterScript();
        bool GrupoEmFaseTratamentoSimNao(int idGrupoEncaminhamento,int idContato);
        bool GrupoEmFaseRetornoSimNao(int idGrupoEncaminhamento,int idContato);
        bool GrupoEmFaseTratRetornoSimNao(int idGrupoEncaminhamento,int idContato);
        bool GrupoCRISimNao(int idGrupoEncaminhamento);
        bool UsuarioAtivoSimNao(int idPessoaUsuario);
        bool AtividadeDisponivelSimNao();
        bool horaIsValida(char *hora);
        bool ObterNrProtocolo(long idAtendimento);
	    //bool usuarioIsSupervisor(long idPessoaUsuario);

	    int ObterIdUsuarioCriProcesso();
        int VerificarTratarScript();
        int ObterGrupoAtual(long idAtendimento);
        int VerificarSituacaoUsuario(int idPessoaUsuario);

        void VerificarMensagemFalha(SmallString &mensagem);
        void carregaDados();
} ;


class CoreWorkflowException
{
    public:
        CoreWorkflowException(char *codigo,char *mensagem)
        {
            this->codigo = codigo?codigo:"00E9999";
            this->mensagem = mensagem?mensagem:"Mensagem de erro não informada";
        }

        const char *getCode() { return codigo.c_str(); }
        const char *getMessage() { return mensagem.c_str(); }

    private:
        SmallString codigo;
        SmallString mensagem;

};
