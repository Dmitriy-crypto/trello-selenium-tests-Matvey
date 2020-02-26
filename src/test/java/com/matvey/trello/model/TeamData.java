package com.matvey.trello.model;

public class TeamData {
    private  String teamName;
    private  String teamDescription;


    public String getTeamName() {
        return teamName;
    }

    public String getTeamDescription() {
        return teamDescription;
    }



    public TeamData withTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public TeamData withTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
        return this;
    }


}
