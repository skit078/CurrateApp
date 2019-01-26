package com.serhiiv.test.currate.ui.main.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.serhiiv.test.currate.R;
import com.serhiiv.test.currate.core.entity.CurrencyPair;

import java.util.Set;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyPairAdapter extends ListAdapter<CurrencyPair, CurrencyPairAdapter.CurrencyPairViewHolder> {
    private static final DiffUtil.ItemCallback<CurrencyPair> DIFF_CALLBACK = new DiffUtil.ItemCallback<CurrencyPair>() {
        @Override
        public boolean areItemsTheSame(@NonNull CurrencyPair oldItem, @NonNull CurrencyPair newItem) {
            return oldItem.getPair().equals(newItem.getPair());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CurrencyPair oldItem, @NonNull CurrencyPair newItem) {
            return oldItem.equals(newItem);
        }
    };

    private final Set<CurrencyPair> checkedPairs = new ArraySet<>();

    @NonNull
    private final OnPairsCheckedChangeListener changeListener;

    public CurrencyPairAdapter(@NonNull OnPairsCheckedChangeListener changeListener) {
        super(DIFF_CALLBACK);
        this.changeListener = changeListener;
    }

    @NonNull
    @Override
    public CurrencyPairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_currency_pair, parent, false);
        return new CurrencyPairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyPairViewHolder holder, int position) {
        CurrencyPair item = getItem(position);
        holder.bind(item, checkedPairs.contains(item));
    }

    public interface OnPairsCheckedChangeListener {
        void onChange(Set<CurrencyPair> checkedPairs);
    }

    class CurrencyPairViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pair_name)
        TextView nameTextView;
        @BindView(R.id.pair_check_box)
        CheckBox checkBox;

        CurrencyPairViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CurrencyPair pair, boolean isChecked) {
            nameTextView.setText(pair.toString());
            checkBox.setChecked(isChecked);

            itemView.setOnClickListener(v -> {
                if (checkedPairs.contains(pair)) {
                    checkedPairs.remove(pair);
                } else {
                    checkedPairs.add(pair);
                }
                notifyItemChanged(getAdapterPosition());
            });
            changeListener.onChange(checkedPairs);
        }
    }
}
