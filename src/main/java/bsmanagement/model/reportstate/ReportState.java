package bsmanagement.model.reportstate;

public interface ReportState {
	
	boolean isValid();

    boolean isClosed();

    boolean isWaitingForApprovement();

    boolean isOpen();
    
    void changeTo();

}
