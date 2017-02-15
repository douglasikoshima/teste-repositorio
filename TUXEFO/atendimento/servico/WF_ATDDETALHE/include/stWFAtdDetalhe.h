/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFATDDETALHE
    #define STWFATDDETALHE

struct st_AtdDetalhe
{
    long idAtendimento;
    char idProcesso[21];
	int idEstado;
    int idSubEstado;
};

struct st_vlAtdDetalhe
{
    short idAtendimento;
	short idProcesso;
	short idEstado;
    short idSubEstado;
};

struct st_AtdBaixa
{
    char nmContato[1049];
};

struct st_vlAtdBaixa
{
    short nmContato;
};


struct st_AtdDetalhePesq
{
    int idContato;
    int idAgrEstadoTProc;
    int idFase;
    int idSequencia;
    long idAtendimento;
    int nrNivel;
    //int idDiscador;
    int qtInsistencia;
    int idGrupoAbertura;
    int idCanal;
    int idProcedencia;
    int idTipoCarteira;
    int idSegmentacao;
    char nmGrupoAbertura[256];
    char nmCanal[256];
    char dtAbertura[24];
    char dtParaFechamento[24];
    char dsProcedencia[256];
    char dsTipoCarteira[256];
    char dsEstado[256];
    char dsSubEstado[256];
    char dsSegmentacao[256];
};

struct st_vlAtdDetalhePesq
{
    short idContato;
    short idAgrEstadoTProc;
    short idFase;
    short idSequencia;
    short idAtendimento;
    short nrNivel;
    //short idDiscador;
    short qtInsistencia;
    short idGrupoAbertura;
    short idCanal;
    short idProcedencia;
    short idTipoCarteira;
    short idSegmentacao;
    short nmGrupoAbertura;
    short nmCanal;
    short dtAbertura;
    short dtParaFechamento;
    short dsProcedencia;
    short dsTipoCarteira;
    short dsEstado;
    short dsSubEstado;
    short dsSegmentacao;
};


struct st_GrupoAtendimento
{
    int idGrupo;
    char nmGrupo[256];
};

struct st_vlGrupoAtendimento
{
    short idGrupo;
    short nmGrupo;
};

#endif
