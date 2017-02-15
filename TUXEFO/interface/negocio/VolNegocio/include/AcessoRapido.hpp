#ifndef CACESSRAPID
#define CACESSRAPID

#include <list>
using namespace std;


class CAcessoRapido{
public:
    CAcessoRapido();

	/*Get*/
	int getIdAcessRapid(void);
	char * getNmItem(void);
	char * getDsHint(void);

	/*Set*/
	void setIdAcessRapid(int);
	void setNmItem(char *);	
	void setDsHint(char *);

    virtual ~CAcessoRapido();
	void consultarAcessoRapido(int , list <CAcessoRapido> & list);
	int insertAcessoRapido(int , int );
	int excluirAcessoRapido(int, int);

private:
		void consultarAcessoRapidoDB( int, list <CAcessoRapido> & list );
		int insertAcessoRapidoDB( int, int );
		int excluirAcessoRapidoDB(int, int);

		int m_iIdAcessRapid;
		char m_cNmItem [ 255 + 1 ];
		char m_cDsHint [ 255 + 1 ];
};
#endif		// !defined( CACESSRAPID )

