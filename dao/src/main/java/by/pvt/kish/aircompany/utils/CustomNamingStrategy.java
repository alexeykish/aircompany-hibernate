package by.pvt.kish.aircompany.utils;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 * Realise DB naming strategy, where all tables starts with "T_" and all fields starts with "F_"
 *
 * @author Kish Alexey
 */
public class CustomNamingStrategy extends DefaultNamingStrategy {

    @Override
    public String classToTableName(String className) {
        return "T_" + super.classToTableName(className).toUpperCase();
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return "F_" + super.propertyToColumnName(propertyName).toUpperCase();
    }

    @Override
    public String tableName(String tableName) {
        return tableName;
    }

    @Override
    public String columnName(String columnName) {
        return columnName;
    }
}
