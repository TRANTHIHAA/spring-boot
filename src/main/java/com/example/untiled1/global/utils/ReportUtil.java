package com.example.untiled1.global.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

public class ReportUtil {

    public static final ObjectMapper MAPPER_WITHOUT_CONFIG;
    static {
        MAPPER_WITHOUT_CONFIG = new ObjectMapper();
        MAPPER_WITHOUT_CONFIG.configOverride(BigDecimal.class)
                .setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
    }


//    public static Connection getConn(EntityManager entityManager) throws Exception {
//        HibernateEntityManager hem = (HibernateEntityManager) entityManager;
//
//        SessionImplementor sim = (SessionImplementor) hem.getSession();
//
//        Connection conn = sim.connection();
//        conn.setAutoCommit(false);
//
//        return conn;
//    }

    public static Connection getConn(EntityManager entityManager) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        SessionFactory sessionFactory = session.getSessionFactory();
        ConnectionProvider connectionProvider = sessionFactory.getSessionFactoryOptions()
                .getServiceRegistry()
                .getService(ConnectionProvider.class);
        return connectionProvider.getConnection();
    }

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


    public static JsonNode getJsonNodeAsPageable(JsonNode jnInput, int page, int size) {
        if (jnInput == null) {
            jnInput = ReportUtil.MAPPER_WITHOUT_CONFIG.createArrayNode();
        }

        ObjectNode onRet = ReportUtil.MAPPER_WITHOUT_CONFIG.createObjectNode();
        onRet.set("result", jnInput);
        onRet.put("page", page);
        onRet.put("size", size);

        int totalElements = 0;
        int totalPages = 0;
        if (!onRet.at("/result/0/totalElements").isMissingNode()) {
            totalElements = onRet.at("/result/0/totalElements").asInt();
            totalPages = size == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) size);
        }
        onRet.put("totalElements", totalElements);
        onRet.put("totalPages", totalPages);
        onRet.put("last", page + 1 >= totalPages);

        return onRet;
    }

}
