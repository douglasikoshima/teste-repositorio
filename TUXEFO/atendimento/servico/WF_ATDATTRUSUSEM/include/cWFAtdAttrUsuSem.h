/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.5 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:18 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "../../AtendimentoGerarXMLDPR/include/cWFAtendimentoGerarXMLDPR.h"
#include "../../../../atendimento/servico/Atendimento/include/cWFAtendimento.h"
#include "stWFAtdAttrUsuSem.h"

class cWFAtdAttrUsuSem : public TuxBasicSvc
{
    public:
        cWFAtdAttrUsuSem() {}
        cWFAtdAttrUsuSem(DOMNode* entrada,XMLGen* saida);
        cWFAtdAttrUsuSem(DOMNode* entrada,XMLGen* saida,int idUsuario);
        ~cWFAtdAttrUsuSem() {}

    public:
        bool Executar();
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline void setarIdUsuario() { char *user=getUser(); idUsuario = user?atoi(user):0; }
        inline int obterIdUsuario() { return idUsuario; }
        void SetarErro(SmallString *ce,SmallString *me) { if (ce) codErro=*ce;msgErro = *me; }
        void SetarErro(char *ce,char *me) { if (ce) codErro=ce;msgErro = me; }

        inline char *ObterMsgErro() { return (char*)msgErro; }
        inline int ObterTamMsgErro() { return msgErro.Size(); }
        inline char *ObterCodErro() { return (char*)codErro; }
        inline int ObterTamCodErro() { return codErro.Size(); }

    public:
        st_AtdAttrUsuSem   m_stDados;
        st_vlAtdAttrUsuSem m_vlDados;

        DOMNode* entrada;
        XMLGen * saida;
        XMLDPR xmlDpr;
        TuxHelper tx;

    private:
        int idUsuario;
        SmallString codErro;
        SmallString msgErro;

        SmallString retornoXml;
        DetalheAtendimento detalheAtendimento;

    private:

        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        void carregaDados();
        void IncluirAtendimentoObservacao( long idAndamento );
        void IncluirAdandamentoMotivo( long idAndamento,int idMotivo,int idFase);
        void IncluirAndamentoTransferencia( long idAndamento,int idMotivo);

        //bool IncluirAtdUsuarioAtual(long idAtendimento);
        bool ObterProximoAgrupamento(int *idProxAgrupEstadoTpProc);
		bool IncluirAndamento(int idProxAgrupEstadoTpProc,long *idAndamentoIns,int inRC);
        // bool AtualizarAndamentoAtual(int idAndamentoIns);
        //bool ObterDetalheAtd();
        bool ObterGrupoCri();
        bool InserirAtendimentoCri(int idPessoaUsuario);
        bool ObterMotivoEncAutomatico(int &idMotivo);
        void registrarAcaoDPR(int idUsuarioWeb,int /*idContato*/,int idAgrupamentoEstadoTpProc);
};
