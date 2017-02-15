
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>


class cRmvPerfil
{

    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;

    public:
        cRmvPerfil( DOMNode * dnode, XMLGen * xml_g );
        short Processa( void );
        short RemovePerfil( void );

    private:
        unsigned long idPerfil;
        void carregaDados( void );

};

