package bsmanagement.model.reportstate;

import java.time.YearMonth;

import bsmanagement.model.Report;

public class WaitingApprovement extends BaseState{

Report report;
	
	public WaitingApprovement(Report report)
	{
		this.report=report;
	}

	@Override
    public boolean isWaitingForApprovement()
    {
		return true;
	}

	@Override
	public boolean isValid()
	{
		return (YearMonth.parse(report.getId()).isBefore(YearMonth.now()));
	}
	
	
	@Override
	public void changeTo() {
		ReportState closed = new Closed(report);
		if (closed.isValid())
			report.setReportState(closed);		
	}

}
