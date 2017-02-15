
#include <tuxfw.h>

#ifndef CLINHA
#define CLINHA

#include <list>
using namespace std;

#define  NO_ERROR	      0

class CLinha  
{

public:

	
    CLinha();
	~CLinha();

 	int			getCdAreaRegistro();
 	int			getNrLinha(); 

 	// Setters
 	void setCdAreaRegistro(int value);
 	void setNrLinha(int value);
 	
	void insereLstRestrita();
	void removeLstRestrita();
	bool consultarLinha();
	bool consultarLinhaListaRestrita();
	bool EnviaSMS(bool bBloqueia, char* cUser);

private:
  
 	int   m_iCdAreaRegistro;
 	int   m_iNrLinha;

};
#endif		// !defined( CLINHA )

