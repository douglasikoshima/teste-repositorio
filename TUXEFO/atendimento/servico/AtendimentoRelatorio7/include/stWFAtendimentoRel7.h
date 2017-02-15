/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:00 $
 **/

#ifndef STWFATENDIMENTOREL7
    #define STWFATENDIMENTOREL7

#define DESC_TOTAL "Total"

#define SIM "1"
#define NAO "0"

#define TAM_DATA      11
#define TAM_DATA_HORA 24
#define TAM_VARCHAR  256
#define TAM_HORA       7
#define TAM_SGUF       3

#define NRO_MAX_REGS 100    // Nro. Maximo de Registros 
                            // por bloco de envio do XML.


enum { RelatorioNivel0, CargaArvoreContatos, CargaDetalhePalitagem };

struct st_AtendimentoRel7
{
    int idContatoPai;
    int idContatoOrigem;
    int idGrupo;
    int nivel;
    char nmGrupo[TAM_VARCHAR];
    char data1[TAM_DATA_HORA];
    char data2[TAM_DATA_HORA];
    char dataHoje[TAM_DATA_HORA];
    char sgUF[32];
    char horaAtual[5];
} ;

struct st_vlAtendimentoRel7
{
    short idContatoPai;
    short idContatoOrigem;
    short idContato;
    short idGrupo;
    short nivel;
    short nmGrupo;
    short data1;
    short data2;
    short dataHoje;
    short sgUF;
    short horaAtual;
} ;

struct ArvoreContatos
{
    int idContatoPai;
    int idContato;
    char nmContato[TAM_VARCHAR];
    int nivel;
    bool possuiDescendente;
};

struct Palitagem
{
    char data[TAM_DATA];
    char hora[TAM_HORA];
    char sgUF[TAM_SGUF];
    char nmGrupo[TAM_VARCHAR];
    char nmContato[TAM_VARCHAR];
    bool possuiDescendente;
    int idContato;
    int idContatoPai; // id do pai do nivel sendo pesquisado no momento
    int idGrupo;
    int quantidade;
};

struct PalitagemAgrupada
{
    char hora[TAM_HORA];
    char descricao[TAM_VARCHAR];
    int qtdeHoje;
    int qtdeData1;
    int qtdeData2;
    int idContatoPai;
    bool possuiDescendente;
};

struct TotalPalitagemAgrupada
{
    char hora[TAM_HORA];
    int qtdeHoje;
    int qtdeData1;
    int qtdeData2;
};

struct Datas
{
    char hoje[32];
    char data1[32];
    char data2[32];
};

struct AgrupamentoNivelZero
{
    char hora[TAM_HORA];
    char sgUF[TAM_SGUF];
    int qtdeHoje;
    int qtdeData1;
    int qtdeData2;
};

struct TotaisAgrupamentoNivelZero
{
    char hora[TAM_HORA];
    int qtdeHoje;
    int qtdeData1;
    int qtdeData2;
};

struct Paginacao
{
    int hasNext;
    int pageNumber;
};
#endif

