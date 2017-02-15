/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:23 $
 **/

#ifndef STWFATENDIMENTOREL7
    #define STWFATENDIMENTOREL7

#define AND " AND "
#define MAXCOLUNAS 21

struct st_AtendimentoRel3
{
    int idUF;
    int idGrupo;
    int idCarteira;
    int idSegmentacao;
    int idRepresentante;
    char prazo;
    char dtInicio[11];
    char dtFim[11];
} ;

struct st_vlAtendimentoRel3
{
    short idUF;
    short idGrupo;
    short idCarteira;
    short idSegmentacao;
    short idRepresentante;
    short prazo;
    short dtInicio;
    short dtFim;
} ;

#endif

