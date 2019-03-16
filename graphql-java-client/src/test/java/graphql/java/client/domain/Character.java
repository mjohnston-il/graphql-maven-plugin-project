package graphql.java.client.domain;

import java.util.List;

import graphql.java.client.ID;

/**
 * @author generated by graphql-maven-plugin
 */
public interface Character {

	public void setId(ID id);

	public ID getId();

	public void setName(String name);

	public String getName();

	public void setFriends(List<Character> friends);

	public List<Character> getFriends();

	public void setAppearsIn(List<Episode> appearsIn);

	public List<Episode> getAppearsIn();
}
