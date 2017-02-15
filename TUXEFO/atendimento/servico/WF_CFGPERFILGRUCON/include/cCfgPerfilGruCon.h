
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>
#include <vector>

using namespace std;

typedef vector<unsigned long> VEC_PERFIL;
typedef vector<unsigned long> VEC_GRUPO;
typedef vector<unsigned long> VEC_GRUPOPERFIL;

class cCfgPerfilGruCon
{
    unsigned long idUser;
    unsigned long idContato;
    bool bFlagDeleta;
    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;
    VEC_PERFIL         pPerfil;
    VEC_GRUPO          pGrupo;
    VEC_GRUPOPERFIL    pGrupoPerfil;

    public:
        cCfgPerfilGruCon( unsigned long idUsuario, DOMNode * dnode, XMLGen * xml_g );
        void Processa( void );

    private:
        void carregaDados( void );
        void AtualizaGrupoPerfil( void );

};
