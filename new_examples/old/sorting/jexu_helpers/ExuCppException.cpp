#include "ExuCppException.h"

ExuCppException::ExuCppException(const char* msg) : message(msg) {}

const char* ExuCppException::getMessage() { return message; }
