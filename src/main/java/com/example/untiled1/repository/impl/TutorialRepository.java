package com.example.untiled1.repository.impl;

import com.example.untiled1.request.TutorialRq;
import com.example.untiled1.response.TutorialRp;

import java.util.List;

public interface TutorialRepository {
    public List<TutorialRp> getListTutorials ();
    public TutorialRp addTutorial (TutorialRq tutorialRq);
}
