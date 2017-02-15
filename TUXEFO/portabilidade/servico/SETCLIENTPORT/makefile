#
# $Id: makefile,v 1.1 2009/07/31 15:34:30 a5110702 Exp $
#
.SILENT:

include ../../defines

DIR=servico
SUBDIR=SETCLIENTPORT
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

include $(HOME)/TUXEFO/modulos.env
LIBPP=$(TUXDIR_DES)/$(PORTABILIDADE_PTH)/lib

PCPP=
CPP=SETCLIENTPORT.cpp
OBJ=lib/SETCLIENTPORT.o
CUSTSO=PORTABILIDADE

all: $(SUBDIR)

INCAUX=-I$(HOME)/TUXEFO/portabilidade/negocio/PORTABILIDADE/include/

COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

     
#$(SRCDIR)/SETCLIENTPORT.cpp:
#	$(PROC) include=$(INCAUX) $*.pcpp

lib/SETCLIENTPORT.o:$(SRCDIR)/SETCLIENTPORT.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/SETCLIENTPORT.cpp -o $*.o  


#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)

install:
	if ! [ -d $(TUXDIR_DES)/$(PORTABILIDADE_PTH) ]; then mkdir $(TUXDIR_DES)/$(PORTABILIDADE_PTH) 2> /dev/null; fi
	if ! [ -d $(LIBPP) ]; then mkdir $(LIBPP) 2> /dev/null; fi
	@-cp $(LIBDIR)/lib$(SUBDIR).so $(LIBPP)

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..
