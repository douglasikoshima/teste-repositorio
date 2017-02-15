/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.114.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:09:02 $
 **/

#ifndef STWFATDENCAMINHADOINCORRETO
    #define STWFATDENCAMINHADOINCORRETO
 
struct st_AtdEncaminhadoIncorreto
{
    int idCanal;
    int idContato;
    int idGrupo;
    int idGrupoAbertura;
    int idProcedencia;
    int idSegmentacao;
    int idTipoCarteira;
    int idTipoLinha;
    int idTipoPessoa;
    int idTipoRelacionamento;
    int idUsuarioAlteracao;
    int qtIncorretos;
    char *dtAbertura;
    char dtUltimaAlteracao[32];
};

struct st_vlAtdEncaminhadoIncorreto
{
    short idCanal;
    short idContato;
    short idGrupo;
    short idGrupoAbertura;
    short idProcedencia;
    short idSegmentacao;
    short idTipoCarteira;
    short idTipoLinha;
    short idTipoPessoa;
    short idTipoRelacionamento;
    short idUsuarioAlteracao;
    short qtIncorretos;
    short dtAbertura;
    short dtUltimaAlteracao;
};

#endif

