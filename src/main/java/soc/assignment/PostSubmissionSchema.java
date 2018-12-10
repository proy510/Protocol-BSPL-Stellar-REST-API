package soc.assignment;

public class PostSubmissionSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final PostSubmissionSchema INSTANCE;

    static {
        INSTANCE = new PostSubmissionSchema(new PostSubmissionSchemaBuilder());
    }

    public static PostSubmissionSchema instance() {
        return INSTANCE;
    }

    private PostSubmissionSchema(PostSubmissionSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class PostSubmissionSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public PostSubmissionSchemaBuilder() {
            name = AssignmentMessageName._postSubmission.canonical();
            senderRole = AssignmentRoleName._S.canonical();
            receiverRole = AssignmentRoleName._T.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(AssignmentParameterName._aID.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(AssignmentParameterName._aID.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(AssignmentParameterName._submission.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
