/**
 * 
 * @modulo  AdmSistemas
 * @usecase listas de fornecedores de atendentes terceirizados (OS MeuCliente)
 * @author  Cassio Garcia
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:41:29 $
 **/

#ifndef CFornecedorConsultorAtdH
#define CFornecedorConsultorAtdH

#include <tuxfw.h>
#include "../include/CFornecedorConsultorAtdItr.h"

class CFornecedorConsultorAtd : public CFornecedorConsultorAtdItr
{
    private:
        int InserirRaiz();
        int InserirNode();

    public:
        CFornecedorConsultorAtd();
        ~CFornecedorConsultorAtd();

        //Monta o xml padrao desta tabela
        void GetXml( char* cNomeTag, XMLGen*xml );
        void GetXmlFrm( char* cNomeTag, XMLGen*xml );
        void GetXmlPadrao( char* cNomeTag, XMLGen*xml );

        //Recupera um registro
        int ListId( const char* cidFornecedorConsultorAtd );

        //Recupera todos os registros
        int ListAll( void );
};

#endif
