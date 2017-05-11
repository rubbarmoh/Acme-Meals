package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ReporterRepository;
import domain.Reporter;

@Service
@Transactional
public class ReporterService {
	//Managed repository--------------------------------
		@Autowired
		private ReporterRepository reporterRepository;
		//Supporting Services-------------------------------
		
		//Constructor---------------------------------------
		public ReporterService(){
			super();
		}
		

		// Simple CRUD methods ----------------------------------------------------

		public Collection<Reporter> findAll() {
			Collection<Reporter> result;
			result = reporterRepository.findAll();

			return result;
		}

		public Reporter findOne(int reporterId) {
			Reporter result;
			result = reporterRepository.findOne(reporterId);

			return result;
		}

		public Reporter save(Reporter reporter) {
			return reporterRepository.save(reporter);
		}

		public void delete(Reporter reporter) {
			reporterRepository.delete(reporter);
		}

		// Other bussines methods ---------------------------
		
		
}
