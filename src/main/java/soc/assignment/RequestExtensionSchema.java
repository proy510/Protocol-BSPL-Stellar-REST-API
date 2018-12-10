package soc.assignment;

public class RequestExtensionSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final RequestExtensionSchema INSTANCE;

    static {
        INSTANCE = new RequestExtensionSchema(new RequestExtensionSchemaBuilder());
    }

    public static RequestExtensionSchema instance() {
        return INSTANCE;
    }

    private RequestExtensionSchema(RequestExtensionSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class RequestExtensionSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public RequestExtensionSchemaBuilder() {
            name = AssignmentMessageName._requestExtension.canonical();
            senderRole = AssignmentRoleName._S.canonical();
            receiverRole = AssignmentRoleName._I.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(AssignmentParameterName._aID.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(AssignmentParameterName._aID.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(AssignmentParameterName._extensionReason.canonical());
            nilParameters = new java.util.ArrayList<>();
            nilParameters.add(AssignmentParameterName._submission.canonical());
        }

    }

}
