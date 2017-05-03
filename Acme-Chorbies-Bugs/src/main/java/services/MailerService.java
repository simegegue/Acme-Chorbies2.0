package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MailerRepository;
import domain.Mailer;

@Service
@Transactional
public class MailerService {
	
	// Managed repository -----------------------------------------------------

		@Autowired
		private MailerRepository	mailerRepository;


		// Supporting services ----------------------------------------------------

		// Constructors -----------------------------------------------------------

		// Simple CRUD methods ----------------------------------------------------

		public Collection<Mailer> findAll() {
			Collection<Mailer> result;

			result = mailerRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public Mailer findOne(int mailerId) {
			Assert.isTrue(mailerId != 0);

			Mailer result;

			result = mailerRepository.findOne(mailerId);
			Assert.notNull(result);

			return result;
		}

		public void save(Mailer mailer) {
			Assert.notNull(mailer);

			mailerRepository.save(mailer);
		}

		public void delete(Mailer mailer) {
			Assert.notNull(mailer);
			Assert.isTrue(mailer.getId() != 0);
			Assert.isTrue(mailerRepository.exists(mailer.getId()));

			mailerRepository.delete(mailer);
		}

		// Other business methods -------------------------------------------------

}
