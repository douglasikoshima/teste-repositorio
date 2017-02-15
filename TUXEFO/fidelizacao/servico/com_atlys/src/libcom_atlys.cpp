//
// $Id: libcom_atlys.cpp,v 1.1.118.1 2013/03/08 18:42:13 a5114878 Exp $
// COMATLYS


#include "../../negocio/fidutil/include/retencao.hpp"

// Prototipos

// SOMENTE PARA CONSULTA ATLYS 
extern int com_Atlys( long idPessoa, int idUser, XMLGen * xml_g );

// Somente para uso no simulador
//extern int com_Atlys( char * cpf, int idPessoa, int idUser, XMLGen * xml_g );

DECLARE_TUXEDO_SERVICE(COMATLYS);

void implCOMATLYS::Execute( DOMNode * dnode, XMLGen * xml_g )
{
    int idUsr = -1;
    long idPessoa = -1;
    char parm[50];
    
    // USAR COMENTE PARA SIMULADOR
//    char cpf[50];

    
    ULOG_START( "COMATLYS" );
    
    idUsr = get_idUsuario( getUser() );
    
    get_tag(parm, dnode, "idClienteLegado", 0, 0);
    idPessoa = atol(parm);
    ULOG ( "XML:idPessoa [%ld]",idPessoa );

    //  Somente para USO NO SIMULADOR
    /*
    get_tag(parm, dnode, "documento", 0, -1);
    strcpy( cpf,parm );
    ULOG ( "XML:CPF [%s]",cpf );
    */
    
    
    // SOMENTE PARA CHAMADA ATLYS
    com_Atlys( idPessoa, idUsr, xml_g );

    // SOMENTE PARA SIMULADOR
    //com_Atlys( cpf, idPessoa, idUsr, xml_g );
      
    setStatusCode( OKFID,OKMSG );

    ULOG_END( "COMATLYS" );

}

