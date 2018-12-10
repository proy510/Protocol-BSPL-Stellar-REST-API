package soc.agents;

import soc.assignment.AcceptGrade;
import soc.assignment.EnabledPostGrade;
import soc.assignment.EnabledPostSubmission;
import soc.assignment.EnabledRegrade;
import soc.assignment.PostSubmission;
import soc.assignment.RequestRegrade;
import soc.assignment.TAgent;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TAgentImp extends TAgent implements Runnable {

    private final AgentIdentifier selfIdentifier;
    private final AgentIdentifier sIdentifier;
    private int counter = 0;
    FileWriter fw = null ;
    BufferedWriter log = null;

   public TAgentImp(AgentIdentifier selfIdentifier, AgentIdentifier sIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.sIdentifier = sIdentifier;
        try
        {
        	fw = new FileWriter("T.log");
        	log = new BufferedWriter(fw);
        	
        }  catch (IOException ioe) {
     	   ioe.printStackTrace();
     	}
   }
    
    @Override
    protected void handlePostSubmission(PostSubmission receivedMessage) {
    	
    	try {
			log.write(selfIdentifier.getName()+" needs to post grades for submission: "+receivedMessage.bindingOfSubmission()+"."+System.lineSeparator());
			log.flush();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Random rand = new Random();
    	
    	//System.out.println(selfIdentifier.getName() + " received submission " +receivedMessage.bindingOfSubmission());
    	EnabledPostGrade enabledPostSubmission = retrieveEnabledPostGrade().get(0);
    	int random_grade = rand.nextInt(100);
    	String grade_string = Integer.toString(random_grade);
        sendEnabledPostGrade(enabledPostSubmission, grade_string, sIdentifier);
        try {
			log.write(selfIdentifier.getName() + " posts grade:  " + grade_string + " for submission: " + receivedMessage.bindingOfSubmission()+"."+System.lineSeparator());
			log.flush();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }

    @Override
    protected void handleAcceptGrade(AcceptGrade receivedMessage) {
    	
    	counter++;
    	System.out.println("count in TA is:" + counter);
		if(counter == 5)
			{
					stop();
			}

    }

    @Override
    protected void handleRequestRegrade(RequestRegrade receivedMessage) {
    	
    	Random rand = new Random();
    	
    	try {
			log.write(selfIdentifier.getName()+" received regrade request with reason: "+receivedMessage.bindingOfRegradeReason()+ " for submission s_"+receivedMessage.bindingOfAID()+"."+System.lineSeparator());
			log.flush();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String reason = receivedMessage.bindingOfRegradeReason();
    	if(reason.contains("legit_reason"))
    	{
    		int rand_1 = rand.nextInt(100);
    		
    		EnabledRegrade enabledregrade = retrieveEnabledRegrade().get(0);
    		sendEnabledRegrade(enabledregrade,rand_1+"",sIdentifier);
    		try {
				log.write(selfIdentifier.getName()+" handles request regrade for legit reason and sends new grade "+ rand_1+"for submission s_"+receivedMessage.bindingOfAID()+"."+System.lineSeparator());
				log.flush();
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	}
    	else
    	{
    		
    		EnabledRegrade enabledregrade = retrieveEnabledRegrade().get(0);
    		sendEnabledRegrade(enabledregrade,receivedMessage.bindingOfTentativeGrade(),sIdentifier);
    		try {
				log.write(selfIdentifier.getName()+" handles request regrade for No reason and sends existing  grade "+receivedMessage.bindingOfTentativeGrade()+" for submission s_"+receivedMessage.bindingOfAID()+"."+System.lineSeparator());
				log.flush();
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
    	}
    	counter++;
    	System.out.println("count in TA is:" + counter);
		if(counter == 5) {
			stop();
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
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    	
    	try {
			log.write(selfIdentifier.getName()+" is about to stop"+"."+System.lineSeparator());
			log.flush();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }

    @Override
    protected void afterStop() {
    	
    	try {
			log.write(selfIdentifier.getName()+" has stopped"+"."+System.lineSeparator());
			log.flush();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    @Override
    public void run() {
        execute();
    }
    
}
