/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:33 $
 **/

#ifndef __STWFATENDIMENTORELPESQSATISFAGRAVA_H__
#define __STWFATENDIMENTORELPESQSATISFAGRAVA_H__

struct st_AtendimentoRelGravaPesqSatis
{
    long idAtendimento;
    long idAtendimentoPesquisaSatisfa;
    int idPergunta;
    int userID;
    char vlResposta[256];
    char dataAtual[256];
} ;

struct st_vlAtendimentoRelGravaPesqSatis
{
    short idAtendimento;
    short idAtendimentoPesquisaSatisfa;
    short idPergunta;
    short userID;
    short vlResposta;
    short dataAtual;
} ;

#endif

