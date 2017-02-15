#
# $Id: makefile,v 1.1 2009/07/31 15:34:47 a5110702 Exp $
#
.SILENT:

include ../../defines

DIR=servico
SUBDIR=CARREGATIPORT
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

LIBCLI=$(TUXDIR_DES)/$(CLIENTE_PTH)/lib

PCPP=main.pcpp
CPP= main.cpp
OBJ= lib/main.o
CUSTSO=clienteCmm

all: $(SUBDIR)

INCAUX=-I$(HOME)/TUXEFO/cliente/negocio/clienteCmm/include/

COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

     
$(SRCDIR)/main.cpp:
	$(PROC) include=$(TUXDIR_DES)/include/$(CUSTSO) $*.pcpp

lib/main.o:$(SRCDIR)/main.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/main.cpp -o $*.o  


$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)

install:
	if ! [ -d $(TUXDIR_DES)/$(CLIENTE_PTH) ]; then mkdir $(TUXDIR_DES)/$(CLIENTE_PTH) 2> /dev/null; fi
	if ! [ -d $(LIBCLI) ]; then mkdir $(LIBCLI) 2> /dev/null; fi
	@-cp -f $(LIBDIR)/lib$(SUBDIR).so $(LIBCLI)

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -fr $(LIBDIR)
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..
