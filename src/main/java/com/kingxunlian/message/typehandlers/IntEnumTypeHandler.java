package com.kingxunlian.message.typehandlers;

import com.kingxunlian.common.IntEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = IntEnum.class)
public class IntEnumTypeHandler extends BaseTypeHandler<IntEnum> {

    private final Class<IntEnum> type;
    private final IntEnum[] enums;


    public IntEnumTypeHandler(final Class<IntEnum> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
    }

    @Override
    public void setNonNullParameter(final PreparedStatement preparedStatement,
                                    final int i,
                                    final IntEnum intEnum,
                                    final JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, intEnum.getIntValue());

    }

    @Override
    public IntEnum getNullableResult(final ResultSet resultSet, final String s) throws SQLException {
        final int key = resultSet.getInt(s);

        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位IEnum子类
            return locateIEnum(key);
        }
    }

    @Override
    public IntEnum getNullableResult(final ResultSet resultSet, final int i) throws SQLException {
        final int key = resultSet.getInt(i);

        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位IEnum子类
            return locateIEnum(key);
        }
    }

    @Override
    public IntEnum getNullableResult(final CallableStatement callableStatement, final int i) throws SQLException {
        final int key = callableStatement.getInt(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位IEnum子类
            return locateIEnum(key);
        }
    }

    private IntEnum locateIEnum(final int key) {
        for (final IntEnum status : enums) {
            if (status.getIntValue() == key) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + key + ",请核对" + type.getSimpleName());
    }
}
