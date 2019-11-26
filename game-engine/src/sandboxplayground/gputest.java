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
		int SIZE = 1000;
		int[] count = new int[SIZE];
//		printArray(count);
		Kernel kernel = new Kernel() {
		     @Override
		     public void run() {
		       int gid = getGlobalId();//iteration counter
		       count[gid]=gid;
		     }
		};
		kernel.execute(SIZE);

	}

}
