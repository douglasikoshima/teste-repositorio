//
// $Id: fidutil.cpp,v 1.1 2009/07/31 15:34:30 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

//
// Adaptacoes para tratar diferencas entre fidelizacao e campanha
//
void sql_error(char *msg)
{
  try{
    sql_error_base(ERRFID,msg);
  }catch(...){ throw; }
}
void sql_notfound(char *msg)
{
  try{
    sql_notfound_base(ERRFID,msg);
  }catch(...){ throw; }
}
int  get_idUsuario(char *usuario)
{
  int rc;

  try{
    rc = get_idUsuario_base(ERRFID,usuario);
  }catch(...){ throw; }

  return(rc);
}

int  get_tag(char *parm,DOMNode *dnode,char *tag,int indice,int codErro)
{
  int rc;
  try{
    rc = get_tag_base(parm,dnode,tag,indice,ERRFID,codErro);
  }catch(...){ throw; }

  return(rc);
}



