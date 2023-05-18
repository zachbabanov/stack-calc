# Stack calculator
## Installing dependencies
  For unit testing it is necessary to install JUnit either manually, with Maven or JetBrains IDEA packet manager  
## Building an application
  In the directory of all included .java files build a file  
  ```
  javac StackCalculator.java 
  ```
  And start it with reference to source file  
  ```
  java StackCalculator source.txt
  ```
## Calculator features
  - \# - commenting current line  
  - POP - remove a value from stack  
  - PUSH - get a value to the stack  
  - +, -, \*, \/, SQRT - arithmetic operators  
  - PRINT - printing a top stack value without popping it  
  - DEFINE - rule for a parameter value  
  Filling the source file with these commands make the usage of calculator possible  
## Logging
  Each of above-described functions is logged to terminal while processing
  As for example, before processing any line without a "#" symbol there will be following information in the log:
  ```
  Processing line: \linenumber
  ```
## Unit testing
  With unit testing I tried to cover all possible scenarios of usage without injection any source code to the program itself
