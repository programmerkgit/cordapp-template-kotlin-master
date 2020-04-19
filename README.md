# Cordapp-template-kotlin-master

## To learn
- [ ] Flow flow exactly. 
- [ ] Flow kick => verify and sign =>
 send sign collection to Notary => Notary verification => Commit => Announce to the other participants.  
 
## Notarize
When two signs gather, notarize that some state was consumed.  
When double spent occurs, notary does't notarize sign collection. So, commitment fails.     

## Run commands deployNodes from terminal and application permission setting was necessary.   

Error to resolve

```
Logs can be found in                    : /Users/admin/IdeaProjects/cordapp-template-kotlin/build/nodes/Dealership/logs
⚠️   ATTENTION: This node is running in development mode! 👩‍💻   This is not safe for production deployment.
[ERROR] 03:24:40+0900 [main] internal.NodeStartupLogging. - Exception during node startup: Unable to determine which flow to use when responding to: com.template.flows.Initiator. [com.template.flows.CarClassResponder, com.template.flows.Responder] are all registered with equal weight. [errorCode=mnl04m, moreInformationAt=https://errors.corda.net/OS/4.4/mnl04m]
Logs can be found in                    : /Users/admin/IdeaProjects/cordapp-template-kotlin/build/nodes/Dealership/logs
⚠️   ATTENTION: This node is running in development mode! 👩‍💻   This is not safe for production deployment.
[ERROR] 03:24:40+0900 [main] internal.NodeStartupLogging. - Exception during node startup: Unable to determine which flow to use when responding to: com.template.flows.Initiator. [com.template.flows.CarClassResponder, com.template.flows.Responder] are all registered with equal weight. [errorCode=mnl04m, moreInformationAt=https://errors.corda.net/OS/4.4/mnl04m]

```

