/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:04 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdPesqGrpReg.h"

class cWFAtdPesqGrpReg : public TuxBasicSvc
{
    public:
        cWFAtdPesqGrpReg() {}
        cWFAtdPesqGrpReg(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdPesqGrpReg() {}

    public:
        bool Executar();
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

        inline void setarUUID(int valor) { UUID = valor; }
        inline void setarUUID() { char *uuid=getUUID(); UUID = uuid?atoi(uuid):0; }
        inline int obterUUID() { return UUID; }


        inline char *ObterMsgErro() { return (char*)msgErro; }
        inline int ObterTamMsgErro() { return msgErro.Size(); }
        inline char *ObterCodErro() { return (char*)codErro; }
        inline int ObterTamCodErro() { return codErro.Size(); }

        void SetarErro(SmallString *ce,SmallString *me) { if (ce) codErro=*ce;msgErro = *me; }
        void SetarErro(char *ce,char *me) { if (ce) codErro=ce;msgErro = me; }

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;
        int UUID;
	    st_AtdPesqGrpReg		m_stDados;
	    st_vlAtdPesqGrpReg		m_vlDados;

        SmallString codErro;
        SmallString msgErro;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        void carregaDados();

        bool PesquisarConsultaWFGrupos();
        bool PesquisarTabelaUFRegional();
} ;
