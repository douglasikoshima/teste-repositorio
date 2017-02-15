/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/

#ifndef STWFATENDIMENTOPSQ
    #define STWFATENDIMENTOPSQ

struct st_AtdPesquisa
{
    long idAtendimento;
    int idContato;
    int idTipoPessoa;
    int idUfOperadora;
} ;

struct st_vlAtdPesquisa
{
    short idAtendimento;
    short idContato;
    short idTipoPessoa;
    short idUfOperadora;
} ;

#endif

