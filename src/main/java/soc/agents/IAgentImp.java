package soc.agents;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import soc.assignment.EnabledExtension;
import soc.assignment.EnabledPostAssignment;
import soc.assignment.IAgent;
import soc.assignment.RequestExtension;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

public class IAgentImp extends IAgent implements Runnable{

    private final AgentIdentifier selfIdentifier;
    private final AgentIdentifier sIdentifier;
    FileWriter fw = null ;
    BufferedWriter log = null;
    
    public IAgentImp(AgentIdentifier selfIdentifier, AgentIdentifier sIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.sIdentifier = sIdentifier;
        
        try
        {
        	fw = new FileWriter("I.log");
        	log = new BufferedWriter(fw);
        	
        }  catch (IOException ioe) {
     	   ioe.printStackTrace();
     	}
    }
    
    @Override
    protected void handleRequestExtension(RequestExtension receivedMessage) {
    	
    	try {
			log.write(selfIdentifier.getName()+" has to handle request for extension for assignment: "+ receivedMessage.bindingOfAID()+" having reason as: "+receivedMessage.bindingOfExtensionReason()+"."+System.lineSeparator());
			log.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//log.write(selfIdentifier.getName()+" has to handle request for extension for assignment: "+ receivedMessage.bindingOfAID()+"having reason as: "+receivedMessage.bindingOfExtensionReason()));
    	//System.out.println(selfIdentifier.getName()+" has to handle request for extension for assignment: "+ receivedMessage.bindingOfAID()+"having reason as: "+receivedMessage.bindingOfExtensionReason());
    	String extension_reason = receivedMessage.bindingOfExtensionReason();
    	String response = "false";
    	if(extension_reason.equalsIgnoreCase("medical"))
    	{
    		response = "true";
    	}
    	EnabledExtension enabledexten = retrieveEnabledExtension().get(0);
    	sendEnabledExtension(enabledexten,response,sIdentifier);
    	if(response.equalsIgnoreCase("true"))
    	{
    		try {
				log.write("For "+receivedMessage.bindingOfExtensionReason()+" reason, "+selfIdentifier.getName()+" allows extension by sending response:  "+response+" for assignment "+receivedMessage.bindingOfAID()+"."+System.lineSeparator());
				log.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else
    	{
    		try {
				log.write("For "+receivedMessage.bindingOfExtensionReason()+" reason, "+selfIdentifier.getName()+" does not allow extension by sending response:  "+response+" for assignment "+receivedMessage.bindingOfAID()+"."+System.lineSeparator());
				log.flush();
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    @Override
    protected void beforeStart() {
        
        try {
			log.write(selfIdentifier.getName() + " is ready to start"+"."+System.lineSeparator());
			log.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @Override
    protected void afterStart() {
    	
    	try {
			log.write(selfIdentifier.getName() + " has started"+"."+System.lineSeparator());
			log.flush();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	/**
    	 * Instructor posting 5 assignments back to back with an interval of 0.5s
    	 */
    	for(int count = 0; count<5;count++)
    	{
    		EnabledPostAssignment enabledPostAssignmentI = retrieveEnabledPostAssignment();
    		sendEnabledPostAssignment(enabledPostAssignmentI, sIdentifier);
    		int temp = count +1;
    		try {
				log.write(selfIdentifier.getName() + " posted assignment "+temp+"."+System.lineSeparator());
				log.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	
    	}
    	
    }

    @Override
    protected void act() {
    	
    	try {
            // This agent has nothing to do and sleeps for half a second.
            Thread.sleep(500);
        } catch (InterruptedException ex) {
           
        }
    }

    @Override
    protected void beforeStop() {
    	
    
        
    }

    @Override
    protected void afterStop() {
        
    }

    @Override
    public void run() {
        execute();
    }
    
}
