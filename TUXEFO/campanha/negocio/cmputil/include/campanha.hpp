//
// $Id: campanha.hpp,v 1.1 2009/07/31 15:35:21 a5110702 Exp $
//

#ifndef _MODULO_CAMPANHA_
#define _MODULO_CAMPANHA_

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>

#include "../../../commons/cpputil/include/cpputil.hpp"
#include "../../../commons/orautil/include/orautil.hpp"

// Definicoes dos codigos base para erros
#define OKCMP              "05I0000"
#define NOKCMP             "05E0000"
#define ERRCMP             "05E%04d"
#define COD_BASE_CAMPANHA  1000

//
// Implementam interface (atual). 
// Ver modificacao em orautil.hpp e cpputil.hpp
// 
extern void sql_error(char *msg);
extern void sql_notfound(char *msg);
extern int  get_idUsuario(char *usuario);
extern int  get_tag(char *parm,DOMNode *dnode,char *tag,int indice,int codErro);

#endif
