package soc.assignment;

public class ExtensionSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final ExtensionSchema INSTANCE;

    static {
        INSTANCE = new ExtensionSchema(new ExtensionSchemaBuilder());
    }

    public static ExtensionSchema instance() {
        return INSTANCE;
    }

    private ExtensionSchema(ExtensionSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class ExtensionSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public ExtensionSchemaBuilder() {
            name = AssignmentMessageName._extension.canonical();
            senderRole = AssignmentRoleName._I.canonical();
            receiverRole = AssignmentRoleName._S.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(AssignmentParameterName._aID.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(AssignmentParameterName._aID.canonical());
            inParameters.add(AssignmentParameterName._extensionReason.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(AssignmentParameterName._extension.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
