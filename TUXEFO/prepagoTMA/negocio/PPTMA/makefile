#
# $Id: makefile,v 1.1.2.1 2010/01/06 20:42:03 a5110702 Exp $
#
.SILENT:

include ../../defines

DIR=negocio
SUBDIR=PPTMA
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages
SO_AUX=PPTMA

include $(HOME)/TUXEFO/modulos.env
LIBPP=$(TUXDIR_DES)/$(PREPAGOTMA_PTH)/lib

PCPP=PermissaoLinhaPUPpcTMA.pcpp AreaRegistroBloqueadoPrepcTMA.pcpp UFpcTMA.pcpp PessoaLinhaPrepcTMA.pcpp \
	RegistraContatopcTMA.pcpp ParametropcTMA.pcpp TipoDocumentopcTMA.pcpp TipoEnderecoCobrancapcTMA.pcpp \
	PessoaContapcTMA.pcpp PessoaSegmentacaoHistoricopcTMA.pcpp PessoaValorPossivelpcTMA.pcpp SistemaOrigempcTMA.pcpp \
	LinhaContapcTMA.pcpp PessoaComunicacaopcTMA.pcpp PessoaLinhapcTMA.pcpp PessoapcTMA.pcpp PessoaFisicapcTMA.pcpp \
	PessoaJuridicapcTMA.pcpp PessoaEnderecopcTMA.pcpp DocumentopcTMA.pcpp PessoaDeParapcTMA.pcpp FilaSetClientInfopcTMA.pcpp \
	ContaEnderecopcTMA.pcpp PessoaSegmentacaopcTMA.pcpp PessoaDocumentopcTMA.pcpp PessoaLinhaHistoricopcTMA.pcpp \
	FilaPreEnvioSetCliInfopcTMA.pcpp

CPP=PPExceptionTMA.cpp ToolsTMA.cpp PermissaoLinhaPUPTMA.cpp AreaRegistroBloqueadoPreTMA.cpp UFTMA.cpp PessoaLinhaPreTMA.cpp \
	RegistraContatoTMA.cpp ParametroTMA.cpp TipoDocumentoTMA.cpp TipoEnderecoCobrancaTMA.cpp PessoaContaTMA.cpp \
	PessoaSegmentacaoHistoricoTMA.cpp PessoaValorPossivelTMA.cpp SistemaOrigemTMA.cpp LinhaContaTMA.cpp \
	PessoaComunicacaoTMA.cpp PessoaLinhaTMA.cpp PessoaTMA.cpp PessoaFisicaTMA.cpp PessoaJuridicaTMA.cpp \
	PessoaEnderecoTMA.cpp DocumentoTMA.cpp PessoaDeParaTMA.cpp FilaSetClientInfoTMA.cpp ContaEnderecoTMA.cpp \
	PessoaSegmentacaoTMA.cpp PessoaDocumentoTMA.cpp PessoaLinhaHistoricoTMA.cpp

OBJ=lib/PPExceptionTMA.o lib/ToolsTMA.o lib/PermissaoLinhaPUPpcTMA.o lib/PermissaoLinhaPUPTMA.o \
	lib/AreaRegistroBloqueadoPrepcTMA.o lib/AreaRegistroBloqueadoPreTMA.o lib/UFpcTMA.o lib/UFTMA.o \
	lib/PessoaLinhaPrepcTMA.o lib/PessoaLinhaPreTMA.o lib/RegistraContatopcTMA.o lib/RegistraContatoTMA.o \
	lib/ParametropcTMA.o lib/ParametroTMA.o lib/TipoDocumentopcTMA.o lib/TipoDocumentoTMA.o \
	lib/TipoEnderecoCobrancapcTMA.o lib/TipoEnderecoCobrancaTMA.o lib/PessoaContapcTMA.o \
	lib/PessoaContaTMA.o lib/PessoaLinhaHistoricopcTMA.o lib/PessoaLinhaHistoricoTMA.o \
	lib/PessoaSegmentacaoHistoricopcTMA.o lib/PessoaSegmentacaoHistoricoTMA.o lib/PessoaValorPossivelpcTMA.o \
	lib/PessoaValorPossivelTMA.o lib/SistemaOrigempcTMA.o lib/SistemaOrigemTMA.o lib/LinhaContapcTMA.o \
	lib/LinhaContaTMA.o lib/PessoaComunicacaopcTMA.o lib/PessoaComunicacaoTMA.o lib/PessoaLinhapcTMA.o \
	lib/PessoaLinhaTMA.o lib/PessoapcTMA.o lib/PessoaTMA.o lib/PessoaFisicapcTMA.o lib/PessoaFisicaTMA.o \
	lib/PessoaJuridicapcTMA.o lib/PessoaJuridicaTMA.o lib/PessoaEnderecopcTMA.o lib/PessoaEnderecoTMA.o \
	lib/DocumentopcTMA.o lib/DocumentoTMA.o lib/PessoaDeParapcTMA.o lib/PessoaDeParaTMA.o \
	lib/FilaSetClientInfopcTMA.o lib/FilaSetClientInfoTMA.o lib/ContaEnderecopcTMA.o lib/ContaEnderecoTMA.o \
	lib/PessoaSegmentacaopcTMA.o lib/PessoaSegmentacaoTMA.o lib/PessoaDocumentopcTMA.o lib/PessoaDocumentoTMA.o \
	lib/PessoaLinhaHistoricopcTMA.o lib/PessoaLinhaHistoricoTMA.o lib/FilaPreEnvioSetCliInfopcTMA.o

CUSTSO=

all: $(SUBDIR)


$(SRCDIR)/PermissaoLinhaPUPpcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AreaRegistroBloqueadoPrepcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinhaPrepcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/UFpcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/RegistraContatopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ParametropcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoDocumentopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoEnderecoCobrancapcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaContapcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaSegmentacaoHistoricopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaValorPossivelpcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/SistemaOrigempcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LinhaContapcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaComunicacaopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoapcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinhapcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaFisicapcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaJuridicapcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaEnderecopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/DocumentopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaDeParapcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/FilaSetClientInfopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ContaEnderecopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/FilaPreEnvioSetCliInfopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaSegmentacaopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaDocumentopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinhaHistoricopcTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PPExceptionTMA.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ToolsTMA.cpp:
	$(PROC) $*.pcpp
	

INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
                         for(i=1;i<=flds;i++)              \
                          printf("$(DESINCDIRS)/%s ",v[i]);\
                         next}'`
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)


lib/PPExceptionTMA.o:$(SRCDIR)/PPExceptionTMA.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PPExceptionTMA.cpp -o $*.o
lib/ToolsTMA.o:$(SRCDIR)/ToolsTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ToolsTMA.cpp -o $*.o
lib/LinhaContaTMA.o:$(SRCDIR)/LinhaContaTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LinhaContaTMA.cpp -o $*.o
lib/LinhaContapcTMA.o:$(SRCDIR)/LinhaContapcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LinhaContapcTMA.cpp -o $*.o
lib/PessoaTMA.o:$(SRCDIR)/PessoaTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaTMA.cpp -o $*.o
lib/PessoapcTMA.o:$(SRCDIR)/PessoapcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoapcTMA.cpp -o $*.o
lib/PessoaFisicaTMA.o:$(SRCDIR)/PessoaFisicaTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaFisicaTMA.cpp -o $*.o
lib/PessoaFisicapcTMA.o:$(SRCDIR)/PessoaFisicapcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaFisicapcTMA.cpp -o $*.o
lib/PessoaJuridicaTMA.o:$(SRCDIR)/PessoaJuridicaTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaJuridicaTMA.cpp -o $*.o
lib/PessoaJuridicapcTMA.o:$(SRCDIR)/PessoaJuridicapcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaJuridicapcTMA.cpp -o $*.o
lib/PessoaEnderecoTMA.o:$(SRCDIR)/PessoaEnderecoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaEnderecoTMA.cpp -o $*.o
lib/PessoaEnderecopcTMA.o:$(SRCDIR)/PessoaEnderecopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaEnderecopcTMA.cpp -o $*.o
lib/DocumentoTMA.o:$(SRCDIR)/DocumentoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/DocumentoTMA.cpp -o $*.o
lib/DocumentopcTMA.o:$(SRCDIR)/DocumentopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/DocumentopcTMA.cpp -o $*.o
lib/PessoaDeParaTMA.o:$(SRCDIR)/PessoaDeParaTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaDeParaTMA.cpp -o $*.o
lib/PessoaDeParapcTMA.o:$(SRCDIR)/PessoaDeParapcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaDeParapcTMA.cpp -o $*.o
lib/FilaSetClientInfoTMA.o:$(SRCDIR)/FilaSetClientInfoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/FilaSetClientInfoTMA.cpp -o $*.o
lib/FilaSetClientInfopcTMA.o:$(SRCDIR)/FilaSetClientInfopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/FilaSetClientInfopcTMA.cpp -o $*.o
lib/ContaEnderecoTMA.o:$(SRCDIR)/ContaEnderecoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ContaEnderecoTMA.cpp -o $*.o
lib/ContaEnderecopcTMA.o:$(SRCDIR)/ContaEnderecopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ContaEnderecopcTMA.cpp -o $*.o
lib/FilaPreEnvioSetCliInfopcTMA.o:$(SRCDIR)/FilaPreEnvioSetCliInfopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/FilaPreEnvioSetCliInfopcTMA.cpp -o $*.o
lib/PessoaSegmentacaoTMA.o:$(SRCDIR)/PessoaSegmentacaoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaSegmentacaoTMA.cpp -o $*.o
lib/PessoaSegmentacaopcTMA.o:$(SRCDIR)/PessoaSegmentacaopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaSegmentacaopcTMA.cpp -o $*.o
lib/PessoaDocumentoTMA.o:$(SRCDIR)/PessoaDocumentoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaDocumentoTMA.cpp -o $*.o
lib/PessoaDocumentopcTMA.o:$(SRCDIR)/PessoaDocumentopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaDocumentopcTMA.cpp -o $*.o
lib/PessoaLinhaHistoricoTMA.o:$(SRCDIR)/PessoaLinhaHistoricoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaHistoricoTMA.cpp -o $*.o
lib/PessoaLinhaHistoricopcTMA.o:$(SRCDIR)/PessoaLinhaHistoricopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaHistoricopcTMA.cpp -o $*.o
lib/PessoaLinhaTMA.o:$(SRCDIR)/PessoaLinhaTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaTMA.cpp -o $*.o
lib/PessoaLinhapcTMA.o:$(SRCDIR)/PessoaLinhapcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhapcTMA.cpp -o $*.o
lib/PessoaComunicacaoTMA.o:$(SRCDIR)/PessoaComunicacaoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaComunicacaoTMA.cpp -o $*.o
lib/PessoaComunicacaopcTMA.o:$(SRCDIR)/PessoaComunicacaopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaComunicacaopcTMA.cpp -o $*.o
lib/SistemaOrigemTMA.o:$(SRCDIR)/SistemaOrigemTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/SistemaOrigemTMA.cpp -o $*.o
lib/SistemaOrigempcTMA.o:$(SRCDIR)/SistemaOrigempcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/SistemaOrigempcTMA.cpp -o $*.o
lib/PessoaValorPossivelTMA.o:$(SRCDIR)/PessoaValorPossivelTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaValorPossivelTMA.cpp -o $*.o
lib/PessoaValorPossivelpcTMA.o:$(SRCDIR)/PessoaValorPossivelpcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaValorPossivelpcTMA.cpp -o $*.o
lib/PessoaSegmentacaoHistoricoTMA.o:$(SRCDIR)/PessoaSegmentacaoHistoricoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaSegmentacaoHistoricoTMA.cpp -o $*.o
lib/PessoaSegmentacaoHistoricopcTMA.o:$(SRCDIR)/PessoaSegmentacaoHistoricopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaSegmentacaoHistoricopcTMA.cpp -o $*.o
lib/PessoaContaTMA.o:$(SRCDIR)/PessoaContaTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaContaTMA.cpp -o $*.o
lib/PessoaContapcTMA.o:$(SRCDIR)/PessoaContapcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaContapcTMA.cpp -o $*.o
lib/TipoEnderecoCobrancaTMA.o:$(SRCDIR)/TipoEnderecoCobrancaTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoEnderecoCobrancaTMA.cpp -o $*.o
lib/TipoEnderecoCobrancapcTMA.o:$(SRCDIR)/TipoEnderecoCobrancapcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoEnderecoCobrancapcTMA.cpp -o $*.o
lib/TipoDocumentoTMA.o:$(SRCDIR)/TipoDocumentoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoDocumentoTMA.cpp -o $*.o
lib/TipoDocumentopcTMA.o:$(SRCDIR)/TipoDocumentopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoDocumentopcTMA.cpp -o $*.o
lib/ParametroTMA.o:$(SRCDIR)/ParametroTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ParametroTMA.cpp -o $*.o
lib/ParametropcTMA.o:$(SRCDIR)/ParametropcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ParametropcTMA.cpp -o $*.o
lib/RegistraContatoTMA.o:$(SRCDIR)/RegistraContatoTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/RegistraContatoTMA.cpp -o $*.o
lib/RegistraContatopcTMA.o:$(SRCDIR)/RegistraContatopcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/RegistraContatopcTMA.cpp -o $*.o
lib/UFTMA.o:$(SRCDIR)/UFTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/UFTMA.cpp -o $*.o
lib/UFpcTMA.o:$(SRCDIR)/UFpcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/UFpcTMA.cpp -o $*.o
lib/PessoaLinhaPreTMA.o:$(SRCDIR)/PessoaLinhaPreTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaPreTMA.cpp -o $*.o
lib/PessoaLinhaPrepcTMA.o:$(SRCDIR)/PessoaLinhaPrepcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaPrepcTMA.cpp -o $*.o
lib/AreaRegistroBloqueadoPreTMA.o:$(SRCDIR)/AreaRegistroBloqueadoPreTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AreaRegistroBloqueadoPreTMA.cpp -o $*.o
lib/AreaRegistroBloqueadoPrepcTMA.o:$(SRCDIR)/AreaRegistroBloqueadoPrepcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AreaRegistroBloqueadoPrepcTMA.cpp -o $*.o
lib/PermissaoLinhaPUPTMA.o:$(SRCDIR)/PermissaoLinhaPUPTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PermissaoLinhaPUPTMA.cpp -o $*.o
lib/PermissaoLinhaPUPpcTMA.o:$(SRCDIR)/PermissaoLinhaPUPpcTMA.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PermissaoLinhaPUPpcTMA.cpp -o $*.o


#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SO_AUX).so $(OBJ) $(FWLIB) $(PLIBPATH)

install:
	if ! [ -d $(TUXDIR_DES)/$(PREPAGOTMA_PTH) ]; then mkdir $(TUXDIR_DES)/$(PREPAGOTMA_PTH) 2> /dev/null; fi
	if ! [ -d $(LIBPP) ]; then mkdir $(LIBPP) 2> /dev/null; fi
	cp $(LIBDIR)/lib$(SO_AUX).so $(LIBPP)

clean:
	rm -f $(LIBDIR)/lib$(SO_AUX).so*
	rm -f $(OBJ)
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..
