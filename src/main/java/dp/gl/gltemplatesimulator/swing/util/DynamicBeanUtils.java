package dp.gl.gltemplatesimulator.swing.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class DynamicBeanUtils {
    public static <T> T getPropertyValue(Object instance, PropertyDescriptor descriptor) {
        try {
            Method m = descriptor.getReadMethod();
            Object result = m.invoke(instance);
            return (T) result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}