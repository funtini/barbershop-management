package bsmanagement.model.reportstate;

public abstract class BaseState implements ReportState{
	
	protected final String reportStateName = this.getClass().getSimpleName();
	
	@Override
	public boolean isValid()
	{
		return false;
	}
	@Override
    public boolean isClosed()
    {
		return false;
	}
	@Override
    public boolean isWaitingForApprovement()
    {
		return false;
	}
	@Override
    public boolean isOpen()
    {
		return false;
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
}


}
