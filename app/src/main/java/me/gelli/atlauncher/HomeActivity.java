package me.gelli.atlauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Used to return a list of {@link ApplicationInfo} reflecting the installed applications on the system
     */
    @NonNull
    private List<AppModel> getActivityList() {
        List<AppModel> list = new ArrayList<>();
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(new Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER), 0);
        for (ResolveInfo resInfo : queryIntentActivities) {
            if (!resInfo.activityInfo.packageName.equals(BuildConfig.APPLICATION_ID)) {
                AppModel appModel = new AppModel();
                try {
                    String packageName = resInfo.activityInfo.packageName;
                    ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
                    appModel.setIcon(getPackageManager().getApplicationIcon(applicationInfo));
                    appModel.setLabel((String) getPackageManager().getApplicationLabel(applicationInfo));
                    appModel.setPackageName(packageName);
                    list.add(appModel);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
