package com.example.untiled1.domain.tutorial;

import com.example.untiled1.domain.tutorial.response.TutorialRp;
import com.example.untiled1.global.base.BaseRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


@Repository
@Slf4j
@Transactional
public class TutorialDTORepository extends BaseRepositoryImpl<TutorialRp> {

    /*@PersistenceContext
    protected EntityManager pcEntityManagerDefault;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.url}")
    String oracleUrl;

    @Value("${spring.datasource.password}")
    String oraclePassword;

    @Value("${spring.datasource.username}")
    String oracleUsername;

    *//* Calling Stored Procedure using JdbcTemplate *//*

    public Map<String, Object> searchUserByName(TutorialRq searchTerm) {
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));

        return jdbcTemplate.call(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall("{call search_users_proc(?,?,?,?,?)}");
                cs.setLong(  1, searchTerm.getIdRq());
                cs.setString(2, searchTerm.getTitleRq());
                cs.setString(3, searchTerm.getDescriptionRq());
                cs.setString(4, searchTerm.getStatusRq());
                cs.registerOutParameter(5, Types.REF_CURSOR);
                cs.execute();
                ResultSet rs = (ResultSet) cs.getObject(5);
                return cs;
            }
        }, parameters);
    }
*/
//    public ResultSet getAll() {
//        return (ResultSet) excuteResultSetUsingSp(
//                "AAA.search_all"
//                , null
//        );
//    }
//    public ResultSet excuteResultSetUsingSp(
//            String procedureName,
//            String params) {
//
//        OracleConnection cnn = null;
//        try {
//            OracleDataSource odcDataSource = new OracleDataSource();
//            odcDataSource.setURL(oracleUrl);
//            odcDataSource.setUser(oracleUsername);
//            odcDataSource.setPassword(oraclePassword);
//            cnn = (OracleConnection) odcDataSource.getConnection();
//        } catch (Exception e) {
//            System.out.println("DB connection Exception");
//            e.printStackTrace();
//        }
//
////        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
//        CallableStatement stmt = null;
//        ResultSet rs = null;
////        HibernateEntityManager hem = (HibernateEntityManager) this.pcEntityManagerDefault;
////        SessionImplementor sim = (SessionImplementor) hem.getSession();
////        Connection cnn = sim.connection();
//        try {
//            stmt = cnn.prepareCall("call " + procedureName + "(?)");
//            stmt.registerOutParameter(1, Types.REF_CURSOR);
//            stmt.execute();
//            rs = (ResultSet) stmt.getObject(1);
//            return rs;
//        } catch (Exception var13) {
//            throw new RuntimeException(var13.getMessage());
//        }
//
//
//    }


    public static String convertObject2Json(Object obj) {
        ObjectMapper objMapper = new ObjectMapper();
        String strResult = "";
        try {

            // get Oraganisation object as a json string
            strResult = objMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResult;
    }

    public List<TutorialRp> getAll1() {
        return super.excuteResultSetUsingSp(
                TutorialRp.class,
                true,
                1,
                "AAA.search_all"
                , (Object) null
        );
    }

    public List<TutorialRp> searchByTitle(TutorialRp tutorialRp) {
        return  super.excuteResultSetUsingSp(
                TutorialRp.class,
                true,
                1,
                "AAA.search_by_title"
                ,tutorialRp.getTitle()
                ,tutorialRp.getDescription()
                ,tutorialRp.getStatus()
                ,(Object) null
        );
    }

    public TutorialRp getById(Long id) {
        return  super.excuteResultSetUsingSp(
                TutorialRp.class,
                true,
                1,
                "AAA.find_by_id"
                , id
                ,null
        ).get(0);
    }

    public TutorialRp updateById(Long id, TutorialRp tutorialRp) {
        return super.excuteResultSetUsingSp(
                TutorialRp.class,
                true,
                1,
                "AAA.update_by_id"
                , id
                ,tutorialRp.getTitle()
                ,tutorialRp.getStatus()
                ,tutorialRp.getDescription()
                ,null
        ).get(0);
    }

    public TutorialRp createById(TutorialRp tutorialRp) {
        return  excuteResultSetUsingSp(
                TutorialRp.class,
                true,
                1,
                "AAA.create_tutorials"
                ,tutorialRp.getTitle()
                ,tutorialRp.getStatus()
                ,tutorialRp.getDescription()
                ,null
        ).get(0);
    }

    public void deleteById(Long id) {
        super.excuteResultSetUsingSp(
                TutorialRp.class,
                true,
                1,
                "AAA.tutorials_delete"
                , id
                ,null
        );
    }

    public void deleteAll() {
         super.excuteResultSetUsingSp(
                TutorialRp.class,
                true,
                1,
                "AAA.tutorials_delete_all"
                ,(Object) null
        );
    }


}
