package com.delivery.utility;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class MyBatisDayOfWeekHandler extends BaseTypeHandler<DayOfWeek> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int idx,
                                    DayOfWeek parameter, JdbcType jdbcType) throws SQLException {
    
    }
    
    @Override
    public DayOfWeek getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        return DayOfWeek.valueOf(string);
    }
    
    @Override
    public DayOfWeek getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }
    
    @Override
    public DayOfWeek getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        return DayOfWeek.valueOf(string);
    }
}
