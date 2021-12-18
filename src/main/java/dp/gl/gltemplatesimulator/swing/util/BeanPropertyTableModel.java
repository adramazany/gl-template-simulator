package dp.gl.gltemplatesimulator.swing.util;

import javax.swing.table.AbstractTableModel;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanPropertyTableModel<T> extends AbstractTableModel {
    // class of the data in rows
    private final Class beanClass;
    // collection of table rows
    private List<T> data = new ArrayList<T>();
    // collection of column property descriptors
    private List<PropertyDescriptor> columns = new ArrayList<PropertyDescriptor>();

    public BeanPropertyTableModel(Class beanClass) {
        if (beanClass == null) {
            throw new IllegalArgumentException("Bean class required, cannot be null");
        }
        this.beanClass = beanClass;
        populateColumns();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getPropertyType();
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column).getDisplayName();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PropertyDescriptor descriptor = columns.get(columnIndex);
        T bean = data.get(rowIndex);
        return DynamicBeanUtils.getPropertyValue(
                bean, descriptor);
    }

    private void populateColumns() {
        BeanInfo info = null;
        try {
            info = Introspector.getBeanInfo(beanClass);
        } catch (IntrospectionException ex) {
            throw new RuntimeException(
                    "Unable to introspect bean class", ex);
        }
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        columns.addAll(Arrays.asList(pds));
    }
}