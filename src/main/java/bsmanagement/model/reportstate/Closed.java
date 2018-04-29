package bsmanagement.model.reportstate;

import java.time.YearMonth;

import bsmanagement.model.Report;

public class Closed extends BaseState{
	
	Report report;
	
	public Closed(Report report)
	{
		this.report=report;
	}

	@Override
    public boolean isClosed()
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
		ReportState open = new Open(report);
		if (open.isValid())
			report.setReportState(open);		
		
	}

}
