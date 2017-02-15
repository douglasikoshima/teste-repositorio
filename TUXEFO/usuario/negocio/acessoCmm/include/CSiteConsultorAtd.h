/**
 * 
 * @modulo  AdmSistemas
 * @usecase listas de sites de atendentes terceirizados (OS MeuCliente)
 * @author  Cassio Garcia
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:41:29 $
 **/

#ifndef CSiteConsultorAtdH
#define CSiteConsultorAtdH

#include <tuxfw.h>
#include "../include/CSiteConsultorAtdItr.h"

class CSiteConsultorAtd : public CSiteConsultorAtdItr
{
    private:
        int InserirRaiz();
        int InserirNode();

    public:
        CSiteConsultorAtd();
        ~CSiteConsultorAtd();

        //Monta o xml padrao desta tabela
        void GetXml( char* cNomeTag, XMLGen*xml );
        void GetXmlFrm( char* cNomeTag, XMLGen*xml );
        void GetXmlPadrao( char* cNomeTag, XMLGen*xml );

        //Recupera um registro
        int ListId( const char* cidSiteConsultorAtd );

        //Recupera todos os registros
        int ListAll( void );
};

#endif
