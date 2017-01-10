package me.gelli.atlauncher;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for the RecyclerView used on {@link HomeActivity}
 */
class HomeAppAdapter extends RecyclerView.Adapter<HomeAppAdapter.AppViewHolder> {
    /**
     * Handles click of the items in the adapter
     */
    interface AppClickListener {
        void onAppClicked(int position);
    }

    @NonNull
    private List<AppModel> list;
    private final AppClickListener listener;

    HomeAppAdapter(@NonNull AppClickListener clickListener) {
        this.list = new ArrayList<>();
        this.listener = clickListener;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_app, parent, false));
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        holder.getBinding().setVariable(me.gelli.atlauncher.BR.viewModel, new AppViewModel(list.get(position)));
        holder.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    AppModel getItemAt(int position) {
        return list.get(position);
    }

    void refresh(List<AppModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    static class AppViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        private AppViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        ViewDataBinding getBinding() {
            return binding;
        }

        void setOnClickListener(final AppClickListener listener) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAppClicked(getAdapterPosition());
                }
            });
        }
    }
}