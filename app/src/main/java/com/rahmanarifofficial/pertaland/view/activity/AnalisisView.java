package com.rahmanarifofficial.pertaland.view.activity;

import com.rahmanarifofficial.pertaland.model.Aset;

public interface AnalisisView {
    void showLoading();
    void hideLoading();
    void showAsets(Aset aset);
    void failedShowingAsets(String error);
}
