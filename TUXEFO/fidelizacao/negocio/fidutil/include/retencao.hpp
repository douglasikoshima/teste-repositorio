//
// $Id: retencao.hpp,v 1.1 2009/07/31 15:33:20 a5110702 Exp $
//

#ifndef _MODULO_RETENCAO_
#define _MODULO_RETENCAO_

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>

#include "../../cpputil/include/cpputil.hpp"
#include "../../orautil/include/orautil.hpp"

// Definicoes dos codigos base para erros
#define OKFID              "06I0000"
#define NOKFID             "06E0000"
#define ERRFID             "06E%04d"
#define COD_BASE_RETENCAO  2000

// Tipos de ofertas
#define  ENC_RETIDO     2
#define  ENC_CANCELADO  3
#define  OF_ADEQUACAO   1
#define  OF_APARELHO    2
#define  OF_BONUS       3
#define  OF_MIGRACAO    4
#define  OF_PONTOS      5
#define  OF_SUSPTEMP    6

//
// Implementam interface (atual). 
// Ver modificacao em orautil.hpp e cpputil.hpp
// 
extern void sql_error(char *msg);
extern void sql_notfound(char *msg);
extern int  get_idUsuario(char *usuario);
extern int  get_tag(char *parm,DOMNode *dnode,char *tag,int indice,int codErro);

#endif
