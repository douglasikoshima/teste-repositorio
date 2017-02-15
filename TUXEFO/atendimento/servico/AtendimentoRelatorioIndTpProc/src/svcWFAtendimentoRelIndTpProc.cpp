/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:33 $
 **/

#include "../include/cWFAtendimentoRelIndTpProc.h"
#include "../../../commons/msgPadrao.h"


DECLARE_TUXEDO_SERVICE(WFATDRELINDTPR);

void implWFATDRELINDTPR::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    char *usuario;

    cWFAtdRelITP ob(dnode,xml_g);

    if (usuario=getUser(),usuario) 
    {
        ob.setarIdUsuario(atoi(usuario));

        ULOG( "idUsuario=%d",ob.obterIdUsuario());

        if ( ob.Executar() )
        {
            setStatusCode("00I0000","Operação concluida");
        }
        else
        {
            setStatusCode("00E0000","Falha na execução");
        }
    }
    else
    {
        char *p = "Valor de 'user' é obrigatório.";
        ULOGE( p );
        setStatusCode("00E0000",p);
    }
}
