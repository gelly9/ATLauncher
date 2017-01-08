package me.gelli.atlauncher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

public class HomeActivity extends Activity {
    private static final int COLUMN_COUNT = 3;

    private HomeAppAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        me.gelli.atlauncher.HomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMN_COUNT));
        binding.recyclerView.addItemDecoration(new GridItemDecorator(getResources().getDimensionPixelSize(R.dimen.between_items), COLUMN_COUNT));
        adapter = new HomeAppAdapter(getActivityList(), new HomeAppAdapter.AppClickListener() {
            @Override
            public void onAppClicked(int position) {
                startActivity(getPackageManager().getLaunchIntentForPackage(adapter.getItemAt(position).getPackageName()));
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }
}
