/**
 * MIT License
 *
 * Copyright (c) 2010-2019 The OSHI project team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package oshi.hardware;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import oshi.SystemInfo;

/**
 * Test USB device
 */
public class UsbDeviceTest {
    /**
     * Test USB Devices
     */
    @Test
    public void testUsbDevices() {
        SystemInfo si = new SystemInfo();
        UsbDevice[] usbArray = si.getHardware().getUsbDevices(true);
        for (UsbDevice usb : usbArray) {
            assertNotNull(usb);
            testUsbRecursive(usb);
        }
    }

    private void testUsbRecursive(UsbDevice usb) {
        assertTrue(usb.getName().length() > 0);
        assertNotNull(usb.getVendor());
        assertNotNull(usb.getProductId());
        assertNotNull(usb.getVendorId());
        assertNotNull(usb.getSerialNumber());

        for (UsbDevice nested : usb.getConnectedDevices()) {
            testUsbRecursive(nested);
        }
    }

}
