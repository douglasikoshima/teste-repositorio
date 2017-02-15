#ifndef _MAPAERROS_H_
#define _MAPAERROS_H_

#ifdef WIN32
#pragma warning(push,3)
#endif

#include <map>

#ifdef WIN32
#pragma warning(pop)
#endif

using namespace std;

typedef map <long,char *> MAP_ERROS;

class MapaErros
   {

   public:
      static MAP_ERROS mapErros;
      static bool mapaErrosInicializado;

      MapaErros();
      ~MapaErros() {}

      const char *obterMensagemErro(long codErro);

   private:
      void inicializarMapaErros();

   };

extern MapaErros mapaErros;


struct MapaErroException
{
    int linha;
    int codErro;
    char *msgErroPadrao;
    char labelErro[32];
    const char *msgErro;
    const char *file;

    MapaErros me;

    MapaErroException () { }
    MapaErroException (int _linha,int _codErro,const char *_file=__FILE__) { file=_file;linha=_linha;codErro=_codErro;msgErroPadrao = 0;msgErro = me.obterMensagemErro(codErro);sprintf(labelErro,"04E%04d",codErro); }
    ~MapaErroException () { if (msgErroPadrao) delete msgErroPadrao; }

    char *obterMsgErroPadrao()
    {
        msgErroPadrao = new char[strlen(msgErro)+512];

        if ( msgErroPadrao )
        {
            sprintf(msgErroPadrao,"ARQUIVO:%s,LINHA:%d:'%s'",file,linha,msgErro);
            return msgErroPadrao;
        }

        return "*** Sem memoria para exibir msg de erro ***";
    }

    const char * obterLabelErro() { return labelErro; }
    int obterCodErro() { return codErro; }
};

#endif
