package friendManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
	private int id;

	@Autowired
	private FriendRepository friendRepo;

	public void deleteFriend(String username1, String username2) {
		FriendRelationEntity friend = new FriendRelationEntity(id, username1, username2);
		friendRepo.delete(friend);
	}

	public void addFriend(String username1, String username2) {
		try {
			id = friendRepo.findFirstByOrderByIdDesc().getId() + 1;
		}

		catch (NullPointerException e) {

		}

		FriendRelationEntity friend = new FriendRelationEntity(id, username1, username2);
		friendRepo.save(friend);
	}

	public List<String> getFriends(String username) {
		List<FriendRelationEntity> friendsEnt = new ArrayList<>();
		friendRepo.findByUsernameo(username).forEach(friendsEnt::add);
		friendRepo.findByUsernamet(username).forEach(friendsEnt::add);

		List<String> friends = new ArrayList<>();
		for (int i = 0; i < friendsEnt.size(); ++i) {
			if (friendsEnt.get(i).getUsernameo().equals(username))
				friends.add(friendsEnt.get(i).getUsernamet());

			else
				friends.add(friendsEnt.get(i).getUsernameo());
		}

		return friends;
	}
}
