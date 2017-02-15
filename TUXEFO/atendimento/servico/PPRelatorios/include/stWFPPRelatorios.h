/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:35 $
 **/

#ifndef STWFPPRELATORIOS
    #define STWFPPRELATORIOS

struct st_PPRelatorios
{
    long idAtendimento;
    int idPessoaUsuario;
    int idGrupoAtual;
    int qtFechados;
    int qtRetorno;
    int qtTratamento;
    int qtEncerrados;
    int cdAreaRegistro;
    int idUFOperadora;
    int idGrupoOperadora;
    int idFase;
    int idMotivo;
    char dtAbertura[32];

};

struct st_vlPPRelatorios
{
    short idAtendimento;
    short idPessoaUsuario;
    short idGrupoAtual;
    short qtFechados;
    short qtRetorno;
    short qtTratamento;
    short qtEncerrados;
    short cdAreaRegistro;
    short idUFOperadora;
    short idGrupoOperadora;
    short idFase;
    short idMotivo;
    short dtAbertura;
};

struct DadosComuns
{
    char dtAbertura[32];
    int cdAreaRegistro;
    int idGrupoOperadora;
    int idUFOperadora;
};

#endif


