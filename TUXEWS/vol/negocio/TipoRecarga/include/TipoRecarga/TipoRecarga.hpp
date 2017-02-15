// TipoRecarda.hpp: interface for the CTipoRecarga class.
//
//////////////////////////////////////////////////////////////////////

#ifndef CTIPORECARGA
#define CTIPORECARGA

#include <list>
using namespace std;

class CTipoRecarga
{
public:
    CTipoRecarga();
    virtual ~CTipoRecarga();

	// Métodos de acesso aos atributos
 	// Getters
	int		getIdTipoRecarga();
	char *  getDsTipoRecarga();
	char *  getDtValidade1();
	char *  getDtValidade2();
	char *  getVlTipoRecarga();

 	// Setters
	void setIdTipoRecarga(int value);
	void setDsTipoRecarga(char *value);
	void setDtValidade1(char *value);
	void setDtValidade2(char *value);
	void setVlTipoRecarga(char *value);

	// Operação de Negocio (Interface)
	static void consultarTipoRecargas(int iCdAreaRegistro, list< CTipoRecarga > & listaRecargas, int iIdUFOperadora);
	
private:
  
	// Dados de Negocio
	int  m_iIdTipoRecarga;
	char m_cDsTipoRecarga[56];
	char m_cDtValidade1[32];
	char m_cDtValidade2[32];
	char m_cVlTipoRecarga[32];

    // Métodos de acesso a banco de dados
    static void carregarTipoRecargasDB(int iCdAreaRegistro, list<CTipoRecarga> & listaRecargas, int iIdUFOperadora);
};

#endif // CTIPORECARGA
