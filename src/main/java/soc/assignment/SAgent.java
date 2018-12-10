package soc.assignment;

public abstract class SAgent extends uk.ac.lancaster.scc.turtles.stellar.core.agent.BasicAgent {

	private final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier;
	private final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever retriever;
	private final uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator generator;

	protected SAgent(
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier,
			final uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory history) {
		super(buildEndpoint(selfIdentifier, history));
		this.selfIdentifier = selfIdentifier;
		this.retriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(
				new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(
						history,
						AssignmentSProtocolSchema.instance(),
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
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever enactmentRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(history, AssignmentSProtocolSchema.instance(), compiler);
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever enabledRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(enactmentRetriever);
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker viabilityChecker = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker(enactmentRetriever);
		return new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEndpoint(sender, receiver, AssignmentSProtocolSchema.instance(), history, compiler, enabledRetriever, viabilityChecker);
	}

	@Override
	protected final void handleMessage(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message message) {
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage bsplMessage = (uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage) message;
        if (bsplMessage.getName().equals(PostAssignmentSchema.instance().getName())) {
        	handlePostAssignment(new PostAssignment(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(ExtensionSchema.instance().getName())) {
        	handleExtension(new Extension(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(PostGradeSchema.instance().getName())) {
        	handlePostGrade(new PostGrade(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(RegradeSchema.instance().getName())) {
        	handleRegrade(new Regrade(bsplMessage));
        	return;
        }
    }

	protected abstract void handlePostAssignment(PostAssignment receivedMessage);
	
	protected abstract void handleExtension(Extension receivedMessage);
	
	protected abstract void handlePostGrade(PostGrade receivedMessage);
	
	protected abstract void handleRegrade(Regrade receivedMessage);
	
	public java.util.List<EnabledPostSubmission> retrieveEnabledPostSubmission() {
		return retrieveEnabledPostSubmission("");
	}
	
	public java.util.List<EnabledPostSubmission> retrieveEnabledPostSubmission(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(PostSubmissionSchema.instance(), whereClause);
		java.util.List<EnabledPostSubmission> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledPostSubmission(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledPostSubmission(
			final EnabledPostSubmission enabledMessage,
			final java.lang.String submission,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(AssignmentParameterName._submission.canonical(), submission);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
	public java.util.List<EnabledRequestExtension> retrieveEnabledRequestExtension() {
		return retrieveEnabledRequestExtension("");
	}
	
	public java.util.List<EnabledRequestExtension> retrieveEnabledRequestExtension(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(RequestExtensionSchema.instance(), whereClause);
		java.util.List<EnabledRequestExtension> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledRequestExtension(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledRequestExtension(
			final EnabledRequestExtension enabledMessage,
			final java.lang.String extensionReason,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(AssignmentParameterName._extensionReason.canonical(), extensionReason);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
	public java.util.List<EnabledAcceptGrade> retrieveEnabledAcceptGrade() {
		return retrieveEnabledAcceptGrade("");
	}
	
	public java.util.List<EnabledAcceptGrade> retrieveEnabledAcceptGrade(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(AcceptGradeSchema.instance(), whereClause);
		java.util.List<EnabledAcceptGrade> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledAcceptGrade(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledAcceptGrade(
			final EnabledAcceptGrade enabledMessage,
			final java.lang.String grade,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(AssignmentParameterName._grade.canonical(), grade);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
	public java.util.List<EnabledRequestRegrade> retrieveEnabledRequestRegrade() {
		return retrieveEnabledRequestRegrade("");
	}
	
	public java.util.List<EnabledRequestRegrade> retrieveEnabledRequestRegrade(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(RequestRegradeSchema.instance(), whereClause);
		java.util.List<EnabledRequestRegrade> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledRequestRegrade(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledRequestRegrade(
			final EnabledRequestRegrade enabledMessage,
			final java.lang.String regradeReason,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(AssignmentParameterName._regradeReason.canonical(), regradeReason);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
}
