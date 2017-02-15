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

#ifndef NUM_LINHAS
#define NUM_LINHAS 100
#endif

#ifndef NUM_MAX_LINHAS
#define NUM_MAX_LINHAS 14
#endif

// Quantidade maxima de linhas por bloco

struct st_AtendimentoRel
{
    int idOperadora;
    int idUF;
    int idGrupo;
    int idCarteira;
    int idSegmentacao;
    int idRepresentante;
    long idAtendimento;
    int idRegional;
    int idContato;
    int idCargo;
    int idStatusUsuario;
    int bloco;
    int qtdLinhasBloco;
    char prazo[2];
    char dtInicio[11];
    char dtFim[11];
    bool relRepresentanteBKO;
    char responsavel[513];
    char nmLoginUsuarioAtual[256];
    char nmGrupo[256];
    char nmUsuarioAtual[513];
} ;

struct st_vlAtendimentoRel
{
    short idOperadora;
    short idUF;
    short idGrupo;
    short idCarteira;
    short idSegmentacao;
    short idRepresentante;
    short idAtendimento;
    short idRegional;
    short idContato;
    short idCargo;
    short idStatusUsuario;
    short bloco;
    short qtdLinhasBloco;
    short prazo;
    short dtInicio;
    short dtFim;
    short relRepresentanteBKO;
    short responsavel;
    short nmLoginUsuarioAtual;
    short nmGrupo;
    short nmUsuarioAtual;
} ;

#endif

