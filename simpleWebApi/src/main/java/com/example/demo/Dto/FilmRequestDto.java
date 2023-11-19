package com.example.demo.Dto;

import java.util.List;

public class FilmRequestDto {
    private String title;
    private String description;
    private Integer releaseYear;
    private String languageName;
    private List<String> actorFullNames;
    private List<String> categoryNames;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public List<String> getActorFullNames() {
        return actorFullNames;
    }

    public void setActorFullNames(List<String> actorFullNames) {
        this.actorFullNames = actorFullNames;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }
}
