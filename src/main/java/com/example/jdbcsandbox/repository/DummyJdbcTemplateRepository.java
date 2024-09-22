package com.example.jdbcsandbox.repository;

import com.example.jdbcsandbox.domain.Dummy;
import org.checkerframework.checker.units.qual.N;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public class DummyJdbcTemplateRepository implements DummyRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate writeTransactionOperations;
    private final TransactionTemplate readTransactionOperations;


    public DummyJdbcTemplateRepository(DataSource dataSource,
                                       TransactionTemplate writeTransactionOperations,
                                       TransactionTemplate readTransactionOperations){

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.writeTransactionOperations = writeTransactionOperations;
        this.readTransactionOperations = readTransactionOperations;
    }

    @Override
    public Dummy save(Dummy dummy) {

        //save 경우 write 이므로 writeTransaction 사용
        return writeTransactionOperations.execute(status ->{

            String sql = "INSERT INTO DUMMY VALUES (?,?)";
            jdbcTemplate.update(sql, LocalDateTime.now().getNano(),dummy.getName());

            return dummy;


        });

    }

    @Override
    public Optional<Dummy> findByName(String name){
        return readTransactionOperations.execute(status -> {
            String sql = "SELECT * FROM DUMMY WHERE name = ?";
            List<Dummy>dummies = jdbcTemplate.query(sql,(ResultSet rs, int rowNum) ->{
                Dummy dummy = new Dummy();
                dummy.setId(rs.getInt("id"));
                dummy.setName(rs.getString("name"));
                return dummy;
            }, name);

            return Optional.ofNullable(dummies.get(0));
        });
    }

//    @Override
//    public Optional<Dummy> findByName(String name){
//        String sql = "SELECT * FROM DUMMY WHERE name =?";
//        List<Dummy> dummies = jdbcTemplate.query(sql, new RowMapper<Dummy>() {
//            @Override
//            public Dummy mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Dummy dummy = new Dummy();
//
//                dummy.setId(rs.getInt("id"));
//                dummy.setName(rs.getString("name"));
//
//
//                return dummy;
//
//            }
//        },name);
//
//        return Optional.ofNullable(dummies.get(0));
//    }

//    @Override
//    public Optional<Dummy> findByName(String name){
//        String sql = "SELECT * FROM DUMMY WHERE name = ?";
//        List<Dummy> dummies = jdbcTemplate.query(sql,new DummyCustomRowMapper(),name);
//
//        return Optional.ofNullable(dummies.get(0));
//
//    }
//



//    @Override
//    public Optional<Dummy> findByName(String name) {
//        return readTransactionOperations.execute(status -> {
//            String sql = "SELECT * FROM DUMMY where name = ?";
//            List<Dummy> dummies = jdbcTemplate.query(sql,(ResultSet rs, int rowNum) ->{
//                Dummy dummy = new Dummy();
//                dummy.setId(rs.getInt("id"));
//                dummy.setName(rs.getString("name"));
//                return dummy;
//            },name);
//
//            return Optional.ofNullable(dummies.get(0));
//
//        });
//
//    }




    @Override
    public int updateNameById(int id, String name) {
        String sql = "UPDATE DUMMY SET NAME =? WHERE ID  = ?";

        int update = jdbcTemplate.update((Connection conn) ->{
            PreparedStatement preparedStatement  = conn.prepareStatement(sql);

            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,id);
            return preparedStatement;
        });

        return update;
    }

    @Override
    public int deleteById(int id) {

        String sql = "DELETE FROM DUMMY WHERE id = ?";
        return jdbcTemplate.update((Connection conn)-> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1,id);

            return preparedStatement;

        });
    }
}
