package soc.assignment;

public class PostAssignmentSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final PostAssignmentSchema INSTANCE;

    static {
        INSTANCE = new PostAssignmentSchema(new PostAssignmentSchemaBuilder());
    }

    public static PostAssignmentSchema instance() {
        return INSTANCE;
    }

    private PostAssignmentSchema(PostAssignmentSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class PostAssignmentSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public PostAssignmentSchemaBuilder() {
            name = AssignmentMessageName._postAssignment.canonical();
            senderRole = AssignmentRoleName._I.canonical();
            receiverRole = AssignmentRoleName._S.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(AssignmentParameterName._aID.canonical());
            inParameters = new java.util.ArrayList<>();
            outParameters = new java.util.ArrayList<>();
            outParameters.add(AssignmentParameterName._aID.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
