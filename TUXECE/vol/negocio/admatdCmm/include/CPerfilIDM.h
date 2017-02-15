#ifndef CPerfilIDMH
#define CPerfilIDMH

#include <tuxfw.h>
#include "CSafePointer.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

#define   MAX_REGS   500

class CPerfilIDM
{
    public:
        CSafePointer   oSafePointer;
        CPerfilIDM();
        ~CPerfilIDM();
        
        void ListaPerfil( XMLGen * xml_g );
        void GrupoPerfil( char * idPerfil, XMLGen * xml_g );
        void OperadoraPerfil( char * idPerfil, XMLGen * xml_g );
        void ItemMenuPerfil( char * idPerfil, XMLGen * xml_g );
        void PerfilFO( char * idPerfil, XMLGen * xml_g );
        void InserePerfilIDM( char * idUsuarioPrm, char * idPerfilIDM, char * nmPerfilIDM,  int * ct  );
        void InsereOperadoraPerfil( char * idUsuarioPrm, char * idUsuarioNovoPrm, DOMNode * dnode, XMLGen * xml_g );
        void InsereItemMenuPerfil( char * idUsuarioPrm, char * idUsuarioNovoPrm, DOMNode * dnode, XMLGen * xml_g );
        void RelacionaPerfil( char * idUsuarioPrm, char * idUsuarioNovoPrm, DOMNode * dnode, XMLGen * xml_g );
        void InsereGrupoPerfil( char * idUsuarioPrm, char * idUsuarioNovoPrm, DOMNode * dnode, XMLGen * xml_g );
        void RelacionaPerfilIDM( char * cidUserPrm,char * idUsuarioNovoPrm,char * idPerfilIDMPrm );
};

#endif

