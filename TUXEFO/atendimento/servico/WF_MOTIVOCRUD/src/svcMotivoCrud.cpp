

#include "../include/cMotivoCrud.h"

DECLARE_TUXEDO_SERVICE(MOTIVOSCRUD);

void implMOTIVOSCRUD::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implMOTIVOSCRUD::Execute()");
    
    int retorno;

    try
    {
        cMotivoCrud ob( dnode,xml_g );
        retorno = ob.Processa();
    }
    catch(...)
    {
        throw;
    }

    if ( retorno == 0 )
    {
        setStatusCode("09I0000","Operação concluída");
    }
    else if ( retorno == -1 )
    {
        setStatusCode("09W0001","Nome de motivo duplicado");
    }
    else if ( retorno == -2 )
    {
        setStatusCode("09W0002","Motivo nao existe na base de dados");
    }
    else if ( retorno == -3 )
    {
        setStatusCode("09W0003","Operação inválida");
    }
    else
    {
        setStatusCode("09W9999","Erro desconhecido");
    }
    
    ULOG_END("implMOTIVOSCRUD::Execute()");
}

