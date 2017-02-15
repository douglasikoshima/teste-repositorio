
APPNAME=atualizacaoAreaRural

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_CFG=$(EXEC_DIR)
EXEC_LOG=$(EXEC_DIR)/log
EXEC_ERR=$(EXEC_DIR)/err
EXEC_DAT=$(EXEC_DIR)/data
EXEC_PRC=$(EXEC_DIR)/prc

all: 

clean:
	rm -f $(EXEC_CFG)/$(APPNAME).cfg
	rm -f $(EXEC_DIR)/$(APPNAME).sh
	rm -f $(EXEC_DIR)/$(APPNAME).ctl
	rm -f $(EXEC_DIR)/$(APPNAME).par
	rm -f $(EXEC_DIR)/$(APPNAME).sql

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
	if [ ! -d $(EXEC_PRC) ] ; then mkdir $(EXEC_PRC); fi

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

	# copia .sh
	cp -pf $(COMPILE_DIR)/$(APPNAME).sh $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/$(APPNAME).ctl $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/$(APPNAME).par $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/$(APPNAME).sql $(EXEC_DIR)

	# seta permissoes
	chmod ug=rwx,o=rx $(EXEC_DIR)/$(APPNAME).sh
	chmod ug=rw,o=r $(EXEC_DIR)/$(APPNAME).ctl
	chmod ug=rw,o=r $(EXEC_DIR)/$(APPNAME).par
	chmod ug=rw,o=r $(EXEC_CFG)/$(APPNAME).cfg
	chmod ug=rw,o=r $(EXEC_DIR)/$(APPNAME).sql
	chmod ug=rwx,o=rwx $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT) $(EXEC_PRC)

uninstall:
	rm -r -f $(EXEC_DIR)

