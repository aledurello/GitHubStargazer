package com.mariorossi.githubstargazer.core.model;

import java.io.Serializable;

public class Repository implements Serializable {

    private int id;
    private String name;
    private String full_name;
    private User owner;
    private boolean privateRepo;
    private String html_url;
    private String description;
    private boolean fork;
    private String url;
    private String forks_url;
    private String keys_url;
    private String collaborators_url;
    private String teams_url;
    private String hooks_url;
    private String issue_events_url;
    private String events_url;
    private String assignees_url;
    private String branches_url;
    private String tags_url;
    private String blobs_url;
    private String git_tags_url;
    private String git_refs_url;
    private String trees_url;
    private String statuses_url;
    private String languages_url;
    private String stargazers_url;
    private String contributors_url;
    private String subscribers_url;
    private String subscription_url;
    private String commits_url;
    private String git_commits_url;
    private String comments_url;
    private String issue_comment_url;
    private String contents_url;
    private String compare_url;
    private String merges_url;
    private String archive_url;
    private String downloads_url;
    private String issues_url;
    private String pulls_url;
    private String milestones_url;
    private String notifications_url;
    private String labels_url;
    private String releases_url;
    private String deployments_url;
    private String created_at;
    private String updated_at;
    private String pushed_at;
    private String git_url;
    private String ssh_url;
    private String clone_url;
    private String svn_url;
    private String homepage;
    private int size;
    private int stargazers_count;
    private int watchers_count;
    private String language;
    private boolean has_issues;
    private boolean has_downloads;
    private boolean has_wiki;
    private boolean has_pages;
    private int forks_count;
    private String mirror_url;
    private int open_issues_count;
    private int forks;
    private int open_issues;
    private int watchers;
    private String default_branch;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public User getOwner() {
        return owner;
    }

    public boolean isPrivateRepo() {
        return privateRepo;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFork() {
        return fork;
    }

    public String getUrl() {
        return url;
    }

    public String getForks_url() {
        return forks_url;
    }

    public String getKeys_url() {
        return keys_url;
    }

    public String getCollaborators_url() {
        return collaborators_url;
    }

    public String getTeams_url() {
        return teams_url;
    }

    public String getHooks_url() {
        return hooks_url;
    }

    public String getIssue_events_url() {
        return issue_events_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public String getAssignees_url() {
        return assignees_url;
    }

    public String getBranches_url() {
        return branches_url;
    }

    public String getTags_url() {
        return tags_url;
    }

    public String getBlobs_url() {
        return blobs_url;
    }

    public String getGit_tags_url() {
        return git_tags_url;
    }

    public String getGit_refs_url() {
        return git_refs_url;
    }

    public String getTrees_url() {
        return trees_url;
    }

    public String getStatuses_url() {
        return statuses_url;
    }

    public String getLanguages_url() {
        return languages_url;
    }

    public String getStargazers_url() {
        return stargazers_url;
    }

    public String getContributors_url() {
        return contributors_url;
    }

    public String getSubscribers_url() {
        return subscribers_url;
    }

    public String getSubscription_url() {
        return subscription_url;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public String getGit_commits_url() {
        return git_commits_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public String getIssue_comment_url() {
        return issue_comment_url;
    }

    public String getContents_url() {
        return contents_url;
    }

    public String getCompare_url() {
        return compare_url;
    }

    public String getMerges_url() {
        return merges_url;
    }

    public String getArchive_url() {
        return archive_url;
    }

    public String getDownloads_url() {
        return downloads_url;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public String getPulls_url() {
        return pulls_url;
    }

    public String getMilestones_url() {
        return milestones_url;
    }

    public String getNotifications_url() {
        return notifications_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public String getReleases_url() {
        return releases_url;
    }

    public String getDeployments_url() {
        return deployments_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public String getGit_url() {
        return git_url;
    }

    public String getSsh_url() {
        return ssh_url;
    }

    public String getClone_url() {
        return clone_url;
    }

    public String getSvn_url() {
        return svn_url;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getSize() {
        return size;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isHas_issues() {
        return has_issues;
    }

    public boolean isHas_downloads() {
        return has_downloads;
    }

    public boolean isHas_wiki() {
        return has_wiki;
    }

    public boolean isHas_pages() {
        return has_pages;
    }

    public int getForks_count() {
        return forks_count;
    }

    public String getMirror_url() {
        return mirror_url;
    }

    public int getOpen_issues_count() {
        return open_issues_count;
    }

    public int getForks() {
        return forks;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public int getWatchers() {
        return watchers;
    }

    public String getDefault_branch() {
        return default_branch;
    }
}
