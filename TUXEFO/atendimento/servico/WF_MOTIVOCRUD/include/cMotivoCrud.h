
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>


class cMotivoCrud
{

    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;

    public:
        cMotivoCrud( DOMNode * dnode, XMLGen * xml_g );
        cMotivoCrud() { entrada=0; saida=0;iOperacao=-1; }
        int Processa( );

    private:
        unsigned long idMotivo;
        char sMotivo[ 257 ];
        int iOperacao;

        int InsereMotivo( );
        int AlteraMotivo( );
        int ConsultaMotivo( );
        int RemoveMotivo( );
        void  carregaDados( );

};

