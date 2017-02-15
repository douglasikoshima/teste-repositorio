include ../../defines

DIR=servico
SUBDIR=LstSessao
SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages


#CPP=lista de fontes a ser compilado
SRC=$(CPP)

#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=lib/LstSessao.o

all: $(LIBDIR)/lib$(SUBDIR).so


# Compile
lib/LstSessao.o:$(SRCDIR)/LstSessao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/LstSessao.cpp -o $*.o

# Link
$(LIBDIR)/lib$(SUBDIR).so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR).so $(OBJ) -lxml2

install:
	if [ -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so ]; then rm -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)
