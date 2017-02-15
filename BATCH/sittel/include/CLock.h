/*
 * CLock.h
 *
 *  Created on: 15/06/2013
 *      Author: Jones Randis
 */

#ifndef CLOCK_H_
#define CLOCK_H_

#include <string>
#include "config.h"

namespace batch {

class CLock {
protected:
	std::string lockfile;
	int plockfile;

public:
	CLock(const std::string& path = std::string(), const std::string& file = std::string(LOCK_FILE));
	virtual ~CLock();
};

} /* namespace batch */
#endif /* CLOCK_H_ */
