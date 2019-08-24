package com.rahmanarifofficial.pertaland.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmanarifofficial.pertaland.R;
import com.rahmanarifofficial.pertaland.model.Aset;
import com.rahmanarifofficial.pertaland.util.Formatter;
import com.rahmanarifofficial.pertaland.util.Globe_Variable;
import com.rahmanarifofficial.pertaland.view.activity.AfterPublishActivity;
import com.rahmanarifofficial.pertaland.view.activity.DetailAsetActivity;

import java.util.List;

public class AsetListAdapter extends RecyclerView.Adapter<AsetListAdapter.ItemViewHolder> {

    private Context context;
    private List<Aset> asets;
    private String type;

    public AsetListAdapter(Context context, List<Aset> asets, String type) {
        this.context = context;
        this.asets = asets;
        this.type = type;
    }

    @NonNull
    @Override
    public AsetListAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_aset, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AsetListAdapter.ItemViewHolder holder, int position) {
        final Aset aset = asets.get(position);
        holder.tvNamaBidang.setText(aset.getNamaBidang());
        holder.tvLokasi.setText(aset.getLokasi());
        holder.tvLuas.setText(Formatter.areaFormatter(aset.getLuasBidang()));
        if (type.equalsIgnoreCase(Globe_Variable.ANALISIS_CLASS)) {
            holder.cvAset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, DetailAsetActivity.class)
                            .putExtra(Globe_Variable.ID_ASET, aset.getIdAset()));
                }
            });
            holder.btnAction.setVisibility(View.GONE);
        } else {
            holder.btnAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, AfterPublishActivity.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return asets.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNamaBidang, tvLuas, tvLokasi;
        private CardView cvAset;
        private Button btnAction;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaBidang = itemView.findViewById(R.id.tv_item_nama_bidang);
            tvLuas = itemView.findViewById(R.id.tv_item_luas_bidang);
            tvLokasi = itemView.findViewById(R.id.tv_item_lokasi_bidang);
            btnAction = itemView.findViewById(R.id.btn_item_action);
            cvAset = itemView.findViewById(R.id.cv_aset);
        }
    }
}
