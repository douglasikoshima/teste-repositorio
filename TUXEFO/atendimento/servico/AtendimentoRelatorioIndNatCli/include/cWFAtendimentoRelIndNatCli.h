/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:42 $
 **/

#include <tuxfw.h>
#include "stWFAtendimentoRelIndNatCli.h"

class cWFAtdRelINC : public TuxBasicSvc
{
    public:
        st_AtendimentoRel   m_stDados;
        st_vlAtendimentoRel m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    public:
        cWFAtdRelINC(DOMNode* entrada,XMLGen* saida);
        bool Executar();

        // Usuario do servi�o
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    private:
        int idUsuario;

        void carregaDados();
} ;
