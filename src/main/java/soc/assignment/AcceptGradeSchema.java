package soc.assignment;

public class AcceptGradeSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final AcceptGradeSchema INSTANCE;

    static {
        INSTANCE = new AcceptGradeSchema(new AcceptGradeSchemaBuilder());
    }

    public static AcceptGradeSchema instance() {
        return INSTANCE;
    }

    private AcceptGradeSchema(AcceptGradeSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class AcceptGradeSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public AcceptGradeSchemaBuilder() {
            name = AssignmentMessageName._acceptGrade.canonical();
            senderRole = AssignmentRoleName._S.canonical();
            receiverRole = AssignmentRoleName._T.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(AssignmentParameterName._aID.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(AssignmentParameterName._aID.canonical());
            inParameters.add(AssignmentParameterName._tentativeGrade.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(AssignmentParameterName._grade.canonical());
            nilParameters = new java.util.ArrayList<>();
            nilParameters.add(AssignmentParameterName._regradeReason.canonical());
        }

    }

}
