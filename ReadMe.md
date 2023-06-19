# Light Logger

We want to build a small Logger library in Java, as you will be working mostly on Java applications.
This will be a library used by other development teams in the company,
so we need to build it as such, and it needs to be generic.


## How to test 

run mvn clean test 

## How to install  

run mvn clean install 


## Big O notation

addLogTarget(LogTarget logTarget): O(1)

log(LogLevel logLevel, String message): O(N)

setMinLogLevel(LogLevel minLogLevel): O(1)

setLogLevelForTarget(LogTarget logTarget, LogLevel logLevel): O(1)

setLogTargets(List<LogTarget> logTargets): O(N)

clearLogTargets(): O(1)

removeLogTarget(LogTarget logTarget): O(N)

removeAllLogTargets(): O(1)

## Next Release 
Implement other log targets 
Add action for other targets 
Performance review 















