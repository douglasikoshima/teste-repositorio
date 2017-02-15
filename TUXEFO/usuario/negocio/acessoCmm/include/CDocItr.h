#ifndef CDocumentoItrH
#define CDocumentoItrH

#include <stdlib.h>
#include <string.h>
#include <tuxfw.h>

struct STDocumentoRegistro
{
	char cidDocumento[21+1];
	char ccdCpfCnpjBase[255+1];
	char ccdCnpjFilial[255+1];
	char ccdCpfCnpjControle[255+1];
	char cnrDocumento[255+1];
	char csgOrgaoExpedidor[255+1];
	char cdtEmissao[12+1];
	char cidPais[21+1];
	char csgPais[255+1];
	char cnmPais[255+1];
	char cidUF[21+1];
	char csgUF[255+1];
	char cnmUF[255+1];
	char cidTipoDocumento[21+1];
	char csgTipoDocumento[255+1];
	char cdsTipoDocumento[255+1];
};

class CDocumentoItr
{
public:
	CDocumentoItr();
	~CDocumentoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STDocumentoRegistro* Registro( void );
	STDocumentoRegistro* Registro(int nPosicao);

private:
	STDocumentoRegistro* stcrDocumento;
	int _iQuantidade;
	int _iPosicao;

protected:                             
	void Add( char* cidDocumento,      
	          char* ccdCpfCnpjBase,    
	          char* ccdCnpjFilial,     
	          char* ccdCpfCnpjControle,
	          char* cnrDocumento,               
	          char* csgOrgaoExpedidor,          
	          char* cdtEmissao,                 
	          char* cidPais,                    
	          char* csgPais,                    
	          char* cnmPais,                    
	          char* cidUF,                      
	          char* csgUF,                      
	          char* cnmUF,                      
	          char* cidTipoDocumento,           
	          char* csgTipoDocumento,           
	          char* cdsTipoDocumento );         
	void ZeraDocumento( void );
         
};       
         
#endif   