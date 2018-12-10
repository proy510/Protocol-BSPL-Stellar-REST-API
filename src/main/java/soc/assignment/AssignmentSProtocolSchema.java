package soc.assignment;

import java.util.ArrayList;
import java.util.List;

public class AssignmentSProtocolSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLProtocolSchema {

    private static final AssignmentSProtocolSchema INSTANCE;

    static {
        INSTANCE = new AssignmentSProtocolSchema(new AssignmentSProtocolSchemaBuilder());
    }

    public static AssignmentSProtocolSchema instance() {
        return INSTANCE;
    }

    private AssignmentSProtocolSchema(AssignmentSProtocolSchemaBuilder builder) {
		super(builder.references);
    }

    private static class AssignmentSProtocolSchemaBuilder {

        public final List<uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema> references;

        public AssignmentSProtocolSchemaBuilder() {
            references = new ArrayList<>();
			references.add(PostAssignmentSchema.instance());
			references.add(PostSubmissionSchema.instance());
			references.add(RequestExtensionSchema.instance());
			references.add(ExtensionSchema.instance());
			references.add(PostGradeSchema.instance());
			references.add(AcceptGradeSchema.instance());
			references.add(RequestRegradeSchema.instance());
			references.add(RegradeSchema.instance());
        }

    }
}