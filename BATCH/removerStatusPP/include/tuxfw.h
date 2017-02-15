/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2010/05/18 20:04:29 $
 * @ID      $Id: tuxfw.h,v 1.1.2.1 2010/05/18 20:04:29 a5114878 Exp $
 **/

#ifndef TuxFW
#define TuxFW

#ifndef TuxFW_W32_LIB

 #pragma comment(lib,"xerces-c_2.lib")
 #pragma comment(lib,"tuxfw.lib")
 #ifndef _LIB
  //#pragma comment(lib,"orasql8.lib")
  //#pragma comment(lib,"orasqx8.lib")

//  #pragma comment(lib,"orasql9.lib")
//  #pragma comment(lib,"orasqx9.lib")

 #endif
#else
 // 
 // Flag para compilação do FWTUX com geração massiva de 
 // mensagens de log ao longo das principais classes do nucleo
 // Este tratamento de mensagens para debug fica desabilitado por
 // default, exigindo recompilação para sua ativação
 // E presenca destes codigos de mensagens de debug na versao releas
 // pode comprometer seriamente a performance e aumentar muito o 
 // consumo de logs das aplicações. Use somente enquanto necessário.
 // #define TUXFW_DEBUG_CORE
#endif


#include<atmi.h>
#include<userlog.h>

#ifdef WIN32
#pragma warning( disable : 4290 )
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#endif


#ifdef WIN32
 #include "tuxfwxmlgen.h"
 #include "tuxfwerrors.h"
 #include "tuxfwlogexception.h"
 #include "tuxfwlog.h"
 #include "tuxfwlogfile.h"
 #include "tuxfwservercontext.h"
 #include "tuxfwremoteservice.h"
 #include "tuxfwhelper.h"
 #include "tuxfwbasicsvc.h"
 #include "tuxfwexception.h"
 #include "tuxfwbasicsvcexception.h"
 #include "tuxfwbasicoraexception.h"
 #include "tuxfwhelper.h"
 #include "tuxfwbasicsvc.h"
 #include "tuxfworastub.h"
 #include "tuxfwservercontext.h"
 #include "tuxfwsvr.h"
 #include "tuxfwmessage.h"
 #include "tuxfwglobal.h"
 #include "tuxfwmacroutils.h"
#else
 #include "tuxfw/tuxfwxmlgen.h"
 #include "tuxfw/tuxfwerrors.h"
 #include "tuxfw/tuxfwlogexception.h"
 #include "tuxfw/tuxfwlog.h"
 #include "tuxfw/tuxfwlogfile.h"
 #include "tuxfw/tuxfwservercontext.h"
 #include "tuxfw/tuxfwremoteservice.h"
 #include "tuxfw/tuxfwhelper.h"
 #include "tuxfw/tuxfwbasicsvc.h"
 #include "tuxfw/tuxfwexception.h"
 #include "tuxfw/tuxfwbasicsvcexception.h"
 #include "tuxfw/tuxfwbasicoraexception.h"
 #include "tuxfw/tuxfwhelper.h"
 #include "tuxfw/tuxfwbasicsvc.h"
 #include "tuxfw/tuxfworastub.h"
 #include "tuxfw/tuxfwservercontext.h"
 #include "tuxfw/tuxfwsvr.h"
 #include "tuxfw/tuxfwmessage.h"
 #include "tuxfw/tuxfwglobal.h"
 #include "tuxfw/tuxfwmacroutils.h"
#endif
   /*"
	* Declara funcao global para recuperação do contexto de 
	* execução de servicos setado em tpsvrinit
	*/

//namespace tuxfw
//{


//}
/// *****************************************************************

#endif
