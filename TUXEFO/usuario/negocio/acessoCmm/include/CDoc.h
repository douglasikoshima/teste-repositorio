#ifndef CDocumentoH
#define CDocumentoH

#include<tuxfw.h>
#include "CDocItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CDocumento : public CDocumentoItr
{
	public:
		CDocumento();
		~CDocumento();
		
		int ListIdUser( char* cidUsuario );
		
		int ListId( char* cidDocumento );
		
		int ListAll( void );
		
		int ListDocSiglaTipo( char* cnrDoc, char* csgTipo );
		int ListDocIdTipo( char* cnrDoc, char* cidTipo );
		int ListDoc( char* cnrDoc );
		int ListDocPorIdPesssoa( char* cidPessoa, char* cidTipo );
		int FindDocumento( char* cnrDocumento, char* cidTipoDocumento );
		
		void GetXml( char* cNomeTag, XMLGen*xml );
		
		int Insert( char* ccdCpfCnpjBase           
	               ,char* ccdCnpjFilial            
	               ,char* ccdCpfCnpjControle       
	               ,char* cnrDocumento             
	               ,char* csgOrgaoExpedidor        
	               ,char* cdtEmissao               
	               ,char* cidPais                  
	               ,char* cidUF                    
	               ,char* cidTipoDocumento         
	               ,char* cidUser );               

		int Update( char* cidDocumento
	               ,char* ccdCpfCnpjBase
	               ,char* ccdCnpjFilial
	               ,char* ccdCpfCnpjControle
	               ,char* cnrDocumento
	               ,char* csgOrgaoExpedidor
	               ,char* cdtEmissao
	               ,char* cidPais
	               ,char* cidUF
	               ,char* cidTipoDocumento
	               ,char* cidUser );
};

#endif