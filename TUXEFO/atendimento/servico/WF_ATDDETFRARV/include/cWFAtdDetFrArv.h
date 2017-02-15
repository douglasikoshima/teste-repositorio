/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:23 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdDetFrArv.h"

class cWFAtdDetFrArv : public TuxBasicSvc
{
    public:
        cWFAtdDetFrArv() {}
        cWFAtdDetFrArv(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdDetFrArv() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdDetFrArv   m_stDados;
        st_vlAtdDetFrArv m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        void ObterAtdFrmCpVl();

        void carregaDados();
} ;
