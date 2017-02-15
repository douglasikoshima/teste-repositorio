#ifndef CPALITAGEM
    #define CPALITAGEM

#include <tuxfw.h>
#include "stPalitagem.h"

class cPalitagem
{
    public:
        TDadosQuery stDadosQuery;

    public:
        cPalitagem();
        ~cPalitagem() {}

        void CarregaDados(DOMNode*dnode);
        void ProcessaPalitagem();
};

#endif
