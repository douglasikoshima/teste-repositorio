include ../../defines

DIR=negocio
SUBDIR=UtilCanais
SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)
INCPROC=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#CPP=lista de fontes a ser compilado
SRC=$(CPP)

#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=lib/UtilCanais.o

all: $(LIBDIR)/lib$(SUBDIR).so

# Compile
lib/UtilCanais.o:$(SRCDIR)/UtilCanais.cpp $(INCDIR)/UtilCanais.hpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/UtilCanais.cpp -o $*.o

# Link
$(LIBDIR)/lib$(SUBDIR).so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR).so $(OBJ) -lxml2
	if ! [ -d $(TUXDIR_DES)/Canais/lib ]; then mkdir -p $(TUXDIR_DES)/Canais/lib; fi
	if [ -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so ]; then rm -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/Canais/lib	


install:
	if ! [ -d $(TUXDIR_DES)/Canais/lib ]; then mkdir -p $(TUXDIR_DES)/Canais/lib; fi
	if [ -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so ]; then rm -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/Canais/lib	

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)

install_h:
	if ! [ -d $(TUXDIR_DES)/include/$(SUBDIR) ]; then mkdir $(TUXDIR_DES)/include/$(SUBDIR); fi
	if [ -f  $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR); fi
