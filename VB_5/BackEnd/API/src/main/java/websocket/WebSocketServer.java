package websocket;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {
	// Store all socket session and their corresponding username
	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> usernameSessionMap = new Hashtable<>();

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);
	}

	@OnMessage
	public void onMessage(Session session, String info) throws IOException {
		String message;
		String username = sessionUsernameMap.get(session);

		String[] inf = info.split("_");

		switch (Integer.parseInt(inf[0])) {
		case 0:
			return;
		// message = "";
		// break;

		case 1:
			return;
		// message = "";
		// break;

		case 2:
			message = username + " has unlocked a new " + inf[1] + " " + inf[2] + "! They have named it " + inf[3]
					+ ".";
			break;

		case 3:
			message = username + " has just studied for " + inf[1] + ". This is their longest study seesion yet!";
			break;

		case 4:
			message = username + " has just studied for " + inf[1] + "!";
			break;

		case 5:
			message = username + ": " + inf[1];
			break;

		default:
			message = "ERROR";
		}

		broadcast(message);
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		String username = sessionUsernameMap.get(session);
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);
	}

	private void broadcast(String message) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			}

			catch (IOException e) {

			}
		});
	}
}
