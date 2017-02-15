/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFATDGETMOTCAN
    #define STWFATDGETMOTCAN

struct st_AtdGetMotCan
{
    int idAtividade;
    long idAtendimento;
    int idFaseMax;
    int inFase;
    int idAgrEstadoTProc;

};

struct st_vlAtdGetMotCan
{
    short idAtividade;
    short idAtendimento;
    short idFaseMax;
    short inFase;
    short idAgrEstadoTProc;
};


#endif
