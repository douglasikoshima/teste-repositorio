#include "../../../negocio/commons/include/Commom.hpp"
#include <tuxfw.h>

extern short BuscaGrpAtd( char * dddPrm, char * nrLinhaPrm, XMLGen* xml_g );

DECLARE_TUXEDO_SERVICE( BUSCAGRPATD );

void implBUSCAGRPATD::Execute( DOMNode * dnode, XMLGen * xml_g ) 
{
	ULOG_START("implBUSCAGRPATD::Execute()");

    char* bf = walkTree(dnode, "numeroInformado", 0);
    if( !bf || !strlen(bf) ) {
        setStatusCode("24W0001","Parametros obrigatorios nao informados!");
    }
    else {
    char cDDD[3];
    char cFone[16];

    memset( cDDD , 0x0, sizeof(cDDD) );
    memset( cFone, 0x0, sizeof(cFone) );

    	sprintf( cDDD , "%.2s", bf );
		sprintf( cFone, "%s", (char*)&bf[2] );

		switch( BuscaGrpAtd( cDDD, cFone, xml_g ) ) {
		case 0:
			setStatusCode("24I0000", "SUCESSO");
			break;
		case 1:
			setStatusCode("24W0002","Nao foram encontrados registros!");
			break;
		default:
			setStatusCode("24W0999","Erro desconhecido / Erro oracle!");
		}
    }

    if (bf)
    XMLString::release(&bf);

    ULOG_END("implBUSCAGRPATD::Execute()");
}
