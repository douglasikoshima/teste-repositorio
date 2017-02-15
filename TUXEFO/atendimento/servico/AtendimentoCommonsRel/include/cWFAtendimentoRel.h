/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:30 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <iostream>
#include <string>
#include <vector>
#include <map>
using namespace std;

#include <tuxfw.h>
#include "stWFAtendimentoRel.h"

class cWFAtdRel : public TuxBasicSvc
{
    public:
        st_AtendimentoRel   m_stDados;
        st_vlAtendimentoRel m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    public:
        cWFAtdRel(DOMNode* entrada,XMLGen* saida);
        bool Executar();

    private:
        void carregaDados();
} ;

