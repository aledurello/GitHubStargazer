package com.mariorossi.githubstargazer.core.model;

import java.io.Serializable;

public class User implements Serializable {

    private String login;
    private int id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (site_admin != user.site_admin) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (avatar_url != null ? !avatar_url.equals(user.avatar_url) : user.avatar_url != null)
            return false;
        if (gravatar_id != null ? !gravatar_id.equals(user.gravatar_id) : user.gravatar_id != null)
            return false;
        if (url != null ? !url.equals(user.url) : user.url != null) return false;
        if (html_url != null ? !html_url.equals(user.html_url) : user.html_url != null)
            return false;
        if (followers_url != null ? !followers_url.equals(user.followers_url) : user.followers_url != null)
            return false;
        if (following_url != null ? !following_url.equals(user.following_url) : user.following_url != null)
            return false;
        if (gists_url != null ? !gists_url.equals(user.gists_url) : user.gists_url != null)
            return false;
        if (starred_url != null ? !starred_url.equals(user.starred_url) : user.starred_url != null)
            return false;
        if (subscriptions_url != null ? !subscriptions_url.equals(user.subscriptions_url) : user.subscriptions_url != null)
            return false;
        if (organizations_url != null ? !organizations_url.equals(user.organizations_url) : user.organizations_url != null)
            return false;
        if (repos_url != null ? !repos_url.equals(user.repos_url) : user.repos_url != null)
            return false;
        if (events_url != null ? !events_url.equals(user.events_url) : user.events_url != null)
            return false;
        if (received_events_url != null ? !received_events_url.equals(user.received_events_url) : user.received_events_url != null)
            return false;
        return type != null ? type.equals(user.type) : user.type == null;

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (avatar_url != null ? avatar_url.hashCode() : 0);
        result = 31 * result + (gravatar_id != null ? gravatar_id.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (html_url != null ? html_url.hashCode() : 0);
        result = 31 * result + (followers_url != null ? followers_url.hashCode() : 0);
        result = 31 * result + (following_url != null ? following_url.hashCode() : 0);
        result = 31 * result + (gists_url != null ? gists_url.hashCode() : 0);
        result = 31 * result + (starred_url != null ? starred_url.hashCode() : 0);
        result = 31 * result + (subscriptions_url != null ? subscriptions_url.hashCode() : 0);
        result = 31 * result + (organizations_url != null ? organizations_url.hashCode() : 0);
        result = 31 * result + (repos_url != null ? repos_url.hashCode() : 0);
        result = 31 * result + (events_url != null ? events_url.hashCode() : 0);
        result = 31 * result + (received_events_url != null ? received_events_url.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (site_admin ? 1 : 0);
        return result;
    }
}
