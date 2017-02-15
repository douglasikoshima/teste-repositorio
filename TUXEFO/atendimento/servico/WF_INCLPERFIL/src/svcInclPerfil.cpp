

#include "../include/cInclPerfil.h"

DECLARE_TUXEDO_SERVICE(INCLPERFIL);

void implINCLPERFIL::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implINCLPERFIL::Execute()");
    
    short retorno;
    cInclPerfil ob( getUser(),dnode,xml_g );

    try
    {
        retorno = ob.Processa();
        if ( retorno == 0 )
        {
            setStatusCode("09I0000","Operação realizada com sucesso");
        }
        else if ( retorno == 1 )
        {
            setStatusCode("09W0001","Nome de PERFIL já existe na base de dados");
        }
        else if ( retorno == 2 )
        {
            setStatusCode("09W0002","Conjunto de variáveis, já existe na base de dados");
        }
        else if ( retorno == 3 )
        {
            setStatusCode("09W0003","Perfil relacionado, não pode ficar sem variáveis");
        }
        else if ( retorno == 4 )
        {
            setStatusCode("09W0004","Perfil sem variáveis não podem serem habilitados");
        }
    }
    catch(...)
    {
        throw;
    }
    
    ULOG_END("implINCLPERFIL::Execute()");


}

