//* Review:
//*
//* Task force to seek potential memory leaks and exception errors - March,2005 - Cassio

#ifndef _cplusplus
extern "C" {
#endif

/*
 * Grid 2003 class: scroll to the bottom for main program.
 *
 * See http://www.pdc.kth.se/~mulmo/grid-2003/ for more information.
 *
 * /Olle
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
//#include <sys/time.h>
#include <time.h>
#include "../include/DES.h"
 
static void buildtables()
{
        register int i, j;
        register word32 v;
        word32 wC_K[64], wD_K[64];
        word32 hKS_C[28], lKS_D[28];
        int Smap[64];
        word32 wP[32];
 
#define       ZERO(array)     memset((char *)(array), '\0', sizeof(array))
 
        /* Invert permuted-choice-1 (key => C,D) */
 
        ZERO(wC_K);
        ZERO(wD_K);
        v = 1;
        for(j = 28; --j >= 0; ) {
                wC_K[ bK_C[j] - 1 ] = wD_K[ bK_D[j] - 1 ] = v;
                v += v;         /* (i.e. v <<= 1) */
        }
 
        for(i = 0; i < 64; i++) {
            int t = 8 >> (i & 3);
            for(j = 0; j < 16; j++) {
                if(j & t) {
                    wC_K4[i >> 3][j] |= wC_K[i];
                    wD_K4[i >> 3][j] |= wD_K[i];
                    if(j < 8) {
                        wC_K3[i >> 3][j] |= wC_K[i + 3];
                        wD_K3[i >> 3][j] |= wD_K[i + 3];
                    }
                }
            }
            /* Generate the sequence 0,1,2,3, 8,9,10,11, ..., 56,57,58,59. */
            if(t == 1) i += 4;
        }
 
        /* Invert permuted-choice-2 */
 
        ZERO(hKS_C);
        ZERO(lKS_D);
        v = 1;
        for(i = 24; (i -= 6) >= 0; ) {
            j = i+5;
            do {
                hKS_C[ bCD_KS[j] - 1 ] = lKS_D[ bCD_KS[j+24] - 28 - 1 ] = v;
                v += v;         /* Like v <<= 1 but may be faster */
            } while(--j >= i);
            v <<= 2;            /* Keep byte aligned */
        }
 
        for(i = 0; i < 28; i++) {
            v = 8 >> (i & 3);
            for(j = 0; j < 16; j++) {
                if(j & v) {
                    hKS_C4[i >> 2][j] |= hKS_C[i];
                    lKS_D4[i >> 2][j] |= lKS_D[i];
                }
            }
        }
 
        /* Initial permutation */
 
        for(i = 0; i <= 0x55; i++) {
            v = 0;
            if(i & 64) v =  (word32) 1 << 24;
            if(i & 16) v |= (word32) 1 << 16;
            if(i & 4)  v |= (word32) 1 << 8;
            if(i & 1)  v |= 1;
            wL_I8[i] = v;
        }
 
        /* Final permutation */
 
        for(i = 0; i < 16; i++) {
            v = 0;
            if(i & 1) v = (word32) 1 << 24;
            if(i & 2) v |= (word32) 1 << 16;
            if(i & 4) v |= (word32) 1 << 8;
            if(i & 8) v |= (word32) 1;
            wO_L4[i] = v;
        }
 
        /* Funny bit rearrangement on second index into S tables */
 
        for(i = 0; i < 64; i++) {
                Smap[i] = (i & 0x20) | (i & 1) << 4 | (i & 0x1e) >> 1;
        }
 
        /* Invert permutation P into mask indexed by R bit number */
 
        v = 1;
        for(i = 32; --i >= 0; ) {
                wP[ P[i] - 1 ] = v;
                v += v;
        }
 
        /* Build bit-mask versions of S tables, indexed in natural bit order */
 
        for(i = 0; i < 8; i++) {
            for(j = 0; j < 64; j++) {
                int k, t;
 
                t = S[i][ Smap[j] ];
                for(k = 0; k < 4; k++) {
                    if(t & 8)
                        wPS[i][j] |= wP[4*i + k];
                    t += t;
                }
            }
        }
}
 
void fsetkey(char key[8], keysched* ks)
{
        register int i;
        register word32 C, D;
        static int built = 0;
 
        if(!built) {
                buildtables();
                built = 1;
        }
 
        C = D = 0;
        for(i = 0; i < 8; i++) {
                register int v;
 
                v = key[i] >> 1;        /* Discard "parity" bit */
                C |= wC_K4[i][(v>>3) & 15] | wC_K3[i][v & 7];
                D |= wD_K4[i][(v>>3) & 15] | wD_K3[i][v & 7];
        }
 
        /*
         * C and D now hold the suitably right-justified
         * 28 permuted key bits each.
         */
        for(i = 0; i < 16; i++) {
                register word32 *ap;
 
#  define choice2(x, v)  ( \
                    ap = &(x)[0][0], \
                    ap[16*6 + (v&15)] | \
                    ap[16*5 + ((v>>4)&15)]  | ap[16*4 + ((v>>8)&15)]  | \
                    ap[16*3 + ((v>>12)&15)] | ap[16*2 + ((v>>16)&15)] | \
                    ap[16*1 + ((v>>20)&15)] | ap[16*0 + ((v>>24)&15)] )
 
 
                /* 28-bit left circular shift */
                C <<= preshift[i];
                C = ((C >> 28) & 3) | (C & (((word32)1<<28) - 1));
                ks->KS[i].h = choice2(hKS_C4, C);
 
                D <<= preshift[i];
                D = ((D >> 28) & 3) | (D & (((word32)1<<28) - 1));
                ks->KS[i].l = choice2(lKS_D4, D);
        }
}
 
 
void fencrypt(char *block, int decrypt, keysched *ks)
{
        int i;
        register word32 L, R;
        register struct keysched::keystage *ksp;
        register word32 *ap;
 
        /* Initial permutation */
 
        L = R = 0;
        i = 7;
        ap = wL_I8;
        do {
                register int v;
 
                v = block[i];   /* Could optimize according to ENDIAN */
                L = ap[v & 0x55] | (L << 1);
                R = ap[(v >> 1) & 0x55] | (R << 1);
        } while(--i >= 0);
 
        if(decrypt) {
                ksp = &ks->KS[15];
        } else {
                ksp = &ks->KS[0];
        }
 
#  define PS(i,j)       ap[64*(i) + (j)]
        ap = &wPS[0][0];
 
        i = 16;
        do {
                register word32 k, tR;
 
                tR = (R >> 15) | (R << 17);
 
                k = ksp->h;
                L ^= PS(0, ((tR >> 12) ^ (k >> 24)) & 63)
                   | PS(1, ((tR >> 8) ^ (k >> 16)) & 63)
                   | PS(2, ((tR >> 4) ^ (k >> 8)) & 63)
                   | PS(3, (tR ^ k) & 63);
 
                k = ksp->l;
                L ^= PS(4, ((R >> 11) ^ (k >> 24)) & 63)
                   | PS(5, ((R >> 7) ^ (k >> 16)) & 63)
                   | PS(6, ((R >> 3) ^ (k >> 8)) & 63)
                   | PS(7, ((tR >> 16) ^ k) & 63);
 
                tR = L;
                L = R;
                R = tR;
 
 
                if(decrypt)
                        ksp--;
                else
                        ksp++;
        } while(--i > 0);
        {
                register word32 t;
 
# define FP(k)  (ap[ (L >> (k)) & 15 ] << 1 | ap[ (R >> (k)) & 15 ])
 
                ap = wO_L4;
 
                t = FP(0) | (FP(8) | (FP(16) | (FP(24) << 2)) << 2) << 2;
                R = FP(4) | (FP(12) | (FP(20) | (FP(28) << 2)) << 2) << 2;
                L = t;
        }
        {
                register word32 t;
                register char *bp;
 
                bp = &block[7];
                t = R;
                *bp   = (char)(t & 255);
                *--bp = (char)((t >>= 8) & 255);
                *--bp = (char)((t >>= 8) & 255);
                *--bp = (char)((t >> 8) & 255);
                t = L;
                *--bp = (char)(t & 255);
                *--bp = (char)((t >>= 8) & 255);
                *--bp = (char)((t >>= 8) & 255);
                *--bp = (char)((t >> 8) & 255);
        }
}

char* bin2hex(unsigned char* block)
{
    int i;
    for(i=0; i<8; i++) {
        output_buf[2*i] = hex[(block[i] >> 4)];
        output_buf[2*i+1] = hex[(block[i] & 0xf)];
    }
    output_buf[16] = '\0';
    return output_buf;
}

unsigned char* hex2bin(char* block)
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

    return (unsigned char*) output_buf_bin;
}

#ifndef _cplusplus
}
#endif
