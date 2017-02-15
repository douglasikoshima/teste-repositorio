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
	void consultarAcessoRapido(long , int, list <CAcessoRapido> & list, int, int);
	int insertAcessoRapido(long , int );
	int excluirAcessoRapido(long, int);
	int consultarIdUF(int iCdAreaRegistro);
	char *itoa(int i,char *dest,int /*radix*/);
private:
		void consultarAcessoRapidoDB( long, int, list <CAcessoRapido> & list, int, int );
		int insertAcessoRapidoDB( long, int );
		int excluirAcessoRapidoDB(long, int);
	

		int m_iIdItemMenu;
		int m_iIdItemMenuPai;
		char m_cNmItem [ 255 + 1 ];
		char m_cDsHint [ 255 + 1 ];
};
#endif		// !defined( CACESSRAPID )

