//
// $Id: del_lista.hpp,v 1.1.2.1 2010/01/13 22:57:17 a5110702 Exp $
//
#ifndef _DEL_LISTA_HPP_
#define _DEL_LISTA_HPP_

// Declaracao de mensagens de erro
#define ERR_CAMPANHA_INS_LISTA_CD	"00E1000"
#define ERR_CAMPANHA_INS_LISTA_MSG	"INSERT INTO campanha.lista"
#define ERR_CAMPANHA_EXIST_LISTA_CD	"00E1000"
#define ERR_CAMPANHA_EXIST_LISTA_MSG	"Lista ja cadastrada, nao pode ser inserida novamente "
// define status carga
#define STATUS_DISPONIVEL 0
#define STATUS_SUCESSO 1
#define STATUS_ERRO 2
#define STATUS_EMCARGA 3

extern int del_lista(char*, char*,int*,int,char*,char*);

#endif
