/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5110702 $ - $Date: 2010/02/23 21:08:02 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtFechMasPqUs.h"

class cWFAtFechMasPqUs : public TuxBasicSvc
{
    public:
        cWFAtFechMasPqUs() {}
        cWFAtFechMasPqUs(DOMNode* entrada,XMLGen* saida);
        ~cWFAtFechMasPqUs() {}

    public:
        bool Executar();
        void setarIdUsuario(const char *idPessoaUsuario) { idUsuario = idPessoaUsuario; }
        const char * obterIdUsuario() { return idUsuario; }

        char *ObterMsgErro() { return (char*)msgErro; }
        int ObterTamMsgErro() { return msgErro.Size(); }
        char *ObterCodErro() { return (char*)codErro; }
        int ObterTamCodErro() { return codErro.Size(); }

    public:
        void SetarErro(SmallString *ce,SmallString *me) { if (ce) codErro=*ce;msgErro = *me; }
        void SetarErro(char *ce,char *me) { if (ce) codErro=ce;msgErro = me; }

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        const char *idUsuario;
        SmallString codErro;
        SmallString msgErro;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        void carregaDados() {}
        bool PesquisarUsuarioPorGrupo();
        //bool PesquisarUsuarioMC1();
        bool PesquisarUsuarioPorGrupoMC();
} ;
