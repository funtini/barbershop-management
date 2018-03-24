package bsmanagement.model.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer>{

}
