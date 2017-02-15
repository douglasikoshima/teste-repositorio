#ifndef __SVCWFATDSETNOTA
#define __SVCWFATDSETNOTA

#include "string.h"
#include "stdlib.h"

// Mensagems de erro
#define MSG_PARAMETRO_INVALIDO "00W0001"
#define MSG_CONSTRAINT_VIOLADA "00W0002"
#define MSG_ERRO_ORACLE "00W0003"
#define MSG_ERRO_PROCESSO_NOT_FOUND "00W0004"
#define MSG_ERRO_PROCESSO_CRI "00W0005"

// Mensagens de erro texto
#define MSG_TEXT_PARAMETRO_INVALIDO "Par�metros de entrada inv�lidos"
#define MSG_TEXT_CONSTRAINT_VIOLADA "Registro duplicado ao criar nota"
#define MSG_TEXT_ERRO_ORACLE "Erro de Oracle"
#define MSG_TEXT_PROCESSO_NOT_FOUND "Processo n�o encontrado ou n�mero inv�lido"
#define MSG_TEXT_PROCESSO_CRI "Processo n�o associado ao CRI"

// C�digos de mensagens de erro
#define MSG_CODE_PARAMETRO_INVALIDO 100
#define MSG_CODE_CONSTRAINT_VIOLADA 101
#define MSG_CODE_ERRO_ORACLE 102
#define MSG_CODE_PROCESSO_NOT_FOUND 103
#define MSG_CODE_PROCESSO_CRI 104

#endif