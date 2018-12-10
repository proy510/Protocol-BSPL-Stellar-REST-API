package soc.assignment;

public class RequestRegrade extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    RequestRegrade(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(RequestRegradeSchema.instance().getName(), sender, receiver, bindings);
	}

    RequestRegrade(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

	public java.lang.String bindingOfAID() {
		return getBinding(AssignmentParameterName._aID.canonical());
	}
	
	public java.lang.String bindingOfTentativeGrade() {
		return getBinding(AssignmentParameterName._tentativeGrade.canonical());
	}
	
	public java.lang.String bindingOfRegradeReason() {
		return getBinding(AssignmentParameterName._regradeReason.canonical());
	}
	
	public java.lang.String bindingOfGrade() {
		return getBinding(AssignmentParameterName._grade.canonical());
	}
	
}
