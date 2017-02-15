/*
 * CNet.h
 *
 *  Created on: 24/06/2013
 *      Author: Jones Randis
 */

#ifndef CNET_H_
#define CNET_H_

#include <string>

namespace batch {

bool getHostName(std::string& hostname);
bool getIpAddress(std::string& ipaddr);

} /* namespace batch */
#endif /* CNET_H_ */
