/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:25 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <tuxfw.h>
#include "../../AtendimentoCommonsRel/include/stWFAtendimentoRel.h"

class cWFAtdRel6DDet : public TuxBasicSvc
{
    public:
        st_AtendimentoRel   m_stDados;
        st_vlAtendimentoRel m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    public:
        cWFAtdRel6DDet(DOMNode* entrada,XMLGen* saida);
        bool Executar();

    private:
        void carregaDados();
} ;

