package graphql.java_client.examples.hello_world.domain;

import java.util.List;

/**
 * @author generated by graphql-maven-plugin
 */
public class Human implements Character {

	String id;
	String name;
	List<Character> friends;
	List<Episode> appearsIn;
	String homePlanet;

	 public void setId(String id) {
		this.id = id;
	}

	 public String getId() {
		return id;
	}
	
	 public void setName(String name) {
		this.name = name;
	}

	 public String getName() {
		return name;
	}
	
	 public void setFriends(List<Character> friends) {
		this.friends = friends;
	}

	 public List<Character> getFriends() {
		return friends;
	}
	
	 public void setAppearsIn(List<Episode> appearsIn) {
		this.appearsIn = appearsIn;
	}

	 public List<Episode> getAppearsIn() {
		return appearsIn;
	}
	
	 public void setHomePlanet(String homePlanet) {
		this.homePlanet = homePlanet;
	}

	 public String getHomePlanet() {
		return homePlanet;
	}
	
    public String toString() {
        return "Human {"
				+ "id: " + id
				+ ", "
				+ "name: " + name
				+ ", "
				+ "friends: " + friends
				+ ", "
				+ "appearsIn: " + appearsIn
				+ ", "
				+ "homePlanet: " + homePlanet
        		+ "}";
    }
}
