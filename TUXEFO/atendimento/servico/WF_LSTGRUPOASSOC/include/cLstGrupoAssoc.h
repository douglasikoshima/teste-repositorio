
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>

class cLstGrupoAssoc
{
    unsigned long idContato;
    unsigned long idUser;
    int inPerfil;//ESta variavei indica que a chamada se originou da tela arvore de contato > Editar Workflow > Perfil CRI
    int inCRI;
    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;

    public:
        cLstGrupoAssoc( unsigned long idUsuario, DOMNode * dnode, XMLGen * xml_g );
        void Processa( void );

    private:
        void carregaDados( void );
        void ListaGrupoHabilitado( void );


};
