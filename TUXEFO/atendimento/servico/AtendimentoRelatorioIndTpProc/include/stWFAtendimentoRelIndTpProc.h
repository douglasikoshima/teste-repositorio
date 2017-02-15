/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/

#ifndef STWFATENDIMENTOREL
    #define STWFATENDIMENTOREL

struct st_AtendimentoRel
{
    int idUF;
    int idGrupo;
    int idCarteira;
    int idSegmentacao;
    int idRepresentante;
    long idAtendimento;
    int idRegional;
    int idPessoaUsuario;
    char prazo;
    char dtInicio[11];
    char dtFim[11];
    char mediaPorcentagem[32];
} ;

struct st_vlAtendimentoRel
{
    short idUF;
    short idGrupo;
    short idCarteira;
    short idSegmentacao;
    short idRepresentante;
    short idAtendimento;
    short idRegional;
    short idPessoaUsuario;
    short prazo;
    short dtInicio;
    short dtFim;
    short mediaPorcentagem;
} ;

#endif

