package soc.assignment;

import java.util.ArrayList;
import java.util.List;

public class AssignmentTProtocolSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLProtocolSchema {

    private static final AssignmentTProtocolSchema INSTANCE;

    static {
        INSTANCE = new AssignmentTProtocolSchema(new AssignmentTProtocolSchemaBuilder());
    }

    public static AssignmentTProtocolSchema instance() {
        return INSTANCE;
    }

    private AssignmentTProtocolSchema(AssignmentTProtocolSchemaBuilder builder) {
		super(builder.references);
    }

    private static class AssignmentTProtocolSchemaBuilder {

        public final List<uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema> references;

        public AssignmentTProtocolSchemaBuilder() {
            references = new ArrayList<>();
			references.add(PostSubmissionSchema.instance());
			references.add(PostGradeSchema.instance());
			references.add(AcceptGradeSchema.instance());
			references.add(RequestRegradeSchema.instance());
			references.add(RegradeSchema.instance());
        }

    }
}