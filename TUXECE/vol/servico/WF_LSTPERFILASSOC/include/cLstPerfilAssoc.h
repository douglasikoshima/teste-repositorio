
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>

class cLstPerfilAssoc
{
    unsigned long idUser;
    unsigned long idContato;
    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;

    public:
        cLstPerfilAssoc( unsigned long idUsuario, DOMNode * dnode, XMLGen * xml_g );
        void Processa( void );

    private:
        void carregaDados( void );
        void ListaPerfilHabilitado( void );


};
