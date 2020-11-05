package userManagement;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class UserTest {
	@Test
	void addTicketsTest() {
		User user = new User("Varun", "salt", 0, 100, 98);
		user.addTickets(100);

		assertEquals(200, user.getTickets());
		assertEquals(98, user.getUsedTickets());
	}

	@Test
	void removeTicketTest() {
		User user = new User("Varun", "salt", 0, 100, 98);
		user.removeTicket();

		assertEquals(99, user.getTickets());
		assertEquals(99, user.getUsedTickets());
	}
}
