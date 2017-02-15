/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.2.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/01/23 18:08:56 $
 **/

#ifndef STWFATDOBTERPARAM
    #define STWFATDOBTERPARAM

struct st_AtdObterParam
{
    long idPessoaLinhaHistorico;
    long idAtendimento;
};

struct st_vlAtdObterParam
{
    short idPessoaLinhaHistorico;
    short idAtendimento;
};

struct DadosLinha
{
    int idTipoLinha;
    long idLinhaTelefonica;
    int idPessoaDePara;
    int idAreaRegistro;
    int cdAreaRegistro;
    int idUfOperadora;
    int idConta;
};

#endif
