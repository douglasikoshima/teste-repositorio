/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:36 $
 **/

#include<tuxfw.h>
#include "stWFAtdEncaminhadoIncorreto.h"

#ifndef CWFANDAMENTOFODPR
    #define CWFANDAMENTOFODPR

class cWFAtdEncaminhadoIncorreto
{
    public:
        cWFAtdEncaminhadoIncorreto() { entrada = 0; saida = 0; }
        ~cWFAtdEncaminhadoIncorreto() {}
        void incluir(st_AtdEncaminhadoIncorreto* dados, st_vlAtdEncaminhadoIncorreto* status);

    private:

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;
};

#endif
