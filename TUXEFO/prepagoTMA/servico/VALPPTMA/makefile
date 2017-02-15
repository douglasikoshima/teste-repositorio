#
# $Id: makefile,v 1.1 2009/07/31 15:35:14 a5110702 Exp $
#
.SILENT:

include ../../defines

DIR=servico
SUBDIR=VALPPTMA
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

include $(HOME)/TUXEFO/modulos.env
LIBPP=$(TUXDIR_DES)/$(PREPAGOTMA_PTH)/lib

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o servicex
#
PCPP=VALPPTMA.pcpp
CPP=VALPPTMA.cpp
OBJ=lib/VALPPTMA.o
CUSTSO=PPTMA

all: $(SUBDIR)

INCAUX=-I$(HOME)/TUXEFO/prepagoTMA/negocio/PPTMA/include/

COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

$(SRCDIR)/VALPPTMA.cpp:
	$(PROC) include=$(TUXDIR_DES)/include/$(CUSTSO) $*.pcpp

lib/VALPPTMA.o:$(SRCDIR)/VALPPTMA.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/VALPPTMA.cpp -o $*.o  


#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)

install:
	if ! [ -d $(TUXDIR_DES)/$(PREPAGOTMA_PTH) ]; then mkdir $(TUXDIR_DES)/$(PREPAGOTMA_PTH) 2> /dev/null; fi
	if ! [ -d $(LIBPP) ]; then mkdir $(LIBPP) 2> /dev/null; fi
	@-cp $(LIBDIR)/lib$(SUBDIR).so $(LIBPP)

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..
