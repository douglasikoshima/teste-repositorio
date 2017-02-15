/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:30 $
 **/

#include<tuxfw.h>
#include "stWFAtendimentoGerarXMLDPR.h"

#ifndef CWFATENDIMENTOGERARXMLDPR
    #define CWFATENDIMENTOGERARXMLDPR

class cWFAtdGerarXMLDPR
{
    public:
        cWFAtdGerarXMLDPR() { entrada = 0; saida = 0; }
        ~cWFAtdGerarXMLDPR() {}

        void persistirDadosDPRContatoTecnico(XMLDPR *xml);

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;
};

#endif
