/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#ifndef STWFATENDIMENTOOBSERVACAO
    #define STWFATENDIMENTOOBSERVACAO

struct st_AtendimentoObservacao
{
    long idAtendimento;
    int idUsuarioAlteracao;
    char dsObservacao[1001];
    char *pdsObservacao;
    char dtUltimaAlteracao[64];
};

struct st_vlAtendimentoObservacao
{
    short idAtendimento;
    short idUsuarioAlteracao;
    short dsObservacao;
    short dtUltimaAlteracao;
};
#endif

