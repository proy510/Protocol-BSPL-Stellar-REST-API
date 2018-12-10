package soc.assignment;

import java.util.ArrayList;
import java.util.List;

public class AssignmentIProtocolSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLProtocolSchema {

    private static final AssignmentIProtocolSchema INSTANCE;

    static {
        INSTANCE = new AssignmentIProtocolSchema(new AssignmentIProtocolSchemaBuilder());
    }

    public static AssignmentIProtocolSchema instance() {
        return INSTANCE;
    }

    private AssignmentIProtocolSchema(AssignmentIProtocolSchemaBuilder builder) {
		super(builder.references);
    }

    private static class AssignmentIProtocolSchemaBuilder {

        public final List<uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema> references;

        public AssignmentIProtocolSchemaBuilder() {
            references = new ArrayList<>();
			references.add(PostAssignmentSchema.instance());
			references.add(RequestExtensionSchema.instance());
			references.add(ExtensionSchema.instance());
        }

    }
}