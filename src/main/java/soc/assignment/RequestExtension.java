package soc.assignment;

public class RequestExtension extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    RequestExtension(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(RequestExtensionSchema.instance().getName(), sender, receiver, bindings);
	}

    RequestExtension(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

	public java.lang.String bindingOfAID() {
		return getBinding(AssignmentParameterName._aID.canonical());
	}
	
	public java.lang.String bindingOfExtensionReason() {
		return getBinding(AssignmentParameterName._extensionReason.canonical());
	}
	
	public java.lang.String bindingOfSubmission() {
		return getBinding(AssignmentParameterName._submission.canonical());
	}
	
}
