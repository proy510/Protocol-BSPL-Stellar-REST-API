package soc.assignment;

public enum AssignmentRoleName {

	_I("I"),
	_T("T"),
	_S("S");

    private final java.lang.String canonical;

    private AssignmentRoleName(java.lang.String canonical) {
        this.canonical = canonical;
    }

    public java.lang.String canonical() {
        return canonical;
    }
}
