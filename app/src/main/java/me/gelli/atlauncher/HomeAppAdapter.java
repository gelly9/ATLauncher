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

class HomeAppAdapter extends RecyclerView.Adapter<HomeAppAdapter.AppViewHolder> {
    interface AppClickListener {
        void onAppClicked(int position);
    }
    @NonNull
    private List<AppModel> list;
    private AppClickListener listener;

    HomeAppAdapter(@NonNull List<AppModel> activityList, AppClickListener clickListener) {
        this.list = new ArrayList<>();
        this.list.addAll(activityList);
        this.list.addAll(activityList);
        this.list.addAll(activityList);
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