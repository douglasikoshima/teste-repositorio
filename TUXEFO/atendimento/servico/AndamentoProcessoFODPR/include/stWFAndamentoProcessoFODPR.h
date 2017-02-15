/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.118.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:06:38 $
 **/

#ifndef STWFANDAMENTOPROCFODPR
    #define STWFANDAMENTOPROCFODPR
 
struct st_AndamentoProcFODPR
{
    const char *xml;
    int idUsuarioAlteracao;
    char dtUltimaAlteracao[64];
};

struct st_vlAndamentoProcFODPR
{
    short xml;
    short idUsuarioAlteracao;
    short dtUltimaAlteracao;
};

#endif

