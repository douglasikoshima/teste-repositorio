/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:31 $
 **/


#include "../include/cFchImediatoContato.h"

DECLARE_TUXEDO_SERVICE(FCHIMECONTATO);

void implFCHIMECONTATO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implFCHIMECONTATO::Execute()");
    
    /* 

    CÓDIGO PARA USO FUTURO EM CASO DE IMPLEMENTAÇÃO DO SERVIÇO

	char operacao;
    char *p;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        operacao = *p;
        XMLString::release(&p);
    }

    */

    setStatusCode("04I0000","Processo concluído.");
    
    ULOG_END("implFCHIMECONTATO::Execute()");
    
}
