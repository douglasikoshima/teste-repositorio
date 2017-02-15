//
// $Id: cmputil.cpp,v 1.1 2009/07/31 15:33:56 a5110702 Exp $
//

#include "campanha.hpp"

void sql_error(char *msg)
{
  try{
    sql_error_base(ERRCMP,msg);
  }catch(...){ throw; }
}
void sql_notfound(char *msg)
{
  try{
    sql_notfound_base(ERRCMP,msg);
  }catch(...){ throw; }
}
int  get_idUsuario(char *usuario)
{
  int rc;

  try{
    rc = get_idUsuario_base(ERRCMP,usuario);
  }catch(...){ throw; }

  return(rc);
}

int  get_tag(char *parm,DOMNode *dnode,char *tag,int indice,int codErro)
{
  int rc;
  try{
    rc = get_tag_base(parm,dnode,tag,indice,ERRCMP,codErro);
  }catch(...){ throw; }

  return(rc);
}
