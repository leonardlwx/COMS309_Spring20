package userManagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

	@Mock
	private UserRepository userRepoMock;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getUsersTest() {
		User user = new User("Varun", "salt", 0);
		User user2 = new User("NotVarun", "salt", 1);

		List<User> users = new ArrayList<>();
		users.add(user);
		users.add(user2);
		Iterable<User> userIt = users;

		when(userRepoMock.findAll()).thenReturn(userIt);
		assertEquals(users, userService.getUsers());
	}

	@Test
	void getUserTest() {
		String username = "Varun";
		User user = new User("Varun", "salt", 0);
		Optional<User> opt = Optional.of(user);

		when(userRepoMock.findById(username)).thenReturn(opt);
		assertEquals(user, userService.getUser(username));
	}

	@Test
	void getNumberOfUsedTicketsTest() {
		String username = "Varun";
		User user = new User("Varun", "salt", 0, 100, 98);
		Optional<User> opt = Optional.of(user);

		when(userRepoMock.findById(username)).thenReturn(opt);
		assertEquals(98, userService.getNumberOfUsedTickets(username));
	}

	@Test
	void getNumberOfTicketsTest() {
		String username = "Varun";
		User user = new User("Varun", "salt", 0, 100, 98);
		Optional<User> opt = Optional.of(user);

		when(userRepoMock.findById(username)).thenReturn(opt);
		assertEquals(100, userService.getNumberOfTickets(username));
	}
}
