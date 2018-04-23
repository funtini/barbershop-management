package bsmanagement.jparepositories.classtests;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import bsmanagement.model.Report;
import bsmanagement.model.jparepositories.ReportRepository;

public class ReportRepositoryClass implements ReportRepository{

	Set<Report> reports = new LinkedHashSet<>();
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Report arg0) {
		reports.remove(arg0);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Report> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(String arg0) {
		for (Report report : reports) {
            if (report.getId().equals(arg0)) {
                return true;
            }
        }
        return false;
	}

	@Override
	public Iterable<Report> findAll() {
		return new ArrayList<Report>(reports);
	}

	@Override
	public Iterable<Report> findAllById(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Report> findById(String arg0) {
		for (Report report : reports) {
            if (report.getId().equals(arg0)) {
                return Optional.of(report);
            }
        }
        return null;
	}

	@Override
	public <S extends Report> S save(S arg0) {
		reports.add(arg0);
		return arg0;
	}

	@Override
	public <S extends Report> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
