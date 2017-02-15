#
# $Id: makefile,v 1.1.4.1 2009/12/23 17:58:56 a5114878 Exp $
#
.SILENT:

include ../../defines

DIR=servico
SUBDIR=PPPROCCAD
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
CPP=PPPROCCAD.cpp
OBJ=lib/PPPROCCAD.o
CUSTSO=ppbasico

all: $(SUBDIR)

INCAUX=-I$(HOME)/TUXEFO/prepago/negocio/ppbasico/include/

COMPFLAGS= -Iinclude -I$(INCDIR) $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

lib/PPPROCCAD.o:$(SRCDIR)/PPPROCCAD.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PPPROCCAD.cpp -o $*.o


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
