/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.126.1 $
 * @CVS     $Author: a5114242 $ - $Date: 2014/02/04 14:49:20 $
 **/

#ifndef __CWFCOMUNSRELATORIOS_H__
#define __CWFCOMUNSRELATORIOS_H__

#ifdef _CWFCOMUNSRELATORIOS_EXTERN_
#   undef _CWFCOMUNSRELATORIOS_EXTERN_
#   define _CWFCOMUNSRELATORIOS_EXTERN_
#else
#   define _CWFCOMUNSRELATORIOS_EXTERN_ extern
#endif

#define BEGIN_EXTERN_C extern "C" {
#define END_EXTERN_C }

#ifdef WIN32
#pragma warning(disable:4786)
#endif
#include <string>
using namespace std;

#include <tuxfw.h>

// Quantidade default de linhas por bloco
#define NUM_LINHAS 150

// Quantidade maxima de linhas por bloco
#define NUM_MAX_LINHAS  13

#define ID_DATA_MAIS_ANTIGA    0
#define ID_TOTAL_DENTRO_PRAZO  1
#define ID_TOTAL_FORA_PRAZO    2
#define ID_QTDE_FECHADOS       3
#define ID_NUM_TENT_RETORNO    4
#define ID_NUM_TENT_TRATMTO    5
#define ID_NUM_ENCERRADOS      6
#define ID_MOTIVO_FECHMTO      7
#define ID_NUM_FECHADOS        8
#define ID_NOME_REPRESENTANTE  9
#define ID_ID_LOGON_REPRES    10
#define ID_ESTR_ORG           11
#define ID_TEMPO_TRATAMENTO   12
#define ID_TEMPO_ATENDIMENTO  13
#define ID_ID_ANDAMENTO       14
#define ID_ID_ATENDIMENTO     15
#define ID_TEMPO_DEMORA_DIAS  16
#define ID_OPERADORA          17
#define ID_UF                 18
#define ID_NUM_PROCESSO       19
#define ID_DATA_ABERTURA      20
#define ID_DATA_ENTR_GRUPO    21
#define ID_GRUPO_ATUAL        22
#define ID_GRUPO_ABERTURA     23
#define ID_USUARIO_ABERTURA   24
#define ID_CARTEIRA           25
#define ID_SEGMENTO           26
#define ID_FONE_CONTATO       27
#define ID_TIPO_CLIENTE       28
#define ID_ESTADO_PROCESSO    29
#define ID_SUBEST_PROCESSO    30
#define ID_ARVORE_CONTATO     31
#define ID_HORAS_DECORRIDAS   32
#define ID_PRAZO_ANATEL       33
#define ID_PRAZO_INTERNO      34
#define ID_NOME_USU_ABER      35
#define ID_TMP_PSA_PROCESSO   36
#define ID_DIAS_PSA_PROCESSO  37
#define ID_PATH               38
#define ID_NOME_RESPONSAVEL   39
#define ID_NOME_USUARIO_ATUAL 40
#define ID_LOGIN_USU_ATUAL    41
#define ID_DATA_DEVOLUCAO     42
#define ID_NOME_GESTOR        43
#define ID_NUM_PROCESSOS      44
#define ID_RAZ_SOCIAL         45
#define ID_NUM_DOCUMENTO      46
#define ID_NUM_PROTOCOLO      47
#define ID_NUM_LINHA	      48 // Demanda 20640442 - 29/01/2014

#define CSV_SEPARADOR         "|" // Alterado de "," para pipe por solicitação da incidência 3337 de Homologação Vivo

extern "C" _CWFCOMUNSRELATORIOS_EXTERN_ void WFAtdRelCompletarWhere(string &where,DOMNode *entrada,char *dnode);
extern "C" _CWFCOMUNSRELATORIOS_EXTERN_ void WFAtdRelCompletarWhereDetalhe(string &where,DOMNode *entrada,char *dnode);
extern "C" _CWFCOMUNSRELATORIOS_EXTERN_ void WFAtdRelSqlErro(sqlca *sqlca);
extern "C" _CWFCOMUNSRELATORIOS_EXTERN_ void WFAtdRelGerarHeaderSaidaXML(string &nmColunas,XMLGen *saida,DOMNode*entrada);
extern "C" _CWFCOMUNSRELATORIOS_EXTERN_ void WFAtdRelGerarDadosSaidaXML(void *vlColunas,XMLGen *saida);
extern "C" _CWFCOMUNSRELATORIOS_EXTERN_ char *rtrim(char *bf);
extern "C" _CWFCOMUNSRELATORIOS_EXTERN_ bool isColunaFixa(char *p);
extern "C" _CWFCOMUNSRELATORIOS_EXTERN_ char *obterNomeColunaFixa(int idColuna);
extern "C" _CWFCOMUNSRELATORIOS_EXTERN_ bool isCampoAlfanumerico(const char *nomeCampo);



#ifndef WIN32
    extern "C" _CWFCOMUNSRELATORIOS_EXTERN_ char * itoa(int i,char *dest,int radix=10);
#endif

#endif
