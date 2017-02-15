/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#ifndef STWFATDGETMOTTAB
    #define STWFATDGETMOTTAB

struct st_AtdGetMotTab
{
    int idAtividade;
    long idAtendimento;
    int inGrupo;
    int inCri;
    int inRC;
};

struct st_vlAtdGetMotTab
{
    short idAtividade;
    short idAtendimento;
    short inGrupo;
    short inCri;
    short inRC;
};


#endif
