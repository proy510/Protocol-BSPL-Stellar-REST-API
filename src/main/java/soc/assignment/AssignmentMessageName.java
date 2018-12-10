package soc.assignment;

public enum AssignmentMessageName {

	_postAssignment("postAssignment"),
	_postSubmission("postSubmission"),
	_requestExtension("requestExtension"),
	_extension("extension"),
	_postGrade("postGrade"),
	_acceptGrade("acceptGrade"),
	_requestRegrade("requestRegrade"),
	_regrade("regrade");

    private final java.lang.String canonical;

    private AssignmentMessageName(java.lang.String canonical) {
        this.canonical = canonical;
    }

    public java.lang.String canonical() {
        return canonical;
    }
}
