package com.kingxunlian.message.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@MappedJdbcTypes(value = JdbcType.BINARY, includeNullJdbcType = true)
@MappedTypes(value = UUID.class)
public class UUIDTypeHandler extends BaseTypeHandler<UUID> {
    @Override
    public void setNonNullParameter(final PreparedStatement preparedStatement, final int i, final UUID uuid, final JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, uuid.toString());
    }

    @Override
    public UUID getNullableResult(final ResultSet resultSet, final String s) throws SQLException {
        final String res = resultSet.getString(s);
        return res == null ? null : UUID.fromString(res);
    }

    @Override
    public UUID getNullableResult(final ResultSet resultSet, final int i) throws SQLException {
        final String res = resultSet.getString(i);
        return res == null ? null : UUID.fromString(res);
    }

    @Override
    public UUID getNullableResult(final CallableStatement callableStatement, final int i) throws SQLException {
        final String res = callableStatement.getString(i);
        return res == null ? null : UUID.fromString(res);
    }

}
