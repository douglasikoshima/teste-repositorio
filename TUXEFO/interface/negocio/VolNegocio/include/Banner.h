#ifndef _BANNER_
#define _BANNER_

#include<tuxfw.h>

class Banner
{
	public:
		Banner();
		~Banner();
		// métodos
		int getBanners(XMLGen*xml,char*idAreaBanner);
		int getParametrosBusca(XMLGen*xml);
		int pesquisaBanner(XMLGen*xml,char*,char*,char*,char*,int numPagina,int registros);
		int incluirBanner(char* idCampanhaPrm,char*urlBanner,char*idAreaBanner,char*idTipoBanner,char*dsBanner,char*nmBanner,char*idBanner);
		int updateBanner(char*banner,char*link);
		int removerGrupoBanner(char*);
		int associarGrupoBanner(char*,char*);
		int log(char*,char*,char*,char* operacao);
		int log(char*idBanner,char*login,char*ip);
	private:
		
};

#endif