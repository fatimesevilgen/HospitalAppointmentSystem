package com.appointmensystem.appointmentsystem.repository;

import com.appointmensystem.appointmentsystem.entities.Policlinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PoliclinicRepository extends JpaRepository<Policlinic, Long> {
}
