package soc.assignment;

public class PostGradeSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final PostGradeSchema INSTANCE;

    static {
        INSTANCE = new PostGradeSchema(new PostGradeSchemaBuilder());
    }

    public static PostGradeSchema instance() {
        return INSTANCE;
    }

    private PostGradeSchema(PostGradeSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class PostGradeSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public PostGradeSchemaBuilder() {
            name = AssignmentMessageName._postGrade.canonical();
            senderRole = AssignmentRoleName._T.canonical();
            receiverRole = AssignmentRoleName._S.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(AssignmentParameterName._aID.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(AssignmentParameterName._aID.canonical());
            inParameters.add(AssignmentParameterName._submission.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(AssignmentParameterName._tentativeGrade.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
