#ifndef TRIPLEDESH
#define TRIPLEDESH


class CTripleDES
{

protected:
    unsigned char _szKey1[8], _szKey2[8], _szKey3[8];

public:

    CTripleDES(void);
    ~CTripleDES(void);


    void cryptString(char *pIn, char *pOut);
    void decryptString(char *pIn, char *pOut);

    void crypt(unsigned char *pIn, unsigned char *pOut);
    void decrypt(unsigned char *pIn, unsigned char *pOut);

    void setKeys(char *pKey1, char *pKey2, char *pKey3);
    void setKey1(unsigned char *pKey);
    void setKey2(unsigned char *pKey);
    void setKey3(unsigned char *pKey);


    unsigned char* hex2bin(unsigned char* block);
    unsigned char* bin2hex(unsigned char* block);
    void upper(char *pTok);
};

#endif
