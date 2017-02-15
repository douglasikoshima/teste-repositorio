/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:26 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdInboxDispo.h"

class cWFAtdInboxDispo : public TuxBasicSvc
{
    public:
        cWFAtdInboxDispo() {}
        cWFAtdInboxDispo(DOMNode* entrada,XMLGen* saida);
        cWFAtdInboxDispo(DOMNode* entrada,XMLGen* saida,char *user);
        ~cWFAtdInboxDispo() {}

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
        int idUsuario;
        st_AtdInboxDispo   m_stDados;
        st_vlAtdInboxDispo m_vlDados;

        SmallString codErro;
        SmallString msgErro;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        //bool AlterarStatusUsuario(int idPessoaUsuario,int idStatusUsuario);
        bool AlterarInDisponivelWF(int idPessoaUsuario,bool inDisponivelWF);
        void carregaDados() {}
} ;
