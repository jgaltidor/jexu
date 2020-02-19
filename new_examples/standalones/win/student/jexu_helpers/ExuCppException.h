#ifndef EXUCPPEXCEPTION_H
#define EXUCPPEXCEPTION_H

class ExuCppException
{
public:
	const char* message;
	ExuCppException(const char* msg);
	const char* getMessage();
};
#endif

