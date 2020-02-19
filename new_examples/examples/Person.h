// =============================================
// Generated on: Tue May 11 00:09:00
// Header of proxy class for Java class: examples.Person
// =============================================
#ifndef EXAMPLES_PERSON_H
#define EXAMPLES_PERSON_H
#include "jexulib.h"

namespace examples {

class Person : public jexu::JavaBaseClass {
public:
  Person(jobject);
  Person(char*);
  char* getName();
  void setName(char*);
  void copyPerson(examples::Person*);
  examples::Person* myself();
  static void printPerson(examples::Person*);
};
} // closing namespace context
#endif
