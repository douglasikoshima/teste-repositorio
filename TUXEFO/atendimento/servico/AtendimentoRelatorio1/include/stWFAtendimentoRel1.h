/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:31 $
 **/

#ifndef STWFATENDIMENTOREL7
    #define STWFATENDIMENTOREL7

#define DESC_TOTAL "Total"

#define SIM "1"
#define NAO "0"
#define AND "AND"
#define MAXCOLUNAS 32

struct st_AtendimentoRel1
{
    char idUF;
    char idGrupo;
    char idCarteira;
    char idSegmentacao;
    char idRepresentante;
    char prazo;
} ;

struct st_vlAtendimentoRel1
{
    short idUF;
    short idGrupo;
    short idCarteira;
    short idSegmentacao;
    short idRepresentante;
    short prazo;
} ;

#endif

