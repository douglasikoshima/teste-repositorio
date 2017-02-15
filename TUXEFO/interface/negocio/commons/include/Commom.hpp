/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:29 $
 **/

#include <ctype.h>
#include <string.h>
#include <math.h>

/*
 * Constantes e defini��es comuns para todos os servi�os de URA
 */

#ifndef Commom_URA
#define Commom_URA

// Codigo de retorno
// Alterado as mensagens de "E"rro para "W"arning
#define COD_I_0000					"24I0000"	// Prossegue atendimento - OK
#define COD_E_0001					"24W0001"	// Conta n�o cadastrada / desligada
#define COD_E_0002					"24W0002"	// Erro nos parametros de entrada
#define COD_E_0003					"24W0003"	// Erro no acesso ao banco
#define COD_E_0004					"24W0004"	// Erro no acesso as sub-rotinas
#define COD_E_0005					"24W0005"	// Erro troca de senha = cli = usu
#define COD_E_0006					"24W0006"	// Cliente n�o pode ser atendido pelo FO
#define COD_E_0007					"24W0007"	// Senha sequencial
#define COD_E_0008					"24W0008"	// D�gitos iguais
#define COD_E_0009					"24W0009"	// Opera��o n�o encontrada
#define COD_E_0010					"24W0010"	// Senha anterior diferente da atual

// TAG STATCOM
#define TAG_STATCOM                                     "statCom"

// Mensagem de retorno
#define MSG_I_0000					"Successfully executed"
#define MSG_E_0001					"Account disabled or not found"
#define MSG_E_0002					"Error in input/output"
#define MSG_E_0003					"Error access database"
#define MSG_E_0004					"Error access sub-routines"
#define MSG_E_0005					"Unknown error"
#define MSG_E_0006					"N�o atendido pelo FO"
#define MSG_E_0007					"Senha sequencial"
#define MSG_E_0008					"D�gitos iguais"
#define MSG_E_0009					"Dados n�o encontrados"
#define MSG_E_0010					"Senha anterior diferente da atual"
#define MSG_E_0011					"Senha bloqueada"
#define MSG_E_SENHA					"Senha inv�lida"

// Status da comunica��o
#define MSG_E_1001					"24W1001"	// Erro de programa
#define MSG_E_1002					"24W1002"	// Programa fora do ar

/* Constantes de retorno dos servi�os */
#define RET_OK					0

/* Constantes de c�digos de erro */
#define ERR_NOT_FOUND_OR_DISABLED		1
#define ERR_IN_PARAM				2
#define ERR_DATABASE				3
#define ERR_SUBROUTINES				4
#define ERR_UNDEFINED				5
#define ERR_SENHA_CLI_USU			6
#define ERR_NAO_ATENDIDO_FO			7
#define ERR_SEQ_INVALIDA			8
#define ERR_DIG_IGUAIS				9
#define ERR_SENHA_INVALIDA			10
#define ERR_SENHA_BLOQUEADA			11
/* 
 *Constantes de erro, aviso e sucesso.
 */

/* Finaliza��o OK */
#define MSG_OK						"24I0000"	// Finaliza��o OK.

/* Erros gerais */
#define MSG_E_2050					"24W2050"	// Erro de aloca��o de mem�ria
#define MSG_E_2051					"24W2051"	// Erro chamando subrotinas
#define MSG_E_2052					"24W2052"	// Sistema indispon�vel

/* Erros de par�metros de entrada */
#define MSG_E_2101					"24W2101"	// DDD n�o encontrado nos par�metros de entrada
#define MSG_E_2102					"24W2102"	// Fone n�o encontrado nos par�metros de entrada

/* Erros de processamento */
#define MSG_E_2201					"24W2201"	// Erro na recupera��o do valor de pontos

/************************************************************************
 * Funcao: Verifica se em um buffer contem somente caracter numerico.   *
 * Parametro: d = dado para realizar a verificacao.                     *
 * Retorno: 0 = contem caracter diferente de numerico                   *
 *          1 = contem somente caracter numerico                        *
 ************************************************************************/
int IsNumeric(char* d){
    for(; *d; d++ )
       if ( !isdigit(*d) )
            return 0;
    return 1;
}

#endif
