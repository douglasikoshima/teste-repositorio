#ifndef CACESSRAPID
#define CACESSRAPID

#include <list>
using namespace std;


class CAcessoRapido{
public:
    CAcessoRapido();

	/*Get*/
	int getIdItemMenu(void);
	int getIdItemMenuPai(void);
	char * getNmItem(void);
	char * getDsHint(void);

	/*Set*/
	void setIdItemMenu(int);
	void setIdItemMenuPai(int);
	void setNmItem(char *);	
	void setDsHint(char *);

    virtual ~CAcessoRapido();
	void consultarAcessoRapido(int , int, list <CAcessoRapido> & list, int, int);
	int insertAcessoRapido(int , int );
	int excluirAcessoRapido(int, int);
	int consultarIdUF(int iCdAreaRegistro);
	char *itoa(int i,char *dest,int /*radix*/);
private:
		void consultarAcessoRapidoDB( int, int, list <CAcessoRapido> & list, int, int );
		int insertAcessoRapidoDB( int, int );
		int excluirAcessoRapidoDB(int, int);
	

		int m_iIdItemMenu;
		int m_iIdItemMenuPai;
		char m_cNmItem [ 255 + 1 ];
		char m_cDsHint [ 255 + 1 ];
};
#endif		// !defined( CACESSRAPID )

