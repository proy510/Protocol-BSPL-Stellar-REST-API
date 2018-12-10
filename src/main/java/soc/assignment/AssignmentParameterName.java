package soc.assignment;

public enum AssignmentParameterName {

	_aID("aID"),
	_submission("submission"),
	_extensionReason("extensionReason"),
	_extension("extension"),
	_tentativeGrade("tentativeGrade"),
	_grade("grade"),
	_regradeReason("regradeReason");

    private final java.lang.String canonical;

    private AssignmentParameterName(java.lang.String canonical) {
        this.canonical = canonical;
    }

    public java.lang.String canonical() {
        return canonical;
    }
}
