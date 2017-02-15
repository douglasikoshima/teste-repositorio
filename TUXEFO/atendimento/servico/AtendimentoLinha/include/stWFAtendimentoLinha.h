/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/ 

#ifndef STWFATENDIMENTOLINHA
    #define STWFATENDIMENTOLINHA

struct st_AtendimentoLinha
{
    long idAtendimento;
    int  idPessoaLinhaHistorico;
    int  idEstadoLinha;
    int  idUsuarioAlteracao;
    char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoLinha
{
    short idAtendimento;
    short idPessoaLinhaHistorico;
    short idEstadoLinha;
    short idUsuarioAlteracao;
    short dtUltimaAlteracao;
} ;

struct LinhaAtendimento
{
    char idPessoaLinhaHistorico[256];
    char nrLinha[256];
    char cdAreaRegistro[256];
};

#endif

