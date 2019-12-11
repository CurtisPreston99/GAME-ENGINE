package sandboxplayground;

import com.aparapi.Kernel;
import com.aparapi.device.Device;
import com.aparapi.internal.kernel.KernelManager;
import com.aparapi.internal.kernel.KernelPreferences;

public class gputest {

	public static void main(String[] args) {
		KernelPreferences preferences = KernelManager.instance().getDefaultPreferences();
		System.out.println("-- Devices in preferred order --");
		for (Device device : preferences.getPreferredDevices(null)) {
			System.out.println("----------");
			System.out.println(device);
		}
//		
		int SIZE = 100;
		int[] count = new int[SIZE];
		int[] in = new int[SIZE];
//		printArray(count);
		new Kernel() {
			@Override
			public void run() {
				int gid = getGlobalId();// iteration counter
				in[gid] = (int) (sin(gid) * 100);
			}
		}.execute(SIZE);

		for (int i : in) {
			System.out.println(i);
		}
		System.out.println("-------------------");
		Kernel kernel = new Kernel() {
			@Override
			public void run() {
				int gid = getGlobalId();// iteration counter
				if (gid == 0) {
					count[gid] = in[gid];

				} else {
					count[gid] = in[gid - 1];

				}
			}
		};
		kernel.execute(SIZE);

		for (int i : count) {
			System.out.println(i);
		}

	}

}
