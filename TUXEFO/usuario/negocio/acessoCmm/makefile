#
# $Id: makefile,v 1.1.2.1 2009/10/26 23:41:29 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env

DIR=negocio
SUBDIR=acessoCmm
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
MODULODIR=$(TUXDIR_DES)/$(USUARIO_PTH)
MODULOLIB=$(TUXDIR_DES)/$(USUARIO_PTH)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o servicex
#
PCPP=CGrupoSkill.pcpp CGrupoUsuario.pcpp CAcs.pcpp CAreReg.pcpp CPesDoc.pcpp CDoc.pcpp CUFOpe.pcpp CCrg.pcpp \
    CGpt.pcpp CGrp.pcpp CImn.pcpp CPais.pcpp CPgn.pcpp CPrf.pcpp CSis.pcpp CSisOrg.pcpp CSrv.pcpp \
    CSts.pcpp CSub.pcpp CTipCrt.pcpp CTipDoc.pcpp CTipPes.pcpp CUF.pcpp CUni.pcpp CUsr.pcpp CTipoGrupo.pcpp \
    CContatoFolhaUsuario.pcpp CContatoFolhaUsuarioSkill.pcpp CSiteConsultorAtd.pcpp \
    CFornecedorConsultorAtd.pcpp CPerfilConsultorAtd.pcpp
CPP=CAcsItr.cpp CAreRegItr.cpp CPesDocItr.cpp CDocItr.cpp CUFOpeItr.cpp CCrgItr.cpp CGrpItr.cpp CImnItr.cpp \
    CPaisItr.cpp CPgnItr.cpp CSisOrgItr.cpp CStsItr.cpp CTipCrtItr.cpp CTipDocItr.cpp CTipPesItr.cpp \
    CUFItr.cpp CUsrItr.cpp CSubItr.cpp CSafePointer.cpp CTipoGrupoItr.cpp CContatoFolhaUsuarioSkillItr.cpp \
    CContatoFolhaUsuarioItr.cpp CSiteConsultorAtdItr.cpp CFornecedorConsultorAtdItr.cpp CPerfilConsultorAtdItr.cpp
OBJ=lib/CAcsItr.o lib/CAreRegItr.o lib/CPesDocItr.o lib/CDocItr.o lib/CUFOpeItr.o lib/CCrgItr.o \
    lib/CGrpItr.o lib/CImnItr.o lib/CPaisItr.o lib/CPgnItr.o lib/CSisOrgItr.o lib/CStsItr.o \
    lib/CTipCrtItr.o lib/CTipDocItr.o lib/CTipPesItr.o lib/CUFItr.o lib/CUsrItr.o lib/CAcs.o \
    lib/CGrupoSkill.o lib/CGrupoUsuario.o lib/CAreReg.o lib/CPesDoc.o lib/CDoc.o lib/CUFOpe.o \
    lib/CCrg.o lib/CGpt.o lib/CGrp.o lib/CImn.o lib/CPais.o lib/CPgn.o lib/CPrf.o lib/CSis.o \
    lib/CSisOrg.o lib/CSrv.o lib/CSts.o lib/CSubItr.o lib/CSub.o lib/CTipCrt.o lib/CTipDoc.o \
    lib/CTipPes.o lib/CUF.o lib/CUni.o lib/CUsr.o lib/CSafePointer.o lib/CTipoGrupo.o \
    lib/CTipoGrupoItr.o lib/CContatoFolhaUsuarioItr.o lib/CContatoFolhaUsuarioSkillItr.o \
    lib/CContatoFolhaUsuario.o lib/CContatoFolhaUsuarioSkill.o lib/CSiteConsultorAtdItr.o \
    lib/CFornecedorConsultorAtdItr.o lib/CPerfilConsultorAtdItr.o \
    lib/CSiteConsultorAtd.o lib/CFornecedorConsultorAtd.o lib/CPerfilConsultorAtd.o
CUSTSO=

all: $(SUBDIR)

#
#  Pre Compile
#  $(SRCDIR)/cpp_tpl.cpp:
#	 $(PROC) $*.pcpp
#
$(SRCDIR)/CContatoFolhaUsuarioItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoFolhaUsuario.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoFolhaUsuarioSkillItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CContatoFolhaUsuarioSkill.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPerfilConsultorAtdItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPerfilConsultorAtd.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFornecedorConsultorAtdItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CFornecedorConsultorAtd.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSiteConsultorAtdItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSiteConsultorAtd.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSafePointer.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CGrpItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAcsItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAreRegItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPesDocItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CDocItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUFOpeItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCrgItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CImnItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPaisItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPgnItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSisOrgItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CStsItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipCrtItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipDocItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipPesItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUFItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUsrItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAcs.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CGrupoSkill.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CGrupoUsuario.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CAreReg.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPesDoc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CDoc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUFOpe.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCrg.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CGpt.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CGrp.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CImn.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPais.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPgn.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPrf.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSis.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSisOrg.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSrv.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSts.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSubItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSub.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipCrt.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipDoc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipPes.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUF.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUni.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUsr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoGrupo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoGrupoItr.cpp:
	$(PROC) $*.pcpp


INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
                         for(i=1;i<=flds;i++)              \
                          printf("$(DESINCDIRS)/%s ",v[i]);\
                         next}'`
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

#
#  FML
#  messages/msg_tpl.fml.h:messages/msg_tpl.fml
#	cd messages; MKFLDHDR messages/msg_tpl.fml; cd ..
#

#
#  Compile
#  lib/obj_tpl.o:$(SRCDIR)/cpp.cpp makefile
#        $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#
lib/CAcsItr.o:$(SRCDIR)/CAcsItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAcsItr.cpp -o $*.o
lib/CSafePointer.o:$(SRCDIR)/CSafePointer.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSafePointer.cpp -o $*.o
lib/CAreRegItr.o:$(SRCDIR)/CAreRegItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAreRegItr.cpp -o $*.o
lib/CGrpItr.o:$(SRCDIR)/CGrpItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CGrpItr.cpp -o $*.o
lib/CPesDocItr.o:$(SRCDIR)/CPesDocItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPesDocItr.cpp -o $*.o
lib/CDocItr.o:$(SRCDIR)/CDocItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CDocItr.cpp -o $*.o
lib/CUFOpeItr.o:$(SRCDIR)/CUFOpeItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUFOpeItr.cpp -o $*.o
lib/CCrgItr.o:$(SRCDIR)/CCrgItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCrgItr.cpp -o $*.o
lib/CImnItr.o:$(SRCDIR)/CImnItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CImnItr.cpp -o $*.o
lib/CPaisItr.o:$(SRCDIR)/CPaisItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPaisItr.cpp -o $*.o
lib/CPgnItr.o:$(SRCDIR)/CPgnItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPgnItr.cpp -o $*.o
lib/CSisOrgItr.o:$(SRCDIR)/CSisOrgItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSisOrgItr.cpp -o $*.o
lib/CStsItr.o:$(SRCDIR)/CStsItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CStsItr.cpp -o $*.o
lib/CTipCrtItr.o:$(SRCDIR)/CTipCrtItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipCrtItr.cpp -o $*.o
lib/CTipDocItr.o:$(SRCDIR)/CTipDocItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipDocItr.cpp -o $*.o
lib/CTipPesItr.o:$(SRCDIR)/CTipPesItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipPesItr.cpp -o $*.o
lib/CUFItr.o:$(SRCDIR)/CUFItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUFItr.cpp -o $*.o
lib/CUsrItr.o:$(SRCDIR)/CUsrItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUsrItr.cpp -o $*.o
lib/CAcs.o:$(SRCDIR)/CAcs.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAcs.cpp -o $*.o
lib/CGrupoSkill.o:$(SRCDIR)/CGrupoSkill.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CGrupoSkill.cpp -o $*.o
lib/CGrupoUsuario.o:$(SRCDIR)/CGrupoUsuario.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CGrupoUsuario.cpp -o $*.o
lib/CAreReg.o:$(SRCDIR)/CAreReg.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CAreReg.cpp -o $*.o
lib/CPesDoc.o:$(SRCDIR)/CPesDoc.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPesDoc.cpp -o $*.o
lib/CDoc.o:$(SRCDIR)/CDoc.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CDoc.cpp -o $*.o
lib/CUFOpe.o:$(SRCDIR)/CUFOpe.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUFOpe.cpp -o $*.o
lib/CCrg.o:$(SRCDIR)/CCrg.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCrg.cpp -o $*.o
lib/CGpt.o:$(SRCDIR)/CGpt.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CGpt.cpp -o $*.o
lib/CGrp.o:$(SRCDIR)/CGrp.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CGrp.cpp -o $*.o
lib/CImn.o:$(SRCDIR)/CImn.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CImn.cpp -o $*.o
lib/CPais.o:$(SRCDIR)/CPais.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPais.cpp -o $*.o
lib/CPgn.o:$(SRCDIR)/CPgn.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPgn.cpp -o $*.o
lib/CPrf.o:$(SRCDIR)/CPrf.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPrf.cpp -o $*.o
lib/CSis.o:$(SRCDIR)/CSis.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSis.cpp -o $*.o
lib/CSisOrg.o:$(SRCDIR)/CSisOrg.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSisOrg.cpp -o $*.o
lib/CSrv.o:$(SRCDIR)/CSrv.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSrv.cpp -o $*.o
lib/CSts.o:$(SRCDIR)/CSts.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSts.cpp -o $*.o
lib/CSubItr.o:$(SRCDIR)/CSubItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSubItr.cpp -o $*.o
lib/CSub.o:$(SRCDIR)/CSub.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSub.cpp -o $*.o
lib/CTipCrt.o:$(SRCDIR)/CTipCrt.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipCrt.cpp -o $*.o
lib/CTipDoc.o:$(SRCDIR)/CTipDoc.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipDoc.cpp -o $*.o
lib/CTipPes.o:$(SRCDIR)/CTipPes.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipPes.cpp -o $*.o
lib/CUF.o:$(SRCDIR)/CUF.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUF.cpp -o $*.o
lib/CUni.o:$(SRCDIR)/CUni.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUni.cpp -o $*.o
lib/CUsr.o:$(SRCDIR)/CUsr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUsr.cpp -o $*.o
lib/CTipoGrupo.o:$(SRCDIR)/CTipoGrupo.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoGrupo.cpp -o $*.o
lib/CTipoGrupoItr.o:$(SRCDIR)/CTipoGrupoItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoGrupoItr.cpp -o $*.o
lib/CContatoFolhaUsuario.o:$(SRCDIR)/CContatoFolhaUsuario.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoFolhaUsuario.cpp -o $*.o
lib/CContatoFolhaUsuarioItr.o:$(SRCDIR)/CContatoFolhaUsuarioItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoFolhaUsuarioItr.cpp -o $*.o
lib/CContatoFolhaUsuarioSkill.o:$(SRCDIR)/CContatoFolhaUsuarioSkill.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoFolhaUsuarioSkill.cpp -o $*.o
lib/CContatoFolhaUsuarioSkillItr.o:$(SRCDIR)/CContatoFolhaUsuarioSkillItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CContatoFolhaUsuarioSkillItr.cpp -o $*.o
lib/CPerfilConsultorAtd.o:$(SRCDIR)/CPerfilConsultorAtd.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPerfilConsultorAtd.cpp -o $*.o
lib/CPerfilConsultorAtdItr.o:$(SRCDIR)/CPerfilConsultorAtdItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPerfilConsultorAtdItr.cpp -o $*.o
lib/CFornecedorConsultorAtd.o:$(SRCDIR)/CFornecedorConsultorAtd.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFornecedorConsultorAtd.cpp -o $*.o
lib/CFornecedorConsultorAtdItr.o:$(SRCDIR)/CFornecedorConsultorAtdItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CFornecedorConsultorAtdItr.cpp -o $*.o
lib/CSiteConsultorAtd.o:$(SRCDIR)/CSiteConsultorAtd.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSiteConsultorAtd.cpp -o $*.o
lib/CSiteConsultorAtdItr.o:$(SRCDIR)/CSiteConsultorAtdItr.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSiteConsultorAtdItr.cpp -o $*.o

#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)

install:
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/$(USUARIO_PTH)/lib
#	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib
#	@-mkdir $(TUXDIR_DES)/include/$(SUBDIR)  2> /dev/null
#	@-cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR) 2> /dev/null

insbin:
	cp -f $(LIBDIR)/lib$(SUBDIR).so /opt/esac/dvfo01rt/server/lib

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..

