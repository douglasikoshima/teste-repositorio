//
// $Id: orautil.hpp,v 1.1 2009/07/31 15:33:37 a5110702 Exp $
//

#ifndef _ORA_UTIL_H_
#define _ORA_UTIL_H_

#include "../../cpputil/include/cpputil.hpp"
#include <ctime>
#include <ctype.h>


#define endOraStr(varstr)      varstr.arr[varstr.len]= '\0'
#define oraToStr(bstr,vchar)   if(!bstr) strncpy(bstr,vchar.arr,vchar.len)
#define strToOra(vchar,bstr)   vchar.len = strlen(bstr);strncpy((char *)vchar.arr,bstr,vchar.len);vchar.arr[vchar.len] = 0
#define strconv(buffer,fonte)  sprintf(buffer,"%d",fonte)

extern struct sqlca sqlca;

//
// Modificacao:
//   * sql_error     -> sql_error_base
//   * sql_notfound  -> sql_notfound_base 
//   * get_idUsuario -> get_idUsuario_base
//   Modificado por que no inicio, trabalhava com conceito errado :
//      fidelizacao/{campanha,retencao}.
//   Entao todos os erros seriam de fidelizacao. Na realidade, sao 
// modulos diferentes ( campanha e fidelizacao ).
//   Para nao ter que mudar a interface de chamada de todos os 
// servicos ( >100 ). Modifiquei os servicos base e serah criado
// em cada submodulo ( cmputil, fidutil ) chamadas que implementem
// a interface atual, e esta chamarah as adaptacoes.
//   
extern void sql_error_base(char *fmt,char *msg);
extern void sql_notfound_base(char *fmt,char *msg);
extern int  get_idUsuario_base(char *fmt,char *usuario);

#endif
