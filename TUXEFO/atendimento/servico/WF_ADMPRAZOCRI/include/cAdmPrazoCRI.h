
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>


class cAdmPrazoCRI
{
    unsigned long idUser;
    unsigned long idContato;
    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;

    public:
        cAdmPrazoCRI( unsigned long idUsuario, DOMNode * dnode, XMLGen * xml_g );
        void GetValor( void );
        void Processa( void );
        void PutValor( void );

    private:
        int operacao;
        unsigned long valor;
        void carregaDados( void );

};
