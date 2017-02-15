/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:36 $
 **/

#include <tuxfw.h>
#include "stUsuario.h"

class cIBWFPSUsuario : public TuxBasicSvc
{
    public:
        st_VariaveisUsuario   m_stDados;
        st_VariaveisUsuario   m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

        enum {SUCESSO,ERRO};

    public:
        cIBWFPSUsuario(DOMNode* entrada,XMLGen* saida);
        int Executar();
        int ObtemUsuario( int,XMLGen * );

} ;
