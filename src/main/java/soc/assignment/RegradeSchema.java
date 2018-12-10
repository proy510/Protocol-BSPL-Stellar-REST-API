package soc.assignment;

public class RegradeSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final RegradeSchema INSTANCE;

    static {
        INSTANCE = new RegradeSchema(new RegradeSchemaBuilder());
    }

    public static RegradeSchema instance() {
        return INSTANCE;
    }

    private RegradeSchema(RegradeSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class RegradeSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public RegradeSchemaBuilder() {
            name = AssignmentMessageName._regrade.canonical();
            senderRole = AssignmentRoleName._T.canonical();
            receiverRole = AssignmentRoleName._S.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(AssignmentParameterName._aID.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(AssignmentParameterName._aID.canonical());
            inParameters.add(AssignmentParameterName._regradeReason.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(AssignmentParameterName._grade.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
