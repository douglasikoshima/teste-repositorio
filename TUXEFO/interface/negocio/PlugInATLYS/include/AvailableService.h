#ifndef TXPB_AVAILABLE_SERVICE_H
#define TXPB_AVAILABLE_SERVICE_H 1


#include <list>

using namespace std;

class CAvailableService
{
    char m_cName[30];
    char m_cType[30];

public:
	CAvailableService();
	virtual ~CAvailableService();

	// Metodo de Acesso ao DB
	static char* getTypeServiceDB(char *, char *);
	static void  getMembersServiceDB(char *, list<CAvailableService>&);

	// SYDATE
	static char* getSysDateBD(char *cDate, char *cFormato = "YYYY-MM-DD");

	// Sobrecarga do operador de atribuição.
	CAvailableService& operator=(const CAvailableService &);

	// Setters
	void setName(char* );
	void setType(char* );
	// Getters
	char* getName();
	char* getType();
};


#endif
