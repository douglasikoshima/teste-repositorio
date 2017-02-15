/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.4.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#ifndef STWFATDATTRUSUSEM
    #define STWFATDATTRUSUSEM

struct st_AtdAttrUsuSem
{
    long idAtendimento;
    int idGrupoAtual;
    long idPessoaLinhaHistorico;
    int inCri;
    int discador;
    char dataAtual[256];
    char dataAndamento[256];
    char dsValorParametro[256];
    char dsObservacao[256];
    int inRC;
    long idAndamento;
	long idPessoaUsuarioAtual ;

};

struct st_vlAtdAttrUsuSem
{
    short idAtendimento;
    short idGrupoAtual;
    short idPessoaLinhaHistorico;
    short inCri;
    short discador;
    short dataAtual;
    short dataAndamento;
    short dsValorParametro;
    short dsObservacao;
    short inRC;
	short idAndamento;
	short idPessoaUsuarioAtual;
};

#endif
