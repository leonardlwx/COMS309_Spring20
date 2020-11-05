package friendManagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FriendServiceTest {
	@Mock
	FriendRepository friendRepoMock;

	@InjectMocks
	FriendService friendService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getFriendsTest() {
		String username1 = "abc";
		String username2 = "xyz";
		String username3 = "pqr";

		List<FriendRelationEntity> friendship1 = new ArrayList<>();
		friendship1.add(new FriendRelationEntity(0, username1, username2));

		List<FriendRelationEntity> friendship2 = new ArrayList<>();
		friendship2.add(new FriendRelationEntity(0, username2, username3));

		List<String> friends = new ArrayList<>();
		friends.add(username1);
		friends.add(username3);

		when(friendRepoMock.findByUsernameo(username2)).thenReturn(friendship1);
		when(friendRepoMock.findByUsernamet(username2)).thenReturn(friendship2);
		assertEquals(friends, friendService.getFriends(username2));
	}

}
