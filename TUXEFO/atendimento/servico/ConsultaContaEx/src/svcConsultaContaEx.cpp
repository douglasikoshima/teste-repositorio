

#include "../include/CConsultaContaEx.h"

#include <string>
using namespace std;


DECLARE_TUXEDO_SERVICE( CONSULTACTAEX );

void implCONSULTACTAEX::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implCONSULTACTAEX::Execute()");
    
    string sCode = "04I0000";
    string mCode = "Operacao concluida com sucesso";

    try
    {
        CConsultaContaEx ob( dnode );
        
        switch (ob.GetOperacao())
        {
        case 0:
            ob.ListaContas( xml_g );
            break;

        case 1:
            ob.ListaLinhas( xml_g );
            break;

        case 2:
            ob.PesquisaConta( xml_g );
            break;

        case 3:
            ob.PesquisaLinhasAssoc( xml_g );
            break;

        default:
            //retorno = -3; // Operação inválida
            break;
        };

    }
    catch ( TuxBasicOraException* ex )
    {
        ULOGE("sqlca.sqlcode %d:%s",ex->eCode,ex->pMsg);

        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
        sCode = codErro;
        mCode = ex->pMsg;

        delete ex;
    }
    catch(...)
    {
        throw;
    }
    
    setStatusCode((char*)sCode.c_str(),(char*)mCode.c_str());

    ULOG_END("implCONSULTACTAEX::Execute()");
}

