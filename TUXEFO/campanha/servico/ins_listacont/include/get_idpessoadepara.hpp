//
// $Id: get_idpessoadepara.hpp,v 1.1 2009/07/31 15:33:38 a5110702 Exp $
//
#ifndef _GET_IDPESSOADEPARA_HPP_
#define _GET_IDPESSOADEPARA_HPP_

#define ERR_CAMPANHA_GET_IDDEPARA_CD    "00E1000"
#define ERR_CAMPANHA_GET_IDDEPARA_MSG   "recuperacao do id cliente para o documento"

int get_idpessoadepara( char* pnmDocumento, int* pidPessoaDePara);

#endif
