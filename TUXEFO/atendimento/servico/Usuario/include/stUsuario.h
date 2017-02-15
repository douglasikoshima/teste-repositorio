/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STVARIAVEISUSUARIO
    #define STVARIAVEISUSUARIO

struct st_VariaveisUsuario
{
    long idAtendimento;
    int idFase;
    int idTipoCarteira;
    int idSegmentacao;
    int idPessoaUsuario;
    int idProcedencia;
    int idContato;
    int idGrupoAbertura;
    int idTipoPessoa;
    int idTipoLinha;
    int idTipoRelacionamento;
    int idCanal;
    int idUFOperadora;
    int nrNivel;
} ;

struct st_vlVariaveisUsuario
{
    short idAtendimento;
    short idFase;
    short idTipoCarteira;
    short idSegmentacao;
    short idPessoaUsuario;
    short idProcedencia;
    short idContato;
    short idGrupoAbertura;
    short idTipoPessoa;
    short idTipoLinha;
    short idTipoRelacionamento;
    short idCanal;
    short idUFOperadora;
    short nrNivel;
} ;

struct st_Result
{
    char idPessoaUsuario[256];
    char nmNome[256];
    char nmLoginUsuario[256];
    char idStatusUsuario[256];
    char inDisponivelWF[256];
} ;

struct DadosStatusUsuario
{
    int idStatusUsuario;
    char sgStatusUsuario[256];
    char dsStatusUsuario[256];
};

#endif
