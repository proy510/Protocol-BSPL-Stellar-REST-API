package soc.assignment;

public class RequestRegradeSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final RequestRegradeSchema INSTANCE;

    static {
        INSTANCE = new RequestRegradeSchema(new RequestRegradeSchemaBuilder());
    }

    public static RequestRegradeSchema instance() {
        return INSTANCE;
    }

    private RequestRegradeSchema(RequestRegradeSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class RequestRegradeSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public RequestRegradeSchemaBuilder() {
            name = AssignmentMessageName._requestRegrade.canonical();
            senderRole = AssignmentRoleName._S.canonical();
            receiverRole = AssignmentRoleName._T.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(AssignmentParameterName._aID.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(AssignmentParameterName._aID.canonical());
            inParameters.add(AssignmentParameterName._tentativeGrade.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(AssignmentParameterName._regradeReason.canonical());
            nilParameters = new java.util.ArrayList<>();
            nilParameters.add(AssignmentParameterName._grade.canonical());
        }

    }

}
