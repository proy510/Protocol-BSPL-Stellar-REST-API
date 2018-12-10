package soc.assignment;

public class PostAssignment extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    PostAssignment(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(PostAssignmentSchema.instance().getName(), sender, receiver, bindings);
	}

    PostAssignment(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

	public java.lang.String bindingOfAID() {
		return getBinding(AssignmentParameterName._aID.canonical());
	}
	
}
