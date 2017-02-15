
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>


class cEncaminHist
{

    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;

    public:
        cEncaminHist( DOMNode * dnode, XMLGen * xml_g );
        cEncaminHist() { entrada=0; saida=0; }
        int Processa( void );

    private:
        unsigned long idGrupo;
        char          sUsuario[80];

        int   AtualizaHistorico( void );
        void  carregaDados( void );

};

