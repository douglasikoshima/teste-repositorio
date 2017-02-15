//
// $Id: cpputil.hpp,v 1.1 2009/07/31 15:34:20 a5110702 Exp $
//

#ifndef _CPP_UTIL_H_
#define _CPP_UTIL_H_

#include "tuxfw.h"

// Definicoes dos codigos base para erros
// -> campanha\negocio\cmputil\include 
// -> fidelizacao\negocio\fidutil\include
#define OKMSG              "Succes Execution"
#define NOKMSG             "Fail Execution"

// Definicoes de log 
// tuxfw_getlogger : Para chamar log em funcoes fora do objeto.
#define ulog tuxfw_getlogger()->debug
#define ulog_int(vrint)  ulog(#vrint"  : [%d]",vrint)
#define ulog_var(vrora)  ulog(#vrora"  : [%.*s]",vrora.len,vrora.arr)
#define ulog_str(vrstr)  ulog(#vrstr"  : [%s]",vrstr)
#define ulog_start(nome) ulog("start: %s ",#nome)
#define ulog_end(nome)   ulog("end: %s ",#nome)

//
// Modificacao:
//   * get_tag -> get_tag_base
//   Modificado por que no inicio, trabalhava com conceito errado :
//      fidelizacao/{campanha,retencao}.
//   Entao todos os erros seriam de fidelizacao. Na realidade, sao 
// modulos diferentes ( campanha e fidelizacao ).
//   Para nao ter que mudar a interface de chamada de todos os 
// servicos ( >100 ). Modifiquei os servicos base e serah criado
// em cada submodulo ( cmputil, fidutil ) chamadas que implementem
// a interface atual, e esta chamarah as adaptacoes.
//   
extern int  get_tag_base(char *parm,DOMNode *dnode,char *tag,int indice,
			 char *fmt,int codErro);


void cl_flag(int *flg);
void st_flag(int *flg);

#endif
