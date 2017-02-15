/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <iostream>
#include <string>

using namespace std;

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdAssocDocTe.h"
#include "../../AtendimentoDocumento/include/cWFAtendimentoDocumento.h"
#include "../../AtendimentoFechamento/include/cWFAtendimentoFechamento.h"
#include"../../Atendimento/include/cWFAtendimento.h"
#include"../../Andamento/include/cWFAndamento.h"
#include"../../AndamentoObservacao/include/cWFAndamentoObservacao.h"
#include "../../AtendimentoGerarXMLDPR/include/cWFAtendimentoGerarXMLDPR.h"

class cWFAtdAssocDocTe : public TuxBasicSvc
{
    public:
        cWFAtdAssocDocTe() {}
        cWFAtdAssocDocTe(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdAssocDocTe() {}

    public:
        bool Executar();
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

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
        st_AtdAssocDocTe mAtdAssocDocTe;

        int idUsuario;
        SmallString codErro;
        SmallString msgErro;
        XMLDPR xmlDpr;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        void carregaDados() {}
        bool IncluirAtdDocTecnico(st_AtendimentoDocumento *dados,st_vlAtendimentoDocumento *status,DetalheAtendimento *detalheAtendimento);
        void IncluirAndamento(st_AtendimentoDocumento *dados,st_vlAtendimentoDocumento *status,DetalheAtendimento *detalheAtendimento);
        void IncluirAndamentoObservacao(st_AtendimentoDocumento *dados,long idAndamento);
        void registrarAcaoDPR(int idUsuarioWeb,long idAtendimento,int idAgrupamentoEstadoTpProc);
        bool IncluirAtendimentosDom();
        bool VerificarAtendimentosDom();
        bool VerificarAtendimentoFechamento(long idAtendimento);

} ;
