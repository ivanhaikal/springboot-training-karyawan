package com.idstar.trainingkaryawan.repository;

import com.idstar.trainingkaryawan.entity.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeningRepository extends JpaRepository<Rekening, Long> {
}
