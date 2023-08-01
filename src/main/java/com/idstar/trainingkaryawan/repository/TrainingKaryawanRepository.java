package com.idstar.trainingkaryawan.repository;

import com.idstar.trainingkaryawan.entity.TrainingKaryawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingKaryawanRepository extends JpaRepository<TrainingKaryawan, Long> {
}
