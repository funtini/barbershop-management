package bsmanagement.model.reportstate;

import java.time.YearMonth;

import bsmanagement.model.Report;

public class Open extends BaseState{
	
	Report report;
	
	public Open(Report report)
	{
		this.report=report;
	}

	@Override
    public boolean isOpen()
    {
		return true;
	}

	@Override
	public boolean isValid()
	{
		return (!YearMonth.parse(report.getId()).isBefore(YearMonth.now()));
	}
	
	
	@Override
	public void changeTo() {
		ReportState waiting = new WaitingApprovement(report);
		if (waiting.isValid())
			report.setReportState(waiting);		
	}

}
