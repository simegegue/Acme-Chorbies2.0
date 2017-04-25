package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SendersRepository;
import domain.Senders;

@Service
@Transactional
public class SendersService {
	
	// Managed repository -----------------------------------------------------

		@Autowired
		private SendersRepository	sendersRepository;


		// Supporting services ----------------------------------------------------

		// Constructors -----------------------------------------------------------

		// Simple CRUD methods ----------------------------------------------------

		public Collection<Senders> findAll() {
			Collection<Senders> result;

			result = sendersRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public Senders findOne(int sendersId) {
			Assert.isTrue(sendersId != 0);

			Senders result;

			result = sendersRepository.findOne(sendersId);
			Assert.notNull(result);

			return result;
		}

		public void save(Senders senders) {
			Assert.notNull(senders);

			sendersRepository.save(senders);
		}

		public void delete(Senders senders) {
			Assert.notNull(senders);
			Assert.isTrue(senders.getId() != 0);
			Assert.isTrue(sendersRepository.exists(senders.getId()));

			sendersRepository.delete(senders);
		}

		// Other business methods -------------------------------------------------

}
