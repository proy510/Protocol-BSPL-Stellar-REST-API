package soc.assignment;

public class Regrade extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    Regrade(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(RegradeSchema.instance().getName(), sender, receiver, bindings);
	}

    Regrade(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

	public java.lang.String bindingOfAID() {
		return getBinding(AssignmentParameterName._aID.canonical());
	}
	
	public java.lang.String bindingOfRegradeReason() {
		return getBinding(AssignmentParameterName._regradeReason.canonical());
	}
	
	public java.lang.String bindingOfGrade() {
		return getBinding(AssignmentParameterName._grade.canonical());
	}
	
}
