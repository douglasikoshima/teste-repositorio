/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:39 $
 **/

#ifndef STWFATDMONITPESQ
    #define STWFATDMONITPESQ

struct st_AtdMonitPesq
{
    char dtInicio[128];
    char dtFim[128];
    int idGrupo;
    int idUsuario;
};

struct st_vlAtdMonitPesq
{
    short dtInicio;
    short dtFim;
    short idGrupo;
    short idUsuario;
};

#endif
