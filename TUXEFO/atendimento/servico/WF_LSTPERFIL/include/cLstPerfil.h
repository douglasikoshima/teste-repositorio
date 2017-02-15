
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>
#include <vector>
#include "stLstPerfil.h"

using namespace std;

//typedef vector<struct st_LstPerfil> VEC_LSTPERFIL;

class cLstPerfil
{
    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;
//    VEC_LSTPERFIL   pPerfil;

    public:
        st_LstPerfil   m_stDados;
        st_vlLstPerfil m_vlDados;

        cLstPerfil( DOMNode * dnode, XMLGen * xml_g );
        void Perfil( void );
        void ListaTodoPerfil( XMLGen * xml_g  );
        void ListaNomePerfil( XMLGen * xml_g  );

    private:
        char sNomePerfil[256];
        void carregaDados( void );

};
