/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.118.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#ifndef STWFATENDIMENTOCLICKFOLHA
    #define STWFATENDIMENTOCLICKFOLHA

struct st_AtendimentoClickFolha
{
    int idContato;
    int idConta;
    int idLinha;
    int idPessoaDePara;
    long idPessoa;
    bool abertoInsistencia;
    int idPessoaUsuario;
    int idTipoLinha;
    int idUFOperadora;
    int idAgrupamentoEstadoTpProc;
    int indAbertura;
    int idFaseProcesso;
    int idGrupo;
    char nrTelefone[256];
    char inTipoPessoa[256];
    int idSegmentacao;
    int idTipoRelacionamento;
    int idTipoCarteira;
    int inPreview;
};

struct st_vlAtendimentoClickFolha
{
    short idContato;
    short idConta;
    short idLinha;
    short idPessoaDePara;
    short idPessoa;
    short abertoInsistencia;
    short idPessoaUsuario;
    short idTipoLinha;
    short idUFOperadora;
    short idAgrupamentoEstadoTpProc;
    short indAbertura;
    short idFaseProcesso;
    short nrTelefone;
    short idGrupo;
    short inTipoPessoa;
    short idSegmentacao;
    short idTipoRelacionamento;
    short idTipoCarteira;
    short inPreview;
};

#endif
