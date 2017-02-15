/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:11 $
 **/


#include "../include/cEncContato.h"

DECLARE_TUXEDO_SERVICE(ENCCONTATO);

void implENCCONTATO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
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
}

