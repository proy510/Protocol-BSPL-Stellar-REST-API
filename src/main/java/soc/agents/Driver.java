package soc.agents;

import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLServerMetadata;

public class Driver {

    // SET THE FOLLOWING CONFIGURATION PARAMETERS
 
    // For simplicity all agents use the same database user account and password.
    // DO NOT FORGET TO SET THESE ACCORDING TO YOUR SETUP
    private final static String DB_USER = "root";
    private final static String DB_PASS = "root";
    
    // All agent databases are in the local host. Otherwise update MYSQL_HOST.
    private final static String MYSQL_HOST = "127.0.0.1";
 
    // The following are the names of the agent's databases according to abc.myq script
    // If you change the database names in the script, update these too.
    private final static String AGENT_I_DB = "ihistory";
    private final static String AGENT_S_DB = "shistory";
    private final static String AGENT_T_DB = "thistory";
        
    // Agent names are not important. They are only used when printing log messages.
    // You can use the default values or change as you want.
    private final static String AGENT_I_NAME = "I";
    private final static String AGENT_S_NAME = "S";
    private final static String AGENT_T_NAME = "T";

    // Since agents run in the same process as threads, we use the localhost.
    // If you want to run the agents on different machines, I can implement them
    // in separate projects.
    private final static String AGENT_I_HOST = "127.0.0.1";
    private final static String AGENT_S_HOST = "127.0.0.1";
    private final static String AGENT_T_HOST = "127.0.0.1";

    // Each agent must have a uniqe port number, which is not in use. The following
    // defaults should normally work.
    private final static String AGENT_I_PORT = "41681";
    private final static String AGENT_S_PORT = "41682";
    private final static String AGENT_T_PORT = "41683";
        
    public static void main(String[] args) throws InterruptedException {
        AgentIdentifier identifierOfI = new AgentIdentifier(AGENT_I_NAME, AGENT_I_HOST, AGENT_I_PORT);
        MySQLHistory historyOfI = new MySQLHistory(new MySQLServerMetadata(MYSQL_HOST, AGENT_I_DB, DB_USER, DB_PASS));
        AgentIdentifier identifierOfS = new AgentIdentifier(AGENT_S_NAME, AGENT_S_HOST, AGENT_S_PORT);
        MySQLHistory historyOfS = new MySQLHistory(new MySQLServerMetadata(MYSQL_HOST, AGENT_S_DB, DB_USER, DB_PASS));
        AgentIdentifier identifierOfT = new AgentIdentifier(AGENT_T_NAME, AGENT_T_HOST, AGENT_T_PORT);
        MySQLHistory historyOfT = new MySQLHistory(new MySQLServerMetadata(MYSQL_HOST, AGENT_T_DB, DB_USER, DB_PASS));

        Thread threadI = new Thread(new IAgentImp(identifierOfI, identifierOfS, historyOfI));
        Thread threadS = new Thread(new SAgentImp(identifierOfS, identifierOfI, identifierOfT, historyOfS));
        Thread threadT = new Thread(new TAgentImp(identifierOfT, identifierOfS, historyOfT));
        threadT.start();
        threadS.start();
        threadI.start();
        threadI.join();
        threadS.join();
        threadT.join();
    }
    
}
