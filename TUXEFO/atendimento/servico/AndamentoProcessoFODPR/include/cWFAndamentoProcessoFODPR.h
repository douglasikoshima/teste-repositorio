/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#include<tuxfw.h>
#include "stWFAndamentoProcessoFODPR.h"

#ifndef CWFANDAMENTOFODPR
    #define CWFANDAMENTOFODPR

class cWFAndamentoProcFODPR
{
    public:
        cWFAndamentoProcFODPR() { entrada = 0; saida = 0; }
        ~cWFAndamentoProcFODPR() {}
        long incluir(st_AndamentoProcFODPR* dados, st_vlAndamentoProcFODPR* status,char *idAtendimento);

    private:

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;
};

#endif
