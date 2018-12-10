package soc.assignment;

public class PostGrade extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    PostGrade(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(PostGradeSchema.instance().getName(), sender, receiver, bindings);
	}

    PostGrade(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

	public java.lang.String bindingOfAID() {
		return getBinding(AssignmentParameterName._aID.canonical());
	}
	
	public java.lang.String bindingOfSubmission() {
		return getBinding(AssignmentParameterName._submission.canonical());
	}
	
	public java.lang.String bindingOfTentativeGrade() {
		return getBinding(AssignmentParameterName._tentativeGrade.canonical());
	}
	
}
