
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>
#include <vector>

using namespace std;

typedef vector<unsigned long> VEC_ATIVIDADE;



class cMotivosAssoc
{

    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;

    public:
        cMotivosAssoc( DOMNode * dnode, XMLGen * xml_g );
        short Processa( void );

    private:
        VEC_ATIVIDADE    pAtividade;
        unsigned long    idMotivo;
        int              iOperacao;

        short  AssociaMotivos( void );
        short ListaNaoAssociados( void );
        short ListaAssociados( void );
        void  carregaDados( void );

};

