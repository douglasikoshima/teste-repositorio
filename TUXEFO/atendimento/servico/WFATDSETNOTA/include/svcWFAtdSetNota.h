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
#define MSG_TEXT_PARAMETRO_INVALIDO "Parâmetros de entrada inválidos"
#define MSG_TEXT_CONSTRAINT_VIOLADA "Registro duplicado ao criar nota"
#define MSG_TEXT_ERRO_ORACLE "Erro de Oracle"
#define MSG_TEXT_PROCESSO_NOT_FOUND "Processo não encontrado ou número inválido"
#define MSG_TEXT_PROCESSO_CRI "Processo não associado ao CRI"

// Códigos de mensagens de erro
#define MSG_CODE_PARAMETRO_INVALIDO 100
#define MSG_CODE_CONSTRAINT_VIOLADA 101
#define MSG_CODE_ERRO_ORACLE 102
#define MSG_CODE_PROCESSO_NOT_FOUND 103
#define MSG_CODE_PROCESSO_CRI 104

#endif