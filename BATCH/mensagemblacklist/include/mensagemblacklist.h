/*
 * mensagemblacklist.h
 *
 *  Created on: 03/01/2013
 *      Author: Jones Randis
 */

#ifndef MENSAGEMBLACKLIST_H_
#define MENSAGEMBLACKLIST_H_

#include <exception>
#include <string>
#include <memory>
#include <vector>
#include <utility>
#include <ostream>
#include <ctime>
using namespace std;

#define DATAFILE_EXT 			".txt"
#define LOGFILE_EXT 			".log"
#define BADFILE_EXT 			".bad"

#define PRCFILE_MAX 	5

#define EX_SUCC			0
#define EX_FAIL			1
#define EX_WARN			2
#define EX_FTL			3
#define ENDORASTR(varstr) varstr.arr[varstr.len]=0;

class CMensagemBlacklist
{
public:
	CMensagemBlacklist(string& configFile);
	virtual ~CMensagemBlacklist();

	static ostream& log(ostream& out = cout) {
		time_t t = time(0);
		struct tm* ptm = localtime( &t );
		char buff[24];
		strftime(buff, sizeof(buff), "%Y-%m-%d %H:%M:%S -- ", ptm);
	    return (out << buff);
	}

	void Run();

private:
	class CException : public exception
	{
	public:
		CException() : emsg("CMensagemBlacklist Exception!") {}
		CException(string emsg) : emsg(emsg) {}
		virtual const char* what() const throw() { return emsg.c_str(); }
		virtual ~CException() throw() {}
	protected:
		string emsg;
	}
	Exception,
	ExceptionConfigFile,
	ExceptionConfigParam,
	ExceptionParamDatabase,
	ExceptionParamControlFile,
	ExceptionPathDir,
	ExceptionStatFile,
	ExceptionRemoveFile,
	ExceptionRenameFile,
	ExceptionControlFile,
	ExceptionSqlldrExec,
	ExceptionDatabaseConnect,
	ExceptionDatabaseDisonnect,
	ExceptionDatabaseSqlError;

	struct CParamConf
	{
		auto_ptr<string> usr_db;
		auto_ptr<string> pwd_db;
		auto_ptr<string> inst_db;
		auto_ptr<string> path;
		auto_ptr<string> control_file;
		auto_ptr<string> path_prc;

		bool verify() {
			if ( !usr_db.get() ) return false;
			if ( !pwd_db.get() ) return false;
			if ( !inst_db.get() ) return false;
			if ( !path.get() ) return false;
			if ( !control_file.get() ) return false;
			if ( !path_prc.get() ) return false;
			return true;
		}
	}
	ParamConf;

	string conn_str;
	vector< pair<int, string> > data_files;
	vector< pair<int, string> >::iterator data_files_it;
	bool connected;

	void GetParamConf(string& configFile);
	void OpenDir(string& dataDir);
	void DBConnect();
	void DBDisconnect();
	void LoadFiles();
	bool CtlInfo(string& logfile, string& sread, string& srejected);
};


#endif /* MENSAGEMBLACKLIST_H_ */
