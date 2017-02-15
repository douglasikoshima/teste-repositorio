#ifndef CMENU
#define CMENU


#undef SQLCA
#define SQLCA_NONE


#include <Util/Util.hpp>
#include <sqlca.h>
#include <tuxfw/tuxfw.h>

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
	int	getInPermitido();
	int getIdContato();
	int getIdCanal();
	int getIdTipoLinha();
	int getIdGrupo();
	char *getSgUF();
	int	getIdUF();
	char *getNmItemPai();
	char *getDsTipoLinha();
	char *getDsGrupo ();
	int   getNivel();
	int   getSqApresentacao();
	int alterarMenu(int iIdItemMenu, int iIdCanal, int iIdUF, int iIdTipoLinha, int iIdGrupo, int iAtivo,char*login,char*ip,char*nmItemMenu);
	

	void	setIdItemMenu(int);
	void	setIdItemMenuPai(int);
	void	setNmItem( char *);
	void	setNmAction( char *);
	void	setInPermitido(int);
	void    setIdContato(int);
	void    setIdCanal(int );
	void	setIdTipoLinha (int );
	void	setIdGrupo (int );
	void	setSgUF( char *);
	void	setIdUF(int );
	void	setNmItemPai (char *cNmItemMenuPai);
	void	setDsTipoLinha (char *cDsTipoLinha);
	void    setNivel(int iNivel);
	void	setDsGrupo (char *cDsGrupo);
	void    setSqApresentacao(int iSqApresentacao);

	void	consultarListagemMenu(int iNumPagina, list <CMenu> & lstFiltros, list <CMenu> & lstMenu);
	char	*itoa(int i,char *dest,int /*radix*/);
	void	obterDadosMenu(int, int, int, int, list <CMenu> & lstMenu, bool, int);
	void	consultarMenu(list <CMenu> & lstFiltro, list <CMenu> & lstMenu);
	int		consultarNrPaginas(list <CMenu> & lstFiltros );
	void	consultarUFDisp( list <CMenu> & lstMenu );
	void	consultarTipoLinhaDisp(list <CMenu> &lstMenu);
	void	consultarDsTipoLinha (list <CMenu> &lstMenu);
	void	consultarTipoRelacionamentoPessoa(list <CMenu> &lstMenu);
	void	consultarIdGrupo (int iIdPessoa, int iNrLinha, int iCdAreaRegistro);
	void    consultarMenuPai (int iIdCanal, list <CMenu> &lstMenu);
	void    alterarOrdemMenu(int iIdItemMenu, int iSqApresentacao);
	void    consultarMenuFilho (int iIdItemMenuPai, int iIdCanal, list <CMenu> &lstMenu);
private:
	void obterDadosMenuDB(int, int, int, int, list <CMenu> & lstMenu, bool, int);
	void consultarMenuDB(list <CMenu> & lstFiltro, list <CMenu> & lstMenu);
	void consultarListagemMenuDB(int iNumPagina, list <CMenu> &lstFiltros, list <CMenu> &lstMenu);
	int alterarMenuDB(int iIdItemMenu, int iIdCanal, int iIdUF, int iIdTipoLinha, int iIdGrupo, int iAtivo,char*login,char*ip,char*nmItemMenu);
	void consultarUFDispDB( list <CMenu> & lstMenu );
	void MontaPesquisaLiteral(char *chrPesquisa, list <CMenu> & lstFiltros );

	int m_iIdItemMenu;
	int m_iIdItemMenuPai;
	int m_iIdCanal; 
	int m_iIdTipoLinha;
	int m_iIdTipoGrupo;
	char cNmItem[255];
	char cNmAction[255];
	int m_iInPermitido;
	int m_iIdContato;
	int m_idUF;
	int m_iNivel;
	char cNmItemMenuPai[255];
	char cDsTipoLinha[30];
	char cSgUF[255];
	char cDsGrupo[255];
	int m_iSqApresentacao;


};
#endif		// !defined( CMENU )

