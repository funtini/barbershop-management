package bsmanagement.model.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, String> {

}
