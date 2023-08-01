package com.idstar.trainingkaryawan;

import com.idstar.trainingkaryawan.entity.DetailKaryawan;
import com.idstar.trainingkaryawan.entity.Karyawan;
import com.idstar.trainingkaryawan.repository.DetailKaryawanRepository;
import com.idstar.trainingkaryawan.repository.KaryawanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
class TrainingKaryawanApplicationTests {

	@Autowired
	private KaryawanRepository karyawanRepository;

	@Autowired
	private DetailKaryawanRepository detailKaryawanRepository;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	void testSaveKaryawan() throws ParseException {
		DetailKaryawan detailKaryawan = new DetailKaryawan();
		detailKaryawan.setNik("123");
		detailKaryawan.setNpwp("123");
		detailKaryawanRepository.save(detailKaryawan);

		Karyawan karyawan = new Karyawan();
		karyawan.setNama("Ivan");
		karyawan.setDob(dateFormat.parse("1999-12-11"));
		karyawan.setStatus("Single");
		karyawan.setAlamat("Jalan");
		karyawan.setDetailKaryawan(detailKaryawan);

		Karyawan save = karyawanRepository.save(karyawan);
		Assertions.assertNotNull(save);
	}

	@Test
	void testFind() {
		Karyawan karyawan = karyawanRepository.findById(1L).orElse(null);
		Assertions.assertNotNull(karyawan);
	}
}
