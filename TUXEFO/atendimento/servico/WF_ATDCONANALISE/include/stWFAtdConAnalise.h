/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFATDCONANALISE
    #define STWFATDCONANALISE

struct st_AtdConAnalise
{
    long idAtendimento;
    char dataAtual[256];
    char dataAndamento[256];
    char dsValorParametro[256];
};

struct st_vlAtdConAnalise
{
    short idAtendimento;
    short dataAtual;
    short dataAndamento;
    short dsValorParametro;
};

struct st_AtdConAnaliseResp
{
    int nrNivel;
    int idSegmentacao;
    int idTipoCarteira;
    int idProcedencia;
    int idContato;
    int idGrupoAbertura;
    int idCanal;
    int idTipoPessoa;
    int idTipoRel;
    int idPesLinha;
    int idTipoLinha;
    long AtdUsrAt;
    long idAndamento;
    int idGrupoAtual;
    int idSequencia;
    int idGrupoFase;

    int UsuarioAtual;
    long idAtendimento;
    int idAgrEstTPrFt;
    int idAgrEstTPrAt;
    int idAtividade;
    int idFase;
    int idMotivo;
    long idAndamentoIns;

    char urlDestino[2049];
};


struct st_vlAtdConAnaliseResp
{
    short nrNivel;
    short idSegmentacao;
    short idTipoCarteira;
    short idProcedencia;
    short idContato;
    short idGrupoAbertura;
    short idCanal;
    short idTipoPessoa;
    short idTipoRel;
    short idPesLinha;
    short idTipoLinha;
    short AtdUsrAt;
    short idAndamento;
    short idGrupoAtual;
    short idSequencia;
    short idGrupoFase;
    short UsuarioAtual;
    short idAtendimento;
    short idAgrEstTPrFt;
    short idAgrEstTPrAt;
    short idAtividade;
    short idFase;
    short idMotivo;
    short idAndamentoIns;
    short urlDestino;
};


#endif
