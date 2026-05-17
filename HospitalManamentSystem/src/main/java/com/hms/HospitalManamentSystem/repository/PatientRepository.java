package com.hms.HospitalManamentSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.HospitalManamentSystem.dto.BloodGroupCountResponseEntity;
import com.hms.HospitalManamentSystem.entity.Patient;
import com.hms.HospitalManamentSystem.entity.type.BloodGroupType;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByName(String name);
	
	List<Patient>  findByBirthDateOrEmail(LocalDate bithDate,String email);
	
	List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
	
	List<Patient> findByNameContainingOrderByIdDesc(String query);
	
	@Query("SELECT p FROM Patient p where  p.bloodGroup=?1")
	List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);
	
	@Query("select p from Patient p where p.birthDate > :birthDate")
	List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);
	
	@Query("select new com.codingshuttle.youtube.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup, COUNT(p)) from Patient p group by p.bloodGroup")
	List<BloodGroupCountResponseEntity> countEachBloodGroupType();
	
	@Query(value="select * from patient",nativeQuery=true)
	Page<Patient> findAllPatients(Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("UPDATE Patient p Set p.name=:name where p.id=:id")
	int updateNameWithId(@Param("name") String name ,@Param("id") Long id);
	
	
	@Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
	List<Patient> findAllPatientWithAppointment();
	
}
