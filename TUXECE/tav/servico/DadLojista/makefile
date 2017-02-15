include ../../defines

DIR=servico
SUBDIR=DadLojista
SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages


#CPP=lista de fontes a ser compilado
SRC=$(CPP)

#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=lib/DadLojista.o

all: $(LIBDIR)/lib$(SUBDIR).so


# Compile
lib/DadLojista.o:$(SRCDIR)/DadLojista.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/DadLojista.cpp -o $*.o

# Link
$(LIBDIR)/lib$(SUBDIR).so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR).so $(OBJ) -lxml2

install:
	rm -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)
