//
// $Id: ins_pessoalista.hpp,v 1.1.2.1 2010/01/13 22:57:17 a5110702 Exp $
//
#ifndef _INS_PESSOALISTA_HPP_
#define _INS_PESSOALISTA_HPP_

#define ERR_CAMPANHA_INS_PESSOALISTA_CD  "00E1000"
#define ERR_CAMPANHA_INS_PESSOALISTA_MSG "INSERT INTO campanha.pessoaLista"

int ins_pessoalista( int pidUsuario, int pidListaConteudo, int pidPessoaDePara);

#endif
