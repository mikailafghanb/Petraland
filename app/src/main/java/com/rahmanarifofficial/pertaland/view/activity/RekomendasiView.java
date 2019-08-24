package com.rahmanarifofficial.pertaland.view.activity;

import com.rahmanarifofficial.pertaland.model.Rekomendasi;

public interface RekomendasiView {
    void showLoading();
    void hideLoading();
    void showRekomendasi(Rekomendasi rekomendasi);
    void failedShowingRekomendasi(String error);
}
