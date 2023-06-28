package com.example.untiled1.domain.tutorial;

import com.example.untiled1.domain.tutorial.request.TutorialRq;
import com.example.untiled1.domain.tutorial.response.TutorialRp;

import java.util.List;

public interface TutorialRepository {
    public List<TutorialRp> getListTutorials ();
    public TutorialRp addTutorial (TutorialRq tutorialRq);
}
