//
// $Id: ins_listaconteudo.hpp,v 1.1.2.1 2010/01/13 22:57:17 a5110702 Exp $
//
#ifndef _INS_LISTACONTEUDO_HPP_
#define _INS_LISTACONTEUDO_HPP_

// Declaracao de mensagens de erro
#define ERR_CAMPANHA_INS_LISTACONTEUDO_CD	"00E1000"
#define ERR_CAMPANHA_INS_LISTACONTEUDO_MSG	"INSERT INTO campanha.listaConteudo"
#define ERR_CAMPANHA_UPD_LISTACONTEUDO_CD	"00E1000"
#define ERR_CAMPANHA_UPD_LISTACONTEUDO_MSG	"UPDATE campanha.listaConteudo "

#include "tuxfwexception.h"
#include "tuxfwbasicoraexception.h"


int ins_listaconteudo( int idUsuario, int idLista, int pTelefone, char* pnmContato, char* pnmDocumento, int* pidListaConteudo) throw(TuxException, TuxBasicOraException);


#endif
