#
# $Id: makefile,v 1.1.8.1 2015/09/21 20:14:54 a5110705 Exp $
#
.SILENT:

include ../../defines

DIR=servico
SUBDIR=SINCVIVONETPP
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

include $(HOME)/TUXEFO/modulos.env
LIBPP=$(TUXDIR_DES)/$(PREPAGO_PTH)/lib

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o servicex
#
PCPP=
CPP=SINCVIVONETPP.cpp
OBJ=lib/SINCVIVONETPP.o
CUSTSO=ppbasico

all: $(SUBDIR)

INCAUX=-I$(HOME)/TUXEFO/prepago/negocio/ppbasico/include/

COMPFLAGS= -Iinclude -I$(INCDIR) $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

lib/SINCVIVONETPP.o:$(SRCDIR)/SINCVIVONETPP.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/SINCVIVONETPP.cpp -o $*.o


#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)

install:
	if ! [ -d $(TUXDIR_DES)/$(PREPAGO_PTH) ]; then mkdir $(TUXDIR_DES)/$(PREPAGO_PTH) 2> /dev/null; fi
	if ! [ -d $(LIBPP) ]; then mkdir $(LIBPP) 2> /dev/null; fi
	@-cp $(LIBDIR)/lib$(SUBDIR).so $(LIBPP)

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..
