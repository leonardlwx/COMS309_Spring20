package friendManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendController {
	@Autowired
	private FriendService friendService;

	@RequestMapping("/friends/{username}")
	public List<String> getFriends(@PathVariable String username) {
		return friendService.getFriends(username);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/friends/{username}")
	public void addFriend(@PathVariable String username, @RequestBody String username2) {
		friendService.addFriend(username, username2);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/friends/{username}")
	public void deleteFriend(@PathVariable String username, @RequestBody String username2) {
		friendService.deleteFriend(username, username2);
	}
}
