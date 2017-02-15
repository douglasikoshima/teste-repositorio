#ifndef WALKTREEAUX_
	#define WALKTREEAUX_

#include <tuxfw.h>
#include<SmallString.h>

class WalkTreeAux
{
    public:

		int walkTreeXMLGen(XMLGen *entrada, char * identificador, char** cadena, int entero)
		{

			TuxHelper tx;
			int tamSaida;
			int retorno = 1;

			SmallString p;
			
            if ( p = entrada->retrieveXML(&tamSaida),p.Size() > 0 )
            {
			    SmallString ssLocal;

			    if ( !strstr(p,"encoding") )
			    {
				    ssLocal += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
			    }

			    ssLocal += p;

			    tamSaida = ssLocal.Size();
			    
			    if ( tamSaida )
			    {
				    XercesDOMParser *pParser = new XercesDOMParser;
				    MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)ssLocal.c_str(), tamSaida, "");

				    if ( pParser && pMemBuf )
				    {
					    pParser->parse(*pMemBuf);
					    DOMNode* pDoc = pParser->getDocument();

					    *cadena=tx.walkTree(pDoc, identificador, entero);

					    retorno = 0;
				    }
				    else
				    {
					    tuxfw_getlogger()->debug("Nao foi possivel alocar memoria "
											     "para objetos DOM e/ou Xerces");
				    }

				    delete pParser;
				    delete pMemBuf;
                }
            }

			return retorno;    
		}
};

#endif
