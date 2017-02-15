#ifndef CMENU
#define CMENU

#include <sqlca.h>
#include <tuxfw.h>
#include "Util.hpp"
#include <list>
using namespace std;



class CMenu  {

public:
	CMenu();
	virtual ~CMenu();
	int getIdItemMenu();
	int	getIdItemMenuPai();
	char *	getNmItem();
	char *	getNmAction();
	

	void	setIdItemMenu(int);
	void	setIdItemMenuPai(int);
	void	setNmItem( char *);
	void	setNmAction( char *);


	void obterDadosMenu(int, list <CMenu> & lstMenu);

private:
	void obterDadosMenuDB(int, list <CMenu> & lstMenu);
	int m_iIdItemMenu;
	int m_iIdItemMenuPai;
	char cNmItem[255];
	char cNmAction[255];


};
#endif		// !defined( CMENU )

