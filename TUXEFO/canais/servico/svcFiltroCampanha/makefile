include ../../defines

DIR=servico
SUBDIR=svcFiltroCampanha
SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages


#CPP=lista de fontes a ser compilado
SRC=$(CPP)

#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=lib/svcFiltroCampanha.o

all: $(LIBDIR)/lib$(SUBDIR).so


# Compile
lib/svcFiltroCampanha.o:$(SRCDIR)/svcFiltroCampanha.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages -I$(TUXDIR_DES)/include/PlugInBE $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/svcFiltroCampanha.cpp -o $*.o

# Link
$(LIBDIR)/lib$(SUBDIR).so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR).so $(OBJ) -lxml2
	if ! [ -d $(TUXDIR_DES)/Canais/lib ]; then mkdir -p $(TUXDIR_DES)/Canais/lib; fi
	rm -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/Canais/lib


install:
	if ! [ -d $(TUXDIR_DES)/Canais/lib ]; then mkdir -p $(TUXDIR_DES)/Canais/lib; fi
	rm -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/Canais/lib


clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)
