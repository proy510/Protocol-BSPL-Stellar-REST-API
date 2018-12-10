package soc.agents;

import java.util.concurrent.TimeUnit;

import soc.assignment.EnabledAcceptGrade;
import soc.assignment.EnabledPostSubmission;
import soc.assignment.EnabledRequestExtension;
import soc.assignment.EnabledRequestRegrade;
import soc.assignment.Extension;
import soc.assignment.PostAssignment;
import soc.assignment.PostGrade;
import soc.assignment.Regrade;
import soc.assignment.SAgent;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class SAgentImp extends SAgent implements Runnable {

    private final AgentIdentifier selfIdentifier;
    private final AgentIdentifier iIdentifier;
    private final AgentIdentifier tIdentifier;
    private int counter = 0;
    FileWriter fw = null ;
    BufferedWriter log = null;
    
    public SAgentImp(AgentIdentifier selfIdentifier, AgentIdentifier iIdentifier, AgentIdentifier tIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.iIdentifier = iIdentifier;
        this.tIdentifier = tIdentifier;
        try
        {
        	fw = new FileWriter("S.log");
        	log = new BufferedWriter(fw);
        	
        }  catch (IOException ioe) {
     	   ioe.printStackTrace();
     	}
    }
    
    @Override
    protected void handlePostAssignment(PostAssignment receivedMessage) {
    	
    	Random rand = new Random();
    	int random_prob = rand.nextInt(100);
    	try {
			log.write(selfIdentifier.getName() + " received assignment with id: "  + 
			        receivedMessage.bindingOfAID()+"."+System.lineSeparator());
			log.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	if(random_prob < 80)
    	{
    		String extension_reason = null;
    		int rand_2 = rand.nextInt(100);
    		if(rand_2 < 50)
    		{
    			extension_reason = "medical";
    			
    				
    		}
    		else
    		{
    			extension_reason = "vacation";
    			
    		}
    		
    		EnabledRequestExtension enabledrequesexten = retrieveEnabledRequestExtension().get(0);
        	sendEnabledRequestExtension(enabledrequesexten, extension_reason, iIdentifier);
        	try {
				log.write(selfIdentifier.getName()+" had "+extension_reason+" reason, therefore requested extension for assignment "+receivedMessage.bindingOfAID()+"."+System.lineSeparator());
				log.flush();
        	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
    	}
    	else
    	{
    		
    		try {
    			TimeUnit.MILLISECONDS.sleep(500);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		EnabledPostSubmission enabledPostSubmissionS = retrieveEnabledPostSubmission().get(0);
        	sendEnabledPostSubmission(enabledPostSubmissionS, "s_"+receivedMessage.bindingOfAID(), tIdentifier);
            try {
				log.write(selfIdentifier.getName() + " posted submission for submission " +
				        " id = s_" +receivedMessage.bindingOfAID()+"."+System.lineSeparator());
				log.flush();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            

    	}
    	
    	
    	
    	
    }

    @Override
    protected void handleExtension(Extension receivedMessage) {
    	
    		String response = receivedMessage.bindingOfExtension();
    		
    		if(response.equalsIgnoreCase("false"))
    		{
    			try {
					log.write(selfIdentifier.getName()+" did not have an extension granted for reason: "+receivedMessage.bindingOfExtensionReason()+"for assignment: "+receivedMessage.bindingOfAID()+"."+System.lineSeparator());
					log.flush();
    			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    			/*try {
    				TimeUnit.MILLISECONDS.sleep(500);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}*/
    			EnabledPostSubmission enabledPostSubmissionS = retrieveEnabledPostSubmission().get(0);
            	sendEnabledPostSubmission(enabledPostSubmissionS, "s_"+receivedMessage.bindingOfAID(), tIdentifier);
                try {
					log.write(selfIdentifier.getName() + " had to submit assignment " + receivedMessage.bindingOfAID() + " with submission " +
					        " id =s_" + receivedMessage.bindingOfAID()+"."+System.lineSeparator());
					log.flush();
                } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		}
    		else
    		{
    			try {
					log.write(selfIdentifier.getName()+" had an extension granted for reason: "+receivedMessage.bindingOfExtensionReason()+"for assignment: "+receivedMessage.bindingOfAID()+"."+System.lineSeparator());
					log.flush();
    			} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

    			try {
    				TimeUnit.MILLISECONDS.sleep(1000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    			EnabledPostSubmission enabledPostSubmissionS = retrieveEnabledPostSubmission().get(0);
            	sendEnabledPostSubmission(enabledPostSubmissionS, "s_"+receivedMessage.bindingOfAID(), tIdentifier);
                try {
					log.write(selfIdentifier.getName() + " sent submission after extension for medical for assignment" + receivedMessage.bindingOfAID() + " with submission " +
					        " id =s_" + receivedMessage.bindingOfAID()+"."+System.lineSeparator());
					log.flush();
                } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		

    }

    @Override
    protected void handlePostGrade(PostGrade receivedMessage) {
    	
    	try {
			log.write(selfIdentifier.getName()+" received grade: "+receivedMessage.bindingOfTentativeGrade()+" for submission: "+receivedMessage.bindingOfSubmission()+"."+System.lineSeparator());
			log.flush();
    	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	Random rand = new Random();
    	int rand_new = rand.nextInt(100);
    	if(rand_new <50)
    	{
    		EnabledAcceptGrade enabledacceptgrade = retrieveEnabledAcceptGrade().get(0);
    		sendEnabledAcceptGrade(enabledacceptgrade,receivedMessage.bindingOfTentativeGrade(),tIdentifier);
    		try {
				log.write(selfIdentifier.getName()+" accepted the grade "+receivedMessage.bindingOfTentativeGrade()+" for submission "+receivedMessage.bindingOfSubmission()+"."+System.lineSeparator());
				log.flush();
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		counter++;
    		if(counter == 5)
    			stop();
    	}
    	else
    	{
    		int rand_2 = rand.nextInt(100);
    		String reason = null;
    		if(rand_2 < 50)
    		{
    			reason = "r_"+receivedMessage.bindingOfAID()+"_legit_reason";
    		}
    		else
    		{
    			reason = "r_"+receivedMessage.bindingOfAID()+"_no_reason";
    		}
    		
    		EnabledRequestRegrade enabledrequestregrade = retrieveEnabledRequestRegrade().get(0);
    		sendEnabledRequestRegrade(enabledrequestregrade,reason,tIdentifier);
    		try {
				log.write(selfIdentifier.getName()+" requested regrade with reason: "+reason+ " for submission "+receivedMessage.bindingOfSubmission()+"."+System.lineSeparator());
				log.flush();
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

    }

    @Override
    protected void handleRegrade(Regrade receivedMessage) {
    	counter++;
		if(counter == 5)
			stop();
    
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
