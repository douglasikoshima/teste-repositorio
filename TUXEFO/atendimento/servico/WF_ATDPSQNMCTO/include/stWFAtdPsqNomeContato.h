/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:59 $
 **/

#ifndef __STWFATDPSQNMCTO_H__
    #define __STWFATDPSQNMCTO_H__

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>

using namespace std;

#ifndef NUM_LINHAS
#define NUM_LINHAS 100 // tamanho default de um bloco de linhas
#endif

#define SUCESSO                0
#define PADRAO_NAO_ENCONTRADO -1

struct st_WFAtdPsqNomeContato
{
    int tamamhoDoBloco;
    int pageNumber;
    string pesquisa; // padrão de caracteres a ser procurado
    string idUFOperadora;
    string idTipoLinha;
    string idTipoCarteira;
    string idSegmentacao;
    string idTipoRelacionamento;
    string idGrupo;

    st_WFAtdPsqNomeContato() { tamamhoDoBloco = NUM_LINHAS;pageNumber = 0; }
} ;

struct st_vlWFAtdPsqNomeContato
{
    short pesquisa;
    short tamamhoDoBloco;
    short pageNumber;
    short idUFOperadora;
    short idTipoLinha;
    short idTipoCarteira;
    short idSegmentacao;
    short idTipoRelacionamento;
    short idGrupo;

    st_vlWFAtdPsqNomeContato() { memset(this,-1,sizeof(st_vlWFAtdPsqNomeContato)); }

} ;

#endif // __STWFATDPSQNMCTO_H__