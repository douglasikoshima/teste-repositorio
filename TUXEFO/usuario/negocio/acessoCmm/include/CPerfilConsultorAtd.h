/**
 * 
 * @modulo  AdmSistemas
 * @usecase listas de perfis de atendentes (OS MeuCliente)
 * @author  Cassio Garcia
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:41:29 $
 **/

#ifndef CPerfilConsultorAtdH
#define CPerfilConsultorAtdH

#include <tuxfw.h>
#include "../include/CPerfilConsultorAtdItr.h"

class CPerfilConsultorAtd : public CPerfilConsultorAtdItr
{
    private:
        int InserirRaiz();
        int InserirNode();

    public:
        CPerfilConsultorAtd();
        ~CPerfilConsultorAtd();

        //Monta o xml padrao desta tabela
        void GetXml( char* cNomeTag, XMLGen*xml );
        void GetXmlFrm( char* cNomeTag, XMLGen*xml );
        void GetXmlPadrao( char* cNomeTag, XMLGen*xml );

        //Recupera um registro
        int ListId( const char* cidPerfilConsultorAtd );

        //Recupera todos os registros
        int ListAll( void );
};

#endif
