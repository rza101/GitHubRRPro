package com.rhezarijaya.githubrrpro.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class UserDetail implements Parcelable{

	@SerializedName("gists_url")
	private String gistsUrl;

	@SerializedName("repos_url")
	private String reposUrl;

	@SerializedName("following_url")
	private String followingUrl;

	@SerializedName("twitter_username")
	private String twitterUsername;

	@SerializedName("bio")
	private String bio;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("login")
	private String login;

	@SerializedName("type")
	private String type;

	@SerializedName("blog")
	private String blog;

	@SerializedName("subscriptions_url")
	private String subscriptionsUrl;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("site_admin")
	private boolean siteAdmin;

	@SerializedName("score")
	private double score;

	@SerializedName("company")
	private String company;

	@SerializedName("id")
	private int id;

	@SerializedName("public_repos")
	private int publicRepos;

	@SerializedName("gravatar_id")
	private String gravatarId;

	@SerializedName("email")
	private String email;

	@SerializedName("organizations_url")
	private String organizationsUrl;

	@SerializedName("hireable")
	private String hireable;

	@SerializedName("starred_url")
	private String starredUrl;

	@SerializedName("followers_url")
	private String followersUrl;

	@SerializedName("public_gists")
	private int publicGists;

	@SerializedName("url")
	private String url;

	@SerializedName("received_events_url")
	private String receivedEventsUrl;

	@SerializedName("followers")
	private int followers;

	@SerializedName("avatar_url")
	private String avatarUrl;

	@SerializedName("events_url")
	private String eventsUrl;

	@SerializedName("html_url")
	private String htmlUrl;

	@SerializedName("following")
	private int following;

	@SerializedName("name")
	private String name;

	@SerializedName("location")
	private String location;

	@SerializedName("node_id")
	private String nodeId;

	protected UserDetail(Parcel in) {
		gistsUrl = in.readString();
		reposUrl = in.readString();
		followingUrl = in.readString();
		twitterUsername = in.readString();
		bio = in.readString();
		createdAt = in.readString();
		login = in.readString();
		type = in.readString();
		blog = in.readString();
		subscriptionsUrl = in.readString();
		updatedAt = in.readString();
		siteAdmin = in.readByte() != 0;
		score = in.readDouble();
		company = in.readString();
		id = in.readInt();
		publicRepos = in.readInt();
		gravatarId = in.readString();
		email = in.readString();
		organizationsUrl = in.readString();
		hireable = in.readString();
		starredUrl = in.readString();
		followersUrl = in.readString();
		publicGists = in.readInt();
		url = in.readString();
		receivedEventsUrl = in.readString();
		followers = in.readInt();
		avatarUrl = in.readString();
		eventsUrl = in.readString();
		htmlUrl = in.readString();
		following = in.readInt();
		name = in.readString();
		location = in.readString();
		nodeId = in.readString();
	}

	public static final Creator<UserDetail> CREATOR = new Creator<UserDetail>() {
		@Override
		public UserDetail createFromParcel(Parcel in) {
			return new UserDetail(in);
		}

		@Override
		public UserDetail[] newArray(int size) {
			return new UserDetail[size];
		}
	};

	public String getGistsUrl(){
		return gistsUrl;
	}

	public String getReposUrl(){
		return reposUrl;
	}

	public String getFollowingUrl(){
		return followingUrl;
	}

	public String getTwitterUsername(){
		return twitterUsername;
	}

	public String getBio(){
		return bio;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getLogin(){
		return login;
	}

	public String getType(){
		return type;
	}

	public String getBlog(){
		return blog;
	}

	public String getSubscriptionsUrl(){
		return subscriptionsUrl;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public boolean isSiteAdmin(){
		return siteAdmin;
	}

	public double getScore() {
		return score;
	}

	public String getCompany(){
		return company;
	}

	public int getId(){
		return id;
	}

	public int getPublicRepos(){
		return publicRepos;
	}

	public String getGravatarId(){
		return gravatarId;
	}

	public String getEmail(){
		return email;
	}

	public String getOrganizationsUrl(){
		return organizationsUrl;
	}

	public String getHireable(){
		return hireable;
	}

	public String getStarredUrl(){
		return starredUrl;
	}

	public String getFollowersUrl(){
		return followersUrl;
	}

	public int getPublicGists(){
		return publicGists;
	}

	public String getUrl(){
		return url;
	}

	public String getReceivedEventsUrl(){
		return receivedEventsUrl;
	}

	public int getFollowers(){
		return followers;
	}

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public String getEventsUrl(){
		return eventsUrl;
	}

	public String getHtmlUrl(){
		return htmlUrl;
	}

	public int getFollowing(){
		return following;
	}

	public String getName(){
		return name;
	}

	public String getLocation(){
		return location;
	}

	public String getNodeId(){
		return nodeId;
	}

	@NonNull
	@Override
	public String toString() {
		return "UserInfo{" +
				"gistsUrl='" + gistsUrl + '\'' +
				", reposUrl='" + reposUrl + '\'' +
				", followingUrl='" + followingUrl + '\'' +
				", twitterUsername='" + twitterUsername + '\'' +
				", bio='" + bio + '\'' +
				", createdAt='" + createdAt + '\'' +
				", login='" + login + '\'' +
				", type='" + type + '\'' +
				", blog='" + blog + '\'' +
				", subscriptionsUrl='" + subscriptionsUrl + '\'' +
				", updatedAt='" + updatedAt + '\'' +
				", siteAdmin=" + siteAdmin +
				", score=" + score +
				", company='" + company + '\'' +
				", id=" + id +
				", publicRepos=" + publicRepos +
				", gravatarId='" + gravatarId + '\'' +
				", email='" + email + '\'' +
				", organizationsUrl='" + organizationsUrl + '\'' +
				", hireable='" + hireable + '\'' +
				", starredUrl='" + starredUrl + '\'' +
				", followersUrl='" + followersUrl + '\'' +
				", publicGists=" + publicGists +
				", url='" + url + '\'' +
				", receivedEventsUrl='" + receivedEventsUrl + '\'' +
				", followers=" + followers +
				", avatarUrl='" + avatarUrl + '\'' +
				", eventsUrl='" + eventsUrl + '\'' +
				", htmlUrl='" + htmlUrl + '\'' +
				", following=" + following +
				", name='" + name + '\'' +
				", location='" + location + '\'' +
				", nodeId='" + nodeId + '\'' +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(gistsUrl);
		dest.writeString(reposUrl);
		dest.writeString(followingUrl);
		dest.writeString(twitterUsername);
		dest.writeString(bio);
		dest.writeString(createdAt);
		dest.writeString(login);
		dest.writeString(type);
		dest.writeString(blog);
		dest.writeString(subscriptionsUrl);
		dest.writeString(updatedAt);
		dest.writeByte((byte) (siteAdmin ? 1 : 0));
		dest.writeDouble(score);
		dest.writeString(company);
		dest.writeInt(id);
		dest.writeInt(publicRepos);
		dest.writeString(gravatarId);
		dest.writeString(email);
		dest.writeString(organizationsUrl);
		dest.writeString(hireable);
		dest.writeString(starredUrl);
		dest.writeString(followersUrl);
		dest.writeInt(publicGists);
		dest.writeString(url);
		dest.writeString(receivedEventsUrl);
		dest.writeInt(followers);
		dest.writeString(avatarUrl);
		dest.writeString(eventsUrl);
		dest.writeString(htmlUrl);
		dest.writeInt(following);
		dest.writeString(name);
		dest.writeString(location);
		dest.writeString(nodeId);
	}
}