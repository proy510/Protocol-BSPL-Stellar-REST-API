package soc.assignment;

public abstract class TAgent extends uk.ac.lancaster.scc.turtles.stellar.core.agent.BasicAgent {

	private final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier;
	private final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever retriever;
	private final uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator generator;

	protected TAgent(
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier,
			final uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory history) {
		super(buildEndpoint(selfIdentifier, history));
		this.selfIdentifier = selfIdentifier;
		this.retriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(
				new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(
						history,
						AssignmentTProtocolSchema.instance(),
						new uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler()));
        this.generator = new uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator();
    }

	private static uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEndpoint buildEndpoint(
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier,
			final uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory history) {
		uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram.DatagramSender sender = new uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram.DatagramSender(2048);
		uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram.DatagramReceiver receiver = null;
		try {
			receiver = new uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram.DatagramReceiver(Integer.parseInt(selfIdentifier.getPort()), 2048);
		} catch (java.net.SocketException e) {
			java.lang.System.err.println("Agent " + selfIdentifier.getName() + " cannot create the communication socket on port " + selfIdentifier.getPort());
			java.lang.System.exit(0);
		}
		uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler compiler = new uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler();
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever enactmentRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(history, AssignmentTProtocolSchema.instance(), compiler);
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever enabledRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(enactmentRetriever);
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker viabilityChecker = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker(enactmentRetriever);
		return new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEndpoint(sender, receiver, AssignmentTProtocolSchema.instance(), history, compiler, enabledRetriever, viabilityChecker);
	}

	@Override
	protected final void handleMessage(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message message) {
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage bsplMessage = (uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage) message;
        if (bsplMessage.getName().equals(PostSubmissionSchema.instance().getName())) {
        	handlePostSubmission(new PostSubmission(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(AcceptGradeSchema.instance().getName())) {
        	handleAcceptGrade(new AcceptGrade(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(RequestRegradeSchema.instance().getName())) {
        	handleRequestRegrade(new RequestRegrade(bsplMessage));
        	return;
        }
    }

	protected abstract void handlePostSubmission(PostSubmission receivedMessage);
	
	protected abstract void handleAcceptGrade(AcceptGrade receivedMessage);
	
	protected abstract void handleRequestRegrade(RequestRegrade receivedMessage);
	
	public java.util.List<EnabledPostGrade> retrieveEnabledPostGrade() {
		return retrieveEnabledPostGrade("");
	}
	
	public java.util.List<EnabledPostGrade> retrieveEnabledPostGrade(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(PostGradeSchema.instance(), whereClause);
		java.util.List<EnabledPostGrade> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledPostGrade(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledPostGrade(
			final EnabledPostGrade enabledMessage,
			final java.lang.String tentativeGrade,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(AssignmentParameterName._tentativeGrade.canonical(), tentativeGrade);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
	public java.util.List<EnabledRegrade> retrieveEnabledRegrade() {
		return retrieveEnabledRegrade("");
	}
	
	public java.util.List<EnabledRegrade> retrieveEnabledRegrade(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(RegradeSchema.instance(), whereClause);
		java.util.List<EnabledRegrade> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledRegrade(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledRegrade(
			final EnabledRegrade enabledMessage,
			final java.lang.String grade,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(AssignmentParameterName._grade.canonical(), grade);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
}
