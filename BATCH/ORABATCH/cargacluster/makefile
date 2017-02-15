
APPNAME=cargacluster

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_LOG=$(COMPILE_DIR)/log
COMPILE_ERR=$(COMPILE_DIR)/err
COMPILE_DAT=$(COMPILE_DIR)/data
COMPILE_PRL=$(BATCH_DIR)/../commons/perl64

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_CFG=$(EXEC_DIR)/
EXEC_LOG=$(EXEC_DIR)/log
EXEC_ERR=$(EXEC_DIR)/err
EXEC_DAT=$(EXEC_DIR)/data
EXEC_TMP=$(EXEC_DIR)/tmp
EXEC_PRL=$(PROCESSOS_DIR)/../perl64

all: build

build: 
	# copia libperl
	if [ ! -d $(EXEC_PRL) ] ; then cp -prf $(COMPILE_PRL) $(EXEC_PRL); fi

clean:
	@echo

install:
	umask 077

	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi

	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_ERR) ] ; then mkdir $(EXEC_ERR); fi
	if [ ! -d $(EXEC_DAT) ] ; then mkdir $(EXEC_DAT); fi
	if [ ! -d $(EXEC_TMP) ] ; then mkdir $(EXEC_TMP); fi

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

	# copia .sh
	cp -pf $(COMPILE_DIR)/$(APPNAME).sh $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/$(APPNAME).pl $(EXEC_DIR)

	# seta permissoes
	chmod a+rx $(EXEC_DIR)/$(APPNAME).sh
	chmod a+rx $(EXEC_DIR)/$(APPNAME).pl
	chmod a+r $(EXEC_CFG)/$(APPNAME).cfg
	chmod a+rwx $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT) $(EXEC_TMP)

uninstall:
	rm -r -f $(EXEC_DIR)

