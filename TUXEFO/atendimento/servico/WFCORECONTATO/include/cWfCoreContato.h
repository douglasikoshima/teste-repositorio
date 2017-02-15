/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:34 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

#include "cWfCoreContatoPC.h"

class cWfCoreContato : public TuxBasicSvc
{
    public:
        DOMNode *entrada;
        XMLGen *saida;
        XMLGen xmlGen;
        cWfCoreContatoPC cwfcorecontatopc;

        TuxRemoteService rc;
        TuxMessage  tmIn;
        TuxMessage *ptmOut;

        st_DadosEntrada dados;
        st_StatusEntrada status;

    public:

        cWfCoreContato(DOMNode *dnode,XMLGen *xml_g) {saida = xml_g;entrada = dnode;}
        ~cWfCoreContato() {}

    public:
        void Executar(string &idAtendimentoProtocolo);

    private:
        void CoreWorkflow();
        void RegContato(string &idAtendimentoProtocolo);
        void SyncRemoteCall(char *nomeServico);
        void ConsistirData(const char *ddmmaaaahhmiss);
        void VerificarPrazosJanelaPortout();
        void CarregarDados();
};
