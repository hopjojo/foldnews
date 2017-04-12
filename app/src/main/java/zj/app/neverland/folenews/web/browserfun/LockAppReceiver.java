package zj.app.neverland.folenews.web.browserfun;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class LockAppReceiver extends BroadcastReceiver {

	private LockAppInterface lockAppInterface;
	
	public static final int NEEDLOCK = 1;
	public static final int UN_NEEDLOCK = 2;
	public static final int LOCK = 3;
	
	public interface LockAppInterface {
		void onLockApp(int lockType);
	}

	public LockAppInterface getLockAppInterface() {
		return lockAppInterface;
	}

	public void setLockAppInterface(LockAppInterface lockAppInterface) {
		this.lockAppInterface = lockAppInterface;
	}

	/**
	 * 1(NEEDLOCK) 需要锁屏 2(UN_NEEDLOCK) 不需要锁屏 3(LOCK) 直接锁屏
	 */
	public static final String ACTION_LOCK_APP = "com.mos.base.action.LOCKAPP";

	@Override
	public void onReceive(Context context, Intent intent) {
		int needLockApp = intent.getIntExtra(ACTION_LOCK_APP, 0);
		if (lockAppInterface != null) {
			lockAppInterface.onLockApp(needLockApp);
		}
	}

	private void registReceiver(Activity activity) {
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION_LOCK_APP);
		activity.registerReceiver(this, filter);
	}
	
	public static void regist(Activity activity,LockAppInterface lockAppInterface){
		LockAppReceiver lockAppReceiver = new LockAppReceiver();
		lockAppReceiver.setLockAppInterface(lockAppInterface);
		lockAppReceiver.registReceiver(activity);
	}
}
