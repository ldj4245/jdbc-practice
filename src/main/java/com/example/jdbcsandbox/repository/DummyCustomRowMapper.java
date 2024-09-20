package com.example.jdbcsandbox.repository;

import com.example.jdbcsandbox.domain.Dummy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DummyCustomRowMapper implements RowMapper<Dummy> {


    @Override
    public Dummy mapRow(ResultSet rs, int rowNum) throws SQLException{
        Dummy dummy = new Dummy();
        dummy.setId(rs.getInt("id"));
        dummy.setName(rs.getString("name"));
        return dummy;
    }


}
