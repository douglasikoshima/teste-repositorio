/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:08 $
 **/

#include <tuxfw.h>
#include "stWFAtendimentoRelIndSegCart.h"

class cWFAtdRelSGC : public TuxBasicSvc
{
    public:
        st_AtendimentoRel   m_stDados;
        st_vlAtendimentoRel m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    public:
        cWFAtdRelSGC(DOMNode* entrada,XMLGen* saida);
        bool Executar();

        // Usuario do serviço
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    private:
        int idUsuario;

        void carregaDados();
} ;

