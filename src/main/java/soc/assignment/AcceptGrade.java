package soc.assignment;

public class AcceptGrade extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    AcceptGrade(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(AcceptGradeSchema.instance().getName(), sender, receiver, bindings);
	}

    AcceptGrade(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

	public java.lang.String bindingOfAID() {
		return getBinding(AssignmentParameterName._aID.canonical());
	}
	
	public java.lang.String bindingOfTentativeGrade() {
		return getBinding(AssignmentParameterName._tentativeGrade.canonical());
	}
	
	public java.lang.String bindingOfGrade() {
		return getBinding(AssignmentParameterName._grade.canonical());
	}
	
	public java.lang.String bindingOfRegradeReason() {
		return getBinding(AssignmentParameterName._regradeReason.canonical());
	}
	
}
