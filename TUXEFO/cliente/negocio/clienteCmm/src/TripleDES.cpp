#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include <tuxfw.h>
#include "../include/3des.h"
#include "../include/TripleDES.h"

CTripleDES::CTripleDES(void)
{
    memset(_szKey1, 0x00, sizeof(_szKey1));
    memset(_szKey2, 0x00, sizeof(_szKey2));
    memset(_szKey3, 0x00, sizeof(_szKey3));
}

CTripleDES::~CTripleDES(void)
{
}

/**********************************************************************************************/
/* recebe as 3 chaves de criptografia em Hexa                                                 */
/**********************************************************************************************/
void CTripleDES::setKeys(char *pKey1, char *pKey2, char *pKey3)
{
    unsigned char *pChaveBin;

    pChaveBin = this->hex2bin((unsigned char *)pKey1);
    memcpy(_szKey1, pChaveBin, 8);

    pChaveBin = this->hex2bin((unsigned char *)pKey2);
    memcpy(_szKey2, pChaveBin, 8);

    pChaveBin = this->hex2bin((unsigned char *)pKey3);
    memcpy(_szKey3, pChaveBin, 8);
}

/**********************************************************************************************/
/* recebe string em Hexa e retorna em binario (decimal)                                       */
/**********************************************************************************************/
void CTripleDES::decryptString(char *pIn, char *pOut)
{
    unsigned char szAuxOut[16];
    unsigned char szAuxIn[16];
    des3_context ctx;
    register int iCount;
    int iLen;

    memset(&ctx, 0x00, sizeof(ctx));
    des3_set_3keys(&ctx, _szKey1, _szKey2, _szKey3);

    iLen=strlen(pIn);

    for(iCount=0; iCount < iLen; iCount+=16)
    {
        memset(szAuxIn, 0x00, sizeof(szAuxIn));
        memcpy(szAuxIn, pIn+iCount, 16);
        des3_decrypt(&ctx, this->hex2bin(szAuxIn), szAuxOut);
        memcpy(pOut+(iCount/2), szAuxOut, 8);
    }
}

/**********************************************************************************************/
/* recebe string em binario(decimal) e retorna em hexa                                        */
/**********************************************************************************************/
void CTripleDES::cryptString(char *pIn, char *pOut)
{
    unsigned char szAuxIn[8], szAuxOut[8];
    des3_context ctx;
    register int iCount;
    int iLen;

    memset(&ctx, 0x00, sizeof(ctx));
    des3_set_3keys(&ctx, _szKey1, _szKey2, _szKey3);

    iLen=strlen(pIn);

    for(iCount=0; iCount < iLen; iCount+=8)
    {
        memset(szAuxIn, 0x00, sizeof(szAuxIn));
        memcpy(szAuxIn, pIn+iCount, strlen((pIn+iCount)) > 8?8:strlen((pIn+iCount)));
        des3_encrypt(&ctx, szAuxIn, szAuxOut);
        memcpy(pOut+(iCount*2), this->bin2hex(szAuxOut), 16);
    }
}


/**********************************************************************************************/
void CTripleDES::crypt(unsigned char *pIn, unsigned char *pOut)
{
    unsigned char szAuxIn[8], szAuxOut[8];
    des3_context ctx;

    memset(&ctx, 0x00, sizeof(ctx));
    des3_set_3keys(&ctx, _szKey1, _szKey2, _szKey3);

    memcpy(szAuxIn, pIn, 8);

    des3_encrypt(&ctx, szAuxIn, szAuxOut);

    memcpy(pOut, szAuxOut, 8);
}

/**********************************************************************************************/
void CTripleDES::decrypt(unsigned char *pIn, unsigned char *pOut)
{
    unsigned char szAuxOut[8];
    des3_context ctx;

    memset(&ctx, 0x00, sizeof(ctx));
    des3_set_3keys(&ctx, _szKey1, _szKey2, _szKey3);

    des3_decrypt(&ctx, pIn, szAuxOut);

    memcpy(pOut, szAuxOut, 8);
}

/**********************************************************************************************/
void CTripleDES::setKey1(unsigned char *pKey)
{
    memcpy(_szKey1, pKey, 8);
}

/**********************************************************************************************/
void CTripleDES::setKey2(unsigned char *pKey)
{
    memcpy(_szKey2, pKey, 8);
}

/**********************************************************************************************/
void CTripleDES::setKey3(unsigned char *pKey)
{
    memcpy(_szKey3, pKey, 8);
}

/**********************************************************************************************/
unsigned char *CTripleDES::hex2bin(unsigned char* block)
{
    int i;
    static unsigned char output_buf_bin[17];
    memset(output_buf_bin, 0, 17);
    for(i=0; i<8; i++) {
                switch(((int)block[2*i])-48) {
                case 1: case 2: case 3: case 4: case 5:
                case 6: case 7: case 8: case 9: case 0:
                        output_buf_bin[i] = (((int)block[2*i]) - 48) << 4;
                        break;
                case 49: case 50: case 51: case 52: case 53: case 54:
                        output_buf_bin[i] = (((int)block[2*i]) - 87) << 4;
                        break;
                case 17: case 18: case 19: case 20: case 21: case 22:
                        output_buf_bin[i] = (((int)block[2*i]) - 55) << 4;
                }
                switch(((int)block[2*i+1])-48) {
                case 1: case 2: case 3: case 4: case 5:
                case 6: case 7: case 8: case 9: case 0:
                        output_buf_bin[i] += (((int)block[2*i+1]) - 48);
                        break;
                case 49: case 50: case 51: case 52: case 53: case 54:
                        output_buf_bin[i] += ((int)block[2*i+1]) - 87;
                        break;
                case 17: case 18: case 19: case 20: case 21: case 22:
                        output_buf_bin[i] += ((int)block[2*i+1]) - 55;
                }
        }

    return (unsigned char *)output_buf_bin;
}

/**********************************************************************************************/
unsigned char* CTripleDES::bin2hex(unsigned char* block)
{
    const char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    static unsigned char szOut[16 + 1];
    int i;

    for(i=0; i<8; i++) {
        szOut[2*i] = hex[(block[i] >> 4)];
        szOut[2*i+1] = hex[(block[i] & 0xf)];
    }
    szOut[16] = '\0';

    this->upper((char *)szOut);
    return szOut;
}

/**********************************************************************************************/
void CTripleDES::upper(char *pTok)
{ 
    for(; *pTok != '\0' ;pTok++)
        *pTok = toupper(*pTok);
}

