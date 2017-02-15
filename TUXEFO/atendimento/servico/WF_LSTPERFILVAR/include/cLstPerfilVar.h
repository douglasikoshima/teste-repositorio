
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>

class cLstPerfilVar
{
    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;

    public:
        cLstPerfilVar( DOMNode * dnode, XMLGen * xml_g );
        void ListaPerfil( void );
        bool ListaPerfilTipoLinha( XMLGen * xml_g );
        bool ListaPerfilSegmentacao( XMLGen * xml_g );
        bool ListaPerfilTipoCarteira( XMLGen * xml_g );
        bool ListaPerfilTipoCanal( XMLGen * xml_g );
        bool ListaPerfilProcedencia( XMLGen * xml_g );
        bool ListaPerfilNatureza( XMLGen * xml_g );
        bool ListaPerfilTipoCliente( XMLGen * xml_g );
        bool ListaPerfilRegional( XMLGen * xml_g );
        bool ListaPerfilGrupoAbertura( XMLGen * xml_g );

        bool ListaPerfilTipoLinhaAssoc( XMLGen * xml_g );
        bool ListaPerfilSegmentacaoAssoc( XMLGen * xml_g );
        bool ListaPerfilTipoCarteiraAssoc( XMLGen * xml_g );
        bool ListaPerfilTipoCanalAssoc( XMLGen * xml_g );
        bool ListaPerfilProcedenciaAssoc( XMLGen * xml_g );
        bool ListaPerfilNaturezaAssoc( XMLGen * xml_g );
        bool ListaPerfilTipoClienteAssoc( XMLGen * xml_g );
        bool ListaPerfilRegionalAssoc( XMLGen * xml_g );
        bool ListaPerfilGrupoAberturaAssoc( XMLGen * xml_g );

    private:
        unsigned long idPerfil;
        void carregaDados( void );

};
